/** File: Assignment1Part2.java
 * Task: Karel needs to rebuild slender rows of stones. Each column must be an integral structure.
 * Specification - in the paragraph "Problem 2 - rows of stones".
 */
package com.shpp.p2p.cs.osur.assignment1;

public class Assignment1Part2 extends KarelCommands {

    /**
     * Prerequisites: The Karel is at the end of a column, facing east.
     * Result: Karel has finished his work. The missing stones in the columns was been filled in with beepers.
     */
    public void run() throws Exception {
        repairColumn();
        while (frontIsClear()){
            repairNextColumn();
        }
    }

    /**
     * Prerequisites: The Karel is at the end of a certain column that has already been repaired,
     *                and he need to move to unrepaired column to the right
     * Result: The Karel is has moved to and repaired the adjacent column that is at the right of that certain column,
     *         and stay at the end of it.
     */
    private void repairNextColumn() throws Exception {
        goToNextColumn();
        repairColumn();
    }

    /**
     * Prerequisites: The Karel is at the upper-end of a column. And he need to return down.
     * Result: The Karel is at the end of a column.
     */
    private void backToOriginalPosition() throws Exception {
        turnAround();
        while(frontIsClear()){
            move();
        }
        turnLeft();
    }

    /**
     * Prerequisites: The Karel is at the end of a column that may needs to be repaired.
     * Result: The Karel is at the same position, and all the column is repaired.
     */
    private void repairColumn() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }
        if (noBeepersPresent()) {
            putBeeper();
        }
        backToOriginalPosition();
    }

    /**
     * Prerequisites: The Karel is at the bottom of column.
     * Result: The Karel has moved to the right, on the next column.
     */
    private void goToNextColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }
}
