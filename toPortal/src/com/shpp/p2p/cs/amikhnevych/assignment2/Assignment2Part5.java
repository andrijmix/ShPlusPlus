package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO:

It is necessary to draw a matrix of black boxes separated by "streets".

It may seem to you that there are dots at the intersections, but they are not there. Don't forget to center the picture!
 */

import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 7;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;


    public void run() {
        /*
         * find center:
         *  center windows - haft side big square
         * */
        double leftUpDot_x = (getWidth() / 2.0 - (NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING) / 2); // left upper coordinate x of rect
        double leftUpDot_y = (getHeight() / 2.0 - (NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING) / 2); // left upper coordinate y of rect
        /*
         * we have 4 counter :
         *   + count_r - count the ROW
         *   + j - count the COLUM
         *   + l - count coordinate between the box in ROW
         *   + k - count coordinate between the box in COL
         * */
        for (int l = (int) leftUpDot_x, count_r = 0; count_r < NUM_COLS; count_r++, l += (int) BOX_SIZE + BOX_SPACING) {
            for (int k = (int) leftUpDot_y, count_c = 0; count_c < NUM_ROWS; count_c++, k += (int) BOX_SIZE + BOX_SPACING) {
                add(BuildSquare(l, k));
            }
        }

    }

    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method return  object type square and fill it color     |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    x1 - Ox  coordinate                                                     |
     * //|    y1 - Oy  coordinate                                                     |
     * //+----------------------------------------------------------------------------+
     */
    private GObject BuildSquare(int i, int j) {
        GRect square = new GRect(i, j, BOX_SIZE, BOX_SIZE);
        square.setFilled(true);
        square.setFillColor(Color.black);
        return square;
    }
}
