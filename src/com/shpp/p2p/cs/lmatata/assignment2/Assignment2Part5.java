package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 11;
    private static final int NUM_COLS = 16;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;
    /*The total width and height of all boxes.*/
    private static final int box_WIDTH = (int) (NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING);
    private static final int box_HEIGHT = (int) (NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING);


    public void run() {
        drawBoxes(NUM_ROWS, NUM_COLS);

    }

    private void drawBoxes(int numRows, int numCols) {
        for (int i = 0; i < numRows; i++)
            drawRowBoxes(i, numCols);
    }

    private void drawRowBoxes(int rowNumber, int numCols) {
        for (int i = 0; i < numCols; i++) {
            drawItemBox(rowNumber, i);

        }
    }

    private void drawItemBox(int rowNumber, int colNumber) {
        int x = (getWidth() - box_WIDTH) / 2;
        int y = (getHeight() - box_HEIGHT) / 2;
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
