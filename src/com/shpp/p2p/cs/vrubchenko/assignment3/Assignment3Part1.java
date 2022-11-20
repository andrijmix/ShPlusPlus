package com.shpp.p2p.cs.vrubchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * In this method, we count your activity for the whole week,
 * if you did a little or a lot of cardio, you will be told about it
 */
public class Assignment3Part1 extends TextProgram { //4
    public void run() {
        int dayInWeek = 7;
        int countAerobic = 0;
        int countAerobicPlus = 0;
        /*
        We count all the days from the first to the last
        And add result of cardio
        */
        for (int day = 0; day < dayInWeek; day++) {
            double minutesInDay = readDouble("How many minutes did you do on day " + (day + 1) + ": ");
            if (minutesInDay >= 30) {
                countAerobic++;
                if (minutesInDay >= 40) {
                    countAerobicPlus++;
                }
            }
        }
        //Show result
        resultCountAerobic(countAerobic);
        resultCountAerobicPlus(countAerobicPlus);

    }

    /**
     * We choose whether there was enough exercise or not compared to the normal result
     */
    private void resultCountAerobicPlus(int countAerobicPlus) {
        int normalResult = 3; //normal result variable in the week

        if (countAerobicPlus < normalResult) {
            int needToTrain = normalResult - countAerobicPlus; // How much you need to work
            println("Blood pressure:");
            println("You needed to train hard for at least " + needToTrain + " more day(s) a week!");
        } else {
            println("Blood pressure:");
            println("Great job! You've done enough exercise to keep a low blood pressure.");
        }
    }

    /**
     * We choose whether there was enough exercise or not compared to the normal result
     */
    private void resultCountAerobic(int countAerobic) {
        int normalResult = 5; //normal result variable in the week
        if (countAerobic < normalResult) {
            int needToTrain = normalResult - countAerobic; //How much you need to work
            println("Cardiovascular health:");
            println("You needed to train hard for at least " + needToTrain + " more day(s) a week!");
        } else {
            println("Cardiovascular health:");
            println("Great job! You've done enough exercise for cardiovascular health");
        }
    }
}
