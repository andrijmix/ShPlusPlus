package com.shpp.p2p.cs.amikhnevych.assignment1;
/*
* TODO take newspaper
*  */

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        goToNewspaper();
        pickNewspaper();
        comebackToStart();
    }
    /*go no newspaper*/
    private void goToNewspaper() throws Exception {
        goToFrontBarrier();
        turnRight();
        move();
        turnLeft();
        move();
        move();
    }
/*pick newspaper*/
    private void pickNewspaper() throws Exception {
        pickBeeper();

    }
/*go to first possitions*/
    private void comebackToStart() throws Exception {
        turn180degrees();
        goToFrontBarrier();
        turnRight();
        move();
    }

    /*
    * --------service functions---------
    * 1-turn180degrees
    * 2-turnRight
    * 3-goToFrontBarrier
    *
    * */


    /*
    Karel will be move direct when front is clear
     */
    private void goToFrontBarrier() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /*
    Karel will turn around
     */
    private void turn180degrees() throws Exception {
        turnLeft();
        turnLeft();

    }

    /*
    * Karel will return 270 degrees
    * */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

// end service functions

}