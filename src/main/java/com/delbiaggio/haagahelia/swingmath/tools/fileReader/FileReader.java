package com.delbiaggio.haagahelia.swingmath.tools.fileReader;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 *
 * @author delbiaggionicolas
 */
public class FileReader {

    public BufferedInputStream getBufferedInputSream(String fileName) {
        try {
            InputStream s = getClass().getClassLoader().getResourceAsStream(fileName);
            
            BufferedInputStream b = new BufferedInputStream(s);
            return b;
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }
}
