/** File: Assignment1Part3.java
 * Task: Karel needs to search for the middle of the low-end line.
 * Specification - in the paragraph "Problem 3 - find middle".
 */

package com.shpp.p2p.cs.osur.assignment1;

public class Assignment1Part3 extends KarelCommands {

    /**
     * Prerequisites: The goal of this program is to be able to let Karel find and stop at the midpoint.
     * Result: The Karel found middle square and put one beeper in the middle square
     */
    public void run() throws Exception {
        setBeepers();
        pickUpBeepers();
        putMiddleBeeper();
    }

    /**
     * Prerequisites: The Karel need to drops beepers along the first row.
     * Result: The Karel drops beepers along the first row until front is clear.
     */
    private void setBeepers() throws Exception {
        while (frontIsClear()) {
            move();
            putBeeper();
        }
    }

    /**
     * Prerequisites: The Karel at the right side world. He must to pickup all unnecessary beepers.
     * Result: The karel stay at the middle of world.
     */
    private void pickUpBeepers() throws Exception {
        if (beepersPresent()) { pickBeeper(); }
           pickOutsideBeepers();
    }

    /**
     * Prerequisites: The Karel scan row from west to east and backwards, and picked up beepers at the edge points.
     * Result: The Karel picked up all beepers.
     */
    private void pickOutsideBeepers() throws Exception {
        while (beepersPresent()) {
            move();
        }
        turnAround();
        if (frontIsClear()) { move(); }
        if (beepersPresent()) {
            pickBeeper();
            move();
            pickOutsideBeepers();
        }
    }

    /**
     * Prerequisites: The Karel stays in the middle of the row.
     * Result: The Karel puts the last beeper in the middle of the first row.
     */
    private void putMiddleBeeper() throws Exception {
        putBeeper();
    }
}

