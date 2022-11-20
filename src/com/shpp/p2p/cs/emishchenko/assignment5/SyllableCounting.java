package com.shpp.p2p.cs.emishchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * SyllableCounting.java - Counting the number of syllables.
 **/
public class SyllableCounting extends TextProgram {
    public void run() {
        // Repeatedly prompt the user for a word and print out the estimated
        // number of syllables in that word.
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * The method checks the values entered by the user.
     *
     * @param word - the values entered by the user.
     * @return A string of letters.
     */
    private String checkWord(String word) {
        // Variable for the received value.
        String text = "";
        // The cycle of checking the entered value.
        while (text.equals("")) {
            word = word.toLowerCase();
            for (int j = 0; j < word.length(); j++) {
                // Check if the current character is a letter. If yes, add it.
                char ch = word.charAt(j);
                if (Character.isLetter(ch)) {
                    text += ch;
                }
            }
            // If the values are entered incorrectly,
            // then the user must enter the values again.
            if (text.equals("")) {
                word = readLine("try again: ");
            }
        }
        return text;
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        // The word entered by the user.
        word = checkWord(word);
        // Vowels of the alphabet.
        String[] vowels = {"a", "e", "i", "o", "u", "y"};
        // Counter  syllables.
        int counter = 0;
        // Counter vowels.
        int isVowel = 0;
        // Checking each letter of the word
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            String text = String.valueOf(ch);
            // Checks if each letter of a word matches any vowel
            for (int j = 0; j < vowels.length; j++) {
                // If we find a vowel.
                if (vowels[j].equals(text)) {
                    if (isVowel == 0) {
                        //If you find the first vowel, increase the syllable counter.
                        counter++;
                        isVowel = 1;
                        // Finishing the cycle "for".
                        j = vowels.length;
                    } else {
                        // If the second consecutive vowel,finishing the cycle "for".
                        j = vowels.length;
                    }
                    // If the check is finished, reset the vowel counter.
                } else if (j == vowels.length - 1) {
                    isVowel = 0;
                }
            }
        }
        // If the word is more than one letter
        if (word.length() > 1) {
            return checkE(counter, word, vowels);
        }
        return counter;
    }

    /**
     * The method checks if the word has the last letter 'e'.
     *
     * @param counter A string containing a single word.
     * @param word    Entered word.
     * @param vowels  Array of vowels.
     * @return Number of syllables in a word.
     */
    private int checkE(int counter, String word, String[] vowels) {
        char e = 'e';
        // The last letter in a word.
        char end = word.charAt(word.length() - 1);
        // Penultimate letter in a word.
        String preEnd = String.valueOf(word.charAt(word.length() - 2));

        for (String vowel : vowels) {
            // If the penultimate letter is a vowel and the last "e".
            if (preEnd.equals(vowel) && e == end) {
                return counter;
            }
        }
        // If the last "e".
        if (counter > 1 && e == end) {
            // Decreasing the syllable counter.
            return --counter;
        }
        return counter;
    }
}
