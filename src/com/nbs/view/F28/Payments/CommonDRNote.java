/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * CommonDRNote.java
 *
 * Created on Feb 27, 2012, 1:38:40 PM
 */
package com.nbs.view.F28.Payments;

import Sources.GLCodes;
import Sources.TableColumnFontChanger;
import Sources.TableColumnRightAlign;
import com.nbs.impl.ServerConnection;
import com.nbs.view.F28.Stock.AB16Buyings;
import com.nbs.view.common.FormatConstants;
import com.nbs.view.common.GeneralUserLogin;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.UnicodeKeyBoard;
import com.nbs.view.common.search.FindAccount5Data;
import com.nbs.view.common.search.FindSectionOrOutsider;
import com.nbs.view.manager.CommonJurnalForm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.nbs.components.HiddenIDTextField;
import org.nbs.components.SelectAtFocusFormattedTextField;

/**
 *
 * @author mmh
 */
public class CommonDRNote extends javax.swing.JDialog {

    public static int DRSubSectionID;
    public static int DROutSiderID;
    public static int acctL5ID;
    public static int reasonID;
    private int glcode;
    private static int glForTotal;
    DefaultTableModel dtm1;
    static DefaultTableModel dtm2;
    public String hdID = "0";
    private JDialog dialog;
    public static DefaultTableModel dtm16B;
    private int status;

    public CommonDRNote(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dtm16B = (DefaultTableModel) TB_pur.getModel();
        setTitle("Server on " + GeneralUserLogin.data.getIP());
        setLocationRelativeTo(null);
        this.dialog = this;
        statusUserName.setText(GeneralUserLogin.data.getUserCode() + "-" + GeneralUserLogin.data.getUsername());
        txtTotal.setText("0.00");
        jdtCheckDate.setDate(GeneralUserLogin.data.getSystemDate());
        dtm2 = (DefaultTableModel) Table2.getModel();
        dtm1 = (DefaultTableModel) Table1.getModel();
        Table1.getTableHeader().setFont(new Font("Iskoola Pota", Font.PLAIN, 16));
        Table2.getTableHeader().setMaximumSize(new Dimension(jScrollPane2.getWidth(), 1));
        txtTrnDate.setDate((GeneralUserLogin.data.getSystemDate()));

        Table1.setValueAt("0.00", 0, 6);
        Table1.getColumn(6).setCellEditor(new NumberCellEditor());
        SelectAtFocusFormattedTextField field7 = (SelectAtFocusFormattedTextField) Table1.getCellEditor(0, 6).getTableCellEditorComponent(Table1, "", true, 0, 6);
        field7.setFont(new Font("Iskoola Pota", Font.PLAIN, 16));
        field7.setHorizontalAlignment(11);

        Table1.setShowGrid(true);
        Table2.setShowGrid(true);
        TableColumnFontChanger.setAmalee(Table1, 0);
        TableColumnFontChanger.setAmalee(Table1, 1);
        TableColumnFontChanger.setAmalee(Table1, 2);
        TableColumnFontChanger.setIskolaPotha(Table1, 5);
        TableColumnRightAlign.align(Table1, 6);
        TableColumnRightAlign.align(Table1, 7);

        TableColumnFontChanger.setAmalee(Table2, 0);
        TableColumnFontChanger.setAmalee(Table2, 1);
        TableColumnFontChanger.setAmalee(Table2, 2);
        TableColumnFontChanger.setIskolaPotha(Table2, 5);
        TableColumnRightAlign.align(Table2, 6);
        TableColumnRightAlign.align(Table2, 7);
        TableColumnRightAlign.alignMany(TB_pur, new int[]{4, 5});

        setComponentStatus(false);
    }

    public CommonDRNote(java.awt.Frame parent, boolean b, int GLCode, String title) {
        this(parent, b);
        this.glcode = GLCode;
        glForTotal = GLCode;;
        defineColumnWidths(glcode);
        switch (GLCode) {
            case GLCodes.PURCHASES:
            case GLCodes.CREDITORSPAYMENTS:
            case GLCodes.ADVANCEPURCHASES:
                jTabbedPane1.remove(0);
                break;
            case GLCodes.BANKDRNOTE:
            case GLCodes.BANKCRNOTE:
            case GLCodes.DRNOTE:
            case GLCodes.CRNOTE:
            case GLCodes.COMMONADMINDR:
            case GLCodes.COMMONADMINCR:
            case GLCodes.COMMONDRNOTE:
            case GLCodes.COMMONCRNOTE:
            case GLCodes.COMMONEXPENCEMATCHDR:
            case GLCodes.COMMONEXPENCEMATCHCR:
            case GLCodes.C9SALE:
                jTabbedPane1.remove(1);
                jTabbedPane1.remove(1);
                break;
            case GLCodes.OTHERPAYMENTS:
                jTabbedPane1.remove(0);
                jTabbedPane1.remove(1);
                break;
            case GLCodes.OTHERADVANCES:
                jTabbedPane1.remove(0);
                jTabbedPane1.remove(1);
                break;
        }
        if (glcode == GLCodes.CRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.BANKCRNOTE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONCRNOTE || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
            jLabel7.setText("බැර ගිණුම :");
        }
        btnConfirm.setVisible(false);
        lableName.setText(title);
        statusGLCode.setText(glcode + "");
    }

    public CommonDRNote(java.awt.Frame parent, boolean b, int GLCode, String title, String hdid) {
        this(parent, b, GLCode, title);
        this.hdID = hdid;
        statusHDID.setText(hdid);
        List<Object> inputs = new ArrayList<>();

        try {
            inputs.add(1);
            inputs.add(hdid);

            List<Object> hdoutput = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_ViewDRNoteEdit", 20);

            txtTrnDate.setDate(FormatConstants.dateFormat.parse(String.valueOf(hdoutput.get(1))));
            txtBillNo.setText(String.valueOf(hdoutput.get(2)));
            txtSubSection.setId(Integer.parseInt(String.valueOf(hdoutput.get(3))));
            txtSubSection.setText(String.valueOf(hdoutput.get(4)));
            txtOutSider.setId(Integer.parseInt(String.valueOf(hdoutput.get(5))));
            txtOutSider.setText(String.valueOf(hdoutput.get(6)));
            txtReason.setId(Integer.parseInt(String.valueOf(hdoutput.get(7))));
            txtReason.setText(String.valueOf(hdoutput.get(8)));
            txtRemarks.setText(String.valueOf(hdoutput.get(19)));
            txtTotal.setText(FormatConstants.numberFormat1.format(Double.parseDouble(String.valueOf(hdoutput.get(9)))));

            switch (glcode) {
                case GLCodes.PURCHASES:
                case GLCodes.CREDITORSPAYMENTS:
                case GLCodes.ADVANCEPURCHASES:
                case GLCodes.OTHERPAYMENTS:
                case GLCodes.OTHERADVANCES:
                    txtBankCrr.setId(Integer.parseInt(String.valueOf(hdoutput.get(10))));
                    txtBankCrr.setText(String.valueOf(hdoutput.get(11)));
                    break;
                case GLCodes.BANKDRNOTE:
                case GLCodes.BANKCRNOTE:
                case GLCodes.DRNOTE:
                case GLCodes.CRNOTE:
                case GLCodes.COMMONADMINDR:
                case GLCodes.COMMONADMINCR:
                case GLCodes.COMMONEXPENCEMATCHDR:
                case GLCodes.COMMONEXPENCEMATCHCR:
                case GLCodes.COMMONDRNOTE:
                case GLCodes.COMMONCRNOTE:
                case GLCodes.C9SALE:
                    txtDebitAccount.setId(Integer.parseInt(String.valueOf(hdoutput.get(10))));
                    txtDebitAccount.setText(String.valueOf(hdoutput.get(11)));
                    break;
            }
            txtCheckNo.setText(String.valueOf(hdoutput.get(12)));
            jdtCheckDate.setDate(hdoutput.get(13) == null?GeneralUserLogin.data.getSystemDate():FormatConstants.dateFormat.parse(String.valueOf(hdoutput.get(13))));
            txtMinAmount.setText(String.valueOf(hdoutput.get(14)));
            txtMaxAmount.setText(String.valueOf(hdoutput.get(15)));
            txtCheckNo1.setText(String.valueOf(hdoutput.get(16)));
            status = Integer.parseInt(String.valueOf(hdoutput.get(17)));

            viewDT(hdID);
            CommonDRNoteResource.showDT(hdid);

            if (status != 1) {
                btnNew.setEnabled(false);
                btnSave.setEnabled(false);
            }
            if (status == 3) {
                btnSave.setEnabled(true);
            }
            btnConfirm.setText(String.valueOf(hdoutput.get(18)));
            if (btnConfirm.getText().equals("null") || btnConfirm.getText().equals("")) {
                btnConfirm.setEnabled(false);
                btnExit.setEnabled(false);
                btnNew.setEnabled(false);
                btnSave.setEnabled(false);
                btnConfirm.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        setComponentStatus(true);
        btnConfirm.setVisible(true);
    }

    public CommonDRNote(java.awt.Frame parent, boolean b, int glcode, String title, String hdid, String code) {
        this(parent, b, glcode, title, hdid);
        if (code.equals("03-08")) {
            btnConfirm.setEnabled(true);
        } else {
            btnConfirm.setEnabled(false);
        }
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
        lableName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBillNo = new javax.swing.JTextField();
        txtOutSider = new org.nbs.components.HiddenIDTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSubSection = new org.nbs.components.HiddenIDTextField();
        txtReason = new org.nbs.components.HiddenIDTextField();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        txtTrnDate = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        txtDebitAccount = new org.nbs.components.HiddenIDTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRemarks = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtCheckNo = new javax.swing.JTextField();
        txtMaxAmount = new javax.swing.JTextField();
        txtMinAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jdtCheckDate = new com.toedter.calendar.JDateChooser();
        txtBankCrr = new org.nbs.components.HiddenIDTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCheckNo1 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TB_pur = new org.jdesktop.swingx.JXTable();
        btn16BAdd = new javax.swing.JButton();
        btn16BDelete = new javax.swing.JButton();
        btn16BNew = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        B16Total = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new org.jdesktop.swingx.JXTable();
        jPanel7 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new org.nbs.components.SelectAtFocusFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new org.jdesktop.swingx.JXTable();
        jPanel8 = new javax.swing.JPanel();
        txtDTSubSection = new org.nbs.components.HiddenIDTextField();
        txtDTOutsider = new org.nbs.components.HiddenIDTextField();
        txtDTReason = new org.nbs.components.HiddenIDTextField();
        txtDTRemarks = new org.nbs.components.HiddenIDTextField();
        txtDTGLAcct = new org.nbs.components.HiddenIDTextField();
        txtDTGLAcctDescription = new org.nbs.components.HiddenIDTextField();
        txtDTDrCr = new javax.swing.JFormattedTextField();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        jLabel1 = new javax.swing.JLabel();
        statusGLCode = new javax.swing.JLabel();
        statusSubsection = new javax.swing.JLabel();
        statusOutsider = new javax.swing.JLabel();
        statusReason = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        statusUserName = new javax.swing.JLabel();
        statusHDID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(1281, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 88, 136));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lableName.setFont(new java.awt.Font("Iskoola Pota", 0, 25)); // NOI18N
        lableName.setForeground(new java.awt.Color(255, 255, 255));
        lableName.setText("හර සටහන");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(lableName, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lableName, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(37, 51, 224)), "විස්තරය", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 17), new java.awt.Color(222, 56, 21))); // NOI18N

        txtBillNo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtBillNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBillNo.setText("0");
        txtBillNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBillNo.setEnabled(false);

        txtOutSider.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtOutSider.setFont(new java.awt.Font("AMALEE", 1, 18)); // NOI18N
        txtOutSider.addKeyListener(new java.awt.event.KeyAdapter() {
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

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("දිනය :");

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("අංකය :");

        jLabel4.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 153, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("අංශය :");

        jLabel5.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 153, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("නම :");

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 153, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("කාරණය :");

        txtSubSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSubSection.setFont(new java.awt.Font("AMALEE", 1, 18)); // NOI18N
        txtSubSection.addKeyListener(new java.awt.event.KeyAdapter() {
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

        txtReason.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtReason.setFont(new java.awt.Font("AMALEE", 1, 18)); // NOI18N
        txtReason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReasonActionPerformed(evt);
            }
        });
        txtReason.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtReasonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtReasonFocusLost(evt);
            }
        });

        jButton3.setText("....");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton12.setText("....");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("....");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        txtTrnDate.setDateFormatString("yyyy-MM-dd");
        txtTrnDate.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTrnDate, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOutSider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSubSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton12)
                            .addComponent(jButton13))))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSubSection, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOutSider, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton13))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4))))
        );

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jPanel6.setBackground(java.awt.Color.white);
        jPanel6.setLayout(null);

        txtDebitAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDebitAccount.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtDebitAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDebitAccountKeyReleased(evt);
            }
        });
        txtDebitAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDebitAccountActionPerformed(evt);
            }
        });
        txtDebitAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDebitAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDebitAccountFocusLost(evt);
            }
        });
        jPanel6.add(txtDebitAccount);
        txtDebitAccount.setBounds(113, 12, 400, 32);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtRemarks.setColumns(20);
        txtRemarks.setRows(5);
        txtRemarks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRemarksKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(txtRemarks);

        jPanel6.add(jScrollPane3);
        jScrollPane3.setBounds(111, 50, 450, 101);

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);
        jButton1.setBounds(520, 10, 40, 32);

        jLabel7.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 153, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("හර ගිණුම :");
        jPanel6.add(jLabel7);
        jLabel7.setBounds(12, 19, 90, 19);

        jLabel8.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 153, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("වෙනත් :");
        jPanel6.add(jLabel8);
        jLabel8.setBounds(12, 50, 90, 19);

        jTabbedPane1.addTab("GL Account", jPanel6);

        jPanel4.setBackground(java.awt.Color.white);

        txtCheckNo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtCheckNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCheckNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCheckNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCheckNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCheckNoFocusLost(evt);
            }
        });
        txtCheckNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckNoActionPerformed(evt);
            }
        });
        txtCheckNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCheckNoKeyReleased(evt);
            }
        });

        txtMaxAmount.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtMaxAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMaxAmount.setText("0.00");
        txtMaxAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMaxAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaxAmountFocusLost(evt);
            }
        });
        txtMaxAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxAmountActionPerformed(evt);
            }
        });
        txtMaxAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaxAmountKeyReleased(evt);
            }
        });

        txtMinAmount.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtMinAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMinAmount.setText("0.00");
        txtMinAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMinAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMinAmountFocusLost(evt);
            }
        });
        txtMinAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinAmountActionPerformed(evt);
            }
        });
        txtMinAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinAmountKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 153, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("දිනය :");
        jLabel9.setMaximumSize(new java.awt.Dimension(52, 21));
        jLabel9.setMinimumSize(new java.awt.Dimension(52, 21));
        jLabel9.setPreferredSize(new java.awt.Dimension(52, 21));

        jLabel10.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 153, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("චෙක් අංකය :");
        jLabel10.setBorder(null);

        jLabel11.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 153, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("බැංකුව :");
        jLabel11.setBorder(null);
        jLabel11.setMaximumSize(new java.awt.Dimension(94, 21));
        jLabel11.setMinimumSize(new java.awt.Dimension(94, 21));
        jLabel11.setPreferredSize(new java.awt.Dimension(94, 21));

        jLabel12.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 153, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("වටිනාකම :");
        jLabel12.setBorder(null);
        jLabel12.setMaximumSize(new java.awt.Dimension(94, 21));
        jLabel12.setMinimumSize(new java.awt.Dimension(94, 21));
        jLabel12.setPreferredSize(new java.awt.Dimension(94, 21));

        jLabel13.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 153, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("උපරිමය :");
        jLabel13.setBorder(null);
        jLabel13.setMaximumSize(new java.awt.Dimension(94, 21));
        jLabel13.setMinimumSize(new java.awt.Dimension(94, 21));
        jLabel13.setPreferredSize(new java.awt.Dimension(94, 21));

        jdtCheckDate.setDateFormatString("yyyy-MM-dd");
        jdtCheckDate.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        txtBankCrr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBankCrr.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        txtBankCrr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBankCrrKeyReleased(evt);
            }
        });
        txtBankCrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBankCrrActionPerformed(evt);
            }
        });
        txtBankCrr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBankCrrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBankCrrFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 153, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("අංකය :");

        txtCheckNo1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtCheckNo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCheckNo1.setText("0");
        txtCheckNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCheckNo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCheckNo1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCheckNo1MouseEntered(evt);
            }
        });
        txtCheckNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckNo1ActionPerformed(evt);
            }
        });
        txtCheckNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCheckNo1KeyReleased(evt);
            }
        });

        jButton9.setText("...");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMinAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCheckNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCheckNo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdtCheckDate, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtBankCrr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBankCrr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdtCheckDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCheckNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMinAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheckNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bank Account", jPanel4);

        jPanel5.setBackground(java.awt.Color.white);

        TB_pur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HDID", "TRNID", "16BNo", "InvoiceNo", "16B_Amount", "PaidAmount", "OutsiderName", "SubSectionName"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TB_pur.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TB_pur.getTableHeader().setReorderingAllowed(false);
        TB_pur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TB_purMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TB_pur);
        if (TB_pur.getColumnModel().getColumnCount() > 0) {
            TB_pur.getColumnModel().getColumn(0).setMinWidth(75);
            TB_pur.getColumnModel().getColumn(0).setPreferredWidth(75);
            TB_pur.getColumnModel().getColumn(0).setMaxWidth(75);
            TB_pur.getColumnModel().getColumn(1).setMinWidth(100);
            TB_pur.getColumnModel().getColumn(1).setPreferredWidth(100);
            TB_pur.getColumnModel().getColumn(1).setMaxWidth(100);
            TB_pur.getColumnModel().getColumn(2).setMinWidth(100);
            TB_pur.getColumnModel().getColumn(2).setPreferredWidth(100);
            TB_pur.getColumnModel().getColumn(2).setMaxWidth(100);
            TB_pur.getColumnModel().getColumn(3).setMinWidth(100);
            TB_pur.getColumnModel().getColumn(3).setPreferredWidth(100);
            TB_pur.getColumnModel().getColumn(3).setMaxWidth(100);
            TB_pur.getColumnModel().getColumn(4).setMinWidth(200);
            TB_pur.getColumnModel().getColumn(4).setPreferredWidth(200);
            TB_pur.getColumnModel().getColumn(4).setMaxWidth(200);
            TB_pur.getColumnModel().getColumn(5).setMinWidth(100);
            TB_pur.getColumnModel().getColumn(5).setPreferredWidth(100);
            TB_pur.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        btn16BAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn16BAdd.setText("Add");
        btn16BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16BAddActionPerformed(evt);
            }
        });

        btn16BDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn16BDelete.setText("Del");
        btn16BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16BDeleteActionPerformed(evt);
            }
        });

        btn16BNew.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn16BNew.setText("New");
        btn16BNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16BNewActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("එකතුව :");

        B16Total.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(btn16BAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn16BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn16BNew, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B16Total, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn16BAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn16BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn16BNew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B16Total, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("16 B", jPanel5);

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", "", "trnDTID", "SubSectionID", "OutsiderID", "ReasonID", "AcctL5ID", "RemarksID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
            Table2.getColumnModel().getColumn(0).setResizable(false);
            Table2.getColumnModel().getColumn(0).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(1).setResizable(false);
            Table2.getColumnModel().getColumn(1).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(2).setResizable(false);
            Table2.getColumnModel().getColumn(2).setPreferredWidth(175);
            Table2.getColumnModel().getColumn(3).setResizable(false);
            Table2.getColumnModel().getColumn(3).setPreferredWidth(130);
            Table2.getColumnModel().getColumn(4).setResizable(false);
            Table2.getColumnModel().getColumn(4).setPreferredWidth(120);
            Table2.getColumnModel().getColumn(5).setResizable(false);
            Table2.getColumnModel().getColumn(5).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(6).setResizable(false);
            Table2.getColumnModel().getColumn(6).setPreferredWidth(150);
            Table2.getColumnModel().getColumn(7).setResizable(false);
            Table2.getColumnModel().getColumn(7).setPreferredWidth(150);
            Table2.getColumnModel().getColumn(8).setMinWidth(0);
            Table2.getColumnModel().getColumn(8).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(8).setMaxWidth(0);
            Table2.getColumnModel().getColumn(9).setMinWidth(0);
            Table2.getColumnModel().getColumn(9).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(9).setMaxWidth(0);
            Table2.getColumnModel().getColumn(10).setMinWidth(0);
            Table2.getColumnModel().getColumn(10).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(10).setMaxWidth(0);
            Table2.getColumnModel().getColumn(11).setMinWidth(0);
            Table2.getColumnModel().getColumn(11).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(11).setMaxWidth(0);
            Table2.getColumnModel().getColumn(12).setMinWidth(0);
            Table2.getColumnModel().getColumn(12).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(12).setMaxWidth(0);
            Table2.getColumnModel().getColumn(13).setMinWidth(0);
            Table2.getColumnModel().getColumn(13).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(13).setMaxWidth(0);
        }

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

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

        btnExit.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnExit.setText("වසන්න");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnConfirm.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnConfirm.setText("ස්ථීර");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Total Book Balance :");

        txtTotal.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtTotal.setHorizontalAlignment(11);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "අංශය", "නම", "කාරණය", "වෙනත්", "ගිනුම් අංකය", "ගිනුම් විස්තරය", "හර", "බැර", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table1.setRowHeight(29);
        Table1.getTableHeader().setReorderingAllowed(false);
        Table1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Table1ComponentMoved(evt);
            }
        });
        Table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Table1);
        if (Table1.getColumnModel().getColumnCount() > 0) {
            Table1.getColumnModel().getColumn(0).setResizable(false);
            Table1.getColumnModel().getColumn(0).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(1).setResizable(false);
            Table1.getColumnModel().getColumn(1).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(2).setResizable(false);
            Table1.getColumnModel().getColumn(2).setPreferredWidth(175);
            Table1.getColumnModel().getColumn(3).setResizable(false);
            Table1.getColumnModel().getColumn(3).setPreferredWidth(130);
            Table1.getColumnModel().getColumn(4).setResizable(false);
            Table1.getColumnModel().getColumn(4).setPreferredWidth(120);
            Table1.getColumnModel().getColumn(5).setResizable(false);
            Table1.getColumnModel().getColumn(5).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(6).setResizable(false);
            Table1.getColumnModel().getColumn(6).setPreferredWidth(150);
            Table1.getColumnModel().getColumn(7).setResizable(false);
            Table1.getColumnModel().getColumn(7).setPreferredWidth(150);
            Table1.getColumnModel().getColumn(8).setMinWidth(0);
            Table1.getColumnModel().getColumn(8).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(8).setMaxWidth(0);
            Table1.getColumnModel().getColumn(9).setMinWidth(0);
            Table1.getColumnModel().getColumn(9).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(9).setMaxWidth(0);
            Table1.getColumnModel().getColumn(10).setMinWidth(0);
            Table1.getColumnModel().getColumn(10).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(10).setMaxWidth(0);
            Table1.getColumnModel().getColumn(11).setMinWidth(0);
            Table1.getColumnModel().getColumn(11).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 1));

        txtDTSubSection.setBackground(new java.awt.Color(191, 214, 235));
        txtDTSubSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTSubSection.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtDTSubSection.setMaximumSize(new java.awt.Dimension(223, 30));
        txtDTSubSection.setMinimumSize(new java.awt.Dimension(223, 30));
        txtDTSubSection.setPreferredSize(new java.awt.Dimension(223, 30));
        txtDTSubSection.setSize(new java.awt.Dimension(223, 30));
        txtDTSubSection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDTSubSectionKeyReleased(evt);
            }
        });
        txtDTSubSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTSubSectionActionPerformed(evt);
            }
        });
        txtDTSubSection.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTSubSectionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTSubSectionFocusLost(evt);
            }
        });

        txtDTOutsider.setBackground(new java.awt.Color(191, 214, 235));
        txtDTOutsider.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTOutsider.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtDTOutsider.setMaximumSize(new java.awt.Dimension(221, 30));
        txtDTOutsider.setMinimumSize(new java.awt.Dimension(221, 30));
        txtDTOutsider.setPreferredSize(new java.awt.Dimension(221, 30));
        txtDTOutsider.setSize(new java.awt.Dimension(221, 30));
        txtDTOutsider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDTOutsiderKeyReleased(evt);
            }
        });
        txtDTOutsider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTOutsiderActionPerformed(evt);
            }
        });
        txtDTOutsider.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTOutsiderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTOutsiderFocusLost(evt);
            }
        });

        txtDTReason.setBackground(new java.awt.Color(191, 214, 235));
        txtDTReason.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTReason.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtDTReason.setMaximumSize(new java.awt.Dimension(195, 30));
        txtDTReason.setMinimumSize(new java.awt.Dimension(195, 30));
        txtDTReason.setPreferredSize(new java.awt.Dimension(195, 30));
        txtDTReason.setSize(new java.awt.Dimension(195, 30));
        txtDTReason.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDTReasonKeyReleased(evt);
            }
        });
        txtDTReason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTReasonActionPerformed(evt);
            }
        });
        txtDTReason.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTReasonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTReasonFocusLost(evt);
            }
        });

        txtDTRemarks.setBackground(new java.awt.Color(191, 214, 235));
        txtDTRemarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTRemarks.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtDTRemarks.setMaximumSize(new java.awt.Dimension(152, 30));
        txtDTRemarks.setMinimumSize(new java.awt.Dimension(152, 30));
        txtDTRemarks.setPreferredSize(new java.awt.Dimension(152, 30));
        txtDTRemarks.setSize(new java.awt.Dimension(150, 30));
        txtDTRemarks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDTRemarksKeyReleased(evt);
            }
        });
        txtDTRemarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTRemarksActionPerformed(evt);
            }
        });
        txtDTRemarks.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTRemarksFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTRemarksFocusLost(evt);
            }
        });

        txtDTGLAcct.setBackground(new java.awt.Color(191, 214, 235));
        txtDTGLAcct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTGLAcct.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtDTGLAcct.setMaximumSize(new java.awt.Dimension(142, 30));
        txtDTGLAcct.setMinimumSize(new java.awt.Dimension(142, 30));
        txtDTGLAcct.setPreferredSize(new java.awt.Dimension(142, 30));
        txtDTGLAcct.setSize(new java.awt.Dimension(142, 30));
        txtDTGLAcct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDTGLAcctKeyReleased(evt);
            }
        });
        txtDTGLAcct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTGLAcctActionPerformed(evt);
            }
        });
        txtDTGLAcct.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTGLAcctFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTGLAcctFocusLost(evt);
            }
        });

        txtDTGLAcctDescription.setEditable(false);
        txtDTGLAcctDescription.setBackground(new java.awt.Color(191, 214, 235));
        txtDTGLAcctDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTGLAcctDescription.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtDTGLAcctDescription.setMaximumSize(new java.awt.Dimension(220, 30));
        txtDTGLAcctDescription.setMinimumSize(new java.awt.Dimension(220, 30));
        txtDTGLAcctDescription.setPreferredSize(new java.awt.Dimension(220, 30));
        txtDTGLAcctDescription.setSize(new java.awt.Dimension(220, 30));
        txtDTGLAcctDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTGLAcctDescriptionActionPerformed(evt);
            }
        });
        txtDTGLAcctDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTGLAcctDescriptionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTGLAcctDescriptionFocusLost(evt);
            }
        });

        txtDTDrCr.setBackground(new java.awt.Color(191, 214, 235));
        txtDTDrCr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTDrCr.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDTDrCr.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        txtDTDrCr.setMaximumSize(new java.awt.Dimension(173, 30));
        txtDTDrCr.setMinimumSize(new java.awt.Dimension(173, 30));
        txtDTDrCr.setPreferredSize(new java.awt.Dimension(173, 30));
        txtDTDrCr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTDrCrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTDrCrFocusLost(evt);
            }
        });
        txtDTDrCr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTDrCrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(txtDTSubSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTOutsider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTGLAcct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTGLAcctDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTDrCr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDTSubSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDTOutsider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDTReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDTRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDTGLAcct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDTGLAcctDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDTDrCr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1)))
                        .addGap(4, 4, 4))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jXStatusBar1Layout = new javax.swing.GroupLayout(jXStatusBar1);
        jXStatusBar1.setLayout(jXStatusBar1Layout);
        jXStatusBar1Layout.setHorizontalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusHDID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusGLCode, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusSubsection, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusOutsider, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusReason, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(588, 588, 588)
                .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXStatusBar1Layout.setVerticalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar1Layout.createSequentialGroup()
                .addGroup(jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusGLCode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusSubsection, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusOutsider, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusReason, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusHDID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    new FindAccount5Data(this, true, 3).setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    this.dispose();
    CommonJurnalForm.ItemRefresh.doClick();
}//GEN-LAST:event_btnExitActionPerformed

private void Table2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseReleased

}//GEN-LAST:event_Table2MouseReleased

private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
    saveHeader();
    setComponentStatus(true);
    txtSubSection.grabFocus();
}//GEN-LAST:event_btnNewActionPerformed

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    saveHeader();
    if (GeneralUserLogin.data.isB16()) {
        if (glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.PURCHASES) {
            if (TB_pur.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "16B  අමුණා නොමැත.", "", JOptionPane.ERROR_MESSAGE);
                jTabbedPane1.setSelectedComponent(jPanel5);
            } else {
                gotoNewTransaction();
            }
        } else {
            gotoNewTransaction();
        }
    } else {
        gotoNewTransaction();
    }
    CommonJurnalForm.ItemRefresh.doClick();
}//GEN-LAST:event_btnSaveActionPerformed

private void txtOutSiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutSiderActionPerformed
    jButton12.doClick();
}//GEN-LAST:event_txtOutSiderActionPerformed

private void txtCheckNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCheckNoKeyReleased

}//GEN-LAST:event_txtCheckNoKeyReleased

private void txtMinAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinAmountKeyReleased

}//GEN-LAST:event_txtMinAmountKeyReleased

private void txtMaxAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaxAmountKeyReleased

}//GEN-LAST:event_txtMaxAmountKeyReleased

private void txtSubSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubSectionActionPerformed
    jButton3.doClick();
}//GEN-LAST:event_txtSubSectionActionPerformed

private void txtSubSectionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSectionKeyReleased

}//GEN-LAST:event_txtSubSectionKeyReleased

private void txtBankCrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBankCrrActionPerformed
    jButton9.doClick();
}//GEN-LAST:event_txtBankCrrActionPerformed

private void txtBankCrrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBankCrrKeyReleased

}//GEN-LAST:event_txtBankCrrKeyReleased

private void btn16BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16BAddActionPerformed
    if (glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.CREDITORSPAYMENTS) {
        saveHeader();
        new FindSectionOrOutsider(CommonDRNote.this, true, TableHeaders.B16FIND_TABLE_MODEL, TableHeaders.B16FIND_TABLE_MODEL, 38, "16B අංකය සෙවීම", new int[]{2, txtOutSider.getId()}, hdID).setVisible(true);
    }
}//GEN-LAST:event_btn16BAddActionPerformed

private void btn16BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16BDeleteActionPerformed
    if (TB_pur.getSelectedRow() > -1) {
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(String.valueOf(TB_pur.getValueAt(TB_pur.getSelectedRow(), 0)));
            inputs.add(String.valueOf(TB_pur.getValueAt(TB_pur.getSelectedRow(), 1)));
            ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Delete_F16B_Payments", 0);
            JOptionPane.showMessageDialog(this, "ඉවත් කරන ලදී", "ඉවත් කරන ලදී", JOptionPane.PLAIN_MESSAGE);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    CommonDRNoteResource.showDT(hdID);
}//GEN-LAST:event_btn16BDeleteActionPerformed

private void btn16BNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16BNewActionPerformed
    saveHeader();
    if (glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.PURCHASES) {
        new AB16Buyings("01-01").setVisible(true);
    } else if (glcode == GLCodes.ADVANCEPURCHASES) {
        new AB16Buyings("01-03").setVisible(true);
    }
}//GEN-LAST:event_btn16BNewActionPerformed

private void TB_purMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_purMouseReleased

}//GEN-LAST:event_TB_purMouseReleased

private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
    if (Table2.getSelectedRow() > -1) {
        int selectedRow = Table2.getSelectedRow();

        txtDTSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 9))));
        txtDTSubSection.setText(String.valueOf(Table2.getValueAt(selectedRow, 0)));
        txtDTOutsider.setText(String.valueOf(Table2.getValueAt(selectedRow, 1)));
        txtDTOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 10))));
        txtDTReason.setText(String.valueOf(Table2.getValueAt(selectedRow, 2)));
        txtDTReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 11))));
        txtDTRemarks.setText(String.valueOf(Table2.getValueAt(selectedRow, 3)));
        txtDTRemarks.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 13))));
        txtDTGLAcct.setText(String.valueOf(Table2.getValueAt(selectedRow, 4)));
        txtDTGLAcct.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 12))));
        txtDTGLAcctDescription.setText(String.valueOf(Table2.getValueAt(selectedRow, 5)));
        switch (glcode) {
            case GLCodes.PURCHASES:
            case GLCodes.CREDITORSPAYMENTS:
            case GLCodes.OTHERPAYMENTS:
            case GLCodes.ADVANCEPURCHASES:
            case GLCodes.OTHERADVANCES:
            case GLCodes.LOANPAYMENTS:
            case GLCodes.DEPOSITPAYMENTS:
            case GLCodes.CRNOTE:
            case GLCodes.COMMONADMINCR:
            case GLCodes.COMMONEXPENCEMATCHCR:
            case GLCodes.C9SALE:
            case GLCodes.BANKCRNOTE:
            case GLCodes.COMMONCRNOTE:
                txtDTDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 6)));
                break;
            case GLCodes.DRNOTE:
            case GLCodes.BANKDRNOTE:
            case GLCodes.COMMONADMINDR:
            case GLCodes.COMMONEXPENCEMATCHDR:
            case GLCodes.COMMONDRNOTE:
                txtDTDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 7)));
                break;
        }
        dtid = Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 8)));

    }
}//GEN-LAST:event_Table2MouseClicked

private void txtCheckNo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCheckNo1KeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCheckNo1KeyReleased

private void txtCheckNo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCheckNo1MouseEntered
// TODO add your handling code here:
}//GEN-LAST:event_txtCheckNo1MouseEntered

private void txtCheckNo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCheckNo1MouseClicked
    txtCheckNo1.selectAll();
}//GEN-LAST:event_txtCheckNo1MouseClicked

private void txtMinAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinAmountFocusLost

}//GEN-LAST:event_txtMinAmountFocusLost

private void txtMaxAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaxAmountFocusLost

}//GEN-LAST:event_txtMaxAmountFocusLost

private void Table2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyReleased

}//GEN-LAST:event_Table2KeyReleased

private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
    if (GeneralUserLogin.data.isB16()) {
        if (glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.PURCHASES) {
            if (TB_pur.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "16B  අමුණා නොමැත.", "", JOptionPane.ERROR_MESSAGE);
                jTabbedPane1.setSelectedComponent(jPanel5);
            } else {
                gotoConfirmation();
            }
        } else {
            gotoConfirmation();
        }
    } else {
        gotoConfirmation();
    }
    CommonJurnalForm.ItemRefresh.doClick();
}//GEN-LAST:event_btnConfirmActionPerformed

private void txtOutSiderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOutSiderKeyReleased

}//GEN-LAST:event_txtOutSiderKeyReleased

private void txtDebitAccountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDebitAccountKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtDebitAccountKeyReleased

    private void Table1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table1ComponentMoved
        jScrollPane2.getViewport().setViewPosition(jScrollPane1.getViewport().getViewPosition());
    }//GEN-LAST:event_Table1ComponentMoved

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (glcode == GLCodes.C9SALE) {
            new FindSectionOrOutsider(this, true, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 49, "අනු අංශය").setVisible(true);
        } else {
            new FindSectionOrOutsider(this, true, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 16, "අනු අංශය").setVisible(true);
        }
//        FindSectionOrOutsider.txtSearchData.grabFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        new FindSectionOrOutsider(this, true, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 17, "පාර්ශවකරු").setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtReasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReasonActionPerformed
        jButton13.doClick();
    }//GEN-LAST:event_txtReasonActionPerformed

    private void Table1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyReleased

    }//GEN-LAST:event_Table1KeyReleased

    private void txtDebitAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDebitAccountActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_txtDebitAccountActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new FindAccount5Data(this, true, 4, 2).setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        switch (this.glcode) {
            case GLCodes.DRNOTE:
            case GLCodes.CRNOTE:
            case GLCodes.C9SALE:
            case GLCodes.COMMONADMINDR:
            case GLCodes.COMMONADMINCR:
            case GLCodes.COMMONEXPENCEMATCHDR:
            case GLCodes.COMMONEXPENCEMATCHCR:
            case GLCodes.COMMONDRNOTE:
            case GLCodes.COMMONCRNOTE:
                new FindSectionOrOutsider(this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 21, "කාරණය", "OTH").setVisible(true);
                break;
            case GLCodes.PURCHASES:
            case GLCodes.CREDITORSPAYMENTS:
                new FindSectionOrOutsider(this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 43, "කාරණය", "F5").setVisible(true);
                break;
            default:
                new FindSectionOrOutsider(this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 44, "කාරණය", "F5").setVisible(true);
                break;
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txtDTSubSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTSubSectionActionPerformed
        if (glcode == GLCodes.C9SALE) {
            new FindSectionOrOutsider(CommonDRNote.this, true, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 18, "අනු අංශය").setVisible(true);
        } else {
            new FindSectionOrOutsider(CommonDRNote.this, true, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 50, "අනු අංශය").setVisible(true);
        }
    }//GEN-LAST:event_txtDTSubSectionActionPerformed

    private void txtDTOutsiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTOutsiderActionPerformed
        new FindSectionOrOutsider(CommonDRNote.this, true, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 19, "පාර්ශවකරු").setVisible(true);
    }//GEN-LAST:event_txtDTOutsiderActionPerformed

    private void txtDTReasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTReasonActionPerformed
        if (glcode == GLCodes.DRNOTE || glcode == GLCodes.CRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONADMINDR || glcode == GLCodes.COMMONCRNOTE || glcode == GLCodes.COMMONDRNOTE || glcode == GLCodes.COMMONEXPENCEMATCHDR || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
            new FindSectionOrOutsider(CommonDRNote.this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 20, "කාරණය", "OTH").setVisible(true);
        } else {
            new FindSectionOrOutsider(CommonDRNote.this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 20, "කාරණය", "F5").setVisible(true);
        }
    }//GEN-LAST:event_txtDTReasonActionPerformed

    private void txtDTRemarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTRemarksActionPerformed
        txtDTGLAcct.grabFocus();
    }//GEN-LAST:event_txtDTRemarksActionPerformed

    private void txtDTGLAcctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTGLAcctActionPerformed
        new FindAccount5Data(this, true, 2).setVisible(true);
    }//GEN-LAST:event_txtDTGLAcctActionPerformed

    private void txtDTDrCrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTDrCrActionPerformed
        if (btnSave.isEnabled()) {
//            if (glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.PURCHASES) {
//             if (TB_pur.getRowCount() > 0) {
            if (GeneralUserLogin.data.isB16()) {
                if (glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.PURCHASES) {
                    if (TB_pur.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "16B  අමුණා නොමැත.", "", JOptionPane.ERROR_MESSAGE);
                        jTabbedPane1.setSelectedComponent(jPanel5);
                    } else {
                        txtDTSubSection.grabFocus();
                        saveHeader();
                        saveDetail();
                        viewDT(hdID);

                        initRowData();
                    }
                } else {
                    txtDTSubSection.grabFocus();
                    saveHeader();
                    saveDetail();
                    viewDT(hdID);

                    initRowData();
                }
//            } else {
//                JOptionPane.showMessageDialog(this, "16B  අමුණා නොමැත.","",JOptionPane.ERROR_MESSAGE);
//                jTabbedPane1.setSelectedComponent(jPanel5);
//            }   
//            }
            } else {
                txtDTSubSection.grabFocus();
                saveHeader();
                saveDetail();
                viewDT(hdID);

                initRowData();
            }
        }

    }//GEN-LAST:event_txtDTDrCrActionPerformed

    private void txtDTGLAcctDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTGLAcctDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDTGLAcctDescriptionActionPerformed

    private void txtSubSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubSectionFocusGained
        txtSubSection.selectAll();
        txtSubSection.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtSubSectionFocusGained

    private void txtSubSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubSectionFocusLost
        txtSubSection.select(0, 0);
        txtSubSection.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtSubSectionFocusLost

    private void txtOutSiderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOutSiderFocusGained
        txtOutSider.selectAll();
        txtOutSider.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtOutSiderFocusGained

    private void txtOutSiderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOutSiderFocusLost
        txtOutSider.select(0, 0);
        txtOutSider.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtOutSiderFocusLost

    private void txtReasonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonFocusGained
        txtReason.selectAll();
        txtReason.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtReasonFocusGained

    private void txtReasonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonFocusLost
        txtReason.select(0, 0);
        txtReason.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtReasonFocusLost

    private void txtDebitAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDebitAccountFocusGained
        txtDebitAccount.selectAll();
        txtDebitAccount.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDebitAccountFocusGained

    private void txtDebitAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDebitAccountFocusLost
        txtDebitAccount.select(0, 0);
        txtDebitAccount.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDebitAccountFocusLost

    private void txtBankCrrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBankCrrFocusGained
        txtBankCrr.selectAll();
        txtBankCrr.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtBankCrrFocusGained

    private void txtBankCrrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBankCrrFocusLost
        txtBankCrr.select(0, 0);
        txtBankCrr.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtBankCrrFocusLost

    private void txtCheckNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCheckNoFocusGained
        txtCheckNo.selectAll();
        txtCheckNo.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtCheckNoFocusGained

    private void txtCheckNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCheckNoFocusLost
        txtCheckNo.select(0, 0);
        txtCheckNo.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtCheckNoFocusLost

    private void txtCheckNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckNoActionPerformed
        txtMinAmount.grabFocus();
    }//GEN-LAST:event_txtCheckNoActionPerformed

    private void txtMinAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinAmountActionPerformed
        txtMaxAmount.grabFocus();
    }//GEN-LAST:event_txtMinAmountActionPerformed

    private void txtMaxAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxAmountActionPerformed
        txtCheckNo1.grabFocus();
    }//GEN-LAST:event_txtMaxAmountActionPerformed

    private void txtCheckNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckNo1ActionPerformed
        txtDTSubSection.grabFocus();
    }//GEN-LAST:event_txtCheckNo1ActionPerformed

    private void txtDTSubSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTSubSectionFocusGained
        txtDTSubSection.selectAll();
        txtDTSubSection.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTSubSectionFocusGained

    private void txtDTSubSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTSubSectionFocusLost
        txtDTSubSection.select(0, 0);
        txtDTSubSection.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTSubSectionFocusLost

    private void txtDTOutsiderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTOutsiderFocusGained
        txtDTOutsider.selectAll();
        txtDTOutsider.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTOutsiderFocusGained

    private void txtDTOutsiderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTOutsiderFocusLost
        txtDTOutsider.select(0, 0);
        txtDTOutsider.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTOutsiderFocusLost

    private void txtDTReasonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTReasonFocusGained
        txtDTReason.selectAll();
        txtDTReason.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTReasonFocusGained

    private void txtDTReasonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTReasonFocusLost
        txtDTReason.select(0, 0);
        txtDTReason.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTReasonFocusLost

    private void txtDTRemarksFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTRemarksFocusGained
        txtDTRemarks.selectAll();
        txtDTRemarks.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTRemarksFocusGained

    private void txtDTRemarksFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTRemarksFocusLost
        txtDTRemarks.select(0, 0);
        txtDTRemarks.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTRemarksFocusLost

    private void txtDTGLAcctFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTGLAcctFocusGained
        txtDTGLAcct.selectAll();
        txtDTGLAcct.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTGLAcctFocusGained

    private void txtDTGLAcctFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTGLAcctFocusLost
        txtDTGLAcct.select(0, 0);
        txtDTGLAcct.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTGLAcctFocusLost

    private void txtDTGLAcctDescriptionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTGLAcctDescriptionFocusGained
        txtDTGLAcctDescription.selectAll();
        txtDTGLAcctDescription.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTGLAcctDescriptionFocusGained

    private void txtDTGLAcctDescriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTGLAcctDescriptionFocusLost
        txtDTGLAcctDescription.select(0, 0);
        txtDTGLAcctDescription.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTGLAcctDescriptionFocusLost

    private void txtDTDrCrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTDrCrFocusGained
        txtDTDrCr.selectAll();
        txtDTDrCr.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtDTDrCrFocusGained

    private void txtDTDrCrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTDrCrFocusLost
        txtDTDrCr.select(0, 0);
        txtDTDrCr.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTDrCrFocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        CommonJurnalForm.ItemRefresh.doClick();
    }//GEN-LAST:event_formWindowClosing

    private void txtDTSubSectionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDTSubSectionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE && txtDTSubSection.getText().trim().equals("")) {
            if (glcode == GLCodes.PURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.OTHERPAYMENTS || glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.OTHERADVANCES || glcode == GLCodes.LOANPAYMENTS || glcode == GLCodes.DEPOSITPAYMENTS || glcode == GLCodes.DRNOTE || glcode == GLCodes.CRNOTE || glcode == GLCodes.BANKCRNOTE || glcode == GLCodes.BANKDRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONADMINDR || glcode == GLCodes.COMMONEXPENCEMATCHDR || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
                txtDTSubSection.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 0)));
                txtDTSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 9))));
            }
            txtDTOutsider.grabFocus();
        }
    }//GEN-LAST:event_txtDTSubSectionKeyReleased

    private void txtDTOutsiderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDTOutsiderKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE && txtDTOutsider.getText().trim().equals("")) {
            if (glcode == GLCodes.PURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.OTHERPAYMENTS || glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.OTHERADVANCES || glcode == GLCodes.LOANPAYMENTS || glcode == GLCodes.DEPOSITPAYMENTS || glcode == GLCodes.DRNOTE || glcode == GLCodes.CRNOTE || glcode == GLCodes.BANKCRNOTE || glcode == GLCodes.BANKDRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONADMINDR || glcode == GLCodes.COMMONDRNOTE || glcode == GLCodes.COMMONCRNOTE || glcode == GLCodes.COMMONEXPENCEMATCHDR || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
                txtDTOutsider.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 1)));
                txtDTOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 10))));
            }
            txtDTReason.grabFocus();
        }
    }//GEN-LAST:event_txtDTOutsiderKeyReleased

    private void txtDTReasonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDTReasonKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE && txtDTReason.getText().trim().equals("")) {
            if (glcode == GLCodes.PURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.OTHERPAYMENTS || glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.OTHERADVANCES || glcode == GLCodes.LOANPAYMENTS || glcode == GLCodes.DEPOSITPAYMENTS || glcode == GLCodes.DRNOTE || glcode == GLCodes.CRNOTE || glcode == GLCodes.BANKCRNOTE || glcode == GLCodes.BANKDRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONADMINDR || glcode == GLCodes.COMMONDRNOTE || glcode == GLCodes.COMMONCRNOTE || glcode == GLCodes.COMMONEXPENCEMATCHDR || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
                txtDTReason.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 2)));
                txtDTReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 11))));
            }
            txtDTRemarks.grabFocus();
        }
    }//GEN-LAST:event_txtDTReasonKeyReleased

    private void txtDTGLAcctKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDTGLAcctKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE && txtDTGLAcct.getText().trim().equals("")) {
            if (glcode == GLCodes.PURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.OTHERPAYMENTS || glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.OTHERADVANCES || glcode == GLCodes.LOANPAYMENTS || glcode == GLCodes.DEPOSITPAYMENTS || glcode == GLCodes.DRNOTE || glcode == GLCodes.CRNOTE || glcode == GLCodes.BANKCRNOTE || glcode == GLCodes.BANKDRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONADMINDR || glcode == GLCodes.COMMONDRNOTE || glcode == GLCodes.COMMONCRNOTE || glcode == GLCodes.COMMONEXPENCEMATCHDR || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
                txtDTGLAcct.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 4)));
                txtDTGLAcctDescription.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 5)));
                txtDTGLAcct.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 12))));
            }
            txtDTDrCr.grabFocus();
        }
    }//GEN-LAST:event_txtDTGLAcctKeyReleased

    private void txtDTRemarksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDTRemarksKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE && txtDTRemarks.getText().trim().equals("")) {
            if (glcode == GLCodes.PURCHASES || glcode == GLCodes.CREDITORSPAYMENTS || glcode == GLCodes.OTHERPAYMENTS || glcode == GLCodes.ADVANCEPURCHASES || glcode == GLCodes.OTHERADVANCES || glcode == GLCodes.LOANPAYMENTS || glcode == GLCodes.DEPOSITPAYMENTS || glcode == GLCodes.DRNOTE || glcode == GLCodes.CRNOTE || glcode == GLCodes.BANKCRNOTE || glcode == GLCodes.BANKDRNOTE || glcode == GLCodes.C9SALE || glcode == GLCodes.COMMONADMINCR || glcode == GLCodes.COMMONADMINDR || glcode == GLCodes.COMMONDRNOTE || glcode == GLCodes.COMMONCRNOTE || glcode == GLCodes.COMMONEXPENCEMATCHDR || glcode == GLCodes.COMMONEXPENCEMATCHCR) {
                txtDTRemarks.setText(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 3)));
                txtDTRemarks.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getRowCount() - 1, 13))));
            }
            txtDTGLAcct.grabFocus();
        }
    }//GEN-LAST:event_txtDTRemarksKeyReleased

    private void txtRemarksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRemarksKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            new UnicodeKeyBoard(this, true, UnicodeKeyBoard.emptyAmaliField, txtRemarks, false).setVisible(true);
        }
    }//GEN-LAST:event_txtRemarksKeyReleased

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
            java.util.logging.Logger.getLogger(CommonDRNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CommonDRNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CommonDRNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CommonDRNote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                CommonDRNote dialog = new CommonDRNote(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    static javax.swing.JLabel B16Total;
    public static org.jdesktop.swingx.JXTable TB_pur;
    public static org.jdesktop.swingx.JXTable Table1;
    private static org.jdesktop.swingx.JXTable Table2;
    public static javax.swing.JButton btn16BAdd;
    private javax.swing.JButton btn16BDelete;
    private javax.swing.JButton btn16BNew;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private com.toedter.calendar.JDateChooser jdtCheckDate;
    private javax.swing.JLabel lableName;
    private javax.swing.JLabel statusGLCode;
    private javax.swing.JLabel statusHDID;
    public static javax.swing.JLabel statusOutsider;
    public static javax.swing.JLabel statusReason;
    public static javax.swing.JLabel statusSubsection;
    private javax.swing.JLabel statusUserName;
    public static org.nbs.components.HiddenIDTextField txtBankCrr;
    private javax.swing.JTextField txtBillNo;
    private javax.swing.JTextField txtCheckNo;
    private javax.swing.JTextField txtCheckNo1;
    public static javax.swing.JFormattedTextField txtDTDrCr;
    public static org.nbs.components.HiddenIDTextField txtDTGLAcct;
    public static org.nbs.components.HiddenIDTextField txtDTGLAcctDescription;
    public static org.nbs.components.HiddenIDTextField txtDTOutsider;
    public static org.nbs.components.HiddenIDTextField txtDTReason;
    public static org.nbs.components.HiddenIDTextField txtDTRemarks;
    public static org.nbs.components.HiddenIDTextField txtDTSubSection;
    public static org.nbs.components.HiddenIDTextField txtDebitAccount;
    private javax.swing.JTextField txtMaxAmount;
    private javax.swing.JTextField txtMinAmount;
    public static org.nbs.components.HiddenIDTextField txtOutSider;
    public static org.nbs.components.HiddenIDTextField txtReason;
    private javax.swing.JTextArea txtRemarks;
    public static org.nbs.components.HiddenIDTextField txtSubSection;
    private static org.nbs.components.SelectAtFocusFormattedTextField txtTotal;
    private com.toedter.calendar.JDateChooser txtTrnDate;
    // End of variables declaration//GEN-END:variables

    private void saveHeader() {
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(hdID);
            inputs.add(FormatConstants.dateFormat.format(txtTrnDate.getDate()));
            inputs.add(txtBillNo.getText());
            inputs.add(txtCheckNo1.getText());
            inputs.add(txtSubSection.getId());
            inputs.add(glcode);
            inputs.add(txtOutSider.getId());
            inputs.add(txtReason.getId());

            switch (glcode) {
                case GLCodes.PURCHASES:
                case GLCodes.CREDITORSPAYMENTS:
                case GLCodes.ADVANCEPURCHASES:
                case GLCodes.OTHERPAYMENTS:
                case GLCodes.OTHERADVANCES:
                    inputs.add(txtBankCrr.getId());
                    break;
                case GLCodes.BANKDRNOTE:
                case GLCodes.BANKCRNOTE:
                case GLCodes.DRNOTE:
                case GLCodes.CRNOTE:
                case GLCodes.COMMONADMINCR:
                case GLCodes.COMMONADMINDR:
                case GLCodes.COMMONEXPENCEMATCHDR:
                case GLCodes.COMMONEXPENCEMATCHCR:
                case GLCodes.C9SALE:
                case GLCodes.COMMONCRNOTE:
                case GLCodes.COMMONDRNOTE:
                    inputs.add(txtDebitAccount.getId());
                    break;
            }
            inputs.add(1);
            inputs.add(FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue());
            switch (glcode) {
                case GLCodes.PURCHASES:
                case GLCodes.CREDITORSPAYMENTS:
                case GLCodes.OTHERPAYMENTS:
                case GLCodes.ADVANCEPURCHASES:
                case GLCodes.OTHERADVANCES:
                case GLCodes.CRNOTE:
                case GLCodes.COMMONADMINCR:
                case GLCodes.COMMONEXPENCEMATCHCR:
                case GLCodes.BANKCRNOTE:
                case GLCodes.C9SALE:
                case GLCodes.COMMONCRNOTE:
                    inputs.add(0);
                    inputs.add(FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue());
                    break;
                case GLCodes.DRNOTE:
                case GLCodes.COMMONADMINDR:
                case GLCodes.COMMONEXPENCEMATCHDR:
                case GLCodes.BANKDRNOTE:
                case GLCodes.COMMONDRNOTE:
                    inputs.add(FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue());
                    inputs.add(0);

                    break;
            }
            inputs.add(txtRemarks.getText());
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add(GeneralUserLogin.data.getSystemDate());
            inputs.add(txtCheckNo.getText());
            inputs.add(FormatConstants.dateFormat.format(jdtCheckDate.getDate()));
            inputs.add(FormatConstants.numberFormat1.parse(txtMinAmount.getText()).doubleValue());
            inputs.add(FormatConstants.numberFormat1.parse(txtMaxAmount.getText()).doubleValue());
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add(GeneralUserLogin.data.getSystemDate());
            inputs.add("@recid");
            inputs.add("@billNo");

            List<Object> outputs = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_Oth_TrnHD", 2);
            txtBillNo.setText(String.valueOf(outputs.get(1)));
            hdID = String.valueOf(outputs.get(0));
            statusHDID.setText(hdID);

        } catch (RemoteException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int dtid = 0;

    private void saveDetail() {
//        for (int i = 0; i < Table2.getRowCount(); i++) {
        System.out.println("nhgbj");
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(dtid);
            inputs.add(hdID);
            inputs.add(txtDTSubSection.getId());
            inputs.add(txtDTOutsider.getId());
            inputs.add(glcode);
            inputs.add(txtDTReason.getId());
            inputs.add(txtDTReason.getText());
            inputs.add(txtDTRemarks.getId());
            inputs.add(txtDTRemarks.getText());
            inputs.add(txtDTGLAcct.getId());
            inputs.add(txtDTGLAcctDescription.getText());
            inputs.add(FormatConstants.numberFormat1.parse(txtDTDrCr.getText()).doubleValue());
            switch (glcode) {
                case GLCodes.PURCHASES:
                case GLCodes.CREDITORSPAYMENTS:
                case GLCodes.OTHERPAYMENTS:
                case GLCodes.ADVANCEPURCHASES:
                case GLCodes.OTHERADVANCES:
                case GLCodes.LOANPAYMENTS:
                case GLCodes.DEPOSITPAYMENTS:
                case GLCodes.CRNOTE:
                case GLCodes.COMMONADMINCR:
                case GLCodes.COMMONEXPENCEMATCHCR:
                case GLCodes.BANKCRNOTE:
                case GLCodes.C9SALE:
                case GLCodes.COMMONCRNOTE:
                    inputs.add(FormatConstants.numberFormat1.parse(String.valueOf(txtDTDrCr.getText())).doubleValue());
                    inputs.add(0);
                    break;
                case GLCodes.DRNOTE:
                case GLCodes.COMMONADMINDR:
                case GLCodes.COMMONEXPENCEMATCHDR:
                case GLCodes.BANKDRNOTE:
                case GLCodes.COMMONDRNOTE:
                    inputs.add(0);
                    inputs.add(FormatConstants.numberFormat1.parse(String.valueOf(txtDTDrCr.getText())).doubleValue());
                    break;
            }
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add("@recID");
            List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_Oth_TrnDT", 1);
            dtid = 0;
        } catch (RemoteException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        }
//        }
    }

    static void viewDT(String hddID) {
        try {
            dtm2.setRowCount(0);
            List<Object> inputs = new ArrayList<>();
            inputs.add(2);
            inputs.add(hddID);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewDRNoteEdit", 14);

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
        getTotal(glForTotal);
    }

    static void getTotal(int glcode) {
        double total = 0;
        switch (glcode) {
            case GLCodes.DRNOTE:
            case GLCodes.COMMONADMINDR:
            case GLCodes.COMMONEXPENCEMATCHDR:
            case GLCodes.BANKDRNOTE:
            case GLCodes.COMMONDRNOTE:
                for (int i = 0; i < Table2.getRowCount(); i++) {
                    try {
                        total += FormatConstants.numberFormat1.parse(String.valueOf(Table2.getValueAt(i, 7))).doubleValue();
                    } catch (ParseException ex) {
                        Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case GLCodes.CRNOTE:
            case GLCodes.COMMONADMINCR:
            case GLCodes.COMMONEXPENCEMATCHCR:
            case GLCodes.BANKCRNOTE:
            case GLCodes.OTHERPAYMENTS:
            case GLCodes.PURCHASES:
            case GLCodes.CREDITORSPAYMENTS:
            case GLCodes.ADVANCEPURCHASES:
            case GLCodes.OTHERADVANCES:
            case GLCodes.LOANPAYMENTS:
            case GLCodes.DEPOSITPAYMENTS:
            case GLCodes.C9SALE:
            case GLCodes.COMMONCRNOTE:
                for (int i = 0; i < Table2.getRowCount(); i++) {
                    try {
                        total += FormatConstants.numberFormat1.parse(String.valueOf(Table2.getValueAt(i, 6))).doubleValue();
                    } catch (ParseException ex) {
                        Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                break;
        }
        txtTotal.setText(FormatConstants.numberFormat1.format(total));
    }

    private void initRowData() {
        JTextField fields[] = null;
        if (glcode == GLCodes.CREDITORSPAYMENTS) {
            fields = new JTextField[]{txtDTDrCr, txtDTGLAcct, txtDTGLAcctDescription, txtDTRemarks};
        } else {
            fields = new JTextField[]{txtDTDrCr, txtDTGLAcct, txtDTGLAcctDescription, txtDTOutsider, txtDTReason, txtDTSubSection, txtDTRemarks};
        }
        for (JTextField field : fields) {
            field.setText("");
            if (field instanceof HiddenIDTextField) {
                ((HiddenIDTextField) field).setId(0);
            }
        }
        dtid = 0;
    }

    private void defineColumnWidths(int glcode) {
        switch (glcode) {
            case GLCodes.ADVANCEPURCHASES:
            case GLCodes.CREDITORSPAYMENTS:
            case GLCodes.CRNOTE:
            case GLCodes.COMMONADMINCR:
            case GLCodes.COMMONEXPENCEMATCHCR:
            case GLCodes.DEPOSITPAYMENTS:
            case GLCodes.LOANPAYMENTS:
            case GLCodes.OTHERADVANCES:
            case GLCodes.OTHERPAYMENTS:
            case GLCodes.PURCHASES:
            case GLCodes.BANKCRNOTE:
            case GLCodes.C9SALE:
            case GLCodes.COMMONCRNOTE:
                Table1.getColumn(7).setMaxWidth(0);
                Table1.getColumn(7).setMinWidth(0);
                Table1.getColumn(7).setPreferredWidth(0);
                Table2.getColumn(7).setMaxWidth(0);
                Table2.getColumn(7).setMinWidth(0);
                Table2.getColumn(7).setPreferredWidth(0);
                break;
            case GLCodes.DRNOTE:
            case GLCodes.COMMONADMINDR:
            case GLCodes.COMMONEXPENCEMATCHDR:
            case GLCodes.BANKDRNOTE:
            case GLCodes.COMMONDRNOTE:
                Table1.getColumn(6).setMaxWidth(0);
                Table1.getColumn(6).setMinWidth(0);
                Table1.getColumn(6).setPreferredWidth(0);
                Table2.getColumn(6).setMaxWidth(0);
                Table2.getColumn(6).setMinWidth(0);
                Table2.getColumn(6).setPreferredWidth(0);

                break;
        }
    }

    private void gotoNewTransaction() {
        if (glcode != GLCodes.CRNOTE && glcode != GLCodes.DRNOTE && glcode != GLCodes.OTHERPAYMENTS && glcode != GLCodes.PURCHASES && glcode != GLCodes.CREDITORSPAYMENTS && glcode != GLCodes.ADVANCEPURCHASES && glcode != GLCodes.OTHERADVANCES && glcode != GLCodes.LOANPAYMENTS && glcode != GLCodes.DEPOSITPAYMENTS && glcode != GLCodes.C9SALE && glcode != GLCodes.BANKDRNOTE && glcode != GLCodes.BANKCRNOTE && glcode != GLCodes.COMMONADMINCR && glcode != GLCodes.COMMONADMINDR && glcode != GLCodes.COMMONCRNOTE && glcode != GLCodes.COMMONDRNOTE && glcode != GLCodes.COMMONEXPENCEMATCHDR && glcode != GLCodes.COMMONEXPENCEMATCHCR) {
            if (validateTotal()) {
                int i = JOptionPane.showConfirmDialog(this, "නව ගණුදෙනුවක් සිදු කරනවාද?", "නව ගණුදෙනුවක් සිදු කරනවාද?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (i == JOptionPane.YES_OPTION) {
                    this.dispose();
                    new CommonDRNote(null, true, this.glcode, lableName.getText()).setVisible(true);
                    CommonJurnalForm.ItemRefresh.doClick();
                } else {
                    this.dispose();
                    CommonJurnalForm.ItemRefresh.doClick();
                }
            } else {
                JOptionPane.showMessageDialog(this, "එකතුවන් සමාන නොවේ. නැවත පිරික්සන්න.", "නැවත පිරික්සන්න.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            int i = JOptionPane.showConfirmDialog(this, "නව ගණුදෙනුවක් සිදු කරනවාද?", "නව ගණුදෙනුවක් සිදු කරනවාද?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (i == JOptionPane.YES_OPTION) {
                this.dispose();
                new CommonDRNote(null, true, this.glcode, lableName.getText()).setVisible(true);
                CommonJurnalForm.ItemRefresh.doClick();
            } else {
                this.dispose();
                CommonJurnalForm.ItemRefresh.doClick();
            }
        }
    }

    private void gotoConfirmation() {
        int statusToSave = status;
        int confirmation = 0;
        confirmation = JOptionPane.showConfirmDialog(this, "ඔබ මෙය " + btnConfirm.getText().split(" ")[0] + " කරනවාද? .", "ඔබ මෙය " + btnConfirm.getText().split(" ")[0] + " කරනවාද?", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(hdID);
                inputs.add(GeneralUserLogin.data.getUsername());
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Confirm_OthTrns", 0);
            } catch (SQLException ex) {
                Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.dispose();
        CommonJurnalForm.ItemRefresh.doClick();
    }

    private boolean validateTotal() {
        try {
            double t1 = FormatConstants.numberFormat1.parse(txtTotal.getText()).doubleValue();
            double t2 = FormatConstants.numberFormat1.parse(B16Total.getText()).doubleValue();
            if (t1 != t2) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CommonDRNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void setComponentStatus(boolean enabled) {
        Component components[] = {txtBankCrr, txtBillNo, txtCheckNo, txtCheckNo1, txtDTDrCr, txtDTGLAcct, txtDTGLAcctDescription, txtDTOutsider, txtDTReason, txtDTRemarks, txtDTSubSection, txtDebitAccount, txtMaxAmount, txtMinAmount, txtOutSider, txtReason, txtRemarks, txtSubSection, txtTotal, txtTrnDate, jButton3, jButton12, jButton13, jButton1, jdtCheckDate, jButton9, btn16BAdd, btn16BDelete, btn16BNew};
        for (Component component : components) {
            component.setEnabled(enabled);
        }
    }

}

class NumberCellEditor extends DefaultCellEditor {

    public NumberCellEditor() {
        super(new SelectAtFocusFormattedTextField());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        SelectAtFocusFormattedTextField editor = (SelectAtFocusFormattedTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        editor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        editor.setText("0.00");
        if (value != null && !value.equals("")) {
            double val = 0;
            try {
                val = FormatConstants.numberFormat1.parse(String.valueOf(value)).doubleValue();
            } catch (ParseException ex) {
                Logger.getLogger(NumberCellEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            Number num = (Number) val;
            String text = FormatConstants.numberFormat1.format(num);
            editor.setHorizontalAlignment(SwingConstants.RIGHT);
            editor.setText(text);
        }
        return editor;
    }

}
