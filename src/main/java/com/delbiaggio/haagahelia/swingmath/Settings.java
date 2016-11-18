package com.delbiaggio.haagahelia.swingmath;

import com.delbiaggio.haagahelia.swingmath.domaine.Configuration;
import com.delbiaggio.haagahelia.swingmath.domaine.Translation;
import com.delbiaggio.haagahelia.swingmath.tools.CreateTablesFromString;
import com.delbiaggio.haagahelia.swingmath.tools.FileManager;
import com.delbiaggio.haagahelia.swingmath.tools.MyFocusTraversalPolicy;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.text.PlainDocument;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author delbiaggionicolas
 */
public class Settings extends javax.swing.JFrame {

    private static Settings current;
    private GameFrame parent;
    private ArrayList<Translation> lstTrans = new ArrayList<Translation>();
    private Translation currentTrans;
    private String BACKGROUND = "backgroundSettings5.jpg";
    private boolean byUser = true;
    

    public Settings(GameFrame parent) {
        initComponents();
        this.setLayout(null);
        setPositon();
        setBackgroundImage();

        initIntTextField();
        tfTime.setVisible(false);
        this.parent = parent;
        Configuration conf = parent.getConf();
        tfMin.setText(conf.getMinNumb() + "");
        tfMax.setText(conf.getMaxNumb() + "");
        tfTime.setText(conf.getNbSeconds() + "");
        ckSubPos.setSelected(conf.isSoustractionPos());
        if (conf.isTime()) {
            ckTime.setSelected(true);
        }
        setTraduction();
        String key = conf.getLocal().getDisplayLanguage(Locale.ENGLISH).toLowerCase();
        if (key.equals("ge")) {
            key = "german";
        }
        int ind = lstTrans.indexOf(new Translation(key));
        byUser = false;
        chLanguage.setSelectedIndex(ind);
        currentTrans = lstTrans.get(ind);
        setTabulationOrder();
    }

    private void setPositon() {
        lblTable.setBounds(217, 275, 250, 30);
        lblMin.setBounds(217, 320, 250, 30);
        lblMax.setBounds(217, 365, 250, 30);
        
        tfTable.setBounds(367, 275, 100, 30);
        tfMin.setBounds(367, 320, 100, 30);
        tfMax.setBounds(367, 365, 100, 30);
        lblExample.setBounds(471, 275, 150 ,30);
        
        ckAddition.setBounds(217, 86, 150, 30);
        ckSoustraction.setBounds(217, 126, 150, 30);
        ckMultiplication.setBounds(217, 166, 150, 30);
        ckDivision.setBounds(217, 206, 150, 30);
        ckSubPos.setBounds(368, 126, 250, 30);
        ckTime.setBounds(217, 418, 150, 30);
        
        tfTime.setBounds(367, 418, 100, 30);
        btnDefaultSetting.setBounds(217, 490, 250, 30);
        btnApply.setBounds(217, 530, 250, 30);
        lblTitre.setBounds(245, 48, 200, 30);
        chLanguage.setBounds(481, 48, 150, 30);
    }

    private void setBackgroundImage() {
        BufferedImage bi = null;
        try {
            InputStream s = getClass().getClassLoader().getResourceAsStream(BACKGROUND);
            bi = ImageIO.read(s);
            ImageIcon img = new ImageIcon(bi);
            JLabel back = new JLabel(img);
            back.setBounds(-165, -100, 1024, 805);
            this.add(back);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
        }
    }

    private void reapaintComponent() {
        lblTitre.repaint();
        chLanguage.repaint();
        ckAddition.repaint();
        ckMultiplication.repaint();
        ckDivision.repaint();
        ckSoustraction.repaint();
        ckSubPos.repaint();
        lblMax.repaint();
        lblMin.repaint();
        lblTable.repaint();
        ckTime.repaint();
        btnApply.repaint();
        btnDefaultSetting.repaint();
        lblExample.repaint();
    }

    /*private void paintBackground(Graphics g) {
        BufferedImage bi = null;
        try {
            InputStream s = getClass().getClassLoader().getResourceAsStream(BACKGROUND);
            bi = ImageIO.read(s);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
        }
        
        g.drawImage(bi, 0, 0, getWidth(), getHeight(), null);
    }*/
    private void initIntTextField() {
        PlainDocument doc = (PlainDocument) tfMin.getDocument();
        doc.setDocumentFilter(new MyIntFilter());
        doc = (PlainDocument) tfMax.getDocument();
        doc.setDocumentFilter(new MyIntFilter());
        doc = (PlainDocument) tfTime.getDocument();
        doc.setDocumentFilter(new MyIntFilter());
    }

    public static Settings getCurrent(GameFrame parent) {
        if (current == null) {
            current = new Settings(parent);
        }
        return current;
    }

    private void setTabulationOrder() {
        Component[] c = new Component[]{chLanguage, ckAddition, ckSoustraction, ckMultiplication,
            ckDivision, tfTable, tfMin, tfMax, ckTime, btnDefaultSetting, btnApply};
        this.setFocusTraversalPolicy(new MyFocusTraversalPolicy(c));
    }

    private void setTraduction() {
        ResourceBundle resBund = parent.resBund;
        lblTitre.setText("<html><u>"+StringUtils.capitalize(resBund.getString("settings")) + "</u></html>");
        ckAddition.setText(StringUtils.capitalize(resBund.getString("addition")));
        ckSoustraction.setText(StringUtils.capitalize(resBund.getString("substraction")));
        ckMultiplication.setText(StringUtils.capitalize(resBund.getString("multiplication")));
        ckAddition.setText(StringUtils.capitalize(resBund.getString("addition")));
        ckSubPos.setText(StringUtils.capitalize(resBund.getString("positiveResult")));
        lblTable.setText(StringUtils.capitalize(resBund.getString("table")));
        lblMin.setText(StringUtils.capitalize(resBund.getString("minNumb")));
        lblMax.setText(StringUtils.capitalize(resBund.getString("maxNumb")));
        ckTime.setText(StringUtils.capitalize(resBund.getString("time")));
        btnDefaultSetting.setText(StringUtils.capitalize(resBund.getString("saveAsDefault")));
        btnApply.setText(StringUtils.capitalize(resBund.getString("apply")));
        byUser = false;
        chLanguage.removeAllItems();
        lstTrans = new ArrayList<Translation>();
        lstTrans.add(new Translation("english", StringUtils.capitalize(resBund.getString("english"))));
        lstTrans.add(new Translation("french", StringUtils.capitalize(resBund.getString("french"))));
        lstTrans.add(new Translation("german", StringUtils.capitalize(resBund.getString("german"))));
        for (Translation t : lstTrans) {
            chLanguage.addItem(t.getTrans());
        }
        this.validate();
    }

    private void updateConf() {
        Configuration conf = parent.getConf();
        conf.setMinNumb(Integer.parseInt(tfMin.getText()));
        conf.setMaxNumb(Integer.parseInt(tfMax.getText()));
        conf.setTime(ckTime.isSelected());
        conf.setSoustractionPos(ckSubPos.isSelected());
        manageTable();
        parent.getConf().setLstOp(updateConfSymboles());
        parent.manageNumberGeneration();
        if (ckTime.isSelected()) {
            conf.setNbSeconds(Integer.parseInt(tfTime.getText()));
            parent.testTimer();
        }else{
            parent.stopTimer();
        }
        conf.setLocal(Language.valueOf(currentTrans.getKey()).getLocale());
        parent.setLabelTime(conf.isTime());
        parent.resetLifesAndPoints();
        parent.showOperations();
                      
        parent.resetArchivement();
        parent.getLstArchivement().resetCurrent();
        parent.showArchivement();
        parent.validate();
    }

    private boolean isValide() {
        try {
            int min = Integer.parseInt(tfMin.getText());
            int max = Integer.parseInt(tfMax.getText());
            if (tfMax.getText().trim().isEmpty() || tfMin.getText().trim().isEmpty() || tfTable.getText().isEmpty()) {
                return false;
            }
            if (min > max) {
                tfMin.setBackground(Color.red);
                tfMax.setBackground(Color.red);
                return false;
            }
            if (!(ckAddition.isSelected() || ckMultiplication.isSelected() || ckSoustraction.isSelected() || ckDivision.isSelected())) {
                return false;
            }
            if (CreateTablesFromString.manageTable(tfTable.getText()) == null) {
                tfTable.setBackground(Color.red);
                return false;
            }
            setDeafaultLayout();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setDeafaultLayout() {
        tfMin.setBackground(Color.white);
        tfMax.setBackground(Color.white);
        tfTable.setBackground(Color.white);
    }

    private void validateButtonSave() {;
        if (isValide()) {
            btnApply.setEnabled(true);
            btnDefaultSetting.setEnabled(true);
        } else {
            btnApply.setEnabled(false);
            btnDefaultSetting.setEnabled(false);
        }
    }

    private ArrayList<String> updateConfSymboles() {
        ArrayList lstSymbole = new ArrayList();
        if (ckAddition.isSelected()) {
            lstSymbole.add("+");
        }
        if (ckMultiplication.isSelected()) {
            lstSymbole.add("*");
        }
        if (ckSoustraction.isSelected()) {
            lstSymbole.add("-");
        }
        if (ckDivision.isSelected()) {
            lstSymbole.add("/");
        }
        return lstSymbole;
    }

    private void setCkSubstractionVisible() {
        if (ckSoustraction.isSelected()) {
            ckSubPos.setVisible(true);
        } else {
            ckSubPos.setVisible(false);
            repaint();
        }
        this.validate();
    }

    private void init() {
        Configuration conf = parent.getConf();
        tfMin.setText(conf.getMinNumb() + "");
        tfMax.setText(conf.getMaxNumb() + "");
        fillTfTable();
        fillSymboles();
        setCkSubstractionVisible();
    }

    private void fillTfTable() {
        ArrayList<Integer> lstTables = parent.getConf().getLstTable();
        StringBuilder str = new StringBuilder();
        str.append(lstTables.get(0));
        for (int i = 1; i < lstTables.size(); i++) {
            str.append(";" + lstTables.get(i));
        }
        tfTable.setText(str.toString());
    }

    private void fillSymboles() {
        ArrayList<String> lstSymboles = parent.getConf().getLstOp();
        ckAddition.setSelected(false);
        ckSoustraction.setSelected(false);
        ckMultiplication.setSelected(false);
        for (String sym : lstSymboles) {
            switch (sym) {
                case "+":
                    ckAddition.setSelected(true);
                    break;
                case "-":
                    ckSoustraction.setSelected(true);
                    break;
                case "*":
                    ckMultiplication.setSelected(true);
                    break;
                case "/":
                    ckDivision.setSelected(true);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void manageTable() {
        ArrayList confTables = CreateTablesFromString.manageTable(tfTable.getText());
        parent.getConf().setLstTable(confTables);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitre = new javax.swing.JLabel();
        chLanguage = new javax.swing.JComboBox<>();
        ckAddition = new javax.swing.JCheckBox();
        ckSoustraction = new javax.swing.JCheckBox();
        ckMultiplication = new javax.swing.JCheckBox();
        ckDivision = new javax.swing.JCheckBox();
        lblTable = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        lblMax = new javax.swing.JLabel();
        tfTable = new javax.swing.JTextField();
        tfMin = new javax.swing.JTextField();
        tfMax = new javax.swing.JTextField();
        ckTime = new javax.swing.JCheckBox();
        tfTime = new javax.swing.JTextField();
        lblExample = new javax.swing.JLabel();
        btnDefaultSetting = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        ckSubPos = new javax.swing.JCheckBox();
        lblPosition = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitre.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitre.setText("Settings");

        chLanguage.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        chLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chLanguage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chLanguageItemStateChanged(evt);
            }
        });
        chLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chLanguageActionPerformed(evt);
            }
        });

        ckAddition.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ckAddition.setText("addition");
        ckAddition.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckItemStateChanged(evt);
            }
        });

        ckSoustraction.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ckSoustraction.setText("substraction");
        ckSoustraction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckItemStateChanged(evt);
                ckSubstrationItemChanged(evt);
            }
        });

        ckMultiplication.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ckMultiplication.setText("multiplication");
        ckMultiplication.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckItemStateChanged(evt);
            }
        });

        ckDivision.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ckDivision.setText("division");
        ckDivision.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckItemStateChanged(evt);
            }
        });

        lblTable.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblTable.setText("Table");

        lblMin.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblMin.setText("Min number");

        lblMax.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblMax.setText("Max number");

        tfTable.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tfTable.setToolTipText("Veuillez insérer les tables selon l'exemple");
        tfTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChanged(evt);
            }
        });

        tfMin.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tfMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMinActionPerformed(evt);
            }
        });
        tfMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChanged(evt);
            }
        });

        tfMax.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tfMax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChanged(evt);
            }
        });

        ckTime.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ckTime.setText("Time");
        ckTime.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckTimeItemStateChanged(evt);
            }
        });

        tfTime.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        tfTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblExample.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblExample.setText("ex(1-3;4)");

        btnDefaultSetting.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        btnDefaultSetting.setText("Save as default");
        btnDefaultSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefaultSettingActionPerformed(evt);
            }
        });

        btnApply.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        ckSubPos.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ckSubPos.setText("positive result");
        ckSubPos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckSubPosItemStateChanged(evt);
            }
        });

        lblPosition.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblPosition.setText("jLabel1");
        lblPosition.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblPositionMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMin)
                            .addComponent(lblTable)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblMax)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfMax)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfTable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblExample))
                    .addComponent(tfMin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnDefaultSetting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(lblTitre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                .addComponent(chLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ckSoustraction)
                        .addGap(170, 170, 170)
                        .addComponent(ckSubPos, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckAddition)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ckTime)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTime, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckMultiplication)
                            .addComponent(ckDivision))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitre)
                    .addComponent(chLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ckAddition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckSoustraction)
                    .addComponent(ckSubPos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckMultiplication)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckDivision)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTable)
                    .addComponent(tfTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExample))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMin)
                    .addComponent(tfMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMax)
                    .addComponent(tfMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckTime)
                    .addComponent(tfTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(lblPosition)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDefaultSetting)
                    .addComponent(btnApply))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        updateConf();
        this.dispose();
    }//GEN-LAST:event_btnApplyActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        init();
    }//GEN-LAST:event_formWindowOpened

    private void ckTimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckTimeItemStateChanged
        if (ckTime.isSelected()) {
            tfTime.setVisible(true);
            tfTime.setText(parent.getConf().getNbSeconds() + "");
            this.validate();
        } else {
            tfTime.setVisible(false);
        }
    }//GEN-LAST:event_ckTimeItemStateChanged

    private void txtChanged(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChanged
        validateButtonSave();
    }//GEN-LAST:event_txtChanged

    private void ckSubPosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckSubPosItemStateChanged
        setCkSubstractionVisible();
    }//GEN-LAST:event_ckSubPosItemStateChanged

    private void btnDefaultSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefaultSettingActionPerformed
        updateConf();
        FileManager.getCurrent().save(parent.getConf().printConf());
        this.dispose();
    }//GEN-LAST:event_btnDefaultSettingActionPerformed

    private void ckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckItemStateChanged
        validateButtonSave();
    }//GEN-LAST:event_ckItemStateChanged

    private void ckSubstrationItemChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckSubstrationItemChanged
        setCkSubstractionVisible();
    }//GEN-LAST:event_ckSubstrationItemChanged

    private void chLanguageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chLanguageItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (byUser) {
                //Récupère la traduction et l'enregistre de côté --> key , trans 
                int ind = chLanguage.getSelectedIndex();
                Translation t = lstTrans.get(ind);
                currentTrans = t;
                //Récupère le Locale en fonction de la key
                Locale loc = Language.valueOf(t.getKey()).getLocale();
                parent.resBund = ResourceBundle.getBundle("MessagesBundle", loc);
                //applique la traduction
                setTraduction();
                byUser = false; // pour empêcher de retourner dans le if 
                chLanguage.setSelectedIndex(lstTrans.indexOf(currentTrans)); // --> problème redéclenche le listeneur
                //applique la traduction dans la fenêtre mère
                parent.setTranslation();
            }
            byUser = true;
        }
    }//GEN-LAST:event_chLanguageItemStateChanged

    private void chLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chLanguageActionPerformed

    }//GEN-LAST:event_chLanguageActionPerformed

    private void tfMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMinActionPerformed
        System.out.println("appuyé");
    }//GEN-LAST:event_tfMinActionPerformed

    private void lblPositionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPositionMouseMoved

    }//GEN-LAST:event_lblPositionMouseMoved

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        lblPosition.setText("X = " + evt.getX() + " Y = " + (evt.getY() - 24));
        lblPosition.validate();
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnDefaultSetting;
    private javax.swing.JComboBox<String> chLanguage;
    private javax.swing.JCheckBox ckAddition;
    private javax.swing.JCheckBox ckDivision;
    private javax.swing.JCheckBox ckMultiplication;
    private javax.swing.JCheckBox ckSoustraction;
    private javax.swing.JCheckBox ckSubPos;
    private javax.swing.JCheckBox ckTime;
    private javax.swing.JLabel lblExample;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblTable;
    private javax.swing.JLabel lblTitre;
    private javax.swing.JTextField tfMax;
    private javax.swing.JTextField tfMin;
    private javax.swing.JTextField tfTable;
    private javax.swing.JTextField tfTime;
    // End of variables declaration//GEN-END:variables
}
