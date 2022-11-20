package com.shpp.p2p.cs.ozalepa.assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {
    /* Declares a database list */
    private final ArrayList<NameSurferEntry> dataBase = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.
     *
     * @param filename file link.
     * @throws IOException if the requested file does not exist or if an error
     *                     occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) throws IOException {
        /* Declares an object of type file and checks if it exists */
        File file = new File(filename);
        /* Creating a buffered reader */
        BufferedReader br = new BufferedReader(new FileReader(file));
        /* Reads the file line by line and adds values in a list */
        String buffer;
        while ((buffer = br.readLine()) != null) {
            /* Create objects for each line of the document */
            NameSurferEntry entry = new NameSurferEntry(buffer);
            /* Adds them to the database */
            dataBase.add(entry);
        }
        br.close();
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     *
     * @return the NameSurferEntry associated with this name, if one
     * exists.
     */
    public NameSurferEntry findEntry(String name) {
        /* Returns a record that matches the searched name */
        return dataBase.stream()
                .filter(buffer -> buffer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

