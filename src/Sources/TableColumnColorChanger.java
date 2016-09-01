/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author mmh
 */
public class TableColumnColorChanger {

    public static void changeColumnColor(int index, JTable table,int type) {
        TableColumn tm = table.getColumnModel().getColumn(index);
        switch (type) {
            case 1:
            case 2:
                tm.setCellRenderer(new ColorColumnRenderer(Color.RED,type));
                break;
            case 3:
                tm.setCellRenderer(new ColorColumnRenderer(Color.BLUE,Color.RED));
                break;
            default:
                break;
        }
    }
}

class ColorColumnRenderer extends DefaultTableCellRenderer {

    Color bkgndColor, fgndColor;

    public ColorColumnRenderer(Color bkgnd, Color foregnd) {
        super();
        bkgndColor = bkgnd;
        fgndColor = foregnd;
    }
    public ColorColumnRenderer(Color gnd,int type){
        super();
        switch (type) {
            case 1:
                bkgndColor = gnd;
                break;
            case 2:
                fgndColor = gnd;
                break;
            default:
                break;
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        cell.setBackground(bkgndColor);
        cell.setForeground(fgndColor);

        return cell;
    }
}
