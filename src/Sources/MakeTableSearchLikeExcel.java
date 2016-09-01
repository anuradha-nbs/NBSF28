/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;
import com.jidesoft.swing.SearchableUtils;
import com.jidesoft.swing.TableSearchable;
import java.awt.Color;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author mmh
 */
public class MakeTableSearchLikeExcel {

    public static void setDataTableSearchable(String[] tableHeader, JXTable dataTable) {

        TableSearchable tableSearchable = SearchableUtils.installSearchable(dataTable);
        int columnIndices[] = new int[tableHeader.length];
        for (int i = 0; i < tableHeader.length; i++) {
            columnIndices[i] = i;
        }

        tableSearchable.setSearchColumnIndices(columnIndices);
        tableSearchable.setBackground(Color.white);
        TableRowFilterSupport.forFilter(new JXTableFilter(dataTable)).searchable(true).apply();
        dataTable.packAll();
    }
    public static void setDataTableSearchable(int columnCount, JXTable dataTable) {

        TableSearchable tableSearchable = SearchableUtils.installSearchable(dataTable);
        int columnIndices[] = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnIndices[i] = i;
        }

        tableSearchable.setSearchColumnIndices(columnIndices);
        tableSearchable.setBackground(Color.white);
        TableRowFilterSupport.forFilter(new JXTableFilter(dataTable)).searchable(true).apply();
        dataTable.packAll();
    }
}
