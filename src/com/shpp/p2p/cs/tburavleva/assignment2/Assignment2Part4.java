package com.shpp.p2p.cs.tburavleva.assignment2;

import acm.graphics.GFillable;
import acm.graphics.GRect;

import acm.graphics.GLabel;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draw a flag that consists of three colored stripes.
 * The flag is located in the center of the window.
 * The name of the country is displayed
 * in the lower right corner
 */
public class Assignment2Part4 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 300;

    public static final int FLAG_HEIGHT = 200;
    public static final int FLAG_WIDTH = 100;

    public void run() {
        /* Calculate coordinates of color stripes */
        double x = getWidth() / 2.0 - 3 * FLAG_WIDTH / 2.0;
        double y = getHeight() / 2.0 - FLAG_HEIGHT / 2.0;

        add(createRect(Color.BLUE, x, y, FLAG_WIDTH, FLAG_HEIGHT));
        add(createRect(Color.WHITE, x + FLAG_WIDTH, y, FLAG_WIDTH, FLAG_HEIGHT));
        add(createRect(Color.RED, x + FLAG_WIDTH + FLAG_WIDTH, y, FLAG_WIDTH, FLAG_HEIGHT));

        add(createLabel(), getWidth() - createLabel().getWidth(), getHeight() - createLabel().getDescent());


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
        res.setColor(Color.BLACK);
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

    /**
     * Create country label in the lower right corner
     *
     * @return object label
     */
    private GLabel createLabel() {
        GLabel label = new GLabel("Flag of France");
        label.setFont("Helvetica-24");
        double labelW = label.getWidth();

        return label;
    }
}

