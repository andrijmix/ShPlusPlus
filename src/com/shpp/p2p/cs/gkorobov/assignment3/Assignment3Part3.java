package com.shpp.p2p.cs.gkorobov.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * this program get number and an exponent from user
 * and after that raise number to exponent
 * this program doesn't use library Math
 *
 * @author Gleb Korobov
 * @version 1.0
 */
public class Assignment3Part3 extends TextProgram {
    public void run() {
        double number = putNumber();
        int exponent = putExponent();

        raiseToPower(number, exponent);
    }

    /**
     * get number from user
     *
     * @return number
     */
    private int putExponent() {
        return readInt("Put an exponent: ");
    }

    /**
     * get exponent from user
     *
     * @return exponent
     */
    private double putNumber() {
        return readDouble("Put number: ");
    }

    /**
     * This method raise number to exponent
     * If exponent is 0 answer always will be 1
     * If exponent is negative program will division 1 by number
     * after make exponent positive and will multiply by itself exponent times
     *
     * @param number   user number
     * @param exponent user exponent
     */
    private void raiseToPower(double number, int exponent) {
        double answer = 0;
        if (exponent == 0) {
            System.out.println(number + "^" + exponent + " = 1");
        } else if (exponent > 0) {
            exponentBiggerThanZero(number, exponent, answer);
        } else {
            exponentSmallerThanZero(number, exponent, answer);
        }
    }

    /**
     * This method raise number to exponent
     * If exponent is positive number will multiply by itself exponent times
     *
     * @param number   user number
     * @param exponent user exponent
     * @param answer   answer after arithmetical count
     */
    private void exponentSmallerThanZero(double number, int exponent, double answer) {
        System.out.print(number + "^" + "(" + exponent + ")" + " = ");
        number = 1 / number;
        answer = number;
        exponent /= -1;
        for (int i = 1; i < exponent; i++) {
            answer *= number;
        }
        System.out.println(answer);
    }

    /**
     * This method raise number to exponent
     * If exponent is negative program will division 1 by number
     * after make exponent positive and will multiply by itself exponent times
     *
     * @param number   user number
     * @param exponent user exponent
     * @param answer   answer after arithmetical count
     */
    private void exponentBiggerThanZero(double number, int exponent, double answer) {
        answer = number;
        System.out.print(number + "^" + exponent + " = ");
        for (int i = 1; i < exponent; i++) {
            answer *= number;
        }
        System.out.println(answer);
    }
}
