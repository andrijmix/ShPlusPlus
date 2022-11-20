package com.shpp.p2p.cs.itkachuk.assignment10;

//"x + a * -2,5 - 4.8 / b + 2^-c" "a = 2,5" "x = -1" "b = 4.0" "c = 2.0"
//"x + a * 2 - 4 / - b + -2^c" "a = 4,5" "x = -1" "b = 4.0" "c = 3.0" - кома
// "x + a * 2 / z * b + 2^c" "a = 4" "x = -1" "b = 4" "c = 2.0" - нема змінної
// "x + a * 2 / --4 * b + 2^c" "a = 4" "x = -1" "b = 4.0" "c = 2.0"  --
// "-x" "a = 4" "x = -1" "b = 4.0" "c = 0"

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetterOrDigit;

/**
 * The class evaluates a mathematical expression. It has the main method main(String[] args),
 * which calls one method.
 */
public class Assignment10Part1 {
    public static final int ASCII_KODE_MINUS = 45;
    public static final int ASCII_KODE_PLUS = 43;

    /**
     * The first parameter at the program input is a mathematical expression. The rest of the parameters are optional
     * and look like variable name = value, for example a = 2. The method calculates the mathematical expression
     * accepted by the parameter, taking into account the values of the variables.
     *
     * @param args An array of strings.
     */
    public static void main(String[] args) {
        String[] argsNew = createNewArray(args);
        checkInputCorrectness(argsNew);
        HashMap<String, Double> variables = createVariablesHashMap(argsNew);
        double result = calculate(argsNew[0], variables);
        System.out.println("Result: " + result);
    }

    /**
     * Creates a HashMap<String, Double> and fills it according to the principle: the key is the name of the
     * variable, the value is the value of the variable that the program accepts as an argument in the
     * String[] args array starting from the element of the array with index 1.
     **/
    private static HashMap<String, Double> createVariablesHashMap(String[] argsNew) {
        HashMap<String, Double> variables = new HashMap<>();
        for (int i = 1; i < argsNew.length; i++) {
            variables.put(argsNew[i].split("=")[0], Double.parseDouble(argsNew[i].split("=")[1]));
        }
        return variables;
    }

    /**
     * Checks the correctness of the user inputs.Does not allow entering non-letters or non-numbers at the beginning
     * and end of mathematical expressions and variable values.
     */
    private static void checkInputCorrectness(String[] argsNew) {
        if (!isLetterOrDigit(argsNew[0].charAt(0)) && argsNew[0].charAt(0) != 45 || !isLetterOrDigit(argsNew[0].charAt(argsNew[0].length() - 1))) {
            System.out.println("Error: a mathematical expression must start and end with a" +
                    " number or letter! Please enter a valid expression.");
            // Terminates the program.
            System.exit(0);
        }
        for (int i = 1; i < argsNew.length; i++) {
            if (!isLetterOrDigit(argsNew[i].charAt(0)) || !isLetterOrDigit(argsNew[i].charAt(argsNew[i].length() - 1))) {
                System.out.println("Error: values of variables must start and end with a number or letter! Please enter a valid values.");
                System.exit(0);
            }
        }
    }

    /**
     * Creates a new arguments array with no spaces, if any.
     *
     * @param args An array of strings.
     * @return argsNew A new arguments array.
     */
    private static String[] createNewArray(String[] args) {
        String[] argsNew = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            argsNew[i] = args[i].replaceAll("\\s+", "");
        }
        return argsNew;
    }

    /**
     * Performs mathematical calculations.
     *
     * @param formula   A mathematical expression in the form of a String.
     * @param variables A HashMap of variables values.
     * @return result of the calculation.
     */
    static double calculate(String formula, HashMap<String, Double> variables) {
        // Forms a new mathematical expression without variables and spaces.
        String formulaNew = deleteVariables(formula, variables);
        // Forms a new mathematical expression after raising to a degree.
        formulaNew = exponentiate(formulaNew);
        // Forms a new mathematical expression after multiplication or division.
        formulaNew = multiplyOrDivide(formulaNew);
        // Forms a result after addition or subtraction.
        formulaNew = addOrSubtract(formulaNew);
        // Returns data type Double.
        return Double.parseDouble(formulaNew);
    }

    /**
     * Performs addition or subtraction operations.
     *
     * @param formulaNew A mathematical expression.
     * @return result of the calculation.
     */
    private static String addOrSubtract(String formulaNew) {
        // The index of the first mathematical sign to the left of the "*" or "/" symbols in the expression.
        int indexBefore = -1;
        // The index of the first mathematical sign to the right of the "*" or "/" symbols symbol in the expression.
        int indexAfter = formulaNew.length() - 1;
        // Replaces the corresponding characters in the string.
        formulaNew = formulaNew.replaceAll("--", "\\+");
        formulaNew = formulaNew.replaceAll("\\+-", "\\-");
        // Searches for mathematical symbols in a string and performs the corresponding mathematical calculations.
        for (int i = 0; i < formulaNew.length(); i++) {
            if (formulaNew.charAt(0) == ASCII_KODE_MINUS && i == 0) i = 1;
            if (formulaNew.charAt(i) == ASCII_KODE_PLUS || formulaNew.charAt(i) == ASCII_KODE_MINUS) {


                if (formulaNew.indexOf(ASCII_KODE_PLUS, i + 1) < indexAfter && formulaNew.indexOf(ASCII_KODE_PLUS, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(43, i + 1);

                if (formulaNew.indexOf(45, i + 1) < indexAfter && formulaNew.indexOf(45, i + 1) > 0) {
                    if (formulaNew.indexOf(45, i + 1) - i == 1) indexAfter = formulaNew.indexOf(45, i + 2);
                    else indexAfter = formulaNew.indexOf(45, i + 1);
                }

                if (indexAfter == -1) indexAfter = formulaNew.length() - 1;

                if (formulaNew.charAt(0) == 45 && formulaNew.indexOf(45, 1) == -1 && formulaNew.indexOf(43, 1) == -1)
                    break;
                double addOrSubtractResult;
                if (indexAfter == formulaNew.length() - 1 && formulaNew.charAt(i) == 43)
                    addOrSubtractResult = Double.parseDouble(formulaNew.substring(0, i)) + Double.parseDouble(formulaNew.substring(i + 1));
                else if (indexAfter == formulaNew.length() - 1 && formulaNew.charAt(i) == 45)
                    addOrSubtractResult = Double.parseDouble(formulaNew.substring(0, i)) - Double.parseDouble(formulaNew.substring(i + 1));
                else {
                    double a = Double.parseDouble(formulaNew.substring(0, i));
                    double b = Double.parseDouble(formulaNew.substring(i + 1, indexAfter));
                    if (formulaNew.charAt(i) == 43) {
                        addOrSubtractResult = a + b;
                    } else {
                        addOrSubtractResult = a - b;
                    }
                }
                String subString;
                if (indexAfter == formulaNew.length() - 1) subString = formulaNew.substring(0);
                else subString = formulaNew.substring(0, indexAfter);

                subString = subString.replaceAll("\\+", "\\\\+");

                formulaNew = formulaNew.replaceAll(subString, String.valueOf(addOrSubtractResult));
                formulaNew = addOrSubtract(formulaNew);
                break;
            }
        }
        return formulaNew;
    }

    /**
     * Performs division or multiplication operations.
     *
     * @param formulaNew
     * @return
     */
    private static String multiplyOrDivide(String formulaNew) {
        // The index of the first mathematical sign to the left of the "*" or "/" symbols in the expression.
        int indexBefore = -1;
        // The index of the first mathematical sign to the right of the "*" or "/" symbols symbol in the expression.
        int indexAfter = formulaNew.length() - 1;

        for (int i = 0; i < formulaNew.length(); i++) {

            if (formulaNew.charAt(i) == 42 || formulaNew.charAt(i) == 47) {
                if (formulaNew.lastIndexOf(43, i - 1) > indexBefore &&
                        formulaNew.lastIndexOf(43, i - 1) >= 0 && isDigit(formulaNew.charAt(formulaNew.lastIndexOf(43, i - 1) - 1)))
                    indexBefore = formulaNew.lastIndexOf(43, i - 1);
                if (formulaNew.lastIndexOf(45, i - 1) > indexBefore && formulaNew.lastIndexOf(45, i - 1) >= 0)
                    indexBefore = formulaNew.lastIndexOf(45, i - 1);


                if (formulaNew.indexOf(42, i + 1) < indexAfter && formulaNew.indexOf(42, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(42, i + 1);
                if (formulaNew.indexOf(43, i + 1) < indexAfter && formulaNew.indexOf(43, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(43, i + 1);

                if (formulaNew.indexOf(45, i + 1) < indexAfter && formulaNew.indexOf(45, i + 1) > 0) {
                    if (formulaNew.indexOf(45, i + 1) - i == 1) indexAfter = formulaNew.indexOf(45, i + 2);
                    else indexAfter = formulaNew.indexOf(45, i + 1);
                }
                if (indexAfter == -1) indexAfter = formulaNew.length() - 1;
                if (formulaNew.indexOf(47, i + 1) < indexAfter && formulaNew.indexOf(47, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(47, i + 1);

                double multiplyOrDivideResult;
                if (indexAfter == formulaNew.length() - 1 && formulaNew.charAt(i) == 42)
                    multiplyOrDivideResult = Double.parseDouble(formulaNew.substring(indexBefore + 1, i)) * Double.parseDouble(formulaNew.substring(i + 1));
                else if (indexAfter == formulaNew.length() - 1 && formulaNew.charAt(i) == 47)
                    multiplyOrDivideResult = Double.parseDouble(formulaNew.substring(indexBefore + 1, i)) / Double.parseDouble(formulaNew.substring(i + 1));
                else {
                    double a = Double.parseDouble(formulaNew.substring(indexBefore + 1, i));
                    double b = Double.parseDouble(formulaNew.substring(i + 1, indexAfter));
                    if (formulaNew.charAt(i) == 42) {
                        multiplyOrDivideResult = a * b;
                    } else {
                        multiplyOrDivideResult = a / b;
                    }
                }
                String subString;
                if (indexAfter == formulaNew.length() - 1) subString = formulaNew.substring(indexBefore + 1);
                else subString = formulaNew.substring(indexBefore + 1, indexAfter);

                subString = subString.replaceAll("\\*", "\\\\*");
                formulaNew = formulaNew.replaceAll(subString, String.valueOf(multiplyOrDivideResult));
                formulaNew = multiplyOrDivide(formulaNew);
                break;
            }
        }
        return formulaNew;
    }

    /**
     * Performs exponentiation.
     */
    private static String exponentiate(String formulaNew) {
        // The index of the first mathematical sign to the left of the "^" symbol in the expression.
        int indexBefore = -1;
        // The index of the first mathematical sign to the right of the "^" symbol in the expression.
        int indexAfter = formulaNew.length() - 1;
        // The index of the "^" symbol in the expression.
        int iIndex = 0;
        for (int i = 0; i < formulaNew.length(); i++) {
            if (formulaNew.charAt(i) == 94) {
                iIndex = i;
                if (formulaNew.lastIndexOf(42, i - 1) > indexBefore && formulaNew.lastIndexOf(42, i - 1) >= 0)
                    indexBefore = formulaNew.lastIndexOf(42, i - 1);
                if (formulaNew.lastIndexOf(43, i - 1) > indexBefore && formulaNew.lastIndexOf(43, i - 1) >= 0)
                    indexBefore = formulaNew.lastIndexOf(43, i - 1);
                if (formulaNew.lastIndexOf(45, i - 1) > indexBefore && formulaNew.lastIndexOf(45, i - 1) >= 0)
                    indexBefore = formulaNew.lastIndexOf(45, i - 1);
                if (formulaNew.lastIndexOf(47, i - 1) > indexBefore && formulaNew.lastIndexOf(47, i - 1) >= 0)
                    indexBefore = formulaNew.lastIndexOf(47, i - 1);

                if (formulaNew.indexOf(42, i + 1) < indexAfter && formulaNew.indexOf(42, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(42, i + 1);
                if (formulaNew.indexOf(43, i + 1) < indexAfter && formulaNew.indexOf(43, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(43, i + 1);
                if (formulaNew.indexOf(45, i + 1) < indexAfter && formulaNew.indexOf(45, i + 1) > 0) {
                    if (formulaNew.indexOf(45, i + 1) - i == 1) indexAfter = formulaNew.indexOf(45, i + 2);
                    else indexAfter = formulaNew.indexOf(45, i + 1);
                }
                if (indexAfter == -1) indexAfter = formulaNew.length() - 1;
                if (formulaNew.indexOf(47, i + 1) < indexAfter && formulaNew.indexOf(47, i + 1) > 0)
                    indexAfter = formulaNew.indexOf(47, i + 1);

                double exponentiateResult;
                if (indexAfter == formulaNew.length() - 1)
                    exponentiateResult = Math.pow(Double.parseDouble(formulaNew.substring(indexBefore + 1, iIndex)), Double.parseDouble(formulaNew.substring(iIndex + 1)));
                else {
                    exponentiateResult = Math.pow(Double.parseDouble(formulaNew.substring(indexBefore + 1, iIndex)), Double.parseDouble(formulaNew.substring(iIndex + 1, indexAfter)));
                }
                String subString;
                if (indexAfter == formulaNew.length() - 1) subString = formulaNew.substring(indexBefore + 1);
                else subString = formulaNew.substring(indexBefore + 1, indexAfter);

                subString = subString.replaceAll("\\^", "\\\\^");
                formulaNew = formulaNew.replaceAll(subString, String.valueOf(exponentiateResult));
                formulaNew = exponentiate(formulaNew);
                break;
            }
        }
        return formulaNew;
    }

    /**
     * Replaces variables with values.
     *
     * @param formula
     * @param variables
     * @return
     */
    private static String deleteVariables(String formula, HashMap<String, Double> variables) {
        for (Map.Entry<String, Double> p : variables.entrySet()) {
            for (int i = 0; i < formula.length(); i++) {
                if (Objects.equals(p.getKey(), String.valueOf(formula.charAt(i))))
                    formula = formula.replaceAll(String.valueOf(formula.charAt(i)), String.valueOf(p.getValue()));

            }
        }
        for (int i = 0; i < formula.length(); i++) {

        }
        return formula;
    }
}