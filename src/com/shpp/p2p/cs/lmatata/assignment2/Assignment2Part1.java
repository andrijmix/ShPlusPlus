package com.shpp.p2p.cs.lmatata.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {
    /**
     * Solution of the quadratic equation.
     */

    public void run() {
        double a;
        do {
            a = readDouble("Please enter a: ");
            if (a == 0) {
                println("Please enter correct value (not equals 0).\n" +
                        "Because it is will not be a quadratic equation.");
            }
        }
        while (a == 0); //continue entering a number while it equals "0"
        double b = readDouble("Please enter b: ");
        double c = readDouble("Please enter c: ");
        solutionOfTheQuadraticEquation(a, b, c);
    }

    private void solutionOfTheQuadraticEquation(double a, double b, double c) {

        /**
         * Definition of discriminant
         * */
        double D = Math.pow(b, 2) - 4 * a * c;
        /**
         * Determination of the possible roots of the equation
         * and displaying them on the screen
         * */
        if (D > 0) {
            double x1 = (-b + Math.sqrt(D)) / (2 * a);
            double x2 = (-b - Math.sqrt(D)) / (2 * a);
            println("There are two roots: " + x1 + " and " + x2);

        } else if (D == 0) {
            double x3 = -b / (2 * a);
            println("There is one root: " + x3);
        } else {
            println("There are no real roots");
        }
    }

}
