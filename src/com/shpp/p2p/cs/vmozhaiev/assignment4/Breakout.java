/**
 * File: Breakout.java
 * ---------------------------
 * This program creates a Breakout game
 * Task description in Assignment4 Breakout game
 */

package com.shpp.p2p.cs.vmozhaiev.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;


public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Number of bricks in the game
     */
    private static final int NBRICK_IN_GAME = NBRICKS_PER_ROW * NBRICK_ROWS;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 2;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;
    /**
     * horizontal velocity of the ball
     */
    private static final double X_VELOCITY = 3.0;
    /**
     * Vertical velocity of the ball
     */
    private static final double Y_VELOCITY = 3.0;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;
    /**
     * Offset of the label from the top
     */
    private static final int LABEL_Y_OFFSET = 50;
    /**
     * Number of turns
     */
    private static final int NTURNS = 3;
    /**
     * Constant set the amount of time to pause between frame(100 fps)
     */
    public static final double PAUSE_TIME = 1000.0 / 100;
    /**
     * Constant set the pause between action in millisecond
     */
    public static final double PAUSE_TIME_BETWEEN_ACTIONS = 300.0;
    /**
     * Constant set the amount of time to pause between frame(100 fps)
     */

    /**
     * Declaring global variables in java: GRect paddle and GOval ball
     */
    public static GRect paddle = null;
    public static GOval ball = null;

    /**
     * This method creates a Breakout game
     */
    public void run() {
        getMenuBar().setVisible(false);
        createGameBreakout();
    }

    /**
     * This method creates a components of Breakout game
     */
    private void createGameBreakout() {
        addMouseListeners();
        createPaddle();
        createLabel("Press LMB to start the game");
        createColorfulWallBricks();
        createBall();
        pause(PAUSE_TIME_BETWEEN_ACTIONS);
        createLabel("Ready!!! Go!");
        moveBall();
    }

    /**
     * This method allows to move the paddle with the mouse
     */
    public void mouseMoved(MouseEvent me) {
        //Sets the binding of the mouse cursor to the coordinates of the object
        double newX = me.getX() - paddle.getWidth() / 2.0;
        //Sets the condition for paddle movement
        if (newX >= 0 && newX < WIDTH - PADDLE_WIDTH) {
            //Sets new position of the paddle on the canvas
            paddle.setLocation(newX, getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /**
     * This method creates the paddle
     */
    private void createPaddle() {
        //Sets the coordinates of the paddle
        double x = (WIDTH - PADDLE_WIDTH) / 2.0;
        double y = (HEIGHT - PADDLE_Y_OFFSET);
        //Assign new GRect to paddle
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        //Sets the color of the paddle
        paddle.setColor(Color.BLACK);
        //Sets the filling of the object
        paddle.setFilled(true);
        //Adds paddle to canvas
        add(paddle);
    }

    /**
     * This method creates the ball
     */
    private void createBall() {
        //Sets the coordinates of the ball
        double x = (WIDTH - BALL_RADIUS * 2) / 2.0;
        double y = (HEIGHT - BALL_RADIUS * 2) / 2.0;
        //Assign new GOval to ball
        ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
        //Sets the color of the ball
        ball.setColor(Color.BLACK);
        //Sets the filling of the object
        ball.setFilled(true);
        pause(PAUSE_TIME_BETWEEN_ACTIONS);
        //Adds ball to canvas
        add(ball);
    }

    /**
     * This method creates the ball
     *
     * @param string The text of the label
     */
    private void createLabel(String string) {
        //Creates the new GLabel
        GLabel label = new GLabel(string);
        //Sets the font of the label
        label.setFont("Unispace-Bold-20");
        //Sets the color of the label
        label.setColor(Color.RED);
        //Sets the coordinates of the label
        double x = (WIDTH - label.getWidth()) / 2.0;
        double y = (HEIGHT - LABEL_Y_OFFSET - label.getDescent()) / 2.0;
        //Adds label to canvas
        add(label, x, y);
        //Wait for user click
        waitForClick();
        //Removes label from canvas
        remove(label);
    }

    /**
     * This method creates the colorful wall from bricks
     */
    private void createColorfulWallBricks() {
        //Creates colorful wall bricks using nested loop
        for (int i = 0; i < NBRICK_ROWS; i++) {
            pause(PAUSE_TIME_BETWEEN_ACTIONS / 2.0);
            for (int j = 0; j < NBRICKS_PER_ROW; j++)
                //Adds brick to canvas
                add(createBrick(i, j));
        }
    }

    /**
     * This method creates the ball colorful wall from bricks
     *
     * @param nBrickRows    Number of rows of bricks
     * @param nBricksPerRow Number of bricks per row
     * @return GRect brick
     */
    private GRect createBrick(int nBrickRows, int nBricksPerRow) {
        //Offset of the top brick row from the left side
        double x = 0;
        //Offset of the top brick row from the top
        double y = BRICK_Y_OFFSET;
        GRect brick = new GRect(x + nBricksPerRow * (BRICK_WIDTH + BRICK_SEP),
                y + nBrickRows * (BRICK_HEIGHT + BRICK_SEP),
                BRICK_WIDTH, BRICK_HEIGHT);
        //Sets the color for rows from bricks
        brick.setColor(setColorBrickRows(nBrickRows));
        brick.setFilled(true);
        return brick;
    }

    /**
     * This method sets the color of the rows of bricks
     *
     * @param nBrickRows Number of rows of bricks
     * @return Color.color
     */
    private Color setColorBrickRows(int nBrickRows) {
        //Condition for red color
        if (nBrickRows < 2) {
            return Color.RED;
            //Condition for orange color
        } else if (nBrickRows < 4) {
            return Color.ORANGE;
        } else if (nBrickRows < 6) {
            //Condition for yellow color
            return Color.YELLOW;
        } else if (nBrickRows < 8) {
            //Condition for green color
            return Color.GREEN;
        }
        return Color.CYAN;
    }

    /**
     * This method set the horizontal velocity of the ball
     */
    private double setVelocityX() {
        //Creates a new random generator rGen
        RandomGenerator rGen = RandomGenerator.getInstance();
        //Sets the horizontal velocity of the ball from 1 to 5
        double vx = rGen.nextDouble(1.0, X_VELOCITY);
        //Returns a random boolean value with the specified probability
        if (rGen.nextBoolean(0.5)) {
            vx = -vx;
        }
        return vx;
    }

    /**
     * This method moves the ball
     */
    public void moveBall() {
        //Numbers of attempts
        int nTurns = NTURNS;
        //Numbers of bricks in game
        int brickCounter = NBRICK_IN_GAME;
        //Set the horizontal velocity of the ball
        double vx = setVelocityX();
        //Set the vertical velocity of the ball
        double vy = Y_VELOCITY;
        //Conditions for continuing the game
        while (nTurns > 0 && brickCounter > 0) {
            //Assign the value of vx, vy and brickCounter
            double[] changedData = wallAndObjectBouncing(vx, vy, brickCounter);
            vx = changedData[0];
            vy = changedData[1];
            brickCounter = (int) changedData[2];
            //Moves ball
            ball.move(vx, vy);
            pause(PAUSE_TIME);
            //Conditions for decrementing nTurns and starting a new attempt
            if (ball.getY() + 2 * BALL_RADIUS > HEIGHT) {
                nTurns--;
                //Removes ball from the canvas
                remove(ball);
                //Sets the pause between action
                pause(PAUSE_TIME_BETWEEN_ACTIONS);
                if (nTurns > 0) {
                    //Creates ball to the canvas
                    createBall();
                    //Creates label to the canvas
                    createLabel(nTurns + " attempts left");
                    //Set the new value of the horizontal velocity
                    vx = setVelocityX();
                }
            }
        }
        //Removes all objects from the canvas
        removeAll();
        pause(PAUSE_TIME_BETWEEN_ACTIONS);
        //Conditions for winning the game
        if (nTurns > 0) {
            createLabel("You won the game!!!");
            //Condition for losing game
        } else {
            createLabel("Game over");
        }
    }

    /**
     * This method determines the objects that the ball collides with
     *
     * @return GObject that the ball collides with to the method moveBall()
     */
    private GObject getCollidingObject() {

        //Coordinates upper left point
        double xUpperLeft = ball.getX();
        double yUpperLeft = ball.getY();
        //Coordinates lower left point
        double xLowerLeft = ball.getX();
        double yLowerLeft = ball.getY() + 2 * BALL_RADIUS;
        //Coordinates upper right point
        double xUpperRight = ball.getX() + 2 * BALL_RADIUS;
        double yUpperRight = ball.getY();
        //Coordinates lower right point
        double xLowerRight = ball.getX() + 2 * BALL_RADIUS;
        double yLowerRight = ball.getY() + 2 * BALL_RADIUS;

        //Coordinates upper centre point of the ball
        double xUpperCenterOfCircle = ball.getX() + BALL_RADIUS;
        double yUpperCenterOfCircle = ball.getY();
        //Coordinates lower centre point of the ball
        double xLowerCenterOfCircle = ball.getX() + BALL_RADIUS;
        double yLowerCenterOfCircle = ball.getY() + 2 * BALL_RADIUS;
        //Coordinates left centre point of the ball
        double xLeftCenterOfCircle = ball.getX();
        double yLeftCenterOfCircle = ball.getY() + BALL_RADIUS;
        //Coordinates right centre point of the ball
        double xRightCenterOfCircle = ball.getX() + 2 * BALL_RADIUS;
        double yRightCenterOfCircle = ball.getY() + BALL_RADIUS;

        //Coordinates upper left point of the ball
        double xUpperLeftOfCircle = ball.getX() + BALL_RADIUS * (1 - 1 / Math.sqrt(2));
        double yUpperLeftOfCircle = ball.getY() + BALL_RADIUS * (1 - 1 / Math.sqrt(2));
        //Coordinates lower left point of the ball
        double xLowerLeftOfCircle = ball.getX() + BALL_RADIUS * (1 - 1 / Math.sqrt(2));
        double yLowerLeftOfCircle = ball.getY() + BALL_RADIUS * (1 + 1 / Math.sqrt(2));
        //Coordinates upper right point of the ball
        double xUpperRightOfCircle = ball.getX() + BALL_RADIUS * (1 + 1 / Math.sqrt(2));
        double yUpperRightOfCircle = ball.getY() + BALL_RADIUS * (1 - 1 / Math.sqrt(2));
        //Coordinates lower right point of the ball
        double xLowerRightOfCircle = ball.getX() + BALL_RADIUS * (1 + 1 / Math.sqrt(2));
        double yLowerRightOfCircle = ball.getY() + BALL_RADIUS * (1 + 1 / Math.sqrt(2));

        //Create the array from found object using method getElementAt
        GObject[] findingObject = {
                getElementAt(xUpperLeft, yUpperLeft),
                getElementAt(xLowerLeft, yLowerLeft),
                getElementAt(xUpperRight, yUpperRight),
                getElementAt(xLowerRight, yLowerRight),
                getElementAt(xUpperCenterOfCircle, yUpperCenterOfCircle),
                getElementAt(xLowerCenterOfCircle, yLowerCenterOfCircle),
                getElementAt(xLeftCenterOfCircle, yLeftCenterOfCircle),
                getElementAt(xRightCenterOfCircle, yRightCenterOfCircle),
                getElementAt(xUpperLeftOfCircle, yUpperLeftOfCircle),
                getElementAt(xLowerLeftOfCircle, yLowerLeftOfCircle),
                getElementAt(xUpperRightOfCircle, yUpperRightOfCircle),
                getElementAt(xLowerRightOfCircle, yLowerRightOfCircle)
        };

        //Iterates over the values of an array
        for (GObject gObject : findingObject) {
            if (gObject != null && gObject != ball) {
                return gObject;
            }
        }
        return null;
    }

    /**
     * This method determines the bounds that the ball collides with.
     * Also changes the direction of the ball
     *
     * @param vx Horizontal velocity of the ball
     * @param vy Vertical velocity of the ball
     * @return Changed horizontal/vertical velocity of the ball and brick counter
     * to the method moveBall()
     */
    private double[] wallAndObjectBouncing(double vx, double vy, int brickCounter) {
        //Assign the received object to GObject collider
        GObject collider = getCollidingObject();
        //Rebound condition from vertical walls
        if (ball.getX() < 0 || ball.getX() > WIDTH - BALL_RADIUS * 2) {
            vx = -vx;
        }
        //Rebound condition from top
        if (ball.getY() < 0) {
            vy = -vy;
        }
        //Rebound condition the left corner of the paddle
        if (vx > 0 && ball.getX() + BALL_RADIUS < paddle.getX() && ball.getX() + 2 * BALL_RADIUS > paddle.getX()
                && ball.getY() + BALL_RADIUS < paddle.getY() && ball.getY() + 2 * BALL_RADIUS > paddle.getY()) {
            vx = -vx;
        }

        //Rebound condition the right corner of the paddle
        if (vx < 0 && ball.getX() < paddle.getX() + PADDLE_WIDTH
                && ball.getX() + BALL_RADIUS > paddle.getX() + PADDLE_WIDTH
                && ball.getY() + BALL_RADIUS < paddle.getY() && ball.getY() + 2 * BALL_RADIUS > paddle.getY()) {
            vx = -vx;
        }

        //Rebound condition from paddle
        if (collider == paddle && ball.getY() + 2 * BALL_RADIUS > paddle.getY()) {
            vy = -vy;
            //Conditions for decrementing brick counter
        } else if (collider != null) {
            //Change vertical direction
            vy = -vy;
            //Removes object from the canvas
            remove(collider);
            //Decrements brick counter
            brickCounter--;
        }
        // Returns changed horizontal/vertical velocity of the ball and brick counter
        return new double[]{vx, vy, brickCounter};
    }
}