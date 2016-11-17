/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.tools;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

/**
 * Outil de gestion de l'ordre de focus d'un ensemble de composants
 *
 * @author Peter DAEHNE - HEG-Genève
 * @version 1.0
 */
public class MyFocusTraversalPolicy extends FocusTraversalPolicy {

    private static final int SUIV = +1, PREC = -1;

    private Component[] comp;

    /* La liste des composants, dans l'ordre du focus */

    /**
     * Constructeur. Le paramètre comp contient la liste des composants dans
     * l'ordre du focus.
     */
    public MyFocusTraversalPolicy(Component[] comp) {
        this.comp = comp;
    }

    /**
     * Retourne l'indice du composant c, 0 si inexistant
     */
    private int indComp(Component c) {
        for (int k = 0; k < comp.length; k++) {
            if (comp[k] == c) {
                return k;
            }
        }
        return 0;
    } // indComp

    /**
     * Retourne le prochain composant qui est "enabled", circulairement à partir
     * de l'indice k + incr (incr = SUIV ou PREC). Si aucun, retourne le
     * premier.
     */
    private Component nextEnabled(int k, int incr) {
        k = (k + comp.length + incr) % comp.length;
        for (int cpt = 0; cpt < comp.length; cpt++) {
            if (comp[k].isEnabled()) {
                return comp[k];
            }
            k = (k + comp.length + incr) % comp.length;
        }
        return comp[0];
    } // nextEnabled

    @Override
    public Component getComponentAfter(Container cont, Component c) {
        return nextEnabled(indComp(c), SUIV);
    }

    @Override
    public Component getComponentBefore(Container cont, Component c) {
        return nextEnabled(indComp(c), PREC);
    }

    @Override
    public Component getFirstComponent(Container cont) {
        return nextEnabled(comp.length - 1, SUIV);
    }

    @Override
    public Component getLastComponent(Container cont) {
        return nextEnabled(0, PREC);
    }

    @Override
    public Component getDefaultComponent(Container cont) {
        return getFirstComponent(cont);
    }

} // MyFocusTraversalPolicy
