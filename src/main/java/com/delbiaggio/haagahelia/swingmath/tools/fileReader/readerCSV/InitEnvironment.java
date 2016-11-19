package com.delbiaggio.haagahelia.swingmath.tools.fileReader.readerCSV;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class InitEnvironment {

    String CONF = "conf.txt";
    private static InitEnvironment current = null;

    private InitEnvironment() {
    }

    public static InitEnvironment getCurreEnvironment() {
        if (current == null) {
            current = new InitEnvironment();
        }
        return current;
    }

    /*
        Create a File and add content
     */
    public void initConf(String path, String content) {
        try {
            System.out.println("Path: " + path);
            File f = new File(path);
            f.createNewFile();
            FileWriter fw = new FileWriter(f, false);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Path: " + path);
            e.printStackTrace();
        }
    }

    /*
        Return the path of the Directory in which is the jar
     */
    public String getDirectoryOfJar() {
        String test = new File(CONF).getAbsolutePath();
        StringTokenizer str = new StringTokenizer(test, File.separator);
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        while (str.countTokens() > 1) {
            sb.append(str.nextToken() + File.separator);
        }
        return sb.toString();
    }
}
