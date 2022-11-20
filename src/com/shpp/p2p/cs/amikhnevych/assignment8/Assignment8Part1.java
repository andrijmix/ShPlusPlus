package com.shpp.p2p.cs.amikhnevych.assignment8;


import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created the random boxes and fanny moved its
 */
public class Assignment8Part1 extends WindowProgram {
    /* The width and height of each box. */
    private static final double BOX_SIZE = 15;

    /* The number of total boxes. */
    private static final int BOX_COUNT = 50;
    /* The speed boxes. */
    private static final int SPEED_BOX = 2;
    /* Frame per second */
    private static final int FPS = 150;

    /* The two array for separate box. */

    /* This flag check mouse button. */
    private static boolean mousePress = false;

    /* Coordinate x of mouse */
    private static int mouseX;
    /* Coordinate y of mouse */
    private static int mouseY;
    /* Flag for the presence of a mouse within the window */
    private static boolean mouseInWindow = false;

    /**
     * The main method
     */
    public void run() {
        GObject[] boxes = new GObject[BOX_COUNT];
        addMouseListeners();
        // create the random boxes
        createBoxes(boxes);
//loop working since program is running
        while (true) {
            if (mouseInWindow)
                moveBox(boxes);
            pause(1000.0 / FPS);
        }
    }


    /**
     * This method create the random boxes and add it is lists
     */
    private void createBoxes(GObject[] boxes) {
        for (int i = 0; i < BOX_COUNT; i++) {
            //build random box
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            GObject box = BuildSquare(x, y);
            boxes[i] = box;
            add(boxes[i]);
        }
    }


    /**
     * This method is moved boxes
     */
    private void moveBox(GObject[] boxes) {

        for (int i = 0; i < boxes.length; i++) {

            // get coordinate box
            double deltaX = (int) (boxes[i].getX());
            double deltaY = (int) (boxes[i].getY());
            // find delta
            double dX = (SPEED_BOX * ((mouseX - deltaX) / getWidth()));
            double dY = (SPEED_BOX * ((mouseY - deltaY) / getHeight()));
            //if mouse press do reverse
            if (mousePress) {
                dX = -dX;
                dY = -dY;
            }
            //divide box into two parts
            if (i % 2 == 0) {
                dX = -dX;
                dY = -dY;
            }
            boxes[i].move(dX, dY);
        }
    }

    /**
     * Create a square
     *
     * @param x The x coordinate of the upper-left corner
     * @param y The y coordinate of the upper-left corner
     * @return Object representing the square
     */
    private GObject BuildSquare(int x, int y) {
        GRect square = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        square.setFilled(true);
        square.setFillColor(Color.black);
        return square;
    }


    /**
     * This method fill global variables of mouse position
     *
     * @param event the event to be processed
     */
    public void mouseMoved(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();

    }

    /**
     * This method activate flag if mouse button is pressed
     *
     * @param e the event to be processed
     */
    public void mousePressed(MouseEvent e) {
        mousePress = true;
    }

    /**
     * This method deactivate flag if mouse button is repressed
     *
     * @param e the event to be processed
     */
    public void mouseReleased(MouseEvent e) {
        mousePress = false;
    }

    /**
     * This method fill global variables of mouse position if button is pressed
     *
     * @param e the event to be processed
     */
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }

    /**
     * This method activate flag if mouse in window
     *
     * @param e the event to be processed
     */
    public void mouseEntered(MouseEvent e) {
        mouseInWindow = true;
    }

    /**
     * This method deactivate flag if mouse out window
     *
     * @param e the event to be processed
     */
    public void mouseExited(MouseEvent e) {
        mouseInWindow = false;
    }
}
