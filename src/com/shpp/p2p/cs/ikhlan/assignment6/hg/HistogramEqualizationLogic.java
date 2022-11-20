package com.shpp.p2p.cs.ikhlan.assignment6.hg;

import java.util.HashMap;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        int[] result = new int[256];

        // memory for fast data retrieval by key
        HashMap<Integer, Integer> numbers = new HashMap<>();
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {

                // checking cells for data
                if (numbers.get(luminances[row][col]) == null) {
                    numbers.put(luminances[row][col], 1);

                    // if such a key already exists, then the number of matches is recorded
                } else {
                    numbers.put(luminances[row][col], numbers.get(luminances[row][col]) + 1);
                }
            }
        }

        // fill the array with the number of matches
        for (int i = 0; i <= 255; i++) {
            if (numbers.get(i) != null) {
                result[i] = numbers.get(i);
            }
        }
        return result;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] result = new int[histogram.length];
        for (int i = 0; i < histogram.length; i++) {

            // using the ternary operator write the sum of all the previous ones into the current cell
            result[i] = i == 0 ? histogram[i] : histogram[i] + result[i - 1];
        }
        return result;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {

        // multiply the width of the matrix by the height of the matrix
        return luminances.length * luminances[luminances.length - 1].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {

        // make a general histogram once so that you don't call it many times in a loop
        int[] cumulativeHistogram = cumulativeSumFor(histogramFor(luminances));

        // pixel array with new luminance
        int[][] newLuminance = new int[luminances.length][luminances[luminances.length - 1].length];
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {

                // determine the luminance value for each pixel
                newLuminance[row][col] = MAX_LUMINANCE * cumulativeHistogram[luminances[row][col]] / totalPixelsIn(luminances);
            }
        }
        return newLuminance;
    }
}//5
