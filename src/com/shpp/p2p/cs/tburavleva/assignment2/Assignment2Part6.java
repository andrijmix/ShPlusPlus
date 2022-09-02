package com.shpp.p2p.cs.tburavleva.assignment2;

import acm.graphics.GOval;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    /* The user can change the initial size of the window.*/
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 240;

    /* oval overlay */
    public static final int OVER = 20;

    /* The number of ovals our caterpillar
     * You can change the number of ovals of the caterpillar
     */
    public static final int CATERPILLAR_NUMBER = 4;

    public void run() {
        /* calculate diameter.
         * The diameter depends on the size of the window
         * and the number of ovals
         */
        double diameter = (double)(getWidth() / CATERPILLAR_NUMBER) +
                (double)(OVER * (CATERPILLAR_NUMBER - 1)) / CATERPILLAR_NUMBER;
        double x = 0, y = OVER;
        for (int n = CATERPILLAR_NUMBER; n > 0; n--) {
            add(createCircle(x, y, diameter));
            x += diameter - OVER;
            if (y == OVER)
                y = 0;
            else
                y = OVER;
        }
    }

    /**
     * The method creates  object circle
     *
     * @param x        The x coordinate of the upper-left corner of the bounding circle box
     * @param y        The y coordinate of the upper-left corner of the bounding circle box
     * @param diameter diameter of the object circle
     * @return created object
     */
    private GOval createCircle(double x, double y, double diameter) {
        GOval oval = new GOval(x, y, diameter, diameter);
        oval.setFillColor(Color.GREEN);
        oval.setFilled(true);
        oval.setColor(Color.RED);
        return oval;
    }
}
