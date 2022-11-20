package com.shpp.p2p.cs.bkokhan.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


/**
 * The program searches for words in the dictionary that can be composed of three letters
 */
public class Assignment5Part3 extends TextProgram {
    /* Class variables */
    private String letters;
    private String[] words = new String[127142];

    /**
     * Writes data into an array and searches for words by key letters
     */
    public void run() {
        dictionary();
        while (true) {
            keyLetters();
            searchWords();
        }
    }

    /**
     * Write data from the file to the array list.
     */
    public void dictionary() {
        try {
            FileReader dictionary = new FileReader("en-dictionary.txt");
            Scanner scanner = new Scanner(dictionary);
            for (int i = 0; scanner.hasNextLine(); i++) {
                words[i] = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
        }

    }

    /**
     * Read the 3 letters entered by the user and check their correctness.
     */
    public void keyLetters() {
        while (true) {
            letters = readLine("Enter three letters:  ");
            if (lettersIsCorrect(letters)) {
                break;
            } else {
                println("Wrong letters. Please try again.");
            }
        }
    }

    /**
     * Given three letters, evaluates the correctness of entering symbols.
     *
     * @param letters A string containing three letters.
     * @return The correctness of entering characters
     */
    private boolean lettersIsCorrect(String letters) {
        letters = letters.toLowerCase();
        for (int i = 0; i < letters.length(); i++) {
            if (letters.charAt(i) < 97 || letters.charAt(i) > 122) {
                return false;
            }
        }
        return true;
    }

    /**
     * Search and output words by three key letters.
     */
    public void searchWords() {
        letters = letters.toLowerCase();
        char one = letters.charAt(0);
        char two = letters.charAt(1);
        char three = letters.charAt(2);

        for (int i = 0; i < 127142; i++) {
            if (words[i].indexOf(one) > 0 && words[i].indexOf(two) > 0 && words[i].indexOf(three) > 0 &&
                    words[i].indexOf(one) < words[i].indexOf(two) && words[i].indexOf(two) < words[i].indexOf(three)) {
                System.out.println(words[i]);
            }
        }
    }

}
