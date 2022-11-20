package com.shpp.p2p.cs.ozalepa.assignment7;

import java.util.Arrays;

public class NameSurferEntry implements NameSurferConstants {
    /* The field stores the name */
    private final String Name;

    /* Array of the positions of the name in the rating in the corresponding decade */
    private final int[] positionsArray = new int[NUM_DECADES];

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     *
     * @param line a data line in the data file.
     */
    public NameSurferEntry(String line) {
        /* Splits the tape into an array of spaces */
        String[] bufferElements = line.split(" ");
        /* Stores the name in the field */
        Name = bufferElements[0];
        /* Stores the position of the name in the list */
        for (int i = 1; i < bufferElements.length; i++) {
            positionsArray[i - 1] = Integer.parseInt(bufferElements[i]);
        }
    }

    /**
     * Returns the name associated with this entry.
     *
     * @return the name that corresponds to this entry.
     */
    public String getName() {
        return Name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.
     *
     * @param decade is an integer indicating how many decades have passed
     *               since the first year in the database.
     * @return The rank associated with an entry for a particular decade.
     */
    public int getRank(int decade) {
        return positionsArray[decade];
    }

    /**
     * Returns a string that makes it easy to see the value of a NameSurferEntry.
     * @return a string of values of a NameSurferEntry.
     */
    public String toString() {
        return Name + " " + Arrays.toString(positionsArray).replaceAll(",","");
    }
}

