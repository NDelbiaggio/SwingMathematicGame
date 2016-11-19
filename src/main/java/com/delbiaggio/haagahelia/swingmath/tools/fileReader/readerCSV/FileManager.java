package com.delbiaggio.haagahelia.swingmath.tools.fileReader.readerCSV;


import com.delbiaggio.haagahelia.swingmath.domaine.Configuration;
import com.delbiaggio.haagahelia.swingmath.tools.CreateTablesFromString;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class FileManager {

    private static String FILE = "conf.txt";
    private static FileManager current = null;

    public static FileManager getCurrent() {
        if (current == null) {
            current = new FileManager();
        }
        return current;
    }

    /*
        Create and return a Configuration from an ArrayList
     */
    public Configuration getInitialConf() {
        ArrayList<String> lines = getTextInArrayList();
        int minNum = Integer.parseInt(lines.get(0));
        int maxNum = Integer.parseInt(lines.get(1));
        int nbSeconds = Integer.parseInt(lines.get(5));
        boolean time = isTime(lines.get(4));
        boolean resultPos = lines.get(6).equals("true");
        String [] local = lines.get(7).split(";");
        Locale loc = new Locale(local[0],local[1]);
        Configuration conf = new Configuration(minNum, maxNum, time, nbSeconds, resultPos,loc);
        conf.setLstTable(CreateTablesFromString.convertStrToArray(lines.get(2)));
        conf.setLstOp(getSymboles(lines.get(3)));
        save(conf.printConf());
        return conf;
    }

    private ArrayList<String> getSymboles(String line) {
        StringTokenizer sT = new StringTokenizer(line, ";");
        ArrayList<String> lstSymboles = new ArrayList<>();
        while (sT.hasMoreTokens()) {
            lstSymboles.add(sT.nextToken());
        }
        return lstSymboles;
    }

    private boolean isTime(String time) {
        return time.equals("true");
    }

    public void save(String content) {
        InitEnvironment env = InitEnvironment.getCurreEnvironment();
        String path = env.getDirectoryOfJar() + FILE;
        env.initConf(path, content);
    }

    /*
        Return true if the file conf.txt exists outside of the jar
     */
    private boolean fileExist() {
        InitEnvironment env = InitEnvironment.getCurreEnvironment();
        return FileStr.getCurrent().fileExist(env.getDirectoryOfJar() + FILE);
    }

    /*
        Return an ArrayList with relevant lines for a configuration
        Read the file outside of the jar if it exists, 
        otherwise it will read the file from the resources
     */
    private ArrayList<String> getTextInArrayList() {
        ArrayList<String> lines;
        if (fileExist()) {
            InitEnvironment env = InitEnvironment.getCurreEnvironment();
            lines = FileStr.getCurrent().read(env.getDirectoryOfJar() + FILE);
        } else {
            lines = FileStr.getCurrent().readFromResources(FILE);
        }
        return lines;
    }
}
