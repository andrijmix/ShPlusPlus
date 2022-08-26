package com.shpp.p2p.cs.amikhnevych.assignment2;
/* TODO: find root
 */

import com.shpp.cs.a.console.TextProgram;


public class Assignment2Part1 extends TextProgram {
    //a*(x^2) + b*x + c = 0
    public void run() {
//ask numbers
        int a = readInt("Please enter a: ");
        int b = readInt("Please enter b: ");
        int c = readInt("Please enter c: ");
//        int a = 5;
//        int b = 2;
//        int c = 3;

        //print Roots
        println(GetRoot(a, b, c));

    }

    //find roots
    private String GetRoot(int a, int b, int c) {
        //    when are no real roots
        if (a == 0)
            return ("There are no real roots");
        double Decreminat = Decreminat(a, b, c);
        //we can't have roof from negative number
        if (Decreminat < 0)
            return ("There are no real roots ");

        double x1 = (-b + Math.sqrt(Decreminat)) / (2 * a);
        double x2 = (-b - Math.sqrt(Decreminat)) / (2 * a);
//        x1=Math.round(x1);
//        x2=Math.round(x2);

        //if the roots are same - display one root
        if (x1 == x2)
            return "There is one root: : " + x1;

        /*
        * if have NaN or infinity - display NO REAL ROOTS*/
        if (x1 == Double.NaN || x2 == Double.NaN || x1 == Double.POSITIVE_INFINITY || x2 == Double.POSITIVE_INFINITY
                || x1 == Double.NEGATIVE_INFINITY || x2 == Double.NEGATIVE_INFINITY)
            return ("There are no real roots");

        return "There are two roots: " + x1 + " and " + x2;
    }

    //calculate Decreminant
    private double Decreminat(int a, int b, int c) {
        return (b * b - 4 * a * c);
    }
}
