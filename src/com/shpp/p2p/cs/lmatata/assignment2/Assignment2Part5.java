package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS =5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /**
     * The total width and height of matrix.
     * ((NUM_COLS - 1) * BOX_SPACING) and (NUM_ROWS - 1) * BOX_SPACING) - expressions for removing
     * indentation from last elements for correct centring a matrix.
     * */
    private static final int matrix_WIDTH = (int) (NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING);
    private static final int matrix_HEIGHT = (int) (NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING);


    public void run() {

        drawMatrix();
    }
    /**
    * The drawMatrix method to run the initialization and draw a matrix.
     * */
    private void drawMatrix() {
        // column initialization
        for (int i = 0; i < NUM_ROWS; i++)
            drawMatrixRow(i);
    }
    /**
     * The drawMatrixRow method for create each row of matrix.
     * */
    private void drawMatrixRow(int rowNumber) {
        // row initialization
        for (int i = 0; i < NUM_COLS; i++) {
            drawMatrixItem(rowNumber, i);
        }
    }
/**
 * The drawMatrixItem method for create each item of matrix.
 * */
    private void drawMatrixItem(int rowNumber, int colNumber) {
        // The "x" end "y" are the initial position from which the method will draw our matrix
        int x = (getWidth() - matrix_WIDTH) / 2;
        int y = (getHeight() - matrix_HEIGHT) / 2;
        // n1 and n2 coefficient for remove indents at extreme elements
        int n1 = colNumber < NUM_COLS ? 1 : 0;
        int n2 = rowNumber < NUM_ROWS ? 1 : 0;
        GRect r = new GRect((x + colNumber * (BOX_SIZE + BOX_SPACING*n1)),
                (y + rowNumber * (BOX_SIZE + BOX_SPACING*n2)),
                BOX_SIZE, BOX_SIZE);
        r.setFilled(true);
        r.setColor(Color.BLACK);
        r.setFillColor(Color.BLACK);
        add(r);
    }
}
