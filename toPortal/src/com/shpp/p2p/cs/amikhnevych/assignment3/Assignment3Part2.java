package com.shpp.p2p.cs.amikhnevych.assignment3;
/* TODO:

Take some positive integer and call it n
If n is even, then divide it by 2
If n is odd, then multiply by 3 and add 1
Continue this process until n is equal to 1
 */

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part2 extends TextProgram {

    public void run() {

//Ask number
        int n;
        do {
            n = readInt("Enter a number: "); // we need only positive number
        } while (n <= 0);


        while (n != 1) {
            if (n % 2 == 0) { // if even number
                println(n + " is even so I take half: " + (n /= 2));
            } else      // if odd  number
                println(n + " is odd so I make 3n + 1: " + (n = n * 3 + 1));
        }
        println("end.");
    }

}