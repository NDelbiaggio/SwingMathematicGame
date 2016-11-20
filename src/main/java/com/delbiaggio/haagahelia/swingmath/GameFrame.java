/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath;

import com.delbiaggio.haagahelia.swingmath.controller.ListLabel;
import com.delbiaggio.haagahelia.swingmath.domaine.Configuration;
import com.delbiaggio.haagahelia.swingmath.math.GeneratorManagement;
import com.delbiaggio.haagahelia.swingmath.tools.imageReader.LoaderImage;
import com.delbiaggio.haagahelia.swingmath.math.MathOperation;
import com.delbiaggio.haagahelia.swingmath.timer.TimerBackgroundColor;
import com.delbiaggio.haagahelia.swingmath.timer.TimerRun;
import com.delbiaggio.haagahelia.swingmath.tools.fileReaderXML.XmlFileReader;
import com.delbiaggio.haagahelia.swingmath.vue.ShowAnnimationAndArchivements;
import com.delbiaggio.haagahelia.swingmath.vue.ShowOperationsImage;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.text.PlainDocument;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author delbiaggionicolas
 */
public class GameFrame extends javax.swing.JFrame {

    private int LIFES = 3;
    private int nbPoints = 0;
    private int nbLifes = LIFES;
    private int TIME = 15;

    private Configuration conf;
    private Timer t = new Timer();
    private TimerRun run;
    public ResourceBundle resBund;

    private ListLabel<JLabel> lstLifes = new ListLabel<>();
    private ListLabel<JLabel> lstOperations = new ListLabel<>();
    private ListLabel<JLabel> lstArchivement = new ListLabel<>();
    private ListLabel<JLabel> lstAnnimation = new ListLabel<>();

    public GameFrame() {
        initComponents();
        this.setLayout(null);
        setPosition();
        PlainDocument doc = (PlainDocument) tfResult.getDocument();
        doc.setDocumentFilter(new MyIntFilter());
        conf = XmlFileReader.getCurrent().getInitialConf();
        if (conf.getTime()) {
            setTimerSeconds();
        }
        manageNumberGeneration();
        tfResult.requestFocusInWindow();
        resBund = ResourceBundle.getBundle("MessagesBundle", conf.getLocal());
        setTranslation();
        lblTime.setVisible((conf.getTime()));
        validate();
        lblLifes.setBackground(this.getBackground());
        LoaderImage loader = new LoaderImage(this);
        lstLifes = loader.getLstLifesImage(nbLifes);
        loader.setLayout();

        ShowOperationsImage.getCurrent().showOperationsImage((ArrayList<String>) conf.getLstOp().getList(), lstOperations, conf.getLocal());
    }

    public ArrayList<JLabel> getLstOperations() {
        return lstOperations;
    }

    public ListLabel<JLabel> getLstArchivement() {
        return lstArchivement;
    }

    public ListLabel<JLabel> getLstAnnimation() {
        return lstAnnimation;
    }

    private void setPosition() {
        btnCheck.setBounds(527, 308, 130, 25);
        lblPts.setBounds(153, 190, 200, 30);
        lblLifes.setBounds(153, 228, 200, 30);
        btnSettings.setBounds(527, 189, 130, 25);
        lblNumber1.setBounds(213, 318, 100, 15);
        lblOp.setBounds(270, 318, 50, 15);
        lblNumber2.setBounds(325, 318, 100, 15);
        lblEqual.setBounds(365, 318, 50, 15);
        tfResult.setBounds(385, 309, 120, 30);
        lblTime.setBounds(358, 190, 200, 30);
        lblLstOperations.setBounds(153, 390, 200, 30);
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
        lblLstOperations.setText(StringUtils.capitalize(resBund.getString("operations")));
    }

    public Configuration getConf() {
        return this.conf;
    }

    public int getNbPoints() {
        return nbPoints;
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
            if (MathOperation.checkEquation(nb1, nb2, op, result)) {
                incrementPoints();
                ShowAnnimationAndArchivements.getCurrent().showArchivement(lstArchivement, lstAnnimation, nbPoints);
                manageNumberGeneration();
                colorBackRed(Color.green, 2);
                tfResult.setText(" ");
                tfResult.setText("");
                if (conf.getTime()) {
                    setTimerSeconds();
                }
            } else {
                colorBackRed(Color.red, 2);
                decreaseLife();
            }
            tfResult.requestFocus();
        }
    }

    private void incrementPoints() {
        nbPoints++;
        lblPts.setText(StringUtils.capitalize(resBund.getString("points") + ": " + nbPoints));
        lblPts.validate();
    }

    public void colorBackRed(Color c, double timeSec) {
        Timer time = new Timer();
        TimerBackgroundColor runCol = new TimerBackgroundColor(tfResult, timeSec, c);
        time.scheduleAtFixedRate(runCol, 0, 500);
    }

    public void setTimerSeconds() {
        if (run != null) {
            run.cancel();
        }
        run = new TimerRun(this, lblTime, conf.getNbSeconds());
        t.scheduleAtFixedRate(run, 0, 1000);
    }

    public void stopTimerSeconds() {
        if (run != null) {
            run.cancel();
        }
    }

    public void manageNumberGeneration() {
        String symb = GeneratorManagement.getCurrent().generateSymbole(conf);
        lblOp.setText(symb);
        int[] result = GeneratorManagement.getCurrent().getTwoGeneratedNumbers(conf, symb);
        lblNumber1.setText(result[0] + "");
        lblNumber2.setText(result[1] + "");
    }

    public void decreaseLife() {
        nbLifes--;
        showLifes(nbLifes,lstLifes);
    }

    public void resetLifesAndPoints() {
        nbPoints = 0;
        lblPts.setText(StringUtils.capitalize(resBund.getString("points") + ": 0"));
        nbLifes = LIFES;
        ShowOperationsImage.getCurrent().showLstLabels(lstLifes);
    }

    private void showLifes(int nbLifes,ListLabel<JLabel> lstLifes) {
        if (nbLifes<0) {
            return;
        }
        for (int i = nbLifes; i < lstLifes.size(); i++) {
            lstLifes.get(i).setVisible(false);
        }
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
        setResizable(false);
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
