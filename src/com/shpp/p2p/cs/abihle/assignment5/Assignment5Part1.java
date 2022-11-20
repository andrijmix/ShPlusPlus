package com.shpp.p2p.cs.abihle.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Syllables count calculation.
 */
public class Assignment5Part1 extends TextProgram {  //5
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
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
        int result = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //if 'e' is last but not single vowel, skip it
            if (c == 'e' && i == word.length() - 1 && result > 0)
                continue;
            //if character is vowel and previous character isn't, syllables++
            if (isVowel(c) && (i == 0 || !isVowel(word.charAt(i - 1))))
                result++;
        }
        return result;
    }

    /**
     * Check that character is vowel (e,y,u,i,o,a)
     *
     * @param c character
     * @return true if vowel, false otherwise
     */
    private boolean isVowel(char c) {
        return "eyuioa".indexOf(c) != -1;
    }
}