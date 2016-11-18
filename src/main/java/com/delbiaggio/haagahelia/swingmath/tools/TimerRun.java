package com.delbiaggio.haagahelia.swingmath.tools;

import com.delbiaggio.haagahelia.swingmath.GameFrame;


import java.util.TimerTask;
import javax.swing.JLabel;

public class TimerRun extends TimerTask {
    private JLabel lbl;
    private int time;
    private GameFrame gf;
    
    public TimerRun(GameFrame gf,JLabel lbl,int time) {
        this.lbl = lbl;
        this.time = time;
        this.gf = gf;
    }    
    
    @Override
    public void run() { 
        lbl.setText("Time: " + time);
        if (time<= 0 ) {
            this.cancel();
            gf.decreaseLife();
        }
        time--;
    }    
}
