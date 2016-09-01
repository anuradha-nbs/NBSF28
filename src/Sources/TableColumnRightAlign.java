/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author mmh
 */
public class TableColumnRightAlign {

    public static void align(JTable table, int index) {
        if (table.getColumnModel().getColumn(index).getCellRenderer() != null) {
            DefaultTableCellRenderer rightRenderer = (DefaultTableCellRenderer) table.getColumnModel().getColumn(index).getCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            table.getColumnModel().getColumn(index).setCellRenderer(rightRenderer);
        } else {
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            table.getColumnModel().getColumn(index).setCellRenderer(rightRenderer);
        }

    }

    public static void alignMany(JXTable dataTable, int[] indexesTobeAligned) {
        for (int i : indexesTobeAligned) {
            align(dataTable, i);
        }
    }

}
