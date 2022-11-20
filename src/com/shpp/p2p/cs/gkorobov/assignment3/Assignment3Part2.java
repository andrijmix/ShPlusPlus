package com.shpp.p2p.cs.gkorobov.assignment3;


import com.shpp.cs.a.console.TextProgram;

/**
 * This program take a number which bigger than 0
 * and make manipulation after that number will be 1
 * it is name graded numbers
 * @author Gleb Korobov
 * @version 1.0
 */
public class Assignment3Part2 extends TextProgram {
    public void run() {
        int number = putNumber();
        gradeNumbers(number);
    }

    /**
     * get number from user if this number less than 1 program ask user give
     * a new number
     * program take only integers if it is a double it will parse to int
     * @return number bigger than 0
     */
    private int putNumber() {
        int number;
        do {
            number = (int) readDouble("Enter a number: ");
        } while (number < 1);
        return number;
    }

    /**
     * if number is even program will division number by 2
     * if number is odd program will multiply number by 3 and plus 1
     * program always show number 1 in the end
     * @param number number which bigger than 0
     */
    private void gradeNumbers(int number) {
        while (number != 1) {
            if ((number % 2) == 0) {
                System.out.print(number + " is even so I take half: ");
                number = number / 2;
                System.out.println(number);
            } else {
                System.out.print(number + " is odd so I make 3n + 1: ");
                number = (number * 3) + 1;
                System.out.println(number);
            }
        }
        System.out.println("end");
    }
}
