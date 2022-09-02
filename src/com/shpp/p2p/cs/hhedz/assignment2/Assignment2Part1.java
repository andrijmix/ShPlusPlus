package com.shpp.p2p.cs.hhedz.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {
    double a, b, c, d; //declare a variables for the coefficients of the equation and the discriminant

    public void run() {
        //We try to execute the method, if an error occurs, we get into the catch block
        try {
            Init();
        }
        //if the user enters letters instead of number or some others incorrect values
        //program will finish and the user will receive message about mistake
        catch (Exception e) {
            System.out.println("You have entered incorrect values");
            return;
        }
        //if variable a is zero, the equation is not quadratic
        if (a == 0) System.out.println("a cannot be 0 in quadratic equation");
        else {
            findRoots();
        }
    }

    /*
    the user enters a value
     */
    void Init() {
        a = readDouble("Please enter a:");
        b = readDouble("Please enter b:");
        c = readDouble("Please enter c:");
    }

    /*
    this method calculates the discriminant and then finds the roots with it
     if the equation has roots, method output them to the console
     */
    void findRoots() {
        d = b * b - 4 * a * c;
        //we check how many roots the equation has
        if (d < 0) {
            System.out.println("There are no real roots");
        }
        if (d == 0) {
            System.out.println("There is one root");
            System.out.println((-b) / (2 * a));
        }
        if (d > 0) {
            System.out.println("There are two roots");
            System.out.println((-b - Math.sqrt(d)) / (2 * a));
            System.out.println((-b + Math.sqrt(d)) / (2 * a));
        }
    }
}