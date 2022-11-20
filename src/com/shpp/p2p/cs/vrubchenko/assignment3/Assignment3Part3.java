package com.shpp.p2p.cs.vrubchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * In this program we raise a number to a power
 * the number may not be an integer
 * degree == integer
 * the degree can be positive or negative and equal to 0
 */
public class Assignment3Part3 extends TextProgram {
    public void run() {                                          //5
        double base = readDouble("Enter the number to be raised: ");
        int exponent = readInt("Enter exponent: ");
        raiseToPower(base, exponent);//add decomposition with 2 parameters
    }

    /**
     * Choose exponent (positive, negative, equal to 0)
     */
    private void raiseToPower(double base, int exponent) {
        if (exponent < 0) {
            negativeExponent(base, exponent);
        } else if (exponent > 0) {
            positiveExponent(base, exponent);
        } else {
            exponentIsZero();
        }
    }

    /**
     * show result 1 if exponent == 0
     */
    private void exponentIsZero() {
        double result = 1;
        println(result);
    }

    /**
     * Add variable result that = base
     * And we make a loop where result = result * base,
     * Base does not change
     */
    private void positiveExponent(double base, int exponent) {
        double result = base;
        for (int i = 1; i < exponent; i++) {
            result *= base;
        }
        println(result);
    }

    /**
     * We need to do base in negative exponent base = 1 / Base;
     * Add variable result that = base
     * And we make a loop where result = result * base,
     * Base does not change
     */
    private void negativeExponent(double base, int exponent) {
        base = 1 / base;
        double result = base;
        for (int i = 1; i < exponent * -1; i++) {
            result *= base;
        }
        println(result);
    }
}
