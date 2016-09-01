/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager.GL;

import com.nbs.impl.ServerConnection;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created on Nov 15, 2015
 *
 * @author mmh
 */
class SaveSubAccounts {

    private static final String[] LEVEL = {"L0", "L1", "L2", "L3", "L4", "L5"};

    static List<Object> saveSubAccount(int subAcctType,String mainAcctID,String subAcctID,String acctNo,String descSin,String descUni,double tblOrder,double pnlOrder,double noteNo,int resultCount) {
        List<Object> outData = null;
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(LEVEL[subAcctType]);
            inputs.add(mainAcctID);
            inputs.add(subAcctID);
            inputs.add(acctNo);
            inputs.add(descSin);
            inputs.add(descUni);
            inputs.add(tblOrder);
            inputs.add(pnlOrder);
            inputs.add(noteNo);
            inputs.add("@actNo");
            inputs.add("@recid");
            outData = ServerConnection.getServerConnector().insertData(inputs, "ssp_Act_Insert_ChartOfAcct", resultCount);
        } catch (RemoteException ex) {
            Logger.getLogger(SaveSubAccounts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaveSubAccounts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveSubAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outData;
    }
}
