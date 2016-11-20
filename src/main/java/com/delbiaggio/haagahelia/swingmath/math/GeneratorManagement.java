/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.math;

import com.delbiaggio.haagahelia.swingmath.domaine.Configuration;
import java.util.ArrayList;

/**
 *
 * @author delbiaggionicolas
 */
public class GeneratorManagement {

    private static GeneratorManagement current = null;
    
    private GeneratorManagement(){
        
    }
    
    public static GeneratorManagement getCurrent(){
        if (current == null) {
            current = new GeneratorManagement();
        }
        return current;
    }
    
    public  int[] getTwoGeneratedNumbers(Configuration conf,String symb) {
        int alea = (int) Math.round(Math.random());
        int aleaNumb = generateNumber(conf.getMinNumb(), conf.getMaxNumb());
        int indTabRand = generateNumber(0, conf.getLstTable().getList().size() - 1);
        Integer nbTable = conf.getLstTable().getList().get(indTabRand);
        int[] result;
        if (symb.equals("/")) {
            result = getGeneratedNumberDivision(nbTable);
        } else if (conf.getSoustractionPos() && symb.equals("-")) {
            if (aleaNumb >= nbTable) {
                result = new int[]{aleaNumb, nbTable};
            } else {
                result = new int[]{nbTable, aleaNumb};
            }
        } else if (alea == 0) {
            result = new int[]{aleaNumb, nbTable};
        } else {
            result = new int[]{nbTable, aleaNumb};
        }
        return result;
    }

    public int generateNumber(int min, int max) {
        return min + (int) Math.round(Math.random() * (max - min));
    }

    public String generateSymbole(Configuration conf) {
        ArrayList<String> lstOp = (ArrayList<String>) conf.getLstOp().getList();
        int ind = generateNumber(0, lstOp.size() - 1);
        return lstOp.get(ind);
    }
    
    public int[] getGeneratedNumberDivision(int nbTable ){
        int nb1 = nbTable;
        ArrayList<Integer> lstPGCD = MathOperation.getPGCD(nbTable);
        int nb2 = lstPGCD.get(generateNumber(0, lstPGCD.size() - 1));
        return new int[]{nb1,nb2};        
    } 
}
