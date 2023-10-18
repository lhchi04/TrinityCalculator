package edu.trinity.assignment1;

public class Fibonacci {

    public static int calculate(int i) {
        if (i == 0) return 0;

        int firstNum = 0;
        int secondNum = 1;

        for (int j = 2; j <= i; j++) {
            int currNum =  secondNum + firstNum;
            firstNum = secondNum;
            secondNum = currNum;
        }
        return secondNum;
    }
}
