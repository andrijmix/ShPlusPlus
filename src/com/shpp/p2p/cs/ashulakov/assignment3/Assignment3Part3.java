package com.shpp.p2p.cs.ashulakov.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Find base (double) to power exp (integer)
 */
public class Assignment3Part3 extends TextProgram {
    // Starting lookup for answer
    @Override
    public void run() {
        double base = readDouble("Enter a base (type of double): ");
        int exp = readInt("Enter a exponent (integer): ");
        println("" + base + " in power " + exp + " is equal: " + raiseToPower(base, exp));
    }

    /**
     * Analog for method Math.pow()
     *
     * @param base     - the base of the exponent
     * @param exponent - exponent
     * @return value equal Math.pow(base, exponent)
     */
    private double raiseToPower(double base, int exponent) {
        if (exponent > 0) return base * raiseToPower(base, exponent - 1);
        else if (exponent < 0) return 1 / base * raiseToPower(base, exponent + 1);
        return 1;
    }
}