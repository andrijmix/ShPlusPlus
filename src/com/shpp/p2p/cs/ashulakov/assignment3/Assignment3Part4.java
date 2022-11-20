package com.shpp.p2p.cs.ashulakov.assignment3;

import acm.graphics.GFillable;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Builds a wall of bricks, where in each subsequent row there is 1 brick less
 */
public class Assignment3Part4 extends WindowProgram {
    // Brick height, width and base size in accordance
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_WIDTH = 40;
    private static final int BRICKS_IN_BASE = 10;

    // Padding size from window edges
    private static final int PADDING = 3;

    // start the construction
    @Override
    public void run() {
        // set window size
        setSize(
                Math.max(getWidth(), BRICK_WIDTH * BRICKS_IN_BASE + PADDING),
                Math.max(getHeight(), BRICK_HEIGHT * BRICKS_IN_BASE + PADDING)
        );

        // calculate start point coordinates
        double startX = (getWidth() - BRICK_WIDTH * BRICKS_IN_BASE) / 2.0;
        double startY = getHeight() - BRICK_HEIGHT;

        //
        constructRowByRow(startX, startY, BRICKS_IN_BASE);
    }

    /**
     * Constructed rows recursively
     *
     * @param x        - x-coordinate of row start point
     * @param y        - y-coordinate of row start point
     * @param rowIndex - index of row from bottom
     * @return - true if rows are constructed
     */
    private boolean constructRowByRow(double x, double y, int rowIndex) {
        // Rows constructed, exit from recursion
        if (rowIndex == 0)
            return true;
        // Rows not constructed yet, put all bricks in current row
        putCurrentRowBricks(x, y, rowIndex);

        // construct next one row
        return constructRowByRow(x + BRICK_WIDTH / 2.0, y - BRICK_HEIGHT, rowIndex - 1);
    }

    /**
     * Put bricks recursively
     *
     * @param x          - x-coordinate of brick start point
     * @param y          - y-coordinate of brick start point
     * @param brickIndex - index of brick in row
     * @return - true if bricks are constructed
     */
    private boolean putCurrentRowBricks(double x, double y, int brickIndex) {
        // put bricks until the row isn't constructed
        if (brickIndex == 0)
            return true;

        // add brick in current row
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        colorfullObject(Color.BLACK, Color.ORANGE, brick);
        add(brick);

        // put next one brick
        return putCurrentRowBricks(x + BRICK_WIDTH, y, brickIndex - 1);
    }

    /**
     * Fill the object with color
     *
     * @param edgeColor - color of object edge
     * @param fillColor - color of fill
     * @param object    - object to be painted
     */
    private void colorfullObject(Color edgeColor, Color fillColor, GFillable object) {
        object.setFillColor(edgeColor);
        object.setFilled(true);
        object.setFillColor(fillColor);
    }
}