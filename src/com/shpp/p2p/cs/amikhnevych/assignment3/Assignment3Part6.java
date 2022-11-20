package com.shpp.p2p.cs.amikhnevych.assignment3;


import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;

/* TODO:
Part 6 - Five seconds of fame
We may not have covered animation yet, but we will soon. Well, for now, your task is to write
a program that displays 5 seconds of any animation (not taken from somewhere, but created by yourself) on the screen.
It can be any animation of your choice with any script.

five seconds, no more and no less. At the end of our course, we will combine all your creations into one cool video.
animation must contain 50+ frames. This means that FPS should be at least 10.
An animation that wants 5 should fit on any computer in 5s ±150ms. For this, System.currentTimeMillis() will help you
An animation that runs on some computer for less than 4s or more than 6s should get 2. Be creative!)
 */
public class Assignment3Part6 extends WindowProgram {


    public static final double BRICK_HEIGHT = 6.5; // it is brick height
    public static final double BRICK_WIDTH = 25; //and it is with for brick
    public static final int BRICKS_IN_BASE = 20; //brick in first row (base)
    public static final double FPS = 45;
    public static final int MILLISECOND_IN_SEC = 1000;
    int fps_count = 0;
    long time_1 = System.currentTimeMillis();

    ArrayList<Integer> list = new ArrayList<>();

    public void run() {
    
        showAnimation();
        println("fps on sec: " + fps_count / 5);
        println("delta time: " + (System.currentTimeMillis() - time_1) / MILLISECOND_IN_SEC);

    }

    void showAnimation() {

        long time_2 = System.currentTimeMillis() + 5 * MILLISECOND_IN_SEC;// end time
        int base = BRICKS_IN_BASE;  // local variable
        int x_start = (int) (getWidth() / 2 - (base * BRICK_WIDTH) / 2);  //start on Ox
        int y_start = (int) (getHeight() - BRICK_HEIGHT);                 // start on Oy
        while (System.currentTimeMillis() < time_2) {
            buildRowOfWall(x_start, y_start, base);
            base--;  //There will be one less brick in the new row
            x_start = (int) (getWidth() / 2 - (base * BRICK_WIDTH) / 2); //center the new line Ox
            y_start -= BRICK_HEIGHT;                 //center the new line Oy

        }
    }

    /**
     * Build row of wall brick
     *
     * @param x_start - Ox  coordinate
     * @param y_start - Oy  coordinate
     * @param base    - count brick in line
     */
    private void buildRowOfWall(int x_start, int y_start, int base) {

        for (int i = 0; i < base; i++) {
            add(buildRect(x_start, y_start));
            x_start += BRICK_WIDTH;
            pause(MILLISECOND_IN_SEC / FPS);
            fps_count++;
        }

    }

    /**
     * Create the GObject for rectangle
     *
     * @param x1 - Ox  coordinate
     * @param y1 - Oy  coordinate
     * @return the GObject
     */
    private GObject buildRect(int x1, int y1) {
        GRect rect = new GRect(x1, y1, BRICK_WIDTH, BRICK_HEIGHT);
        rect.setFillColor(Color.ORANGE);
        rect.setFilled(true);
        rect.setColor(Color.BLACK);
        return rect;
    }

}