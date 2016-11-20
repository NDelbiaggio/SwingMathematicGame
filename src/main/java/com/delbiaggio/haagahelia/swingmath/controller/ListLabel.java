/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.controller;

import java.util.ArrayList;

/**
 *
 * @author delbiaggionicolas
 */
public class ListLabel<JLabel> extends ArrayList<JLabel> {

    int current = 0;

    public JLabel getNext() {
        int size = this.size();
        if (current  < size) {
            JLabel l = get(current);
            current++;
            return l;

        }
        return get(size() - 1);
    }

    public JLabel getNextWithCycle() {
        int size = this.size();
        JLabel l;
        if (current < size) {
            l = get(current);
            current++;
            return l;

        }else{
            current = 0;
            return get(current);    
        }
    }

    public JLabel getPrevious() {
        if (current - 1 < 0) {
            return get(0);
        }
        return get(current - 1);
    }

    public void resetCurrent() {
        current = 0;
    }
    
}
