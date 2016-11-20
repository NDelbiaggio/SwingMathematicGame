package com.delbiaggio.haagahelia.swingmath.math;

import java.util.ArrayList;

/**
 *
 * @author delbiaggionicolas
 */
public class MathOperation {

    public static ArrayList<Integer> getPGCD(int number) {
        ArrayList<Integer> lstPGCD = new ArrayList<>();
        int middle = (int) Math.sqrt(number);
        if (number % middle == 0) {
            lstPGCD.add(middle);
        }
        for (int i = middle - 1; i > 0; i--) {
            if (number % i == 0) {
                lstPGCD.add(i);
                lstPGCD.add(number / i);
            }
        }
        return lstPGCD;
    }
  
    public static boolean checkEquation(int nb1, int nb2, String op, int result) {
        switch (op) {
            case "+":
                return nb1 + nb2 == result;
            case "-":
                return nb1 - nb2 == result;
            case "*":
                return nb1 * nb2 == result;
            case "/":
                return nb1 / nb2 == result;
            default:
                throw new AssertionError();
        }
    }
    
}
