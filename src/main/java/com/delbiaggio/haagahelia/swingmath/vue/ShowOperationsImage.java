/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.vue;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class ShowOperationsImage {
    private static ShowOperationsImage current = null;
    private ResourceBundle resBundImg;
    
    private ShowOperationsImage(){
        resBundImg = ResourceBundle.getBundle("ImgResources");
    }
    
    public static ShowOperationsImage getCurrent(){
        if (current == null) {
            current = new ShowOperationsImage();
        }
        return current;
    }       
    
    public void showOperationsImage(JLabel lbl,ArrayList<String> lstOP,ArrayList<JLabel> lstOperations) {
        hideLstLabels(lstOperations);        
        int x = lbl.getX() + lbl.getText().length()*10+10;        
        for (String op : lstOP) {
            findOperations(op,lstOperations, x);
            x += 60;
        }
    }

    private void findOperations(String op,ArrayList<JLabel> lstOperations, int x) {
        for (JLabel lab : lstOperations) {
            if (resBundImg.getString(op).equals(lab.getName())) {
                lab.setVisible(true);
                lab.setBounds(x, lab.getY(), lab.getWidth(), lab.getHeight());
            }
        }
    }
    
    
    /*
        Affichage d'une ArrayList de JLabel
    */   
    public void hideLstLabels(ArrayList<JLabel> lst) {
        for (JLabel lab : lst) {
            lab.setVisible(false);
        }
    }
    
    public void showLstLabels(ArrayList<JLabel> lst) {
        for (JLabel lab : lst) {
            lab.setVisible(true);
        }        
    }
    
    public void hideLstLabels(int debut, ArrayList<JLabel> lst) {
        if (debut < 0) {
            return;
        }
        for (int i = debut; i < lst.size(); i++) {
            lst.get(i).setVisible(false);
        }
    }
}
