package com.shpp.p2p.cs.amikhnevych.assignment3;

import com.shpp.cs.a.console.TextProgram;

/* TODO:

Take some positive integer and call it n
If n is even, then divide it by 2
If n is odd, then multiply by 3 and add 1
Continue this process until n is equal to 1
 */
public class Assignment3Part2 extends TextProgram {
    static int n;

    public void run() {

        AskData();
        funnyGame();


    }

    //hmm, I will ask data
    void AskData() {
        //Ask number
        do {
            n = readInt("Enter a only positive number: "); // we need only positive number
        } while (n <= 0);
    }

    //then I run game
    void funnyGame() {
        do {
            if (n % 2 == 0) { // if even number
                println(n + " is even so I take half: " + (n /= 2));
            } else      // if odd  number
                println(n + " is odd so I make 3n + 1: " + (n = n * 3 + 1));
        } while (n != 1);
        println("end.");
    }

}

