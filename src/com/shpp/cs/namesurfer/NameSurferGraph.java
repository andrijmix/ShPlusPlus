package com.shpp.cs.namesurfer;

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

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    static NameSurferEntry g_entry;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        int widthColumn = APPLICATION_WIDTH / 12;
        int fontSize = 18;
        int sizeBackdown = 100;

        GLine h_line1 = new GLine(0, 0,
                APPLICATION_WIDTH, 0);
        add(h_line1);
        GLine h_line2 = new GLine(0, APPLICATION_HEIGHT - fontSize - sizeBackdown,
                APPLICATION_WIDTH, APPLICATION_HEIGHT - fontSize - sizeBackdown);
        add(h_line2);

        GLine h_line3 = new GLine(0, APPLICATION_HEIGHT - sizeBackdown,
                APPLICATION_WIDTH, APPLICATION_HEIGHT - sizeBackdown);
        add(h_line3);

        int years = 1900;
        for (int i = 0; i < 13; i++) {
            GLine line = new GLine(i * widthColumn, 0, i * widthColumn, APPLICATION_HEIGHT);
            add(line);
            GLabel label = new GLabel(String.valueOf(years + i * 10), i * widthColumn, APPLICATION_HEIGHT - sizeBackdown);
            label.setFont("Arial -" + String.valueOf(fontSize));
            add(label);
        }
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        // You fill this in //
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        g_entry = entry;
        update();
//        int widthColumn = APPLICATION_WIDTH / 12;
//        for (int i = 0; i < 12; i++) {
//            int x1 = i * widthColumn;
//            int y1 = entry.getRank(i) * APPLICATION_HEIGHT / 100;
//            int x2 = i + 1 * widthColumn;
//            int y2 = entry.getRank(i + 1) * APPLICATION_HEIGHT / 100;
//
//            GLine line = new GLine(x1, y1, x2, y2);
//
//        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        if (g_entry == null) return;

        int widthColumn = APPLICATION_WIDTH / 12;
        for (int i = 1; i < 12; i++) {
            int x1 = i * widthColumn;
            int y1 = g_entry.getRank(i) * APPLICATION_HEIGHT / 1000;
            int x2 = (i + 1) * widthColumn;
            int y2 = g_entry.getRank(i + 1) * APPLICATION_HEIGHT / 1000;
            System.out.println("update");
            GLine line = new GLine(x1, y1, x2, y2);
            add(line);
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
