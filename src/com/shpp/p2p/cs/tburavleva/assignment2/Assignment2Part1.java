package com.shpp.p2p.cs.tburavleva.assignment2;

import com.shpp.cs.a.console.TextProgram;

import java.util.Scanner;

/**
 * The program finds the roots of a quadratic equation through the discriminant
 * A quadratic equation is an equation of the form
 * ax^2 + bx + c = 0, where a is not equal to 0
 */
public class Assignment2Part1 extends TextProgram {
    public void run() {
        double a, b, c;

        /*Get a, b and c values. Check the correctness of the input.*/
        do {
            print("Please enter a (a!=0): ");
            a = checkInput();
        } while (a == 0);                     // a must not be equal to 0

        print("Please enter b: ");
        b = checkInput();

        print("Please enter c: ");
        c = checkInput();

        roots(a, b, c);
    }

    /**
     * The method takes a value. Checks it. The value must be a number.
     * If the entered value is not a number, an error message is displayed.
     * You need to re-enter.
     *
     * @return: entered number
     */
    private double checkInput() {

        //create a new object of the "Scanner" class
        Scanner in = new Scanner(System.in);
        double number;
        if (in.hasNextDouble()) {
            number = in.nextDouble();
            return number;
        } else {
            println("Input Error. Try again: ");
            return checkInput();
        }
    }

    /**
     * Finding the roots of a quadratic equation.
     * The method takes the values a, b, and c
     * Calls a method that calculates the discriminant
     * If discriminant > 0, then the equation has two different real roots.
     * If discriminant = 0, then both roots are real and equal.
     * If discriminant < 0, then both roots are complex numbers.
     * Determines the roots of a quadratic equation. Then prints the result.
     *
     * @param a
     * @param b
     * @param c
     */
    private void roots(double a, double b, double c) {
        double d = discriminant(a, b, c);

        if (d > 0) {
            println("Roots are real and different:");
            print("x1 = " + ((-1) * b + Math.sqrt(d)) / (2 * a) +
                    "\nx2 = " + ((-1) * b - Math.sqrt(d)) / (2 * a));
        } else if (d == 0) {
            println("Roots are real and same:");
            print("x1, x2 = " + (-1) * b / (2 * a));
        } else {
            println("There are no real roots. Root are complex and different:");
            println("x1 = " + (-1.0) * b / (2 * a) + "+" + Math.sqrt((-1) * d) / (2 * a) + "i");
            print("x2 = " + (-1.0) * b / (2 * a) + "-" + Math.sqrt((-1) * d) / (2 * a) + "i");
        }
    }

    /**
     * discriminant = b^2 - 4a*c
     *
     * @param a:
     * @param b:
     * @param c:
     * @return: discriminant.
     */
    private double discriminant(double a, double b, double c) {
        return (Math.pow(b, 2) - (4 * a * c));
    }

}
