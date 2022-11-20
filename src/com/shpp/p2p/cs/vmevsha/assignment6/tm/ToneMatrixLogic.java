package com.shpp.p2p.cs.vmevsha.assignment6.tm;

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
    //4
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        //The maximum value in the result array
        double max = 0;

        for (int i = 0; i < toneMatrix.length; i++) {
            //Calculation of activated buttons
            if (toneMatrix[i][column]) {
                //Recording an array of sounds samples.
                // And also their addition.
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] += samples[i][j];
                }
            }
        }

        //Finding the wave with the highest intensity.
        for (int i = 0; i < result.length; i++) {
            if (Math.abs(result[i]) > Math.abs(max))
                max = result[i];
        }
        // Empty array option.
        //In order not to twist another cycle into an empty one.
        if (max == 0)
            return result;
        //Sound wave normalization for the range [-1.0; 1.0]
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                result[i] = 0;
            } else {
                result[i] = result[i] / max;
            }
        }

        return result;
    }
}