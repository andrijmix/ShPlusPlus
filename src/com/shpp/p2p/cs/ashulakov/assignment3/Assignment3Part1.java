package com.shpp.p2p.cs.ashulakov.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Aerobic
 * Starting condition: ask the user for his weekly aerobic load
 * Result:  make a recommendation according to which it is necessary to have
 * 30+ minutes of exercise 5 times a week
 * and at least 3 times a week to have 40+ minutes of exercise
 */
public class Assignment3Part1 extends TextProgram {
    // minimum days with 30 minutes activity
    private static int WITH_30_MINUTES_ACTIVITY = 5;
    // minimum days with 40 minutes activity
    private static int WITH_40_MINUTES_ACTIVITY = 3;

    // Lookup for answer
    @Override
    public void run() {
        println("Please enter your weekly activity minutes: ");
        checkActivity(1, 0, 0);
    }

    /**
     * @param dayIndex - weekly index of a day
     * @return - day activity minutes
     */
    private boolean checkActivity(int dayIndex, int daysWith30Minutes, int daysWith40Minutes) {
        if (dayIndex > 7) {
            getActivitySummary(daysWith30Minutes, daysWith40Minutes);
            return true;
        }

        // initial day activity minutes
        int dayMinutes;
        dayMinutes = readInt("How many minutes did you do on day " + dayIndex + "? ");

        //control of wrong entry of minutes
        while (dayMinutes < 0) {
            dayMinutes = readInt(
                    "Minutes always is a positive value! So, how many minutes did you do on day "
                            + dayIndex + "?"
            );
        }

        return checkActivity(
                dayIndex + 1,
                minutesAnalysis(dayMinutes, daysWith30Minutes, 30),
                minutesAnalysis(dayMinutes, daysWith40Minutes, 40)
        );
    }

    /**
     * Analysis of minutes of activity by the load duration parameter
     *
     * @param minutes      - minutes of activity
     * @param currentState - current number of days with normal activity
     * @param compareWith  - value of activity in minutes with which we compare
     * @return new number of days with normal activity
     */
    private int minutesAnalysis(int minutes, int currentState, int compareWith) {
        // the activity value is compared with the minimum load indicator
        return (minutes >= compareWith) ? currentState + 1 : currentState;
    }

    /**
     * Print recomendation based on weekly activity parameters
     *
     * @param daysWith30Minutes - number of days with weekly activity 30+ minutes
     * @param daysWith40Minutes - number of days with weekly activity 40+ minutes
     */
    private void getActivitySummary(int daysWith30Minutes, int daysWith40Minutes) {
        println("Cardiovascular health:");
        println((daysWith30Minutes >= WITH_30_MINUTES_ACTIVITY)
                ? "Great job! You've done enough exercise for cardiovascular health."
                : "You needed to train hard for at least "
                + (WITH_30_MINUTES_ACTIVITY - daysWith30Minutes)
                + " more day(s) a week!"
        );

        println("Blood pressure:");
        println((daysWith40Minutes >= WITH_40_MINUTES_ACTIVITY)
                ? "Great job! You've done enough exercise to keep a low blood pressure."
                : "You needed to train hard for at least "
                + (WITH_40_MINUTES_ACTIVITY - daysWith40Minutes)
                + " more day(s) a week!"
        );
    }
}