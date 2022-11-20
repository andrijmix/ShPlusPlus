package com.shpp.cs.namesurfer;

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
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    /**
     * The HashMap of names
     */
    HashMap<String, String> datebase = new HashMap<>();
    ArrayList<String> name_and_years = new ArrayList<>();
    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String nameLine = reader.readLine();
            if (nameLine == null) {
                break;
            }
            String data[] = nameLine.split(" ");
            name_and_years.add(nameLine);
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {

        for (int i = 0; i < name_and_years.size(); i++) {
            String text = name_and_years.get(i);
            if (text.split(name, -1).length - 1 > 0) {
                return new NameSurferEntry(name_and_years.get(i));
            }
        }
        return null;
    }
}

