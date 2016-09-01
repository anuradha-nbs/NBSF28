/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nbs.view.common.search;

import com.nbs.impl.ServerConnection;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmh
 */
public class DataFiller {
        public static void searchData(DefaultTableModel dtm,String searchText,int branchid,int searchItemNo,String ssp,int resultCount,int area[]) {
            try {
                List<Object> inputs = new ArrayList<>();
                dtm.setRowCount(0);
                inputs.add(branchid);
                inputs.add(searchItemNo);
                inputs.add(searchText);
                List<List<Object>> list = ServerConnection.getServerConnector().searchMultipleResults(inputs, ssp, resultCount);
                for (List<Object> list1 : list) {
                    Object[] toArray = list1.toArray();
                    List<Object> data = new ArrayList<>();
                    for (int i = 0; i < area.length; i++) {
                        data.add(toArray[area[i]]);
                    }
                    dtm.addRow(data.toArray());
                }
                System.gc();
            } catch (RemoteException ex) {
                Logger.getLogger(DataFiller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DataFiller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataFiller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
