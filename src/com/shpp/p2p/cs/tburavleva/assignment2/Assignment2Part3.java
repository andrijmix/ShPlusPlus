package com.shpp.p2p.cs.tburavleva.assignment2;

import acm.graphics.GFillable;
import acm.graphics.GOval;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The program draws two identical paw-prints
 */
public class Assignment2Part3 extends WindowProgram {

    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the paw-print.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the paw-print.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;
    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;
    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {

        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a paw-print. The parameters should specify the upper-left corner of the
     * bounding box containing that paw-print.
     *
     * @param x: The x coordinate of the upper-left corner of the bounding box
     * @param y: The y coordinate of the upper-left corner of the bounding box
     */
    private void drawPawprint(double x, double y) {

        /*  first toe */
        add(createOval(Color.BLACK, x + FIRST_TOE_OFFSET_X, y + FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT));

        /*  second toe */
        add(createOval(Color.BLACK, x + SECOND_TOE_OFFSET_X, y + SECOND_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT));

        /*  third toe */
        add(createOval(Color.BLACK, x + THIRD_TOE_OFFSET_X, y + THIRD_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT));

        /*  heel */
        add(createOval(Color.BLACK, x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT));
    }

    /**
     * The method creates  object oval
     *
     * @param color the color that the object is filled with
     * @param x     The x coordinate of the upper-left corner of the bounding oval box
     * @param y     The y coordinate of the upper-left corner of the bounding oval box
     * @param width width of the object oval
     * @param hight hight of the object oval
     * @return created object
     */
    private GOval createOval(Color color, double x, double y, double width, double hight) {
        GOval res = new GOval(x, y, width, hight);
        fillObject(res, color);

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


