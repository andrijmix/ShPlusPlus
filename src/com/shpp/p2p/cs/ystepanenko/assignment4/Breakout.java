package com.shpp.p2p.cs.ystepanenko.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

//примагнічення ракетки
//спочатку рухається не за рандомом
//не виводить к-сть помилок
//при деякосу куті прис
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
     * PADDLE_COLOR - set PADDLE color
     */
    final static Color PADDLE_COLOR = new Color(0, 0, 0);  //black

    /**
     * BALL_COLOR - set BALL color
     */
    final static Color BALL_COLOR = new Color(0, 0, 0);  //black

    /**
     * BRICK_COLORS - array of colors of bricks
     */
    final static Color[] BRICK_COLORS = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

    /**
     * constants to control the random number generator for ball
     */
    final static double START_VALUE_TO_RAND_GENERATOR = 1.0;
    final static double FINISH_VALUE_TO_RAND_GENERATOR = 3.0;

    /**
     * Ball speed. the lower the value, the more difficult
     * 5 - hard
     * 10 - middle
     * 15 - easy
     */
    final static int TIME_DELAY = 10;

    /**
     * variables for changing the position of the ball on the screen
     */
    private double vx, vy = 3;

    /**
     * Main paddle variable
     */
    private GRect paddle;

    /**
     * Main ball variable
     */
    private GOval ball;

    public void run() {
        /** countBricks - the number of bricks that must be destroyed to win */
        int countBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
        addMouseListeners();
        paddle = createPaddle(PADDLE_COLOR);
        drawWallBricks();
        ball = createBall(BALL_RADIUS, BALL_COLOR);
        startGame(countBricks);
    }

    /**
     * this method adds and displays wall Bricks
     */
    private void drawWallBricks() {
        /** startX, startY - start point to draw bricks*/
        double startX, startY;
        Color breakColor;
        startX = (getWidth() - BRICK_WIDTH * NBRICKS_PER_ROW - BRICK_SEP * (NBRICKS_PER_ROW - 1)) / 2.0;
        startY = BRICK_Y_OFFSET;
        //draw rows
        for (int i = 0; i < Breakout.NBRICK_ROWS; i++) {
            //draw colums
            for (int j = 0; j < Breakout.NBRICKS_PER_ROW; j++) {
                breakColor = getBreakColor(i);
                drawBrick(startX, startY, breakColor);
                startX += Breakout.BRICK_WIDTH + Breakout.BRICK_SEP;
            }
            //reset the x-coordinate when moving to a new row
            startX = (getWidth() - Breakout.BRICK_WIDTH * Breakout.NBRICKS_PER_ROW -
                    Breakout.BRICK_SEP * (Breakout.NBRICK_ROWS - 1)) / 2.0;
            startY += Breakout.BRICK_HEIGHT + Breakout.BRICK_SEP;
        }
    }

    /**
     * description: depending on the counter - return the color of the brick.
     * We take the conditions from the initial technical task
     *
     * @param i - accept counter parameter
     * @return - color of bricks
     */
    private Color getBreakColor(int i) {
        return switch (i) {
            case 0, 1 -> BRICK_COLORS[0];
            case 2, 3 -> BRICK_COLORS[1];
            case 4, 5 -> BRICK_COLORS[2];
            case 6, 7 -> BRICK_COLORS[3];
            case 8, 9 -> BRICK_COLORS[4];
            default -> null;
        };
    }

    /**
     * starting condition: it is necessary to set all constants at the beginning of the program
     *
     * @param minRandomValue - minimum value parameter for random number generator
     * @param maxRandomValue - maximum value parameter for random number generator
     *                       result: Calculated offset of ball
     */
    private double getBallRandomOffset(double minRandomValue, double maxRandomValue, boolean changeDirection) {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(minRandomValue, maxRandomValue);
        if (changeDirection && rgen.nextBoolean(0.5))
            vx = -vx;
        return vx;
    }

    /**
     * this method describes the process of the whole game
     */
    private void startGame(int countBricks) {
        int gameCount = Breakout.NTURNS;

        waitForClick();

        while (gameCount > 0) {
            // bottom of field check
            if (lostBall(ball)) {
                gameCount--;
                if (gameCount > 0) {
                    double startX = WIDTH / 2.0;
                    double startY = getHeight() / 2.0;
                    ball.setLocation(startX, startY);
                    waitForClick();
                }
            }

            countBricks = moveProcessed(ball, countBricks);
            pause(TIME_DELAY);

            // WIN condition
            if (countBricks == 0) {
                finishGame("Congratulations! You won!", Color.GREEN);
                return;
            }

            //loss condition
            if (gameCount == 0) {
                finishGame("GAME OVER(((", Color.RED);
                return;
            }
        }
    }

    /**
     * notes - check whether we lost the ball or not
     *
     * @return - boolean whether the ball has reached the bottom of the window or not
     */
    private boolean lostBall(GOval ball) {
        boolean lost;

        if (ball.getY() + BALL_RADIUS * 2 + vy > getHeight()) {
            lost = true;
        } else {
            lost = false;
        }
        return lost;
    }

    /**
     * in this method, the control of the ball is processed. not to go beyond the screen
     */
    public void checkMoveBall() {
        if (ball.getX() <= 0 || ball.getX() + BALL_RADIUS * 2 >= APPLICATION_WIDTH) {
            vx = -vx;
        }
        if (ball.getY() <= 0) {
            vy = -vy;
        }
        ball.setLocation(ball.getX() + vx, ball.getY() + vy);
    }

    /**
     * in this method, the movement of the ball is processed
     *
     * @param ball        - ball object
     * @param countBricks - number of unbroken bricks
     */
    private int moveProcessed(GOval ball, int countBricks) {
        //paddle
        if (getCollidingObject() == paddle || getCollidingObject() == ball) {
            if (paddle.getY() - ball.getY() <= BALL_RADIUS * 2 - vy) {
                vx = -vx;
                vy = -vy;
            } else {
                vy = -vy;
            }

            getBallRandomOffset(START_VALUE_TO_RAND_GENERATOR, FINISH_VALUE_TO_RAND_GENERATOR, true);
            checkMoveBall();
        } else if ((ball.getY() + BALL_RADIUS >= paddle.getY()) && ball.getX() >= paddle.getX()
                && ball.getX() <= paddle.getX() + PADDLE_WIDTH) {
            vy = -vy;
        }
        //Bricks
        else if (getCollidingObject() != paddle && getCollidingObject() != null) {
            remove(getCollidingObject());
            countBricks--;
            vy = -vy;
            checkMoveBall();
        } else {
            checkMoveBall();
        }
        return countBricks;
    }

    /**
     * @returns the object that the ball collided with.
     */
    private GObject getCollidingObject() {
        GObject objColliding;

        // ball coordinates
        GPoint westTop = new GPoint((ball.getX()), (ball.getY()));
        GPoint westBottom = new GPoint((ball.getX()), (ball.getY() + BALL_RADIUS * 2));
        GPoint eastTop = new GPoint((ball.getX() + BALL_RADIUS * 2), (ball.getY()));
        GPoint eastBottom = new GPoint((ball.getX() + BALL_RADIUS * 2), ball.getY() + BALL_RADIUS * 2);

        // if there is a collision - get the object and return it
        if (getElementAt(westTop) != null) {
            objColliding = getElementAt(westTop);
        } else if (getElementAt(westBottom) != null) {
            objColliding = getElementAt(westBottom);
        } else if (getElementAt(eastTop) != null) {
            objColliding = getElementAt(eastTop);
        } else if (getElementAt(eastBottom) != null) {
            objColliding = getElementAt(eastBottom);
        } else {
            objColliding = null;
        }
        return objColliding;
    }

    /**
     * Game end method. Displays the result of the game
     *
     * @param gameMessage -  The message that the user will receive at the end of the game
     */
    private void finishGame(String gameMessage, Color color) {
        GLabel finalMessage = new GLabel(gameMessage);
        finalMessage.setFont("Comic-30");
        finalMessage.setColor(color);
        double labelStartX = (WIDTH - finalMessage.getWidth()) / 2.0;
        double labelStartY = (getHeight() / 2.0) - finalMessage.getHeight();
        add(finalMessage, labelStartX, labelStartY);
    }

    /**
     * starting condition: you need to get the following parameters:
     *
     * @param color - color Paddle
     * @return - object of type GRect (Paddle)
     */
    private GRect createPaddle(Color color) {
        double startX = (Breakout.WIDTH - Breakout.PADDLE_WIDTH) / 2;
        double startY = getHeight() - Breakout.PADDLE_Y_OFFSET - Breakout.PADDLE_HEIGHT;
        GRect paddle = new GRect(startX, startY, Breakout.PADDLE_WIDTH, Breakout.PADDLE_HEIGHT);
        paddle.setFillColor(color);
        paddle.setFilled(true);
        add(paddle);
        return paddle;
    }

    /**
     * processing of mouse movements. Racket movement.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        double newX;
        if (e.getX() + PADDLE_WIDTH < APPLICATION_WIDTH) {
            newX = e.getX() - paddle.getWidth() / 2.0;
        } else {//to stay within the screen on the right
            newX = APPLICATION_WIDTH - paddle.getWidth();
        }
        if (newX < 0) {
            newX = 0; //to stay within the screen on the left
        }
        // calculate the y-coordinate for the racket
        double newY = getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET;
        paddle.setLocation(newX, newY);
    }

    /**
     * starting condition: you need to get the following parameters:
     *
     * @param ballRadius - ball radius
     * @param ballColor  - ball color
     * @return - object of type GOval (ball)
     */
    private GOval createBall(int ballRadius, Color ballColor) {
        double startX = WIDTH / 2.0;
        double startY = getHeight() / 2.0;
        GOval ball = new GOval(startX, startY, ballRadius * 2, ballRadius * 2);
        ball.setFillColor(ballColor);
        ball.setColor(ballColor);
        ball.setFilled(true);
        System.out.println("startX = " + startX + " startY = " + startY);
        add(ball);
        return ball;
    }

    /**
     * starting condition: set the starting coordinate for drawing and color
     *
     * @param startX - starting coordinate x
     * @param startY - starting coordinate y
     * @param color  - color of Brick
     *               result: We get a drawn Brick
     */
    private GRect drawBrick(double startX, double startY, Color color) {
        GRect brick = new GRect(startX, startY, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFillColor(color);
        brick.setColor(color);
        brick.setFilled(true);
        add(brick);
        return brick;
    }

}
