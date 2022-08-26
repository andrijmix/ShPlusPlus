package com.shpp.p2p.cs.amikhnevych.assignment1;
/*
 * TODO build pillar
 *  */
import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {
       // turnLeft();
        /*
        * fist Karel will build (beacouse start in coner)
        * then will return to start possitions
        * and find next pillar
        * */
        while (frontIsClear()) {
            Build();
            returnToStratPossition();
            stepToNextPillar();
        }
        Build();
    }
// Karel come back in start possition
    private void returnToStratPossition() throws Exception {
        turn180degrees();
        goToFrontBarrier();
        turnLeft();
    }

    /*
* move 3 step to x axis
* (according to the conditions, the next column is in 3 steps)
* */
    private void stepToNextPillar() throws Exception {
        if(frontIsClear()) {
            for (int i = 0; i <= 3; i++) {
                move();
            }
        }
    }

    /*
* Robot will be build to upper and then come back to start possitions
* */
    private void Build() throws Exception {
        turnLeft();
        while (frontIsClear())
        {
            if(!beepersPresent())
            {
                putBeeper();
            }
            move();
        }
        //check cell near wall
        if(!beepersPresent())
        {
            putBeeper();
        }
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

// end service functions

}