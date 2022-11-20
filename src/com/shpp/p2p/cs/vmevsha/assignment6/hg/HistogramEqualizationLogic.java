package com.shpp.p2p.cs.vmevsha.assignment6.hg;

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
        int[] histogram = new int[256];
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {
                histogram[luminances[row][col]]++;
            }
        }

        return histogram;
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
        int[] cumulativeSum = new int[histogram.length];
        for (int i = 0; i < cumulativeSum.length; i++) {
            if (i > 0) {
                cumulativeSum[i] = histogram[i] + cumulativeSum[i - 1];
                //For the first element of the array
            } else {
                cumulativeSum[i] = histogram[i];
            }
        }

        return cumulativeSum;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        int res = 0;
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {
                res++;
            }
        }
        return res;
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
        int[][] newLuminance = new int[luminances.length][luminances[0].length];
        //Created a buffer to read from the method once
        int[] buffer = cumulativeSumFor(histogramFor(luminances));
        double[] fractionSmaller = new double[buffer.length];
        int totalPixels = totalPixelsIn(luminances);

        for (int i = 0; i < fractionSmaller.length; i++) {
            fractionSmaller[i] = (double) buffer[i] / totalPixels;
        }

        //Calculation of the new brightness matrix.
        for (int row = 0; row < newLuminance.length; row++) {
            for (int col = 0; col < newLuminance[row].length; col++) {

                newLuminance[row][col] = (int) (MAX_LUMINANCE * fractionSmaller[luminances[row][col]]);
            }
        }
        return newLuminance;
    }
}