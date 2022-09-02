package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    private static final int NUM_ELEM = 20;
    private static final int DIAMETER = 15;

    /**
     * Draw caterpillar
     */
    public void run() {

        drawCaterpillar(NUM_ELEM);

    }

    private void drawCaterpillar(int numElem) {
        for (int i = 0; i < numElem; i++) {
            int y = (i % 2 == 0) ? DIAMETER / 2 : 0;
            int x = i * ((DIAMETER * 3) / 5);
            drawCaterpillarItem(x, y, DIAMETER, DIAMETER);
        }
    }

    private void drawCaterpillarItem(int x, int y, int diameter, int diameter1) {
        GOval o = new GOval(x, y, DIAMETER, DIAMETER);
        o.setColor(Color.RED);
        o.setFilled(true);
        o.setFillColor(Color.GREEN);
        add(o);
    }
}
