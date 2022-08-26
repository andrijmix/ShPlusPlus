package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO: Draw optical illusion
 */

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part5 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;


    public void run() {
/*
* find center:
*  center windows - haft side big square
* */
        double xCenter = getWidth() / 2.0 - NUM_ROWS*(BOX_SIZE+BOX_SPACING)/2;
        double yCenter = getHeight() / 2.0- NUM_COLS*(BOX_SIZE+BOX_SPACING)/2;


        /*
        * we have 4 counter :
        *   + i - count the ROW
        *   + j - count the COL
        *   + l - count coordinate between the box in ROW
        *   + k - count coordinate between the box in COL
        * */
        for (int l = (int)xCenter, i = 0; i < NUM_ROWS; i++, l += (int) BOX_SIZE + BOX_SPACING) {
            for (int k =(int) yCenter, j = 0; j < NUM_COLS; j++, k += (int) BOX_SIZE + BOX_SPACING) {
                add(BuildSquare(l, k));
            }


        }


    }

    private GObject BuildSquare(int i, int j) {
        GRect square = new GRect(i, j, BOX_SIZE, BOX_SIZE);
        square.setFilled(true);
        square.setFillColor(Color.black);
        return square;
    }
}
