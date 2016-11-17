package com.delbiaggio.haagahelia.swingmath.domaine;


import java.util.ArrayList;
import java.util.Locale;

public class Configuration {
    private ArrayList<String> lstOp;
    private ArrayList<Integer> lstTable; //hésitation du cast
    private int minNumb;
    private int maxNumb;
    private boolean time;
    private int nbSeconds;
    private boolean soustractionPos;
    private Locale local;

    public Configuration() {
    }
    
    public Configuration(int minNumb, int maxNumb, boolean time,int nbSeconds,boolean sousPos,Locale local) {
        this.minNumb = minNumb;
        this.maxNumb = maxNumb;
        this.time = time;
        this.nbSeconds = nbSeconds;
        lstOp = new ArrayList<String>();
        lstTable = new ArrayList<Integer>();
        this.soustractionPos = sousPos;
        this.local = local;
    }

    public Locale getLocal() {
        return local;
    }

    public void setLocal(Locale local) {
        this.local = local;
    }

    public boolean isSoustractionPos() {
        return soustractionPos;
    }

    public void setSoustractionPos(boolean soustractionPos) {
        this.soustractionPos = soustractionPos;
    }

    public int getNbSeconds() {
        return nbSeconds;
    }

    public void setNbSeconds(int nbSeconds) {
        this.nbSeconds = nbSeconds;
    }
    
    public void addOperation(String op){
        lstOp.add(op);
    }

    public void addTable(Integer table){
        lstTable.add(table);
    }

    public ArrayList getLstOp() {
        return lstOp;
    }

    public void setLstOp(ArrayList lstOp) {
        this.lstOp = lstOp;
    }

    public ArrayList<Integer> getLstTable() {
        return lstTable;
    }

    public void setLstTable(ArrayList<Integer> lstTable) {
        this.lstTable = lstTable;
    }

    public int getMinNumb() {
        return minNumb;
    }

    public void setMinNumb(int minNumb) {
        this.minNumb = minNumb;
    }

    public int getMaxNumb() {
        return maxNumb;
    }

    public void setMaxNumb(int maxNumb) {
        this.maxNumb = maxNumb;
    }

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }
    
    public String printConf(){
        StringBuilder st = new StringBuilder();
        st.append("#Minimum Number\n");
        st.append(getMinNumb()+"\n");
        st.append("#Maximum Number \n");
        st.append(getMaxNumb() + "\n");
        st.append("#Table \n");
        for (Integer n : lstTable) {
            st.append(n+";");
        }
        st.append("\n#list Symboles\n");
        for (String op : lstOp) {
            st.append(op+";");
        }
        st.append("\n#time \n");
        if (isTime()) {
            st.append("true");    
        }else{
            st.append("false");
        }
        st.append("\n#number of seconds\n");
        st.append(getNbSeconds()+"\n");
        st.append("#Result all the time positive for substraction\n");
        if (isSoustractionPos()) {
            st.append("true");    
        }else{
            st.append("false");
        }
        st.append("#Language & country\n");
        st.append(local.getLanguage()+";"+local.getCountry());
        return st.toString();
    }

}
