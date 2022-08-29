package com.shpp.p2p.cs.amikhnevych.assignment1;

/*
 * TODO search middle
 *  */
import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

    /*
     * 1- full all cells
     * 2- take beeper if have on the ends (recursion)
     *
     * */
    public void run() throws Exception {
        if(!frontIsClear())
        {
        putBeeper();
        }else {
            fullAllCellsExFirst();
            TakeBeeperOnEnds();
            putBeeper();
        }
    }

    // Take beepers to the end
    private void TakeBeeperOnEnds() throws Exception {
        pickBeeper();
        if (frontIsBlocked())
            turn180degrees();
        move();
        while (beepersPresent()) //will be move until beeper under your legs
        {
            move();
        }

        if (noBeepersPresent()) //if we have empty cell come back one step
        {
            comeBackStep();
        }
        if (beepersPresent()) {
            TakeBeeperOnEnds();
        }
    }
/*
* if we have last beeper - return one step
* */
    private void comeBackStep() throws Exception {
        turn180degrees();
        move();
    }
//full cells beepers (ex first)
    private void fullAllCellsExFirst() throws Exception {


        while (frontIsClear()) {
            move();
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }
        if (noBeepersPresent()) {
            putBeeper();
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