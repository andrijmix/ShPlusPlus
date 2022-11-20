package com.shpp.p2p.cs.abihle.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Algorithm for summing big numbers in string representation.
 */
public class Assignment5Part2 extends TextProgram {  //5
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
        //index of digit in numbers starting from end of string
        int n1i = n1.length() - 1;
        int n2i = n2.length() - 1;
        //carrying number to the next digit
        int nextDigit = 0;
        while (n1i >= 0 || n2i >= 0) {
            //digits at corresponding positions of each number,
            //or 0 if one of them is shorter
            int d1 = n1i >= 0 ? n1.charAt(n1i--) - '0' : 0;
            int d2 = n2i >= 0 ? n2.charAt(n2i--) - '0' : 0;
            //sum of current digits + carrying number from previous sum
            int d = d1 + d2 + nextDigit;
            //carrying number to next digit
            nextDigit = d / 10;
            //appending last digit of sum to result
            result.append((char) (d % 10 + '0'));
        }
        //if we have carrying number after all digits in n1, n2 have been added
        if (nextDigit != 0)
            result.append((char) (nextDigit + '0'));

        return result.reverse().toString();
    }
}