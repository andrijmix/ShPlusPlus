package com.shpp.p2p.cs.amikhnevych.assignment5;


import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * In this exercise, you write a program that prompts the user for a string of three letters and then outputs
 * the words that can be made from those letters.
 * The program must be looped, at each iteration the user is asked to enter 3 new letters
 * and process them (the letters can be of any case).
 */
public class Assignment5Part3 extends TextProgram {

    // start program
    public void run() {
        String[] array_words;
        try {
            array_words = readDictionary().toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            String string = readLine("Enter a string of three letters");

            searchWord(string, array_words);
        }
    }

    /**
     * This method creates the dictionary
     *
     * @return array of words
     */
    private ArrayList<String> readDictionary() throws IOException {

        FileReader name = new FileReader("src/com/shpp/p2p/cs/amikhnevych/assignment5/file/en-dictionary.txt");
        BufferedReader reader = new BufferedReader(name);

        ArrayList<String> words = new ArrayList<>();


        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            words.add(line);
        }


        return words;
    }

    /**
     * Search word in dictionary
     *
     * @param s          - string for searchWord
     * @param dictionary - array of words
     */
    private void searchWord(String s, String[] dictionary) {
        s = s.replaceAll("[^a-zA-Z]", "");   //delete the wrong symbols
        s = s.toLowerCase();                                 // put all symbols in lower case
        if (s.length() < 3) {
            println("try again: ");
            return;
        }
        for (String value : dictionary) {
            int index_1 = value.indexOf(s.charAt(0));
            int index_2 = value.indexOf(s.charAt(1), index_1 + 1);
            int index_3 = value.indexOf(s.charAt(2), index_2 + 1);
            if (index_1 != -1)   //get chart
                if (index_2 != -1)
                    if (index_3 != -1)
                        if (index_1 != index_2 && index_2 != index_3)
                            println(value);
        }
    }

}




