package com.shpp.p2p.cs.gkorobov.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * this program simulates a game for two players first player put 1 dollar
 * second player throw a coin if it is eagle first player put on the table money
 * as much as it was
 * if coin show tails second player take all money from the table
 * game will end when someone earns 20 dollars
 *
 * @author Gleb Korobov
 * @version 1.0
 */
public class Assignment3Part5 extends TextProgram {
    public void run() {
        int winMoney = 0;

        int games = 0;
        game(winMoney, games);
    }

    /**
     * simulate a game
     *
     * @param winMoney it is how much player win money
     * @param games    it is how many takes game to win 20 dollars
     */
    private void game(int winMoney, int games) {
        while (winMoney < 20) {
            int firstPlayer = 1;

            int coin = (int) (Math.random() * 2);
            games++;

            while (coin == 1) {
                firstPlayer *= 2;
                coin = (int) (Math.random() * 2);
            }
            System.out.println("his game, you earned $" + firstPlayer);
            winMoney += firstPlayer;
            System.out.println("Your total is $" + winMoney);
        }
        System.out.println("It took " + games + " games to earn $" + winMoney);
    }
}
