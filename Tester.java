/*
 * Tester.java 
 * 
 * A program that you can use to make test calls to the methods that you 
 * write as part of your Wordle implementation. 
 */

import java.util.*;

public class Tester {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Wordle.processGuess("towel", "loyal");
        console.close();
    }
}