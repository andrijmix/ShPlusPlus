package com.shpp.p2p.cs.tburavlova.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    /* list of name entries */
    private ArrayList<NameSurferEntry> graphData;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        graphData = new ArrayList<NameSurferEntry>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        graphData.clear();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     */
    public void addEntry(NameSurferEntry entry) {
        graphData.add(entry);
    }

    /**
     * Updates the display image by removing all graphics from the canvas and
     * then reassembling the display according to the list of entries.
     * update is called after clear or addEntry is called;
     * update is also called whenever the canvas is resized.
     */
    public void update() {
        removeAll();
        displayBackgroundGrid();
        if (graphData.size() > 0) {
            for (int i = 0; i < graphData.size(); i++) {
                NameSurferEntry entries = graphData.get(i);
                displayEntry(entries, i);
            }
        }
    }

    /**
     * display the graph line with the name and rank
     *
     * @param entry       One entry from the entry list to display.
     * @param entryNumber list entry number
     */
    private void displayEntry(NameSurferEntry entry, int entryNumber) {
        /* The coordinates of the beginning and end of the chart lines */
        double x1 = 0.0;
        double x2 = (double) (getWidth() / NDECADES);
        double y1 = 0;
        double y2 = 0;

        /* Color options for displaying graph lines */
        Color[] color = {new Color(0, 0, 255), new Color(255, 0, 0),
                new Color(255, 0, 255), new Color(0, 0, 0)};
        /* Loop output all lines */
        for (int i = 0; i < NDECADES - 1; i++) {
            int rank01 = entry.getRank(i);
            int rank02 = entry.getRank(i + 1);

            if (rank01 != 0 && rank02 != 0) {
                y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2.0) * rank01 / MAX_RANK;
                y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2.0) * rank02 / MAX_RANK;
            }
            if (rank01 == 0 && rank02 == 0) {
                y1 = getHeight() - GRAPH_MARGIN_SIZE;
                y2 = getHeight() - GRAPH_MARGIN_SIZE;
            }
            if (rank01 == 0 && rank02 != 0) {
                y1 = getHeight() - GRAPH_MARGIN_SIZE;
                y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2.0) * rank02 / MAX_RANK;
            }
            if (rank02 == 0 && rank01 != 0) {
                y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2.0) * rank01 / MAX_RANK;
                y2 = getHeight() - GRAPH_MARGIN_SIZE;
            }
            GLine line = new GLine(x1, y1, x2, y2);
            line.setColor(color[entryNumber % 4]);
            add(line);
            /* Calculate the start and end x coordinates of the next line */
            x1 = x1 + (double) (getWidth() / NDECADES);
            x2 = x2 + (double) (getWidth() / NDECADES);
        }
        displayNameAndRank(entry, entryNumber, color[entryNumber % 4]);
    }

    /**
     * Name and rating displays
     *
     * @param entry       One entry from the list
     * @param entryNumber number of entry
     * @param color       color for label display
     */
    private void displayNameAndRank(NameSurferEntry entry, int entryNumber, Color color) {
        double x = 2.0;
        double y;
        for (int i = 0; i < NDECADES; i++) {
            String name = entry.getName();

            /* First letter of name in UpperCase */
            name = (name.substring(0, 1)).toUpperCase() + name.substring(1);
            int rank = entry.getRank(i);
            String rankString = Integer.toString(rank);
            String label = name + " " + rankString;

            if (rank != 0) {
                y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2.0) * rank / MAX_RANK - 2.0;
            } else {
                label = name + " *";                    // if rank == 0
                y = getHeight() - GRAPH_MARGIN_SIZE - 2.0;
            }
            GLabel nameLabel = new GLabel(label, x, y);
            nameLabel.setColor(color);
            nameLabel.setFont("Helvetica-12");
            add(nameLabel);
            x += (double) (getWidth() / NDECADES);
        }
    }

    /**
     * Creating a background grid:
     * Show vertical grid lines.
     * Two horizontal lines.
     * Decadal inscriptions.
     */
    private void displayBackgroundGrid() {
        /* Display vertical lines */
        double x = 0.0;
        double dx = (double) (getWidth() / NDECADES);
        for (int i = 0; i < NDECADES; i++) {
            GLine line = new GLine(x, 0, x, getHeight());
            add(line);
            x += dx;
        }
        /* Display two horizontal lines (Top/Bottom) */
        x = getWidth();
        double yTopLine = getHeight() - GRAPH_MARGIN_SIZE;
        GLine topLine = new GLine(0, yTopLine, x, yTopLine);
        add(topLine);
        double yBottomLine = GRAPH_MARGIN_SIZE;
        GLine bottomLine = new GLine(0, yBottomLine, x, yBottomLine);
        add(bottomLine);

        /* Display decades */
        int decade = START_DECADE;
        x = 2.0;
        double y = getHeight() - (GRAPH_MARGIN_SIZE / 4.0);
        for (int i = 0; i < NDECADES; i++) {
            String label = Integer.toString(decade);
            GLabel labelDecade = new GLabel(label, x, y);
            labelDecade.setFont("Helvetica-14");
            add(labelDecade);
            decade += 10;
            x += dx;
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    /**
     * called whenever the canvas is resized.
     *
     * @param e the event to be processed
     */
    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
