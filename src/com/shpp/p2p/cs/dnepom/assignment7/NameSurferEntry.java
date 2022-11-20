package com.shpp.p2p.cs.dnepom.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    //exception message
    private static final String ERR_MESSAGE = "data contains not valid entry";

    private String name;
    private int[] ranks;

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {

        //initialization ranks
        ranks = new int[NDECADES];

        //split line by spaces
        String[] strings = line.split(" ");

        //checking for the required line length and for the fact that the first
        // element is similar to the name (contains only letters)
        if (strings.length != NDECADES + 1 || !strings[0].matches("[a-zA-Z]+")) {
            throw new NotValidEntryException(ERR_MESSAGE);
        }

        //initialization name by first element from strings
        name = strings[0];

        //In this block we parse ints from the second element of strings to last
        //and check that int values are in range 0..1000.
        //If an error occurs, the object is not created.
        try {
            int val;
            for (int i = 1; i < NDECADES + 1; i++) {
                val = Integer.parseInt(strings[i]);
                if (val < 0 || val > MAX_RANK) {
                    throw new NotValidEntryException(ERR_MESSAGE);
                }
                ranks[i - 1] = val;
            }
        } catch (NumberFormatException e) {
            throw new NotValidEntryException(ERR_MESSAGE);
        }
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
        return ranks[decade];
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return name + Arrays.toString(ranks);
    }
}

