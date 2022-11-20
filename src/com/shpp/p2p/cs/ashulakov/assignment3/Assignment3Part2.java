package com.shpp.p2p.cs.ashulakov.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Numbers game
 * Starting condition: ask the user for any positive integer
 * Result:  build a message chain that
 *          If n is even, divided it by 2 and repeat step for it
 *          If n is odd, multiply by 3 and add 1 and repeat step for it
 *          Continues this process until n equals 1
 */
public class Assignment3Part2 extends TextProgram {
    /**
     * Starts the creation of a message chain
     */
    @Override
    public void run() {
        // read some integer
        int someIntegerNumber = readInt("Enter a numeric (integer, positive): ");

        while(someIntegerNumber <= 0) {
            someIntegerNumber = readInt("Numeric must be integer and positive! Enter correctly: ");
        }
            // start recursion for the read number
            stepOfRecursion(someIntegerNumber);
    }

    /**
     * One step of the flow
     * @param n - current integer number
     * @return - true and stop flow if number == 1,
     *           half it if number is Even and run next step of recursion with n/2 value,
     *           3n+1 if number is Odd and run next step of recursion with 3n+1 value
     */
    private boolean stepOfRecursion(int n){
        // exit from recursion and report it
        if(n == 1){
            println ("The end");
            return true;
        }

        // check number is Even or not
        boolean numberIsEven = (n % 2 == 0);
        //start new step of recursion with appropriate message
        if (numberIsEven) {
            println ("" + n + " is even, so take it half: we get " + n / 2);
            return stepOfRecursion(n / 2);
        }else {
            println ("" + n + " is odd, so make 3n + 1: we get " + (3 * n + 1));
            return stepOfRecursion(3 * n + 1);
        }
    }
}