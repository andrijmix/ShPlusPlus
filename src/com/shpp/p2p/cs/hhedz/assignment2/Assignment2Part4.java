package com.shpp.p2p.cs.hhedz.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    //Constants for size of rectangle
    static final int rectWidth = 100, rectHeight = 200;

    //This method draws flag and label
    public void run() {
        drawFlag();
        drawLabel();
        GLabel label = new GLabel("+",getWidth()/2,getHeight()/2);
        add(label);
    }

    //This method draw tricolor flag and label
    private void drawFlag() {
        //There is x and y for flag centring
        int x = getWidth() / 2 - (rectWidth * 3) / 2;
        int y = getHeight() / 2 - (rectHeight) / 2;
        //call the method three times to draw the flag
        //in each next call, we increase x by the width of the rectangle
        //add 1 to the coordinates so that the edges do not stick together
        drawRect(x + 1, y, rectWidth, rectHeight, Color.black);
        drawRect(x + rectWidth + 1, y, rectWidth, rectHeight, Color.yellow);
        drawRect(x + rectWidth * 2 + 1, y, rectWidth, rectHeight, Color.red);
    }

    //draws label in the lower right corner
    private void drawLabel() {
        GLabel label = new GLabel("Flag of Belgium");
        label.setFont("London-36"); //set bigger font
        //set location of label in the lower right corner
        label.setLocation(getWidth() - label.getWidth(), getHeight() - label.getAscent() / 2);
        add(label);
    }

    /*
       @param x The x coordinate of the upper-left corner of the bounding box for the rectangle.
       @param y The y coordinate of the upper-left corner of the bounding box for the rectangle.
       @param width The width of rectangle
       @param height The height of rectangle
       @param the color with which the rectangle will be filled
        This method draws rectangle according to the coordinates that we transmit
        */
    private void drawRect(int x, int y, int width, int height, Color color) {
        GRect rect = new GRect(x, y, width, height);
        rect.setFillColor(color);
        rect.setColor(color);
        rect.setFilled(true);
        add(rect);
    }
}