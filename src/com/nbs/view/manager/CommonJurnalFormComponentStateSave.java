/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager;

import com.nbs.view.common.GeneralUserLogin;
import java.util.Date;

/**
 *
 * @author mmh
 */
public class CommonJurnalFormComponentStateSave {

    public static String getCAText() {
        return CAText;
    }

    public static void setCAText(String CAText) {
        CommonJurnalFormComponentStateSave.CAText = CAText;
    }

    private CommonJurnalFormComponentStateSave(boolean sectionAll, boolean outsiderAll, Date from, Date to, int stateID, int CAID, int outsiderID, int sectionID, String CAText) {

        CommonJurnalFormComponentStateSave.sectionAll = sectionAll;
        CommonJurnalFormComponentStateSave.outsiderAll = outsiderAll;
        CommonJurnalFormComponentStateSave.from = from;
        CommonJurnalFormComponentStateSave.to = to;
        CommonJurnalFormComponentStateSave.stateID = stateID;
        CommonJurnalFormComponentStateSave.CAID = CAID;
        CommonJurnalFormComponentStateSave.CAText = CAText;
        CommonJurnalFormComponentStateSave.outsiderID = outsiderID;
        CommonJurnalFormComponentStateSave.sectionID = sectionID;

    }

    public CommonJurnalFormComponentStateSave() {

        this(true, true, GeneralUserLogin.data.getCommonJurnalFormFromDate(), GeneralUserLogin.data.getSystemDate(), 1, 0, 0, 0, "");
    }

    private static boolean sectionAll;
    private static boolean outsiderAll;
    private static Date from;
    private static Date to;
    private static int stateID;
    private static int CAID;
    private static String CAText;
    private static int outsiderID;
    private static int sectionID;

    public static boolean isSectionAll() {
        return sectionAll;
    }

    public static void setSectionAll(boolean sectionAll) {
        CommonJurnalFormComponentStateSave.sectionAll = sectionAll;
    }

    public static boolean isOutsiderAll() {
        return outsiderAll;
    }

    public static void setOutsiderAll(boolean outsiderAll) {
        CommonJurnalFormComponentStateSave.outsiderAll = outsiderAll;
    }

    public static Date getFrom() {
        return from;
    }

    public static void setFrom(Date from) {
        CommonJurnalFormComponentStateSave.from = from;
    }

    public static Date getTo() {
        return to;
    }

    public static void setTo(Date to) {
        CommonJurnalFormComponentStateSave.to = to;
    }

    public static int getStateID() {
        return stateID;
    }

    public static void setStateID(int stateID) {
        CommonJurnalFormComponentStateSave.stateID = stateID;
    }

    public static int getCAID() {
        return CAID;
    }

    public static void setCAID(int CAID) {
        CommonJurnalFormComponentStateSave.CAID = CAID;
    }

    public static int getOutsiderID() {
        return outsiderID;
    }

    public static void setOutsiderID(int outsiderID) {
        CommonJurnalFormComponentStateSave.outsiderID = outsiderID;
    }

    public static int getSectionID() {
        return sectionID;
    }

    public static void setSectionID(int sectionID) {
        CommonJurnalFormComponentStateSave.sectionID = sectionID;
    }

}
