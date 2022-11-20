package com.shpp.p2p.cs.emishchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Assignment5Part4.java - CSV parsing.
 **/
public class Assignment5Part4 extends TextProgram {

    /**
     * The method opens the csv file and returns all the values that are in the specified column.
     */
    public void run() {
        println(extractColumn("test.csv", 1));

    }

    /**
     * The method loads the file data into an array, determines the required column, and returns the values
     * in the array. There is a check for the absence of a csv file.
     *
     * @param filename    Csv file name.
     * @param columnIndex Column number from file csv.
     * @return Returns an array of data from one column of the csv file.
     */

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        // Read csv file.
        ArrayList<String> fileCSV = readCSV(filename);
        // If file not found returns null.
        if (null == fileCSV) {
            return null;
        }
        // Returned the necessary data.
        return convertValues(findColumn(fileCSV, columnIndex));
    }

    /**
     * The method reads the data from the csv file and saves it to an array.
     * If file csv not found returns null.
     *
     * @param filename Csv file name.
     * @return Data array or null.
     */
    private ArrayList<String> readCSV(String filename) {
        ArrayList<String> csv = new ArrayList<>();
        try {
            // Read values from  csv file and save to array.
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while (true) {
                String word = br.readLine();
                // When the values in the file are over, exit the loop.
                if (word == null) break;
                csv.add(word);
            }
            br.close();
            // If the file does not exist, return null.
        } catch (IOException e) {
            println("You have a problem.");
            return null;
        }
        return csv;
    }

    /**
     * The method finds values in the required column in the array passed to it
     * and returns the found data in the array.
     *
     * @param fileCSV     Array of data from a file.
     * @param columnIndex The index of the data column to be found.
     * @return Array data column from file.
     */
    private ArrayList<String> findColumn(ArrayList<String> fileCSV, int columnIndex) {
        ArrayList<String> columnCSV = new ArrayList<>();
        // Variable counter quotation marks.
        int quotationMarks = 0;
        //In the cycle, we checked all the values of the array and find those that are in the
        // corresponding column.
        for (String checkValue : fileCSV) {
            // Counter column.
            int counterColumn = 0;
            // The string to store the found value.
            String value = "";
            // Finding values in an array and writing to a new array.
            for (int i = 0; i < checkValue.length(); i++) {
                char ch = checkValue.charAt(i);
                // Counts the number of characters '"'.
                if (ch == '"') {
                    quotationMarks++;
                    if (quotationMarks == 2) {
                        quotationMarks = 0;
                    }
                }
                // Counts the column number.
                if (ch == ',' && quotationMarks == 0) {
                    counterColumn++;
                }
                // If the condition is met, write the character to the string.
                if ((columnIndex == counterColumn && ch != ',') || (columnIndex == counterColumn && quotationMarks > 0 && ch == ',')) {
                    value += String.valueOf(ch);
                }
            }
            // Writes the selected rows to an array.
            columnCSV.add(value);
        }
        return columnCSV;
    }

    /**
     * The method converts the values from the file to the required format.
     *
     * @param columnCSV Array of one column.
     * @return Data array from the specified column of the file.
     */
    private ArrayList<String> convertValues(ArrayList<String> columnCSV) {
        ArrayList<String> dataCSV = new ArrayList<>();
        // Checks all elements of the array, remove the extra sign  '"'.
        for (String element : columnCSV) {
            int counterMarks = 0;
            String str = "";
            // Rewriting elements into a string
            for (int i = 0; i < element.length(); i++) {
                char ch = element.charAt(i);
                // Determine how many characters '"' are needed in a string.
                if (ch == '"') {
                    if (counterMarks > 0) {
                        str += String.valueOf(ch);
                        counterMarks = 0;
                    } else {
                        counterMarks++;
                    }
                } else {
                    str += String.valueOf(ch);
                    counterMarks = 0;
                }
            }
            // Write the result to a new array.
            dataCSV.add(str);
        }
        return dataCSV;
    }
}