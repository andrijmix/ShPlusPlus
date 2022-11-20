package com.shpp.p2p.cs.dnepom.assignment7;
//5
/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

import java.awt.*;

public interface NameSurferConstants {

    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 1000;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * The name of the file containing the data
     */
    public static final String NAMES_DATA_FILE = "names-data.txt";

    /**
     * The first decade in the database
     */
    public static final int START_DECADE = 1900;

    public static final int DECADE = 10;

    /**
     * The number of decades
     */
    public static final int NDECADES = 12;
    public static final int NLINES = NDECADES - 2;

    /**
     * The maximum rank in the database
     */
    public static final int MAX_RANK = 1000;

    /**
     * The number of pixels to reserve at the top and bottom
     */
    public static final int GRAPH_Y_MARGIN_SIZE = 20;

    public static final int GRAPH_X_MARGIN_SIZE = 20;

    public static final Color[] COLORS = new Color[]{Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

}
