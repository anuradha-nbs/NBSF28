/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import javax.swing.DefaultComboBoxModel;
import org.nbs.components.HiddenItemComboBox;

/**
 *
 * @author mmh
 */
public class HiddenItemComboBoxSelector {
    public  static void setSelectedItem(HiddenItemComboBox comboBox,int id,String description){
        ((DefaultComboBoxModel) comboBox.getModel()).setSelectedItem(new org.nbs.resources.Item(id, description));
    }
}
