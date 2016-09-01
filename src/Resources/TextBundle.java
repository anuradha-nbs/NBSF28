/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created on Nov 10, 2015
 *
 * @author mmh
 */
public class TextBundle {

    private Locale lang;
    private ResourceBundle textBundle;

    public TextBundle(String lang, String path) {
        FileInputStream inputStream = null;
        try {
            this.lang = new Locale(lang);
            inputStream = new FileInputStream(path);
            this.textBundle = new PropertyResourceBundle(inputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextBundle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextBundle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(TextBundle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Locale getLang() {
        return lang;
    }

    public void setLang(Locale lang) {
        this.lang = lang;
    }

    public ResourceBundle getTextBundle() {
        return textBundle;
    }

    public void setTextBundle(ResourceBundle textBundle) {
        this.textBundle = textBundle;
    }

}
