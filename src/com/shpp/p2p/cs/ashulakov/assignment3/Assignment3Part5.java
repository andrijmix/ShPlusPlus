package com.shpp.p2p.cs.ashulakov.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

/**
 * "Casino" game. (Its leads to addiction, be careful!)
 * Initially, $1 is at bet.
 * If a coin flips a head, then the winnings are and the bet is increased by $1.
 * If the reverse falls out - the winnings and the bet are doubled.
 * The game is over when the bet is equal to or greater than $20
 */
public class Assignment3Part5 extends TextProgram {
    // save random generator instance to the class variable
    private RandomGenerator rgen = RandomGenerator.getInstance();

    // starts the game process
    @Override
    public void run() {
        gamblingResult(0, 0, 0);
    }

    /**
     * Starts the gameplay recursively.
     * Exit from recursion - the size of the current rate >= 20
     *
     * @param passedGames  - the number of the games that passed
     * @param currentTotal - current total
     * @param lastGain     - last game earned sum
     */
    private void gamblingResult(int passedGames, int currentTotal, int lastGain) {
        // Game is over
        int newTotal = currentTotal + lastGain;
        if (passedGames != 0) {
            println("Your total is " + newTotal + "$");
        }
        if (newTotal < 20) {
            // Start game
            gamblingResult(passedGames + 1, newTotal, tossingCoin(1));
        } else {
            println("It took " + passedGames + " games to earn 20$");
        }
    }

    /**
     * Tossing a coin until a head has fallen
     * If a coin flips to the avers, finish the game, output of information of the earned sum
     * In the opposite case (reverse) start next toss with increased bet
     *
     * @param bet - current total value
     * @return - last toss earned bet
     */
    private int tossingCoin(int bet) {
        // calculating the gain in accordance with the result of the coin toss
        int gain = (isRevers()
                ? bet * 2
                : 1
        );

        // finish the game or start next toss
        if (gain == 1) {
            println("This game, you earned " + bet + "$");
            return bet;
        } else {
            return tossingCoin(gain);
        }
    }

    /**
     * Check coin side: true is Revers, false is Avers
     * @return  - Is a coin flips to the revers
     */
    private boolean isRevers(){
        return rgen.nextBoolean();
    }
}