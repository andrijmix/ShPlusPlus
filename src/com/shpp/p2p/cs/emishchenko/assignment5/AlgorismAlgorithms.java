package com.shpp.p2p.cs.emishchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * AlgorismAlgorithms.java - Addition algorithm.
 **/

public class AlgorismAlgorithms extends TextProgram {
    public void run() {
        //Sit in a loop, reading numbers and adding them.
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        StringBuilder A = new StringBuilder();
        // Index of the first and second number.
        int index1 = n1.length() - 1;
        int index2 = n2.length() - 1;
        // The value that carries over to the second column.
        int secondDigit = 0;
        // Cycle through the largest number.
        for (int i = largestNumber(n1, n2).length() - 1; i >= 0; i--) {
            // Variables for storing character numbers.
            int value1;
            int value2;
            // Determines the value of each character of the first number.
            if (index1 >= 0) {
                value1 = n1.charAt(index1) - '0';
                index1--;

            } else {
                value1 = 0;
            }
            // Determines the value of each character of the second number.
            if (index2 >= 0) {
                value2 = n2.charAt(index2) - '0';
                index2--;

            } else {
                value2 = 0;
            }
            // The result is entered in a string.
            A.append(countValue(value1, value2, secondDigit, i));
            // The value that carries over to the second column.
            secondDigit = (value1 + value2 + secondDigit) / 10;
        }
        // Result string rovers.
        return reverseString(A.toString());
    }

    /**
     * The method performs bitwise addition of numbers.
     *
     * @param value1      Value of the digit of the first number.
     * @param value2      Value of the digit of the second number.
     * @param secondDigit Value that carries over to the second column.
     * @param i           Column number.
     * @return Addition result.
     */
    private String countValue(int value1, int value2, int secondDigit, int i) {
        // Add the numbers and get the number of the least significant digit.
        int firstDigit = (value1 + value2 + secondDigit) % 10;
        // The result is written to a string.
        char ch = (char) (firstDigit + '0');
        String A = String.valueOf(ch);
        // Write the zero digit of the number.
        if (i == 0 && secondDigit != 0) {
            ch = (char) (secondDigit + '0');
            A += String.valueOf(ch);
        }
        return A;
    }

    /**
     * Determine the largest number.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return The largest number.
     */
    private String largestNumber(String n1, String n2) {
        if (n1.length() > n2.length()) {
            return n1;
        }
        return n2;
    }

    /**
     * The method reverses the string.
     *
     * @param str String for reverse.
     * @return String after reverse.
     */
    private String reverseString(String str) {
        String A = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            A += String.valueOf(ch);
        }
        return A;
    }
}