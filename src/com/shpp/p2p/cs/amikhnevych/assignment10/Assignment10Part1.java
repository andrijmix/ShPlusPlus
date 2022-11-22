package com.shpp.p2p.cs.amikhnevych.assignment10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Double.*;

/**
 * Created the calculation by support:
 * "+" "-" "*" "/" "^"
 */
public class Assignment10Part1 {
    static ArrayList<String> PARSE_LIST = new ArrayList<>();
    static int numberOfTest = 0;
    static Boolean Log = true;
    //This sings supported by this version
    static String singSupport = "+-*/^";

    /**
     * The first parameter at the program input is a mathematical expression. The rest of the parameters are optional
     * and look like variable name = value, for example a = 2. The method calculates the mathematical expression
     * accepted by the parameter, taking into account the values of the variables.
     *
     * @param args An array of strings.
     */
    public static void main(String[] args) {
        getResult(args);
//        // test(0, new String[]{"  ", "a = 2,5", "x = -1", "b = 4.0", "c = 2.0"});
//        test(-30, new String[]{"5-5 * 2-10/2-5 * 3-15/3"});
//        test(0, new String[]{"1+a", "a=-1"});
//        test(3, new String[]{"2+a", "a=1"});
//        test(4, new String[]{" a + b / c ^ 2 ", "a=2", "b=32 ", "c = 4"});
//        // test(-8.2, new String[]{"x + a * -2,5 - 4.8 / b + 2^-c", "a = 2,5", "x = -1", "b = 4.0", "c = 2.0"});
//        test(11, new String[]{"x + a * 2 / 4 * b + 2^c", "a = 4", "x = -1", "b = 4.0", "c = 2.0"});
//        // test(1, new String[]{"x + a * 2 - 4 / - b + -2^c", "a = 4,5", "x = -1", "b = 4.0", "c = 3.0"});
//
//        test(8, new String[]{"x + a * 2 / 4 * b + 2^c", "a = 4", "x = -1", "b = 4.0", "c = 0"});
//        test(7, new String[]{"x + a * 2 / 4 * b + 0^c", "a = 4", "x = -1", "b = 4.0", "c = 2"});
//        test(9, new String[]{"x + a * 2 / 4 * b + 4^c", "a = 4", "x = -1", "b = 4.0", "c = 0.5"});
//        test(1, new String[]{"-x", "a = 4", "x = -1", "b = 4.0", "c = 0"});
    }

    private static void test(double result, String[] formula) {
        numberOfTest++;
        System.out.println("#Test: " + numberOfTest);
        System.out.println("formula: " + formula[0]);
        if (getResult(formula) == result)
            System.out.println("\u001B[42m" + "TRUE" + "\u001B[0m");
        else
            System.out.println("\u001B[41m" + "FALSE" + "\u001B[0m");
    }

    private static double getResult(String[] args) {

        PARSE_LIST.clear();
        normalizeDate(args);
        HashMap<String, Double> variables = new HashMap<>();
        // Set hashMap key is letter
        for (int i = 1; i < args.length; i++) {
            String[] var = args[i].split("=");
            String variable = var[0];
            String number = var[1];
            // fill HashMap variables
            variables.put(variable, Double.valueOf(number));
        }
        // extract formula
        String formula = args[0];
// print final result
        double result = calculate(formula, variables);
        exceptions(result);
        System.out.println("result: " + result);
        return result;
    }

    /**
     * This method normalize the format of formula
     *
     * @param args redacted format string
     */
    private static void normalizeDate(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].replaceAll(" ", "");
            args[i] = args[i].replaceAll(",", ".");
            args[i] = args[i].replaceAll("--", "+");
            args[i] = args[i].replaceAll("\\+-", "-");
            args[i] = args[i].replaceAll("-\\+", "-");
        }
    }

    /**
     * This method check the result of the calculation
     *
     * @param result - get result from formula
     */
    static void exceptions(double result) {
        if (result == POSITIVE_INFINITY || result == NEGATIVE_INFINITY) {
            System.out.println("The formula incorrect, maybe you divided by zero");
        }

    }

    /**
     * Calculate a formula
     *
     * @param formula   - the string of formula
     * @param variables - HashMap from variables
     * @return the result calculation
     */
    static double calculate(String formula, HashMap<String, Double> variables) {
        if (Log) System.out.println("formula: " + formula);
        if (Log) System.out.println("variables: " + variables.toString());
        double number;
        // List of the number from a formula
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {

            if (formula.charAt(i) >= '0' && formula.charAt(i) <= '9' || formula.charAt(i) == '.') {
                num.append(formula.charAt(i));

            }
            //replace variables
            else if (formula.charAt(i) >= 'a' && formula.charAt(i) <= 'z') {
                String variable = String.valueOf(formula.charAt(i));
                if (variables.containsKey(variable)) {
                    String x = String.valueOf(variables.get(variable));
                    num.append(x);
                    PARSE_LIST.add(num.toString());
                    num = new StringBuilder();
                }
            } else {
                if (num.length() > 0) {
                    PARSE_LIST.add(num.toString());
                }
                if (singSupport.contains(Character.toString(formula.charAt(i)))) {
                    PARSE_LIST.add(String.valueOf(formula.charAt(i)));
                }
                num = new StringBuilder();
            }

        }
        if (num.length() > 0) {
            PARSE_LIST.add(num.toString());
        }

        //Check if the fist element is negative
        if (formula.charAt(0) == '-') {
            double n = Double.parseDouble(PARSE_LIST.get(1));
            n *= -1;
            PARSE_LIST.remove(0);
            PARSE_LIST.set(0, String.valueOf(n));
        }

        // replace  variables in formula (to check if we have all the variables specified)
        for (int i = 0; i < formula.length(); i++) {
            String variable = String.valueOf(formula.charAt(i));
            //Check if the variable have in HashMap
            if (variables.containsKey(variable)) {
                String x = String.valueOf(variables.get(variable));
                formula = formula.replace(variable, x);
            }
        }


        if (Log) System.out.println("formula: " + formula);
        if (Log) System.out.println("PARSE_LIST: " + PARSE_LIST);


        // count variables
        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) >= 'a' && formula.charAt(i) <= 'z' ||
                    formula.charAt(i) >= 'A' && formula.charAt(i) <= 'Z') {
                System.out.println("The formula incorrect");
                System.out.println("not recognized " + formula.charAt(i));
                System.out.println("check if the input is correct");
                return NaN;
            }
        }
        brackets();
        searchPow();
        multiAndDiv();
        number = additionAndSub();
        if (Log) System.out.println("formula: " + formula);
        if (Log) System.out.println("PARSE_LIST: " + PARSE_LIST);
        return number;
    }

    private static void brackets() {
        int openBracketsIndex = -1, closeBracketsIndex = -1;
        ArrayList<String> bracketsList = new ArrayList<>();
        for (int i = 0; i < PARSE_LIST.size(); i++) {
            if (Objects.equals(PARSE_LIST.get(i), "("))
                openBracketsIndex = i;
            if (Objects.equals(PARSE_LIST.get(i), ")"))
                closeBracketsIndex = i;
            if (closeBracketsIndex != -1 && openBracketsIndex != -1) {
                System.out.println("brackets: " + bracketsList.toString());
                for (int j = openBracketsIndex; j < closeBracketsIndex; j++) {
                    bracketsList.add(PARSE_LIST.get(i));
                }
            }


        }
    }

    /**
     * Find and calculation multiplication and division
     */
    private static void multiAndDiv() {
        double number;
        for (int i = 0; i < PARSE_LIST.size(); i++) {
            //find "*" calculation and delete Elements
            if (Objects.equals(PARSE_LIST.get(i), "*")) {
                number = Double.parseDouble(PARSE_LIST.get(i - 1)) * Double.parseDouble(PARSE_LIST.get(i + 1));
                deleteElements(number, PARSE_LIST, i - 1);
                i = 0;
            } else
                //find "/" calculation and delete Elements
                if (Objects.equals(PARSE_LIST.get(i), "/")) {
                    number = Double.parseDouble(PARSE_LIST.get(i - 1)) / Double.parseDouble(PARSE_LIST.get(i + 1));
                    deleteElements(number, PARSE_LIST, i - 1);
                    i = 0;
                }
        }
    }

    /**
     * Find and calculation Pow
     */
    private static void searchPow() {
        double number;
        for (int i = 0; i < PARSE_LIST.size(); i++) {
            if (Objects.equals(PARSE_LIST.get(i), "^")) {
                number = Math.pow(Double.parseDouble(PARSE_LIST.get(i - 1)), Double.parseDouble(PARSE_LIST.get(i + 1)));
                deleteElements(number, PARSE_LIST, i - 1);
            }
        }
    }

    /**
     * Performs expression simplification operation
     * (removes the performed operation)
     *
     * @param number        - number after operation
     * @param Parse         - list of parse formula
     * @param deleteMembers - excessive member
     */
    private static void deleteElements(double number, ArrayList<String> Parse,
                                       int deleteMembers) {
        Parse.remove(deleteMembers);
        Parse.remove(deleteMembers);
        Parse.remove(deleteMembers);
        Parse.add(deleteMembers, String.valueOf(number));

    }

    /**
     * This method performs operation "+" and "-"
     *
     * @return the final result for formula
     */
    private static double additionAndSub() {
        double number = Double.parseDouble(PARSE_LIST.get(0));
        for (int i = 1; i < PARSE_LIST.size(); i++) {
            //if we find "+" - make addition last number for number from list
            if (Objects.equals(PARSE_LIST.get(i), "+")) {
                number += Double.parseDouble(PARSE_LIST.get(i + 1));
                // make subtraction
            } else if (Objects.equals(PARSE_LIST.get(i), "-")) {
                number -= Double.parseDouble(PARSE_LIST.get(i + 1));
            }
        }
        return number;
    }
}
