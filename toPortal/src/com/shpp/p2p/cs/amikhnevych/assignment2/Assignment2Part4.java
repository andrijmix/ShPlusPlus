package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO:
Your task: write a program that draws a similar flag (of a real country or a fictional one).
The size of the flag is controlled by constants, it should be centered in the middle of the window.
In the lower right corner of the window, there should also be the inscription “Flag of …”.
In the Art and Science of Java book, you should probably find hints on how to calculate the size of the text to position the GLabel correctly.
 */
import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part4 extends WindowProgram {
    public static final double FLAG_WIDTH = 300;
    public static final double FLAG_HEIGHT = 300;

    public static final Color LEFT_COLOR = Color.black;
    public static final Color MIDDLE_COLOR = Color.yellow;
    public static final Color RIGHT_COLOR = Color.red;
    public static final String COUNTRY = "Belgium";

    public void run() {

        double xCenter = getWidth() /2.0;    // find xCenter of window
        double yCenter = getHeight() / 2.0;  // find yCenter of window

        // center part flag
        double x1 = xCenter-FLAG_WIDTH/2/3;
        double y1 = yCenter-FLAG_HEIGHT/2;
        add(BuildRect(x1,y1, MIDDLE_COLOR));

        // left part flag
        double x2 = x1-FLAG_WIDTH/3;
        double y2 = yCenter-FLAG_HEIGHT/2;
        add(BuildRect(x2,y2, LEFT_COLOR));

        // right part flag
        double x3 = x1+FLAG_WIDTH/3;
        double y3 = yCenter-FLAG_HEIGHT/2;
        add(BuildRect(x3,y3, RIGHT_COLOR));


        //Create label of name country
        GLabel label = new GLabel("Flag of "+ COUNTRY);
        label.setFont("Arial-20");
        double length= label.getWidth();
        //change location of label
        label.setLocation(getWidth()-length,getHeight()- label.getDescent());
        add(label);

    }
    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method return  object type rect and fill it color       |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    x1 - Ox  coordinate                                                     |
     * //|    y1 - Oy  coordinate                                                     |
     * //|    color - color for rect                                                  |
     * //+----------------------------------------------------------------------------+
     */
    private GObject BuildRect(double x, double y, Color color) {
        GRect rect = new GRect(x,y,FLAG_WIDTH/3,FLAG_HEIGHT);

        rect.setFillColor(color);
        rect.setFilled(true);
        rect.setColor(color);
        return rect;
    }
}
