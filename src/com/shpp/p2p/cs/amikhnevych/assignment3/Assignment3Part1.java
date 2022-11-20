package com.shpp.p2p.cs.amikhnevych.assignment3;

import com.shpp.cs.a.console.TextProgram;

/* TODO:
Your task: write a program that asks the user for the number of minutes spent on exercise in the last seven days,
and accordingly reports the following:

whether enough time was devoted to exercise for cardiovascular health, and, if not, outputs:
how many days of healthy living (with exercises more than 30 minutes) are not enough for the recommended schedule.
whether there was enough exercise to reduce blood pressure and cholesterol, and if not, it displays:
how many days the user did not make it to 40 minutes of exercise per day.
 */
public class Assignment3Part1 extends TextProgram {


    static final int MINUTE_FOR_CARDIO = 30; //workout duration for cardio
    static final int MINUTE_FOR_PRESSURE = 40; //workout duration for pressure


    static int goodCardioDay = 5; // how many good day for cardio?
    static int goodDayPressure = 3;  //and for pressure?
    static int dayCardioMin = 0;  //count good day for cardio
    static int dayPressureMin = 0; // and for pressure

    public void run() {

        AskData();
        ReportStatus();

    }

    //we show result
    void ReportStatus() {

        //print result according to template

        println("Cardiovascular health:");
        if (dayCardioMin >= goodCardioDay)
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        else
            println("\tYou needed to train hard for at least " + (goodCardioDay - dayCardioMin) + " more day(s) a week!");

        println("Blood pressure:");
        if (dayPressureMin >= goodDayPressure)
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        else
            println("\tYou needed to train hard for at least " + (goodDayPressure - dayPressureMin) + " more day(s) a week!");
    }

    //we just ask datta
    void AskData() {

        //in loop fill the array
        for (int i = 1; i <= 7; i++) {
            int day_time = readInt("How many minutes did you do on day " + i + " ?");
            while (day_time < 0) {
                day_time = readInt("Enter positive number!");
            }
            if (day_time >= MINUTE_FOR_CARDIO)
                dayCardioMin++;
            if (day_time >= MINUTE_FOR_PRESSURE)
                dayPressureMin++;
        }


    }
}