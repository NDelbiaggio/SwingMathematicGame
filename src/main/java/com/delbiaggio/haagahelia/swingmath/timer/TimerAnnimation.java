package com.delbiaggio.haagahelia.swingmath.timer;

import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class TimerAnnimation extends TimerTask {

    private JLabel l;
    private int x = 16;
    private int y = 421;
    private int width = 60;
    private int length = 45;
    
    
    public TimerAnnimation(JLabel l) {
        this.l = l;
        this.width = l.getWidth();
        this.length = l.getHeight();
        l.setVisible(true);
    }

    @Override
    public void run() {
        if (x>= 680) {
           this.cancel();
        }
        x+= 3;
        l.setBounds(x, y, width, length);
    }
    
}
