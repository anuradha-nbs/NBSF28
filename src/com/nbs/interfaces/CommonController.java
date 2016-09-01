/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mmh
 */
public interface CommonController extends Remote {

    public List<List<Object>> searchMultipleResults(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> searchResultsWithOutputs(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> searchSingleResult(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> insertData(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> updateData(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> deleteData(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> insertImage(List<Object> inputs, String storedProceedure, int imagePosition, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;

    public List<Object> getImage(List<Object> inputs, String storedProceedure, int resultCount) throws RemoteException,SQLException,ClassNotFoundException;
    
    public HashMap getResultSet(String sql)throws RemoteException,SQLException,ClassNotFoundException;
    
    public HashMap getConnection()throws RemoteException,SQLException,ClassNotFoundException;
}
