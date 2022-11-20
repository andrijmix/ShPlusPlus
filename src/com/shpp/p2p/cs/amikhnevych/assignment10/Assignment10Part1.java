package com.shpp.p2p.cs.amikhnevych.assignment10;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Double.*;

/**
 * Created the calculation by support:
 * "+" "-" "*" "/" "^"
 */
public class Assignment10Part1 {
    static ArrayList<String> PARSE_LIST = new ArrayList<>();
    static int numberOfTest = 0;
    static Boolean Log = false;
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
//TODO: один раз обробити рядок, згенерувати з нього щось, що вже представляє розпарсенний вираз

        getResult(args);
        // test(0, new String[]{"  ", "a = 2,5", "x = -1", "b = 4.0", "c = 2.0"});
        test(-30, new String[]{"5-5 * 2-10/2-5 * 3-15/3"});
        test(0, new String[]{"1+a", "a=-1"});
        test(3, new String[]{"2+a", "a=1"});
        test(4, new String[]{" a + b / c ^ 2 ", "a=2", "b=32 ", "c = 4"});
        // test(-8.2, new String[]{"x + a * -2,5 - 4.8 / b + 2^-c", "a = 2,5", "x = -1", "b = 4.0", "c = 2.0"});
        test(11, new String[]{"x + a * 2 / 4 * b + 2^c", "a = 4", "x = -1", "b = 4.0", "c = 2.0"});
        // test(1, new String[]{"x + a * 2 - 4 / - b + -2^c", "a = 4,5", "x = -1", "b = 4.0", "c = 3.0"});

        test(8, new String[]{"x + a * 2 / 4 * b + 2^c", "a = 4", "x = -1", "b = 4.0", "c = 0"});
        test(7, new String[]{"x + a * 2 / 4 * b + 0^c", "a = 4", "x = -1", "b = 4.0", "c = 2"});
        test(9, new String[]{"x + a * 2 / 4 * b + 4^c", "a = 4", "x = -1", "b = 4.0", "c = 0.5"});
        test(1, new String[]{"-x", "a = 4", "x = -1", "b = 4.0", "c = 0"});
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
     * @return to normalize formula
     */
    private static String[] normalizeDate(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].replaceAll(" ", "");
            args[i] = args[i].replaceAll(",", ".");
            args[i] = args[i].replaceAll("--", "+");
            args[i] = args[i].replaceAll("\\+-", "-");
            args[i] = args[i].replaceAll("-\\+", "-");
        }
        return args;
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
        ArrayList<Double> numbers = new ArrayList<>();
        ArrayList<Character> sings = new ArrayList<>();
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
                    numbers.add(Double.valueOf(num.toString()));
                    PARSE_LIST.add(num.toString());
                    num = new StringBuilder();
                    //i++;
                    //  formula = formula.replace(variable, x);

                }
            } else {
                if (num.length() > 0) {
                    PARSE_LIST.add(num.toString());
                    numbers.add(Double.valueOf(num.toString()));
                }
                if (singSupport.contains(Character.toString(formula.charAt(i)))) {
                    sings.add(formula.charAt(i));
                    PARSE_LIST.add(String.valueOf(formula.charAt(i)));
                }

                num = new StringBuilder();
            }

        }

        if (num.length() > 0) {
            PARSE_LIST.add(num.toString());
            numbers.add(Double.valueOf(num.toString()));
        }


        //Check if the fist element is negative
        if (formula.charAt(0) == '-') {
            numbers.set(0, numbers.get(0) * -1);
            sings.remove(0);
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

        System.out.println("PARSE_LIST: " + PARSE_LIST);
        if (Log) System.out.println("formula: " + formula);
        if (Log) System.out.println("sings: " + sings);
        if (Log) System.out.println("number: " + numbers);


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
        //check numbers of signs
        if (sings.size() >= numbers.size()) {
            System.out.println("The formula incorrect");
            System.out.println("Check math signs");
            return NaN;
        }


        searchPow(numbers, sings);
        multiAndDiv(numbers, sings);
        if (Log) System.out.println("formula: " + formula);
        if (Log) System.out.println("sings: " + sings);
        if (Log) System.out.println("number: " + numbers);
        number = additionAndSub(numbers, sings);

        return number;
    }

    /**
     * Find and calculation multiplication and division
     *
     * @param numbers list of numbers
     * @param sings   list of operators
     */
    private static void multiAndDiv(ArrayList<Double> numbers, ArrayList<Character> sings) {
        double number;

        for (int i = 0; i < sings.size(); ) {
            //find "*" calculation and delete Elements
            if (sings.get(i) == '*') {
                number = numbers.get(i) * numbers.get(i + 1);
                deleteElements(number, numbers, sings, i);
                i = 0;
                continue;
            }
            //find "/" calculation and delete Elements
            if (sings.get(i) == '/') {
                number = numbers.get(i) / numbers.get(i + 1);
                deleteElements(number, numbers, sings, i);
                i = 0;
            }
            i++;
        }
    }

    /**
     * Find and calculation Pow
     *
     * @param numbers list of numbers
     * @param sings   list of operators
     */
    private static void searchPow(ArrayList<Double> numbers, ArrayList<Character> sings) {
        double number;
        int operator = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (sings.size() == 0)
                break;
            if (sings.get(operator) == '^') {
                number = Math.pow(numbers.get(operator), numbers.get(operator + 1));
                deleteElements(number, numbers, sings, operator);
            } else if (sings.size() - 1 > operator)
                operator++;
        }
    }

    /**
     * Performs expression simplification operation
     * (removes the performed operation)
     *
     * @param number  - deleted number
     * @param numbers - list from which deleted
     * @param sings   - deleted sings
     * @param s       - list form with deleted
     */
    private static void deleteElements(double number, ArrayList<Double> numbers,
                                       ArrayList<Character> sings, int s) {
        numbers.remove(s);
        numbers.add(s + 1, number);
        numbers.remove(s);
        sings.remove(s);
    }

    /**
     * This method performs operation "+" and "-"
     *
     * @param numbers list of numbers
     * @param sings   list of operations
     * @return the final result for formula
     */
    private static double additionAndSub(ArrayList<Double> numbers, ArrayList<Character> sings) {
        double number;
        int s = 0;
        number = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            //break if  the end list
            if (sings.size() == s)
                break;
            //if we find "+" - make addition last number for number from list
            if (sings.get(s) == '+') {
                number += numbers.get(i);
                s++;
                // make subtraction
            } else if (sings.get(s) == '-') {
                number -= numbers.get(i);
                s++;
            }
        }
        return number;
    }
}
