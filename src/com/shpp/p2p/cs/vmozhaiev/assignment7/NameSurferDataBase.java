package com.shpp.p2p.cs.vmozhaiev.assignment7;

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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {
    private HashMap<String, NameSurferEntry> entryNameRank;
    //private NameSurferEntry entry;
    private BufferedReader br;

	/* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */

    public NameSurferDataBase(String filename) {
        entryNameRank = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file: " + e);
        }
        try {
            while (true) {
                String readLine = br.readLine();
                if (readLine == null) break;
                NameSurferEntry entry = new NameSurferEntry(readLine);
                entryNameRank.put(entry.getName(), entry);
            }
            br.close();
        } catch (NullPointerException | IOException e) {
            System.out.println("Can't read file: " + e);
        }
    }
	
	/* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        // Convert the string to the correct format
        if (name.length() != 0) {
            StringBuilder sb = new StringBuilder(name.toLowerCase());
            char capitalLetter = Character.toUpperCase(sb.charAt(0));
            sb.setCharAt(0,capitalLetter);
            name = sb.toString();
        }
        // Finds name in database
        if (entryNameRank.containsKey(name)) {
            return entryNameRank.get(name);
        }
        return null;
    }
}

