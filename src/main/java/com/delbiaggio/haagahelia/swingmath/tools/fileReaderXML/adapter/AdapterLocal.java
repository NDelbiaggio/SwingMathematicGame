/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.tools.fileReaderXML.adapter;

import java.util.Locale;
import java.util.StringTokenizer;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author delbiaggionicolas
 */
public class AdapterLocal extends XmlAdapter<String,Locale> {

    @Override
    public Locale unmarshal(String v) throws Exception {
        StringTokenizer str = new StringTokenizer(v, ";");
        Locale l = new Locale(str.nextToken(), str.nextToken());
        return l;
    }

    @Override
    public String marshal(Locale v) throws Exception {
        return v.getCountry() + ";" + v.getLanguage();
    }

}
