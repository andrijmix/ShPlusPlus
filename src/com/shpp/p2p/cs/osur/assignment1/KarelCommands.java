/** File: KarelCommands.java
 * The KarelCommands class is designed for avoid duplicate code.
 * It's contains common methods for all tasks.
 */

package com.shpp.p2p.cs.osur.assignment1;

import com.shpp.karel.KarelTheRobot;

public class KarelCommands extends KarelTheRobot {

    /* Rotate Karel in place  */
    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /* Turn Karel to the right */
    public void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /* Karel move until will reach wall or edge */
    public void moveToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }
}
