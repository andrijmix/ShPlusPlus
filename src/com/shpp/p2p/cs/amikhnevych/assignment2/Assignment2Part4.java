package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO: Draw 4 circle and square
 */
import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part4 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    public static final double FLAG_WIDTH = 300;
    public static final double FLAG_HEIGHT = 300;

    public static final Color LEFT_COLOR = Color.black;
    public static final Color MIDDLE_COLOR = Color.yellow;
    public static final Color RIGHT_COLOR = Color.red;
    public static final String Country = "Belguim";

    public void run() {


        double xCenter = getWidth() /2.0;
        double yCenter = getHeight() / 2.0;

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

        GLabel label = new GLabel("Flag of "+Country,(int)getWidth(),(int)getHeight());
        double length= label.getWidth();
        double height = label.getHeight();

        int indent_label = 5;
        label.setLocation(getWidth()-length- indent_label,getHeight()- indent_label);
        add(label);

//        GLabel lab = new GLabel("1",xCenter,yCenter);
//        add(lab);
    }

    private GObject BuildRect(double x, double y, Color color) {
        GRect rect = new GRect(x,y,FLAG_WIDTH/3,FLAG_HEIGHT);

        rect.setFillColor(color);
        rect.setFilled(true);
        rect.setColor(color);
        return rect;
    }
}
