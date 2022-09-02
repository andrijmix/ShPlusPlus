package com.shpp.p2p.cs.tburavleva.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The user can change the initial size of the window.*/
    public static final int APPLICATION_WIDTH = 440;
    public static final int APPLICATION_HEIGHT = 340;

    /* The number of rows and columns in the grid, respectively.*/
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 4;
    /* The width and height of each box. */
    private static final double BOX_SIZE = 20;
    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 20;

    public void run() {

        /* Calculate the coordinates of the first box.
         * Coordinates depend on window size,
         */
        double x = getWidth() / 2.0 - (NUM_COLS / 2.0) * BOX_SIZE - ((NUM_COLS - 1) / 2.0) * BOX_SPACING;
        double y = getHeight() / 2.0 - (NUM_ROWS / 2.0) * BOX_SIZE - ((NUM_ROWS - 1) / 2.0) * BOX_SPACING;

        drawMatrix(x, y, NUM_COLS, NUM_ROWS, BOX_SIZE, BOX_SPACING);
        GLabel label = new GLabel("+",getWidth()/2,getHeight()/2);
        add(label);
    }

    /**
     * draw a matrix of black boxes, divided by “streets”.
     * The matrix will be displayed in the center of the window.
     *
     * @param x:      coordinate x of the first box in matrix
     * @param y:      coordinate y of the first box in matrix
     * @param column: number of columns
     * @param row:    number of rows
     * @param size:   box size
     * @param space:  spacing between the boxes
     */
    private void drawMatrix(double x, double y, int column, int row, double size, double space) {
        for (int i = row; i > 0; i--) {
            drawRow(x, y, column, size, space);
            y = y + size + space;
        }
    }

    /**
     * draw a row of black boxes, divided by space.
     *
     * @param x:      x-coordinate of the first box in row
     * @param y:      y-coordinate of the first box in row
     * @param column: number of columns in row
     * @param size:   box size
     * @param space:  spacing between the boxes
     */
    private void drawRow(double x, double y, int column, double size, double space) {
        for (int i = column; i > 0; i--) {
            add(createRect(x, y, size, size));
            x = x + size + space;
        }
    }

    /**
     * The method creates object rectangle
     *
     * @param x     The x coordinate of the upper-left corner of the rectangle
     * @param y     The y coordinate of the upper-left corner of the rectangle
     * @param width object width
     * @param hight object hight
     * @return created object
     */
    private GRect createRect(double x, double y, double width, double hight) {
        GRect res = new GRect(x, y, width, hight);
        res.setFillColor(Color.BLACK);
        res.setFilled(true);

        return res;
    }

}

