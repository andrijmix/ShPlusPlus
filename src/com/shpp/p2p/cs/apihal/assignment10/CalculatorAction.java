package com.shpp.p2p.cs.apihal.assignment10;

public class CalculatorAction {
    public static double multiplication(String num1, String num2) { // it means "*"
        return Double.parseDouble(num1) * Double.parseDouble(num2);
    }

    public static double division(String num1, String num2) { // it means "/"
        return Double.parseDouble(num1) / Double.parseDouble(num2);
    }

    public static double addition(String num1, String num2) { // it means "+"
        return Double.parseDouble(num1) + Double.parseDouble(num2);
    }

    public static double subtraction(String num1, String num2) { // it means "-"
        return Double.parseDouble(num1) - Double.parseDouble(num2);
    }

    public static double exponentiation(String num1, String num2) { // it means "^"
        return Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
    }
}

