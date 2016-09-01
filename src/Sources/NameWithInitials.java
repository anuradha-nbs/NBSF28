/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author mmh
 */
public class NameWithInitials {
    public static void createNameWithInitials(String text, JComponent initText, JComponent lastNameText) {
        String[] names = text.split(" ");
        String inits = "";
        for (int i = 0; i < names.length; i++) {
            if (i != names.length-1) {
                inits += names[i].substring(0,1).toUpperCase()+".";
            }
        }
        if (initText instanceof JLabel) {
            ((JLabel)initText).setText(inits);
        }else if(initText instanceof JTextComponent){
            ((JTextComponent)initText).setText(inits);
        }
        if (lastNameText instanceof JLabel) {
            ((JLabel)lastNameText).setText(names[names.length-1].toUpperCase());
        }else if(lastNameText instanceof JTextComponent){
            ((JTextComponent)lastNameText).setText(names[names.length-1].toUpperCase());
        }
    }
}
