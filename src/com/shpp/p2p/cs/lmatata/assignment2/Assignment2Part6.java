package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    // The value of the number of elements
    private static final int NUM_ELEM = 10;
    // The value of the diameter of a separate element
    private static final int DIAMETER = 80;

    /**
     * Draw caterpillar
     */
    public void run() {
        drawCaterpillar();

    }
/**
 * The drawCaterpillar method for to take initial coordinates and transfer them
 * */
    private void drawCaterpillar() {
        for (int i = 0; i < NUM_ELEM; i++) {
            // ternary operator that manipulates the "y" coordinate depending on the value of "i"
            int y = (i % 2 == 0) ? DIAMETER / 2 : 0;
            // (DIAMETER * 3) / 5 - coordinate step value "x"
            int x = i * ((DIAMETER * 3) / 5);
            drawCaterpillarItem(x, y);
        }
    }
/**
 * The drawCaterpillarItem method for drawing single item
 * of "caterpillar" with predefined coordinates in the method drawCaterpillar
 * */
    private void drawCaterpillarItem(int x, int y) {
        GOval o = new GOval(x, y, DIAMETER, DIAMETER);
        o.setColor(Color.RED);
        o.setFilled(true);
        o.setFillColor(Color.GREEN);
        add(o);
    }
}
