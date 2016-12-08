package com.delbiaggio.haagahelia.swingmath.timer;

import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author delbiaggionicolas
 */
public class TimerGameOver extends TimerTask {

    private int time = 0;
    private int nbPoints;
    private JLabel lblGameOver;
    
    
    public TimerGameOver(int nbPoints,JLabel lblGameOver){
        this.nbPoints = nbPoints;
        this.lblGameOver = lblGameOver;
        lblGameOver.setText("Game Over! Score: " + nbPoints);
        lblGameOver.setVisible(true);
    }
    
    @Override
    public void run() {
        time ++;
        if (time == 3) {
            lblGameOver.setText("Try again!");
        }else if (time == 5) {
            lblGameOver.setVisible(false);
            cancel();
        }
    }
    
}
