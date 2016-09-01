/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager;

/**
 *
 * @author mmh
 */
public class JurnalTableHeaders {

    public static String accountHeader[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    public static String dailyCashHeader[] = {"ID", "Branch ID", "Type ID", "Date", "GL Description", "User", "Amount", "Status", "JHD ID", "GLTRn Description"};
    public static String newOpenedAccountsHeader[] = {"ID", "BranchID", "DepTypeID", "CustomerID", "Start Date", "Acct#", "Dep.Name", "Full Name", "Int %", "St.Amount", "AcctStatusID", "Status", "OpenBy", "ConfirmBy"};
    public static String newClosedAccountsHeader[] = {"ID", "BranchID", "DepTypeID", "CustomerID", "Start Date", "Cls.Date", "Acct#", "Dep.Name", "Full Name", "Int %", "St.Amount", "TotCR", "TotDR", "Int.Paid", "Tax", "Cls.Amount", "Duration", "AcctStatusID", "Status", "OpenBy", "ConfirmBy"};
//    public static String matueredDepositsHeader[] = {"ID","BranchID","DepTypeID","CustomerID","Acct#","DepName","FullName","CurBookBal.","H.Amount","Avbl.Balance","Int%","St.Date","LastUpdateDate","LastUpdateDate_WihoutIntCR","LastIntCalDate","NextIntCalDate","NotCreditIntBalance","No_of_Days"};
    public static String matueredDepositsHeader[] = {"ID", "BranchID", "DepTypeID", "CustomerID", "St.Date", "NextIntCalDate", "Acct#", "DepName", "FullName", "Int%    ", "St.Amnt", "Not Cr Bal", "Int.Paid", "Tax    ", "No_of_Days", "Duration_Days", "AcctStatusID", "AcctStatus", "OpenBy", "ConfirmBy"};
    public static String inactiveAccountsHeader[] = {"ID", "BranchID", "DepTypeID", "CustomerID", "OurAccountNo", "DepName", "FullName", "CurBookBalance", "HoldedAmount", "AvailableBalance", "Int%", "St.Date", "LastUpdateDate", "LastUpdateDate_WihoutIntCR", "LastIntCalDate", "NextIntCalDate", "NotCreditIntBalance", "No_of_Days"};
    public static String emptyHeader[] = {};
    public static String depostPrototypeHeader[] = {"අංකය", "තැන්පත් කේතය", "තැන්පත් නම", "තැන්පත් නම( සිංහල )", "තැන්පත් වර්ගය", "පොලිය"};
    public static String loanPrototypeHeader[] = {"අංකය", "ණය කේතය", "ණය නම", "ණය නම( සිංහල )", "ණය වර්ගය", "පොලිය"};
    public static String[] _01_01 = {"16BTranID","GLTrnID","TrnDate","SubSectionNameSin","OutsiderNameSin","16BNo","InvoiceNo","InvoiceDate","InvoiceValue","InvoiceValue_SellingPrice","Discount","PurExpenses","FreeIssues","GoodsReturns","TotSuppPayments","SectionTransfer_In","CashPurchase","CreditPeriod","PaymentDate","AddBy","ConfirmedBy","JournalHDID"};
    public static String[] _01_02 = {"ID", "TrnDate", "Section", "Outsider", "16BNo", "Inv.#", "Inv.Date", "Inv.Value", "Sell Value", "Discount", "PurExpenses", "FreeIssues", "GoodsReturns", "TotSuppPayments", "SectionTransfer_In", "CashPurchase", "CreditPeriod", "PaymentDate", "AddBy", "ConfirmedBy", "JournalHDID"};
    public static String[] _01_03 = {"ID", "TrnDate", "Section", "Outsider", "16BNo", "Inv.#", "Inv.Date", "Inv.Value", "Sell Value", "Discount", "PurExpenses", "FreeIssues", "GoodsReturns", "TotSuppPayments", "SectionTransfer_In", "CashPurchase", "CreditPeriod", "PaymentDate", "AddBy", "ConfirmedBy", "JournalHDID"};
    public static String[] _01_04 = {"ID", "TrnDate", "Section", "Outsider", "16BNo", "Inv.#", "Inv.Date", "Inv.Value", "Sell Value", "Discount", "PurExpenses", "FreeIssues", "GoodsReturns", "TotSuppPayments", "SectionTransfer_In", "CashPurchase", "CreditPeriod", "PaymentDate", "AddBy", "ConfirmedBy", "JournalHDID"};
    public static String[] _01_05 = {"9BTrnHDID", "9BDate", "9B_Number", "SubSectionNameSin", "OutsiderNameSin", "TrnSubSectionNameSin", "Sale_Cash", "Sale_Credit", "Sale_Loans", "Sale_StampSamu", "Sale_StampPoshana", "Sale_StampDryItems", "Sale_SectionTransfer", "Sale_CreditCard", "Sale_Total", "CashPurchases", "F2Expenses", "OtherCashReceipts", "9BAddBy", "9BAddDate", "ConfirmedBy", "ConfirmedDate", "JournalHDID"};
    public static String[] _01_05_NEW = {"9BTrnHDID", "9BDate", "9B_Number", "SubSectionNameSin", "OutsiderNameSin", "TrnSubSectionNameSin", "DR", "CR", "9BAddBy", "9BAddDate", "JournalHDID"};
    public static String[] _01_06 = {"nF17TranID", "dTrnDate", "nSubSectionID", "nOutsiderMFID", "nReasonID", "nTrnStatusID", "nF17TypeID", "cF17Number", "nProfit", "nLost", "cAddBy", "cEditBy", "cOutsiderName", "cSubSectionName", "cReason"};
    public static String[] _01_07 = {"nF18TranID", "dTrnDate", "nGLTrnID", "nSubSectionID", "nOutsiderMFID", "nReasonID", "nTrnStatusID", "cF18Number", "nTypeID", "nOut_PurPrice", "nOut_SalePrice", "nIn_PurPrice", "nIn_SalePrice", "cAddBy", "cEditBy", "cOutsiderName", "cSubSectionName", "cReason", "nJournalHDID", "nJournalDTID"};
    public static String[] _01_08 = {"nF19TranID", "dTrnDate", "nGLTrnID", "nSubSectionID", "nOutsiderMFID", "nReasonID", "nTrnStatusID", "nF19TypeID", "cF19Number", "nPurchase", "nSales", "cAddBy", "cEditBy", "cOutsiderName", "cSubSectionName", "cReason", "nJournalHDID", "nJournalDTID"};
    public static String[] _01_11 = {"nAdjustmentTranID", "dTrnDate", "nSubSectionID", "cSubSectionNameSin","nOutsiderMFID", "cOutsiderNameSin", "nTrnStatusID", "cRefNumber", "nAdjustment_In", "nAdjustment_Out"};
    public static String[] _02_01 = {"ID", "Date", "F10No", "SubSectionName", "OutsiderName", "Description", "Amount", "JournalHDID"};
    public static String[] _02_02 = {"ID", "Date", "F10No", "SubSectionName", "OutsiderName", "Description", "Amount", "JournalHDID"};
    public static String[] _02_03 = {"ID", "Date", "F10No", "SubSectionName", "OutsiderName", "Description", "Amount", "JournalHDID"};
    public static String[] _02_04 = {"ID", "Date", "F10No", "SubSectionName", "OutsiderName", "Description", "Amount", "JournalHDID"};

    public static String[] _03 = {"HDID", "TrnDate", "BillNo", "SubSectionNameSin", "OutsiderNameSin", "ReasonSin", "Amount", "DescriptionUni_L5", "OurBillNo", "ChequeNo", "ChequeDate", "ChequeValue", "ChequeValue_Max", "cAddBy", "JournalHDID", "GLTrnID", "StatusID"};
    public static String[] _05 = {"OthTrnHDID", "TrnDate", "BillNo", "Amount", "SubSectionNameSin", "OutsiderNameSin", "ReasonSin", "DescriptionUni_L5", "GLTrnID", "TrnStatusID", "AddBy", "JHDID"};
    public static String[] _04_01 = {"OthTrnHDID", "TrnDate", "PaidDate", "BillNo", "OurBillNo", "Amount", "DescriptionUni_L5", "ChequeNo", "ChequeDate", "ChequeValue", "ChequeValue_Max", "OutsiderNameSin", "JournalHDID"};
    public static String[] _04_02 = {};
    public static String[] _07_01 = {"nOthTrnHDID", "dTrnDate", "cBillNo", "cF9ENo", "Act_Sections_Sub.cSubSectionNameSin", "Act_OutsiderMaster.cOutsiderNameSin", "nAmount", "nDr", "nCr", "bConfirmed", "bSuppervised", "bApproved", "bCompleted", "nJournalHDID", "nJournalDTID"};
    public static String[] _05_01 = {"nOthTrnHDID", "dTrnDate", "cBillNo", "cSubSectionNameSin", "cOutsiderNameSin", "cReasonSin", "nAmount", "cDescriptionUni_L5", "cOurBillNo", "cChequeNo", "dChequeDate", "nChequeValue", "nChequeValue_Max", "nJournalHDID"};
    //8
    public static String[] _03_08 = {"nOthTrnHDID", "dTrnDate", "nGLTrnID", "cBillNo", "cOurBillNo", "nAmount", "cOutsiderNameSin", "cReasonSin", "cDescriptionUni_L5", "nTrnStatusID"};
    public static String[] _10_02 = {"OthTrnHDID", "TrnDate", "DescriptionUni_L5", "cRemarks", "F10No", "F9DNo", "OutsiderNameSin", "PaymentVal", "BankedDate", "StatementDate", "RefNo"};
    public static String[] _10_03 = {"OthTrnHDID", "TrnDate", "PaidDate", "ChequeNo", "ChequeDate", "ChequeValue", "DescriptionUni_L5", "StatementDate", "RefNo"};
}
