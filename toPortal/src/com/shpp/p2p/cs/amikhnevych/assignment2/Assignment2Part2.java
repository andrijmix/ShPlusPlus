package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO:
Your task is to create the following:

A white rectangle overlapping four circles.

The program should be flexible and we recommend using constants to easily adjust the size of the balls.
 */

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part2 extends WindowProgram {

    public static final int R = 50;// radius of bag

    public void run() {

        int d = R * 2; // diameter of bag

        add(BuildOval(0, 0, d));                                   // left upper corner
        add(BuildOval(getWidth() - d, 0, d));                      // left lower corner
        add(BuildOval(0, getHeight() - d, d));                     // right upper corner
        add(BuildOval(getWidth() - d, getHeight() - d, d));        // right lower corner

        add(BuildRect(getWidth() - d, getHeight() - d));   // main rect
    }

    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method return  object type rect and fill it color       |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    x1 - Ox  coordinate                                                     |
     * //|    y1 - Oy  coordinate                                                     |
     * //|    x2 - length firs side                                                   |
     * //|    y2 - length second side                                                 |
     * //+----------------------------------------------------------------------------+
     */
    private GObject BuildRect(double x2, double y2) {
        GRect rect = new GRect(R, R, x2, y2);
        rect.setFillColor(Color.WHITE);
        rect.setFilled(true);
        rect.setColor(Color.WHITE);
        return rect;
    }

    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method return  object type oval and fill it color       |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    x1 - Ox  coordinate                                                     |
     * //|    y1 - Oy  coordinate                                                     |
     * //|    d -  length diameter                                                    |
     * //+----------------------------------------------------------------------------+
     */
    private GObject BuildOval(double x, double y, double d) {
        GOval circle = new GOval(x, y, d, d);
        circle.setFillColor(Color.BLACK);
        circle.setFilled(true);
        return circle;
    }

}
