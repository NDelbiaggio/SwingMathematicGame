package com.delbiaggio.haagahelia.swingmath;

import java.util.Locale;
/**
 *
 * @author delbiaggionicolas
 */

public enum Language {
    english("english",Locale.US),
    us("us",Locale.US),
    french("french",Locale.FRANCE),
    deutsch("deutsch",Locale.GERMANY);
    
    private String language;
    private Locale loc;
    
    Language(String language,Locale loc){
        this.language = language;
        this.loc = loc;
    }
    
    public Locale getLocale(){
//       String abb = language.substring(0, 2);
//       String lang = abb.toLowerCase();
//       String country = abb.toUpperCase();
        //System.out.println("lang "+ lang + "country "+country );
       //return new Locale(lang,country);
       return this.loc;
    }
    
}
