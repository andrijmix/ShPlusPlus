package com.shpp.p2p.cs.tburavleva.assignment2;

import acm.graphics.GFillable;
import acm.graphics.GOval;
import acm.graphics.GRect;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The program displays four circles.
 * They are overlaid with a white rectangle.
 * The size of the circles depends on the initial size of the window.
 * The user can change the initial size of the window.
 */
public class Assignment2Part2 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    public void run() {

        /*  Calculate the diameter of circles.*/
        double circleDiameter = (getWidth() < getHeight()) ? getWidth() / 3.0 : getHeight() / 3.0;
        double x = 0, y = 0;

        add(createCircle(Color.BLACK, x, y, circleDiameter));
        add(createCircle(Color.BLACK, getWidth() - circleDiameter, y, circleDiameter));
        add(createCircle(Color.BLACK, x, getHeight() - circleDiameter, circleDiameter));
        add(createCircle(Color.BLACK, getWidth() - circleDiameter,
                getHeight() - circleDiameter, circleDiameter));

        add(createRect(Color.GRAY, circleDiameter / 2, circleDiameter / 2,
                getWidth() - circleDiameter, getHeight() - circleDiameter));

    }

    /**
     * The method creates  object circle
     *
     * @param color    the color that the object is filled with
     * @param x        The x coordinate of the upper-left corner of the bounding circle box
     * @param y        The y coordinate of the upper-left corner of the bounding circle box
     * @param diameter diameter of the object circle
     * @return created object
     */
    private GOval createCircle(Color color, double x, double y, double diameter) {
        GOval res = new GOval(x, y, diameter, diameter);
        fillObject(res, color);

        return res;
    }

    /**
     * The method creates object rectangle
     *
     * @param color the color that the object is filled with
     * @param x     The x coordinate of the upper-left corner of the rectangle
     * @param y     The y coordinate of the upper-left corner of the rectangle
     * @param width object width
     * @param hight object hight
     * @return created object
     */
    private GRect createRect(Color color, double x, double y, double width, double hight) {
        GRect res = new GRect(x, y, width, hight);
        fillObject(res, color);
        res.setColor(Color.WHITE);

        return res;
    }

    /**
     * The method takes an object as input and fills it with color
     *
     * @param obj   input object
     * @param color fill color
     */
    private void fillObject(GFillable obj, Color color) {
        obj.setFillColor(color);
        obj.setFilled(true);
    }
}

