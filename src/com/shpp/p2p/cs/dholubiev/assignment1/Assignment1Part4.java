package com.shpp.p2p.cs.dholubiev.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * Assignment1Part4.java - Karel builds a chessboard by placing beepers
 */

public class Assignment1Part4 extends KarelTheRobot {

    /**
     * Precondition: Karel is located in starting position looking east
     * Postcondition: Karel is located in the north-east corner of world
     *  and beepers are arranged in a checkerboard pattern
     */
    public void run() throws Exception {

        if (frontIsBlocked()) {
            /* if we are here this means it is "one-column" world (0x1) */
            turnLeft();
            putBeeper();
            buildRow();
        } else {
            /* if we are here this means it is "one-row" world (1x0) or bigger */
            while (leftIsClear()) {
                /* we know that left and front is not blocked so the world is at least 2x2,
                 * so we can move through two rows in one iteration
                 */
                buildTwoRows();
            }
        }

        /* to prevent the issue of forgetting last row when their number is not even,
         * build a row at the end of execution.
         */

        if (frontIsClear() && leftIsBlocked()) {
            putBeeper();
            buildRow();
        }

    }

    /**
     * Precondition: nothing
     * Postcondition: Karel is facing 90 degrees clockwise
     *   from the previous position
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /**
     * Precondition: Karel is located at the beginning of the row facing east
     *   or at the end of the row facing west
     * Postcondition: Karel is located at the beginning of the row facing west
     *   or at the end of the row facing east
     *   and beepers behind him is placed in chessboard pattern
     */
    private void buildRow() throws Exception {
        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    /**
     * Precondition: Karel is located at the end of the passed row facing east,
     *   he will move to the next row depending on two conditions:
     *   if there is a beeper at the end of the passed row or not
     * Postcondition: Karel is located at the beginning of the next row
     *   facing west
     */
    private void moveToWestRow() throws Exception {
        turnLeft();
        if (beepersPresent()) {
            /* if there is a beeper at the end of the passed row,
             * then it should not be at the beginning of next row,
             * so we just skip the first corner of the next row
             */
            move();
            turnLeft();
            move();
            putBeeper();
        } else {
            move();
            putBeeper();
            turnLeft();
        }

    }

    /**
     * Precondition: Karel is located at the end of the passed row facing west,
     *   he will move to the next row
     * Postcondition: Karel is located at the beginning of the next row
     *   facing east
     */
    private void moveToEastRow() throws Exception {
        turnRight();
        if (frontIsClear()) {
            move();
            turnRight();
        }
    }

    /**
     * Precondition: Karel is located at the beginning of the row facing east
     *   he will move through current row placing beepers in chessboard pattern,
     *   then he will move to the next row
     * Postcondition: Karel is located at the beginning of the next row
     *   facing west
     */
    private void buildMovingEast() throws Exception {
        putBeeper();
        buildRow();
        moveToWestRow();
    }

    /**
     * Precondition: Karel is located at the beginning of the row facing west
     *   he will move through current row placing beepers in chessboard pattern,
     *   then he will move to the next row
     * Postcondition: Karel is located at the beginning of the next row
     *   facing east
     */
    private void buildMovingWest() throws Exception {
        buildRow();
        moveToEastRow();
    }

    /**
     * Precondition: Karel is located at the beginning of the row facing east
     *   he will move through two rows placing beepers in chessboard pattern
     *   until his left side is blocked
     * Postcondition: Karel is located at the beginning of the passed rows
     *   facing west
     */
    private void buildTwoRows() throws Exception {
        buildMovingEast();
        buildMovingWest();
    }

}
