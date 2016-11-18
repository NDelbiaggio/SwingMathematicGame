/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.gameFrameComponents;

import com.delbiaggio.haagahelia.swingmath.GameFrame;
import com.delbiaggio.haagahelia.swingmath.tools.ImageIconReader;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class JLabelBackground  {

    private String BACKGROUND = "mainBackground9.jpg";
    private GameFrame parent;

    public JLabelBackground(GameFrame parent) {
        this.parent = parent;
    }
    
    public  void setLayout(){
        ArrayList<JLabel> lstOp = parent.getLstOperations();
        JLabel plus = getComponent("plus.png", 271, 376, 50, 50);
        JLabel minus = getComponent("minus.png", 345, 376, 50, 50);
        JLabel division = getComponent("division.png", 400, 376, 50, 50);
        JLabel multiplication = getComponent("multiplication.png", 460, 376, 50, 50);
        
        lstOp.add(plus);
        lstOp.add(minus);
        lstOp.add(division);
        lstOp.add(multiplication);
        
        parent.add(plus);
        parent.add(minus);
        parent.add(division);
        parent.add(multiplication);
        
        
        parent.add(getComponent("regle.png", 400, 452, 50, 50));
        parent.add(getComponent("compas.png", 350, 452, 50, 89));
        
        parent.add(getComponent("tableau.png", 593, 452, 50, 89));
        parent.add(getComponent(BACKGROUND, -120, -100, 1024, 805));
    }
    
    
    
    private JLabel getComponent(String file,int x, int y , int width,int height){
        JLabel back = new JLabel(ImageIconReader.getCurrent().readImageIcon(file));
        back.setName(file);
        back.setBounds(x, y, width, height);
        return back;
    }
    



}
