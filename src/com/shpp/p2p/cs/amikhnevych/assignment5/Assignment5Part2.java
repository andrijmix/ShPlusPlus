package com.shpp.p2p.cs.amikhnevych.assignment5;


import com.shpp.cs.a.console.TextProgram;

/**
 * TODO:
 * takes 2 String values and returns a String value that is the sum of the received numbers..
 */
public class Assignment5Part2 extends TextProgram {

    // start program
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
    private int addNumericStrings(String n1, String n2) {

        StringBuilder sb = new StringBuilder(); // for save result
        int carry = 0; // transport one
        for (int i = n1.length() - 1, j = n2.length() - 1; i >= 0 || j >= 0
                || carry > 0; i--, j--) {// Add from the ones place of each number.
            int x = i < 0 ? 0 : n1.charAt(i) - '0';
            int y = j < 0 ? 0 : n2.charAt(j) - '0';
            int sum = x + y + carry;
            sb.append(sum % 10);
            carry = sum / 10; // one digit
        }
        return Integer.parseInt(sb.reverse().toString());
    }

}