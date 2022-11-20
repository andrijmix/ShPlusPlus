package com.shpp.p2p.cs.itkachuk.assignment8;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

/**
 * The class moves the squares.
 * It has one run method, which calls 5 methods.
 */
public class Assignment8 extends WindowProgram {

    private static final double RECT_WIDTH = 15;
    // The variables responsible for rectangles objects.
    GRect rect1;
    GRect rect2;
    GRect rect3;
    GRect rect4;

    /**
     *
     */
    public void run() {
        addMouseListeners();
        rect1 = createRectangles();
        rect2 = createRectangles();
        rect3 = createRectangles();
        rect4 = createRectangles();
    }

    /**
     * Creates a rectangle.
     *
     * @return GRect rect.
     */
    private GRect createRectangles() {
        double x = RandomGenerator.getInstance().nextDouble(0, getWidth() - RECT_WIDTH);
        double y = RandomGenerator.getInstance().nextDouble(0, getHeight() - RECT_WIDTH);
        GRect rect = new GRect(x, y, RECT_WIDTH, RECT_WIDTH);
        rect.setFilled(true);
        add(rect);
        return rect;
    }

    /**
     * The behavior of the squares when the mouse is pressed.
     *
     * @param e Event e.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        double x1 = 5;
        double y1 = 5;

        if (e.getX() > rect1.getX()) x1 = -x1;
        if (e.getY() > rect1.getY()) y1 = -y1;
        rect1.setLocation(rect1.getX() - x1, rect1.getY() - y1);

        double x2 = 5;
        double y2 = 5;

        if (e.getX() > rect2.getX()) x2 = -x2;
        if (e.getY() > rect2.getY()) y2 = -y2;
        rect2.setLocation(rect2.getX() - x2, rect2.getY() - y2);

        double x3 = 5;
        double y3 = 5;
        if (e.getX() > rect3.getX()) x3 = -x3;
        if (e.getY() > rect3.getY()) y3 = -y3;
        rect3.setLocation(rect3.getX() + x3, rect3.getY() + y3);

        double x4 = 5;
        double y4 = 5;
        if (e.getX() > rect4.getX()) x4 = -x4;
        if (e.getY() > rect4.getY()) y4 = -y4;
        rect4.setLocation(rect4.getX() + x4, rect4.getY() + y4);
    }

    /**
     * The behavior of the squares when the mouse is released.
     *
     * @param e Event e.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        double x1 = 5;
        double y1 = 5;

        if (e.getX() > rect1.getX()) x1 = -x1;
        if (e.getY() > rect1.getY()) y1 = -y1;
        rect1.setLocation(rect1.getX() + x1, rect1.getY() + y1);

        double x2 = 5;
        double y2 = 5;

        if (e.getX() > rect2.getX()) x2 = -x2;
        if (e.getY() > rect2.getY()) y2 = -y2;
        rect2.setLocation(rect2.getX() + x2, rect2.getY() + y2);

        double x3 = 5;
        double y3 = 5;
        if (e.getX() > rect3.getX()) x3 = -x3;
        if (e.getY() > rect3.getY()) y3 = -y3;
        rect3.setLocation(rect3.getX() - x3, rect3.getY() - y3);

        double x4 = 5;
        double y4 = 5;
        if (e.getX() > rect4.getX()) x4 = -x4;
        if (e.getY() > rect4.getY()) y4 = -y4;
        rect4.setLocation(rect4.getX() - x4, rect4.getY() - y4);
    }
}

