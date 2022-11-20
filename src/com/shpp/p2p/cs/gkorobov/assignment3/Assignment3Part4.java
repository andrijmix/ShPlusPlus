package com.shpp.p2p.cs.gkorobov.assignment3;

import acm.graphics.GFillable;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * this program draw a pyramid first line contains BRICKS_IN_BASE and ech next line
 * has one less blocks
 *
 * @author Gleb Korobov
 * @version 1.0
 */
public class Assignment3Part4 extends WindowProgram {
    public static final int BRICK_HEIGHT = 50;
    public static final int BRICK_WIDTH = 100;

    public static final int BRICKS_IN_BASE = 5;

    public static final int APPLICATION_WIDTH = BRICK_WIDTH * BRICKS_IN_BASE;
    public static final int APPLICATION_HEIGHT = BRICK_HEIGHT * BRICKS_IN_BASE;

    public void run() {
        //delete menu bar
        getMenuBar().setVisible(false);
        makePyramid();
    }

    /**
     * this method make position of pyramid with all constants
     */
    private void makePyramid() {
        double y = APPLICATION_HEIGHT - BRICK_HEIGHT;
        double x;

        for (int i = 0; i < BRICKS_IN_BASE; i++) {

            x = BRICK_WIDTH * (i * 0.5);

            for (int j = i; j < BRICKS_IN_BASE; j++) {
                drawBrick(x, y);
                x += BRICK_WIDTH;
            }
            y -= BRICK_HEIGHT;
        }
    }

    /**
     * this method draw each block of pyramid
     *
     * @param x position x of pyramid block
     * @param y position y of pyramid block
     */
    private void drawBrick(double x, double y) {
        GRect Brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        fillObject(Brick);
        add(Brick);
    }

    /**
     * This method make a color of blocks
     *
     * @param obj block of pyramid
     */
    private void fillObject(GFillable obj) {
        obj.setFillColor(Color.WHITE);
        obj.setFilled(true);
    }
}
