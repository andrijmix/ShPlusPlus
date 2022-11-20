package com.shpp.cs.assignments.arrays.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        //  for (int col = 0; col < toneMatrix.length; col++) {
        for (int row = 0; row < toneMatrix[column].length; row++) {
            if (toneMatrix[column][row]) {
                result[row] = samples[column][row];
            }
        }

        // normalize range


        double minElement = result[0];//find min element
        double maxElement = result[0];//and max element
        for (int i = 0; i < result.length; i++) {
            if (result[i] > maxElement)
                maxElement = result[i];
            if (result[i] < minElement)
                minElement = result[i];
        }
        // created new range
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0)
                result[i] = result[i] / maxElement;

        }
        // }
        System.out.println(samples[samples.length - 1][samples[samples.length - 1].length - 1]);
        // System.out.println(Arrays.toString(result));

        /* TODO: Fill this in! */

        return result;
    }
}
