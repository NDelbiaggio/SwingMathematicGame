package com.delbiaggio.haagahelia.swingmath.tools.imageReader;

import com.delbiaggio.haagahelia.swingmath.GameFrame;
import com.delbiaggio.haagahelia.swingmath.controller.ListLabel;
import com.delbiaggio.haagahelia.swingmath.vue.ShowOperationsImage;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class LoaderImage {

    private String BACKGROUND = "mainBackground9.jpg";
    private String IMG_RES = "ImgResources";
    private String IMG_BORDER = "backgroundBorder.png";

    private GameFrame parent;
    ResourceBundle resBundImg;

    public LoaderImage(GameFrame parent) {
        this.parent = parent;
    }

    public void setLayout() {
        resBundImg = ResourceBundle.getBundle(IMG_RES);
        loadOperations();
        loadArchivements();
        JLabel border = getCustomisedJLabel(IMG_BORDER, -120, -100, 1024, 805);
        parent.add(border);
        loadAnnimations();
        loadAnnimationCachee();
        parent.add(getCustomisedJLabel(BACKGROUND, -120, -100, 1024, 805));
    }

    private void loadOperations() {
        ArrayList<JLabel> lstOp = parent.getLstOperations();
        JLabel plus = getCustomisedJLabel(resBundImg.getString("+"), 271, 376, 50, 50);
        JLabel minus = getCustomisedJLabel(resBundImg.getString("-"), 345, 376, 50, 50);
        JLabel division = getCustomisedJLabel(resBundImg.getString("/"), 400, 376, 50, 50);
        JLabel multiplication = getCustomisedJLabel(resBundImg.getString("*"), 460, 376, 50, 50);
        addInArrayAndParent(lstOp, plus);
        addInArrayAndParent(lstOp, minus);
        addInArrayAndParent(lstOp, multiplication);
        addInArrayAndParent(lstOp, division);

    }

    private void loadArchivements() {
        ListLabel lstArch = parent.getLstArchivement();
        int x = 600;
        int y = 106;
        JLabel regle = getCustomisedJLabel("regle.png", x, y, 50, 50);
        JLabel compas = getCustomisedJLabel("compas.png", x, y, 50, 89);
        JLabel tableau = getCustomisedJLabel("tableau.png", x, y, 50, 89);
        JLabel calculette = getCustomisedJLabel("calculatrice.png", x, y, 50, 43);
        JLabel bag = getCustomisedJLabel("bag.png", x, y, 60, 60);
        addInArrayAndParent(lstArch, regle);
        addInArrayAndParent(lstArch, compas);
        addInArrayAndParent(lstArch, tableau);
        addInArrayAndParent(lstArch, calculette);
        addInArrayAndParent(lstArch, bag);
        ShowOperationsImage.getCurrent().hideLstLabels(lstArch);
    }

    private void loadAnnimations() {
        ListLabel lstAnn = parent.getLstAnnimation();
        JLabel amb = getCustomisedJLabel("ambulance.png", 0, 0, 60, 45);
        JLabel pompier = getCustomisedJLabel("pompier.png", 0, 0, 60, 45);
        JLabel moto = getCustomisedJLabel("moto.png", 0, 0, 60, 27);
        JLabel police = getCustomisedJLabel("police2.png", 0, 0, 80, 28);
        JLabel bike = getCustomisedJLabel("bike2.png", 0, 0, 60, 35);
        JLabel spacial = getCustomisedJLabel("navette.png", 0, 0, 80, 63);
        addInArrayAndParent(lstAnn, bike);
        addInArrayAndParent(lstAnn, moto);
        addInArrayAndParent(lstAnn, police);
        addInArrayAndParent(lstAnn, amb);
        addInArrayAndParent(lstAnn, pompier);
        addInArrayAndParent(lstAnn, spacial);
        ShowOperationsImage.getCurrent().hideLstLabels(lstAnn);
    }
    
    private void loadAnnimationCachee(){
        JLabel grenouille = getCustomisedJLabel("grenouille.png", 659, 551, 80, 72);
        parent.add(grenouille);
        parent.setAnnGrenouille(grenouille);
    }

    public ListLabel<JLabel> getLstLifesImage(int nbLifes) {
        ListLabel<JLabel> lstLifes = new ListLabel<>();
        for (int i = 0; i < nbLifes; i++) {
            JLabel back = new JLabel(ImageIconReader.getCurrent().readImageIcon("pencil3.png"));
            back.setBounds(230 + (i * 30), 221, 50, 38);
            parent.add(back);
            lstLifes.add(back);
        }
        return lstLifes;
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
