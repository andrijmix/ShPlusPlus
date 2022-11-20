package com.shpp.p2p.cs.amikhnevych.assignment5;


import com.shpp.cs.a.console.TextProgram;

/**
 * write method
 * which takes the word as a parameter and turns the number of warehouses into a new one.
 */
public class Assignment5Part1 extends TextProgram {

    // start program
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        int count = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word, i)                           // letter is vowel
                    && (i == 0 || !isVowel(word, i - 1)) // prev letter isn vowel
                    && !lastIsE(word, i)) {                 // last letter in word is not vowel
                count++;
            }
        }
        if (count == 0) { // if our count is zero , set it is 1
            count = 1;
        }
        return count;
    }

    /**
     * Check last letter in word
     *
     * @param word - word were we check
     * @param i    - number of letter in that word
     * @return - true if we found the last letter is 'e'
     */
    private boolean lastIsE(String word, int i) {
        return word.charAt(i) == 'e' && i == word.length() - 1;
    }

    /**
     * Check letter is vowel or not
     *
     * @param word - word were we check
     * @param i    - number of letter in that word.
     * @return - true if letter is vowel
     */

    private boolean isVowel(String word, int i) {
        return word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' ||
                word.charAt(i) == 'u' || word.charAt(i) == 'y';
    }

}



