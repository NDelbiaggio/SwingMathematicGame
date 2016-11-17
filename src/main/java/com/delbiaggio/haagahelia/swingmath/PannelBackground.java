/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author delbiaggionicolas
 */
public class PannelBackground extends java.awt.Panel {

    private String BACKGROUND = "mainBackground2.jpeg";
    
    /**
     * Creates new form PannelBackground
     */
    public PannelBackground() {
        initComponents();
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        BufferedImage bi = null;
        try {
            InputStream s = getClass().getClassLoader().getResourceAsStream(BACKGROUND);
            bi = ImageIO.read(s);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
        }
        g.drawImage(bi, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}