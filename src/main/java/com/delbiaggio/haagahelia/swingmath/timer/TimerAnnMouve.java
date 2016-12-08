package com.delbiaggio.haagahelia.swingmath.timer;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import java.lang.Math;

/**
 *
 * @author delbiaggionicolas
 */
public class TimerAnnMouve extends TimerTask {

    private JLabel l;
    private int x;
    private int y;
    private int finX = 580;
    private int finY = 501-24; 
    private int debX ;
    private int debY;
    boolean sens;
    private int i = 0;

    public TimerAnnMouve(JLabel l) {
        l.setBounds(659, 551, 80, 72);
        this.l = l;
        this.x = l.getX();
        this.y = l.getY();
        this.debX = l.getX();
        this.debY = l.getY()-24;
        this.sens = true;
    }
    
    @Override
    public void run() {
        if (sens) {
            if (x > finX && y > finY) {
                x--;
                y--;
            }else{
                sens = false;
            }
        }else{
            if (x < debX && y < debY) {
                x++;
                y++;
            }else{
                sens = true;
                
                cancel();
            }
        }
        l.setBounds(x, y, l.getWidth(), l.getHeight());        
    }
    
    private void round(){
        Point rayon = new Point(0,-200);
        double x = (rayon.x * java.lang.Math.cos(i)) - (rayon.y * Math.sin(i));
        double y = (rayon.x * Math.sin(i)) + (rayon.y * Math.cos(i));
        
        this.x = (int)x;
        this.y=(int)y;
//        System.out.println("X = " + x);
//        System.out.println("Y = " + y);
        i+=1;
    }
}
