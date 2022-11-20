package com.shpp.p2p.cs.bkokhan.assignment5;
import com.shpp.cs.a.console.TextProgram;

/**
 * The program for counting syllables in a word.
 */

public class SyllableCounting extends TextProgram {
    /**
     *  Constantly counts the number of syllables in the word entered by the user.
     */
    public void run() {
        while (true) {
            String word = readLine("Enter a single word: ");
            if (wordIsCorrect(word)) {
                println("\tSyllable count: " + syllablesIn(word));
            } else {
                println("Wrong word. Please try again.");
            }
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
        int syllables = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (i != word.length() - 1) {
                if (isVowel(word.charAt(i)) && !isVowel(word.charAt(i + 1))) {
                    syllables++;
                }
            } else {
                if ((isVowel(word.charAt(i)) && word.charAt(i) != 'e') ||
                        (isVowel(word.charAt(i - 1)) && word.charAt(i) == 'e')) {

                    syllables++;
                }
            }
        }
        return syllables;
    }

    /**
     * Given a letter, estimates the for correspondence with vowels.
     *
     * @param ch Ordinal letter.
     * @return Correspondence to vowels.
     */
    private boolean isVowel(Character ch) {
        String[] vowel = new String[]{"a", "e", "i", "o", "u", "y"};
        for (String s : vowel) {
            if (s.equals(ch.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a word, evaluates the correctness of entering symbols.
     *
     * @param word A string containing a single word.
     * @return The correctness of entering characters
     */
    private boolean wordIsCorrect(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 97 || word.charAt(i) > 122) {
                return false;
            }
        }
        return true;
    }
}
