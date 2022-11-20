package com.shpp.p2p.cs.vmozhaiev.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    private ArrayList<NameSurferEntry> names = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */

    public NameSurferGraph() {
        addComponentListener(this);
        update();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        names.removeAll(names);
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        for (NameSurferEntry name : names) {
            if (entry.equals(name)) return;
        }
        names.add(entry);
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
        drawGridAndLabels();
        drawGraphNameRanks();
    }

    /**
     * This method adds GObject to the canvas
     * add draws grid and label
     */
    private void drawGridAndLabels() {
        // Creates the horizontal lines
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - GRAPH_MARGIN_SIZE));

        // Calculates the grid step for X coordinate
        double gridStep = (double) getWidth() / NDECADES;

        // Creates the vertical lines
        for (double i = 0; i < getWidth(); i += gridStep) {
            add(new GLine(i, 0, i, getHeight()));
        }

        int j = START_DECADE;
        // Creates the GLabel from decade value
        for (double i = 0; i < getWidth(); i += gridStep) {
            GLabel nDecades = new GLabel("" + j);
            nDecades.setFont("Century Schoolbook-20");
            add(nDecades, i, getHeight() - DECADE_LABEL_MARGIN_SIZE);
            j += DECADE;
        }
    }

    /**
     * This method bypasses the ArrayList of names
     * and adds the GObject to the canvas from the
     * given data
     */
    private void drawGraphNameRanks() {
        for (int i = 0; i < names.size(); i++) {
            add(createGraph(names.get(i), i));
            add(createNameAndRanks(names.get(i), i));
        }
    }

    /**
     * This method creates a GCompound from GLabels
     * for the give NameSurferEntry.
     *
     * @param entry       The instance of NameSurferEntry class
     * @param numberGraph Number for the graph label on the graph
     * @return GCompound from GLabel
     */
    private GCompound createNameAndRanks(NameSurferEntry entry, int numberGraph) {
        // Creates new instance of GCompound class
        GCompound labels = new GCompound();
        // Calculates the grid step for X coordinate
        double gridStep = (double) getWidth() / NDECADES;
        // Starting X coordinate
        double xCoordinate = 0;
        // Creates label for each decade
        for (int i = 1; i <= NDECADES; i++) {
            // Calculate the multiplier for Y coordinate
            double multiplierY = (getHeight() - (2.0 * GRAPH_MARGIN_SIZE)) / MAX_RANK;
            // Calculate start Y coordinates for label for each decade
            double yCoordinate = entry.getRank(i) * multiplierY + GRAPH_MARGIN_SIZE;
            String name = entry.getName();
            //Condition if the rank is zero
            if (entry.getRank(i) == 0) {
                yCoordinate = (getHeight() - GRAPH_MARGIN_SIZE);
                name += "*";
            } else {
                name += " " + entry.getRank(i);
            }
            // Creates GLabel from name and ranks
            GLabel nameRanks = new GLabel(name, xCoordinate, yCoordinate);
            nameRanks.setFont("Calibri-Bold-12");
            nameRanks.setColor(getColor(numberGraph));
            labels.add(nameRanks);
            // Increments X coordinate for GLabel
            xCoordinate += gridStep;
        }
        return labels;
    }

    /**
     * This method creates a GCompound from GLines
     * for the given NameSurferEntry.
     *
     * @param entry       The instance of NameSurferEntry class
     * @param numberGraph Number for the graph line on the graph
     * @return GCompound from GLines
     */
    private GCompound createGraph(NameSurferEntry entry, int numberGraph) {
        // Creates new instance of GCompound class
        GCompound graph = new GCompound();
        // Calculates the step for X coordinate
        double gridStep = (double) getWidth() / NDECADES;
        // Sets starting X coordinate
        double xCoordinate = 0;
        // Creates line for each decade
        for (int i = 1; i < NDECADES; i++) {
            // Calculate the multiplier for Y coordinate
            double multiplierY = (getHeight() - (2.0 * GRAPH_MARGIN_SIZE)) / MAX_RANK;
            // Calculate start and end Y coordinates for line for each decade
            double yCoordinate0 = (entry.getRank(i) * multiplierY) + GRAPH_MARGIN_SIZE;
            double yCoordinate1 = (entry.getRank(i + 1) * multiplierY) + GRAPH_MARGIN_SIZE;
            // Condition if the rank is zero for Y0 and Y1 coordinates
            if (entry.getRank(i) == 0) {
                yCoordinate0 = (getHeight() - GRAPH_MARGIN_SIZE);
            }
            if (entry.getRank(i + 1) == 0) {
                yCoordinate1 = getHeight() - GRAPH_MARGIN_SIZE;
            }
            // Creates GLine from the computed coordinates
            GLine line = new GLine(xCoordinate, yCoordinate0, xCoordinate + gridStep, yCoordinate1);
            line.setColor(getColor(numberGraph));
            graph.add(line);
            // Increments X coordinate for GLine
            xCoordinate += gridStep;
        }
        return graph;
    }

    /**
     * This method sets the color of the data on the graph
     *
     * @param numberGraph Line number on the graph
     * @return Returns a color based on the number of graph line
     */
    private Color getColor(int numberGraph) {


        int colorSet = numberGraph % 4;
        return switch (colorSet) {
            case 0 -> Color.BLUE;
            case 1 -> Color.RED;
            case 2 -> Color.MAGENTA;
            default -> Color.BLACK;
        };
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


