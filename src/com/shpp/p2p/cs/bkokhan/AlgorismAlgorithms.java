package com.shpp.p2p.cs.bkokhan.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * The program calculates the sum of two numbers and outputs the result
 */
public class AlgorismAlgorithms extends TextProgram {
    /**
     * Continuously calculates the sum of two numbers entered by the user
     */
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
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


        StringBuilder result = new StringBuilder();

        int length1 = n1.length() - 1;
        int length2 = n2.length() - 1;

        int carry = 0;

        while (length1 >= 0 || length2 >= 0) {

            int number1 = 0;

            if (length1 >= 0) {
                number1 = n1.charAt(length1) - '0';
                length1--;
            }

            int number2 = 0;

            if (length2 >= 0) {
                number2 = n2.charAt(length2) - '0';
                length2--;
            }
            int sum = number1 + number2 + carry;
            int unit = sum % 10;
            carry = sum / 10;

            result.append((char) (unit + '0'));

        }
        if (carry > 0) {
            result.append((char) (carry + '0'));
        }

        return result.reverse().toString();
    }
}