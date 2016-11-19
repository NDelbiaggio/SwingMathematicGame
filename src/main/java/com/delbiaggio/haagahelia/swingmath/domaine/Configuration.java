package com.delbiaggio.haagahelia.swingmath.domaine;


import com.delbiaggio.haagahelia.swingmath.controller.MyList;
import com.delbiaggio.haagahelia.swingmath.tools.fileReaderXML.adapter.AdapterLocal;
import java.util.ArrayList;
import java.util.Locale;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = { "minNumb", "maxNumb", "time","nbSeconds","soustractionPos","local","lstOp","lstTable"})
@XmlRootElement(name = "Configuration")
public class Configuration {
    private MyList<String> lstOp;
    private MyList<Integer> lstTable;
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
        lstOp = new MyList<String>();
        lstTable = new MyList<Integer>();
        this.soustractionPos = sousPos;
        this.local = local;
    }

    public Locale getLocal() {
        return local;
    }

    @XmlJavaTypeAdapter(AdapterLocal.class)
    public void setLocal(Locale local) {
        this.local = local;
    }

    public boolean getSoustractionPos() {
        return soustractionPos;
    }

    @XmlElement(name = "Conf_soustractionPos")
    public void setSoustractionPos(boolean soustractionPos) {
        this.soustractionPos = soustractionPos;
    }

    public int getNbSeconds() {
        return nbSeconds;
    }

    @XmlElement(name = "Conf_nbSeconds")
    public void setNbSeconds(int nbSeconds) {
        this.nbSeconds = nbSeconds;
    }
    
    public void addOperation(String op){
        lstOp.getList().add(op);
    }

    public void addTable(Integer table){
        lstTable.getList().add(table);
    }

    public MyList<String> getLstOp() {
        return lstOp;
    }

    @XmlElement(name = "Conf_lstOperation")
    public void setLstOp(MyList<String> lstOp) {
        this.lstOp = lstOp;
    }

    public MyList<Integer> getLstTable() {
        return lstTable;
    }

    @XmlElement(name = "Conf_lstTables")
    public void setLstTable(MyList<Integer> lstTable) {
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

    public boolean getTime() {
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
        for (Integer n : lstTable.getList()) {
            st.append(n+";");
        }
        st.append("\n#list Symboles\n");
        for (String op : lstOp.getList()) {
            st.append(op+";");
        }
        st.append("\n#time \n");
        if (getTime()) {
            st.append("true");    
        }else{
            st.append("false");
        }
        st.append("\n#number of seconds\n");
        st.append(getNbSeconds()+"\n");
        st.append("#Result all the time positive for substraction\n");
        if (getSoustractionPos()) {
            st.append("true");    
        }else{
            st.append("false");
        }
        st.append("#Language & country\n");
        st.append(local.getLanguage()+";"+local.getCountry());
        return st.toString();
    }

}
