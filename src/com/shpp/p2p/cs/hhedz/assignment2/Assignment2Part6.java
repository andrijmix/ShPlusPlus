package com.shpp.p2p.cs.hhedz.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    //constant for the number of caterpillar segments
    final static int circleCount = 2;

    public void run() {
        //We calculate the size of the segment to place everyone along the length of the window
        //if wr have even numbers of segments we need add 0.5 else we need add 1
        double size = getWidth() / (circleCount / 2 + pairOrNot(circleCount));
        drawCaterpillar(size);
    }

    /*
    @param circleCount The number of caterpillar segments
    This method checks if the number of segments is even
     */
    private double pairOrNot(int circleCount) {
        if (circleCount % 2 == 0)
            return 0.5;
        else {
            return 1;
        }
    }

    /*
    @param size The size of 1 caterpillar segment
    This method draw caterpillar width of the entire window and centered in height
     */
    private void drawCaterpillar(double size) {
        for (int i = 0; i < circleCount; i++) {
            //draw one segment above and one below in turn
            if (i % 2 == 0) {
                setOval(i * (size / 2), getHeight() / 2 - size / 2, size, size);
            } else {
                //to draw segment below add 1/4 of size to height
                setOval(i * (size / 2), getHeight() / 2 - size / 2 + size / 4, size, size);
            }
        }
    }

    /*
   @param x The x coordinate of the upper-left corner of the bounding box for the oval.
   @param y The y coordinate of the upper-left corner of the bounding box for the oval.
   @param width The width of oval
   @param height The height of oval
   This method draws black oval according to the coordinates that we transmit
    */
    void setOval(double x, double y, double width, double height) {
        GOval oval = new GOval(x, y, width, height);
        oval.setFillColor(Color.BLACK);
        oval.setColor(Color.RED); //set another color for outline
        oval.setFilled(true);
        add(oval);
    }
}
