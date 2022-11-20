package com.shpp.p2p.cs.amikhnevych.assignment2;

/* TODO:
Part 6 - Caterpillars
Use GOval and draw the most enchanting caterpillar in the world.
The ovals must overlap each other in the correct order, otherwise there will be porridge.
In this task, we do not require the caterpillar to be centered in the window or occupy its entire space.
On the other hand, you will have to think about constants yourself. And don't forget to comment on their purpose!
The color of the circle must differ from the color of the border of this circle.
It should be possible to easily change the number of track segments.
Well, that's free creativity!
 */

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part6 extends WindowProgram {

    /* The number of segment caterpillar . */
    private static final int NUM_SEGMENT = 5;

    /* The radius of segment. */
    private static final int SEGMENT_R = 100;

    /* The color of segment. */
    private static final Color SEGMENT_COLOR = Color.green;


    public void run() {
        double yCenter = getHeight() / 2.0;
        int x1 = 10;
        int y1 = (int) yCenter - SEGMENT_R;
        boolean upper_segment = true;

        /*
         * flag upper_segment switches after iteration
         * (in this way we adjust the height of the segment)
         *
         * next segment will have x1 and y1 on the haft radius previous segment
         * */
        for (int i = 0; i < NUM_SEGMENT; i++) {
            add(DrawSegment(x1, y1));
            x1 = x1 + SEGMENT_R * 3 / 5;
            if (upper_segment) {
                y1 = y1 - SEGMENT_R * 3 / 5;
            } else
                y1 = y1 + SEGMENT_R * 3 / 5;

            upper_segment = !upper_segment;
        }

    }

    /**
     * //+----------------------------------------------------------------------------+
     * //|  Description: This method return  object type oval and fill it color       |
     * //+----------------------------------------------------------------------------+
     * //|  Parameters :                                                              |
     * //|    x1 - Ox  coordinate                                                     |
     * //|    y1 - Oy  coordinate                                                     |
     * //+----------------------------------------------------------------------------+
     */
    private GObject DrawSegment(int x, int y) {
        GOval circle = new GOval(x, y, SEGMENT_R, SEGMENT_R);
        circle.setFilled(true);
        circle.setFillColor(SEGMENT_COLOR);
        if (circle.getColor() == SEGMENT_COLOR)
            circle.setColor(Color.black);
        return circle;
    }
}
