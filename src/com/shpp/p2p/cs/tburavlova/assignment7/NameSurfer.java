package com.shpp.p2p.cs.tburavlova.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 *The NameSurfer program reflects the popularity of baby names
 * in the United States over the past century.
 */

import acm.program.*;
import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

/**
 * NameSurfer is the main class of the application, binding all others.
 * It is responsible for creating other objects, buttons,
 * but only in the part of forwarding to the corresponding classes that
 * already describe the object;
 */
public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    /* Width of the JTextField */
    private static final int NUM_COLUMNS = 40;

    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferGraph graph;

    private NameSurferDataBase dataBase;

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(new JLabel("Name: "), NORTH);
        nameField = new JTextField(NUM_COLUMNS);
        add(nameField, NORTH);

        graphButton = new JButton("Graph");
        add(graphButton, NORTH);

        clearButton = new JButton("Clear");
        add(clearButton, NORTH);

        graph = new NameSurferGraph();
        add(graph);                         // Display background grid

        addActionListeners();

        nameField.setActionCommand("EnterPressed");
        nameField.addActionListener(this);
        addActionListeners();

        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
    }

    /**
     * The method is called on all ActionEvent's.
     * This is the entry point.
     * It defines which command is behind the event.
     * Allows you to track clicks on objects and read the contents of the text field.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("EnterPressed")|| (e.getSource() == graphButton)) {    // Text Field Name or Button "Graph"
            if (dataBase.findEntry(nameField.getText().trim().toLowerCase()) != null) {
                /* Get record from row of file  */
                NameSurferEntry nameSurfer = dataBase.findEntry(nameField.getText().trim().toLowerCase());
                graph.addEntry(nameSurfer);
                graph.update();
            }

        } else if (e.getSource() == clearButton) {           //    Button "Clear"
            graph.clear();
            graph.update();
        }
    }

}

