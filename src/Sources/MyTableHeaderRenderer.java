/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyTableHeaderRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        setText(value.toString());
//        setToolTipText((String) value);
setMaximumSize(new Dimension(table.getColumnModel().getColumn(column).getMaxWidth(), table.getTableHeader().getHeight()));
        setHorizontalTextPosition(JLabel.CENTER);
        return this;
    }
}
