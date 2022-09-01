package com.shpp.p2p.cs.amikhnevych.assignment3;
/* TODO: write a program that asks the user for the number of minutes spent on exercise in the last seven days
 */

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part1 extends TextProgram {

    public void run() {

        int[] day_time = new int[7]; //array for indicator days

        //in loop fill the array
        for (int i = 1; i <= 7; i++) {
            day_time[i - 1] = readInt("How many minutes did you do on day " + i + " ?");
        }

        //2 counters for bad day
        int badDayCardio = 0;
        int badDayPressure = 0;

        //check which day is bad
        for (int i = 0; i < 7; i++) {
            if (day_time[i] < 30)
                badDayCardio++;
            if (day_time[i] < 40)
                badDayPressure++;
        }

        //print result according to template

        println("Cardiovascular health:");
        if (badDayCardio == 0)
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        else
            println("\tYou needed to train hard for at least " + badDayCardio + " more day(s) a week!");

        println("Blood pressure:");
        if (badDayPressure == 0)
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        else
            println("\tYou needed to train hard for at least " + badDayPressure + " more day(s) a week!");
    }

}