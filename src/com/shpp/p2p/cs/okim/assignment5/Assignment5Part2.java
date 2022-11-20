package com.shpp.p2p.cs.okim.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Assignment5Part2.java - Addition algorithm.
 */

// 00001 + 1
public class Assignment5Part2 extends TextProgram {  //5

    /**
     * Sit in a loop, reading numbers and adding them.
     */
    public void run() {
        while (true) {
            String n1 = readLine("Enter first number: ");
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
        String rev1 = reversed(n1);
        String rev2 = reversed(n2);

        /* Alignment of the number of digits.*/
        int difLength = rev1.length() - rev2.length();
        if (difLength > 0) {
            rev2 = alignLength(rev2, difLength);
        } else if (difLength < 0) {
            difLength *= -1;
            rev1 = alignLength(rev1, difLength);
        }

        return reversed(createReverseSum(rev1, rev2));
    }

    /**
     * Reverse sum calculation.
     *
     * @param rev1 First revers number.
     * @param rev2 Second revers number.
     * @return Reverse sum.
     */
    private String createReverseSum(String rev1, String rev2) {
        StringBuilder revSum = new StringBuilder();
        int remember = 0;
        for (int i = 0; i < rev1.length(); i++) {
            int value1 = rev1.charAt(i) - '0';
            int value2 = rev2.charAt(i) - '0';
            int sum = value1 + value2 + remember;
            if (sum < 10) {
                revSum.append(sum);
                remember = 0;
            } else {
                revSum.append(sum % 10);
                remember = sum / 10;
            }
        }
        if (remember != 0) {
            revSum.append(remember);
        }
        return revSum.toString();
    }

    /**
     * The method aligns the number of digits.
     *
     * @param rev       Reverse lesser number.
     * @param difLength The difference in the number of digits.
     * @return Lesser number with 0 for align.
     */
    private String alignLength(String rev, int difLength) {
        rev = rev + "0".repeat(Math.max(0, difLength));
        return rev;
    }

    /**
     * Reverse number.
     *
     * @param num Number to be reversed.
     * @return Reverse number.
     */
    private String reversed(String num) {
        StringBuilder reversedNum = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            char ch = num.charAt(i);
            reversedNum.append(ch);
        }
        return reversedNum.toString();
    }
}
