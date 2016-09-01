/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * frm_F28_CashReceipt.java
 *
 * Created on Aug 7, 2013, 9:59:39 PM
 */
package com.nbs.view.F28.Payments;

import Sources.GLCodes;
import Sources.JTextFieldRemoveNull;
import Sources.ReportFileLocator;
import Sources.TableColumnFontChanger;
import Sources.TableColumnRightAlign;
import Sources.User;
import com.nbs.connection.DB;
import com.nbs.impl.ServerConnection;
import com.nbs.view.common.BankDepositDialog;
import com.nbs.view.common.FormatConstants;
import com.nbs.view.common.GeneralUserLogin;
import static com.nbs.view.common.GeneralUserLogin.data;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.search.FindAccount5Data;
import com.nbs.view.common.search.FindSectionOrOutsider;
import com.nbs.view.manager.CommonJurnalForm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author mmh
 */
public class CommonCashReceiptCash extends javax.swing.JDialog {

    /**
     * Creates new form frm_F28_CashReceipt
     */
    public static String code;
    public static int inputReasonID = 0;
    public static int inputGLAcctID = 0;
    DefaultTableModel dtm1;
    DefaultTableModel dtm2;
    public static boolean save = false;

    private JTextField textFields[];
    private JLabel labels[];
    private User user;
    private String id;

    public CommonCashReceiptCash(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        textFields = new JTextField[]{txtOutSider, txtReasonorGlAccount, txtSubSection, txt10, txt100, txt1000, txt20, txt2000, txt50, txt500, txt5000, txtCoins, txtDrCr, txtF10No, txtF9DNo, txtNo1, txtNo2, txtOther, txtOutSider, txtReasonorGlAccount, txtSubSection, txtTotal, txtRelatedGLAccount};
        labels = new JLabel[]{jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, statusGLCode, statusUserName, title};

        setTitle("Server on " + GeneralUserLogin.data.getIP());

        statusUserName.setText(GeneralUserLogin.data.getUserCode() + "-" + GeneralUserLogin.data.getUsername());
        txtTrnDate.setDate(GeneralUserLogin.data.getSystemDate());
        dtm2 = (DefaultTableModel) Table2.getModel();
        Table1.getTableHeader().setFont(new Font("Iskoola Pota", Font.BOLD, 16));
        Table2.setShowGrid(true);

        TableColumnFontChanger.setAmalee(Table2, 1, 18);
        TableColumnFontChanger.setAmalee(Table2, 0, 18);
        TableColumnFontChanger.setAmalee(Table2, 2, 18);
        TableColumnRightAlign.alignMany(Table2, new int[]{14, 15, 16});
        txtTotal.setText("0.00");
    }

    public CommonCashReceiptCash(java.awt.Frame parent, boolean modal, String code) {
        this(parent, modal);
        this.code = code;
        switch (code) {
            case "02-01":
                title.setText("මුදල් ලැබීම්");
                Table1.getColumn(0).setMaxWidth(0);
                Table2.getColumn(0).setMaxWidth(0);
                Table1.getColumn(15).setMaxWidth(0);
                Table2.getColumn(15).setMaxWidth(0);
                TableColumnRightAlign.alignMany(Table2, new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                statusGLCode.setText(GLCodes.CASHRECEIVINGS + "");
                break;
            case "02-03":
                title.setText("මුදල් බැඳීම්");
                txtReasonorGlAccount1.setVisible(false);
                Table1.getColumn(1).setMaxWidth(0);
                Table2.getColumn(1).setMaxWidth(0);
                Table1.getColumn(2).setMaxWidth(0);
                Table2.getColumn(2).setMaxWidth(0);
//                Table1.getColumn(4).setMaxWidth(410);
//                Table1.getColumn(4).setMinWidth(410);
//                txtReasonorGlAccount1.setPreferredSize(new Dimension(410, txtReasonorGlAccount1.getHeight()));
                Table1.getColumn(14).setMaxWidth(0);
                Table2.getColumn(14).setMaxWidth(0);
                TableColumnRightAlign.alignMany(Table2, new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                statusGLCode.setText(GLCodes.CASHTIES + "");
                break;
        }
    }

    public CommonCashReceiptCash(java.awt.Frame parent, boolean b, String code, String id) {
        this(parent, b, code);
        this.id = id;
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(1);
            inputs.add(id);
            List<Object> output = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_ViewCashierEdit", 13);

            txtNo2.setText(String.valueOf(output.get(0)));
            txtTrnDate.setDate(FormatConstants.dateFormat.parse(String.valueOf(output.get(1))));
            txtNo1.setText(String.valueOf(output.get(2)));
            txtF10No.setText(String.valueOf(output.get(3)));
            txtF9DNo.setText(String.valueOf(output.get(4)));
            txtSubSection.setId(Integer.parseInt(String.valueOf(output.get(5))));
            txtSubSection.setText(String.valueOf(output.get(6)));
            txtOutSider.setId(Integer.parseInt(String.valueOf(output.get(7))));
            txtOutSider.setText(String.valueOf(output.get(8)));
            txtTotal.setText(FormatConstants.decimalFormat.format(Double.parseDouble(String.valueOf(output.get(9)))));
            txtRelatedGLAccount.setText(String.valueOf(output.get(11)));
            boolean number = NumberUtils.isNumber(String.valueOf(output.get(10)));
            int glAcctID = 0;
            if (number) {
                glAcctID = Integer.parseInt(String.valueOf(output.get(10)));
            }
            txtRelatedGLAccount.setId(glAcctID);
            btnConfirm.setText(String.valueOf(output.get(12)));

            viewDT(id);
            removeNull();
        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        }
        enableFields();
    }

    private CommonCashReceiptCash(java.awt.Frame parent, boolean b, String code, int type) {
        this(parent, b, code);
        saveHD();
    }

    public CommonCashReceiptCash(java.awt.Frame parent, boolean b, String code, String id, User user) {
        this(parent, b, code, id);
        this.user = user;
        setPermission();
    }

    public CommonCashReceiptCash(java.awt.Frame parent, boolean b, String code, String id, User user, int statusID) {
        this(parent, b, code, id, user);
        if (statusID != 1) {
            JButton buttons[] = {btnNew, btnPrint, btnSave};
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
            Table2.setEnabled(false);
            Component components[] = {txtF10No, txtF9DNo, txtSubSection, txtOutSider, txtReasonorGlAccount, isCheck, txtOther, txt5000, txt2000, txt1000, txt500, txt100, txt50, txt20, txt10, txtCoins, txtDrCr, txtRelatedGLAccount, txtReasonorGlAccount1};
            for (Component component : components) {
                component.setEnabled(false);
            }
        }
        setPermission();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        txtTotal = new org.nbs.components.SelectAtFocusFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtOutSider = new org.nbs.components.HiddenIDTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSubSection = new org.nbs.components.HiddenIDTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRelatedGLAccount = new org.nbs.components.HiddenIDTextField();
        jPanel6 = new javax.swing.JPanel();
        txtNo1 = new javax.swing.JTextField();
        txtNo2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTrnDate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtF10No = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtF9DNo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        txtReasonorGlAccount = new org.nbs.components.HiddenIDTextField();
        isCheck = new javax.swing.JCheckBox();
        txtOther = new javax.swing.JTextField();
        txt5000 = new javax.swing.JTextField();
        txt2000 = new javax.swing.JTextField();
        txt1000 = new javax.swing.JTextField();
        txt500 = new javax.swing.JTextField();
        txt100 = new javax.swing.JTextField();
        txt50 = new javax.swing.JTextField();
        txt20 = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        txtCoins = new javax.swing.JTextField();
        txtDrCr = new javax.swing.JTextField();
        txtReasonorGlAccount1 = new org.nbs.components.HiddenIDTextField();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        jLabel1 = new javax.swing.JLabel();
        statusGLCode = new javax.swing.JLabel();
        statusUserName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(1209, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 88, 136));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        title.setFont(new java.awt.Font("Iskoola Pota", 0, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("මුදල් ලැබීම");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 21), java.awt.Color.white)); // NOI18N

        btnNew.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnNew.setText("නිව්");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnSave.setText("සේව්");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnPrint.setText("මුද්‍රණය");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtTotal.setHorizontalAlignment(4);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.black);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("TOTAL");

        btnConfirm.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnConfirm.setText("ස්ථිර කරන්න");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 81, 136)));

        txtOutSider.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtOutSider.setEnabled(false);
        txtOutSider.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtOutSider.setForeground(java.awt.Color.black);
        txtOutSider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOutSiderKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOutSiderKeyReleased(evt);
            }
        });
        txtOutSider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOutSiderActionPerformed(evt);
            }
        });
        txtOutSider.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOutSiderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOutSiderFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel3.setText("නම");

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel6.setText("අංශය");

        txtSubSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSubSection.setEnabled(false);
        txtSubSection.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtSubSection.setForeground(java.awt.Color.black);
        txtSubSection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubSectionKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubSectionKeyReleased(evt);
            }
        });
        txtSubSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubSectionActionPerformed(evt);
            }
        });
        txtSubSection.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubSectionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSubSectionFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel8.setText("අදාළ අංශයේ ජංගම ගිණුම");

        txtRelatedGLAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtRelatedGLAccount.setEnabled(false);
        txtRelatedGLAccount.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtRelatedGLAccount.setForeground(java.awt.Color.black);
        txtRelatedGLAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRelatedGLAccountKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRelatedGLAccountKeyReleased(evt);
            }
        });
        txtRelatedGLAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRelatedGLAccountActionPerformed(evt);
            }
        });
        txtRelatedGLAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRelatedGLAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRelatedGLAccountFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(txtSubSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOutSider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRelatedGLAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSubSection, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtOutSider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRelatedGLAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 81, 136)));

        txtNo1.setEditable(false);
        txtNo1.setBackground(new java.awt.Color(130, 123, 116));
        txtNo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNo1.setForeground(new java.awt.Color(255, 255, 255));
        txtNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNo2.setEditable(false);
        txtNo2.setBackground(new java.awt.Color(130, 123, 116));
        txtNo2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNo2.setForeground(new java.awt.Color(255, 255, 255));
        txtNo2.setText("0");
        txtNo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel4.setText("දිනය");

        txtTrnDate.setDateFormatString("yyyy-MM-dd");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("F 10");

        txtF10No.setEditable(false);
        txtF10No.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtF10No.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtF10No.setEnabled(false);
        txtF10No.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtF10NoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtF10NoFocusLost(evt);
            }
        });
        txtF10No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtF10NoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("F 9D No :");

        txtF9DNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtF9DNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtF9DNo.setEnabled(false);
        txtF9DNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtF9DNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtF9DNoFocusLost(evt);
            }
        });
        txtF9DNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtF9DNoActionPerformed(evt);
            }
        });
        txtF9DNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtF9DNoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTrnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtF10No, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtF9DNo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNo2)
                    .addComponent(txtF9DNo)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtF10No, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNo1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 30));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(4, 30));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1755, 30));

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ගිණුම් විස්තරය", "කාරණය", "ගිණුම", "චෙක්", "වෙනත්", "5000", "2000", "1000", "500", "100", "50", "20", "10", "කාසි", "බැර", "හර", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table1.setMaximumSize(new java.awt.Dimension(1690, 32));
        Table1.setMinimumSize(new java.awt.Dimension(1540, 32));
        Table1.setPreferredSize(new java.awt.Dimension(1690, 32));
        Table1.setRowHeight(29);
        Table1.setSelectionBackground(new java.awt.Color(113, 114, 125));
        Table1.setSelectionForeground(new java.awt.Color(1, 1, 1));
        Table1.getTableHeader().setReorderingAllowed(false);
        Table1.setVisibleRowCount(0);
        Table1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Table1ComponentMoved(evt);
            }
        });
        jScrollPane1.setViewportView(Table1);
        if (Table1.getColumnModel().getColumnCount() > 0) {
            Table1.getColumnModel().getColumn(0).setMinWidth(0);
            Table1.getColumnModel().getColumn(0).setPreferredWidth(320);
            Table1.getColumnModel().getColumn(0).setMaxWidth(320);
            Table1.getColumnModel().getColumn(1).setMinWidth(0);
            Table1.getColumnModel().getColumn(1).setPreferredWidth(320);
            Table1.getColumnModel().getColumn(1).setMaxWidth(320);
            Table1.getColumnModel().getColumn(2).setMinWidth(0);
            Table1.getColumnModel().getColumn(2).setPreferredWidth(210);
            Table1.getColumnModel().getColumn(2).setMaxWidth(210);
            Table1.getColumnModel().getColumn(3).setMinWidth(50);
            Table1.getColumnModel().getColumn(3).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(3).setMaxWidth(50);
            Table1.getColumnModel().getColumn(4).setMinWidth(100);
            Table1.getColumnModel().getColumn(4).setPreferredWidth(100);
            Table1.getColumnModel().getColumn(4).setMaxWidth(100);
            Table1.getColumnModel().getColumn(5).setMinWidth(50);
            Table1.getColumnModel().getColumn(5).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(5).setMaxWidth(50);
            Table1.getColumnModel().getColumn(6).setMinWidth(50);
            Table1.getColumnModel().getColumn(6).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(6).setMaxWidth(50);
            Table1.getColumnModel().getColumn(7).setMinWidth(50);
            Table1.getColumnModel().getColumn(7).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(7).setMaxWidth(50);
            Table1.getColumnModel().getColumn(8).setMinWidth(50);
            Table1.getColumnModel().getColumn(8).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(8).setMaxWidth(50);
            Table1.getColumnModel().getColumn(9).setMinWidth(50);
            Table1.getColumnModel().getColumn(9).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(9).setMaxWidth(50);
            Table1.getColumnModel().getColumn(10).setMinWidth(50);
            Table1.getColumnModel().getColumn(10).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(10).setMaxWidth(50);
            Table1.getColumnModel().getColumn(11).setMinWidth(50);
            Table1.getColumnModel().getColumn(11).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(11).setMaxWidth(50);
            Table1.getColumnModel().getColumn(12).setMinWidth(50);
            Table1.getColumnModel().getColumn(12).setPreferredWidth(50);
            Table1.getColumnModel().getColumn(12).setMaxWidth(50);
            Table1.getColumnModel().getColumn(13).setMinWidth(60);
            Table1.getColumnModel().getColumn(13).setPreferredWidth(60);
            Table1.getColumnModel().getColumn(13).setMaxWidth(60);
            Table1.getColumnModel().getColumn(14).setMinWidth(0);
            Table1.getColumnModel().getColumn(14).setPreferredWidth(196);
            Table1.getColumnModel().getColumn(14).setMaxWidth(196);
            Table1.getColumnModel().getColumn(15).setMinWidth(0);
            Table1.getColumnModel().getColumn(15).setPreferredWidth(196);
            Table1.getColumnModel().getColumn(15).setMaxWidth(196);
            Table1.getColumnModel().getColumn(16).setMinWidth(0);
            Table1.getColumnModel().getColumn(16).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(16).setMaxWidth(0);
            Table1.getColumnModel().getColumn(17).setMinWidth(0);
            Table1.getColumnModel().getColumn(17).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(17).setMaxWidth(0);
            Table1.getColumnModel().getColumn(18).setMinWidth(0);
            Table1.getColumnModel().getColumn(18).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(18).setMaxWidth(0);
        }

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table2.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        Table2.setRowHeight(25);
        Table2.getTableHeader().setReorderingAllowed(false);
        Table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Table2MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table2MouseClicked(evt);
            }
        });
        Table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);
        if (Table2.getColumnModel().getColumnCount() > 0) {
            Table2.getColumnModel().getColumn(0).setMinWidth(0);
            Table2.getColumnModel().getColumn(0).setPreferredWidth(320);
            Table2.getColumnModel().getColumn(0).setMaxWidth(320);
            Table2.getColumnModel().getColumn(1).setMinWidth(0);
            Table2.getColumnModel().getColumn(1).setPreferredWidth(320);
            Table2.getColumnModel().getColumn(1).setMaxWidth(320);
            Table2.getColumnModel().getColumn(2).setMinWidth(0);
            Table2.getColumnModel().getColumn(2).setPreferredWidth(210);
            Table2.getColumnModel().getColumn(2).setMaxWidth(210);
            Table2.getColumnModel().getColumn(3).setMinWidth(50);
            Table2.getColumnModel().getColumn(3).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(3).setMaxWidth(50);
            Table2.getColumnModel().getColumn(4).setMinWidth(100);
            Table2.getColumnModel().getColumn(4).setPreferredWidth(100);
            Table2.getColumnModel().getColumn(4).setMaxWidth(100);
            Table2.getColumnModel().getColumn(5).setMinWidth(50);
            Table2.getColumnModel().getColumn(5).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(5).setMaxWidth(50);
            Table2.getColumnModel().getColumn(6).setMinWidth(50);
            Table2.getColumnModel().getColumn(6).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(6).setMaxWidth(50);
            Table2.getColumnModel().getColumn(7).setMinWidth(50);
            Table2.getColumnModel().getColumn(7).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(7).setMaxWidth(50);
            Table2.getColumnModel().getColumn(8).setMinWidth(50);
            Table2.getColumnModel().getColumn(8).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(8).setMaxWidth(50);
            Table2.getColumnModel().getColumn(9).setMinWidth(50);
            Table2.getColumnModel().getColumn(9).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(9).setMaxWidth(50);
            Table2.getColumnModel().getColumn(10).setMinWidth(50);
            Table2.getColumnModel().getColumn(10).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(10).setMaxWidth(50);
            Table2.getColumnModel().getColumn(11).setMinWidth(50);
            Table2.getColumnModel().getColumn(11).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(11).setMaxWidth(50);
            Table2.getColumnModel().getColumn(12).setMinWidth(50);
            Table2.getColumnModel().getColumn(12).setPreferredWidth(50);
            Table2.getColumnModel().getColumn(12).setMaxWidth(50);
            Table2.getColumnModel().getColumn(13).setMinWidth(60);
            Table2.getColumnModel().getColumn(13).setPreferredWidth(60);
            Table2.getColumnModel().getColumn(13).setMaxWidth(60);
            Table2.getColumnModel().getColumn(14).setMinWidth(0);
            Table2.getColumnModel().getColumn(14).setPreferredWidth(196);
            Table2.getColumnModel().getColumn(14).setMaxWidth(196);
            Table2.getColumnModel().getColumn(15).setMinWidth(0);
            Table2.getColumnModel().getColumn(15).setPreferredWidth(196);
            Table2.getColumnModel().getColumn(15).setMaxWidth(196);
            Table2.getColumnModel().getColumn(16).setMinWidth(0);
            Table2.getColumnModel().getColumn(16).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(16).setMaxWidth(0);
            Table2.getColumnModel().getColumn(17).setMinWidth(0);
            Table2.getColumnModel().getColumn(17).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(17).setMaxWidth(0);
            Table2.getColumnModel().getColumn(18).setMinWidth(0);
            Table2.getColumnModel().getColumn(18).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(18).setMaxWidth(0);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(1190, 27));

        txtReasonorGlAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtReasonorGlAccount.setEnabled(false);
        txtReasonorGlAccount.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtReasonorGlAccount.setMaximumSize(new java.awt.Dimension(320, 27));
        txtReasonorGlAccount.setMinimumSize(new java.awt.Dimension(320, 27));
        txtReasonorGlAccount.setPreferredSize(new java.awt.Dimension(320, 27));
        txtReasonorGlAccount.setSize(new java.awt.Dimension(320, 27));
        txtReasonorGlAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReasonorGlAccountKeyReleased(evt);
            }
        });
        txtReasonorGlAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReasonorGlAccountActionPerformed(evt);
            }
        });
        txtReasonorGlAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtReasonorGlAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtReasonorGlAccountFocusLost(evt);
            }
        });

        isCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        isCheck.setBorderPainted(true);
        isCheck.setBorderPaintedFlat(true);
        isCheck.setEnabled(false);
        isCheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        isCheck.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        isCheck.setMaximumSize(new java.awt.Dimension(50, 27));
        isCheck.setMinimumSize(new java.awt.Dimension(50, 27));
        isCheck.setPreferredSize(new java.awt.Dimension(50, 27));
        isCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isCheckActionPerformed(evt);
            }
        });
        isCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isCheckKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                isCheckKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                isCheckKeyReleased(evt);
            }
        });

        txtOther.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtOther.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtOther.setEnabled(false);
        txtOther.setMaximumSize(new java.awt.Dimension(100, 27));
        txtOther.setMinimumSize(new java.awt.Dimension(100, 27));
        txtOther.setPreferredSize(new java.awt.Dimension(100, 27));
        txtOther.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOtherFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOtherFocusLost(evt);
            }
        });
        txtOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOtherActionPerformed(evt);
            }
        });
        txtOther.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtherKeyReleased(evt);
            }
        });

        txt5000.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt5000.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt5000.setText("0");
        txt5000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt5000.setEnabled(false);
        txt5000.setMaximumSize(new java.awt.Dimension(50, 27));
        txt5000.setMinimumSize(new java.awt.Dimension(50, 27));
        txt5000.setPreferredSize(new java.awt.Dimension(50, 27));
        txt5000.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt5000FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt5000FocusLost(evt);
            }
        });
        txt5000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5000ActionPerformed(evt);
            }
        });

        txt2000.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt2000.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt2000.setText("0");
        txt2000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt2000.setEnabled(false);
        txt2000.setMaximumSize(new java.awt.Dimension(50, 27));
        txt2000.setMinimumSize(new java.awt.Dimension(50, 27));
        txt2000.setPreferredSize(new java.awt.Dimension(50, 27));
        txt2000.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt2000FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt2000FocusLost(evt);
            }
        });
        txt2000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2000ActionPerformed(evt);
            }
        });

        txt1000.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt1000.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt1000.setText("0");
        txt1000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt1000.setEnabled(false);
        txt1000.setMaximumSize(new java.awt.Dimension(50, 27));
        txt1000.setMinimumSize(new java.awt.Dimension(50, 27));
        txt1000.setPreferredSize(new java.awt.Dimension(50, 27));
        txt1000.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt1000FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt1000FocusLost(evt);
            }
        });
        txt1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1000ActionPerformed(evt);
            }
        });

        txt500.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt500.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt500.setText("0");
        txt500.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt500.setEnabled(false);
        txt500.setMaximumSize(new java.awt.Dimension(50, 27));
        txt500.setMinimumSize(new java.awt.Dimension(50, 27));
        txt500.setPreferredSize(new java.awt.Dimension(50, 27));
        txt500.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt500FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt500FocusLost(evt);
            }
        });
        txt500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt500ActionPerformed(evt);
            }
        });

        txt100.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt100.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt100.setText("0");
        txt100.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt100.setEnabled(false);
        txt100.setMaximumSize(new java.awt.Dimension(50, 27));
        txt100.setMinimumSize(new java.awt.Dimension(50, 27));
        txt100.setPreferredSize(new java.awt.Dimension(50, 27));
        txt100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt100FocusLost(evt);
            }
        });
        txt100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt100ActionPerformed(evt);
            }
        });

        txt50.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt50.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt50.setText("0");
        txt50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt50.setEnabled(false);
        txt50.setMaximumSize(new java.awt.Dimension(50, 27));
        txt50.setMinimumSize(new java.awt.Dimension(50, 27));
        txt50.setPreferredSize(new java.awt.Dimension(50, 27));
        txt50.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt50FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt50FocusLost(evt);
            }
        });
        txt50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt50ActionPerformed(evt);
            }
        });

        txt20.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt20.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt20.setText("0");
        txt20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt20.setEnabled(false);
        txt20.setMaximumSize(new java.awt.Dimension(50, 27));
        txt20.setMinimumSize(new java.awt.Dimension(50, 27));
        txt20.setPreferredSize(new java.awt.Dimension(50, 27));
        txt20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt20FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt20FocusLost(evt);
            }
        });
        txt20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt20ActionPerformed(evt);
            }
        });

        txt10.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txt10.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt10.setText("0");
        txt10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt10.setEnabled(false);
        txt10.setMaximumSize(new java.awt.Dimension(50, 27));
        txt10.setMinimumSize(new java.awt.Dimension(50, 27));
        txt10.setPreferredSize(new java.awt.Dimension(50, 27));
        txt10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt10FocusLost(evt);
            }
        });
        txt10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt10ActionPerformed(evt);
            }
        });

        txtCoins.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtCoins.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCoins.setText("0");
        txtCoins.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCoins.setEnabled(false);
        txtCoins.setMaximumSize(new java.awt.Dimension(60, 27));
        txtCoins.setMinimumSize(new java.awt.Dimension(60, 27));
        txtCoins.setPreferredSize(new java.awt.Dimension(60, 27));
        txtCoins.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCoinsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCoinsFocusLost(evt);
            }
        });
        txtCoins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCoinsActionPerformed(evt);
            }
        });

        txtDrCr.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtDrCr.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDrCr.setText("0.00");
        txtDrCr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDrCr.setEnabled(false);
        txtDrCr.setMaximumSize(new java.awt.Dimension(194, 27));
        txtDrCr.setMinimumSize(new java.awt.Dimension(194, 27));
        txtDrCr.setPreferredSize(new java.awt.Dimension(194, 27));
        txtDrCr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDrCrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDrCrFocusLost(evt);
            }
        });
        txtDrCr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDrCrActionPerformed(evt);
            }
        });

        txtReasonorGlAccount1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtReasonorGlAccount1.setEnabled(false);
        txtReasonorGlAccount1.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtReasonorGlAccount1.setMaximumSize(new java.awt.Dimension(210, 27));
        txtReasonorGlAccount1.setMinimumSize(new java.awt.Dimension(210, 27));
        txtReasonorGlAccount1.setPreferredSize(new java.awt.Dimension(210, 27));
        txtReasonorGlAccount1.setSize(new java.awt.Dimension(210, 27));
        txtReasonorGlAccount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReasonorGlAccount1KeyReleased(evt);
            }
        });
        txtReasonorGlAccount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReasonorGlAccount1ActionPerformed(evt);
            }
        });
        txtReasonorGlAccount1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtReasonorGlAccount1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtReasonorGlAccount1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtReasonorGlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtReasonorGlAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(isCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt5000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt2000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt1000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtCoins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDrCr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtReasonorGlAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtReasonorGlAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtOther, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt5000, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt2000, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt1000, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCoins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDrCr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(isCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(225, 225, 225)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1338, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setText("ONLINE");

        statusUserName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        javax.swing.GroupLayout jXStatusBar1Layout = new javax.swing.GroupLayout(jXStatusBar1);
        jXStatusBar1.setLayout(jXStatusBar1Layout);
        jXStatusBar1Layout.setHorizontalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusGLCode, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jXStatusBar1Layout.setVerticalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusGLCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXStatusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txtSubSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubSectionActionPerformed
    new FindSectionOrOutsider(this, true, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 22, "අනු අංශය").setVisible(true);
    FindSectionOrOutsider.txtSearchData.grabFocus();
    removeNull();
}//GEN-LAST:event_txtSubSectionActionPerformed

private void txtSubSectionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSectionKeyReleased

}//GEN-LAST:event_txtSubSectionKeyReleased

private void txtSubSectionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSectionKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtSubSectionKeyTyped

private void txtOutSiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutSiderActionPerformed
    if (txtOutSider.getText().trim().equals("")) {
        new FindSectionOrOutsider(this, true, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 23, "පාර්ශවකරු").setVisible(true);
        removeNull();
    } else {
        txtReasonorGlAccount.grabFocus();
        removeNull();

    }

}//GEN-LAST:event_txtOutSiderActionPerformed

private void txtOutSiderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOutSiderKeyReleased

}//GEN-LAST:event_txtOutSiderKeyReleased

private void txtOutSiderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOutSiderKeyTyped
    //  char c=evt.getKeyChar();
    //  evt.setKeyChar(Character.toUpperCase(c));

}//GEN-LAST:event_txtOutSiderKeyTyped

private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
    saveHD();
    txtF9DNo.grabFocus();
    enableFields();
}//GEN-LAST:event_btnNewActionPerformed

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    saveHD();
    int i = JOptionPane.showConfirmDialog(this, "නව ගණුදෙනුවක් සිදු කරනවාද?", "නව ගණුදෙනුවක් සිදු කරනවාද?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (i == JOptionPane.YES_OPTION) {
        this.dispose();
        new CommonCashReceiptCash(null, true, code, 1).setVisible(true);
        CommonJurnalForm.ItemRefresh.doClick();
    } else {
        this.dispose();
        CommonJurnalForm.ItemRefresh.doClick();
    }
//    saveDTFromTable();
}//GEN-LAST:event_btnSaveActionPerformed

private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
    try {
        JasperReport compileReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("/R007_F28_F10.jrxml"));
        Map params = new HashMap<Object, Object>();
        params.put("Oth_Trn_HDID", Integer.parseInt(txtNo2.getText()));
        params.put("MPCSName", data.getBranchName());
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DB.getConnection());
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewer.setVisible(true);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JRException ex) {
        Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnPrintActionPerformed

private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
    confirm(id, statusGLCode.getText());
}//GEN-LAST:event_btnConfirmActionPerformed

private void txtF9DNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtF9DNoKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtF9DNoKeyReleased

    private void Table1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table1ComponentMoved
        jScrollPane2.getViewport().setViewPosition(jScrollPane1.getViewport().getViewPosition());
    }//GEN-LAST:event_Table1ComponentMoved

    private void Table2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseReleased

    }//GEN-LAST:event_Table2MouseReleased

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        if (Table2.getSelectedRow() > -1) {
            int selectedRow = Table2.getSelectedRow();
            if (code.equals("02-01")) {
                txtReasonorGlAccount.setText(String.valueOf(Table2.getValueAt(selectedRow, 1)));
//                if (code.equals("02-01")) {
                txtReasonorGlAccount1.setText(String.valueOf(Table2.getValueAt(selectedRow, 2)));
                txtReasonorGlAccount1.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 18))));
//                }
                if (Boolean.valueOf(String.valueOf(Table2.getValueAt(selectedRow, 3)))) {
                    isCheck.setSelected(true);
                } else {
                    isCheck.setSelected(false);
                }
                txtOther.setText(String.valueOf(Table2.getValueAt(selectedRow, 4)));
                txt5000.setText(String.valueOf(Table2.getValueAt(selectedRow, 5)));
                txt2000.setText(String.valueOf(Table2.getValueAt(selectedRow, 6)));
                txt1000.setText(String.valueOf(Table2.getValueAt(selectedRow, 7)));
                txt500.setText(String.valueOf(Table2.getValueAt(selectedRow, 8)));
                txt100.setText(String.valueOf(Table2.getValueAt(selectedRow, 9)));
                txt50.setText(String.valueOf(Table2.getValueAt(selectedRow, 10)));
                txt20.setText(String.valueOf(Table2.getValueAt(selectedRow, 11)));
                txt10.setText(String.valueOf(Table2.getValueAt(selectedRow, 12)));
                txtCoins.setText(String.valueOf(Table2.getValueAt(selectedRow, 13)));
//                if (code.equals("02-01")) {
                txtDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 14)));
//                } 
//                else if (code.equals("02-03")) {
//                    txtDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 15)));
//                }
                txtReasonorGlAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 16))));
                dtid = Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 17)));
            } else if (code.equals("02-03")) {
                txtReasonorGlAccount.setText(String.valueOf(Table2.getValueAt(selectedRow, 0)));
                if (Boolean.valueOf(String.valueOf(Table2.getValueAt(selectedRow, 3)))) {
                    isCheck.setSelected(true);
                } else {
                    isCheck.setSelected(false);
                }
                txtOther.setText(String.valueOf(Table2.getValueAt(selectedRow, 4)));
                txt5000.setText(String.valueOf(Table2.getValueAt(selectedRow, 5)));
                txt2000.setText(String.valueOf(Table2.getValueAt(selectedRow, 6)));
                txt1000.setText(String.valueOf(Table2.getValueAt(selectedRow, 7)));
                txt500.setText(String.valueOf(Table2.getValueAt(selectedRow, 8)));
                txt100.setText(String.valueOf(Table2.getValueAt(selectedRow, 9)));
                txt50.setText(String.valueOf(Table2.getValueAt(selectedRow, 10)));
                txt20.setText(String.valueOf(Table2.getValueAt(selectedRow, 11)));
                txt10.setText(String.valueOf(Table2.getValueAt(selectedRow, 12)));
                txtCoins.setText(String.valueOf(Table2.getValueAt(selectedRow, 13)));
                txtDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 15)));
                txtReasonorGlAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 16))));
                dtid = Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 17)));

            }
        }
        removeNull();
    }//GEN-LAST:event_Table2MouseClicked

    private void Table2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyReleased

    }//GEN-LAST:event_Table2KeyReleased

    private void txtF10NoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF10NoActionPerformed
        txtF9DNo.grabFocus();
    }//GEN-LAST:event_txtF10NoActionPerformed

    private void txtF9DNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF9DNoActionPerformed
        txtSubSection.grabFocus();
    }//GEN-LAST:event_txtF9DNoActionPerformed

    private void txtReasonorGlAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReasonorGlAccountActionPerformed
        if (txtReasonorGlAccount.getText().trim().equals("")) {
            if (code.equals("02-01")) {
                new FindSectionOrOutsider(this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 24, "කාරණය", "F10").setVisible(true);
                removeNull();
            } else if (code.equals("02-03")) {
                new FindAccount5Data(this, true, 6, 2).setVisible(true);
                removeNull();
            }
        }
    }//GEN-LAST:event_txtReasonorGlAccountActionPerformed

    private void isCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isCheckKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOther.grabFocus();
        }

    }//GEN-LAST:event_isCheckKeyReleased

    private void txtOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtherActionPerformed
        if (isCheck.isSelected()) {
            txtDrCr.grabFocus();
        } else {
            txt5000.grabFocus();
        }
    }//GEN-LAST:event_txtOtherActionPerformed

    private void txt5000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5000ActionPerformed
        txt2000.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt5000ActionPerformed

    private void txt2000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2000ActionPerformed
        txt1000.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt2000ActionPerformed

    private void txt1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1000ActionPerformed
        txt500.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt1000ActionPerformed

    private void txt500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt500ActionPerformed
        txt100.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt500ActionPerformed

    private void txt100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt100ActionPerformed
        txt50.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt100ActionPerformed

    private void txt50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt50ActionPerformed
        txt20.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt50ActionPerformed

    private void txt20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt20ActionPerformed
        txt10.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt20ActionPerformed

    private void txt10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt10ActionPerformed
        txtCoins.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txt10ActionPerformed

    private void txtCoinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCoinsActionPerformed
        txtDrCr.grabFocus();
        calDebitOrCredit();
    }//GEN-LAST:event_txtCoinsActionPerformed

    private void txtDrCrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDrCrActionPerformed
        txtReasonorGlAccount.grabFocus();
        saveDT();
        saveHD();
        viewDT(txtNo2.getText());
        getTotal();
        initRowData();
    }//GEN-LAST:event_txtDrCrActionPerformed

    private void txt5000FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5000FocusGained
        txt5000.selectAll();
    }//GEN-LAST:event_txt5000FocusGained

    private void txt5000FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt5000FocusLost
        txt5000.select(0, 0);
        calDebitOrCredit();
        if (txt5000.getText().trim().equals("")) {
            txt5000.setText("0");
        }
    }//GEN-LAST:event_txt5000FocusLost

    private void txt2000FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2000FocusGained
        txt2000.selectAll();
    }//GEN-LAST:event_txt2000FocusGained

    private void txt2000FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2000FocusLost
        txt2000.select(0, 0);
        calDebitOrCredit();
        if (txt2000.getText().trim().equals("")) {
            txt2000.setText("0");
        }
    }//GEN-LAST:event_txt2000FocusLost

    private void txt1000FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1000FocusGained
        txt1000.selectAll();
    }//GEN-LAST:event_txt1000FocusGained

    private void txt1000FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1000FocusLost
        txt1000.select(0, 0);
        calDebitOrCredit();
        if (txt1000.getText().trim().equals("")) {
            txt1000.setText("0");
        }
    }//GEN-LAST:event_txt1000FocusLost

    private void txt500FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt500FocusGained
        txt500.selectAll();
    }//GEN-LAST:event_txt500FocusGained

    private void txt500FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt500FocusLost
        txt500.select(0, 0);
        calDebitOrCredit();
        if (txt500.getText().trim().equals("")) {
            txt500.setText("0");
        }
    }//GEN-LAST:event_txt500FocusLost

    private void txt100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt100FocusGained
        txt100.selectAll();
    }//GEN-LAST:event_txt100FocusGained

    private void txt100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt100FocusLost
        txt100.select(0, 0);
        calDebitOrCredit();
        if (txt100.getText().trim().equals("")) {
            txt100.setText("0");
        }
    }//GEN-LAST:event_txt100FocusLost

    private void txt50FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt50FocusGained
        txt50.selectAll();
    }//GEN-LAST:event_txt50FocusGained

    private void txt50FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt50FocusLost
        txt50.select(0, 0);
        calDebitOrCredit();
        if (txt50.getText().trim().equals("")) {
            txt50.setText("0");
        }
    }//GEN-LAST:event_txt50FocusLost

    private void txt20FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt20FocusGained
        txt20.selectAll();
    }//GEN-LAST:event_txt20FocusGained

    private void txt20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt20FocusLost
        txt20.select(0, 0);
        calDebitOrCredit();
        if (txt20.getText().trim().equals("")) {
            txt20.setText("0");
        }
    }//GEN-LAST:event_txt20FocusLost

    private void txt10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt10FocusGained
        txt10.selectAll();
    }//GEN-LAST:event_txt10FocusGained

    private void txt10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt10FocusLost
        txt10.select(0, 0);
        calDebitOrCredit();
        if (txt10.getText().trim().equals("")) {
            txt10.setText("0");
        }
    }//GEN-LAST:event_txt10FocusLost

    private void txtCoinsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCoinsFocusGained
        txtCoins.selectAll();
    }//GEN-LAST:event_txtCoinsFocusGained

    private void txtCoinsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCoinsFocusLost
        txtCoins.select(0, 0);
        calDebitOrCredit();
        if (txtCoins.getText().trim().equals("")) {
            txtCoins.setText("0");
        }
    }//GEN-LAST:event_txtCoinsFocusLost

    private void txtDrCrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDrCrFocusGained
        txtDrCr.selectAll();
    }//GEN-LAST:event_txtDrCrFocusGained

    private void txtDrCrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDrCrFocusLost
        txtDrCr.select(0, 0);
        calDebitOrCredit();
        if (txtDrCr.getText().trim().equals("")) {
            txtDrCr.setText("0.00");
        }
    }//GEN-LAST:event_txtDrCrFocusLost

    private void txtF10NoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF10NoFocusGained
        txtF10No.setBackground(Color.YELLOW);
//        if ("0".equals(txtNo2.getText()) || "".equals(txtNo2.getText().trim())) {
//            JOptionPane.showMessageDialog(this, "කරුණාකර නව අංකය ලබා ගන්න", "කරුණාකර නව අංකය ලබා ගන්න", JOptionPane.WARNING_MESSAGE);
//            jButton1.grabFocus();
//        }
    }//GEN-LAST:event_txtF10NoFocusGained

    private void txtF10NoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF10NoFocusLost
        txtF10No.setBackground(Color.WHITE);

    }//GEN-LAST:event_txtF10NoFocusLost

    private void txtF9DNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF9DNoFocusGained
        txtF9DNo.setBackground(Color.YELLOW);
//        if (txtF10No.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(this, "F10 අංකය ඇතුලත් කර නොමැත", "අංකය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
//            txtF10No.grabFocus();
//        }
    }//GEN-LAST:event_txtF9DNoFocusGained

    private void txtF9DNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF9DNoFocusLost
        txtF9DNo.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtF9DNoFocusLost

    private void txtSubSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubSectionFocusGained
        txtSubSection.setBackground(Color.YELLOW);
//        txtOutSider.setFocusable(true);
//        txtReasonorGlAccount.setFocusable(true);
    }//GEN-LAST:event_txtSubSectionFocusGained

    private void txtSubSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubSectionFocusLost
        txtSubSection.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtSubSectionFocusLost

    private void txtOutSiderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOutSiderFocusGained
        txtOutSider.setBackground(Color.YELLOW);
        if (txtSubSection.getId() == 0) {
            txtSubSection.grabFocus();
            JOptionPane.showMessageDialog(this, "අංශය ඇතුලත් කර නොමැත", "අංශය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
//            txtOutSider.setFocusable(false);
//            txtSubSection.requestFocusInWindow();
        }
    }//GEN-LAST:event_txtOutSiderFocusGained

    private void txtOutSiderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOutSiderFocusLost
        txtOutSider.setBackground(Color.WHITE);

    }//GEN-LAST:event_txtOutSiderFocusLost

    private void txtReasonorGlAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonorGlAccountFocusGained
        txtReasonorGlAccount.setBackground(Color.YELLOW);
        if (txtOutSider.getId() == 0) {
            txtOutSider.grabFocus();
            JOptionPane.showMessageDialog(this, "කළමණාකරු ඇතුලත් කර නොමැත", "කළමණාකරු ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
//            txtReasonorGlAccount.setFocusable(false);
//            txtOutSider.requestFocusInWindow();
        }
//        if (txtRelatedGLAccount.getId() == 0) {
//            txtRelatedGLAccount.grabFocus();
//            JOptionPane.showMessageDialog(this, "අදාළ අංශයේ ජංගම ගිණුම ඇතුලත් කර නොමැත", "අදාළ අංශයේ ජංගම ගිණුම ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_txtReasonorGlAccountFocusGained

    private void txtReasonorGlAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonorGlAccountFocusLost
        txtReasonorGlAccount.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtReasonorGlAccountFocusLost

    private void txtOtherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtherFocusGained
        txtOther.setBackground(Color.YELLOW);
        viewDT(id);
        getTotal();
    }//GEN-LAST:event_txtOtherFocusGained

    private void txtOtherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtherFocusLost
        txtOther.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtOtherFocusLost

    private void isCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isCheckActionPerformed

        if (isCheck.isSelected()) {
            JTextField fields[] = {txt5000, txt2000, txt1000, txt500, txt100, txt50, txt20, txt10, txtCoins};
            for (JTextField field : fields) {
                field.setEnabled(false);
            }
        } else {
            JTextField fields[] = {txt5000, txt2000, txt1000, txt500, txt100, txt50, txt20, txt10, txtCoins};
            for (JTextField field : fields) {
                field.setEnabled(true);
            }
        }
    }//GEN-LAST:event_isCheckActionPerformed

    private void txtRelatedGLAccountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRelatedGLAccountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRelatedGLAccountKeyTyped

    private void txtRelatedGLAccountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRelatedGLAccountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRelatedGLAccountKeyReleased

    private void txtRelatedGLAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRelatedGLAccountActionPerformed
        new FindAccount5Data(this, true, 12, 2).setVisible(true);
    }//GEN-LAST:event_txtRelatedGLAccountActionPerformed

    private void txtRelatedGLAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRelatedGLAccountFocusGained
        txtRelatedGLAccount.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtRelatedGLAccountFocusGained

    private void txtRelatedGLAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRelatedGLAccountFocusLost
        txtRelatedGLAccount.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtRelatedGLAccountFocusLost

    private void isCheckKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isCheckKeyTyped

    }//GEN-LAST:event_isCheckKeyTyped

    private void isCheckKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isCheckKeyPressed

    }//GEN-LAST:event_isCheckKeyPressed

    private void txtReasonorGlAccountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReasonorGlAccountKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            if (code.equals("02-01")) {
                txtReasonorGlAccount.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 1)));
                txtReasonorGlAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 15))));
            } else if (code.equals("02-03")) {
                txtReasonorGlAccount.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 0)));
                txtReasonorGlAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 15))));
            }
            isCheck.grabFocus();
        }
    }//GEN-LAST:event_txtReasonorGlAccountKeyReleased

    private void txtOtherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtherKeyReleased
        if (code.equals("02-03")) {
            if (evt.getKeyCode() == KeyEvent.VK_F2) {
                String hdid = txtNo2.getText();
                String glText = txtReasonorGlAccount.getText();
                int glid = txtReasonorGlAccount.getId();
                new BankDepositDialog(this, true, hdid, glid, glText, FormatConstants.dateFormat.format(txtTrnDate.getDate()),code).setVisible(true);
            }
        }
    }//GEN-LAST:event_txtOtherKeyReleased

    private void txtReasonorGlAccount1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReasonorGlAccount1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReasonorGlAccount1KeyReleased

    private void txtReasonorGlAccount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReasonorGlAccount1ActionPerformed
        new FindAccount5Data(this, true, 15, 1).setVisible(true);
        removeNull();
    }//GEN-LAST:event_txtReasonorGlAccount1ActionPerformed

    private void txtReasonorGlAccount1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonorGlAccount1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReasonorGlAccount1FocusGained

    private void txtReasonorGlAccount1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonorGlAccount1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReasonorGlAccount1FocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (txtNo2.getText().trim().equals("") || txtNo2.getText().trim().equals("0")) {
            btnSave.doClick();
        }
        CommonJurnalForm.ItemRefresh.doClick();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptCash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptCash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptCash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptCash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JDialog dialog = new CommonCashReceiptCash(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static org.jdesktop.swingx.JXTable Table1;
    private org.jdesktop.swingx.JXTable Table2;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    public static javax.swing.JCheckBox isCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    public static javax.swing.JLabel statusGLCode;
    private javax.swing.JLabel statusUserName;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt100;
    private javax.swing.JTextField txt1000;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextField txt2000;
    private javax.swing.JTextField txt50;
    private javax.swing.JTextField txt500;
    private javax.swing.JTextField txt5000;
    private javax.swing.JTextField txtCoins;
    private javax.swing.JTextField txtDrCr;
    private javax.swing.JTextField txtF10No;
    private javax.swing.JTextField txtF9DNo;
    private javax.swing.JTextField txtNo1;
    private javax.swing.JTextField txtNo2;
    private javax.swing.JTextField txtOther;
    public static org.nbs.components.HiddenIDTextField txtOutSider;
    public static org.nbs.components.HiddenIDTextField txtReasonorGlAccount;
    public static org.nbs.components.HiddenIDTextField txtReasonorGlAccount1;
    public static org.nbs.components.HiddenIDTextField txtRelatedGLAccount;
    public static org.nbs.components.HiddenIDTextField txtSubSection;
    private org.nbs.components.SelectAtFocusFormattedTextField txtTotal;
    private com.toedter.calendar.JDateChooser txtTrnDate;
    // End of variables declaration//GEN-END:variables

    private void saveHD() {
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(txtNo2.getText());
            inputs.add(FormatConstants.dateFormat.format(txtTrnDate.getDate()));
            inputs.add(txtNo1.getText());
            inputs.add(txtF10No.getText());
            inputs.add(txtF9DNo.getText());
            inputs.add(txtSubSection.getId());
            inputs.add(statusGLCode.getText());
            inputs.add(txtOutSider.getId());
            inputs.add(txtRelatedGLAccount.getId());
            inputs.add(FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue());
            switch (code) {
                case "02-01":
                    inputs.add(FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue());
                    inputs.add(0);
                    break;
                case "02-03":
                    inputs.add(0);
                    inputs.add(FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue());
                    break;
            }
            inputs.add("");
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add("@F10");
            inputs.add("@recID");
            inputs.add("@billNo");

            List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranHD", 3);
//            hdID = Integer.parseInt(String.valueOf(output.get(0)));
            txtNo1.setText(String.valueOf(output.get(2)));
            txtNo2.setText(String.valueOf(output.get(1)));
            id = txtNo2.getText();
            txtF10No.setText(String.valueOf(output.get(0)));

        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        }
        removeNull();
    }
    private int dtid = 0;

    private void saveDT() {

        try {
            List inputs = new ArrayList<>();
            inputs.add(dtid);
            inputs.add(txtNo2.getText());
            inputs.add(txtSubSection.getId());
            inputs.add(txtOutSider.getId());
            inputs.add(statusGLCode.getText());
            if (code.equals("02-01")) {
                inputs.add(txtReasonorGlAccount.getId());
            } else {
                inputs.add(4);
            }
            inputs.add(isCheck.isSelected());
            inputs.add(txtOther.getText());
            if (code.equals("02-03")) {
                inputs.add(txtReasonorGlAccount.getId());
            } else {
                inputs.add(txtReasonorGlAccount1.getId());
            }
            inputs.add("");
            switch (code) {
                case "02-01":
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    inputs.add(0);
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    break;
                case "02-03":
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    inputs.add(0);
                    break;
            }
            inputs.add(txt5000.getText());
            inputs.add(txt2000.getText());
            inputs.add(txt1000.getText());
            inputs.add(txt500.getText());
            inputs.add(txt100.getText());
            inputs.add(txt50.getText());
            inputs.add(txt20.getText());
            inputs.add(txt10.getText());
            inputs.add(txtCoins.getText());
            inputs.add(0);
            inputs.add(0);
            inputs.add(GeneralUserLogin.data.getUsername());
            if (code.equals("02-01")) {
                if (txtReasonorGlAccount.getId() != 0 && txtReasonorGlAccount1.getId() != 0) {
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranDT", 0);
                dtid = 0;
            }else{
                JOptionPane.showMessageDialog(this, "කාරණය හෝ ගිණුම් විස්තරය ඇතුලත් කර නොමැත");
                txtReasonorGlAccount.grabFocus();
            }
            }else if (code.equals("02-03")) {
                if (txtReasonorGlAccount.getId() != 0) {
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranDT", 0);
                dtid = 0;
            }else{
                JOptionPane.showMessageDialog(this, "කාරණය හෝ ගිණුම් විස්තරය ඇතුලත් කර නොමැත");
                txtReasonorGlAccount.grabFocus();
            }
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        }
        removeNull();
    }

    private void calDebitOrCredit() {
        try {
            int val5000 = Integer.parseInt(txt5000.getText());
            int val2000 = Integer.parseInt(txt2000.getText());
            int val1000 = Integer.parseInt(txt1000.getText());
            int val500 = Integer.parseInt(txt500.getText());
            int val100 = Integer.parseInt(txt100.getText());
            int val50 = Integer.parseInt(txt50.getText());
            int val20 = Integer.parseInt(txt20.getText());
            int val10 = Integer.parseInt(txt10.getText());
            double valCoins = Double.parseDouble(txtCoins.getText());

            double valTotal = val5000 * 5000 + val2000 * 2000 + val1000 * 1000 + val500 * 500 + val100 * 100 + val50 * 50 + val20 * 20 + val10 * 10 + valCoins;
            txtDrCr.setText(FormatConstants.numberFormat1.format(valTotal));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        removeNull();
    }

    private void getTotal() {
        double total = 0;
        if (code.equals("02-01")) {
            for (int i = 0; i < Table2.getRowCount(); i++) {
                try {
                    total += FormatConstants.numberFormat1.parse(String.valueOf(Table2.getValueAt(i, 14))).doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (code.equals("02-03")) {
            for (int i = 0; i < Table2.getRowCount(); i++) {
                try {
                    total += FormatConstants.numberFormat1.parse(String.valueOf(Table2.getValueAt(i, 15))).doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        txtTotal.setText(FormatConstants.numberFormat1.format(total));
        removeNull();
    }

    private void initRowData() {
        JTextField fields[] = {txt5000, txt2000, txt1000, txt500, txt100, txt50, txt20, txt10, txtCoins};
        for (JTextField field : fields) {
            field.setText("0");
        }
        txtReasonorGlAccount.setText("");
        txtReasonorGlAccount.setId(0);
        txtReasonorGlAccount1.setText("");
        txtReasonorGlAccount1.setId(0);
        txtOther.setText("");
        isCheck.setSelected(false);
        txtDrCr.setText("0.00");
        removeNull();
    }

    private void viewDT(String id) {
        try {
            dtm2.setRowCount(0);
            List<Object> inputs = new ArrayList<>();
            inputs.add(2);
            inputs.add(id);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewCashierEdit", 19);

            for (List rowData : outputs) {
                Vector v = new Vector();
                for (Object object : rowData) {
                    v.add(object);
                }
                dtm2.addRow(v);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        }
        removeNull();
    }

    private void enableFields() {
        Component components[] = {txtF10No, txtF9DNo, txtSubSection, txtOutSider, txtReasonorGlAccount, isCheck, txtOther, txt5000, txt2000, txt1000, txt500, txt100, txt50, txt20, txt10, txtCoins, txtDrCr, txtRelatedGLAccount, txtReasonorGlAccount1};
        for (Component component : components) {
            component.setEnabled(true);
        }
        removeNull();
    }

    private void removeNull() {
        for (JTextField textField : textFields) {
            JTextFieldRemoveNull.removeNull(textField);
        }
        for (JLabel label : labels) {
            JTextFieldRemoveNull.removeNull(label);
        }
    }

    private void setPermission() {
        if (!user.isAddable()) {
            btnNew.setEnabled(false);
        }
        if (!user.isEditable()) {
            btnSave.setEnabled(false);
        }
        if (!user.isCheckable()) {
            btnConfirm.setEnabled(false);
        }
    }

    private void confirm(String recID, String glcode) {
        try {
            String[] buttons = {"Yes", "No"};
            int i = JOptionPane.showOptionDialog(this, "<html><p><span style='color:red'>" + btnConfirm.getText().split(" ")[0] + " කළ පසු වෙනස් කල නොහැක.</span> " + btnConfirm.getText().split(" ")[0] + " කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
            if (i == JOptionPane.YES_OPTION) {
                List<Object> inputs = new ArrayList<>();
                inputs.add(glcode);
                inputs.add(recID);
                inputs.add(GeneralUserLogin.data.getUsername());
                inputs.add("@HDID");
                List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Create_Journals_All", 1);
                if (Integer.parseInt(String.valueOf(output.get(0))) > 0) {
                    JOptionPane.showMessageDialog(this, btnConfirm.getText().split(" ")[0] + " කිරීම සාර්ථකයි", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    CommonJurnalForm.ItemRefresh.doClick();
                } else {
                    JOptionPane.showMessageDialog(this, btnConfirm.getText().split(" ")[0] + " කිරීම අසාර්ථකයි", "අසාර්ථකයි", JOptionPane.ERROR_MESSAGE);
                    CommonJurnalForm.ItemRefresh.doClick();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
