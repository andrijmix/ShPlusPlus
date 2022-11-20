package com.shpp.p2p.cs.emishchenko.assignment5;


import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Assignment5Part3.java - Game on the road.
 **/
public class Assignment5Part3 extends TextProgram {
    /**
     * The user enters the license plate of the car or three letters,
     * the program selects words that contain the entered three letters.
     * The result is displayed on the5NPT839 screen.
     */
    public void run() {
        while (true) {
            char[] threeLetters = checkThreeLetters(readLine("Enter the license plate of the car:  "));
            findPrintWord(readWords(), threeLetters);
        }
    }

    /**
     * The method checks the entered value of a license plate or three letters
     * and returns an array of three letters.
     *
     * @param licensePlate The meaning of the license plate of the car or three letters.
     * @return An array of three letters.
     */
    private char[] checkThreeLetters(String licensePlate) {
        // Variable for the received value.
        String text = "";
        // Create an array for three letters.
        char[] threeLetters = new char[3];
        // letter index.
        int index = 0;
        // The cycle of checking the entered value.
        while (text.equals("")) {
            // All letters are lowercase.
            licensePlate = licensePlate.toLowerCase();
            for (int j = 0; j < licensePlate.length(); j++) {
                // Check if the current character is a letter. If yes, add it.
                char ch = licensePlate.charAt(j);
                if (Character.isLetter(ch) && index < 3) {
                    // Write letters to array.
                    threeLetters[index] = ch;
                    index++;
                    text += ch;
                }
            }
            // If the values are entered incorrectly,
            // then the user must enter the values again.
            if (text.equals("")) {
                licensePlate = readLine("try again: ");
            }
        }
        return threeLetters;
    }

    /**
     * The method reads words from a file (dictionary) and stores them in an array.
     *
     * @return Array of words.
     */
    private ArrayList<String> readWords() {
        ArrayList<String> listWords = new ArrayList<>();
        try {
            // Open the dictionary file for reading.
            BufferedReader br = new BufferedReader(new FileReader("en-dictionary.txt"));
            while (true) {
                // Read words from a file and write to an array.
                String word = br.readLine();
                // When the words in the file are over, exit the loop.
                if (word == null) break;
                listWords.add(word);
            }
            br.close();
            // If the file does not exist.
        } catch (IOException e) {
            println("You have a problem.");
        }
        return listWords;
    }

    /**
     * The method selects words from an array that contain the entered three letters.
     *
     * @param dictionary   Array of words.
     * @param threeLetters An array of three letters.
     */
    private void findPrintWord(ArrayList<String> dictionary, char[] threeLetters) {
        // Checking all the words in the array one by one.
        for (String oneWord : dictionary) {
            // Find the first letter in a word.
            int index = 0;
            index = oneWord.indexOf(threeLetters[0], index);
            // Find the second letter in a word.
            if (index >= 0) {
                index = oneWord.indexOf(threeLetters[1], index + 1);
                // Find the third letter in the word.
                if (index > 0) {
                    index = oneWord.indexOf(threeLetters[2], index + 1);
                    // If three letters were found in the word, the word was displayed on the screen.
                    if (index > 0) {
                        println(oneWord);
                    }
                }
            }
        }
    }
}