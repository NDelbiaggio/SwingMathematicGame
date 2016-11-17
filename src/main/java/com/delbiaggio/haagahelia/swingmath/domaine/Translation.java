/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.domaine;

import java.util.Objects;

/**
 *
 * @author delbiaggionicolas
 */
public class Translation {
    
    private String key;
    private String trans;

    public Translation(String key, String trans) {
        this.key = key;
        this.trans = trans;
    }

    public Translation(String key) {
        this.key = key;
    }
        

    public String getKey() {
        return key;
    }

    public String getTrans() {
        return trans;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Translation other = (Translation) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }
    
    
}
