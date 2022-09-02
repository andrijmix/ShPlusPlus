package com.shpp.p2p.cs.lmatata.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {

    public void run() {

        drawFlag();
        drawLabelOfFlag();

        GLabel label = new GLabel("+",getWidth()/2,getHeight()/2);
        add(label);
    }
    private void drawFlag() {
        int flag_WIDTH = (getWidth() / 5) * 3;
        int flag_HEIGHT = (getHeight() / 11) * 9;
        int x = (getWidth() - flag_WIDTH) / 2;
        int y = (getHeight() - flag_HEIGHT) / 2;
        for (int i = 0; i < 3; i++) {
            int colorLineWidth = flag_WIDTH / 3;
            int stripStepX = x + i * colorLineWidth;
            drawColorStrip(i, stripStepX, y, colorLineWidth,flag_HEIGHT);
        }
    }
    private void drawColorStrip(int i, int stripStepX, int y, int colorLineWidth,int flag_HEIGHT) {
        GRect colorStrip = new GRect(stripStepX, y, colorLineWidth, flag_HEIGHT);
        colorStrip.setFilled(true);
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
    private void drawLabelOfFlag() {
        GLabel l = new GLabel("Flag of Belgium");
        double x = getWidth() - l.getWidth() * 3;
        double y = getHeight() - l.getAscent() + l.getDescent();
        l.setFont("Verdana-30");
        l.setColor(Color.BLACK);
        add(l, x, y);
    }
}