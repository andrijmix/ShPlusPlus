package com.shpp.p2p.cs.dholubiev.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * Assignment1Part2.java - Karel makes columns straight by filling spaces in them
 */

public class Assignment1Part2 extends KarelTheRobot {

    /**
     * Precondition: Karel is located in starting position looking east
     * Postcondition: Karel is standing at the foot of the last column
     *   and each previously "bad" columns became completely filled with stones
     */
    public void run() throws Exception {
        while (frontIsClear()) {
            fillColumnAndReturn();
            moveToNextColumn();
        }

        /* to prevent the issue of forgetting last element,
         * fill last column at the end of execution
         */
        fillColumnAndReturn();
    }

    /**
     * Precondition: nothing
     * Postcondition: Karel is looking in the opposite direction
     * from the previous position
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * Precondition: nothing
     * Postcondition: Karel is moving until he hits the wall
     */
    private void moveToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }

    }

    /**
     * Precondition: nothing
     * Postcondition: Karel put a stone in current corner if it was not there
     */
    private void putStoneIfEmpty() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    /**
     * Precondition: Karel is standing at the foot of current column
     *   facing east, he will turn left to facing north and move
     *   until he hits the wall, along the way placing stones in empty places
     * Postcondition: Karel is standing at the top of current column facing north
     *   and column is completely filled with stones
     */
    private void fillColumnWithStones() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            putStoneIfEmpty();
            move();
        }

        /* to prevent the issue of forgetting last element,
         * put a stone at the end of method execution.
         */
        putStoneIfEmpty();
    }

    /**
     * Precondition: Karel is standing at the top of the column
     *   facing north, he will turn around to facing south
     *   and move straight to the foot of the column and turn left to facing east
     * Postcondition: Karel is standing at the bottom of the column facing east
     */
    private void returnToFoot() throws Exception {
        turnAround();
        moveToWall();
        turnLeft();
    }

    /**
     * Precondition: Karel is standing at the foot of the column
     *   he will make 4 steps to the foot of the next column
     * Postcondition: Karel is standing at the bottom of the next column facing east
     */
    private void moveToNextColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    /**
     * Precondition: Karel is standing at the foot of the column
     *   he will fill empty places of the column and return to its foot facing east
     * Postcondition: Karel is standing at the bottom of column facing east
     *   and the column is completely filled with stones
     */
    private void fillColumnAndReturn() throws Exception {
        fillColumnWithStones();
        returnToFoot();
    }
}
