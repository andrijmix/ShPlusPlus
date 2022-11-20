package com.shpp.p2p.cs.amikhnevych.assignment7;
//не виводити нове ім'я
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Main class
 */
public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /**
     * This field of name space
     */
    static JTextField jtextField;
    /**
     * Object of date base
     */
    public static NameSurferDataBase DataBase;

    /**
     * Object of graphic elements
     */
    private NameSurferGraph graph = new NameSurferGraph();

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interacts at the top of the window.
     */
    public void init() {


        try {
            DataBase = new NameSurferDataBase(NAMES_DATA_FILE); //create a new DataBase
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        graph = new NameSurferGraph(); //add the net
        add(graph);
        addLabelsAndButtons();
        addActionListeners();// listening Enter
    }

    /**
     * This method create the buttons
     */
    private void addLabelsAndButtons() {
        add(new JLabel("Name: "), NORTH);
        jtextField = new JTextField("  ", 30);
        add(jtextField, NORTH);
        add(new JButton("Graph"), NORTH);
        add(new JButton("Clear"), NORTH);
        jtextField.addActionListener(this);
        jtextField.setActionCommand("Graph");
    }


    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Graph")) { // if press Graph
            String name = NormalizeName(jtextField.getText());
            NameSurferEntry entry = DataBase.findEntry(name); //find name in dateBase
            if (entry == null) //if not have this name
                return;
            jtextField.setText("");
            graph.addEntry(entry); //add  lines
            graph.update();

        }
        if (e.getActionCommand().equals("Clear")) {
            jtextField.setText("");
            graph.clear();
        }
    }

    /**
     * This method is normalized the name (template Aaron)
     *
     * @param text - the text which need to check
     * @return - the normalized name
     */
    private String NormalizeName(String text) {
        String name = text;
        name = name.replaceAll("[^a-zA-Z]", "");  // deleted all wrong symbols
        if (name.isEmpty()) return "";
        name = name.toLowerCase();                    // set all character symbols in lower case
        name = name.substring(0, 1).toUpperCase() + name.substring(1); // set fist character to upper case
        return name;
    }
}
