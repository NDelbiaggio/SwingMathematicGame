/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath;

import com.delbiaggio.haagahelia.swingmath.domaine.Configuration;
import com.delbiaggio.haagahelia.swingmath.gameFrameComponents.JLabelBackground;
import com.delbiaggio.haagahelia.swingmath.tools.FileManager;
import com.delbiaggio.haagahelia.swingmath.tools.ImageIconReader;
import com.delbiaggio.haagahelia.swingmath.tools.TimerBackgroundColor;
import com.delbiaggio.haagahelia.swingmath.tools.TimerRun;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.PlainDocument;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author delbiaggionicolas
 */
public class GameFrame extends javax.swing.JFrame {

    private int LIFES = 7;
    private int nbPoints = 0;
    private int nbLifes = LIFES;
    private int TIME = 15;
    private String BACKGROUND = "mainBackground9.jpg";

    private Configuration conf;
    private Timer t = new Timer();
    private TimerRun run;
    public ResourceBundle resBund;
    public ResourceBundle resBundImg;

    private ArrayList<JLabel> lstLifes = new ArrayList<>();
    private ArrayList<JLabel> lstOperations = new ArrayList<>();

    public GameFrame() {
        initComponents();
        this.setLayout(null);
        setPosition();
        PlainDocument doc = (PlainDocument) tfResult.getDocument();
        doc.setDocumentFilter(new MyIntFilter());
        conf = FileManager.getCurrent().getInitialConf();
        if (conf.isTime()) {
            testTimer();
        }
        manageNumberGeneration();
        tfResult.requestFocusInWindow();
        resBund = ResourceBundle.getBundle("MessagesBundle", conf.getLocal());
        resBundImg = ResourceBundle.getBundle("ImgResources");
        setTranslation();
        setLabelTime(conf.isTime());
        validate();
        lblLifes.setBackground(this.getBackground());
        PannelBackground p = new PannelBackground();
        p.setSize(504, 186);
        p.validate();

        setPencilJlabel();
//        setLevelImage();
        //setSymbolPictures();

        new JLabelBackground(this).setLayout();
        //setBackgroundJlabel();
        
        showOperations();

    }
    
    public void showOperations(){
        ArrayList<String> lstOP = conf.getLstOp();
        for (JLabel lab : lstOperations) {
            lab.setVisible(false);
        }
        int x = 271;
        int y = 376;
        int width = 50;
        int length = 50;
        for (String string : lstOP) {
            System.out.println(string);
            findOperations(string,x,y,width,length);
            x+= 60;
        }
    }
    
    private void findOperations(String op,int x, int y, int width, int length){
        for (JLabel lab : lstOperations) {
            if (resBundImg.getString(op).equals(lab.getName())) {
                lab.setVisible(true);
                lab.setBounds(x, y, width, length);
            }
        }
    }
    
    
    public ArrayList<JLabel> getLstOperations(){
        return lstOperations;
    }

//    private void setSymbolPictures() {
//        BufferedImage bi = null;
//        try {
//            InputStream s = getClass().getClassLoader().getResourceAsStream("plus.png");
//            bi = ImageIO.read(s);
//            ImageIcon img = new ImageIcon(bi);
//            JLabel back = new JLabel(img);
//            back.setBounds(450, 452, 50, 49);
//            this.add(back);
//            lstLifes.add(back);
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + " " + e.getStackTrace());
//        }
//    }

//    private void setLevelImage() {
//        BufferedImage bi = null;
//        try {
//            InputStream s = getClass().getClassLoader().getResourceAsStream("tableau.png");
//            bi = ImageIO.read(s);
//            ImageIcon img = new ImageIcon(bi);
//            JLabel back = new JLabel(img);
//            back.setBounds(593, 452, 50, 89);
//            this.add(back);
//            lstLifes.add(back);
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + " " + e.getStackTrace());
//        }
//    }

    private void setPencilJlabel() {
        BufferedImage bi = null;
        for (int i = 0; i < nbLifes; i++) {
            try {
                InputStream s = getClass().getClassLoader().getResourceAsStream("pencil3.png");
                bi = ImageIO.read(s);
                ImageIcon img = new ImageIcon(bi);
                JLabel back = new JLabel(img);
                back.setBounds(-260 + (i * 30), -160, 1024, 805);
                this.add(back);
                lstLifes.add(back);
            } catch (Exception e) {
                System.out.println(e.getMessage() + " " + e.getStackTrace());
            }
        }
    }

    private void setPosition() {
        btnCheck.setBounds(527, 308, 130, 25);
        lblPts.setBounds(164, 190, 200, 30);
        lblLifes.setBounds(164, 228, 200, 30);
        btnSettings.setBounds(527, 189, 130, 25);
        lblNumber1.setBounds(213, 318, 100, 15);
        lblOp.setBounds(270, 318, 50, 15);
        lblNumber2.setBounds(325, 318, 100, 15);
        lblEqual.setBounds(365, 318, 50, 15);
        tfResult.setBounds(385, 309, 120, 30);
        lblTime.setBounds(358, 190, 200, 30);
        lblLstOperations.setBounds(153, 390, 200, 30);
    }

    private void setBackgroundJlabel() {
        JLabel back = new JLabel(ImageIconReader.getCurrent().readImageIcon(BACKGROUND));
        back.setBounds(-120, -100, 1024, 805);
        this.add(back);
    }

    public void setLabelTime(boolean result) {
        lblTime.setVisible(result);
    }

    public void setTranslation() {
        btnCheck.setText(StringUtils.capitalize(resBund.getString("validate")));
        lblTime.setText(StringUtils.capitalize(resBund.getString("time")));
        lblPts.setText(StringUtils.capitalize(resBund.getString("points") + ": 0"));
        lblLifes.setText(StringUtils.capitalize(resBund.getString("lifes") + ": "));
        btnSettings.setText(StringUtils.capitalize(resBund.getString("settings")));
    }

    public Configuration getConf() {
        return this.conf;
    }

    public void setInitialState() {
        nbPoints = 0;
        nbLifes = 0;
    }

    private void checkResult() {
        if (!tfResult.getText().isEmpty()) {
            int nb1 = Integer.parseInt(lblNumber1.getText().trim());
            int nb2 = Integer.parseInt(lblNumber2.getText().trim());
            String op = lblOp.getText();
            int result = Integer.parseInt(tfResult.getText().trim());
            if (checkEquation(nb1, nb2, op, result)) {
                nbPoints++;
                lblPts.setText("Points: " + nbPoints);
                manageNumberGeneration();
                colorBackRed(Color.green, 2);
                tfResult.setText(" ");
                tfResult.setText("");
                if (conf.isTime()) {
                    testTimer();
                }

                lblPts.validate();
            } else {
                colorBackRed(Color.red, 2);
                decreaseLife();
            }
            tfResult.requestFocus();
        }
    }

    public void colorBackRed(Color c, double timeSec) {
        Timer time = new Timer();
        TimerBackgroundColor runCol = new TimerBackgroundColor(tfResult, timeSec, c);
        time.scheduleAtFixedRate(runCol, 0, 500);
    }

    public void testTimer() {
        if (run != null) {
            run.cancel();
        }
        run = new TimerRun(this, lblTime, conf.getNbSeconds());
        t.scheduleAtFixedRate(run, 0, 1000);
    }

    public void stopTimer() {
        if (run != null) {
            run.cancel();    
        }
    }

    public void manageNumberGeneration() {
        String symb = generateSymbole();
        int alea = (int) Math.round(Math.random());
        int aleaNumb = generateNumber(conf.getMinNumb(), conf.getMaxNumb());
        int indTabRand = generateNumber(0, conf.getLstTable().size() - 1);
        Integer nbTable = conf.getLstTable().get(indTabRand);
        String nb1;
        String nb2;
        if (symb.equals("/")) {
            nb1 = nbTable + "";
            ArrayList<Integer> lstPGCD = getPGCD(nbTable);
            nb2 = lstPGCD.get(generateNumber(0, lstPGCD.size() - 1)) + "";
        } else if (conf.isSoustractionPos() && symb.equals("-")) {
            if (aleaNumb >= nbTable) {
                nb1 = aleaNumb + "";
                nb2 = nbTable + "";
            } else {
                nb1 = nbTable + "";
                nb2 = aleaNumb + "";
            }
        } else if (alea == 0) {
            nb1 = aleaNumb + "";
            nb2 = nbTable + "";
        } else {
            nb1 = nbTable + "";
            nb2 = aleaNumb + "";
        }
        lblNumber1.setText(nb1);
        lblNumber2.setText(nb2);
    }

    public void decreaseLife() {
        nbLifes--;
        showLifes();
    }

    public void resetLifesAndPoints() {
        nbPoints = 0;
        lblPts.setText(StringUtils.capitalize(resBund.getString("points") + ": 0"));
        nbLifes = LIFES;
        for (int i = 0; i < nbLifes; i++) {
            lstLifes.get(i).setVisible(true);
        }
    }

    private void showLifes() {
        for (int i = nbLifes; i >= 0 && i < LIFES; i++) {
            lstLifes.get(i).setVisible(false);
        }
    }

    /*
    *return a random number between two borders
     */
    private int generateNumber(int min, int max) {
        return min + (int) Math.round(Math.random() * (max - min));
    }

    private String generateSymbole() {
        ArrayList<String> lstOp = conf.getLstOp();
        int ind = generateNumber(0, lstOp.size() - 1);
        lblOp.setText(lstOp.get(ind));
        return lstOp.get(ind);
    }

    private boolean checkEquation(int nb1, int nb2, String op, int result) {
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

    private ArrayList<Integer> getPGCD(int number) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSettings = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        lblNumber1 = new javax.swing.JLabel();
        lblOp = new javax.swing.JLabel();
        lblNumber2 = new javax.swing.JLabel();
        lblEqual = new javax.swing.JLabel();
        lblPts = new javax.swing.JLabel();
        lblLifes = new javax.swing.JLabel();
        tfResult = new javax.swing.JTextField();
        btnCheck = new javax.swing.JButton();
        lblPosition = new javax.swing.JLabel();
        lblLstOperations = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(787, 614));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnSettings.setText("Settings");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblTime.setText("Time");

        lblNumber1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblNumber1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumber1.setText("5");

        lblOp.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblOp.setText("+");

        lblNumber2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblNumber2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumber2.setText("3");

        lblEqual.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblEqual.setText("=");

        lblPts.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblPts.setText("Points: 0");

        lblLifes.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblLifes.setText("Lifes: XXX");

        tfResult.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tfResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfResultActionPerformed(evt);
            }
        });

        btnCheck.setText("Validate");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        lblPosition.setText("jLabel1");

        lblLstOperations.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblLstOperations.setText("Operations: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLifes, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnCheck))
                    .addComponent(btnSettings, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lblNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblOp)
                        .addGap(26, 26, 26)
                        .addComponent(lblNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEqual)
                        .addGap(48, 48, 48)
                        .addComponent(tfResult, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(368, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblLstOperations)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnSettings))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lblPts)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLifes)
                    .addComponent(btnCheck))
                .addGap(45, 45, 45)
                .addComponent(lblTime)
                .addGap(145, 145, 145)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber1)
                    .addComponent(lblOp)
                    .addComponent(lblNumber2)
                    .addComponent(lblEqual)
                    .addComponent(tfResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(lblLstOperations)
                .addGap(100, 100, 100)
                .addComponent(lblPosition)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        checkResult();
    }//GEN-LAST:event_btnCheckActionPerformed

    private void tfResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfResultActionPerformed
        checkResult();
    }//GEN-LAST:event_tfResultActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        tfResult.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        Settings.getCurrent(this).setVisible(true);
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        lblPosition.setText("X = " + evt.getX() + " Y = " + (evt.getY() - 24));
        lblPosition.validate();
    }//GEN-LAST:event_formMouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnSettings;
    private javax.swing.JLabel lblEqual;
    private javax.swing.JLabel lblLifes;
    private javax.swing.JLabel lblLstOperations;
    private javax.swing.JLabel lblNumber1;
    private javax.swing.JLabel lblNumber2;
    private javax.swing.JLabel lblOp;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblPts;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField tfResult;
    // End of variables declaration//GEN-END:variables
}
