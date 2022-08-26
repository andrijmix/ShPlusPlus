package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO: Draw caterpillar
 */

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part6 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /* The number of segment caterpillar . */
    private static final int NUM_SEGMENT = 5;

    /* The radius of segment. */
    private static final int SEGMENT_R = 250;

    /* The color of segment. */
    private static final Color SEGMENT_COLOR = Color.green;


    public void run() {
        double yCenter = getHeight() / 2.0 ;
        int x1 = 0;
        int y1 = (int)yCenter-SEGMENT_R;
        boolean upper_segment = false;

        /*
        * flag upper_segment switches after iteration
        * (in this way we adjust the height of the segment)
        *
        * next segment will have x1 and y1 on the haft radius previous segment
        * */
        for (int i = 0; i < NUM_SEGMENT; i++) {
            add(DrawSegment(x1,y1));
            x1=x1+SEGMENT_R/2;
            if(upper_segment)
            {
                y1=y1-SEGMENT_R/2;
            }else
                y1=y1+SEGMENT_R/2;

            upper_segment = !upper_segment;
        }

    }

    private GObject DrawSegment(int x, int y) {
        GOval circle = new GOval(x,y,SEGMENT_R,SEGMENT_R);
        circle.setFilled(true);
        circle.setFillColor(SEGMENT_COLOR);
        if(circle.getColor()==SEGMENT_COLOR)
            circle.setColor(Color.black);
        return circle;
    }
}
