package com.shpp.p2p.cs.gkorobov.assignment3;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * this program make a graphic which take a 5 second
 *
 * @author Gleb Korobov
 * @version 1.0
 */
public class Assignment3Part6 extends WindowProgram {
    /**
     * The amount of time to pause between frames (64fps)
     */
    private static final double PAUSE_TIME = 1000.0 / 64;

    /**
     * The initial horizontal velocity of the car
     */
    private static final double HORIZONTAL_VELOCITY = 4.0;

    /**
     * size of wheel
     */
    private static final double DIAMETER_OF_WHEEL = 50;

    /**
     * Size of door
     */
    private static final double SIZE_OF_DOOR_WIDTH = 1;
    private static final double SIZE_OF_DOOR_HEIGHT = 85;

    /**
     * Size of window
     */
    private static final double SIZE_OF_WINDOW_WIDTH = 60;
    private static final double SIZE_OF_WINDOW_HEIGHT = 25;

    /**
     * Colors of Objects
     */
    private static final Color CAR_COLOR = Color.BLUE;
    private static final Color WINDOW_COLOR = Color.LIGHT_GRAY;
    private static final Color DOOR_COLOR = Color.BLACK;
    private static final Color FRONT_LIGHT_COLOR = Color.YELLOW;
    private static final Color REAR_LIGHT_COLOR = Color.RED;
    private static final Color WHEEL_COLOR = Color.BLACK;

    /**
     * Size of the world
     */
    public static final int APPLICATION_WIDTH = 1500;
    public static final int APPLICATION_HEIGHT = 500;

    public void run() {
        makeGroundAndSky();
        long timeCurrent = System.currentTimeMillis();
        moveCar(makeCar());
        long timeEnd = System.currentTimeMillis() - timeCurrent;
        System.out.println(timeEnd);
        println("FPS " + count_FPS);
    }

    /**
     * This method make a car
     *
     * @return ready car
     */
    private GCompound makeCar() {
        GCompound Car = new GCompound();
        Car.add(makeFirstWheel());
        Car.add(makeSecondWheel());
        Car.add(makeCarBody());
        Car.add(makeCarCab());
        Car.add(makeFirstWindow());
        Car.add(makeSecondWindow());
        Car.add(makeFirstDoor());
        Car.add(makeSecondDoor());
        Car.add(makeThirdDoor());
        Car.add(makeFrontLight());
        Car.add(makeRearLight());
        add(Car);
        return Car;
    }

    /**
     * this method move the car
     *
     * @param car an Object of the car
     */

    int count_FPS = 0;

    private void moveCar(GCompound car) {
        while (car.getX() != (APPLICATION_WIDTH - 340)) {
            count_FPS++;
            car.move(HORIZONTAL_VELOCITY, 0);
            pause(PAUSE_TIME);
        }
    }

    /**
     * make first door and a color
     *
     * @return ready first door
     */
    private GRect makeFirstDoor() {
        GRect FirstDoor = new GRect(100, 340, SIZE_OF_DOOR_WIDTH, SIZE_OF_DOOR_HEIGHT);
        FirstDoor.setFilled(true);
        FirstDoor.setFillColor(DOOR_COLOR);
        return FirstDoor;
    }

    /**
     * make second door and a color
     *
     * @return ready second door
     */
    private GRect makeSecondDoor() {
        GRect SecondDoor = new GRect(175, 340, SIZE_OF_DOOR_WIDTH, SIZE_OF_DOOR_HEIGHT);
        SecondDoor.setFilled(true);
        SecondDoor.setFillColor(DOOR_COLOR);
        return SecondDoor;
    }

    /**
     * make third door and a color
     *
     * @return ready third door
     */
    private GRect makeThirdDoor() {
        GRect ThirdDoor = new GRect(250, 340, SIZE_OF_DOOR_WIDTH, SIZE_OF_DOOR_HEIGHT);
        ThirdDoor.setFilled(true);
        ThirdDoor.setFillColor(DOOR_COLOR);
        return ThirdDoor;
    }

    /**
     * make first wheel and a color
     *
     * @return ready first wheel
     */
    private GOval makeFirstWheel() {
        GOval FirstWheel = new GOval(40, 400, DIAMETER_OF_WHEEL, DIAMETER_OF_WHEEL);
        FirstWheel.setFilled(true);
        FirstWheel.setFillColor(WHEEL_COLOR);
        return FirstWheel;
    }

    /**
     * make second wheel and a color
     *
     * @return ready second wheel
     */
    private GOval makeSecondWheel() {
        GOval SecondWheel = new GOval(260, 400, DIAMETER_OF_WHEEL, DIAMETER_OF_WHEEL);
        SecondWheel.setFilled(true);
        SecondWheel.setFillColor(WHEEL_COLOR);
        return SecondWheel;
    }

    /**
     * make front light and a color
     *
     * @return ready front light
     */
    private GRect makeFrontLight() {
        GRect FrontLight = new GRect(300, 375, 40, 20);
        FrontLight.setFilled(true);
        FrontLight.setFillColor(FRONT_LIGHT_COLOR);
        FrontLight.setColor(FRONT_LIGHT_COLOR);
        return FrontLight;
    }

    /**
     * make rear light and a color
     *
     * @return ready rear light
     */
    private GRect makeRearLight() {
        GRect RearLight = new GRect(0, 375, 40, 20);
        RearLight.setFilled(true);
        RearLight.setFillColor(REAR_LIGHT_COLOR);
        RearLight.setColor(REAR_LIGHT_COLOR);
        return RearLight;
    }

    /**
     * make car body and a color
     *
     * @return ready car body
     */
    private GRect makeCarBody() {
        GRect CarBody = new GRect(0, 375, 340, 50);
        CarBody.setFilled(true);
        CarBody.setColor(CAR_COLOR);
        CarBody.setFillColor(CAR_COLOR);
        return CarBody;
    }

    /**
     * make car cab and a color
     *
     * @return ready car cab
     */
    private GRect makeCarCab() {
        GRect CarCab = new GRect(100, 340, 150, 35);
        CarCab.setFilled(true);
        CarCab.setColor(CAR_COLOR);
        CarCab.setFillColor(CAR_COLOR);
        return CarCab;
    }

    /**
     * make first window and a color
     *
     * @return ready first window
     */
    private GRect makeFirstWindow() {
        GRect FirstWindow = new GRect(110, 345, SIZE_OF_WINDOW_WIDTH, SIZE_OF_WINDOW_HEIGHT);
        FirstWindow.setFilled(true);
        FirstWindow.setFillColor(WINDOW_COLOR);
        return FirstWindow;
    }

    /**
     * make second window and a color
     *
     * @return ready second window
     */
    private GRect makeSecondWindow() {
        GRect SecondWindow = new GRect(180, 345, SIZE_OF_WINDOW_WIDTH, SIZE_OF_WINDOW_HEIGHT);
        SecondWindow.setFilled(true);
        SecondWindow.setFillColor(WINDOW_COLOR);
        return SecondWindow;
    }

    /**
     * This method make the world
     */
    private void makeGroundAndSky() {
        GRect ground = new GRect(0, 450, APPLICATION_WIDTH, 50);
        GRect sky = new GRect(0, 0, APPLICATION_WIDTH, APPLICATION_HEIGHT);

        sky.setFilled(true);
        sky.setFillColor(Color.cyan);
        sky.setColor(Color.cyan);
        ground.setFilled(true);
        ground.setFillColor(Color.GREEN);
        ground.setColor(Color.GREEN);
        add(sky);
        add(ground);
    }
}