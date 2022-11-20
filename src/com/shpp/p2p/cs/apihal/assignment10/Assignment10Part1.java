package com.shpp.p2p.cs.apihal.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//"x + a * -2,5 - 4.8 / b + 2^-c" "a = 2,5" "x = -1" "b = 4.0" "c = 2.0"
//-1+1
//- 1 * 0.5
//"x + a * 2 / z * b + 2^c" "a = 4" "x = -1" "b = 4" "c = 2.0"
// "x + a * 2 / 4 * b + 2^c" "a = 4" "x = -1" "b = 4.0" "c = 0"
// "x + a * 2 / 4 * b + 0^c" "a = 4" "x = -1" "b = 4.0" "c = 2"
// "x + a * 2 / 4 * b + 4^c" "a = 4" "x = -1" "b = 4.0" "c = 0.5")

/**
 * Main class with basic program
 */
public class Assignment10Part1 {

    static final String POSSIBLE_NUMBERS = "1234567890";
    static final String POSSIBLE_CHARS = "qwertyuiopasdfghjklzxcvbnm";
    static final String POSSIBLE_SPECIAL_CHARS = ".,+-/*^ ";
    static final String POSSIBLE_VARIABLES = POSSIBLE_CHARS + POSSIBLE_NUMBERS + POSSIBLE_SPECIAL_CHARS;

    /**
     * This is the main method which starts the program, parses gotten arguments and print out the result of calculation
     *
     * @param args gotten data which consists formula and args
     */
    public static void main(String[] args) {


        if (args.length == 0)
            throw new IllegalArgumentException("Oops... Something is uncorrected with input args");

        String formula = CalculatorParsing.parsingFormula(args);  // parsing of data
        HashMap<String, Double> variables = CalculatorParsing.parsingVars(args);

        System.out.println("Input data: " + formula + " | " + variables);

        double result = calculate(formula, variables);

        System.out.println("Answer: " + formula + " = " + result);

    }

    /**
     * This method calculate result of formula according to args
     *
     * @param formula   parsed formula with letters
     * @param variables parsed args with letters values
     * @return answer of calculation
     */
    private static double calculate(String formula, HashMap<String, Double> variables) {


        ArrayList<String> nums = new ArrayList<>(Arrays.asList(formula.split(" "))); // creating ArrayList with parts of formula

        for (int index = 0; index < nums.size(); index++) { // replace values from variables
            for (String key : variables.keySet())
                if (nums.get(index).contains(key))
                    nums.set(index, nums.get(index).replace(key, String.valueOf(variables.get(key))));
        }

        while (nums.contains("^")) { // calculate all exponentiations in the equation
            int index = nums.indexOf("^");

            try {
                double result = CalculatorAction.exponentiation(nums.get(index - 1), nums.get(index + 1));
                nums.remove(index + 1);
                nums.remove(index);
                nums.remove(index - 1);
                nums.add(index - 1, String.valueOf(result));
            } catch (Exception e) {
                throw new IllegalArgumentException("Oops... Something is uncorrected with formula");
            }
        }

        while (nums.contains("*")) { // calculate all multiplications in the equation
            int index = nums.indexOf("*");

            try {
                double result = CalculatorAction.multiplication(nums.get(index - 1), nums.get(index + 1));
                nums.remove(index + 1);
                nums.remove(index);
                nums.remove(index - 1);
                nums.add(index - 1, String.valueOf(result));
            } catch (Exception e) {
                throw new IllegalArgumentException("Oops... Something is uncorrected with formula");
            }
        }

        while (nums.contains("/")) { // calculate all divisions in the equation
            int index = nums.indexOf("/");

            try {
                double result = CalculatorAction.division(nums.get(index - 1), nums.get(index + 1));
                nums.remove(index + 1);
                nums.remove(index);
                nums.remove(index - 1);
                nums.add(index - 1, String.valueOf(result));
            } catch (Exception e) {
                throw new IllegalArgumentException("Oops... Something is uncorrected with formula");
            }
        }

        while (nums.size() != 1) { // doing math actions we have to leave only one number
            // The loop calculate equation according to order of math actions

            int index = 1; // if everything is corrected in the second place is always some action

            if (nums.get(1).equals("+")) { // calculate addition on the second place of equation
                try {
                    double result = CalculatorAction.addition(nums.get(0), nums.get(index + 1));
                    nums.remove(index + 1);
                    nums.remove(index);
                    nums.remove(0);
                    nums.add(0, String.valueOf(result));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Oops... Something is uncorrected with formula");
                }
            } else { // calculate subtraction on the second place of equation
                try {
                    double result = CalculatorAction.subtraction(nums.get(0), nums.get(index + 1));
                    nums.remove(index + 1);
                    nums.remove(index);
                    nums.remove(0);
                    nums.add(0, String.valueOf(result));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Oops... Something is uncorrected with formula");
                }
            }
        }

        return Double.parseDouble(nums.get(0)); // return answer of calculation
    }
}