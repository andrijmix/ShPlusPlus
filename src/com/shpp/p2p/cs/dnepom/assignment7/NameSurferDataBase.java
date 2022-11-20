package com.shpp.p2p.cs.dnepom.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    //it is assumed that the number of names is less than 10000
    private static final int MAP_INITIAL_CAPACITY = 10000;
    //all data is stored here, key - name, value - NameSurferEntry object
    private HashMap<String, NameSurferEntry> data;

    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {

        //initialize data with initial value so that the map does not expand
        data = new HashMap<>(MAP_INITIAL_CAPACITY);

        String line;
        int lineCounter = 0;
        NameSurferEntry entry;
        String name;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }

                lineCounter++;

                //if entry is not correct it does not add to map and signals this
                // to the console and program continues to work
                try {
                    entry = new NameSurferEntry(line);
                    name = entry.getName().toLowerCase();
                    data.put(name, entry);
                } catch (NotValidEntryException e) {
                    System.err.println(e.getMessage() + " at line " + lineCounter);
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
            System.exit(1);
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {

        name = name.toLowerCase();

        //trows exception if data doesn't contain name
        if (data.containsKey(name)){
            return data.get(name);
        }
        throw new NameDoesNotExistException("Not valid name");
    }
}

