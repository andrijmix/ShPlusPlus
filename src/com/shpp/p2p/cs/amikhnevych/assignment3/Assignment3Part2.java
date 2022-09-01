package com.shpp.p2p.cs.amikhnevych.assignment3;
/* TODO: game "By numbers-hailstones"
 */

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part2 extends TextProgram {

    public void run() {

//Ask number
        int n = readInt("Enter a number: ");

        while (n != 1) {
            if (n % 2 == 0) { // if even number
                println(n + " is even so I take half:" + (n /= 2));
            } else      // if odd  number
                println(n + " is odd so I make 3n + 1:" + (n = n * 3 + 1));
        }

    }

}