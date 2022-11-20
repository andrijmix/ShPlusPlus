package com.shpp.p2p.cs.vrubchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram { //4
    //variable name the side of coin
    String coin = "";
    //started bet
    int addMoney = 1;

    public void run() {
        //variable of sum money
        int sum = 0;
        while (sum < 20) {
            //Add random number [0,1] in this range
            //And this number is int (0 or 1)
            int a = (int) (Math.random() * (1 + 1)) + 1;
            //choose type of money
            typeOfMoney(a);
            //Show side of the coin
            println("Coin is drop - " + coin);
            //If side == Eagle we add to the table the amount of the bet
            if (coin.equals("Eagle")) {
                addMoney += addMoney;
            } else {  //We take all the money from the table and make a bet of 1 dollar
                sum += addMoney;
                println("This game you win - " + addMoney);
                println("Your total win - " + sum);//add win to the sum
                addMoney = 1;
            }
        }
    }

    //Choose side of coin with random number
    private void typeOfMoney(int a) {
        if (a == 1) {
            coin = "Eagle";
        } else {
            coin = "Tail of coin";
        }
    }
}