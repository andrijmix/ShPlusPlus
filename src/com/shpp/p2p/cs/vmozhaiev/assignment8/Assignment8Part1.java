package com.shpp.p2p.cs.vmozhaiev.assignment8;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Assignment8Part1 extends WindowProgram {
    // Constant sets the default width and height of the window
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 800;
    // Constant sets the number of squares
    private static final int NUMBER_OF_SQUARE = 100;
    // Constant sets the size of square
    private static final int SIDE_OF_SQUARE = 10;
    // Constant set the amount of time to pause between frame(60 fps)
    public static final double PAUSE_TIME = 1000.0 / 60;
    // Creates RandomGenerator rGen
    private final RandomGenerator rGen = RandomGenerator.getInstance();
    // Creates array from GRects for magnet square
    private final ArrayList<GRect> magnetSquares = new ArrayList<>();
    // Creates array from GRects for anti-magnet square
    private final ArrayList<GRect> antiMagnetSquares = new ArrayList<>();
    // Sets the delta of x coordinate for moving object
    private int dx = 1;
    // Sets the delta for y coordinate for moving object
    private int dy = 1;
    // Variable to get the x coordinate from the mouse
    private double eGetX;
    // Variable to get the y coordinate from the mouse
    private double eGetY;
    // Boolean variable for start/stop animation
    private boolean startStopAnimation = false;

    /**
     * This method creates the GObjects of magnet/anti-magnet squares,
     * monitors mouse actions, starts|stops animation and moving graphic objects
     */
    public void run() {
        createMagnetAndAntiMagnetSquare();
        addMouseListeners();
        while(true) {
            moveAllSquares();
            pause(PAUSE_TIME);
        }
    }

    /**
     * This method moves all graphic object on the canvas
     * according to certain conditions
     */
    private void moveAllSquares() {
        // Moves anti-magnet square if mouse is moving
        moveSquares(antiMagnetSquares, dx, dy);
        // Moves magnet square if mouse is moving
        moveSquares(magnetSquares, -dx, -dy);
    }

    /**
     * This method moves the graphics objects on the canvas
     *
     * @param squares ArrayList from GRect
     * @param dx      The X-coordinate offset
     * @param dy      The Y-coordinate offset
     */
    private void moveSquares(ArrayList<GRect> squares, int dx, int dy) {
        for (GRect square : squares) {
            // Moves north-east to mouse cursor
            if (eGetX < square.getX() + SIDE_OF_SQUARE / 2.0 && eGetY < square.getY() + SIDE_OF_SQUARE / 2.0) {
                square.move(-dx, -dy);
                // Moves south-west to mouse cursor
            } else if (eGetX > square.getX() + SIDE_OF_SQUARE / 2.0 && eGetY > square.getY() + SIDE_OF_SQUARE / 2.0) {
                square.move(dx, dy);
                // Moves south-east to mouse cursor
            } else if (eGetX < square.getX() + SIDE_OF_SQUARE / 2.0 && eGetY > square.getY() + SIDE_OF_SQUARE / 2.0) {
                square.move(-dx, dy);
                // Moves north-west to mouse cursor
            } else if (eGetX > square.getX() + SIDE_OF_SQUARE / 2.0 && eGetY < square.getY() + SIDE_OF_SQUARE / 2.0) {
                square.move(dx, -dy);
            }
        }
    }

    /**
     * This method adds a magnet/anti-magnet squares to the canvas
     * and creates the ArrayLists from GRects
     */
    private void createMagnetAndAntiMagnetSquare() {
        for (int i = 0; i < NUMBER_OF_SQUARE; i++) {
            GRect square = createBlackSquare();
            double rGenX = rGen.nextDouble(getWidth() - SIDE_OF_SQUARE);
            double rGenY = rGen.nextDouble(getHeight() - SIDE_OF_SQUARE);
            square.setLocation(rGenX, rGenY);
            add(square);
            if (i % 2 == 0) {
                magnetSquares.add(square);
            } else antiMagnetSquares.add(square);
        }
    }

    /**
     * This method creates GRect square
     *
     * @return GRect
     */
    public GRect createBlackSquare() {
        GRect s = new GRect(0, 0, SIDE_OF_SQUARE, SIDE_OF_SQUARE);
        s.setFilled(true);
        return s;
    }

    /**
     * This method gets the coordinates of mouse
     * when mouse is moving
     *
     * @param e the event to be processed
     */
    public void mouseMoved(MouseEvent e) {
        eGetX = e.getX();
        eGetY = e.getY();
    }

    /**
     * This method gets the coordinates of mouse
     * when mouse is dragging
     *
     * @param e the event to be processed
     */
    public void mouseDragged(MouseEvent e) {
        eGetX = e.getX();
        eGetY = e.getY();
    }

    /**
     * This method changes the direction for moving objects
     * when mouse is pressed
     *
     * @param e the event to be processed
     */
    public void mousePressed(MouseEvent e) {
        dx = -dx;
        dy = -dy;
    }

    /**
     * This method changes the direction for moving objects
     * when mouse is released
     *
     * @param e the event to be processed
     */
    public void mouseReleased(MouseEvent e) {
        dx = -dx;
        dy = -dy;
    }
}
