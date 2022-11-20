package com.shpp.p2p.cs.okim.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * SteganographyLogic.java - Counting the number of syllable.
 */
//
public class Assignment5Part1 extends TextProgram {  //5

    /**
     * Repeatedly prompt the user for a word and print out the estimated
     * number of syllables in that word.
     */
    public void run() {

        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {

        boolean[] vowelsInWord = checkVowelsInWord(word);
        boolean[] syllablesInWord = checkSyllablesInWord(word, vowelsInWord);
        return syllablesNumInWord(syllablesInWord);

    }

    /**
     * Method to check if each letter in a word is a vowel.
     *
     * @param word A string containing a single word.
     * @return Boolean array containing information for each letter in a word (vowel/not vowel).
     */
    private boolean[] checkVowelsInWord(String word) {

        char[] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U', 'y', 'Y'};
        boolean[] vowelsInWord = new boolean[word.length()];

        for (int i = 0; i < word.length(); i++) {
            vowelsInWord[i] = false;
            for (char vowel : vowels) {
                if (word.charAt(i) == vowel) {
                    vowelsInWord[i] = true;
                    break;
                }
            }
        }
        return vowelsInWord;
    }

    /**
     * Checking for syllable count exceptions.
     *
     * @param word         A string containing a single word.
     * @param vowelsInWord Boolean array containing information for each letter in a word (vowel/not vowel).
     * @return Boolean array containing information for each letter in a word,
     * with the exception for 'e' at the end of a word and double vowels.
     */
    private boolean[] checkSyllablesInWord(String word, boolean[] vowelsInWord) {
        if (word.charAt(word.length() - 1) == 'e' || word.charAt(word.length() - 1) == 'E') {
            vowelsInWord[word.length() - 1] = false;
        }

        for (int i = word.length() - 1; i > 0; i--) {
            if (vowelsInWord[i] == vowelsInWord[i - 1] && vowelsInWord[i]) {
                vowelsInWord[i] = false;
            }
        }
        return vowelsInWord;
    }

    /**
     * Counting the number of syllables.
     *
     * @param syllablesInWord Boolean array containing information for each letter in a word,
     *                        with the exception for 'e' at the end of a word and double vowels.
     * @return Number of syllables.
     */
    private int syllablesNumInWord(boolean[] syllablesInWord) {
        int result = 0;
        for (boolean b : syllablesInWord) {
            if (b) {
                result += 1;
            }
        }
        if (result == 0) {
            result = 1;
        }
        return result;
    }
}
