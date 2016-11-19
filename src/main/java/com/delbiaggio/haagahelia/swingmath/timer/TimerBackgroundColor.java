package com.delbiaggio.haagahelia.swingmath.timer;


import java.awt.Color;
import java.util.TimerTask;
import javax.swing.JTextField;

public class TimerBackgroundColor extends TimerTask {

    private JTextField tf;
    private double time;
    private Color c;
    private boolean deb = true;
    
    public TimerBackgroundColor(JTextField tf,double time,Color c) {
        this.tf = tf;
        this.time = time;
        this.c = c;
    }

    @Override
    public void run() {
        time-= 0.5;
        if (deb) {
            tf.setBackground(c);    
            deb = false;
        }        
        if (time<=0) {
            tf.setBackground(Color.white);
        }
    }
    
    
    
}
