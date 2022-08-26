package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO: Draw 4 circle and square
 */
import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;
    public static final int r = 50;// radius
    double xCenter = getWidth() / 2.0;
    double yCenter = getHeight() / 2.0;

    public void run() {

        int d = r * 2; // diament

        double x = xCenter - r;
        double y = yCenter - r;
//        GOval oval = new GOval(0,0,50,50);
//        add(oval);


        add(BuildOval(0, 0, d));                                   // left upper corner
        add(BuildOval(APPLICATION_WIDTH - d, 0, d));               // left lower corner
        add(BuildOval(0, getHeight() - d, d));                     // right upper corner
        add(BuildOval(APPLICATION_WIDTH - d, getHeight() - d, d)); // right lower corner

        add(BuildRect(r,r,getWidth()-d,getHeight()-d));
    }

    private GObject BuildRect(int x1, int y1, double x2, double y2) {
        GRect  rect = new GRect(x1, y1, x2, y2);
        rect.setFillColor(Color.WHITE);
        rect.setFilled(true);
        rect.setColor(Color.WHITE);
        return rect;
    }

    private GObject BuildOval(double x, double y, double d) {

        GOval circle = new GOval(x, y, d, d);
        circle.setFillColor(Color.BLACK);
        circle.setFilled(true);
        return circle;
    }

}
