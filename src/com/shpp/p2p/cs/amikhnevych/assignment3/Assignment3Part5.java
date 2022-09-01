package com.shpp.p2p.cs.amikhnevych.assignment3;
/* TODO:
Part 5 - St. Petersburg game
This is a hypothetical casino game with a simple ideology. Two people play: the lucky one and the sweaty one.
The game ends when the first person earns $20 or more. The sweaty one puts $1 on the table, and the lucky
one starts flipping a coin. If it is an eagle, then the sweaty person adds exactly the same amount
to the amount on the table. If it's a tail, everything on the table goes to the lucky person.
If the lucky person ends up with less than $20, then the game is repeated.

Theoretically, even the first game can result in the lucky person getting $32, $64, … instead of 20,
depending on how lucky he is with the eagles.

That is, theoretically, one global iteration can earn an infinite amount of money.
On the other hand, it may turn out that there will be frequent tails,
and the sum of all the winnings will be the same $20 (or close to it).

The program should simulate a similar game and display the following on the screen:

This game, you earned $1
Your total is $1
This game, you earned $1
Your total is $2
This game, you earned $1
Your total is $3
This game, you earned $128
Your total is $131
It took 4 games to earn $20
 */

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;


public class Assignment3Part5 extends TextProgram {

    public void run() {
        int bank = 0;  //On start the lucky have zero $
        int money_on_table = 1; //and the sweaty put on table 1$
        int n_game = 0; //count games
        while (bank < 20) {   //play until the lucky have less 20$
            n_game++;
            if (Coin().equals("eagle")) {
                money_on_table *= 2;
                println("Your total is $" + money_on_table);
            } else if (Coin().equals("tails")) {
                bank += money_on_table;
                println("This game, you earned $" + bank);
                money_on_table = 1;  //the sweaty put on table 1$
            }
        }
        println("It took "+n_game+" games to earn $"+bank);
    }

    /**
     * @return eagle or tails
     */
    private String Coin() {
        Random randomNum = new Random();
        if (randomNum.nextInt(2) == 0)
            return "eagle";
        else
            return "tails";
    }

}