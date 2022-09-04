package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;
    private static final int DIAMETER = 100;

    public void run() {
        /**
         * The "x" end "y" it is a coordinate of the upper-left corner of the rectangle
         * The (getWidth() - DIAMETER) and (getHeight() - DIAMETER) it is a width and height of rectangle.
         * */
        int x = (getWidth() - (getWidth() - DIAMETER)) / 2;
        int y = (getHeight() - (getHeight() - DIAMETER)) / 2;
        drawCircles();
        drawRectangle(x, y);
    }
/**
 * The drawCircles method for determining the coefficients indicating the coordinates of the circles
 * */
    private void drawCircles() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawItemCircle(j, i);
            }
        }
    }
/**
 * The drawItemCircle method for drawing circles around rectangle
 * */
    private void drawItemCircle(int j, int i) {
        /**
         * "i * (getWidth() - DIAMETER)" and "j * (getHeight() -  DIAMETER)" indicates where the circles
         * from the values APPLICATION_WIDTH and APPLICATION_HEIGHT will be placed.
         * */
        GOval o = new GOval(i * (getWidth() - DIAMETER),
                j * (getHeight() -  DIAMETER), DIAMETER, DIAMETER);
        o.setColor(Color.BLACK);
        o.setFilled(true);
        o.setFillColor(Color.BLACK);
        add(o);
    }
    /**
     * The drawRectangle method for drawing rectangle above the circles
     * */
    private void drawRectangle(int x, int y) {
        GRect r = new GRect(x, y, getWidth() - DIAMETER, getHeight() - DIAMETER);
        r.setColor(Color.WHITE);
        r.setFilled(true);
        r.setFillColor(Color.WHITE);
        add(r);
    }
}
