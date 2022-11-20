package com.shpp.p2p.cs.amikhnevych.assignment4;


import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

//create variable and run game
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
    private static final int NUMBER_BRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NUMBER_BRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NUMBER_BRICKS_PER_ROW - 1) * BRICK_SEP) / NUMBER_BRICKS_PER_ROW;

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
    private static final int NUMBER_TURNS = 3;

    /**
     * Start acceleration of ball
     */
    private static final int ACCELERATION = 3;
    /**
     * Pause for frame
     */
    private static final int PAUSE_FRAME = 15;

    //first start game
    public void run() {
        getMenuBar().setVisible(false); //remove the excess
        createBricks();
        addMouseListeners();
        startGame(createRacket(), createBall(), NUMBER_TURNS);
    }

    private void startGame(GRect paddle, GOval ball, int life_count) {
        RandomGenerator random_gen = RandomGenerator.getInstance();
        double vx = random_gen.nextDouble(1.0, 3.0);
        double vy = 0;
        vy += ACCELERATION;
        if (random_gen.nextBoolean(0.5))
            vx = -vx;

        createGLabel("Click for start", Color.black);
        int count_destroy_bricks = 0;//Counter of destroy bricks
        while (true) {
            movePaddle(paddle);
            ball.setLocation(ball.getX() + vx, ball.getY() + vy); //make move ball

            //listening collision
            GObject collision = getCollidingObject(ball);
            //if it is bricks
            if (collision != null)
                if (collision != paddle) {
                    remove(collision);
                    count_destroy_bricks++;
                }
            //if no have bricks
            if (count_destroy_bricks == NUMBER_BRICK_ROWS * NUMBER_BRICKS_PER_ROW) {
                gameStatus(1, life_count, paddle, ball);
                return;
            }
            //if ball on the floor
            if (ball.getY() + BALL_RADIUS > HEIGHT) {
                if (gameStatus(0, life_count, paddle, ball) == 1)
                    return;
            }
            //if ball on the top
            if (ball.getY() <= 0 || collision != null) {
                vy *= -1;
                println("vy : " + vy);
                pause(PAUSE_FRAME);
            }

            //if ball on the right side
            if (ball.getX() + BALL_RADIUS * 2 > WIDTH
                    || ball.getX() <= 0   //if ball on the left side
            ) {
                vx *= -1;
                pause(PAUSE_FRAME);
            }


            //Rebound condition the  corner of the paddle
            if (vx > 0 && ball.getX() + BALL_RADIUS < paddle.getX() && ball.getX() + 2 * BALL_RADIUS > paddle.getX()
                    && ball.getY() + BALL_RADIUS < paddle.getY() && ball.getY() + 2 * BALL_RADIUS > paddle.getY()
                    //right
                    || (vx < 0 && ball.getX() < paddle.getX() + PADDLE_WIDTH
                    && ball.getX() + BALL_RADIUS > paddle.getX() + PADDLE_WIDTH
                    && ball.getY() + BALL_RADIUS < paddle.getY() && ball.getY() + 2 * BALL_RADIUS > paddle.getY())
            ) {
                vx = -vx;
                pause(PAUSE_FRAME);
            }
            //Rebound condition the  corner of the collision
            if (collision != null)
                if (vx > 0 && ball.getX() + BALL_RADIUS < collision.getX() && ball.getX() + 2 * BALL_RADIUS > collision.getX()
                        && ball.getY() + BALL_RADIUS < collision.getY() && ball.getY() + 2 * BALL_RADIUS > collision.getY()
                        //right
                        || (vx < 0 && ball.getX() < collision.getX() + PADDLE_WIDTH
                        && ball.getX() + BALL_RADIUS > collision.getX() + PADDLE_WIDTH
                        && ball.getY() + BALL_RADIUS < collision.getY() && ball.getY() + 2 * BALL_RADIUS > collision.getY())
                ) {
                    vx = -vx;
                    pause(PAUSE_FRAME);
                }

            pause(PAUSE_FRAME);


        }
    }

    /**
     * Create Label
     *
     * @param text  - text in label
     * @param color - color label
     */
    private void createGLabel(String text, Color color) {
        GLabel game_status = new GLabel(text, (double) WIDTH / 2, (double) HEIGHT / 2);
        game_status.setLocation(0, HEIGHT - game_status.getHeight() - game_status.getDescent());
        game_status.setFont("Arial-20-BOLD");
        game_status.setColor(color);
        add(game_status);

        waitForClick();
        remove(game_status);
    }

    private void movePaddle(GObject paddle) {
        double x = MouseInfo.getPointerInfo().getLocation().getX();
        if (x > (double) PADDLE_WIDTH / 2 && x < WIDTH - (double) PADDLE_WIDTH / 2) {
            paddle.setLocation(MouseInfo.getPointerInfo().getLocation().getX() - (double) PADDLE_WIDTH / 2, paddle.getY());
        }
    }

    /**
     * @param i          - accepts situation on game
     * @param life_count - count life
     * @param paddle     - pointer for paddle
     * @param ball       -  pointer for ball
     * @return game status and create label
     */
    private int gameStatus(int i, int life_count, GRect paddle, GOval ball) {
        if (i == 1) {   // if we win
            createGLabel("GAME IS WIN", Color.black);
            remove(ball);
            return 0;
        }
        if (i == 0) {  // if we lost
            life_count--;
            if (life_count == 0) {
                createGLabel("GAME IS OVER", Color.red);
            } else {
                createGLabel("LIFE: " + life_count, Color.darkGray);
                ball.setLocation((double) WIDTH / 2, (double) HEIGHT / 2);
                startGame(paddle, ball, life_count);
            }
        }
        return life_count;
    }

    /**
     * Create and return GOval
     */
    private GOval createBall() {

        GOval ball = new GOval((double) WIDTH / 2, (double) HEIGHT / 2, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFillColor(Color.red);
        ball.setFilled(true);
        add(ball);
        return ball;
    }

    /**
     * Start listening mouse
     */
    public void mouseMoved(MouseEvent event) {
        println("mouseMoved: " + event.getX() + "");
        //listening paddle

    }

    /**
     * Check getElementAt and return GObject if it is not null
     */
    private GObject getCollidingObject(GOval ball) {

        GObject collision = getElementAt(ball.getX(), ball.getY());
        if (collision != null) {
            return collision;
        }

        collision = getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
        if (collision != null) {
            return collision;
        }
        collision = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
        if (collision != null) {
            return collision;
        }
        collision = getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
        return collision;
    }

    /**
     * Create Wall and set color
     */
    void createBricks() {
        Color[] c1 = {Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN,};
        /*
         * we have 4 counter :
         *   + count_r - count the ROW
         *   + l - count coordinate between the box in ROW
         *   + k - count coordinate between the box in COL
         * */
        for (int l = 0, count_r = 0; count_r < NUMBER_BRICKS_PER_ROW; count_r++, l += BRICK_WIDTH + BRICK_SEP) {
            for (int k = (int) (double) BRICK_Y_OFFSET, count_c = 0; count_c < NUMBER_BRICK_ROWS; count_c++, k += BRICK_HEIGHT + BRICK_SEP) {
                Color cl = c1[count_c / 2];
                add(BuildSquare(l, k, cl));
            }
        }

    }


    /**
     * This method create and set color obj
     *
     * @param x   - set start x position
     * @param y   - set start y position
     * @param clr - set color
     * @return GObject square
     */
    private GObject BuildSquare(int x, int y, Color clr) {
        GRect square = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        square.setFilled(true);
        square.setFillColor(clr);
        return square;
    }

    /**
     * Create and return GRect
     */
    private GRect createRacket() {

        GRect racket = new GRect((double) WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET + PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        racket.setFillColor(Color.black);
        racket.setFilled(true);

        add(racket);
        return racket;
    }
}

