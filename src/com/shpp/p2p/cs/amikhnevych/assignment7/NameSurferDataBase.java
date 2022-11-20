package com.shpp.p2p.cs.amikhnevych.assignment7;

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
import java.util.ArrayList;
import java.util.Objects;

public class NameSurferDataBase implements NameSurferConstants {

    /**
     * The List of names
     */
    ArrayList<String> name_and_years = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String nameLine = reader.readLine();        // fill the base with name line
            if (nameLine == null) {
                break;
            }
            NameSurferEntry entry = new NameSurferEntry(nameLine);
            name_and_years.add(nameLine);
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {

        for (String text : name_and_years) {
            String[] text_array = text.split(" ");  // split by space
            if (Objects.equals(text_array[0], name)) { // if first index of array is out name
                return new NameSurferEntry(text);
            }
        }
        return null;
    }
}

