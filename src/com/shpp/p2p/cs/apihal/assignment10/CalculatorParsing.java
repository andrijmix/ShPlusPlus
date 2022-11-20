package com.shpp.p2p.cs.apihal.assignment10;

import java.util.HashMap;

import static com.shpp.p2p.cs.apihal.assignment10.Assignment10Part1.*;

/**
 * This class contains method for parsing data!
 * Correct forms of String[] args:
 * - "1 + a * 2", "a = 2", "b = 3"
 * - "1 + a * 2" "a = 2,0" "b = 3"
 * - "1 + a * 2", "a=2", "b=3.0"
 * and other combined cases...
 * The form "1 + a * 2""a = 2""b = 3" IS INCORRECT !!!
 * The form "2a" IS INCORRECT !!! YOU MUST USE 2 * a
 */
public class CalculatorParsing {

    /**
     * This class is parsing formula from gotten args
     *
     * @param args gotten arguments
     * @return string formula which have already been parsed
     */
    static String parsingFormula(String[] args) {
        String formula = args[0];

        if (formula.endsWith(","))
            formula = formula.substring(0, formula.length() - 1);

        formula = formula.replace(" ", ""); // delete space

        if (POSSIBLE_SPECIAL_CHARS.contains(formula.substring(formula.length() - 1)))
            throw new IllegalArgumentException("The formula ends with uncorrected char - " + formula.substring(formula.length() - 1));

        for (char a : formula.toCharArray()) // check up allowed chars
            if (!POSSIBLE_VARIABLES.contains(Character.toString(a))) {
                throw new IllegalArgumentException("The formula contains uncorrected char - " + a);
            }

        formula = formula.replace("+", " + ");
        formula = formula.replace("-", " - ");
        formula = formula.replace("*", " * ");
        formula = formula.replace("/", " / ");
        formula = formula.replace("^", " ^ ");

        return formula;
    }

    /**
     * This class is parsing values of vars from gotten args
     *
     * @param args gotten arguments
     * @return Dictionary with vars and doubles which have already been parsed
     */
    static HashMap<String, Double> parsingVars(String[] args) {

        HashMap<String, Double> variables = new HashMap<>();

        for (int index = 1; index < args.length; index++) {
            String valString;
            double valDouble;
            String[] data;

            if (args[1].endsWith(","))
                args[1] = args[1].substring(0, args[1].length() - 1);

            if (args[index].contains("="))
                data = args[index].split("=");
            else
                throw new IllegalArgumentException(String.format("The variable %s is uncorrected, correct form is \"a = 2\"", args[index]));

            valString = data[0].strip();

            if (valString.length() != 1 || !POSSIBLE_CHARS.contains(valString))
                throw new IllegalArgumentException(String.format("The variable %s is uncorrected, you use illegal char, it must be one letter in lower case", valString));

            try {
                if (data[1].contains(",") || data[1].contains("."))
                    valDouble = Double.parseDouble(data[1].replace(",", ".").strip());
                else
                    valDouble = Integer.parseInt(data[1].strip());
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("The variable %s is uncorrected, correct form is \"a = 2\"", args[index]));
            }

            variables.put(valString, valDouble);

        }
        return variables;
    }
}

