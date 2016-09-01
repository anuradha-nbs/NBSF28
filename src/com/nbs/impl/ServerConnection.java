/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.impl;

import com.nbs.interfaces.CommonController;
import com.nbs.view.common.GeneralUserLogin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ServerConnection implements CommonController {

    @Override
    public List<List<Object>> searchMultipleResults(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> searchResultsWithOutputs(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> searchSingleResult(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> insertData(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> updateData(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> deleteData(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private static CommonController connector;

    private ServerConnection() throws RemoteException {
        
    }

    public static CommonController getServerConnector() throws RemoteException {
        if (connector == null) {
            try {
                connector = (CommonController)Naming.lookup("rmi://"+GeneralUserLogin.data.getIP()+":2030/nbsBankingErp");
            } catch (NotBoundException ex) {
                JOptionPane.showMessageDialog(null, "Start The Server", "Connection Lost", JOptionPane.WARNING_MESSAGE);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connector;
    }

    @Override
    public List<Object> insertImage(List<Object> inputs, String storedProceedure, int imagePosition, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getImage(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap getResultSet(String sql) throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap getConnection() throws RemoteException, SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
