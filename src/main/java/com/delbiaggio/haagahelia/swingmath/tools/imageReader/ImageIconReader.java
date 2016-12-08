package com.delbiaggio.haagahelia.swingmath.tools.imageReader;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author delbiaggionicolas
 */
public class ImageIconReader {

    private static ImageIconReader current = null;

    private ImageIconReader() {

    }

    public static ImageIconReader getCurrent() {
        if (current == null) {
            current = new ImageIconReader();
        }
        return current;
    }

    public ImageIcon readImageIcon(String file) {
        BufferedImage bi = null;
        try {
            InputStream s = getClass().getClassLoader().getResourceAsStream(file);
            bi = ImageIO.read(s);
            ImageIcon img = new ImageIcon(bi);
            return img;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
        }
        return null;
    }
}
