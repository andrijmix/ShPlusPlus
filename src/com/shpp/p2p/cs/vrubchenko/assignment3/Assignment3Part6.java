package com.shpp.p2p.cs.vrubchenko.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part6 extends WindowProgram { //4
    //Choose fps > 10
    public static final int PAUSE_TIME = 1000 / 50;
    //Size of the oval
    public static final int SIZE_OVAL = 100;
    public static int count_ftp = 0;

    public void run() {
        long time_1 = System.currentTimeMillis();
        int i = 0; // variable color of Goval color
        long startTime = System.currentTimeMillis();//Time of start program
        long endTime = System.currentTimeMillis() + 5000;//Time of end program
        // println(startTime); can see start time
        int boxSize = SIZE_OVAL; // variable for transform oval
        //Program is working while start time < or = end time
        while (startTime <= endTime) {
            transformingOval(boxSize, i);
            //refresh start time
            startTime = System.currentTimeMillis() + PAUSE_TIME;
            boxSize++;
            //If i < 250 transform color
            if (i < 250) {
                i++;
            }
        }
        println(System.currentTimeMillis() - time_1);
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")


    private void transformingOval(int boxSize, int i) {
        count_ftp++;
        GOval o = new GOval(getWidth() / 2 - boxSize / 2, getHeight() / 2 - boxSize / 2, boxSize, boxSize);
        //set color outline
        Color Special_Color = new Color(0, i, i);
        o.setColor(Special_Color);
        o.setFilled(true);
        //add pause
        pause(PAUSE_TIME);
        //add form on application
        add(o);

    }
}