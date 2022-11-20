package com.shpp.p2p.cs.dnepom.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /* the constants below define the buttons "Graph" and "Clear",
     user input field, and label on the north side of the window
     ____________________________________________________________*/
    private static final String LABEL_TEXT = "Name: ";
    private static final JLabel LABEL = new JLabel(LABEL_TEXT);
    private static final int TEXT_LABEL_COLUMNS = 12;
    private static final JTextField TEXT_FIELD = new JTextField(TEXT_LABEL_COLUMNS);
    private static final String CLEAR_BUTTON_NAME = "Clear";
    private static final JButton CLEAR_BUTTON = new JButton(CLEAR_BUTTON_NAME);
    private static final String GRAPH_BUTTON_NAME = "Graph";
    private static final JButton GRAPH_BUTTON = new JButton(GRAPH_BUTTON_NAME);
    /*__________________________________________________________________________*/

    //This field is needed to store an object with type NameSurferGraph for drawing a graph in the window
    private NameSurferGraph graph;

    //data file path
    private static final String FILE_PATH = "names-data.txt";

    //here stored database that contains names and values of their popularity
    private static final NameSurferDataBase NAME_SURFER_DATA_BASE = new NameSurferDataBase(FILE_PATH);

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        // adding UI to the  window  //
        add(LABEL, NORTH);
        add(TEXT_FIELD, NORTH);
        add(GRAPH_BUTTON, NORTH);
        add(CLEAR_BUTTON, NORTH);

        // adding interaction //
        GRAPH_BUTTON.setActionCommand(GRAPH_BUTTON_NAME);
        CLEAR_BUTTON.setActionCommand(CLEAR_BUTTON_NAME);
        addActionListeners();

        // adding graph //
        graph = new NameSurferGraph();
        add(graph);

        //addKeyListener(this);
    }


    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent event) {

        try {
            if (event.getActionCommand().equals(GRAPH_BUTTON_NAME)){
               graph.addEntry(NAME_SURFER_DATA_BASE.findEntry(TEXT_FIELD.getText()));
            }
        } catch (NameDoesNotExistException e) {
            System.err.println(e.getMessage());
        }

        if (event.getActionCommand().equals(CLEAR_BUTTON_NAME)) {
            graph.clear();
            TEXT_FIELD.setText("");
        }
    }

//    @Override
//    public void keyPressed(KeyEvent keyEvent) {
//        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
//            System.out.println("Graph! " + TEXT_FIELD.getText());
//        }
//    }
}
