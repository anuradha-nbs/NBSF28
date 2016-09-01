/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import com.nbs.impl.ServerConnection;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmh
 */
public class SystemParameterList {

    public static Map<Object, Object> parameters;

    public static void getParameterList() {
        try {
            parameters = ServerConnection.getServerConnector().getResultSet("SELECT * FROM F28_SystemParameters");            
        } catch (RemoteException ex) {
            Logger.getLogger(SystemParameterList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SystemParameterList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SystemParameterList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
