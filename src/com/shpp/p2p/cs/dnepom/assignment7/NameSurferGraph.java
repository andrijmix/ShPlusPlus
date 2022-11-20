package com.shpp.p2p.cs.dnepom.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    //a frame inside the window in which the graph is plotted
    private GRect frame;

    //here stored graphs that shown on screen
    private ArrayList<NameSurferEntry> graphs = new ArrayList<>();

    //years labels style
    private static final Font yearsFont = new Font("Dialog", 2, 12);

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
        graphs.clear();
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (graphs.contains(entry)) {
            return;
        }
        graphs.add(entry);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {

        //first clear window from any objects
        removeAll();

        //Create all necessary elements
        addFrame();
        addLines();
        addLabels();
        addGraphs();
    }

    /**
     * Adds graph lines to the screen as well as captions to them
     */
    private void addGraphs() {

        int colorCounter = 0;
        Color color;

        //draw a graph for each entry
        for (NameSurferEntry entry:
             graphs) {
            color = COLORS[colorCounter % COLORS.length];
            colorCounter++;

            double x1 = frame.getX();
            double distBetweenLines = frame.getWidth() / (NDECADES - 1);
            double x2;
            double y1 = getGraphY(0, entry);
            double y2;
            GLabel data = new GLabel("" + entry.getRank(0) + " " + entry.getName(), x1, y1);
            data.setColor(color);
            add(data);

            //Draws a line for each segment of the graph and label it
            for (int i = 0; i < NLINES + 1; i++) {
                x2 = x1 + distBetweenLines;
                y2 = getGraphY(i + 1, entry);
                GLine segment = new GLine(x1, y1, x2, y2);
                segment.setColor(color);
                data = new GLabel("" + entry.getRank(i + 1) + " " + entry.getName(), x2, y2);
                data.setColor(color);
                add(data);
                add(segment);
                y1 = y2;
                x1 = x2;
            }
        }
    }

    /**
     * This function return relative y coordinate of graph point.
     * If rank == 0 than y takes the value of the bottom side of the frame.
     */
    private double getGraphY(int decade, NameSurferEntry entry) {
        double rank = entry.getRank(decade);
        double k = rank == 0 ? 1 : rank / MAX_RANK;
        return frame.getY() + frame.getHeight() * k;
    }

    /**
     * Signs the lines with their respective decades
     */
    private void addLabels() {
        double x = frame.getX();
        double y  = frame.getY() + frame.getHeight() ;
        double distBetweenLines = frame.getWidth() / (NDECADES - 1);
        int currentDecade = START_DECADE;
        GLabel year;
        for (int i = 0; i < NDECADES; i++) {
            year = new GLabel("" + currentDecade);
            year.setFont(yearsFont);
            add(year, x, y + year.getHeight());
            currentDecade += DECADE;
            x += distBetweenLines;
        }
    }

    /**
     * Creates vertical lines in the frame that represent decades
     */
    private void addLines() {
        double y1 = frame.getY();
        double y2 = frame.getY() + frame.getHeight();
        double x = frame.getX();
        double distBetweenLines = frame.getWidth() / (NDECADES - 1);
        for (int i = 0; i < NLINES; i++) {
            x = x + distBetweenLines;
            add(new GLine(x, y1, x, y2));
        }
    }


    /**
     * Create GRect frame inside the window in which the graph is plotted.
     * Needed for the best look.
     */
    private void addFrame() {
        frame = new GRect(getWidth() - GRAPH_X_MARGIN_SIZE * 2, getHeight() - 2 * GRAPH_Y_MARGIN_SIZE);
        add(frame, GRAPH_X_MARGIN_SIZE, GRAPH_Y_MARGIN_SIZE);
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
