/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author mmh
 */
public class JTextFieldRemoveNull {
    public static void removeNull(JTextField field){
        if (field.getText().equals("null")) {
            field.setText("");
        }
    }
    public static void removeNull(JLabel label){
        if (label.getText().equals("null")) {
            label.setText("");
        }
    }
}
