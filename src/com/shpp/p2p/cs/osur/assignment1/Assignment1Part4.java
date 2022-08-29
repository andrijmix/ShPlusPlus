/** File: Assignment1Part4.java
 * TODO: Karel was instructed to create a "chessboard" with a beeper in a rectangular
 * (NOT square!) empty world.
 * Specification - in the paragraph "Problem 4 - chessboard".
 */

package com.shpp.p2p.cs.osur.assignment1;

public class Assignment1Part4 extends KarelCommands {

    /**
     * Prerequisites: Karel starts from the south-west corner, looking to the east.
     * Result: Karel goes row by row (if rows are several) and put down beepers across the cell.
     *         The result is a chessboard of beeper and empty cells.
     */
    public void run() throws Exception {
        setBeepersInRow();
        while (frontIsClear()) {
            goToNextRow();
            if (frontIsClear()) {
                setBeepersInRow();
            }
        }
    }

    /**
     * Prerequisites: Karel is at a square where a beeper needs to be in place. And put down a beeper every other step.
     * Result: The row is now completed.
     *
     */
    private void setBeepersInRow() throws Exception {
        putBeeper();

        while (frontIsClear()) {
            move();

            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
        turnUp();
    }

    /**
     * Prerequisites: Karel is new wall and facing east or west.
     * Result: Karel is facing north
     *
     */
    private void turnUp() throws Exception {
        if (facingEast()) {
            turnLeft();
        }
        else {
            if (facingWest()) {
                turnRight();
            }
        }
    }

    /**
     * Prerequisites: Karel is facing north
     * Result: Karel is facing east or west, with a wall right behind it
     */
    private void turnToSide() throws Exception {
        if (leftIsClear()) {
            turnLeft();
        } else {
            turnRight();
        }
    }

    /**
     * Prerequisites: Karel need to go the next row, if there is at least one row above the row that Karel stay currently.
     * Result: Karel has moved onto the next row, to a space that needs to put new beeper.
     */
    private void goToNextRow() throws Exception {
        if (noBeepersPresent()) {
            move();
            turnToSide();
        }
        else {
            move();
            turnToSide();

            if (frontIsClear()) {
                move();
            }
            else {
                turnUp();
                if (frontIsClear()) {
                    move();
                }
            }

        }
    }
}

