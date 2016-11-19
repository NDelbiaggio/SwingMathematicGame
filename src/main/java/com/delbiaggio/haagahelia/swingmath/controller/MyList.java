/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.controller;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author delbiaggionicolas
 */
public class MyList<T> {

    List<T> list = new ArrayList<T>();
    
    public MyList(){
        
    }
    
    public MyList(List<T> list){
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    @XmlElement(name = "Value")
    public void setList(List<T> list) {
        this.list = list;
    }    
}
