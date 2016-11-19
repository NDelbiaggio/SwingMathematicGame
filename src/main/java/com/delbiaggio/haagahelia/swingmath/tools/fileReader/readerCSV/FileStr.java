package com.delbiaggio.haagahelia.swingmath.tools.fileReader.readerCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Lecture d'un fichier texte dans un String[] Ecriture d'un String[] dans un
 * fichier texte
 */
public class FileStr {

    private static final int EOF = -1;

    private static FileStr current = null;

    public static FileStr getCurrent() {
        if (current == null) {
            current = new FileStr();
        }
        return current;
    }

    /**
     * Lecture d'un fichier texte dans un String[].
     *
     * @param fileName Le nom du fichier texte.
     * @return le String[] contenant les lignes du fichier texte.
     */
    public ArrayList<String> readFromResources(String fileName) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            ArrayList<String> res = new ArrayList();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) != '#') {
                    res.add(line);
                }
            }
            inputStream.close();
            return res;
        } catch (FileNotFoundException e0) {
            e0.printStackTrace();
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    } // readFromResources

    public ArrayList<String> read(String fileName) {
        try {
            FileInputStream f = new FileInputStream(fileName);
            StringBuffer b = new StringBuffer(f.available());
            int c = f.read();
            while (c != EOF) {
                b.append((char) c);
                c = f.read();
            }
            f.close();
            StringTokenizer sT = new StringTokenizer(b.toString(), "\r\n");
            ArrayList<String> res = new ArrayList();
            while (sT.hasMoreTokens()) {
                String st = sT.nextToken();
                if (st.charAt(0) != '#') {
                    res.add(st);
                }
            }
            return res;
        } catch (FileNotFoundException e0) {
            e0.printStackTrace();
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    } // readFromResources

    /**
     * Ecriture d'un String[] dans un fichier texte.
     *
     * @param fileName Le nom du fichier texte.
     * @param str le String[] contenant les lignes à écrire.
     */
    /*public String write (String fileName, String content) {
      return null;
  } // write*/
 /*
    @parameter path 
    return true if a file exists in the parameter path
     */
    public boolean fileExist(String path) {
        File f = new File(path);
        return f.exists();
    }

} // FileStr
