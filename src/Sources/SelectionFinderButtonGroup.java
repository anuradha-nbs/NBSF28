/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author mmh
 */
public class SelectionFinderButtonGroup extends ButtonGroup {

    private JRadioButton SelectedRadioButton;
    private int selectedIndex;

    public JRadioButton getSelectedRadioButton() {
        selectedIndex = 0;
        Enumeration<AbstractButton> elements = getElements();
        while (elements.hasMoreElements()) {
            AbstractButton nextElement = elements.nextElement();
            selectedIndex++;
            if (nextElement.isSelected()) {
                SelectedRadioButton = (JRadioButton) nextElement;

                return SelectedRadioButton;
            }
        }
        return SelectedRadioButton;
    }

    public void setSelectedRadioButton(int index) {
        int counter = 1;
        Enumeration<AbstractButton> elements = getElements();
        while (elements.hasMoreElements()) {
            AbstractButton nextElement = elements.nextElement();
            if (counter == index) {
                nextElement.setSelected(true);
            }
            counter++;
        }
    }
/**
 * get the selected radio button index
 * @return int selectedIndex - Starts from 1
 */
    public int getSelectedIndex() {
        getSelectedRadioButton();
        return selectedIndex;
    }

}
