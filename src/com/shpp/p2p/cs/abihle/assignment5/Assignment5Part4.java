package com.shpp.p2p.cs.abihle.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV parsing
 */
public class Assignment5Part4 extends TextProgram { //5
    /**
     * Application entry point.
     * Test method extractColumn
     */
    @Override
    public void run() {
        extractColumn("test.csv", 1).forEach(System.out::println);
    }

    /**
     * Parses csv file and returns one column as ArraysList<String>
     *
     * @param filename    cvs file to parse
     * @param columnIndex index of column to extract
     * @return ArrayList<String> of fields defined by columnIndex in each row
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        try {
            //read all lines in file to List
            List<String> lines = Files.readAllLines(Path.of(filename));
            for (String line : lines) {
                //extract fields in each line as List
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
        StringBuilder fieldSB = new StringBuilder();
        //flag which determines that current char is between double quotes
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            //check for two double quotes in sequence
            if (c == '"' && i != line.length() - 1 && line.charAt(i + 1) == '"') {
                fieldSB.append('"');
                i++;
                continue;
            }
            //if we get '"' swap inQuotes flag
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }
            //if we get ',' and it isn't in double quotes
            //it's delimiter, save field to result and create new field
            if (c == ',' && !inQuotes) {
                result.add(fieldSB.toString());
                fieldSB = new StringBuilder();
                continue;
            }
            //all other char add to current field
            fieldSB.append(c);
        }
        //save last field in line
        if (fieldSB.length() > 0)
            result.add(fieldSB.toString());
        return result;
    }
}
