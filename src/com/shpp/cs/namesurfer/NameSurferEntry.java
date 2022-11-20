package com.shpp.cs.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.HashMap;

public class NameSurferEntry implements NameSurferConstants {


    static HashMap<String, String> datebase = new HashMap<>();

    static String name = "";

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {


        String data[] = line.split(" ", 2);
        datebase.put(data[0], data[1]);
        name = data[0];
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {

        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        String line = datebase.get(name);
        String[] decarde = line.split(" ");
        return Integer.parseInt((decarde[decade - 1]));
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {

        // You need to turn this stub into a real implementation //
        return name + "[" + datebase.get(name) + "]";
    }
}

