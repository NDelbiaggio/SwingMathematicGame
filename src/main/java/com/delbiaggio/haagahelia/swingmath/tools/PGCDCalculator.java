package com.delbiaggio.haagahelia.swingmath.tools;

import java.util.ArrayList;

/**
 *
 * @author delbiaggionicolas
 */
public class PGCDCalculator {

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
    
}
