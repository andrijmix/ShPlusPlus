package com.shpp.p2p.cs.amikhnevych.assignment2;
/* TODO: find root
 */

import com.shpp.cs.a.console.TextProgram;


public class Assignment2Part1 extends TextProgram {
    //a*(x^2) + b*x + c = 0
    public void run() {
//ask numbers on user
        double a = readDouble("Please enter a: ");
        double b = readDouble("Please enter b: ");
        double c = readDouble("Please enter c: ");

        //print Roots
        println(GetRoot(a, b, c));
    }


    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method returns roots on a string value                  |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    a - first coefficient                                                   |
     * //|    b - second coefficient                                                  |
     * //|    c - third coefficient                                                   |
     * //+----------------------------------------------------------------------------+
     */
    private String GetRoot(double a, double b, double c) {
        //    when are no real roots
        if (a == 0)
            return ("There are no real roots");
        double discriminant = Discriminant(a, b, c);
        //we can't have roof from negative number
        if (discriminant < 0)
            return ("There are no real roots ");

        double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        //if the roots are same - display one root
        if (x1 == x2) {
            return "There is one root: : " + x1;
        } //if the roots are not same - display two root
        else if (x1 > x2) {
            return "There are two roots: " + x1 + " and " + x2;
        } else {  /*
         * in other case - display NO REAL ROOTS*/
            return ("There are no real roots");
        }
    }

    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method returns Discriminant                             |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    a - first coefficient                                                   |
     * //|    b - second coefficient                                                  |
     * //|    c - third coefficient                                                   |
     * //+----------------------------------------------------------------------------+
     */
    private double Discriminant(double a, double b, double c) {
        return (b * b - 4 * a * c);
    }
}
