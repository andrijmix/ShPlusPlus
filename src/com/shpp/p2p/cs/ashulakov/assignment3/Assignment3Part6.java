package com.shpp.p2p.cs.ashulakov.assignment3;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Animation. Build countdown 5 seconds length
 * <p>
 * Code snippet of methods GStar is taken from the book 'TheArtAndScienceOfJava' - Chapter 9. Object-Oriented Graphics
 */
public class Assignment3Part6 extends WindowProgram {
    /**
     * Animation duration
     */
    public final static int DURATION = 5000;
    /**
     * Padding around countdown
     */
    public final static int PADDING = 25;
    /**
     * Font face for countdown
     */
    public final static String SECONDS_FONT = "Verdana-120";
    /**
     * Font face for final label
     */
    public final static String FINAL_FONT = "Verdana-40";
    /**
     * Final label text
     */
    public final static String FINAL_TEXT = "Nice!";
    /**
     * Countdown timer size
     */
    public final static int TIMER_SIZE = 150;
    /**
     * Maximum number of stars per iteration
     */
    public final static int ITERATION_STARS_NUMBER = 15;
    /**
     * Maximum size of star
     */
    public final static int STARS_MAX_SIZE = 20;
    /**
     * Pause between frames
     */
    public final static double BETWEEN_FRAMES_PAUSE = 1000 / 60.0;
    public double countFPS = 0;

    // Draws animation and printing results in console
    public void run() {
        // calculating coordinates of window center
        double xCenter = getWidth() / 2.0;
        double yCenter = getHeight() / 2.0;
        // take start time
        long start = System.currentTimeMillis();

        // draw process recursively
        drawsSingeSecondAnimation(start, xCenter, yCenter);
        //draw final message
        drawFinalText(FINAL_TEXT);

        // console info about start, finish and duration time
        println("start - " + start);
        println("now - " + System.currentTimeMillis());
        println("duration: " + millisPassedAway(start) + "ms");
        println("FPS: " + countFPS / 5);
    }

    /**
     * Draws stars mosaic picture with countdown
     *
     * @param start   - start time pointer in milliseconds
     * @param xCenter - window center x-coordinate
     * @param yCenter - window center y-coordinate
     */
    private void drawsSingeSecondAnimation(long start, double xCenter, double yCenter) {
        // loop process while not outran from animation DURATION
        if (millisPassedAway(start) < DURATION) {
            // generate random color for single animation second
            RandomGenerator rgen = RandomGenerator.getInstance();
            Color color = rgen.nextColor();
            // calculate countdowns name and labeled it in a window center area
            int currentSec = getCurrentSec(start);
            GLabel s = drawSecLabel("" + (DURATION / 1000 - currentSec), xCenter, yCenter, color);
            add(s);
            GCompound comp = new GCompound();
            // start drawing mosaic
            drawFrames(xCenter, yCenter, start, currentSec, color);
            // remove name after current second is finished
            remove(s);

            // next step of countdown
            drawsSingeSecondAnimation(start, xCenter, yCenter);
        }
    }

    /**
     * Draws mosaic with 5-angles stars and countdown pointer
     *
     * @param xCenter    - window center x-coordinate
     * @param yCenter    - window center y-coordinate
     * @param start      - start time pointer in milliseconds
     * @param currentSec - number of seconds remaining
     * @param color      - the color of the drawing
     */
    private void drawFrames(double xCenter, double yCenter, long start, int currentSec, Color color) {
        // continue drawing the mosaic until we are within one second
        if (deltaInCurrentSec(start, currentSec) < 1000) {
            // update countdown pointer
            GArc a = drawPointer(xCenter, yCenter, (TIMER_SIZE + PADDING), start, currentSec, color);
            add(a);
            countFPS++;
            pause(BETWEEN_FRAMES_PAUSE);
            remove(a);
            // drawing mosaic
            drawStars(Math.random() * STARS_MAX_SIZE, color, xCenter, yCenter);

            // drawing new frame recursively
            drawFrames(xCenter, yCenter, start, currentSec, color);
        }
    }

    /**
     * Returns the fraction of a second that has elapsed
     *
     * @param start      - start time pointer
     * @param currentSec - current full seconds that elapsed
     * @return - fraction of current second
     */
    private int deltaInCurrentSec(long start, int currentSec) {
        return (int) (millisPassedAway(start) - currentSec * 1000);
    }

    /**
     * Returns milliseconds that elapsed from animation start
     *
     * @param start - start time pointer
     * @return - milliseconds that elapsed
     */
    private long millisPassedAway(long start) {
        return (System.currentTimeMillis() - start);
    }

    /**
     * Returns number of full seconds that elapsed
     *
     * @param start - start time pointer
     * @return - number of seconds
     */
    private int getCurrentSec(long start) {
        return (int) (millisPassedAway(start) / 1000);
    }

    /**
     * Draws an arc in the center of the window
     * with a size corresponding to the value of milliseconds
     * that have passed since the beginning of the current second
     *
     * @param x          - x-coordinate of center of the arc
     * @param y          - y-coordinate of center of the arc
     * @param size       - arc size
     * @param start      - start time pointer
     * @param currentSec - current full seconds that elapsed
     * @param color      - color of drawing
     * @return - arc with set color
     */
    private GArc drawPointer(double x, double y, int size, long start, int currentSec, Color color) {
        int theta = 360 * deltaInCurrentSec(start, currentSec) / 1000;
        GArc arc = new GArc(x - size / 2.0, y - size / 2.0, size, size, 90 - theta, theta);
        arc.setColor(color);
        return arc;
    }

    /**
     * Draws label with the full seconds that remaining
     *
     * @param name    - number of full seconds that remaining
     * @param xCenter - x-coordinate for label
     * @param yCenter - y-coordinate for label
     * @param color   - label color
     * @return - label with the color
     */
    private GLabel drawSecLabel(String name, double xCenter, double yCenter, Color color) {
        GLabel l = getLabel(name, xCenter, yCenter, color, SECONDS_FONT);
        l.move(-l.getWidth() / 2, (l.getAscent() - l.getDescent()) / 2);
        return l;
    }

    /**
     * Return GLabel object with font and color from params
     *
     * @param s     - label text
     * @param x     - x-coordinate for label
     * @param y     - y-coordinate for label
     * @param color - label color
     * @param font  - label font
     * @return - GLabel object
     */
    private GLabel getLabel(String s, double x, double y, Color color, String font) {
        GLabel label = new GLabel(s, x, y);
        label.setColor(color);
        label.setFont(font);
        return label;
    }

    /**
     * Draws a mosaic of stars with a number that does not exceed a ITERATION_STARS_NUMBER
     * and does not fall within a circle equal to the size of the countdown
     *
     * @param size    - maximum size of single star element
     * @param color   - color of a star
     * @param xCenter - x-coordinate for star
     * @param yCenter - x-coordinate for star
     */
    private void drawStars(double size, Color color, double xCenter, double yCenter) {
        // generate random coordinates for stars
        RandomGenerator rgen = RandomGenerator.getInstance();
        for (int i = 0; i < ITERATION_STARS_NUMBER; i++) {
            double x = rgen.nextDouble(PADDING, getWidth() - PADDING);
            double y = rgen.nextDouble(PADDING, getHeight() - PADDING);

            // cut off the coordinates that fall into the zone of the countdown scoreboard
            if ((Math.pow(xCenter - x, 2) + Math.pow(yCenter - y, 2)) > Math.pow(TIMER_SIZE - PADDING - size, 2)) {
                GPolygon star = GStar(size);
                star.setFilled(true);
                star.setColor(color);
                add(star, x, y);
            }
        }
    }

    /**
     * Defines a new GObject class that appears as a
     * five-pointed star.
     *
     * @return GPolygon object with a star and width equal for parameter 'width'
     */
    public GPolygon GStar(double width) {
        GPolygon star = new GPolygon();
        double dx = width / 2;
        double dy = dx * GMath.tanDegrees(18);
        double edge = width / 2 - dy * GMath.tanDegrees(36);
        star.addVertex(-dx, -dy);
        int angle = 0;
        for (int i = 0; i < 5; i++) {
            star.addPolarEdge(edge, angle);
            star.addPolarEdge(edge, angle + 72);
            star.move(10, 10);
            angle -= 72;
        }
        return star;
    }

    /**
     * Draws final text message at the center of window
     *
     * @param text - message text
     */
    private void drawFinalText(String text) {
        GLabel finalText = getLabel(text, 0, 0, Color.BLACK, FINAL_FONT);
        finalText.setLocation(
                (getWidth() - finalText.getWidth()) / 2,
                (getHeight() + finalText.getAscent()) / 2
        );
        add(finalText);
    }
}