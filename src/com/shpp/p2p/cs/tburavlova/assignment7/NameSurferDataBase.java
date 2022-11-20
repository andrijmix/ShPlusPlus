package com.shpp.p2p.cs.tburavlova.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NameSurferDataBase implements NameSurferConstants {
    Map<String, String> mapNameSurfer;

    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {

        mapNameSurfer = HashMapFromTextFile(filename);
    }

    /**
     * Reads the entire data set from a file into an internal data structure
     *
     * @param filename File name. Used constant (NAMES_DATA_FILE)
     * @return HashMap mapFromTextFile   data file content
     */
    public static Map<String, String> HashMapFromTextFile(String filename) {
        Map<String, String> mapFromTextFile = new HashMap<String, String>();
        filename = NAMES_DATA_FILE;

        BufferedReader br = null;
        try {
            /* create BufferedReader object from the file */
            br = new BufferedReader(new FileReader(filename));

            String line = null;
            /* read file line by line */
            while ((line = br.readLine()) != null) {
                /* split the line by "space" */
                String[] parts = line.split(" ");

                /* first part is name, next is rank[i] */
                String name = parts[0].trim().toLowerCase();
                String rank = "";
                for (int i = 1; i <= NDECADES; i++) {
                    rank = rank + " " + parts[i];
                }
                /* put name, rank's in HashMap */
                mapFromTextFile.put(name, rank);
            }
            br.close();
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println("Error open file");
        }
        return mapFromTextFile;
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        if (mapNameSurfer.containsKey(name)) {
            for (Map.Entry<String, String> p : mapNameSurfer.entrySet()) {
                if (p.getKey().equals(name))
                    return new NameSurferEntry(p.getKey() + p.getValue());
            }
        }
        return null;
    }
}

