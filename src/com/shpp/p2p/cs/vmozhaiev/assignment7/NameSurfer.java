package com.shpp.p2p.cs.vmozhaiev.assignment7;
//маштаб цифр
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    private NameSurferDataBase dataBase;
    private NameSurferGraph graph;
    private JTextField nameField;

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interactors at the top of the window.
     */

    public void init() {
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        add(graph);
        addLabelsAndButtons();
        addActionListeners();
    }

    /**
     * This method adds the interactors
     */
    private void addLabelsAndButtons() {
        add(new JLabel("Name: "), NORTH);
        nameField = new JTextField(COLUMN);
        add(nameField, NORTH);
        JButton graphButton = new JButton("Graph");
        add(graphButton, NORTH);
        JButton clearButton = new JButton("Clear");
        add(clearButton, NORTH);
        nameField.addActionListener(this);
        nameField.setActionCommand("Graph");
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Graph")) {
            String name = nameField.getText();
            NameSurferEntry entry = dataBase.findEntry(name);
            if (entry != null) {
                graph.addEntry(entry);
                graph.update();
            } else {
                getDialog().println("Name not found in database");
            }
        } else if (e.getActionCommand().equals("Clear"))
            graph.clear();
    }
}
