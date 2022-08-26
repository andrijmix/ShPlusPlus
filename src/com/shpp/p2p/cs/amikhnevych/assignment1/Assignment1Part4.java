package com.shpp.p2p.cs.amikhnevych.assignment1;
/*
 * TODO draw chess map
 *  need : cell in lower lest corner must be fill
 *  */
import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {

    /*

      1-fill first row
      2- in while fill rows to the last row
      3 - fill the last row
     * */
    public void run() throws Exception {
        /*
        * put beeper on the cell when Karel started
        * */
        putBeeper();
        /*
        * if on lest and front no have barriers - move
        * */
        while(leftIsClear()){
            if (frontIsClear()){
                move();
                fillRowToBarrier();
            } else { // if we on the top - need turn left and look on East
                if (facingEast()){
                    turnLeftAndMove();
                } else if (facingWest() )
                if(rightIsClear())
                {
                    turnRightAndMoveUp();
                } else {
                    //when at top
                    turnRight();
                }

            }
        }
        //if top  row
        while (facingEast() && leftIsBlocked() && frontIsClear()) {
            // is we standing on beeper - move
            if (beepersPresent()){
                move();
                fillRowToBarrier();
            } else {
                fillRowToBarrier();
            }
        }
    }
    /* Prerequisites: Front is Clear and we standing on beeper
     * Result: Karel fill the row on beeper to end
     */
    private void fillRowToBarrier() throws Exception {
        if (frontIsClear()){
            move();
            putBeeper();
        }
    }
    /* Prerequisites: Karel fill row and now on the end row
     * Result: Karel put beeper on the first cell and continue fil the row to end
     */
    private void turnLeftAndMove() throws Exception {

        turnLeft();
        if (frontIsClear() )
            if(beepersPresent()){
            move();
            turnLeft();
            fillRowToBarrier();
        }else if (frontIsClear() && noBeepersPresent())
  {
            fillRowToBarrier();
            turnLeft();
        }


    }
    /* Prerequisites: Karel on the left end and we have no fill cell
     * Result: Karel turn right , move , put beeper and turn right
     */
    private void turnRightAndMoveUp() throws Exception {
        turnRight();
        if (frontIsClear()){
            move();
            putBeeper();
        }
        turnRight();
    }

    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}
