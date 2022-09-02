package com.shpp.p2p.cs.lmatata.assignment2;


import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;
    private static final int RECTANGLE_WIDTH = 350;
    private static final int RECTANGLE_HEIGHT = 150;
    private static final int DIAMETER = 130;
    private static final int RADIUS = DIAMETER / 2;

    public void run() {
        int x = (getWidth() - RECTANGLE_WIDTH) / 2;
        int y = (getHeight() - RECTANGLE_HEIGHT) / 2;
        drawCircles(x,y);
        drawRectangle(x,y);

    }
    private void drawCircles(int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawItemCircle(x, y, j, i);

            }
        }
    }
    private void drawItemCircle(int x, int y, int j, int i) {
        GOval o = new GOval(x + j * RECTANGLE_WIDTH - RADIUS,
                y + i * RECTANGLE_HEIGHT - RADIUS, DIAMETER, DIAMETER);
        o.setColor(Color.BLACK);
        o.setFilled(true);
        o.setFillColor(Color.BLACK);
        add(o);
    }
    private void drawRectangle(int x, int y) {
        GRect r = new GRect(x, y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        r.setColor(Color.WHITE);
        r.setFilled(true);
        r.setFillColor(Color.WHITE);
        add(r);
    }

}
