package com.shpp.p2p.cs.ozalepa.assignment7;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    /* Declares a graphics buffer */
    ArrayList<NameSurferEntry> graphBuffer = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        if (graphBuffer != null) {
            /* Clears the graphics buffer */
            graphBuffer.clear();
            /* Updates graphics */
            update();
        }
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     *
     * @param entry the current entry to be saved.
     */
    public void addEntry(NameSurferEntry entry) {
        if (!(graphBuffer.contains(entry))) {
            /* Adds the corresponding graph to the buffer */
            graphBuffer.add(entry);
            /* Updates graphics */
            update();
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        /* Determines the size of the graph */
        int height = getHeight();
        int width = getWidth();
        /* Draws the grid and all graphs */
        drawGrid(width, height);
        drawGraphs(width, height);
    }

    /** Returns a color depending on the index of an entry in the buffer list.
     * @param index an integer that indicates the index of an entry in the buffer.
     * @return a color depending on the index of an entry in the buffer list.
     */
    private Color choiceColor(int index) {
        switch (index % 4) {
            case 0 -> {
                return Color.BLUE;
            }
            case 1 -> {
                return Color.RED;
            }
            case 2 -> {
                return Color.MAGENTA;
            }
            default -> {
                return Color.BLACK;
            }
        }
    }

    /** Draws information on the screen at the desired point on the graph.
     * @param signature the inscription to be displayed.
     * @param x The X coordinate for displaying the inscription.
     * @param y The Y coordinate for displaying the inscription.
     * @param index The index of the entry in the buffer list.
     */
    private void drawSignature(String signature, int x, int y, int index) {
        /* Reflects the popularity of the name in the corresponding decade */
        GLabel label = new GLabel(signature, x, y);
        label.setColor(choiceColor(index));
        add(label);
    }

    /** Returns the label depending on the popularity value of the name in a given decade.
     * @param entry The specific entry from which you need to take the popularity of the name.
     * @param index The index of the entry in the buffer list.
     * @return the label depending on the popularity value of the name in a given decade.
     */
    private String getSignature(NameSurferEntry entry, int index) {
        /* If the popularity is zero, display it with the symbol '*' */
        if (entry.getRank(index) == 0) return entry.getName() + "*";
        return entry.getName() + " " + entry.getRank(index);
    }

    /** Draws a segment of the graph. The parameters are responsible for its coordinates.
     * @param x1 The X coordinate of the beginning of the segment.
     * @param y1 The Y coordinate of the beginning of the segment.
     * @param x2 The X coordinate of the segment end.
     * @param y2 The Y coordinate of the segment end.
     * @param index The index of the entry in the buffer list.
     */
    private void drawSegment(int x1, int y1, int x2, int y2, int index) {
        /* Draws a segment of the graph */
        GLine line = new GLine(x1, y1, x2, y2);
        line.setColor(choiceColor(index));
        add(line);
    }

    /** Draws a grid for graphing.
     * @param width The width of the plane for drawing graphs.
     * @param height The height of the plane for drawing graphs.
     */
    private void drawGrid(int width, int height) {
        /* Declaration of initial coordinate X */
        int x = 0;
        /* Declaration the number of the current decade */
        int decadeNum = START_DECADE;
        /* A cycle for drawing a graph grid */
        for (int i = 0; i < NUM_DECADES; i++) {
            add(new GLine(x, 0, x, height));
            add(new GLabel(Integer.toString(decadeNum), x, height));

            x += width / NUM_DECADES;
            decadeNum += VALUE_DECADE;
        }
        /* Adds the lower and upper line of the graph */
        add(new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE));
        add(new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE));
    }

    /** Draws all graphs according to the saved entries in the buffer list.
     * @param width The width of the plane for drawing graphs.
     * @param height The height of the plane for drawing graphs.
     */
    private void drawGraphs(int width, int height) {
        int index = 0;
        int x1, x2, y1, y2;
        /* Cycles for drawing graphs from the buffer */
        for (NameSurferEntry nameSurferEntry : graphBuffer) {
            /* Checking if there are graphs */
            if (nameSurferEntry != null) {
                /* Declare the starting coordinates for the X-axis lines */
                x1 = 0;
                for (int j = 0; j < NUM_DECADES - 1; j++) {
                    /* Calculates the final coordinate of a graph segment */
                    x2 = x1 + width / NUM_DECADES;
                    /* Calculates the Y coordinates of the segment of the graph */
                    y1 = GRAPH_MARGIN_SIZE +
                            (nameSurferEntry.getRank(j) * (height - GRAPH_MARGIN_SIZE * 2) / MAX_RANK);
                    y2 = GRAPH_MARGIN_SIZE +
                            (nameSurferEntry.getRank(j + 1) * (height - GRAPH_MARGIN_SIZE * 2) / MAX_RANK);
                    /* Checks for missing name. */
                    if (y1 == GRAPH_MARGIN_SIZE) y1 = height - GRAPH_MARGIN_SIZE;
                    if (y2 == GRAPH_MARGIN_SIZE) y2 = height - GRAPH_MARGIN_SIZE;
                    /* Draws the corresponding segment of the graph */
                    drawSegment(x1, y1, x2, y2, index);
                    /* Draws information in the appropriate positions of the graph */
                    drawSignature(getSignature(nameSurferEntry, j), x1, y1, index);
                    drawSignature(getSignature(nameSurferEntry, j + 1), x2, y2, index);
                    /* Calculation of the X coordinate of the beginning of the segment of the graph */
                    x1 += width / NUM_DECADES;
                }
                index++;
            }
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}