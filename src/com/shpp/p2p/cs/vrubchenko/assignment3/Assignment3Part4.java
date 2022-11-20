package com.shpp.p2p.cs.vrubchenko.assignment3;
//TODO

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment3Part4 extends WindowProgram { //4
    //The constant explains how many bricks are in the base
    public static final int BRICKS_IN_BASE = 10;
    //Brick width
    public static final int BRICK_WIDTH = 70;
    //Retreat from the wall
    public static final int RETREAT_FROM_THE_WALL = 20;
    //Brick width
    public static final int BRICK_HEIGHT = 35;
    //Width and height of application
    public static final int APPLICATION_WIDTH = BRICK_WIDTH * BRICKS_IN_BASE + RETREAT_FROM_THE_WALL;
    public static final int APPLICATION_HEIGHT = (BRICK_HEIGHT * BRICKS_IN_BASE) + BRICK_HEIGHT;

    /**
     * In this program we make a pyramid and center it
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void run() {
        int cols = 0;
        int brickInBase = BRICKS_IN_BASE; //Add variable that decides how many bricks to put in a row
        //Create loop in the loop
        while (cols < BRICKS_IN_BASE) {
            int indentFromTheWall = BRICKS_IN_BASE - brickInBase;//Add variable to center pyramid
            int rows = 0;
            while (rows < brickInBase) {
                //Create pyramid set color, coordinates and size of pyramid
                GRect pyramid = new GRect(rows * BRICK_WIDTH + RETREAT_FROM_THE_WALL / 2 + indentFromTheWall * BRICK_WIDTH / 2,
                        getHeight() - BRICK_HEIGHT - cols * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
                pyramid.setColor(Color.BLACK);
                pyramid.setFilled(true);
                pyramid.setFillColor(Color.GRAY);
                add(pyramid);//add pyramid
                rows++;
            }
            cols++;
            brickInBase--;//Every next row -1 brick
        }

    }
}