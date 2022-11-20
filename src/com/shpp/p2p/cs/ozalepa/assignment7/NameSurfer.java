package com.shpp.p2p.cs.ozalepa.assignment7;

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    /* Declaration of program interactors */
    private JTextField textField;
    private JButton graphButton;
    private JButton clearButton;

    /* Declaration of the database object */
    private NameSurferDataBase dataBase;

    /* Declaration of the graph object */
    private NameSurferGraph graph;

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        /* Creates and adds the interactors at the top of the window */
        add(new JLabel("Name:"), BorderLayout.NORTH);

        textField = new JTextField(NUM_COLUMNS);
        textField.setActionCommand("Enter");
        textField.addActionListener(this);
        add(textField, BorderLayout.NORTH);

        graphButton = new JButton("Graph");
        add(graphButton, BorderLayout.NORTH);

        clearButton = new JButton("Clear");
        add(clearButton, BorderLayout.NORTH);
        /* Implements the 'actionPerformed' method */
        addActionListeners();
        /* Creates a database object */
        try {
            dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Creates and adds a graph to the screen */
        graph = new NameSurferGraph();
        add(graph);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Enter") || e.getSource() == graphButton) {
            /* Returns text and removes spaces from it */
            String name = textField.getText().replaceAll("\\s+", "");
            textField.setText(name);
            /* Checks if the field is empty */
            if (!name.equals("")) {
                NameSurferEntry bufferEntry = dataBase.findEntry(name);
                /* Checks to see if a matching record is found for the specified name */
                if (bufferEntry != null) {
                    /* Calls the method of adding an object */
                    graph.addEntry(bufferEntry);
                    /* Clears the field */
                    textField.setText("");
                } else {
                    /* Calls up a message informing about the lack of information on the given name */
                    JOptionPane.showMessageDialog(null,
                            "The name " + name + " was not found in the database!",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            if (e.getSource() == clearButton) {
                /* Clears the screen from graphics */
                graph.clear();
            }
        }
    }
}