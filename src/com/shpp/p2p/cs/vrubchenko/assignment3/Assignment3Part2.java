package com.shpp.p2p.cs.vrubchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This method solves the numbers of grids,
 * if the number is even, we divide it by 2,
 * if it is odd, we multiply it by 3 and add 1
 */
public class Assignment3Part2 extends TextProgram {//5

    public void run() {
        //Add the variable with which we work
        int n = readInt("Enter a number : ");
        //De make a loop until n is equal to 1, then we limit that n is a positive integer
        //And does not work with 0 and a negative number
        do {
            //We look at whether it is even or odd
            if (n % 2 == 0 && n > 0) {
                n = n / 2;
                println(n * 2 + " is even so I take half: " + n);
            } else if (n % 2 != 0 && n > 0) {
                n = n * 3 + 1;
                println((n - 1) / 3 + " is odd so I make 3n + 1: " + n);
            } else {
                break;
            }
        }
        //If n is not equal 1 do this program again
        while (n != 1);
    }

}