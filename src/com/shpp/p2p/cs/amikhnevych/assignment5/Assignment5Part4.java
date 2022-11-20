package com.shpp.p2p.cs.amikhnevych.assignment5;


import com.shpp.cs.a.console.TextProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Return the colum in CSV file
 */
public class Assignment5Part4 extends TextProgram {

    /**
     * number of columns in the file
     */
    private static final int NUM_COLS = 1;

    /**
     * Name of file
     */
    private static final String FILE = "test.csv";

    // start program
    public void run() {
        println(extractColumn(FILE, NUM_COLS));
    }

    /**
     * This method returns the string of column
     *
     * @param filename    - filename of the CSV file
     * @param columnIndex - index of the column in the file
     * @return the string of column
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        try {
            //read all lines from the file
            List<String> lines = Files.readAllLines(Path.of(filename));
            for (String line : lines) {
                //each line in List
                List<String> fields = fieldsIn(line);
                //add defined field to result
                result.add(fields.get(columnIndex));
            }
        } catch (IOException e) {
            return null;
        }
        return result;
    }


    /**
     * Extracts fields in one line, separated by commas.
     *
     * @param line one line of csv file
     * @return ArrayList<String> of fields in line(row)
     */
    private ArrayList<String> fieldsIn(String line) {

        ArrayList<String> result = new ArrayList<>();
        //current field
        StringBuilder bufferSting = new StringBuilder();
        //flag which determines that current char is between double quotes
        boolean haveQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            //check for two double quotes in sequence
            if (c == '"' && i != line.length() - 1 && line.charAt(i + 1) == '"') {
                bufferSting.append('"');
                i++;
                continue;
            }
            //if we get '"' swap haveQuotes flag
            if (c == '"') {
                haveQuotes = !haveQuotes;
                continue;
            }
            //if we get ',' and it isn't in double quotes
            //it's delimiter, save field to result and create new field
            if (c == ',' && !haveQuotes) {
                result.add(bufferSting.toString());
                bufferSting = new StringBuilder();
                continue;
            }
            //all other char add to current field
            bufferSting.append(c);
        }
        //save last field in line
        if (bufferSting.length() > 0)
            result.add(bufferSting.toString());
        return result;
    }
}







