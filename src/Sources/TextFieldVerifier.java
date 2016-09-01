/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author mmh
 */
public class TextFieldVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        if (!(input instanceof JTextField)) {
            return true;
        }
        return isValidText((JTextField) input);
    }

    protected boolean isValidText(JTextField field) {
        return field.getText() != null;
    }

}
