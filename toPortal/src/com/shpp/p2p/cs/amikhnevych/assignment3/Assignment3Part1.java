package com.shpp.p2p.cs.amikhnevych.assignment3;
/* TODO:
Your task: write a program that asks the user for the number of minutes spent on exercise in the last seven days,
and accordingly reports the following:

whether enough time was devoted to exercise for cardiovascular health, and, if not, outputs:
how many days of healthy living (with exercises more than 30 minutes) are not enough for the recommended schedule.
whether there was enough exercise to reduce blood pressure and cholesterol, and if not, it displays:
how many days the user did not make it to 40 minutes of exercise per day.
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
        //we start the variable  on -2, Because we have 7 days, only 5 of them should be normal for cardio
        int badDayCardio = -2;
        int badDayPressure = -4;  //and 3 for Pressure

        //check which day is bad
        for (int i = 0; i < 7; i++) {
            if (day_time[i] < 30)
                badDayCardio++;
            if (day_time[i] < 40)
                badDayPressure++;
        }

        //print result according to template

        println("Cardiovascular health:");
        if (badDayCardio <= 0)
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        else
            println("\tYou needed to train hard for at least " + badDayCardio + " more day(s) a week!");

        println("Blood pressure:");
        if (badDayPressure <= 0)
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        else
            println("\tYou needed to train hard for at least " + badDayPressure + " more day(s) a week!");
    }

}