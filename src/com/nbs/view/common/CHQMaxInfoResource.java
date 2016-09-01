/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common;

import com.nbs.impl.ServerConnection;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmh
 */
public class CHQMaxInfoResource {

    public static void showDT(String hdID) {
        try {
            CHQModuleMaxInfo.dtm16B.setRowCount(0);
            List<Object> inputs = new ArrayList<>();
            inputs.add(hdID);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock_16B_Payments", 8);
            for (List<Object> output : outputs) {
                Vector v = new Vector();
                for (Object object : output) {
                    v.add(object);
                }
                CHQModuleMaxInfo.dtm16B.addRow(v);
            }
            getTotal();
            
        } catch (SQLException ex) {
            Logger.getLogger(CHQMaxInfoResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CHQMaxInfoResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CHQMaxInfoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void getTotal() {
        int rowCount = CHQModuleMaxInfo.dtm16B.getRowCount();
        double d = 0;
        for (int i = 0; i < rowCount; i++) {
            try {
                d += FormatConstants.numberFormat1.parse(String.valueOf(CHQModuleMaxInfo.TB_pur.getValueAt(i, 4))).doubleValue();
            } catch (ParseException ex) {
                Logger.getLogger(CHQMaxInfoResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        CHQModuleMaxInfo.B16Total.setText(FormatConstants.numberFormat1.format(d));
    }
}
