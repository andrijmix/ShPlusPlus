package com.shpp.p2p.cs.okim.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Assignment5Part4.java - CSV parsing.
 */
//не вивводить 3 член
//не прокоментовані глобальні
public class Assignment5Part4 extends TextProgram {  //5
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<ArrayList<String>> column = new ArrayList<>();
    int startIndex; // start index
    int commaIndex; // comma indexOfBoundsException

    /**
     * The program displays the column values.
     */

    public void run() {
        println(extractColumn("test.csv", 1));
    }

    /**
     * Extracting a column from the file.
     *
     * @param filename    CSV file name.
     * @param columnIndex Index of column we need to get.
     * @return All values in the selected column.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        try {

            BufferedReader file = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = file.readLine()) != null) {
                lines.add(line);
            }

            file.close();

            sortByColumn();
            return column.get(columnIndex);

        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Splits the import data into columns.
     */
    private void sortByColumn() {
        for (String line : lines) {
            lineSeparator(line);
        }
    }

    /**
     * Splits each line into columns.
     *
     * @param line Line in import data.
     */
    private void lineSeparator(String line) {
        int colNum = columnCount();
        startIndex = 0;

        for (int i = 0; i < colNum; i++) {
            ArrayList<String> colLine = new ArrayList<>();

            column.add(colLine);

            String cell = extractCell(line);

            colLine = column.get(i);
            colLine.add(cell);
            column.set(i, colLine);
        }
    }

    /**
     * Extract cell from line.
     *
     * @param line Line in import data.
     * @return Separate cell.
     */
    private String extractCell(String line) {
        int isQuotes = -1;
        String cell;

        try {
            for (int i = startIndex; i < line.length(); i++) {
                if (line.charAt(i) == '"') {
                    isQuotes *= (-1);
                    if (isQuotes != 1 && i != line.length() - 1 && line.charAt(i + 1) == ',') {
                        commaIndex = i + 1;
                        break;
                    }
                } else if (isQuotes == -1 && line.charAt(i) == ',') {
                    commaIndex = i;
                    break;
                }
            }

            if (betweenQuotes(line, commaIndex)) {
                cell = line.substring(startIndex + 1, commaIndex - 1);
            } else {
                cell = line.substring(startIndex, commaIndex);
            }

            startIndex = commaIndex + 1;
        } catch (Exception e) {
            if (betweenQuotes(line, line.length())) {
                cell = line.substring(startIndex + 1, line.length() - 1);
            } else {
                cell = line.substring(startIndex);
            }
        }
        cell = removeDoubleQuotes(cell);

        return cell;
    }

    /**
     * Is the cell wrapped in quotes?
     *
     * @param line     The cell.
     * @param lastChar Index last char + 1.
     * @return True/False.
     */
    private boolean betweenQuotes(String line, int lastChar) {
        return line.charAt(startIndex) == '"' && line.charAt(lastChar - 1) == '"';
    }

    /**
     * Remove double quotes to single quot.
     *
     * @param cell The cell.
     * @return The cell without double quotes.
     */
    private String removeDoubleQuotes(String cell) {
        return cell.replace("\"\"", "\"");
    }

    /**
     * Counts the number of columns.
     *
     * @return The number of columns.
     */
    private int columnCount() {
        int colNum = 1;
        int quotesIndexStart;
        int quotesIndexFinish;
        String line;
        line = lines.get(0);


        for (int i = 0; i < line.length(); i++) {
            if (line.startsWith("\"", i)) {
                quotesIndexStart = line.indexOf("\"", i);
                quotesIndexFinish = line.indexOf("\"", quotesIndexStart);
                i = quotesIndexFinish;
            }
            if (line.charAt(i) == ',') {
                colNum += 1;
            }
        }
        return colNum;
    }


}
