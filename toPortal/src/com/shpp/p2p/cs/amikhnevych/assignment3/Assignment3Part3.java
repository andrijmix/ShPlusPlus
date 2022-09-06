package com.shpp.p2p.cs.amikhnevych.assignment3;

import com.shpp.cs.a.console.TextProgram;

/* TODO:
Your task is to write a method:

private double raiseToPower(double base, int exponent)

This method will take two parameters and calculate the value of the first parameter raised to the power of parameter
2. i.e. raiseToPower(2.0, 3) will give 2^3 = 8 The second parameter can be not only positive, but also negative.
For example, 0.5 to the power of -2 is 4. To simplify your life, let's assume that everything raised to the power of 0
 will become 1, that is, 0 to the power of 0 will be 1 😃

Since double cannot store infinitely long fractional numbers (as we discussed), all kinds of small errors are possible
when manipulating doubles. This is ok.

Also, you're not allowed to use Math.pow, Math.exp, Math.log and other convenient methods from Math in this subtask,
otherwise what's the point of writing one method, right? 😃
 */
public class Assignment3Part3 extends TextProgram {

    public void run() {
        double base = 0.5;
        int exponent = -2;
        println(raiseToPower(base, exponent));
    }

    /**
     * Returns the value of the first argument raised to the power of the
     * second argument. Special cases:
     *
     * <ul><li>If the second argument is positive or negative zero, then the
     * result is 1.0.
     * <li>If the second argument is 1.0, then the result is the same as the
     * first argument.
     * <li>If the second argument is negative number, then the
     * result is  1</sup>&frasl;{@code a}<sup>{@code b}</sup>
     *
     * @param base     the base.
     * @param exponent the exponent.
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    private double raiseToPower(double base, int exponent) {
        //check zero in exponent
        if (exponent == 0)
            return 1;
        if (exponent < 0) {
            base = 1 / base;//divided on 1 base
            exponent *= -1; //and make the counter a positive number
        }
        double root = 1;
        for (int i = 0; i < exponent; i++) {
            root *= base; // find the root in the loop
        }
        return root;
    }
}