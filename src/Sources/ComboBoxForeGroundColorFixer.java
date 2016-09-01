/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.awt.Color;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;

/**
 *
 * @author mmh
 */
public class ComboBoxForeGroundColorFixer {

    /**
     *
     * @param combo combobox to be changed
     * @param colorType color to be set.
     * 
     * <br>
     * <br>currently
     * <br>1 to java.awt.Color(4, 9, 250)
     * <br>2 to java.awt.Color(190, 121, 0)
     * <br>3 to java.awt.Color(139, 0,139)
     * <br>4 to java.awt.Color(3, 130, 42)
     * 
     */
    public static void fixColor(JComboBox combo, int colorType) {
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void setForeground(Color fg) {
                switch (colorType) {
                    case 1:
                        super.setForeground(new java.awt.Color(4, 9, 250));
                        break;
                    case 2:
                        super.setForeground(new java.awt.Color(128, 0, 0));
                        break;
                    case 3:
                        super.setForeground(new java.awt.Color(230, 0, 230));
                        break;
                    case 4:
                        super.setForeground(new java.awt.Color(3, 130, 42));
                        break;
                    default:
                        break;
                }
            }

        });
    }

}
