package com.shpp.p2p.cs.gkorobov.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This program take minutes from each day per weak
 * and calculate did you do enough exercise, or you need more
 * @author Gleb Korobov
 * @version 1.0
 */
public class Assignment3Part1 extends TextProgram {

    public void run() {
        takeTimesPearWeek();
    }

    /**
     * This method get minutes from each day from user
     */
    private void takeTimesPearWeek() {
        int time = 0;
        int cardiovascular = 0;
        int bloodHealth = 0;

        for (int i = 1; i < 8; i++) {
            do {
                time = readInt("How many minutes did you do on day " + i + "? ");
            } while (time < 0);
            if (time >= 30) {
                cardiovascular++;
                if (time >= 40) bloodHealth++;
            }
        }
        showMessageToUser(cardiovascular, bloodHealth);
    }

    /**
     * this method show how much day you need to train Cardiovascular and Blood pressure
     * @param cardiovascular How many days you did a point for Cardiovascular
     * @param bloodHealth How many days you did a point for Blood pressure
     */
    private void showMessageToUser(int cardiovascular, int bloodHealth) {
        System.out.println("Cardiovascular health:");


        if (cardiovascular >= 5) System.out.println("   Great job! You've done enough exercise for cardiovascular health.");
        else System.out.println("\tYou needed to train hard for at least " + (5 - cardiovascular) + " more day(s) a week!");

        System.out.println("Blood pressure:");

        if (bloodHealth >= 3) System.out.println("  Great job! You've done enough exercise to keep a low blood pressure.");
        else System.out.println("\tYou needed to train hard for at least " + (3 - bloodHealth ) + " more day(s) a week!");
    }
}
