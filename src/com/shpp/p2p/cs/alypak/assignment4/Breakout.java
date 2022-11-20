package com.shpp.p2p.cs.alypak.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Precondition: Need to create a game called Breakout.
 * Result: Game done.
 */
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
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;
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
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;
    /**
     * Number of turns
     */
    private static final int NTURNS = 3;
    /**
     * Colors of blocks
     */
    static final Color RED = Color.RED;
    static final Color ORANGE = Color.ORANGE;
    static final Color YELLOW = Color.YELLOW;
    static final Color GREEN = Color.GREEN;
    static final Color CYAN = Color.CYAN;
    /**
     * l - lose score
     */
    int l = 0;
    int count = 0;  // broken brick count
    int totalCount = NBRICKS_PER_ROW * NBRICK_ROWS;
    double vx, vy = 3; // starting ball velocity
    /**
     * strings for start, win or lose
     */
    private static final String START_TEXT = "Press mouse button to start";
    private static final String WIN_TEXT = "Win";
    private static final String LOSE_TEXT = "Lose";
    /**
     * ball speed
     */
    private static final double speed = 10;
    private GRect paddle; // to use paddle from anywhere

    /**
     * paint all elements in window, add mouse listeners, start game and end game configuration
     */
    public void run() {
        GOval ball = buildBall();
        buildBrick();
        GLabel text = textMB();
        addMouseListeners();
        buildPaddle();
        startGame(ball, text);
        endGame(text);
    }

    /**
     * Used to randomize starting x velocity
     *
     * @return random x velocity
     */
    private double startingVelocityX() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        return vx;
    }

    /**
     * textMB used to output message in screen
     */
    private GLabel textMB() {
        GLabel text = new GLabel(START_TEXT);
        text.setFont("Verdana-20");
        text.setLocation((getWidth() - text.getWidth()) / 2, getHeight() / 2.0 - text.getHeight());
        return text;
    }

    /**
     * endGame output final text in screen
     *
     * @param text output win or lose text in screen
     */
    private void endGame(GLabel text) {
        if (count == totalCount) {
            text.setLabel(WIN_TEXT);
            text.setLocation((getWidth() - text.getWidth()) / 2, getHeight() / 2.0 - text.getHeight());
            add(text); //b
        }
        if (l == NTURNS) {
            text.setLabel(LOSE_TEXT);
            text.setLocation((getWidth() - text.getWidth()) / 2, getHeight() / 2.0 - text.getHeight());
            add(text); //c
        }
    }

    /**
     * startGame will run when current count of games is lover than count in constant and broken brick count lover than
     * all created brick count, before start output at screen text and after mouse click remove text and start moving ball
     *
     * @param ball to use created ball
     * @param text text from constant to output it in screen
     */
    private void startGame(GOval ball, GLabel text) {
        while (l < NTURNS && count < totalCount) {
            ball = buildBall();
            add(ball);
            add(text);
            waitForClick();
            remove(text);
            reboundBall(ball, text);
        }
    }

    /**
     * this object is used to output the coordinates on the edge of ball and another object when ball hit it
     *
     * @param ball to get ball coordinates
     * @return ball coordinates or null
     */
    private GObject getCollidingObject(GOval ball) {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
        } else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
        } else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
        } else {
            return null;
        }
    }

    /**
     * buildBrick used to paint all bricks in window
     */
    private void buildBrick() {
        for (int i = 0; i < NBRICKS_PER_ROW; i++) {
            for (int j = 0; j < NBRICK_ROWS; j++) {
                GRect brick = new GRect(BRICK_SEP / 2.0 + (BRICK_WIDTH + BRICK_SEP) * i,
                        BRICK_Y_OFFSET + (BRICK_SEP + BRICK_HEIGHT) * j, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(getColor(j));
                add(brick);
            }
        }
    }

    /**
     * getColor to add colors to bricks from constants
     *
     * @param j used for brick row paint
     * @return color or null when all bricks paint done
     */
    public Color getColor(int j) {
        if (j <= 1) {
            return RED;
        }
        if (j <= 3) {
            return ORANGE;
        }
        if (j <= 5) {
            return YELLOW;
        }
        if (j <= 7) {
            return GREEN;
        }
        if (j <= 9) {
            return CYAN;
        } else return null;
    }

    /**
     * reboundBall is a body of code, here ball rebound from all objects, when it hit block - block remove, all other
     * objects just rebound ball with counter velocity, when ball falls outside the paddle game stops,
     * and you can start a new round if you had turns. This method work until you had turns and count of broken bricks
     * lower than total count of created bricks
     *
     * @param ball to use created ball
     */
    private void reboundBall(GOval ball, GLabel text) {
        vx = startingVelocityX();
        while (l < NTURNS && count < totalCount) {
            pause(speed); // ball speed
            /* Move the ball by the current velocity. */
            ball.move(vx, vy);
            BallHitScreenEdges(ball);
            BallHitObjects(ball, text);
            // stop method when ball hit last brick
            if (count == totalCount) {
                break;
            }
        }
    }

    private void BallHitObjects(GOval ball, GLabel text) {
        // ball rebound when hit paddle
        if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) == paddle && vy > 0 ||
                getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) == paddle && vy > 0) {
            vy = -vy;
        }
        // if ball hit brick, brick removed, increase count of removed bricks and ball rebound
        if (getCollidingObject(ball) != null && getCollidingObject(ball) != paddle) {
            remove(getCollidingObject(ball));
            vy = -vy;
            count = count + 1;
        }
        if (ball.getY() + ball.getHeight() >= getHeight()) {
            l++;
            remove(ball);
            startGame(ball, text);
        }
    }

    private void BallHitScreenEdges(GOval ball) {
        if (ball.getX() >= getWidth() - BALL_RADIUS * 2 && vx > 0) { // rebound right edge of window
            vx = -vx;
        }
        if (ball.getX() <= 0 && vx < 0) { // rebound left edge of window
            vx = -vx;
        }
        if (ball.getY() <= 0 && vy < 0) { // rebound top edge of window
            vy = -vy;
        }
        /* to rebound from bottom edge of window if needed

         if (ball.getY() >= getHeight() - BALL_RADIUS * 2 && vy > 0) {
         vy = -vy;
         }

         */
        // when ball hit bottom edge - lose score +1, remove ball, and going to startGame method
    }

    /**
     * buildBall used to paint a ball at middle of window
     *
     * @return ball to use in other methods
     */
    private GOval buildBall() {
        GOval ball = new GOval(getWidth() / 2.0 - BALL_RADIUS, getHeight() / 2.0 - BALL_RADIUS,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(Color.BLUE);
        return ball;
    }

    /**
     * buildPaddle used to paint a paddle
     */
    private void buildPaddle() {
        paddle = new GRect(getWidth() / 2.0 - PADDLE_WIDTH / 2.0, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT,
                PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    /**
     * mouseMoved used to move paddle, paddle move only left or right direction and can't move beyond window edges
     *
     * @param e the event to be processed
     */
    public void mouseMoved(MouseEvent e) {
        paddle.setLocation(e.getX() - PADDLE_WIDTH / 2.0, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        if (e.getX() > getWidth() - PADDLE_WIDTH / 2) {
            paddle.setLocation(getWidth() - PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
        if (e.getX() < PADDLE_WIDTH / 2) {
            paddle.setLocation(0, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
    }
}

