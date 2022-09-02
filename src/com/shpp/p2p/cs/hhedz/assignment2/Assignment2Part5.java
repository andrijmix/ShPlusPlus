package com.shpp.p2p.cs.hhedz.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 45;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 12;

    public void run() {
        drawRectangles();
    }

    private void drawRectangles() {
        //for centering rectangles
        double x = getWidth() / 2 - (NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING) / 2;
        double y = getHeight() / 2 - (NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING) / 2;
        //draw 6 squares in 5 rows
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                //in order to lay out the squares,
                // I add the size of the square and the distance between them and multiply by the i or j
                drawSquare(x + ((BOX_SPACING + BOX_SIZE) * j), y + ((BOX_SPACING + BOX_SIZE) * i), BOX_SIZE);
            }
        }
    }

    /*
          @param x The x coordinate of the upper-left corner of the bounding box for the rectangle.
          @param y The y coordinate of the upper-left corner of the bounding box for the rectangle.
          @param size The size of square
           */
    //this method draw black square
    private void drawSquare(double x, double y, double size) {
        GRect rect = new GRect(x, y, size, size);
        rect.setFillColor(Color.black);
        rect.setFilled(true);
        add(rect);
    }
}