/*
 * WordList.java
 * 
 * A WordList object represents a collection of 5-letter English words 
 * in which each word is associated with a unique integer key.
 * It allows Wordle to select a 5-letter word at random.
 * 
 */

import java.io.*;
import java.util.*;

public class WordList {
    // key = an integer
    // value = a 5-letter word
    private HashMap<Integer, String> contents;
    
    // random-number generator
    private Random rand;
    
    public WordList(String fileName, int randomSeed) {
        this.contents = new HashMap<Integer, String>();
 
        if (randomSeed != -1) {
            this.rand = new Random(randomSeed);
        } else {
            this.rand = new Random();
        }

        try {
            Scanner f = new Scanner(new File(fileName));
            
            int i = 0;
            while (f.hasNextLine()) {
                String word = f.nextLine();
                if (word.length() == 5) {
                    this.contents.put(i, word);
                    i++;
                }
            }
            
            f.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("could not process file of words");
        }
    }

    public String getRandomWord() {
        int whichOne = Math.abs(this.rand.nextInt()) % this.contents.size();
        return this.contents.get(whichOne);
    }
}