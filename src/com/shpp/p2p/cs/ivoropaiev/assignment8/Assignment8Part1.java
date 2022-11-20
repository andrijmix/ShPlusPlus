package com.shpp.p2p.cs.ivoropaiev.assignment8;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

/**
 * File: Assignment8Part1.java
 * <p>---------------------</p>
 * When it is run, this program implements the window with the black boxes placed in a randomized location. The first part of the boxes magnetizes to the mouse pointer and the other fly away from there. If you press and hold the mouse button second part of the boxes will magnetize and the first flyaway. The origin polarity will come back if you released the mouse button.
 * You can set the magnetize sensitivity by the constant variable: SENSITIVITY_RADIUS.
 */
public class Assignment8Part1 extends WindowProgram {
    /**
     * Width of the window
     */
    private static final int APPLICATION_WIDTH = 1000;
    /**
     * Height of the window
     */
    private static final int APPLICATION_HEIGHT = 700;
    /**
     * Minimum boxes number on the canvas
     */
    private static final int MIN_NUM_OF_BOXES = 40;
    /**
     * Maximum boxes number on the canvas
     */
    private static final int MAX_NUM_OF_BOXES = 50;
    /**
     * Size of the box-side
     */
    private static final int SIZE_OF_BOXES = 20;
    /**
     * The radius of the magnetise-sensitive circle
     */
    private static final int SENSITIVITY_RADIUS = 800;
    /**
     * The delay value to slow down the animation
     */
    private static final int DELAY = 0;
    /**
     * Contains presses or releases the mouse button value
     */
    boolean mousePressedFlag = false;
    /**
     * Contains enters or exits the mouse pointer to the canvas
     */
    boolean mouseEnteredFlag = false;
    /**
     * Contains x-coordinate of the mouse pointer
     */
    int xMouse;
    /**
     * Contains y-coordinate of the mouse pointer
     */
    int yMouse;
    /**
     * An array of all objects (GRect) in the class
     */
    ArrayList<GRect> arrayOfBoxes = new ArrayList<>();

    /**
     * Sets the size of the window adds mouseListener and all the squares to the canvas
     */
    @Override
    public void init() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);    // Sets the size of the window
        addMouseListeners(this);                     // Adds mouseListener
        createTheBoxes();                                       // Adds all boxes to the canvas
    }

    /**
     * If the mouse pointer placed on canvas all boxes starts the moving.
     * The first part of the boxes magnetizes to the mouse pointer and the other fly away from there.
     * If you press and hold the mouse button second part of the boxes will magnetize and the first flyaway.
     * The origin polarity will come back if you released the mouse button.
     */
    @Override
    public void run() {

        while (true) {
            if (mouseEnteredFlag) {                             // The mouse pointer is on the canvas
                moveTheBoxes();                                 // then move the boxes
            }
            pause(DELAY);                                       // Pause to slow down the animation
        }
    }

    /**
     * Implements box movement where even boxes are attracted and odd boxes are repelled.
     * Changing direction while holding down the mouse button
     * Offset factor calculated based on the value of the distance of the box from the mouse pointer and the size of the window
     */
    private void moveTheBoxes() {
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        double dX;                                              // Object offset step along the X axis
        double dY;                                              // Object offset step along the Y axis
        double boxX;                                            // X-coordinate of the box
        double boxY;                                            // Y-coordinate of the box

        for (int i = 0; i < arrayOfBoxes.size(); i++) {
            boxX = arrayOfBoxes.get(i).getX() + SIZE_OF_BOXES / 2.0;
            boxY = arrayOfBoxes.get(i).getY() + SIZE_OF_BOXES / 2.0;
            dX = ((xMouse - boxX) / (canvasWidth));    //Offset factor along the X axis
            dY = ((yMouse - boxY) / (canvasHeight));    //Offset factor along the Y axis

            if (mousePressedFlag || isBorderCheck(boxX, boxY)) {    // Is mouse pressed
                dX = -dX;                                           // or border
                dY = -dY;                                           // Change the direction
            }
            if (i % 2 == 0 || isBorderCheck(boxX, boxY)) {          // Is it an even or odd box
                dX = -dX;                                           // or border
                dY = -dY;                                           // Change the direction
            }
            if (isTheSensitivityArea(boxX, boxY)) {                 // Is the magnetise-sensitive area
                arrayOfBoxes.get(i).move(dX, dY);                   // Then move the box
            }
        }
    }

    /**
     * Checks that the obj in the magnetise-sensitive area
     *
     * @param boxX X-coordinate of the box upper-left corner
     * @param boxY Y-coordinate of the box upper-left corner
     * @return True if the obj in the magnetise-sensitive area
     */
    private boolean isTheSensitivityArea(double boxX, double boxY) {
        return Math.sqrt(Math.pow(Math.abs(xMouse - boxX), 2) + Math.pow(Math.abs(yMouse - boxY), 2))
                < SENSITIVITY_RADIUS;
    }

    /**
     * Checks that the box touch the edge of the canvas
     *
     * @param boxX X-coordinate of the box center
     * @param boxY Y-coordinate of the box center
     * @return True if the box touch the edge of the canvas
     */
    private boolean isBorderCheck(double boxX, double boxY) {
        boxX -= (SIZE_OF_BOXES / 2.0);
        boxY -= (SIZE_OF_BOXES / 2.0);
        return boxX < 0 || boxX > getWidth() - SIZE_OF_BOXES || boxY < 0 || boxY > getHeight() - SIZE_OF_BOXES;
    }

    /**
     * Creates the black boxes in a random position on the canvas.
     * Number of the boxes is the random value in the interval from
     * MIN_NUM_OF_BOXES till MAX_NUM_OF_BOXES
     */
    private void createTheBoxes() {
        RandomGenerator rg = new acm.util.RandomGenerator();
        int numOfBoxes = rg.nextInt(MIN_NUM_OF_BOXES, MAX_NUM_OF_BOXES);  // Random num of the boxes
        double x;
        double y;
        for (int i = 0; i < numOfBoxes; i++) {
            x = rg.nextDouble(getWidth());                                // Random box`s X-coordinate
            y = rg.nextDouble(getHeight());                               // Random box`s Y-coordinate

            arrayOfBoxes.add(getFilledRect(x, y));                        // Add box to the arrayList
            add(arrayOfBoxes.get(i));                                     // Ad box on the canvas
        }
    }

    /**
     * Makes filled GRect instance in needed position
     *
     * @param x X-coordinate of the box (upper-left corner)
     * @param y Y-coordinate of the box (upper-left corner)
     * @return finished GRect obj with needed color and position
     */
    private GRect getFilledRect(double x, double y) {
        GRect rect = new GRect(x, y, SIZE_OF_BOXES, SIZE_OF_BOXES);
        rect.setFilled(true);
        return rect;
    }

    /**
     * Changes the polarity of the boxes when the mouse button is pressed
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedFlag = true;
    }

    /**
     * Changes the polarity of the boxes when the mouse button is released
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressedFlag = false;
    }

    /**
     * Tracks the position of the mouse pointer and writes the coordinates to the appropriate variables
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }

    /**
     * Keeps track of whether the mouse pointer is on the canvas
     * writes true value to the appropriate variables if it is entered
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        mouseEnteredFlag = true;
    }

    /**
     * Keeps track of whether the mouse pointer is on the canvas
     * writes false value to the appropriate variables if it is exited
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        mouseEnteredFlag = false;
    }

    /**
     * Tracks the position of the mouse pointer and writes the coordinates to the appropriate variables
     * when mouse button is pressed
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }
}
