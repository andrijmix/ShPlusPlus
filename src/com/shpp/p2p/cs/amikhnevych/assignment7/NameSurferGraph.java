package com.shpp.p2p.cs.amikhnevych.assignment7;

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

import static com.shpp.p2p.cs.amikhnevych.assignment7.NameSurfer.DataBase;

/**
 * Clas for graph drawing
 */
public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * List of name with need drawing
     */
    ArrayList<String> gName = new ArrayList<>();

    /**
     * 3 horizontal line
     */
    GLine hLine1, hLine2, hLine3;


    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        update();
    }

    /**
     * Created the grid of window (horizontally and vertical line)
     */
    public void createNet() {


        int fontSize = getWidth() / 43; // size for years label
        drawHorizontalLine(fontSize);

        drawVerticalLine(fontSize);
    }

    /**
     * Draw the vertical line
     *
     * @param fontSize the size of the font
     */
    private void drawVerticalLine(int fontSize) {
        int widthColumn = getWidth() / NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            GLine line = new GLine(i * widthColumn, 0, i * widthColumn, getHeight());
            add(line);
            GLabel label = new GLabel(String.valueOf(START_DECADE + i * 10), i * widthColumn, getHeight() - GRAPH_MARGIN_SIZE);
            label.setFont("Arial -" + fontSize);
            add(label);
        }
    }

    /**
     * Draw the horizontal line
     *
     * @param fontSize the size of the font
     */
    private void drawHorizontalLine(int fontSize) {

        hLine1 = new GLine(0, fontSize,
                getWidth(), fontSize);
        add(hLine1);
        hLine2 = new GLine(0, getHeight() - fontSize - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - fontSize - GRAPH_MARGIN_SIZE);
        add(hLine2);

        hLine3 = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(hLine3);
        GLine v_line = new GLine(2, 2, 2, getHeight());
        add(v_line);
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        gName.clear();
        removeAll();
        createNet();
    }


    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (!gName.contains(entry.getName()))
            gName.add(entry.getName());
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
        removeAll();
        createNet();   // set net
        createChart(); // //set chart of name

    }

    /**
     * Create a chart of name
     */
    private void createChart() {
        if (gName == null)
            return;
        //in name list
        for (int j = 0; j < gName.size(); j++) {
            NameSurferEntry g_entry = DataBase.findEntry(gName.get(j));
            drawChart(j, g_entry);
        }
    }

    /**
     * This method draw the chart
     *
     * @param j       the index of the chart of name
     * @param g_entry object of name
     */
    private void drawChart(int j, NameSurferEntry g_entry) {
        //size of the space between decade
        int widthColumn = getWidth() / NDECADES;
        //in decade list
        for (int i = 0; i < NDECADES; i++) {
            int x1 = i * widthColumn;
            int y1 = (int) (g_entry.getRank(i) * (hLine2.getY() - hLine1.getY()) / MAX_RANK);

            if (g_entry.getRank(i) == 0)
                y1 = (int) (hLine2.getY() - hLine1.getY());

            int x2 = x1;

            int y2 = y1;
            if (i + 1 != NDECADES) { //if not last column
                x2 = (i + 1) * widthColumn;
                y2 = (int) (g_entry.getRank(i + 1) * (hLine2.getY() - hLine1.getY()) / MAX_RANK);
                if (g_entry.getRank(i + 1) == 0) {
                    y2 = (int) ((hLine2.getY() - hLine1.getY()));
                }
            }
            y1 = (int) (y1 + hLine1.getY());
            y2 = (int) (y2 + hLine1.getY());


            drawObject(j, g_entry, i, x1, y1, x2, y2);
        }
    }

    /**
     * This method draw the chart
     *
     * @param j       the decade
     * @param g_entry object of decade
     * @param i       the number of decade
     * @param x1      the x1 coordinate
     * @param y1      the y1 coordinate
     * @param x2      the x2 coordinate
     * @param y2      the y2 coordinate
     */
    private void drawObject(int j, NameSurferEntry g_entry, int i, int x1, int y1, int x2, int y2) {
        Color[] colorLine = {Color.BLUE, Color.RED, Color.MAGENTA, Color.black};
        Color color = colorLine[j % colorLine.length];
        //create a line
        GLine line = new GLine(x1, y1, x2, y2);
        // create a label
        GLabel label = new GLabel(gName.get(j) + " " + g_entry.getRank(i), x1, y1);
        //if the decade is zero
        if (g_entry.getRank(i) == 0)
            label = new GLabel(gName.get(j) + " *", x1, y1);
        line.setColor(color);
        label.setColor(color);
        add(line);
        add(label);
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
