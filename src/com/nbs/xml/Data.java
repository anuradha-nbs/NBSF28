/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.xml;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author mmh
 */
public class Data {

    private int branchID;
    private String dbName;
    private String serviceName;
    private String port;
    private String ip;
    private String password;
    private String username;
    private Date systemDate;
    private String branchName;
    private int cashInHandID = 0;
    private String userCode;
    private boolean bdayOK;
    private boolean fromHome;
    private String reportPath;
    private int userID;
    private boolean B9old;
    private boolean B16;
    private boolean B16AutoAddFroPayments;
    private boolean webHosted;
    private Date commonJurnalFormFromDate;

    public Data(int branchID, String dbName, String serviceName, String port, String ip, String password) {
        this.branchID = branchID;
        this.dbName = dbName;
        this.serviceName = serviceName;
        this.port = port;
        this.ip = ip;
        this.password = password;
        this.reportPath = System.getProperty("user.home") + "/NBSReports/F28/";
    }

    public Data() {
        this.reportPath = System.getProperty("user.home") + "/NBSReports/F28/";
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(systemDate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        setCommonJurnalFormFromDate(c.getTime());
        this.systemDate = systemDate;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getCashInHandID() {
        return cashInHandID;
    }

    public void setCashInHandID(int cashInHandID) {
        this.cashInHandID = cashInHandID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean isBdayOK() {
        return bdayOK;
    }

    public void setBdayOK(boolean bdayOK) {
        this.bdayOK = bdayOK;
    }

    public boolean isFromHome() {
        return fromHome;
    }

    public void setFromHome(boolean fromHome) {
        this.fromHome = fromHome;
    }

    public String getReportPath() {
        return reportPath;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isB9old() {
        return B9old;
    }

    public void setB9old(boolean B9old) {
        this.B9old = B9old;
    }

    public boolean isB16() {
        return B16;
    }

    public void setB16(boolean B16) {
        this.B16 = B16;
    }

    public boolean isB16AutoAddFroPayments() {
        return B16AutoAddFroPayments;
    }

    public void setB16AutoAddFroPayments(boolean B16AutoAddFroPayments) {
        this.B16AutoAddFroPayments = B16AutoAddFroPayments;
    }

    public void setWebHosted(boolean webHosted) {
        this.webHosted = webHosted;
    }

    public boolean isWebHosted() {
        return webHosted;
    }

    public Date getCommonJurnalFormFromDate() {
        return commonJurnalFormFromDate;
    }

    public void setCommonJurnalFormFromDate(Date commonJurnalFormFromDate) {
        this.commonJurnalFormFromDate = commonJurnalFormFromDate;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
}
