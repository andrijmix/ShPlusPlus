package com.shpp.p2p.cs.ikhlan.assignment6.tm;

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
//DECOMPOSITION
        // variable to find the maximum value
        double maxValue = result[0];
        for (int i = 0; i < toneMatrix.length; i++) {

            // write result only in specific column
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[0].length; j++) {

                    // write the sound in the desired cell
                    result[j] += samples[i][j];
                }
            }
        }

        // find max number and negative numbers in array
        for (double j : result) {
            if (j < 0) {
                j = -j;
            }
            if (j > maxValue)
                maxValue = j;
        }

        // make sound normalization so that their values do not go beyond the permissible limits
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                result[i] = result[i] / maxValue;
            }
        }
        return result;
    }
}
//4