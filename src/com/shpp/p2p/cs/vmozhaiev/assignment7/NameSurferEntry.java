package com.shpp.p2p.cs.vmozhaiev.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.Arrays;
import java.util.HashMap;

public class NameSurferEntry implements NameSurferConstants {
    private String name;
    private int[] ranks;
    public HashMap<String, int[]> nameRank = new HashMap<>();
	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        // Splits the input string
        String[] str = line.split("\\s");
        name = "";
        ranks = new int[str.length - 1];
        // Separates the String (name) type and Integer (ranks) type
        for (int i = 0; i < str.length; i++) {
            String element = str[i];
            if (!isNumeric(element)) {
                name = element;
            } else if (isNumeric(element)) {
                ranks[i-1] = Integer.parseInt(element);
            }
        }
        nameRank.put(name, ranks);
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
        return ranks[decade-1];
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String res = "";
        String decades = Arrays.toString(nameRank.get(name));
        res = getName() + " " + decades.replace(",", "");
        return res;
    }

    /**
     * Checks the string for numeric format
     *
     * @param str Input string
     * @return false or true
     */
    public static boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

