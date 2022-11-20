package com.shpp.cs.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    static JTextField jtextField;
    NameSurferDataBase DataBase;

    private NameSurferGraph graph;

    public void init() {
        // You fill this in, along with any helper methods //
        add(new JLabel("Name: "), NORTH);
        jtextField = new JTextField("  ", 10);
        add(jtextField, NORTH);
        add(new JButton("Graph"), NORTH);
        add(new JButton("Clear"), NORTH);

        addActionListeners();

        try {
            DataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        graph = new NameSurferGraph();
        add(graph);
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Graph")) {
            String name = NormalizeName(jtextField.getText());
            println("Graph clicked:");
            NameSurferEntry entry = DataBase.findEntry(name);
            String names = entry.toString();
            NameSurferGraph graph = new NameSurferGraph();
            graph.addEntry(entry);
            System.out.print("Name: " + names);

        }
        if (action.equals("Clear")) {
            println("Clear clicked");
            //  clear();
        }

    }

    private String NormalizeName(String text) {
        String name = text;
        name = name.replace(" ", "");
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        return name;
    }
}
