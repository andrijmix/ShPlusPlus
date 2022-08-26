package com.shpp.p2p.cs.dholubiev.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * Assignment1Part3.java - Karel makes columns straight by filling spaces in them
 */

public class Assignment1Part3 extends KarelTheRobot {

    /**
     * Precondition: Karel is located in starting position facing east
     * Postcondition: Karel is located in the middle of the row
     *   and there is beeper in that corner
     */
    public void run() throws Exception {

        // case in which the row length is less than 2
        if (frontIsBlocked()) {

            //in this case we just put beeper and execution is finished
            putBeeper();

        } else {

            /* put beepers in the beginning and in the end of the row
             * and narrow the row
             */
            makeFirstBorder();

            // we are moving if there is no beepers present
            while(noBeepersPresent()){
                move();

                /* if we found a beeper, we put one there so the row becomes narrower
                 * and borders is getting closer to the middle
                 */
                if(beepersPresent()){
                    turnAround();
                    move();
                    putBeeper();
                    move();
                }

            }

            /* we are standing right next to the middle of the row now,
             * and row is filled by beepers,
             * we are turning around and make one step to be exactly right in the middle
             */
            turnAround();
            move();

            /* put one more beeper to flag it,
             * and now middle has two beepers inside
             */
            putBeeper();

            /**
             * Now we are in situation when row is filled by beepers
             *   and middle corner has two beepers inside, so
             *   all we need to do is clear one beeper in every corner
             */

            // move to the edge of the row, so we can start cleaning up beepers
            moveToWall();
            turnAround();

            // cleaning them up
            cleanBeepers();

            /**
             * Now our row is empty except middle, it has one beeper,
             * so we are just moving until they find it
             */
            moveToMiddle();
        }


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
        while(frontIsClear()){
            move();
        }
    }

    /**
     * Precondition: Karel located in starting position facing east
     *   he will move until he hits the wall,
     *   then put a beeper, turn around and make a step
     * Postcondition: Karel is located in the next corner after edge facing west
     */
    private void makeFirstBorder() throws Exception {
        putBeeper();
        moveToWall();
        putBeeper();
        turnAround();
        move();
    }

    /**
     * Precondition: Karel is located at the beginning of the row facing east
     *   he will move through row and pick one beeper
     * Postcondition: Karel is located in the end of the row facing west and there is
     *   only one beeper in the middle corner of the row, other corners is empty
     */
    private void cleanBeepers() throws Exception {
        while(frontIsClear()){
            pickBeeper();
            move();
        }
        pickBeeper();
        turnAround();
    }

    /**
     * Precondition: Karel is located in the end of the row facing west
     *   he will move until he finds a beeper
     * Postcondition: Karel is located in the middle of the row facing east
     *   and there is beeper in that corner
     */
    private void moveToMiddle() throws Exception {
        while(noBeepersPresent()) {
            move();
        }
    }


}
