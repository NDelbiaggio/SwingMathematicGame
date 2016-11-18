/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.gameFrameComponents;

import com.delbiaggio.haagahelia.swingmath.GameFrame;
import com.delbiaggio.haagahelia.swingmath.controller.ListArchievements;
import com.delbiaggio.haagahelia.swingmath.tools.ImageIconReader;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class LoaderImage {

    private String BACKGROUND = "mainBackground9.jpg";
    private GameFrame parent;

    public LoaderImage(GameFrame parent) {
        this.parent = parent;
    }

    public void setLayout() {
        loadOperations();
        loadArchivements();
        JLabel border = getCustomisedJLabel("backgroundBorder.png", -120, -100, 1024, 805);
        parent.add(border);
        loadAnnimations();
        parent.add(getCustomisedJLabel(BACKGROUND, -120, -100, 1024, 805));
    }

    private void loadOperations() {
        ArrayList<JLabel> lstOp = parent.getLstOperations();
        JLabel plus = getCustomisedJLabel("plus.png", 271, 376, 50, 50);
        JLabel minus = getCustomisedJLabel("minus.png", 345, 376, 50, 50);
        JLabel division = getCustomisedJLabel("division.png", 400, 376, 50, 50);
        JLabel multiplication = getCustomisedJLabel("multiplication.png", 460, 376, 50, 50);
        addInArrayAndParent(lstOp, plus);
        addInArrayAndParent(lstOp, minus);
        addInArrayAndParent(lstOp, division);
        addInArrayAndParent(lstOp, multiplication);
    }
    
    private void loadArchivements() {
        ListArchievements lstArch = parent.getLstArchivement();
        JLabel regle = getCustomisedJLabel("regle.png", 600, 106, 50, 50);
        regle.setVisible(false);
        JLabel compas = getCustomisedJLabel("compas.png", 600, 106, 50, 89);
        compas.setVisible(false);
        JLabel tableau = getCustomisedJLabel("tableau.png", 600, 106, 50, 89);
        tableau.setVisible(false);
        JLabel calculette = getCustomisedJLabel("calculatrice.png", 600, 106, 50, 43);
        calculette.setVisible(false);
        JLabel bag = getCustomisedJLabel("bag.png", 600, 106, 60, 60);
        bag.setVisible(false);
        addInArrayAndParent(lstArch, regle);
        addInArrayAndParent(lstArch, compas);
        addInArrayAndParent(lstArch, tableau);
        addInArrayAndParent(lstArch, calculette);
        addInArrayAndParent(lstArch, bag);
    }
    
    private void loadAnnimations(){
        ListArchievements lstAnn = parent.getLstAnnimation();
        JLabel amb = getCustomisedJLabel("ambulance.png", 0, 0, 60, 45);
        amb.setVisible(false);
        JLabel pompier = getCustomisedJLabel("pompier.png", 0, 0, 60, 45);
        pompier.setVisible(false);
        JLabel moto = getCustomisedJLabel("moto.png", 0, 0, 60, 27);
        moto.setVisible(false);
        JLabel police = getCustomisedJLabel("police2.png", 0, 0, 80, 28);
        police.setVisible(false);
        JLabel bike = getCustomisedJLabel("bike2.png", 0, 0, 60, 35);
        police.setVisible(false);
        JLabel spacial = getCustomisedJLabel("navette.png", 0, 0, 80, 63);
        police.setVisible(false);
        addInArrayAndParent(lstAnn, bike);
        addInArrayAndParent(lstAnn, moto);
        addInArrayAndParent(lstAnn, police);
        addInArrayAndParent(lstAnn, amb);
        addInArrayAndParent(lstAnn, pompier);
        addInArrayAndParent(lstAnn, spacial);        
    }

    private void addInArrayAndParent(ArrayList lst, JLabel l) {
        lst.add(l);
        parent.add(l);
    }

    private JLabel getCustomisedJLabel(String file, int x, int y, int width, int height) {
        JLabel back = new JLabel(ImageIconReader.getCurrent().readImageIcon(file));
        back.setName(file);
        back.setBounds(x, y, width, height);
        return back;
    }

}
