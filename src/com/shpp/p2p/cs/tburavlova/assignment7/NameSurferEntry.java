package com.shpp.p2p.cs.tburavlova.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    String[] fieldsInLine;          // record from row of file
    private String name;

    // A list of 12 values that show the position of the name
    // in the ranking in the corresponding decade
    private int[] ranks = new int[NDECADES];

    /**
     * The constructor separates the data by space and
     * converts strings to integers
     *
     * @param line Record from row of file
     */
    public NameSurferEntry(String line) {
        fieldsInLine = line.split(" ");
        name = fieldsInLine[0];
        for (int i = 0; i < NDECADES; i++) {
            ranks[i] = Integer.parseInt(fieldsInLine[i + 1]);
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
        String str = fieldsInLine[0] + " [";
        for (int i = 1; i < NDECADES; i++)
            str = str + fieldsInLine[i] + " ";
        return str + fieldsInLine[NDECADES] + "]";
    }
}

