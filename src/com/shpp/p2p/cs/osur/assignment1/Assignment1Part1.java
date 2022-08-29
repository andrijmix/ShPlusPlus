/** File: Assignment1Part1.java
 * Task: Karel lives in a square house. Karel starts in the northwest corner of his house, and he needs to pick up
 * a newspaper that lies on the doorstep of his house. After picked up the newspaper, Karel must to return
 * to the starting position.
 * Specification - in the paragraph "Problem 1 - pick up newspaper".
 */
package com.shpp.p2p.cs.osur.assignment1;

public class Assignment1Part1 extends KarelCommands {

    /**
     * Prerequisites: The Karel solves to collect the newspaper from outside the doorway and then to return to its
     *                initial position.
     * Result: The task has been decomposed on  three part (methods) and run its.
     */

    public void run() throws Exception {
        moveToNewspaper();
        getNewspaper();
        returnToStartPoint();
    }

    /**
     * Prerequisites: The Karel state near the newspaper.
     * Result: The Karel pickup the newspaper.
     */
    private void getNewspaper() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
        }
    }

    /**
     * Prerequisites: Karel move to the newspaper.
     * Result: Karel was stopped near the newspaper.
     */
    private void moveToNewspaper() throws Exception {
        moveToWall();
        turnRight();
        move();
        turnLeft();
        move();
        move();
    }

    /**
     * Prerequisites: The Karel return to initial position.
     * Result: The Karel with newspaper has returned to his starting position.
     */
    private void returnToStartPoint() throws Exception {
        turnAround();
        moveToWall();
        turnRight();
        move();
        turnRight();
    }
}
