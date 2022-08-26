package com.shpp.p2p.cs.dholubiev.assignment1;

import com.shpp.karel.KarelTheRobot;

/*
 * Assignment1Part1.java - Karel moves to the beeper (newspaper) outside,
 * picks it up and returns to start position
 */

public class Assignment1Part1 extends KarelTheRobot {

    /**
     * Precondition: Karel is located in starting position facing east
     * Postcondition: Karel is located in start position with a beeper (newspaper) in his backpack
     *   facing east
     */
    public void run() throws Exception {
        moveToNewspaper();
        pickNewspaper();
        returnHome();
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
     * Precondition: nothing
     * Postcondition: Karel is facing in the opposite direction
     *   from the previous position
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
     * Precondition: Karel is located in the starting position facing east,
     *   he will move near the wall touching it with his left hand,
     *   and keep doing it until he finds a place
     *   where there will be no wall on the left side of him (found the door)
     * Postcondition: Karel is facing east and there is no wall in front of him
     */
    private void findTheDoor() throws Exception {
        while (leftIsBlocked()) {
            if (frontIsClear()) {
                move();
            } else {
                turnRight();
            }
        }
    }

    /**
     * Precondition: nothing
     * Postcondition: Karel is moving until he gets corner with the newspaper inside
     */
    private void moveToBeeper() throws Exception {
        while (noBeepersPresent()) {
            move();
        }
    }

    /**
     * Precondition: Karel located the starting position facing east
     * Postcondition: Karel is standing in the corner
     *   with the newspaper inside facing east
     */
    private void moveToNewspaper() throws Exception {
        findTheDoor();
        turnLeft();
        moveToBeeper();
    }

    /**
     * Precondition: Karel is standing in the corner
     *   with the newspaper inside facing east
     *   he will pick newspaper and turn around
     * Postcondition: Karel is standing in the same corner
     *   with the newspaper in his backpack facing west
     */
    private void pickNewspaper() throws Exception {
        pickBeeper();
        turnAround();
    }

    /**
     * Precondition: Karel is standing outside with the newspaper
     *   in his backpack facing west (at the door),
     *   he will move until he hits the wall, turn right
     *   and return to his starting position
     * Postcondition: Karel is standing in the starting position
     *   with the newspaper in his backpack facing east
     */
    private void returnHome() throws Exception {
        moveToWall();
        turnRight();
        moveToWall();
        turnRight();
    }

}