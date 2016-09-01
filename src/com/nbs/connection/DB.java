/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.connection;

import com.nbs.view.common.GeneralUserLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mmh
 */
public class DB {

    public static String databaseName = "nbsERP_testing";
    public static String pw = "123";
    private static Connection c;

    /**
     *
     * @return Connetion object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        if (c == null) {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + GeneralUserLogin.data.getIP() + ":3306/" + GeneralUserLogin.data.getDbName(), "root", GeneralUserLogin.data.getPassword());
            
        }
        return c;
    }
    
}
