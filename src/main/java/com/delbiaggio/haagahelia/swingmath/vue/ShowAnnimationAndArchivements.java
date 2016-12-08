package com.delbiaggio.haagahelia.swingmath.vue;

import com.delbiaggio.haagahelia.swingmath.controller.ListLabel;
import com.delbiaggio.haagahelia.swingmath.timer.TimerAnnimation;
import java.util.Timer;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class ShowAnnimationAndArchivements {
    private static ShowAnnimationAndArchivements current = null;
    
    private ShowAnnimationAndArchivements(){
        
    }
    
    public static ShowAnnimationAndArchivements getCurrent(){
        if (current == null) {
            current = new ShowAnnimationAndArchivements();
        }
        return current;
    }    
    
    public void showArchivement(ListLabel<JLabel> lstArchivement,ListLabel<JLabel> lstAnnimation, int nbPoints) {
        if (nbPoints % 10 == 0) {
            showAnnimation(lstAnnimation);
        }
        switch (nbPoints) {
            case 1:
                lstArchivement.getNext().setVisible(true);
                break;
            case 5:
                lstArchivement.getPrevious().setVisible(false);
                lstArchivement.getNext().setVisible(true);
                break;
            case 10:
                lstArchivement.getPrevious().setVisible(false);
                lstArchivement.getNext().setVisible(true);
                break;
            case 25:
                lstArchivement.getPrevious().setVisible(false);
                lstArchivement.getNext().setVisible(true);
                break;
            case 50:
                lstArchivement.getPrevious().setVisible(false);
                lstArchivement.getNext().setVisible(true);
                break;
            default:
                break;
        }
    }

    public void showAnnimation(ListLabel<JLabel> lstAnnimation) {
        Timer tAnn = new Timer();
        TimerAnnimation runAnn = new TimerAnnimation(lstAnnimation.getNextWithCycle());
        tAnn.scheduleAtFixedRate(runAnn, 0, 15);
    }
    
}
