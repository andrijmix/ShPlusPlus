package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    /**
     * The FLAG_WIDTH and FLAG_HEIGHT are constants for determinate size of flag
     * */
    public static final int FLAG_WIDTH = 450;
    public static final int FLAG_HEIGHT = 350;
    /**
     * The main method consist of two methods
     * */

    public void run() {
        drawFlag();
        drawLabelOfFlag();
    }
/**
 * The drawFlag method for determining the coordinates and drawing the flag
 * */
    private void drawFlag() {
        // start coordinate position
        int x = (getWidth() - FLAG_WIDTH) / 2;
        int y = (getHeight() - FLAG_HEIGHT) / 2;
        // determining the width of the flag strip
        int colorLineWidth = FLAG_WIDTH / 3;
        // loop to control the change of x-axis coordinates and color
        for (int i = 0; i < 3; i++) {
            int stripStepX = x + i * colorLineWidth;
            drawColorStrip(i, stripStepX, y, colorLineWidth);
        }
    }
/**
 * The drawColorStrip method for draw each strip of flag
 * */
    private void drawColorStrip(int i, int stripStepX, int y, int colorLineWidth) {
        GRect colorStrip = new GRect(stripStepX, y, colorLineWidth, FLAG_HEIGHT);
        colorStrip.setFilled(true);
        // used the switch operator to set the color of an individual strip
        switch (i) {
            case 0 -> {
                colorStrip.setColor(Color.BLACK);
                colorStrip.setFillColor(Color.BLACK);
            }
            case 1 -> {
                colorStrip.setColor(Color.YELLOW);
                colorStrip.setFillColor(Color.YELLOW);
            }
            case 2 -> {
                colorStrip.setColor(Color.RED);
                colorStrip.setFillColor(Color.RED);
            }
        }
        add(colorStrip);
    }
/**
 * The drawLabelOfFlag method for draw label in lower right corner
 * */
    private void drawLabelOfFlag() {
        GLabel l = new GLabel("Flag of Belgium");
        l.setFont("Helvetica-34");
        l.setColor(Color.BLACK);
        // determining the coordinates of the label l
        l.setLocation((getWidth() - l.getWidth()), (getHeight() - l.getDescent()));
        add(l);
    }
}