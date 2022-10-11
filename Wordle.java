/*
 * Wordle.java 
 * 
 * An console-based implementation of a popular word-guessing game
 * 
 */

import java.util.*;

public class Wordle {
    // the name of a file containing a collection of English words, one word per line
    public static final String WORD_FILE = "words.txt";

    /*
     * printWelcome - prints the message that greets the user at the beginning of the game
     */  
    public static void printWelcome() {
        System.out.println();   
        System.out.println("Welcome to Wordle!");
        System.out.println("The mystery word is a 5-letter English word.");
        System.out.println("You have 6 chances to guess it.");
        System.out.println();
    }
    
    /*
     * initWordList - creates the WordList object that will be used to select
     * the mystery work. Takes the array of strings passed into main(),
     * since that array may contain a random seed specified by the user 
     * from the command line.
     */
    public static WordList initWordList(String[] args) {
        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }

        return new WordList(WORD_FILE, seed);
    }

    /*
     * readGuess - reads a single guess from the user and returns it
     * inputs:
     *   guessNum - the number of the guess (1, 2, ..., 6) that is being read
     *   console - the Scanner object that will be used to get the user's inputs
     */
    public static String readGuess(int guessNum, Scanner console) {
        String guess;
        do {
            System.out.print("guess " + guessNum + ": ");
            guess = console.next();
        } while (! isValidGuess(guess));

        return guess.toLowerCase();
    }

    public static boolean includes(String s, char c){
        if (s.indexOf(c) != -1){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAlpha(String s) {
        for (int i = 0; i < s.length(); i++){
            if (Character.isAlphabetic(s.charAt(i)) == false){
                return false;
            }
        }
        return true;
    }

    
    public static int numOccur(char c, String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if (c == s.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public static int numInSamePosn(char c, String s1, String s2){
        int count = 0;
        for (int i = 0; i < s1.length(); i++){
            if ((c == s1.charAt(i)) && (c == s2.charAt(i))){
                count++;
            }
        }
        return count;
    }
     

    /*
     * 
     * isValidGuess -  takes an arbitrary string guess and returns true
     * if it is a valid guess for Wordle, and false otherwise
     */

    public static boolean isValidGuess(String guess){
        if (guess.length() != 5){
            System.out.println("Your guess must be 5 letters long.");
            return false;
        } else if (isAlpha(guess) == false){
            System.out.println("Your guess must only contain letters of the alphabet.");
            return false;
        }
        return true;
    }


    public static boolean processGuess(String guess, String mystery){
        String result = "  ";
        for (int i = 0; i < 5; i++){
            if (guess.charAt(i) == mystery.charAt(i)){
                result += guess.charAt(i) + " ";
            } else if (guess.charAt(i) != mystery.charAt(i) && includes(mystery, guess.charAt(i)) == true){
                if (numOccur(guess.charAt(i), result) < numOccur(guess.charAt(i), mystery) && numInSamePosn(guess.charAt(i), guess, mystery) < numOccur(guess.charAt(i), mystery)){
                    result += "[" + guess.charAt(i) + "] ";
                }else{
                    result += "_ ";
                }
            }else{
                result += "_ ";
            }
            
        }
        System.out.println(result);
        if (guess.equals(mystery) == true){
            System.out.println();
            return true;
            
        }else{
            System.out.println();
            return false;
        }
    }



    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        printWelcome();

        WordList words= initWordList(args);

        String mystery = words.getRandomWord();


        for (int i = 1; i <= 6; i++){
            String s1 = readGuess(i, console);
            boolean s2 = processGuess(s1, mystery);

            if (s2){
                System.out.println("Congrats! You guessed it!");
                break;
            } else if (s2 == false && i ==6){
                System.out.println("Sorry! Better luck next time!");
        System.out.println("Your word was " + mystery);
            }

            }
            console.close();
        }
    

}