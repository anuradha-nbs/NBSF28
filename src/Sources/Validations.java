/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.awt.event.KeyEvent;

/**
 * created on Nov 13, 2015
 *
 * @author mmh
 */
public class Validations {

    private static boolean state = false;

    public static boolean isValiedNumber(KeyEvent evt) {
        if (Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.') {
            state = true;
        }else{
            state = false;
        }
        return state;
    }
}
