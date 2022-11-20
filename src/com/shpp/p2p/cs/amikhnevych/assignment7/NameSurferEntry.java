package com.shpp.p2p.cs.amikhnevych.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.HashMap;

/**
 * The class work with the string of name
 */
public class NameSurferEntry implements NameSurferConstants {

    /**
     * HashMap containing the name and list of popularity
     */
    static HashMap<String, String> dateBase = new HashMap<>();

    /**
     * Name with we work in this object
     */
    static String name = "";

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        // Slip the string to 2 part (name and list of popularity)
        String[] data = line.split(" ", 2);
        dateBase.put(data[0], data[1]);
        name = data[0];
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        String line = dateBase.get(name);
        String[] decade_array = line.split(" ");
        return Integer.parseInt((decade_array[decade]));
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return name + "[" + dateBase.get(name) + "]";
    }
}

