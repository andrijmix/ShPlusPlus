package com.shpp.p2p.cs.okim.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Assignment5Part3.java - Game on the road.
 */
//5
public class Assignment5Part3 extends TextProgram {

    /**
     * The program displays words containing letters entered by the user.
     */
    public void run() {

        ArrayList<String> wordList = importWord();

        while (true) {
            String letters = readLine("Enter 3 letters: ");
            wordSearch(wordList, letters.toLowerCase());
        }

    }

    /**
     * Import file with word.
     *
     * @return Array import word.
     */
    private ArrayList<String> importWord() {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            BufferedReader word = new BufferedReader(
                    new FileReader("en-dictionary.txt"));

            String line;
            while ((line = word.readLine()) != null) {
                wordList.add(line.toLowerCase());
            }

            word.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    /**
     * Search and print words containing the entered letters.
     *
     * @param wordList Array import word.
     * @param letters  Letters entered by the user.
     */
    private void wordSearch(ArrayList<String> wordList, String letters) {
        ArrayList<Integer> wordIndexWithLetters = wordIndexWithLettersSearch(wordList, letters);
        getWordByIndex(wordIndexWithLetters, wordList);
    }

    /**
     * Print words using indexes found.
     *
     * @param wordIndexWithLetters Array indexes containing word with entered letters.
     * @param wordList             Array import word.
     */
    private void getWordByIndex(ArrayList<Integer> wordIndexWithLetters, ArrayList<String> wordList) {
        for (Integer wordIndexWithLetter : wordIndexWithLetters) {
            println(wordList.get(wordIndexWithLetter));
        }
    }

    /**
     * Create array indexes containing word with entered letters.
     *
     * @param wordList Array import word.
     * @param letters  Letters entered by the user.
     * @return Array indexes containing word with entered letters.
     */
    private ArrayList<Integer> wordIndexWithLettersSearch(ArrayList<String> wordList, String letters) {
        ArrayList<Integer> wordIndexWithLetters = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            int lettersInWord = 0;

            lettersInWord = checkLettersInWord(letters, word, lettersInWord);

            if (lettersInWord == letters.length()) {
                wordIndexWithLetters.add(i);
            }

        }
        return wordIndexWithLetters;
    }

    /**
     * Check if the word contains the entered letters.
     *
     * @param letters       Letters entered by the user.
     * @param word          Import word.
     * @param lettersInWord Number found letters in word.
     * @return Number found letters in word.
     */
    private int checkLettersInWord(String letters, String word, int lettersInWord) {
        int indexPreviousLetter = 0;
        for (int i = 0; i < letters.length(); i++) {
            if (word.indexOf(letters.charAt(i), indexPreviousLetter) != -1) {
                indexPreviousLetter = word.indexOf(letters.charAt(i), indexPreviousLetter) + 1;
                lettersInWord += 1;
            }
        }
        return lettersInWord;
    }
}
