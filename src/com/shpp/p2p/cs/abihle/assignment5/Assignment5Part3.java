package com.shpp.p2p.cs.abihle.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// натискання ентеру
//константа

/**
 * Game on the go.
 * Prints all words that can be made of three letters user input.
 */
public class Assignment5Part3 extends TextProgram { //4
    /**
     * Dictionary filename.
     */
    private static final String DICTIONARY_FILE = "en-dictionary.txt";
    /**
     * List of all words in dictionary
     */
    private List<String> dictionaryList;

    /**
     * Loads dictionary from file to List<String>,
     * or print message if exception occurred.
     */
    @Override
    public void init() {
        try {
            dictionaryList = loadDictionary(DICTIONARY_FILE);
        } catch (IOException e) {
            println(e.getMessage());
            println("No dictionary file found\nNothing to do, exiting....");
            System.exit(-1);
        }
    }

    /**
     * Application entry point.
     */
    @Override
    public void run() {
        println("Type q for exit");
        String letters;
        //while user didn't input "q", read input
        while (!"q".equals(letters = readLine("Input 3 letters:"))) {
            //if user input anything other than three letters
            if (!letters.matches("[a-zA-Z]{3}")) {
                println("You input something wrong, try again");
                continue;
            }
            //finds the right words
            List<String> words = findWords(letters);
            //prints them
            finePrint(words, letters);
        }
    }

    /**
     * Returns the list of  words, that contains letters in right order.
     *
     * @param letters three letter to search in word
     * @return List<String> of right words
     */
    private List<String> findWords(String letters) {
        List<String> result = new ArrayList<>();
        for (String word : dictionaryList)
            //add word to result list if it's right
            if (checkWord(letters.toLowerCase(), word.toLowerCase()))
                result.add(word);
        return result;
    }

    /**
     * Checks that word contains all letters in right order.
     *
     * @param letters three letter to search in word.
     * @param word    word to check
     * @return true if word is right, or false otherwise
     */
    private boolean checkWord(String letters, String word) {
        //index of char in word
        int ci = -1;
        //for each of three chars
        for (char c : letters.toCharArray()) {
            //find index of that char, starting after previous char index
            ci = word.indexOf(c, ci + 1);
            //if char not found, word isn't right
            if (ci == -1)
                return false;
        }
        return true;
    }

    /**
     * Prints words to console in five columns, upper-casing corresponding letters.
     *
     * @param words   list of words to print
     * @param letters letters in word to print them uppercase
     */
    private void finePrint(List<String> words, String letters) {
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            StringBuilder sb = new StringBuilder(word.toLowerCase());
            int wi = -1;
            for (char c : letters.toLowerCase().toCharArray()) {
                //find index of char in word
                wi = word.indexOf(c, wi + 1);
                //upper-case that char
                sb.setCharAt(wi, Character.toUpperCase(c));
            }
            //print word in row
            print(String.format("%-20s", sb.toString()));
            //every 5 words, start printing from next line
            if ((i + 1) % 5 == 0)
                println();
        }
        println();
    }

    /**
     * Load words from file.
     *
     * @param fileName file name of dictionary
     * @return list of words
     * @throws IOException
     */
    private List<String> loadDictionary(String fileName) throws IOException {
        Path path = Paths.get(DICTIONARY_FILE);
        return Files.readAllLines(path);
    }
}
