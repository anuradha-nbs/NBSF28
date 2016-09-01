/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager;

import Sources.CheckNull;
import Sources.GLCodes;
import Sources.ReportFileLocator;
import Sources.SinhalaOriginalNumberToLetter;
import Sources.TableColumnFontChanger;
import Sources.TableColumnRightAlign;
import Sources.TextPrompt;
import Sources.User;
import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;
import com.jidesoft.swing.SearchableUtils;
import com.jidesoft.swing.TableSearchable;
import com.nbs.connection.DB;
import com.nbs.impl.ServerConnection;
import com.nbs.view.F28.Payments.CommonCashReceiptCash;
import com.nbs.view.F28.Payments.CommonCashReceiptStamp;
import com.nbs.view.F28.Payments.CommonDRNote;
import com.nbs.view.F28.Payments.CommonRuralBank9E;
import com.nbs.view.F28.Stock.AB16Buyings;
import com.nbs.view.F28.Stock.AB16BuyingsNewFormat;
import com.nbs.view.F28.Stock.AB16StoresBuyings;
import com.nbs.view.F28.Stock.C14B9;
import com.nbs.view.F28.Stock.C14B9HD;
import com.nbs.view.F28.Stock.F17;
import com.nbs.view.F28.Stock.F18;
import com.nbs.view.F28.Stock.F19;
import com.nbs.view.F28.Stock.StockBalance;
import com.nbs.view.common.CHQModule;
import com.nbs.view.common.CHQModuleMaxInfo;
import com.nbs.view.common.FormatConstants;
import com.nbs.view.common.GeneralUserLogin;
import static com.nbs.view.common.GeneralUserLogin.data;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.search.DoubleEntryViwer;
import com.nbs.view.common.search.FindAccount5Data;
import com.nbs.view.common.search.FindSectionOrOutsider;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.lang3.math.NumberUtils;
import org.nbs.resources.Item;

/**
 *
 * @author mmh
 */
public class CommonJurnalForm extends javax.swing.JPanel {

    DefaultTableModel dtm;
    int type;
    TableSearchable tableSearchable;
    DateFormat df;
    private String[] tableHeaders;
    String code;
    public JFrame parentForCommonJurnalForm;
    private User user;
    public static String searchPanelSerialized;

    /**
     * Creates new form CommonJurnalForm
     */
    public CommonJurnalForm() {
        initComponents();

        TextPrompt prompt = new TextPrompt("සියලු ගිණුම් සඳහා හිස්ව තබන්න", txtCurrentAct);
        prompt.setFont(new Font("Iskoola Pota", Font.PLAIN, 13));
        setHeaderHeight();

//        dtm = (DefaultTableModel) dataTable.getModel();
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dataTable.setModel(dtm);
        dataTable.setShowGrid(true);
        tableSearchable = SearchableUtils.installSearchable(dataTable);
        df = new SimpleDateFormat("yyyy-MM-dd");
        fromDate.setDate(data.getSystemDate());
        toDate.setDate(data.getSystemDate());
        resetButtons();
//        viewOBal(false);
    }

    public CommonJurnalForm(String title) {
        this();
        lblTitle.setText(" " + title);
        dtm = (DefaultTableModel) dataTable.getModel();
        dataTable.setShowGrid(true);
        dtm.setColumnIdentifiers(JurnalTableHeaders.dailyCashHeader);
        try {
            comboLoad();
        } catch (NotBoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDataTableSearchable(JurnalTableHeaders.dailyCashHeader);
    }
    // <editor-fold defaultstate="collapsed" desc="Commented Constructor">

    /**
     * public CommonJurnalForm(String title, String tableHeader[]) { this();
     * lblTitle.setText(" " + title); dtm = (DefaultTableModel)
     * dataTable.getModel(); dataTable.setShowGrid(true); this.tableHeaders =
     * tableHeader; dtm.setColumnIdentifiers(tableHeader); try { comboLoad(); }
     * catch (NotBoundException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } catch (MalformedURLException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } catch (ClassNotFoundException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } catch (RemoteException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } catch (InterruptedException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } catch (SQLException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } // setDataTableSearchable(tableHeader); }
     *
     * CommonJurnalForm(String header, String[] tableHeaders, int type) {
     * this(); lblTitle.setText(" " + header); dtm = (DefaultTableModel)
     * dataTable.getModel(); dataTable.setShowGrid(true); this.tableHeaders =
     * tableHeaders; dtm.setColumnIdentifiers(JurnalTableHeaders.emptyHeader);
     * // dataTable.setDefaultRenderer(Object.class, new ColumnColorRenderer());
     * this.type = type; try { comboLoad(); } catch (NotBoundException |
     * MalformedURLException | ClassNotFoundException | RemoteException |
     * SQLException | InterruptedException ex) {
     * Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE,
     * null, ex); } // setDataTableSearchable(tableHeaders);
     *
     * }
     *
     */
    // </editor-fold> 
    CommonJurnalForm(String header, String[] tableHeaders, String code) {
        this();

        lblTitle.setText(" " + header);
        dtm = (DefaultTableModel) dataTable.getModel();
        dataTable.setShowGrid(true);
        this.tableHeaders = tableHeaders;
        dtm.setColumnIdentifiers(JurnalTableHeaders.emptyHeader);
//        dataTable.setDefaultRenderer(Object.class, new ColumnColorRenderer());
//        this.type = type;
        this.code = code;
//        System.out.println(code.startsWith("04-") + " 3");
        if (code.startsWith("04-")) {
            itemConfirmPrint.setEnabled(false);
            itemEditVoucher.setEnabled(false);
            itemJournal.setEnabled(false);
            itemNewVoucher.setEnabled(false);
            itemPrintVoucher.setEnabled(false);
            dataTable.setComponentPopupMenu(ChequePopup);
            viewTotal(code);
        } else if (code.equals("01-09")) {
            dataTable.setComponentPopupMenu(journalPopup);
            viewTotal(code);
        } else if (code.startsWith("01-")) {
//            Date systemDate = data.getSystemDate();
//            Calendar c = Calendar.getInstance();
//            c.setTime(systemDate);
//            c.set(Calendar.DAY_OF_MONTH, 1);
            if ((code.endsWith("01") || code.endsWith("02") || code.endsWith("03") || code.endsWith("04"))) {
                stockPopup.remove(jMenu2);
                stockPopup.remove(jSeparator29);
            } else if (code.endsWith("05")) {
                stockPopup.remove(jMenu1);
                stockPopup.remove(jSeparator29);
            } else {
                stockPopup.remove(jMenu1);
                stockPopup.remove(jMenu2);
                stockPopup.remove(jSeparator29);
                stockPopup.remove(jSeparator5);
            }
            dataTable.setComponentPopupMenu(stockPopup);
            fromDate.setDate(data.getCommonJurnalFormFromDate());

            viewTotal(code);
        } else if (code.startsWith("03-")) {
            if (code.equals("03-08")) {
                VoucherPopup.remove(itemNewVoucher);
                VoucherPopup.remove(jSeparator6);
            }
            dataTable.setComponentPopupMenu(VoucherPopup);
            viewTotal(code);
        } else if (code.startsWith("02-")) {
            dataTable.setComponentPopupMenu(cashierPopup);
            viewOBal(true);
        } else if (code.startsWith("05-") || code.startsWith("08-")) {
            dataTable.setComponentPopupMenu(journalPopup);
            viewTotal(code);
        } else if (code.startsWith("07-")) {
            dataTable.setComponentPopupMenu(ruralBankPopup);
            viewTotal(code);
        }

        try {
            comboLoad();
        } catch (NotBoundException | MalformedURLException | ClassNotFoundException | RemoteException | SQLException | InterruptedException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadStatuses(code);
//        showCurrentAccount(code);

        showChequeBtn(code);
        selectConfirmedCombo();
//        setDataTableSearchable(tableHeaders);
    }

    CommonJurnalForm(String header, String[] tableHeaders, int type, String code) {
        this();
        lblTitle.setText(" " + header);
        dtm = (DefaultTableModel) dataTable.getModel();
        dataTable.setShowGrid(true);
        this.tableHeaders = tableHeaders;
        dtm.setColumnIdentifiers(JurnalTableHeaders.emptyHeader);
//        dataTable.setDefaultRenderer(Object.class, new ColumnColorRenderer());
        this.type = type;
        this.code = code;
//        System.out.println(code.startsWith("04-") + " 1");
        if (code.startsWith("04-")) {
            itemConfirmPrint.setEnabled(false);
            itemEditVoucher.setEnabled(false);
            itemJournal.setEnabled(false);
            itemNewVoucher.setEnabled(false);
            itemPrintVoucher.setEnabled(false);
            dataTable.setComponentPopupMenu(ChequePopup);
            viewTotal(code);
        } else if (code.startsWith("01-")) {
//            Date systemDate = data.getSystemDate();
//            Calendar c = Calendar.getInstance();
//            c.setTime(systemDate);
//            c.set(Calendar.DAY_OF_MONTH, 1);
            fromDate.setDate(data.getCommonJurnalFormFromDate());
            dataTable.setComponentPopupMenu(stockPopup);
            viewTotal(code);
        } else if (code.startsWith("03-")) {
            if (code.equals("03-08")) {
                VoucherPopup.remove(itemNewVoucher);
                VoucherPopup.remove(jSeparator6);
            }
            dataTable.setComponentPopupMenu(VoucherPopup);
            viewTotal(code);
        } else if (code.startsWith("02-")) {
            viewOBal(true);
            dataTable.setComponentPopupMenu(cashierPopup);
        } else if (code.startsWith("05-") || code.startsWith("08-")) {
            dataTable.setComponentPopupMenu(journalPopup);
            viewTotal(code);
        }
        try {
            comboLoad();
        } catch (NotBoundException | MalformedURLException | ClassNotFoundException | RemoteException | SQLException | InterruptedException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
//        setDataTableSearchable(tableHeaders);
        loadStatuses(code);
//        showCurrentAccount(code);
        showChequeBtn(code);
        selectConfirmedCombo();
    }

    CommonJurnalForm(String title, String[] tableHeaders, String code, User user) {
        this(title, tableHeaders, code);
        this.user = user;
        if (code.startsWith("04-")) {
            itemConfirmPrint.setEnabled(false);
            itemEditVoucher.setEnabled(false);
            itemJournal.setEnabled(false);
            itemNewVoucher.setEnabled(false);
            itemPrintVoucher.setEnabled(false);
            dataTable.setComponentPopupMenu(ChequePopup);
            viewTotal(code);
        } else if (code.equals("01-09")) {
            dataTable.setComponentPopupMenu(journalPopup);
            viewTotal(code);
        } else if (code.startsWith("01-")) {
            Date systemDate = data.getSystemDate();
            Calendar c = Calendar.getInstance();
            c.setTime(systemDate);
            c.set(Calendar.DAY_OF_MONTH, 1);
            fromDate.setDate(data.getCommonJurnalFormFromDate());
            dataTable.setComponentPopupMenu(stockPopup);
            viewTotal(code);
        } else if (code.startsWith("03-")) {
            if (code.equals("03-08")) {
                VoucherPopup.remove(itemNewVoucher);
                VoucherPopup.remove(jSeparator6);
            }
            dataTable.setComponentPopupMenu(VoucherPopup);
            viewTotal(code);
        } else if (code.startsWith("02-")) {
            viewOBal(true);
            dataTable.setComponentPopupMenu(cashierPopup);
        } else if (code.startsWith("05-") || code.startsWith("08-")) {
            dataTable.setComponentPopupMenu(journalPopup);
            viewTotal(code);
        } else if (code.startsWith("10")) {
            jPanel3.removeAll();
        }

    }

    CommonJurnalForm(String header, String[] tableHeaders, int type, String code, User user) {
        this(header, tableHeaders, type, code);
        this.user = user;
    }

    CommonJurnalForm(String header, User user) {
        this(header);
        this.user = user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChequePopup = new javax.swing.JPopupMenu();
        itemChequePrint = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemConfirmPrint = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        ItemMaxCheque = new javax.swing.JMenuItem();
        itemAdvancedCheque = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemJournalCheque = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        itemRealized = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemChequeFind = new javax.swing.JMenuItem();
        ItemRefresh = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        itemCancelCheque = new javax.swing.JMenuItem();
        stockPopup = new javax.swing.JPopupMenu();
        itemNewStock = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        itemEditStock = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        itemJournalStock = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        itemPrint16B = new javax.swing.JMenuItem();
        itemPrint16B1 = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        itemPrint9B = new javax.swing.JMenuItem();
        itemPrint9B1 = new javax.swing.JMenuItem();
        itemPrint9B2 = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        itemRefreshStock = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JPopupMenu.Separator();
        itemCancelStock = new javax.swing.JMenuItem();
        VoucherPopup = new javax.swing.JPopupMenu();
        itemNewVoucher = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        itemEditVoucher = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        itemJournal = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        itemPrintVoucher = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        itemVouchersAll = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        itemVoucherRefresh = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        itemVoucherCancel = new javax.swing.JMenuItem();
        cashierPopup = new javax.swing.JPopupMenu();
        itemNewCashier = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        itemEditCashier = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        itemPrintCashier = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        itemJournalCashier = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        itemRefreshCashier = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JPopupMenu.Separator();
        itemCancelCashier = new javax.swing.JMenuItem();
        journalPopup = new javax.swing.JPopupMenu();
        itemNewJournal = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        itemEditJournal = new javax.swing.JMenuItem();
        itemCopyJournal = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        itemPrintJournal = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        itemJournalJournal = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        itemRefreshJournal = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        itemCancelJournal = new javax.swing.JMenuItem();
        ruralBankPopup = new javax.swing.JPopupMenu();
        itemNewRB = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        itemEditRB = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        itemJournalRB = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        itemPrintRB = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        itemRBRefresh = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JPopupMenu.Separator();
        itemRBCancel = new javax.swing.JMenuItem();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblCashSale = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblCreditSale = new javax.swing.JLabel();
        lblSamurdhi = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblLoanSale = new javax.swing.JLabel();
        lblDryItems = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblPoshana = new javax.swing.JLabel();
        lblCreditCard = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblSectionsTransfer = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblCashPurchases = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblF2Expences = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblOtherReceivings = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lblInvVal = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblSellVal = new javax.swing.JLabel();
        lblPurExpences = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblGoodReturns = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblFreeIssue = new javax.swing.JLabel();
        lblSecTransIn = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblTotSupPayments = new javax.swing.JLabel();
        lblCashPur = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lblProfit = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblLost = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        lblOutPurPrice = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblOutSalePrice = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        lblPurchase = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblSales = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtRemarks1002 = new javax.swing.JTextField();
        opt10021 = new javax.swing.JRadioButton();
        opt10022 = new javax.swing.JRadioButton();
        jdtBankDate1002 = new com.toedter.calendar.JDateChooser();
        txtChequeNo1002 = new org.nbs.components.HiddenIDTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel11 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtRemarks1003 = new javax.swing.JTextField();
        opt10031 = new javax.swing.JRadioButton();
        opt10032 = new javax.swing.JRadioButton();
        jdtBankDate1003 = new com.toedter.calendar.JDateChooser();
        txtChequeNo1003 = new org.nbs.components.HiddenIDTextField();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel12 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        lblPurchase1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataTable = new org.jdesktop.swingx.JXTable();
        detailHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        toDate = new com.toedter.calendar.JDateChooser();
        outsider = new org.nbs.components.HiddenItemComboBox();
        section = new org.nbs.components.HiddenItemComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sectionCheck = new javax.swing.JCheckBox();
        supplierCheck = new javax.swing.JCheckBox();
        btnSection = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        cmbConfirmed = new org.nbs.components.HiddenItemComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCurrentAct = new org.nbs.components.HiddenIDTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblDR = new javax.swing.JLabel();
        lblCBal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblOBal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblCR = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblCashTotal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        ChequePopup.setBackground(new java.awt.Color(22, 47, 207));
        ChequePopup.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ChequePopupAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ChequePopup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ChequePopupFocusLost(evt);
            }
        });
        ChequePopup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ChequePopupKeyPressed(evt);
            }
        });

        itemChequePrint.setBackground(new java.awt.Color(163, 156, 149));
        itemChequePrint.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemChequePrint.setText("චෙක්පත මුද්‍රණය");
        itemChequePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemChequePrintActionPerformed(evt);
            }
        });
        ChequePopup.add(itemChequePrint);
        ChequePopup.add(jSeparator1);

        itemConfirmPrint.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemConfirmPrint.setText("මුද්‍රණය තහවුරු කිරීම");
        itemConfirmPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConfirmPrintActionPerformed(evt);
            }
        });
        ChequePopup.add(itemConfirmPrint);
        ChequePopup.add(jSeparator2);

        ItemMaxCheque.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        ItemMaxCheque.setText("උ.චෙක් ගිණුම්ගත කිරීම");
        ItemMaxCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemMaxChequeActionPerformed(evt);
            }
        });
        ChequePopup.add(ItemMaxCheque);

        itemAdvancedCheque.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemAdvancedCheque.setText("අත්තිකාරම් චෙක් ගිණුම්ගත කිරීම");
        itemAdvancedCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAdvancedChequeActionPerformed(evt);
            }
        });
        ChequePopup.add(itemAdvancedCheque);
        ChequePopup.add(jSeparator4);

        itemJournalCheque.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemJournalCheque.setText("ද්විත්ව සටහන");
        itemJournalCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJournalChequeActionPerformed(evt);
            }
        });
        ChequePopup.add(itemJournalCheque);
        ChequePopup.add(jSeparator24);

        itemRealized.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemRealized.setText("නිශ්කාෂණ තොරතුරු");
        itemRealized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRealizedActionPerformed(evt);
            }
        });
        ChequePopup.add(itemRealized);
        ChequePopup.add(jSeparator3);

        itemChequeFind.setBackground(new java.awt.Color(163, 156, 149));
        itemChequeFind.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemChequeFind.setText("චෙක්පතක් සොයන්න");
        itemChequeFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemChequeFindActionPerformed(evt);
            }
        });
        ChequePopup.add(itemChequeFind);

        ItemRefresh.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        ItemRefresh.setText("නැවත සකසන්න");
        ItemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemRefreshActionPerformed(evt);
            }
        });
        ChequePopup.add(ItemRefresh);
        ChequePopup.add(jSeparator34);

        itemCancelCheque.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemCancelCheque.setText("අවලංගු කිරීම");
        itemCancelCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelChequeActionPerformed(evt);
            }
        });
        ChequePopup.add(itemCancelCheque);

        itemNewStock.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemNewStock.setText("නව ඇතුලත් කිරීම්");
        itemNewStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewStockActionPerformed(evt);
            }
        });
        stockPopup.add(itemNewStock);
        stockPopup.add(jSeparator12);

        itemEditStock.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemEditStock.setText("වෙනස් කිරීම");
        itemEditStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditStockActionPerformed(evt);
            }
        });
        stockPopup.add(itemEditStock);
        stockPopup.add(jSeparator13);

        itemJournalStock.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemJournalStock.setText("ද්විත්ව සටහන");
        itemJournalStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJournalStockActionPerformed(evt);
            }
        });
        stockPopup.add(itemJournalStock);
        stockPopup.add(jSeparator5);

        jMenu1.setText("16B වාර්තාව");
        jMenu1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N

        itemPrint16B.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrint16B.setText("16B වාර්තාව සවිස්තරාත්මක");
        itemPrint16B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrint16BActionPerformed(evt);
            }
        });
        jMenu1.add(itemPrint16B);

        itemPrint16B1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrint16B1.setText("16B වාර්තාව සාරාංශ");
        itemPrint16B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrint16B1ActionPerformed(evt);
            }
        });
        jMenu1.add(itemPrint16B1);

        stockPopup.add(jMenu1);
        stockPopup.add(jSeparator29);

        jMenu2.setText("9B වාර්තාව");
        jMenu2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N

        itemPrint9B.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrint9B.setText("9B වාර්තාව 1");
        itemPrint9B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrint9BActionPerformed(evt);
            }
        });
        jMenu2.add(itemPrint9B);

        itemPrint9B1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrint9B1.setText("9B වාර්තාව 2");
        itemPrint9B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrint9B1ActionPerformed(evt);
            }
        });
        jMenu2.add(itemPrint9B1);

        itemPrint9B2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrint9B2.setText("9B වාර්තාව 3");
        itemPrint9B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrint9B2ActionPerformed(evt);
            }
        });
        jMenu2.add(itemPrint9B2);

        stockPopup.add(jMenu2);
        stockPopup.add(jSeparator14);

        itemRefreshStock.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemRefreshStock.setText("නැවත සැකසීම");
        itemRefreshStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRefreshStockActionPerformed(evt);
            }
        });
        stockPopup.add(itemRefreshStock);
        stockPopup.add(jSeparator33);

        itemCancelStock.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemCancelStock.setText("අවලංගු කිරීම");
        itemCancelStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelStockActionPerformed(evt);
            }
        });
        stockPopup.add(itemCancelStock);

        itemNewVoucher.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemNewVoucher.setText("නව වවුචරයක් ඇතුලත් කිරීම");
        itemNewVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewVoucherActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemNewVoucher);
        VoucherPopup.add(jSeparator6);

        itemEditVoucher.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemEditVoucher.setText("වවුචරය වෙනස් කිරීම");
        itemEditVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditVoucherActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemEditVoucher);
        VoucherPopup.add(jSeparator7);

        itemJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemJournal.setText("ද්විත්ව සටහන");
        itemJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJournalActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemJournal);
        VoucherPopup.add(jSeparator8);

        itemPrintVoucher.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrintVoucher.setText("වවුචරය මුද්‍රණය");
        itemPrintVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrintVoucherActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemPrintVoucher);
        VoucherPopup.add(jSeparator9);

        itemVouchersAll.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemVouchersAll.setText("සියලු වවුචර");
        itemVouchersAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVouchersAllActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemVouchersAll);
        VoucherPopup.add(jSeparator10);

        itemVoucherRefresh.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemVoucherRefresh.setText("නැවත සැකසීම");
        itemVoucherRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVoucherRefreshActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemVoucherRefresh);
        VoucherPopup.add(jSeparator11);

        itemVoucherCancel.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemVoucherCancel.setText("වවුචරය අවලංගු කිරීම");
        itemVoucherCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVoucherCancelActionPerformed(evt);
            }
        });
        VoucherPopup.add(itemVoucherCancel);

        itemNewCashier.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemNewCashier.setText("නව ඇතුලත් කිරීම්");
        itemNewCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewCashierActionPerformed(evt);
            }
        });
        cashierPopup.add(itemNewCashier);
        cashierPopup.add(jSeparator15);

        itemEditCashier.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemEditCashier.setText("වෙනස් කිරීම");
        itemEditCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditCashierActionPerformed(evt);
            }
        });
        cashierPopup.add(itemEditCashier);
        cashierPopup.add(jSeparator16);

        itemPrintCashier.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrintCashier.setText("මුද්‍රණය");
        itemPrintCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrintCashierActionPerformed(evt);
            }
        });
        cashierPopup.add(itemPrintCashier);
        cashierPopup.add(jSeparator18);

        itemJournalCashier.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemJournalCashier.setText("ද්විත්ව සටහන");
        itemJournalCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJournalCashierActionPerformed(evt);
            }
        });
        cashierPopup.add(itemJournalCashier);
        cashierPopup.add(jSeparator17);

        itemRefreshCashier.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemRefreshCashier.setText("නැවත සැකසීම");
        itemRefreshCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRefreshCashierActionPerformed(evt);
            }
        });
        cashierPopup.add(itemRefreshCashier);
        cashierPopup.add(jSeparator32);

        itemCancelCashier.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemCancelCashier.setText("අවලංගු කිරීම");
        itemCancelCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelCashierActionPerformed(evt);
            }
        });
        cashierPopup.add(itemCancelCashier);

        itemNewJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemNewJournal.setText("නව ඇතුලත් කිරීම්");
        itemNewJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemNewJournal);
        journalPopup.add(jSeparator19);

        itemEditJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemEditJournal.setText("වෙනස් කිරීම");
        itemEditJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemEditJournal);

        itemCopyJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemCopyJournal.setText("නව පිටපතක්");
        itemCopyJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCopyJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemCopyJournal);
        journalPopup.add(jSeparator20);

        itemPrintJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrintJournal.setText("මුද්‍රණය");
        itemPrintJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrintJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemPrintJournal);
        journalPopup.add(jSeparator21);

        itemJournalJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemJournalJournal.setText("ද්විත්ව සටහන");
        itemJournalJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJournalJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemJournalJournal);
        journalPopup.add(jSeparator22);

        itemRefreshJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemRefreshJournal.setText("නැවත සැකසීම");
        itemRefreshJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRefreshJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemRefreshJournal);
        journalPopup.add(jSeparator23);

        itemCancelJournal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemCancelJournal.setText("අවලංගු කිරීම");
        itemCancelJournal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelJournalActionPerformed(evt);
            }
        });
        journalPopup.add(itemCancelJournal);

        itemNewRB.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemNewRB.setText("නව 9E වාර්තාවක් ඇතුලත් කිරීම");
        itemNewRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewRBActionPerformed(evt);
            }
        });
        ruralBankPopup.add(itemNewRB);
        ruralBankPopup.add(jSeparator25);

        itemEditRB.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemEditRB.setText("වාර්තාව වෙනස් කිරීම");
        itemEditRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditRBActionPerformed(evt);
            }
        });
        ruralBankPopup.add(itemEditRB);
        ruralBankPopup.add(jSeparator26);

        itemJournalRB.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemJournalRB.setText("ද්විත්ව සටහන");
        itemJournalRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJournalRBActionPerformed(evt);
            }
        });
        ruralBankPopup.add(itemJournalRB);
        ruralBankPopup.add(jSeparator27);

        itemPrintRB.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemPrintRB.setText("මුද්‍රණය");
        itemPrintRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrintRBActionPerformed(evt);
            }
        });
        ruralBankPopup.add(itemPrintRB);
        ruralBankPopup.add(jSeparator28);

        itemRBRefresh.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemRBRefresh.setText("නැවත සැකසීම");
        itemRBRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRBRefreshActionPerformed(evt);
            }
        });
        ruralBankPopup.add(itemRBRefresh);
        ruralBankPopup.add(jSeparator30);

        itemRBCancel.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        itemRBCancel.setText("අවලංගු කිරීම");
        itemRBCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRBCancelActionPerformed(evt);
            }
        });
        ruralBankPopup.add(itemRBCancel);

        jLabel8.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("මුදලට :");

        lblCashSale.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblCashSale.setForeground(new java.awt.Color(0, 0, 255));

        jLabel10.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("ණයට :");

        lblCreditSale.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblCreditSale.setForeground(new java.awt.Color(0, 0, 255));

        lblSamurdhi.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblSamurdhi.setForeground(new java.awt.Color(0, 0, 255));

        jLabel12.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("සමෘද්ධි :");

        jLabel13.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("තැන්පත් ණයට :");

        lblLoanSale.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblLoanSale.setForeground(new java.awt.Color(0, 0, 255));

        lblDryItems.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblDryItems.setForeground(new java.awt.Color(0, 0, 255));

        jLabel15.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("වියළි සලාක :");

        jLabel16.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("පෝෂණ :");

        lblPoshana.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblPoshana.setForeground(new java.awt.Color(0, 0, 255));

        lblCreditCard.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblCreditCard.setForeground(new java.awt.Color(0, 0, 255));

        jLabel17.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("ණයපත් :");

        jLabel18.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("මාරු යැවීම් :");

        lblSectionsTransfer.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblSectionsTransfer.setForeground(new java.awt.Color(0, 0, 255));

        jLabel19.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel19.setText("F2 වියදම් :");

        lblCashPurchases.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblCashPurchases.setForeground(new java.awt.Color(0, 0, 255));

        jLabel20.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("මුදලට ගැණුම් :");

        lblF2Expences.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblF2Expences.setForeground(new java.awt.Color(0, 0, 255));

        jLabel21.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText("වෙනත් ලැබීම් :");

        lblTotal.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 255));

        lblOtherReceivings.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblOtherReceivings.setForeground(new java.awt.Color(0, 0, 255));

        jLabel22.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText("මුළු එකතුව :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCashSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCreditSale, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoanSale, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(lblSamurdhi, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPoshana, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lblDryItems, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSectionsTransfer, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(lblCreditCard, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCashPurchases, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(lblF2Expences, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOtherReceivings, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCashSale, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLoanSale, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPoshana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSectionsTransfer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCashPurchases, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOtherReceivings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSamurdhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCreditSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCreditCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDryItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblF2Expences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText("ගැ.මිලට වටිනාකම :");

        lblInvVal.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblInvVal.setForeground(new java.awt.Color(0, 0, 255));

        jLabel25.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText("වි.මිලට වටිනාකම :");

        lblSellVal.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblSellVal.setForeground(new java.awt.Color(0, 0, 255));

        lblPurExpences.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblPurExpences.setForeground(new java.awt.Color(0, 0, 255));

        jLabel26.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText("ගැණුම් වියදම් :");

        jLabel27.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("වට්ටම් :");

        lblDiscount.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblDiscount.setForeground(new java.awt.Color(0, 0, 255));

        lblGoodReturns.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblGoodReturns.setForeground(new java.awt.Color(0, 0, 255));

        jLabel28.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText("ආපසු යැවීම් :");

        jLabel29.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel29.setText("නොමිලේ ලැබීම් :");

        lblFreeIssue.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblFreeIssue.setForeground(new java.awt.Color(0, 0, 255));

        lblSecTransIn.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblSecTransIn.setForeground(new java.awt.Color(0, 0, 255));

        jLabel30.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel30.setText("මාරු යැවීම් :");

        jLabel31.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel31.setText("ණය හිමි ගෙවීම් :");

        lblTotSupPayments.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblTotSupPayments.setForeground(new java.awt.Color(0, 0, 255));

        lblCashPur.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblCashPur.setForeground(new java.awt.Color(0, 0, 255));

        jLabel33.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel33.setText("මුදලට ගැණුම් :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInvVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSellVal, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(lblPurExpences, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFreeIssue, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(lblGoodReturns, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotSupPayments, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(lblSecTransIn, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCashPur, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblInvVal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDiscount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFreeIssue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotSupPayments, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCashPur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPurExpences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSellVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSecTransIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGoodReturns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel32.setText("ලාභය :");

        lblProfit.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblProfit.setForeground(new java.awt.Color(0, 0, 255));

        jLabel34.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel34.setText("පාඩුව :");

        lblLost.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblLost.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProfit, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLost, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addGap(1014, 1014, 1014))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblProfit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLost, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel35.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel35.setText("ගැණුම් :");

        lblOutPurPrice.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblOutPurPrice.setForeground(new java.awt.Color(0, 0, 255));

        jLabel36.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel36.setText("විකුණුම් :");

        lblOutSalePrice.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblOutSalePrice.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOutPurPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOutSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(825, 825, 825))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblOutPurPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOutSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel39.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel39.setText("ගැණුම් :");

        lblPurchase.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblPurchase.setForeground(new java.awt.Color(0, 0, 255));

        jLabel40.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("විකුණුම් :");

        lblSales.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblSales.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPurchase, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSales, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(832, 832, 832))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblPurchase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel41.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel41.setText("චෙක් අංකය :");

        jLabel42.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setText("බැංකු සටහනේ දිනය :");

        jLabel43.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setText("Remarks :");

        txtRemarks1002.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        txtRemarks1002.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(opt10021);
        opt10021.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        opt10021.setText("උපලබ්ධි වූ");
        opt10021.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt10021ActionPerformed(evt);
            }
        });

        buttonGroup1.add(opt10022);
        opt10022.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        opt10022.setText("අගරු වූ");
        opt10022.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt10022ActionPerformed(evt);
            }
        });

        jdtBankDate1002.setDateFormatString("yyyy-MM-dd");

        txtChequeNo1002.setEditable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdtBankDate1002, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtChequeNo1002, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemarks1002, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(opt10021)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opt10022)))
                .addContainerGap(434, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRemarks1002)
                    .addComponent(jLabel41)
                    .addComponent(txtChequeNo1002, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(opt10021, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(opt10022, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jdtBankDate1002, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel44.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel44.setText("චෙක් අංකය :");

        jLabel45.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel45.setText("බැංකු සටහනේ දිනය :");

        jLabel46.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel46.setText("Remarks :");

        txtRemarks1003.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        txtRemarks1003.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup2.add(opt10031);
        opt10031.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        opt10031.setText("නිශ්කාෂනය වූ");
        opt10031.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt10031ActionPerformed(evt);
            }
        });

        buttonGroup2.add(opt10032);
        opt10032.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        opt10032.setText("අගරු වූ");
        opt10032.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt10032ActionPerformed(evt);
            }
        });

        jdtBankDate1003.setDateFormatString("yyyy-MM-dd");

        txtChequeNo1003.setEditable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdtBankDate1003, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtChequeNo1003, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemarks1003, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(opt10031)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opt10032)))
                .addContainerGap(434, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtRemarks1003)
                    .addComponent(jLabel44)
                    .addComponent(txtChequeNo1003, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(opt10031, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(opt10032, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jdtBankDate1003, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel47.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel47.setText("එකතුව :");

        lblPurchase1.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        lblPurchase1.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPurchase1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(846, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
            .addComponent(lblPurchase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(135, 206, 250));

        jPanel1.setMinimumSize(new java.awt.Dimension(1006, 660));

        lblTitle.setBackground(new java.awt.Color(0, 114, 198));
        lblTitle.setFont(new java.awt.Font("Iskoola Pota", 1, 21)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("ණය ගිණුම් වර්ගය");
        lblTitle.setOpaque(true);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        dataTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataTable.setRowHeight(25);
        dataTable.setSelectionBackground(new java.awt.Color(0, 114, 198));
        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataTableMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dataTable);

        detailHeader.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("පාර්ශවකරු :");

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("දක්වා : ");

        fromDate.setDateFormatString("yyyy-MM-dd");
        fromDate.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        fromDate.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                fromDateAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        fromDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fromDateFocusGained(evt);
            }
        });
        fromDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fromDateMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fromDateMouseClicked(evt);
            }
        });
        fromDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fromDatePropertyChange(evt);
            }
        });

        toDate.setDateFormatString("yyyy-MM-dd");
        toDate.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        toDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                toDateMouseReleased(evt);
            }
        });
        toDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                toDatePropertyChange(evt);
            }
        });

        outsider.setEnabled(false);
        outsider.setFont(new java.awt.Font("AMALEE", 0, 15)); // NOI18N
        outsider.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outsiderItemStateChanged(evt);
            }
        });
        outsider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outsiderActionPerformed(evt);
            }
        });

        section.setEnabled(false);
        section.setFont(new java.awt.Font("AMALEE", 0, 15)); // NOI18N
        section.setMinimumSize(new java.awt.Dimension(41, 33));
        section.setPreferredSize(new java.awt.Dimension(41, 33));
        section.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("සිට :");

        jLabel7.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("අංශය :");

        sectionCheck.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        sectionCheck.setSelected(true);
        sectionCheck.setText("සියලු");
        sectionCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionCheckActionPerformed(evt);
            }
        });

        supplierCheck.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        supplierCheck.setSelected(true);
        supplierCheck.setText("සියලු");
        supplierCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierCheckActionPerformed(evt);
            }
        });

        btnSection.setText("...");
        btnSection.setEnabled(false);
        btnSection.setMaximumSize(new java.awt.Dimension(24, 33));
        btnSection.setMinimumSize(new java.awt.Dimension(24, 33));
        btnSection.setPreferredSize(new java.awt.Dimension(24, 33));
        btnSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSectionActionPerformed(evt);
            }
        });

        btnSupplier.setText("...");
        btnSupplier.setEnabled(false);
        btnSupplier.setMaximumSize(new java.awt.Dimension(24, 33));
        btnSupplier.setMinimumSize(new java.awt.Dimension(24, 33));
        btnSupplier.setPreferredSize(new java.awt.Dimension(24, 33));
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        cmbConfirmed.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        cmbConfirmed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbConfirmedActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("ස්වභාවය :");

        jLabel4.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("ජ.ගි. :");

        txtCurrentAct.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtCurrentAct.setToolTipText("සියලු ගිණුම් සඳහා හිස්ව තබන්න");
        txtCurrentAct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCurrentActKeyReleased(evt);
            }
        });
        txtCurrentAct.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCurrentActPropertyChange(evt);
            }
        });
        txtCurrentAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurrentActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detailHeaderLayout = new javax.swing.GroupLayout(detailHeader);
        detailHeader.setLayout(detailHeaderLayout);
        detailHeaderLayout.setHorizontalGroup(
            detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(cmbConfirmed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurrentAct, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outsider, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sectionCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supplierCheck))
                .addContainerGap())
        );
        detailHeaderLayout.setVerticalGroup(
            detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailHeaderLayout.createSequentialGroup()
                        .addComponent(sectionCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailHeaderLayout.createSequentialGroup()
                        .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSection, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(detailHeaderLayout.createSequentialGroup()
                                .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbConfirmed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCurrentAct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(detailHeaderLayout.createSequentialGroup()
                        .addGroup(detailHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outsider, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel11.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("බැර :");

        lblDR.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblDR.setForeground(new java.awt.Color(255, 0, 0));

        lblCBal.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblCBal.setForeground(new java.awt.Color(0, 0, 255));

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("ආරම්භක ශේෂය :");

        lblOBal.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblOBal.setForeground(new java.awt.Color(0, 0, 255));

        jLabel14.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("අවසාන ශේෂය :");

        lblCR.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblCR.setForeground(new java.awt.Color(45, 140, 45));

        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("හර :");

        jLabel23.setFont(new java.awt.Font("Iskoola Pota", 0, 13)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("එකතුව :");

        lblCashTotal.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblCashTotal.setForeground(new java.awt.Color(45, 140, 45));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblOBal, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(lblCBal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDR, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(lblCR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCashTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(288, 288, 288))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOBal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCBal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel11)
                                .addComponent(lblCR, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lblDR, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblCashTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(detailHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(detailHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(521, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ChequePopupFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ChequePopupFocusLost

    }//GEN-LAST:event_ChequePopupFocusLost

    private void ChequePopupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChequePopupKeyPressed

    }//GEN-LAST:event_ChequePopupKeyPressed

    private void dataTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMousePressed
//        if (evt.isPopupTrigger()) {
////            ChequePopup.setLocation(evt.getXOnScreen(), evt.getYOnScreen());
////            ChequePopup.setInvoker(this);
//            ChequePopup.setVisible(true);
//            // popup.grabFocus();
//        }
    }//GEN-LAST:event_dataTableMousePressed

    private void fromDateAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_fromDateAncestorAdded

    }//GEN-LAST:event_fromDateAncestorAdded

    private void fromDateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fromDateMouseReleased

    }//GEN-LAST:event_fromDateMouseReleased

    private void fromDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fromDateMouseClicked

    }//GEN-LAST:event_fromDateMouseClicked

    private void fromDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fromDatePropertyChange
        if (evt.getPropertyName().equals("date")) {
            if (!CheckNull.isNullable(code)) {
                if (!code.trim().isEmpty()) {
                    if (cmbConfirmed.getSelectedItem() != null) {
                        selectConfirmedCombo();
                        CommonJurnalFormComponentStateSave.setFrom(fromDate.getDate());
                    }
                }

            }

        }
    }//GEN-LAST:event_fromDatePropertyChange

    private void toDateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toDateMouseReleased

    }//GEN-LAST:event_toDateMouseReleased

    private void toDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_toDatePropertyChange
        if (evt.getPropertyName().equals("date")) {
            if (!CheckNull.isNullable(code)) {
                if (!code.trim().isEmpty()) {
                    if (cmbConfirmed.getSelectedItem() != null) {
                        selectConfirmedCombo();
                        CommonJurnalFormComponentStateSave.setTo(toDate.getDate());
                    }
                }

            }

        }
    }//GEN-LAST:event_toDatePropertyChange

    private void outsiderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outsiderItemStateChanged

    }//GEN-LAST:event_outsiderItemStateChanged

    private void sectionCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionCheckActionPerformed
        if (sectionCheck.isSelected()) {
            section.setEnabled(false);
            btnSection.setEnabled(false);
            CommonJurnalFormComponentStateSave.setSectionAll(true);
            CommonJurnalFormComponentStateSave.setSectionID(0);
        } else {
            section.setEnabled(true);
            btnSection.setEnabled(true);
            CommonJurnalFormComponentStateSave.setSectionAll(false);
            CommonJurnalFormComponentStateSave.setSectionID(((Item) section.getSelectedItem()).getId());
        }
    }//GEN-LAST:event_sectionCheckActionPerformed

    private void supplierCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierCheckActionPerformed
        if (supplierCheck.isSelected()) {
            outsider.setEnabled(false);
            btnSupplier.setEnabled(false);
            CommonJurnalFormComponentStateSave.setOutsiderAll(true);
            CommonJurnalFormComponentStateSave.setOutsiderID(0);
        } else {
            outsider.setEnabled(true);
            btnSupplier.setEnabled(true);
            CommonJurnalFormComponentStateSave.setOutsiderAll(false);
            CommonJurnalFormComponentStateSave.setOutsiderID(((Item) outsider.getSelectedItem()).getId());
        }
    }//GEN-LAST:event_supplierCheckActionPerformed

    private void btnSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSectionActionPerformed
        new FindSectionOrOutsider(TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 1, "අනු අංශය").setVisible(true);
    }//GEN-LAST:event_btnSectionActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        new FindSectionOrOutsider(TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 2, "පාර්ශවකරු").setVisible(true);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void itemChequePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemChequePrintActionPerformed
//        btnChequePrint.doClick();
        printCheque();
    }//GEN-LAST:event_itemChequePrintActionPerformed

    private void cmbConfirmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConfirmedActionPerformed
        selectConfirmedCombo();
        CommonJurnalFormComponentStateSave.setStateID(cmbConfirmed.getSelectedItem().getId());
    }//GEN-LAST:event_cmbConfirmedActionPerformed

    private void txtCurrentActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurrentActActionPerformed
        java.awt.Container c = this.getParent();
        while (!(c instanceof javax.swing.JFrame) && (c != null)) {
            c = c.getParent();
        }
        JFrame owner = null;
        if (c != null) {
            owner = (javax.swing.JFrame) c;

        }
        if (code.equals("01-01")) {
            txtCurrentAct.setFont(new Font("Iskoola Pota", Font.PLAIN, 15));
            new FindSectionOrOutsider(owner, TableHeaders.B16FINDER_TABLE_MODEL, TableHeaders.B16FINDER_TABLE_MODEL2, 60, "16B වර්ගය").setVisible(true);
        } else if (code.startsWith("05") || code.equals("01-09") || code.startsWith("08")) {
            txtCurrentAct.setFont(new Font("AMALEE", 0, 18));
            new FindAccount5Data(owner, true, 10, 0).setVisible(true);
        } else {
            txtCurrentAct.setFont(new Font("AMALEE", 0, 18));
            new FindAccount5Data(owner, true, 10, 2).setVisible(true);
        }
    }//GEN-LAST:event_txtCurrentActActionPerformed

    private void txtCurrentActKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCurrentActKeyReleased
        if (txtCurrentAct.getText().trim().isEmpty()) {
            txtCurrentAct.setId(0);
        }
    }//GEN-LAST:event_txtCurrentActKeyReleased

    private void sectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionActionPerformed
        selectConfirmedCombo();
        CommonJurnalFormComponentStateSave.setSectionAll(false);
        CommonJurnalFormComponentStateSave.setSectionID(((Item) section.getSelectedItem()).getId());
    }//GEN-LAST:event_sectionActionPerformed

    private void outsiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outsiderActionPerformed
        selectConfirmedCombo();
        CommonJurnalFormComponentStateSave.setOutsiderAll(false);
        CommonJurnalFormComponentStateSave.setOutsiderID(((Item) outsider.getSelectedItem()).getId());
    }//GEN-LAST:event_outsiderActionPerformed

    private void fromDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fromDateFocusGained

    }//GEN-LAST:event_fromDateFocusGained

    private void ItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemRefreshActionPerformed
        selectConfirmedCombo();
    }//GEN-LAST:event_ItemRefreshActionPerformed

    private void itemRealizedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRealizedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemRealizedActionPerformed

    private void ChequePopupAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ChequePopupAncestorAdded

    }//GEN-LAST:event_ChequePopupAncestorAdded

    private void itemVouchersAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVouchersAllActionPerformed
        try {
            int[] indexesTobeAligned = null;
            boolean isAlignable = false;
            boolean isAmali = false;
            boolean isIskoolaPota = false;
            boolean isNumber = false;
            int[] amaliIndexes = null;
            int[] iskoolaPotaIndexes = null;
            int[] isNumberIndexes = null;
            boolean hasBackColor = false;
            int backColorIndexes[] = null;
            Color backColor = null;
            Color selectionBackColor = null;
            List<Object> inputs = new ArrayList<>();
            inputs.add(0);
            inputs.add(txtCurrentAct.getId());
            inputs.add(df.format(fromDate.getDate()));
            inputs.add(df.format(toDate.getDate()));
            if (sectionCheck.isSelected()) {
                inputs.add(0);
            } else {
                inputs.add(((Item) section.getSelectedItem()).getId());
            }
            if (supplierCheck.isSelected()) {
                inputs.add(0);
            } else {
                inputs.add(((Item) outsider.getSelectedItem()).getId());
            }
            inputs.add(0);

            String ssp = "ssp_F28_LoadCombo_ViewDRNote";

            int outputCount = 17;

            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;

            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, ssp, outputCount);
            dtm.setRowCount(0);
            for (List<Object> output : outputs) {
                Vector v = new Vector();
                for (Object object : output) {
                    v.add(object);
                }
                dtm.addRow(v);
            }

            if (isNumber) {
                for (int numberIndex : isNumberIndexes) {
                    TableColumnFontChanger.setFont(dataTable, numberIndex, new Font("Tahoma", Font.BOLD, 12));
                }
            }
            if (hasBackColor) {
                for (int backColorIndex : backColorIndexes) {
                    TableColumnFontChanger.setFont(dataTable, backColorIndex, new Font("Tahoma", Font.BOLD, 12), backColor, selectionBackColor);
                }
            }
            if (isAlignable) {
                TableColumnRightAlign.alignMany(dataTable, indexesTobeAligned);
            }
            if (isIskoolaPota) {
                for (int iskoolaPotaIndex : iskoolaPotaIndexes) {
                    TableColumnFontChanger.setIskolaPotha(dataTable, iskoolaPotaIndex);
                }
            }
            if (isAmali) {
                for (int amaliIndex : amaliIndexes) {
                    TableColumnFontChanger.setAmalee(dataTable, amaliIndex);
                }
            }
            dataTable.packAll();
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemVouchersAllActionPerformed

    private void itemNewVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewVoucherActionPerformed
        newCommonJournalForm();
    }//GEN-LAST:event_itemNewVoucherActionPerformed

    private void itemEditVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditVoucherActionPerformed
        editCommonJournalForm();
    }//GEN-LAST:event_itemEditVoucherActionPerformed

    private void itemJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJournalActionPerformed
        doubleEntryView();
    }//GEN-LAST:event_itemJournalActionPerformed

    private void itemPrintVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrintVoucherActionPerformed
        print();
    }//GEN-LAST:event_itemPrintVoucherActionPerformed

    private void itemVoucherRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVoucherRefreshActionPerformed
        selectConfirmedCombo();
    }//GEN-LAST:event_itemVoucherRefreshActionPerformed

    private void itemNewStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewStockActionPerformed
        newCommonJournalForm();
    }//GEN-LAST:event_itemNewStockActionPerformed

    private void itemEditStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditStockActionPerformed
        editCommonJournalForm();
    }//GEN-LAST:event_itemEditStockActionPerformed

    private void itemJournalStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJournalStockActionPerformed
        doubleEntryView();
    }//GEN-LAST:event_itemJournalStockActionPerformed

    private void itemRefreshStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRefreshStockActionPerformed
        selectConfirmedCombo();
    }//GEN-LAST:event_itemRefreshStockActionPerformed

    private void itemNewCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewCashierActionPerformed
        newCommonJournalForm();
    }//GEN-LAST:event_itemNewCashierActionPerformed

    private void itemEditCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditCashierActionPerformed
        editCommonJournalForm();
    }//GEN-LAST:event_itemEditCashierActionPerformed

    private void itemJournalCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJournalCashierActionPerformed
        doubleEntryView();
    }//GEN-LAST:event_itemJournalCashierActionPerformed

    private void itemRefreshCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRefreshCashierActionPerformed
        selectConfirmedCombo();
    }//GEN-LAST:event_itemRefreshCashierActionPerformed

    private void itemConfirmPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfirmPrintActionPerformed
        confirmTRN();
    }//GEN-LAST:event_itemConfirmPrintActionPerformed

    private void itemPrintCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrintCashierActionPerformed
        print();
    }//GEN-LAST:event_itemPrintCashierActionPerformed

    private void itemNewJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewJournalActionPerformed
        newCommonJournalForm();
    }//GEN-LAST:event_itemNewJournalActionPerformed

    private void itemEditJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditJournalActionPerformed
        editCommonJournalForm();
    }//GEN-LAST:event_itemEditJournalActionPerformed

    private void itemPrintJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrintJournalActionPerformed

    }//GEN-LAST:event_itemPrintJournalActionPerformed

    private void itemJournalJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJournalJournalActionPerformed
        doubleEntryView();
    }//GEN-LAST:event_itemJournalJournalActionPerformed

    private void itemRefreshJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRefreshJournalActionPerformed
        selectConfirmedCombo();
    }//GEN-LAST:event_itemRefreshJournalActionPerformed

    private void itemCancelJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelJournalActionPerformed
        cancelTransaction();
    }//GEN-LAST:event_itemCancelJournalActionPerformed

    private void itemJournalChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJournalChequeActionPerformed
        doubleEntryView();
    }//GEN-LAST:event_itemJournalChequeActionPerformed

    private void itemNewRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewRBActionPerformed
        newCommonJournalForm();
    }//GEN-LAST:event_itemNewRBActionPerformed

    private void itemEditRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditRBActionPerformed
        editCommonJournalForm();
    }//GEN-LAST:event_itemEditRBActionPerformed

    private void itemJournalRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJournalRBActionPerformed
        doubleEntryView();
    }//GEN-LAST:event_itemJournalRBActionPerformed

    private void itemPrintRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrintRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemPrintRBActionPerformed

    private void itemRBRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRBRefreshActionPerformed
        selectConfirmedCombo();
    }//GEN-LAST:event_itemRBRefreshActionPerformed

    private void itemCancelCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelCashierActionPerformed
        cancelTransaction();
    }//GEN-LAST:event_itemCancelCashierActionPerformed

    private void itemCancelStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelStockActionPerformed
        cancelTransaction();
    }//GEN-LAST:event_itemCancelStockActionPerformed

    private void itemCancelChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelChequeActionPerformed
        cancelTransaction();
    }//GEN-LAST:event_itemCancelChequeActionPerformed

    private void itemRBCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRBCancelActionPerformed
        cancelTransaction();
    }//GEN-LAST:event_itemRBCancelActionPerformed

    private void itemVoucherCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVoucherCancelActionPerformed
        cancelTransaction();
    }//GEN-LAST:event_itemVoucherCancelActionPerformed

    private void txtCurrentActPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCurrentActPropertyChange
        if (evt.getPropertyName().equals("date")) {
            if (!CheckNull.isNullable(code)) {
                if (!code.trim().isEmpty()) {
                    if (cmbConfirmed.getSelectedItem() != null) {
                        selectConfirmedCombo();
                        CommonJurnalFormComponentStateSave.setTo(toDate.getDate());
                    }
                }

            }

        }
    }//GEN-LAST:event_txtCurrentActPropertyChange

    private void ItemMaxChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemMaxChequeActionPerformed
        if (dataTable.getSelectedRow() > -1) {
            new CHQModuleMaxInfo(null, true, String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), "උපරිම චෙක්පත්").setVisible(true);
        }
    }//GEN-LAST:event_ItemMaxChequeActionPerformed

    private void itemPrint16BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrint16BActionPerformed
        print16B(1);
    }//GEN-LAST:event_itemPrint16BActionPerformed

    private void itemAdvancedChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAdvancedChequeActionPerformed
        if (dataTable.getSelectedRow() > -1) {
            new CHQModuleMaxInfo(null, true, String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), "අත්තිකාරම් චෙක්පත්").setVisible(true);
        }
    }//GEN-LAST:event_itemAdvancedChequeActionPerformed

    private void itemPrint9BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrint9BActionPerformed
        print9B(1);
    }//GEN-LAST:event_itemPrint9BActionPerformed

    private void itemPrint16B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrint16B1ActionPerformed
        print16B(2);
    }//GEN-LAST:event_itemPrint16B1ActionPerformed

    private void itemPrint9B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrint9B1ActionPerformed
        print9B(2);
    }//GEN-LAST:event_itemPrint9B1ActionPerformed

    private void itemCopyJournalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCopyJournalActionPerformed
        if (dataTable.getSelectedRow() > -1) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)));
                inputs.add("@reID");
                System.out.println("copy" + inputs);
                List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_DuplicateJournal", 1);
                switch (code) {
                    case "05-01":
                        new CommonDRNote(null, true, GLCodes.DRNOTE, lblTitle.getText(), String.valueOf(output.get(0))).setVisible(true);
                        break;
                    case "05-02":
                        new CommonDRNote(null, true, GLCodes.CRNOTE, lblTitle.getText(), String.valueOf(output.get(0))).setVisible(true);
                        break;
                    case "05-03":
                        new CommonDRNote(null, true, GLCodes.BANKDRNOTE, lblTitle.getText(), String.valueOf(output.get(0))).setVisible(true);
                        break;
                    case "05-04":
                        new CommonDRNote(null, true, GLCodes.BANKCRNOTE, lblTitle.getText(), String.valueOf(output.get(0))).setVisible(true);
                        break;
                    case "05-05":
                        new CommonDRNote(null, true, GLCodes.COMMONDRNOTE, lblTitle.getText(), String.valueOf(output.get(0))).setVisible(true);
                        break;
                    case "05-06":
                        new CommonDRNote(null, true, GLCodes.COMMONCRNOTE, lblTitle.getText(), String.valueOf(output.get(0))).setVisible(true);
                        break;
                    default:
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_itemCopyJournalActionPerformed

    private void itemChequeFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemChequeFindActionPerformed
        try {
            String chequeNo = JOptionPane.showInputDialog(this, "චෙක්පත් අංකය යොදන්න", "චෙක්පත් අංකය", JOptionPane.INFORMATION_MESSAGE);
            if (!chequeNo.trim().isEmpty()) {
                dtm.setRowCount(0);
                List<Object> inputs = new ArrayList<>();
                inputs.add(chequeNo);
                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_FindChequeIssued", 15);
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm.addRow(v);
                }
                dataTable.packAll();
                viewTotal(code);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemChequeFindActionPerformed

    private void itemPrint9B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrint9B2ActionPerformed
        print9B(3);
    }//GEN-LAST:event_itemPrint9B2ActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        if (code.equals("10-02")) {
            if (dataTable.getSelectedRow() > -1) {
                try {
                    ManagerOperationalNavigation.navigateTo(jPanel10, jPanel3);
                    txtChequeNo1002.setId(Integer.parseInt(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))));
                    txtChequeNo1002.setText(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 3)));
                    if (String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 9)).equals("null") || String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 9)) == null) {
                        jdtBankDate1002.setDate(data.getSystemDate());
                    } else {
                        jdtBankDate1002.setDate(FormatConstants.dateFormat.parse(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 9))));
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (code.equals("10-03")) {
            if (dataTable.getSelectedRow() > -1) {
                try {
                    ManagerOperationalNavigation.navigateTo(jPanel11, jPanel3);
                    txtChequeNo1003.setId(Integer.parseInt(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))));
                    txtChequeNo1003.setText(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 3)));
                    if (String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 7)).equals("null") || String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 7)) == null) {
                        jdtBankDate1003.setDate(data.getSystemDate());
                    } else {
                        jdtBankDate1003.setDate(FormatConstants.dateFormat.parse(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 7))));
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_dataTableMouseClicked

    private void opt10021ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt10021ActionPerformed
        if (opt10021.isSelected()) {
            updateDepositedCheque(1);
        }
    }//GEN-LAST:event_opt10021ActionPerformed

    private void opt10022ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt10022ActionPerformed
        if (opt10022.isSelected()) {
            updateDepositedCheque(2);
        }
    }//GEN-LAST:event_opt10022ActionPerformed

    private void opt10031ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt10031ActionPerformed
        if (opt10031.isSelected()) {
            updateIssued(1);
        }
    }//GEN-LAST:event_opt10031ActionPerformed

    private void opt10032ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt10032ActionPerformed
        if (opt10032.isSelected()) {
            updateIssued(2);
        }
    }//GEN-LAST:event_opt10032ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu ChequePopup;
    private javax.swing.JMenuItem ItemMaxCheque;
    public static javax.swing.JMenuItem ItemRefresh;
    private javax.swing.JPopupMenu VoucherPopup;
    private javax.swing.JButton btnSection;
    private javax.swing.JButton btnSupplier;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPopupMenu cashierPopup;
    public static org.nbs.components.HiddenItemComboBox cmbConfirmed;
    private org.jdesktop.swingx.JXTable dataTable;
    private javax.swing.JPanel detailHeader;
    static com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JMenuItem itemAdvancedCheque;
    private javax.swing.JMenuItem itemCancelCashier;
    private javax.swing.JMenuItem itemCancelCheque;
    private javax.swing.JMenuItem itemCancelJournal;
    private javax.swing.JMenuItem itemCancelStock;
    private javax.swing.JMenuItem itemChequeFind;
    private javax.swing.JMenuItem itemChequePrint;
    private javax.swing.JMenuItem itemConfirmPrint;
    public static javax.swing.JMenuItem itemCopyJournal;
    public static javax.swing.JMenuItem itemEditCashier;
    public static javax.swing.JMenuItem itemEditJournal;
    public static javax.swing.JMenuItem itemEditRB;
    public static javax.swing.JMenuItem itemEditStock;
    public static javax.swing.JMenuItem itemEditVoucher;
    private javax.swing.JMenuItem itemJournal;
    private javax.swing.JMenuItem itemJournalCashier;
    private javax.swing.JMenuItem itemJournalCheque;
    private javax.swing.JMenuItem itemJournalJournal;
    private javax.swing.JMenuItem itemJournalRB;
    private javax.swing.JMenuItem itemJournalStock;
    public static javax.swing.JMenuItem itemNewCashier;
    public static javax.swing.JMenuItem itemNewJournal;
    public static javax.swing.JMenuItem itemNewRB;
    public static javax.swing.JMenuItem itemNewStock;
    public static javax.swing.JMenuItem itemNewVoucher;
    private javax.swing.JMenuItem itemPrint16B;
    private javax.swing.JMenuItem itemPrint16B1;
    private javax.swing.JMenuItem itemPrint9B;
    private javax.swing.JMenuItem itemPrint9B1;
    private javax.swing.JMenuItem itemPrint9B2;
    public static javax.swing.JMenuItem itemPrintCashier;
    public static javax.swing.JMenuItem itemPrintJournal;
    private javax.swing.JMenuItem itemPrintRB;
    private javax.swing.JMenuItem itemPrintVoucher;
    private javax.swing.JMenuItem itemRBCancel;
    public static javax.swing.JMenuItem itemRBRefresh;
    private javax.swing.JMenuItem itemRealized;
    public static javax.swing.JMenuItem itemRefreshCashier;
    public static javax.swing.JMenuItem itemRefreshJournal;
    public static javax.swing.JMenuItem itemRefreshStock;
    private javax.swing.JMenuItem itemVoucherCancel;
    public static javax.swing.JMenuItem itemVoucherRefresh;
    private javax.swing.JMenuItem itemVouchersAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JPopupMenu.Separator jSeparator25;
    private javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JPopupMenu.Separator jSeparator29;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator30;
    private javax.swing.JPopupMenu.Separator jSeparator32;
    private javax.swing.JPopupMenu.Separator jSeparator33;
    private javax.swing.JPopupMenu.Separator jSeparator34;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private com.toedter.calendar.JDateChooser jdtBankDate1002;
    private com.toedter.calendar.JDateChooser jdtBankDate1003;
    private javax.swing.JPopupMenu journalPopup;
    private javax.swing.JLabel lblCBal;
    private javax.swing.JLabel lblCR;
    private javax.swing.JLabel lblCashPur;
    private javax.swing.JLabel lblCashPurchases;
    private javax.swing.JLabel lblCashSale;
    private javax.swing.JLabel lblCashTotal;
    private javax.swing.JLabel lblCreditCard;
    private javax.swing.JLabel lblCreditSale;
    private javax.swing.JLabel lblDR;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblDryItems;
    private javax.swing.JLabel lblF2Expences;
    private javax.swing.JLabel lblFreeIssue;
    private javax.swing.JLabel lblGoodReturns;
    private javax.swing.JLabel lblInvVal;
    private javax.swing.JLabel lblLoanSale;
    private javax.swing.JLabel lblLost;
    private javax.swing.JLabel lblOBal;
    private javax.swing.JLabel lblOtherReceivings;
    private javax.swing.JLabel lblOutPurPrice;
    private javax.swing.JLabel lblOutSalePrice;
    private javax.swing.JLabel lblPoshana;
    private javax.swing.JLabel lblProfit;
    private javax.swing.JLabel lblPurExpences;
    private javax.swing.JLabel lblPurchase;
    private javax.swing.JLabel lblPurchase1;
    private javax.swing.JLabel lblSales;
    private javax.swing.JLabel lblSamurdhi;
    private javax.swing.JLabel lblSecTransIn;
    private javax.swing.JLabel lblSectionsTransfer;
    private javax.swing.JLabel lblSellVal;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotSupPayments;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JRadioButton opt10021;
    private javax.swing.JRadioButton opt10022;
    private javax.swing.JRadioButton opt10031;
    private javax.swing.JRadioButton opt10032;
    public static org.nbs.components.HiddenItemComboBox outsider;
    private javax.swing.JPopupMenu ruralBankPopup;
    public static org.nbs.components.HiddenItemComboBox section;
    static javax.swing.JCheckBox sectionCheck;
    private javax.swing.JPopupMenu stockPopup;
    static javax.swing.JCheckBox supplierCheck;
    static com.toedter.calendar.JDateChooser toDate;
    private org.nbs.components.HiddenIDTextField txtChequeNo1002;
    private org.nbs.components.HiddenIDTextField txtChequeNo1003;
    public static org.nbs.components.HiddenIDTextField txtCurrentAct;
    private javax.swing.JTextField txtRemarks1002;
    private javax.swing.JTextField txtRemarks1003;
    // End of variables declaration//GEN-END:variables

    private void loadCombos() throws NotBoundException, MalformedURLException, ClassNotFoundException, RemoteException, InterruptedException, SQLException {

    }

    private void comboLoad() throws NotBoundException, MalformedURLException, ClassNotFoundException, RemoteException, SQLException, InterruptedException {
        List<Object> inputs = new ArrayList<>();
        inputs.add(2);
        inputs.add(fromDate.getDate());
        inputs.add(toDate.getDate());
        inputs.add(data.getBranchID());
        inputs.add(1);
        List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock", 2);
        Vector v = new Vector();
        for (List<Object> output : outputs) {
            v.add(new Item(Integer.parseInt(output.get(0).toString()), output.get(1).toString()));
        }
        outsider.setModel(new DefaultComboBoxModel(v));
//        ((DefaultComboBoxModel) outsider.getModel()).setSelectedItem(new Item(data.getBranchID(), data.getBranchName()));
        outsider.setEnabled(false);

        if (code.equals("07-01")) {
            inputs.set(0, 6);
        } else {
            inputs.set(0, 1);
        }
        List<List<Object>> typeOutputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock", 2);

        Vector tyV = new Vector();
        for (List<Object> typeOutput : typeOutputs) {
            tyV.add(new Item(Integer.parseInt(typeOutput.get(0).toString()), typeOutput.get(1).toString()));
        }

        section.setModel(new DefaultComboBoxModel(tyV));
        section.setEnabled(false);
//        sectionCheck.setSelected(true);
//        supplierCheck.setSelected(true);
    }

    private void onFormLord(int node, int type) {

    }

    private void setHeaderHeight() {
    }

    private void setDataTableSearchable(String[] tableHeader) {
        int columnIndices[] = new int[tableHeader.length];
        for (int i = 0; i < tableHeader.length; i++) {
            columnIndices[i] = i;
        }

        tableSearchable.setSearchColumnIndices(columnIndices);
        tableSearchable.setBackground(Color.white);
        TableRowFilterSupport.forTable(dataTable).searchable(true).apply();
        dataTable.packAll();
    }

    private void loadStatuses(String code) {
        try {
            List<Object> inputs = new ArrayList<>();
            if (code.equals("01-09")) {
                inputs.add(5);
            } else if (code.startsWith("01")) {
                inputs.add(1);
            } else if (code.startsWith("02")) {
                inputs.add(2);
            } else if (code.startsWith("03")) {
                inputs.add(3);
            } else if (code.startsWith("04")) {
                inputs.add(4);
            } else if (code.startsWith("05")) {
                inputs.add(5);
            } else if (code.startsWith("06")) {
                inputs.add(6);
            } else if (code.startsWith("07")) {
                inputs.add(7);
            } else if (code.startsWith("08")) {
                inputs.add(8);
            } else if (code.startsWith("10")) {
                inputs.add(10);
            }

            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewTranStatus", 2);
            Vector v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(0)) + " - " + String.valueOf(output.get(1))));
            }
            cmbConfirmed.setModel(new DefaultComboBoxModel<>(v));
            if (code.equals("10-02")) {
                cmbConfirmed.setSelectedHiddenItem(11);
            } else if (code.equals("10-03")) {
                cmbConfirmed.setSelectedHiddenItem(9);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
//        setSearchComponentStatus();

    }

    private void selectConfirmedCombo() {
        getState();
        List<Object> inputs = new ArrayList<>();
        String ssp = "";
        int outputCount = 0;
        dtm.setRowCount(0);
        int[] indexesTobeAligned = null;
        boolean isAlignable = false;
        boolean isAmali = false;
        boolean isIskoolaPota = false;
        boolean isNumber = false;
        int[] amaliIndexes = null;
        int[] iskoolaPotaIndexes = null;
        int[] isNumberIndexes = null;
        boolean hasBackColor = false;
        int backColorIndexes[] = null;
        Color backColor = null;
        Color selectionBackColor = null;

        dtm.setColumnIdentifiers(tableHeaders);
        if (type == Nodes.NEWOPENEDACCOUNTS) {
            dataTable.removeColumn(dataTable.getColumn("BranchID"));
            dataTable.removeColumn(dataTable.getColumn("DepTypeID"));
            dataTable.removeColumn(dataTable.getColumn("CustomerID"));
            dataTable.removeColumn(dataTable.getColumn("AcctStatusID"));
        } else if (type == Nodes.NEWCLOSEDACCOUNTS) {
            dataTable.removeColumn(dataTable.getColumn("BranchID"));
            dataTable.removeColumn(dataTable.getColumn("DepTypeID"));
            dataTable.removeColumn(dataTable.getColumn("CustomerID"));
            dataTable.removeColumn(dataTable.getColumn("AcctStatusID"));
        }
        if (type == Nodes.NEWOPENEDACCOUNTS) {
            inputs.add(6);
            ssp = "ssp_bnk_LoadCombo_ViewManager";
            outputCount = 14;
        } else if (type == Nodes.NEWCLOSEDACCOUNTS) {
            inputs.add(7);
            ssp = "ssp_bnk_LoadCombo_ViewManager";
            outputCount = 14;
        } else if (code.equals("01-01")) {
            inputs.add(txtCurrentAct.getId());
            ssp = "ssp_F28_LoadCombo_ViewStock_16B";
            int[] indexes = {5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {2, 3, 4};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 21};
//            int[] iskoolapota = {2};
//            iskoolaPotaIndexes = iskoolapota;
//            isIskoolaPota = true;
            outputCount = JurnalTableHeaders._01_01.length;
        } else if (code.equals("01-02")) {
            inputs.add(2);
            ssp = "ssp_F28_LoadCombo_ViewStock_16B";
            int[] indexes = {4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {2, 3};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20};
            outputCount = JurnalTableHeaders._01_02.length;
        } else if (code.equals("01-03")) {
            inputs.add(3);
            int[] indexes = {4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
            indexesTobeAligned = indexes;
            isAlignable = true;
            ssp = "ssp_F28_LoadCombo_ViewStock_16B";
            int[] ama = {2, 3};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20};
            outputCount = JurnalTableHeaders._01_03.length;
        } else if (code.equals("01-04")) {
            inputs.add(4);
            int[] indexes = {4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
            indexesTobeAligned = indexes;
            isAlignable = true;
            ssp = "ssp_F28_LoadCombo_ViewStock_16B";
            int[] ama = {2, 3};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20};
            outputCount = JurnalTableHeaders._01_04.length;
        } else if (code.equals("01-05")) {
            inputs.add(3);
            //11
            if (data.isB9old()) {
                int[] indexes = {2, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 22};
                indexesTobeAligned = indexes;
                isAlignable = true;
                int[] ama = {3, 4, 5};
                amaliIndexes = ama;
                isAmali = true;
                isNumber = true;
                isNumberIndexes = new int[]{0, 1, 2, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 19, 21, 22};
                ssp = "ssp_F28_LoadCombo_ViewStock_9B";
                outputCount = 23;
            } else {
                int[] indexes = {6, 7};
                indexesTobeAligned = indexes;
                isAlignable = true;
                int[] ama = {3, 4, 5};
                amaliIndexes = ama;
                isAmali = true;
                isNumber = true;
                isNumberIndexes = new int[]{0, 1, 2, 6, 7, 9, 10};
                ssp = "ssp_F28_LoadCombo_ViewStock_9B_New";
                outputCount = 11;

            }

        } else if (code.equals("01-06")) {
            inputs.add(3);
            int[] indexes = {8, 9};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {14, 13, 12};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{14};
            ssp = "ssp_F28_LoadCombo_ViewStock_F17";
            outputCount = 15;
        } else if (code.equals("01-07")) {
            inputs.add(3);
            int[] indexes = {9, 10, 11, 12};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {17, 16, 15};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{17};
            ssp = "ssp_F28_LoadCombo_ViewStock_F18";
            outputCount = 20;
        } else if (code.equals("01-08")) {
            inputs.add(3);
            int[] indexes = {9, 10};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {15, 14, 13};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{15};
            ssp = "ssp_F28_LoadCombo_ViewStock_F19";
            outputCount = 18;
        } else if (code.equals("01-09")) {
            inputs.add(GLCodes.C9SALE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("01-11")) {
            inputs.add(3);
            int[] indexes = {8, 9};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 5};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 2, 4, 6};
            ssp = "ssp_F28_LoadCombo_ViewStock_Trn_Adj";
            outputCount = 10;
        } else if (code.equals("02-01")) {
            inputs.add(GLCodes.CASHRECEIVINGS);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 7};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{5};
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 6, 7};
            ssp = "ssp_F28_LoadCombo_ViewCashier";
            outputCount = 8;
        } else if (code.equals("02-02")) {
            inputs.add(GLCodes.STAMPRECEIVINGS);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 7};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 6, 7};
            ssp = "ssp_F28_LoadCombo_ViewCashier";
            outputCount = 8;
        } else if (code.equals("02-03")) {
            inputs.add(GLCodes.CASHTIES);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 7};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 6, 7};
            ssp = "ssp_F28_LoadCombo_ViewCashier";
            outputCount = 8;
        } else if (code.equals("02-04")) {
            inputs.add(GLCodes.STAMPTIES);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 7};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 6, 7};
            ssp = "ssp_F28_LoadCombo_ViewCashier";
            outputCount = 8;
        } else if (code.equals("03-01")) {
            inputs.add(GLCodes.PURCHASES);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{15};
            outputCount = 17;
        } else if (code.equals("03-02")) {
            inputs.add(GLCodes.CREDITORSPAYMENTS);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 6, 8, 9, 10, 11, 12, 13, 15};
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            outputCount = 17;
        } else if (code.equals("03-03")) {
            inputs.add(GLCodes.OTHERPAYMENTS);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 6, 8, 9, 10, 11, 12, 13, 15};
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            outputCount = 17;
        } else if (code.equals("03-04")) {
            inputs.add(GLCodes.ADVANCEPURCHASES);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{15};
            outputCount = 17;
        } else if (code.equals("03-05")) {
            inputs.add(GLCodes.OTHERADVANCES);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{15};
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            outputCount = 17;
        } else if (code.equals("03-06")) {
            inputs.add(GLCodes.LOANPAYMENTS);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{15};
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            outputCount = 17;
        } else if (code.equals("03-07")) {
            inputs.add(GLCodes.DEPOSITPAYMENTS);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {2, 6, 8, 9, 11, 12, 13};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {3, 4, 5};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {7};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{15};
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            outputCount = 17;
        } else if (code.equals("03-08")) {
            inputs.add(0);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {5};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {6, 7};
            amaliIndexes = ama;
            isAmali = true;
//            int[] iskoolapota = {7};
//            iskoolaPotaIndexes = iskoolapota;
//            isIskoolaPota = true;
//            isNumber = true;
//            isNumberIndexes = new int[]{15};
            ssp = "ssp_F28_LoadCombo_ViewPayment_Approval";
            outputCount = 10;
        } else if (code.equals("04-01")) {
            inputs.add(1);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3, 4, 5, 7, 9, 10, 12};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {11};
            amaliIndexes = ama;
            isAmali = true;
            int[] iskoolapota = {6};
            iskoolaPotaIndexes = iskoolapota;
            isIskoolaPota = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 12};
            hasBackColor = true;
            backColorIndexes = new int[]{5};
            backColor = Color.BLUE;
            selectionBackColor = Color.WHITE;
            ssp = "ssp_F28_LoadCombo_ViewChequesToIssues";
            outputCount = 15;
        } else if (code.equals("04-02")) {
            inputs.add(1);
            int[] indexes = {7, 8, 9};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {6};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 3, 4, 14, 15};
            ssp = "ssp_F28_LoadCombo_ViewChequesDeposit";
            outputCount = 16;
        } else if (code.equals("05-01")) {
            inputs.add(GLCodes.DRNOTE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("05-02")) {
            inputs.add(GLCodes.CRNOTE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("05-03")) {
            inputs.add(GLCodes.BANKDRNOTE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("05-04")) {
            inputs.add(GLCodes.BANKCRNOTE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("05-05")) {
            inputs.add(GLCodes.COMMONDRNOTE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("05-06")) {
            inputs.add(GLCodes.COMMONCRNOTE);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("07-01")) {
            inputs.add(1);
            int[] indexes = {6, 7, 8};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5};
            amaliIndexes = ama;
            isAmali = true;
            ssp = "ssp_F28_LoadCombo_View9E";
            outputCount = 15;
        } else if (code.equals("08-01")) {
            inputs.add(GLCodes.COMMONADMINDR);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("08-02")) {
            inputs.add(GLCodes.COMMONADMINCR);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("08-03")) {
            inputs.add(GLCodes.COMMONEXPENCEMATCHDR);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("08-04")) {
            inputs.add(GLCodes.COMMONEXPENCEMATCHCR);
            inputs.add(txtCurrentAct.getId());
            int[] indexes = {3};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {4, 5, 6};
            amaliIndexes = ama;
            isAmali = true;
            iskoolaPotaIndexes = new int[]{7};
            isIskoolaPota = true;
            ssp = "ssp_F28_LoadCombo_ViewDRNote";
            isNumber = true;
            isNumberIndexes = new int[]{3};
            outputCount = 12;
        } else if (code.equals("10-02")) {
            inputs.add(1);
            int[] indexes = {7, 8, 9};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {6};
            amaliIndexes = ama;
            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 3, 4};
            ssp = "ssp_F28_LoadCombo_ViewChequesDeposit";
            outputCount = 11;
        } else if (code.equals("10-03")) {
            inputs.add(1);
            int[] indexes = {5};
            indexesTobeAligned = indexes;
            isAlignable = true;
            int[] ama = {6};
            amaliIndexes = ama;
//            isAmali = true;
            isNumber = true;
            isNumberIndexes = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
            ssp = "ssp_F28_LoadCombo_ViewChequesIssuesed";
            outputCount = 9;
        }
        inputs.add(df.format(fromDate.getDate()));
        inputs.add(df.format(toDate.getDate()));
        if (!code.startsWith("10")) {
            if (sectionCheck.isSelected()) {
                inputs.add(0);
            } else {
                inputs.add(((Item) section.getSelectedItem()).getId());
            }
            if (supplierCheck.isSelected()) {
                inputs.add(0);
            } else {
                inputs.add(((Item) outsider.getSelectedItem()).getId());
            }
        } else {
            inputs.add(txtCurrentAct.getId());
        }

        inputs.add(cmbConfirmed.getSelectedItem().getId());
        if (code.equals("04-01") && cmbConfirmed.getSelectedItem().getId() != 5) {
            itemChequePrint.setEnabled(false);
            itemConfirmPrint.setEnabled(false);
            itemChequePrint.setEnabled(false);
        } else {
            itemChequePrint.setEnabled(true);
            itemConfirmPrint.setEnabled(true);
            itemChequePrint.setEnabled(true);
        }
        if (code.equals("04-01") && cmbConfirmed.getSelectedItem().getId() != 6) {
            ItemMaxCheque.setEnabled(false);
        } else {
            ItemMaxCheque.setEnabled(true);
        }
        if (code.equals("04-01") && cmbConfirmed.getSelectedItem().getId() != 7) {
            itemAdvancedCheque.setEnabled(false);
        } else {
            itemAdvancedCheque.setEnabled(true);
        }
//        for (Object input : inputs) {
//            System.out.println(input);
//        }
        try {
            System.out.println(inputs + ssp);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, ssp, outputCount);
            dtm.setColumnCount(outputCount);
            if (outputs != null) {
                if (!outputs.isEmpty()) {
                    for (List<Object> output : outputs) {
                        Vector v = new Vector();
                        for (Object cellData : output) {
                            v.add(cellData);
                        }
                        dtm.addRow(v);
                    }
                }
            }

            if (isNumber) {
                for (int numberIndex : isNumberIndexes) {
                    TableColumnFontChanger.setFont(dataTable, numberIndex, new Font("Tahoma", Font.BOLD, 12));
                }
            }
            if (hasBackColor) {
                for (int backColorIndex : backColorIndexes) {
                    TableColumnFontChanger.setFont(dataTable, backColorIndex, new Font("Tahoma", Font.BOLD, 12), backColor, selectionBackColor);
                }
            }
            if (isAlignable) {
                TableColumnRightAlign.alignMany(dataTable, indexesTobeAligned);
            }
            if (isIskoolaPota) {
                for (int iskoolaPotaIndex : iskoolaPotaIndexes) {
                    TableColumnFontChanger.setIskolaPotha(dataTable, iskoolaPotaIndex);
                }
            }
            if (isAmali) {
                for (int amaliIndex : amaliIndexes) {
                    TableColumnFontChanger.setAmalee(dataTable, amaliIndex);
                }
            }
            dataTable.packAll();
            viewTotal(code);
            if (code.startsWith("02-")) {
                viewOBal(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void confirm(String recID, String glcode) {
        try {
            String[] buttons = {"Yes", "No"};
            int i = JOptionPane.showOptionDialog(this, "<html><p><span style='color:red'>මෙම චෙක්පත මුද්‍රණය තහවුරු කළ පසු වෙනස් කළ නොහැක.</span> තහවුරු කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
            if (i == JOptionPane.YES_OPTION) {
                List<Object> inputs = new ArrayList<>();
                inputs.add(recID);
                inputs.add(GeneralUserLogin.data.getUsername());
                inputs.add("@typeid");
                inputs.add("@msg");
                System.out.println(inputs + "ii");
                List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Confirm_ChequePrint", 2);
                JOptionPane.showMessageDialog(this, String.valueOf(output.get(1)), "", Integer.parseInt(String.valueOf(output.get(0))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        ItemRefresh.doClick();
    }

    private void showCurrentAccount(String code) {
        if (code.startsWith("03") || code.startsWith("04")) {
            txtCurrentAct.setVisible(true);
            jLabel4.setVisible(true);
        } else {
            txtCurrentAct.setVisible(false);
            jLabel4.setVisible(false);
        }
    }

    private void showChequeBtn(String code) {
        if (code.startsWith("04")) {
//            btnChequePrint.setVisible(true);
        } else {
//            btnChequePrint.setVisible(false);
        }
    }

    private void print() {
        if (dataTable.getSelectedRow() > -1) {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            if (code.startsWith("02")) {
                try {
                    JasperReport compileReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R007_F28_F10.jrxml"));
                    Map params = new HashMap<Object, Object>();
                    params.put("Oth_Trn_HDID", Integer.parseInt(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))));
                    params.put("MPCSName", data.getBranchName());
                    SinhalaOriginalNumberToLetter toLetter = new SinhalaOriginalNumberToLetter();
                    String number = toLetter.getNumber(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 6)));
                    number = "රුපියල් " + number;
                    params.put("TotalText", number);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DB.getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (JRException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (SQLException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            } else if (code.startsWith("03")) {
                try {
                    JasperReport compileReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R010_F28_F5_Voucher.jrxml"));
                    Map params = new HashMap<Object, Object>();
                    params.put("HDID", Integer.parseInt(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))));
                    params.put("MPCSName", data.getBranchName());
                    SinhalaOriginalNumberToLetter toLetter = new SinhalaOriginalNumberToLetter();
                    String number = toLetter.getNumber(FormatConstants.numberFormat1.parse(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 6))).doubleValue() + "");
                    number = "රුපියල් " + number;
                    params.put("TotalText", number);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DB.getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (JRException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (SQLException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (ParseException ex) {
                    Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
                    dataTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }
    }

    private void resetButtons() {
//        JButton buttons[] = {btnChequePrint, btnEdit, btnJournal, btnNew, btnPrint, btnRefresh, btnSection, btnSupplier};
//        for (JButton button : buttons) {
//            button.setEnabled(true);
//        }
    }

    private void confirmTRN() {
        if (dataTable.getSelectedRow() > -1) {
            confirm(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), ManagerOperational.statusGLID2.getText());
        }
    }

    private void confirmStock(String id) {
        String[] buttons = {"Yes", "No"};
        int i = JOptionPane.showOptionDialog(this, "<html><p><span style='color:red'>අනුමත කළ පසු වෙනස් කල නොහැක.</span> අනුමත කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
        if (i == JOptionPane.YES_OPTION) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(1);
                if (code.equals("01-01")) {
                    inputs.add(GLCodes.AB16BUYINGS);
                } else if (code.equals("01-02")) {
                    inputs.add(GLCodes.AB16CASHPURCHASE);
                } else if (code.equals("01-03")) {
                    inputs.add(GLCodes.AB16PURCHASEDADVANCE);
                } else if (code.equals("01-04")) {
                    inputs.add(GLCodes.AB16STORESRECEIVINGS);
                }
                inputs.add(id);
                inputs.add(GeneralUserLogin.data.getUsername());
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Confirm_Stock", 0);
                JOptionPane.showMessageDialog(this, "සාර්ථකයි", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editCommonJournalForm() {
        if (dataTable.getSelectedRow() > -1) {
            List dataArray = new ArrayList<>();
            for (int i = 0; i < dataTable.getColumnCount(); i++) {
                dataArray.add(dataTable.getValueAt(dataTable.getSelectedRow(), i));
            }
            if (null != code) {
                switch (code) {
                    case "01-01":
//                        new AB16Buyings(code, dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString()).setVisible(true);
                        new AB16BuyingsNewFormat(code, dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString()).setVisible(true);
                        break;
                    case "01-02":
                        new AB16StoresBuyings(code, String.valueOf(dataArray.get(0))).setVisible(true);
                        break;
                    case "01-03":
                        new AB16Buyings(code, String.valueOf(dataArray.get(0))).setVisible(true);
                        break;
                    case "01-04":
                        new AB16StoresBuyings(code, String.valueOf(dataArray.get(0))).setVisible(true);
                        break;
                    case "01-05":
                        if (data.isB9old()) {
                            new C14B9(code, dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString()).setVisible(true);
                        } else {
                            new C14B9HD(code, dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString()).setVisible(true);
                        }
                        break;
                    case "01-06":
                        new F17(code, dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString()).setVisible(true);
                        break;
                    case "01-07":
//                        try {
//                            List<Object> inputs = new ArrayList<>();
//                            inputs.add(dataArray.get(0));
//                            dataArray = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_FindData_F18", 17);
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (ClassNotFoundException ex) {
//                            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
//                        }

                        new F18(code, String.valueOf(dataArray.get(0)), cmbConfirmed.getSelectedItem().getId()).setVisible(true);
                        break;
                    case "01-08":
                        new F19(code, String.valueOf(dataArray.get(0)), cmbConfirmed.getSelectedItem().getId()).setVisible(true);
                        break;
                    case "01-09":
                        new CommonDRNote(null, true, GLCodes.C9SALE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "01-11":
                        new StockBalance(dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString()).setVisible(true);
                        break;
                    case "02-01":
                    case "02-03":
                        new CommonCashReceiptCash(null, true, code, String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), this.user, cmbConfirmed.getSelectedItem().getId()).setVisible(true);
                        break;
                    case "02-02":
                    case "02-04":
                        new CommonCashReceiptStamp(null, true, code, String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), cmbConfirmed.getSelectedItem().getId()).setVisible(true);
                        break;
                    case "03-01":
                        new CommonDRNote(null, true, GLCodes.PURCHASES, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-02":
                        new CommonDRNote(null, true, GLCodes.CREDITORSPAYMENTS, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-03":
                        new CommonDRNote(null, true, GLCodes.OTHERPAYMENTS, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-04":
                        new CommonDRNote(null, true, GLCodes.ADVANCEPURCHASES, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-05":
                        new CommonDRNote(null, true, GLCodes.OTHERADVANCES, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-06":
                        new CommonDRNote(null, true, GLCodes.LOANPAYMENTS, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-07":
                        new CommonDRNote(null, true, GLCodes.DEPOSITPAYMENTS, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), code).setVisible(true);
                        break;
                    case "03-08":
                        new CommonDRNote(null, true, Integer.parseInt(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 2))), lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "05-01":
                        new CommonDRNote(null, true, GLCodes.DRNOTE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "05-02":
                        new CommonDRNote(null, true, GLCodes.CRNOTE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "05-03":
                        new CommonDRNote(null, true, GLCodes.BANKDRNOTE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "05-04":
                        new CommonDRNote(null, true, GLCodes.BANKCRNOTE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "05-05":
                        new CommonDRNote(null, true, GLCodes.COMMONDRNOTE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "05-06":
                        new CommonDRNote(null, true, GLCodes.COMMONCRNOTE, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "07-01":
                        new CommonRuralBank9E(null, true, GLCodes.RURALBANK9E, "බැංකු අංශයේ මාසික වාර්තාව - 9E", String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "08-01":
                        new CommonDRNote(null, true, GLCodes.COMMONADMINDR, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "08-02":
                        new CommonDRNote(null, true, GLCodes.COMMONADMINCR, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "08-03":
                        new CommonDRNote(null, true, GLCodes.COMMONEXPENCEMATCHDR, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    case "08-04":
                        new CommonDRNote(null, true, GLCodes.COMMONEXPENCEMATCHCR, lblTitle.getText(), String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0))).setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void newCommonJournalForm() {

        if (code.equals("01-01") || code.equals("01-03")) {
//            new AB16Buyings(code).setVisible(true);
            new AB16BuyingsNewFormat(code).setVisible(true);
        } else if (code.equals("01-02")) {
            new AB16StoresBuyings(code).setVisible(true);
        } else if (code.equals("01-04")) {
            new AB16StoresBuyings(code).setVisible(true);
        } else if (code.equals("01-05")) {
            if (data.isB9old()) {
                new C14B9(code).setVisible(true);
            } else {
                new C14B9HD(code).setVisible(true);
            }
        } else if (code.equals("01-06")) {
            new F17(code).setVisible(true);
        } else if (code.equals("01-07")) {
            new F18(code).setVisible(true);
        } else if (code.equals("01-08")) {
            new F19(code).setVisible(true);
        } else if (code.equals("01-11")) {
            new StockBalance().setVisible(true);
        } else if (type == GLCodes.C9SALE || type == GLCodes.PURCHASES || type == GLCodes.CREDITORSPAYMENTS || type == GLCodes.OTHERPAYMENTS || type == GLCodes.ADVANCEPURCHASES || type == GLCodes.OTHERADVANCES || type == GLCodes.LOANPAYMENTS || type == GLCodes.DEPOSITPAYMENTS || type == GLCodes.DRNOTE || type == GLCodes.CRNOTE || type == GLCodes.BANKCRNOTE || type == GLCodes.BANKDRNOTE || type == GLCodes.COMMONADMINDR || type == GLCodes.COMMONADMINCR || type == GLCodes.COMMONDRNOTE || type == GLCodes.COMMONCRNOTE || type == GLCodes.COMMONEXPENCEMATCHDR || type == GLCodes.COMMONEXPENCEMATCHCR) {
            new CommonDRNote(null, true, type, lblTitle.getText()).setVisible(true);
        } else if (code.equals("02-01") || code.equals("02-03")) {
            new CommonCashReceiptCash(null, true, code).setVisible(true);
        } else if (code.equals("02-02") || code.equals("02-04")) {
            new CommonCashReceiptStamp(null, true, code).setVisible(true);
        } else if (code.equals("07-01")) {
            new CommonRuralBank9E(null, true, GLCodes.RURALBANK9E, code).setVisible(true);
        }
    }

    private void doubleEntryView() {
        if (dataTable.getSelectedRow() > -1) {
            int glType = 0;
            String hdid = String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0));
            if (code.equals("01-09")) {
                glType = 5;
            } else if (code.startsWith("01-")) {
                glType = 1;
            } else if (code.startsWith("02-")) {
                glType = 2;
            } else if (code.startsWith("03-")) {
                glType = 3;
            } else if (code.startsWith("04-")) {
                glType = 0;
                hdid = String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 12));
            } else if (code.startsWith("05-")) {
                glType = 5;
            } else if (code.startsWith("06-")) {
                glType = 6;
            } else if (code.startsWith("07-")) {
                glType = 7;
            } else if (code.startsWith("08-")) {
                glType = 8;
            }
            if (hdid.equals("0")) {
                JOptionPane.showMessageDialog(this, "ද්විත්ව සටහන නොමැත", "ද්විත්ව සටහන නොමැත", JOptionPane.WARNING_MESSAGE);
            } else {
                new DoubleEntryViwer(null, true, hdid, ManagerOperational.statusGLID2.getText(), glType).setVisible(true);
            }
        }
    }

    private void printCheque() {
        if (dataTable.getSelectedRow() > -1) {
            new CHQModule(null, true, String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)), user).setVisible(true);
        }
    }

    private void viewOBal(boolean b) {
        try {
            JLabel labels[] = {jLabel6, jLabel9, jLabel11, lblDR, lblCR, lblOBal};
            for (JLabel label : labels) {
                label.setVisible(b);
            }
            if (code.startsWith("02-01")) {
                jLabel14.setText("අවසාන ශේෂය :");
            }
            List<Object> inputs = new ArrayList<>();
            inputs.add(fromDate.getDate());
            inputs.add(toDate.getDate());
            List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_Rep_F11A_Screen", 4);
            lblOBal.setText(FormatConstants.decimalFormat.format(NumberUtils.toDouble(String.valueOf(outputs.get(0)), 0.00)));
            lblCR.setText(FormatConstants.decimalFormat.format(NumberUtils.toDouble(String.valueOf(outputs.get(1)), 0.00)));
            lblDR.setText(FormatConstants.decimalFormat.format(NumberUtils.toDouble(String.valueOf(outputs.get(2)), 0.00)));
            lblCBal.setText(FormatConstants.decimalFormat.format(NumberUtils.toDouble(String.valueOf(outputs.get(3)), 0.00)));
            cal9BTotal(6, lblCashTotal);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewTotal(String code) {
        jLabel14.setText("එකතුව :");
        if (code.startsWith("03-") && !code.equals("03-08")) {
            calTotal(6, lblCBal);
        } else if (code.startsWith("04-01")) {
            calTotal(9, lblCBal);
            calTotal(10, lblCR);
            jLabel11.setText("චෙක් උපරිම එකතුව");
            JLabel labels[] = {jLabel6, jLabel9, jLabel23, lblOBal, lblDR, lblCashTotal};
            hideComponents(labels, false);
        } else if (code.startsWith("04-02")) {
//            calTotal(9, lblCBal);
//            calTotal(10, lblCR);
            jLabel11.setText("චෙක් උපරිම එකතුව");
            JLabel labels[] = {jLabel6, jLabel9, jLabel23, lblOBal, lblDR, lblCashTotal};
            hideComponents(labels, false);
        } else if (code.startsWith("05-") || code.startsWith("08-")) {
            calTotal(3, lblCashTotal);
            JLabel labels[] = {lblOBal, lblDR, lblCR, lblCBal, jLabel14, jLabel11, jLabel9, jLabel6};
            hideComponents(labels, false);
        } else if (code.equals("01-09")) {
            calTotal(3, lblCBal);
        } else if (code.startsWith("01-05")) {
            jPanel3.removeAll();
            jPanel3.add(jPanel5);
            jPanel3.revalidate();
            jPanel3.repaint();
            jPanel3.updateUI();

            if (data.isB9old()) {
                cal9BTotal(6, lblCashSale);
                cal9BTotal(7, lblCreditSale);
                cal9BTotal(8, lblLoanSale);
                cal9BTotal(9, lblSamurdhi);
                cal9BTotal(10, lblPoshana);
                cal9BTotal(11, lblDryItems);
                cal9BTotal(12, lblSectionsTransfer);
                cal9BTotal(13, lblCreditCard);
                cal9BTotal(14, lblTotal);
                cal9BTotal(15, lblCashPurchases);
                cal9BTotal(16, lblF2Expences);
                cal9BTotal(17, lblOtherReceivings);
            } else {
                cal9BTotal(6, lblCashSale);
                jLabel8.setText("හර");
                jLabel10.setText("බැර");
                cal9BTotal(7, lblCreditSale);
                JLabel labels[] = {lblLoanSale, lblSamurdhi, lblPoshana, lblDryItems,
                    lblSectionsTransfer, lblCreditCard, lblTotal, lblCashPurchases,
                    lblF2Expences, lblOtherReceivings, jLabel12, jLabel13,
                    jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22};
                for (JLabel label : labels) {
                    label.setVisible(false);
                }
            }
        } else if (code.equals("01-01") /**
                 * || code.equals("01-02") || code.equals("01-03") ||
                 * code.equals("01-04")*
                 */
                ) {
            jPanel3.removeAll();
            jPanel3.add(jPanel6);
            jPanel3.revalidate();
            jPanel3.repaint();
            jPanel3.updateUI();
            jLabel4.setText("වර්ගය :");
            TextPrompt prompt = new TextPrompt("සියලු වර්ග සඳහා හිස්ව තබන්න", txtCurrentAct);
            prompt.setFont(new Font("Iskoola Pota", Font.PLAIN, 13));
            calTotal(8, lblInvVal);
            calTotal(9, lblSellVal);
            calTotal(10, lblDiscount);
            calTotal(11, lblPurExpences);
            calTotal(12, lblFreeIssue);
            calTotal(13, lblGoodReturns);
            calTotal(14, lblTotSupPayments);
            calTotal(15, lblSecTransIn);
            calTotal(16, lblCashPur);
        } else if (code.equals("01-06")) {
            jPanel3.removeAll();
            jPanel3.add(jPanel7);
            jPanel3.revalidate();
            jPanel3.repaint();
            jPanel3.updateUI();
            calTotal(8, lblProfit);
            calTotal(9, lblLost);
        } else if (code.equals("01-07")) {
            jPanel3.removeAll();
            jPanel3.add(jPanel8);
            jPanel3.revalidate();
            jPanel3.repaint();
            jPanel3.updateUI();
            calTotal(9, lblOutPurPrice);
            calTotal(10, lblOutSalePrice);
        } else if (code.equals("01-08")) {
            jPanel3.removeAll();
            jPanel3.add(jPanel9);
            jPanel3.revalidate();
            jPanel3.repaint();
            jPanel3.updateUI();
            calTotal(9, lblPurchase);
            calTotal(10, lblSales);
        } else if (code.startsWith("07-")) {
            jLabel6.setText("");
            jLabel14.setText("");
//            lblCBal.setText("");
//            lblOBal.setText("");
            jLabel23.setText("");
//            lblCashTotal.setText("");
            calTotal(7, lblDR);
            calTotal(8, lblCR);
        }//else if (code.equals("10-02")) {
//            ManagerOperationalNavigation.navigateTo(jPanel10, jPanel3);
//        }
        if (code.startsWith("01-")) {
            JLabel labels[] = {jLabel6, jLabel9, jLabel11, jLabel23, lblOBal, lblDR, lblCR, lblCashTotal};
            hideComponents(labels, true);
        }
    }

    private void calTotal(int index, JLabel jLabel) {
        double d = 0;
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            try {
                d += FormatConstants.numberFormat1.parse(String.valueOf(dataTable.getValueAt(i, index))).doubleValue();
            } catch (ParseException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jLabel.setText(FormatConstants.numberFormat1.format(d));

    }

    private void cancelTransaction() {
        if (dataTable.getSelectedRow() > -1) {
            try {
                List<Object> inputs = new ArrayList<>();
                int glType = 0;
                if (code.equals("01-09")) {
                    glType = 5;
                } else if (code.startsWith("01-")) {
                    glType = 1;
                } else if (code.startsWith("02-")) {
                    glType = 2;
                } else if (code.startsWith("03-")) {
                    glType = 3;
                } else if (code.startsWith("04-")) {
                    glType = 4;
                } else if (code.startsWith("05-")) {
                    glType = 5;
                } else if (code.startsWith("06-")) {
                    glType = 6;
                } else if (code.startsWith("07-")) {
                    glType = 7;
                } else if (code.startsWith("08-")) {
                    glType = 8;
                }
                inputs.add(glType);
                inputs.add(ManagerOperational.statusGLID2.getText());
                inputs.add(String.valueOf(dataTable.getValueAt(dataTable.getSelectedRow(), 0)));
                inputs.add(data.getUsername());

                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Cancel_Transactions", 0);
            } catch (RemoteException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        selectConfirmedCombo();
    }

    private void cal9BTotal(int index, JLabel label) {
        double d = 0;
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            try {
                d += FormatConstants.numberFormat1.parse(String.valueOf(dataTable.getValueAt(i, index))).doubleValue();
            } catch (ParseException ex) {
                Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        label.setText(FormatConstants.numberFormat1.format(d));

    }

    private void hideComponents(Component[] components, boolean visibility) {
        for (Component component : components) {
            component.setVisible(visibility);
        }
    }

    private void getState() {

    }

    private void setSearchComponentStatus() {
        fromDate.setDate(CommonJurnalFormComponentStateSave.getFrom());
        toDate.setDate(CommonJurnalFormComponentStateSave.getTo());
        txtCurrentAct.setText(CommonJurnalFormComponentStateSave.getCAText());
        txtCurrentAct.setId(CommonJurnalFormComponentStateSave.getCAID());
        sectionCheck.setSelected(CommonJurnalFormComponentStateSave.isSectionAll());
        supplierCheck.setSelected(CommonJurnalFormComponentStateSave.isOutsiderAll());
        if (sectionCheck.isSelected()) {
            section.setEnabled(false);
        } else {
            section.setEnabled(true);
        }
        if (supplierCheck.isSelected()) {
            outsider.setEnabled(false);
        } else {
            outsider.setEnabled(true);
        }
        section.setSelectedHiddenItem(CommonJurnalFormComponentStateSave.getSectionID());
        outsider.setSelectedHiddenItem(CommonJurnalFormComponentStateSave.getOutsiderID());
        cmbConfirmed.setSelectedHiddenItem(CommonJurnalFormComponentStateSave.getStateID());
    }

    private void print16B(int type) {
        try {
            JasperReport jasperReport = null;
            if (type == 1) {
                jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R023_16B_List.jrxml"));
            } else if (type == 2) {
                jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R023_16B_List2.jrxml"));
            }

            Map params = new HashMap();
            params.put("dFromDate", fromDate.getDate());
            params.put("dToDate", toDate.getDate());
            params.put("MPCSName", data.getBranchName());
            if (sectionCheck.isSelected()) {
                params.put("nSubSectionID", 0);
            } else {
                params.put("nSubSectionID", section.getSelectedItem().getId());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DB.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void print9B(int type) {
        try {
            JasperReport jasperReport = null;
            switch (type) {
                case 1:
                    jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R020_F28_9B_List.jrxml"));
                    break;
                case 2:
                    jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R020_F28_9B_List2.jrxml"));
                    break;
                case 3:
                    jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R020_F28_9B_List3.jrxml"));
                    break;
                default:
                    break;
            }
            Map params = new HashMap();
            params.put("dFromDate", fromDate.getDate());
            params.put("dToDate", toDate.getDate());
            params.put("MPCSName", data.getBranchName());
            if (sectionCheck.isSelected()) {
                params.put("nSubSectionID", 0);
            } else {
                params.put("nSubSectionID", section.getSelectedItem().getId());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DB.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateDepositedCheque(int i) {
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(txtChequeNo1002.getId());
            inputs.add(FormatConstants.dateFormat.format(jdtBankDate1002.getDate()));
            if (i == 1) {
                inputs.add(12);
            } else if (i == 2) {
                inputs.add(13);
            }
            inputs.add(txtRemarks1002.getText());
            inputs.add(data.getUsername());
            ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Update_ChequesDeposit", 0);
            selectConfirmedCombo();
            jPanel3.removeAll();
            jPanel3.repaint();
            jPanel3.revalidate();
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateIssued(int i) {
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(txtChequeNo1003.getId());
            inputs.add(FormatConstants.dateFormat.format(jdtBankDate1003.getDate()));
            if (i == 1) {
                inputs.add(10);
            } else if (i == 2) {
                inputs.add(13);
            }
            inputs.add(txtRemarks1003.getText());
            inputs.add(data.getUsername());
            ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Update_ChequesIssued", 0);
            selectConfirmedCombo();
            jPanel3.removeAll();
            jPanel3.repaint();
            jPanel3.revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonJurnalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class ColumnColorRenderer extends DefaultTableCellRenderer {

    public ColumnColorRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column == 8) {
            c.setBackground(Color.YELLOW);
        }
        return c;
    }
}
