/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.F28.Stock;

import Sources.GLCodes;
import Sources.TableColumnFontChanger;
import Sources.TableColumnRightAlign;
import com.nbs.impl.ServerConnection;
import com.nbs.view.common.FormatConstants;
import com.nbs.view.common.GeneralUserLogin;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.search.FindSectionOrOutsider;
import com.nbs.view.manager.CommonJurnalForm;
import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmh
 */
public class C14B9HD extends javax.swing.JFrame {

    private String code;
    private DefaultTableModel dtm;
    private String status;

    /**
     * Creates new form F17
     */
    public C14B9HD() {
        initComponents();
        setTitle("Server on " + GeneralUserLogin.data.getIP());

        initTxtFields();
        dtm = (DefaultTableModel) Table2.getModel();
        jdtDate.setDate(GeneralUserLogin.data.getSystemDate());
        txt9BNumber.grabFocus();
        statusUserName.setText(GeneralUserLogin.data.getUserCode() + "-" + GeneralUserLogin.data.getUsername());

        Table1.getTableHeader().setFont(new Font("Iskoola Pota", Font.PLAIN, 16));
        TableColumnFontChanger.setAmalee(Table2, 0);
        TableColumnRightAlign.alignMany(Table2, new int[]{1, 2});
        btnConfirm.setVisible(false);
    }

    public C14B9HD(String code, String hdID) {
        this(code);
        btnNew.setEnabled(false);
        btnSave.setEnabled(true);
        try {
            List<Object> inputs = new ArrayList<>();
            HDID = hdID;
            inputs.add(1);
            inputs.add(hdID);
            List<Object> output = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_View9B_Edit", 12);

            txtNumber.setText(String.valueOf(output.get(0)));
            jdtDate.setDate(FormatConstants.dateFormat.parse(String.valueOf(output.get(1))));
            txt9BNumber.setText(String.valueOf(output.get(2)));
            statusGLTrnID.setText(GLCodes.B9 + "");
            txtSection.setId(Integer.parseInt(String.valueOf(output.get(4))));
            txtSection.setText(String.valueOf(output.get(5)));
            txtCreditor.setId(Integer.parseInt(String.valueOf(output.get(6))));
            txtCreditor.setText(String.valueOf(output.get(7)));
            txtSoldSection.setId(Integer.parseInt(String.valueOf(output.get(8))));
            txtSoldSection.setText(String.valueOf(output.get(9)));
            btnConfirm.setText(String.valueOf(output.get(10)));
            btnConfirm.setVisible(true);
            status = String.valueOf(output.get(11));

            if (status.equals("2")) {
                btnConfirm.setEnabled(false);
                btnSave.setEnabled(false);
                Table2.setEnabled(false);
                txtDTCR.setEnabled(false);
                txtDTDR.setEnabled(false);
                txtDTReason.setEnabled(false);
            }
            viewDT(hdID);
            enableTextFields();
        } catch (RemoteException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public C14B9HD(String code) {
        this();
        this.code = code;
        statusGLTrnID.setText(GLCodes.B9 + "");
    }

    public C14B9HD(String code, List dataArray) {
        this(code);
        try {
            txtNumber.setText(String.valueOf(dataArray.get(0)));
            txt9BNumber.setText(String.valueOf(dataArray.get(2)));
            jdtDate.setDate(FormatConstants.dateFormat.parse(String.valueOf(dataArray.get(1))));
            txtSection.setId(Integer.parseInt(String.valueOf(dataArray.get(4))));
            txtSection.setText(String.valueOf(dataArray.get(5)));
            txtSoldSection.setId(Integer.parseInt(String.valueOf(dataArray.get(8))));
            txtSoldSection.setText(String.valueOf(dataArray.get(9)));
            txtCreditor.setId(Integer.parseInt(String.valueOf(dataArray.get(6))));
            txtCreditor.setText(String.valueOf(dataArray.get(7)));
//            txtCash.setText(String.valueOf(dataArray.get(13)));
//            txtCredit.setText(String.valueOf(dataArray.get(14)));
//            txtCreditLoan.setText(String.valueOf(dataArray.get(15)));
//            txtSamurdhi.setText(String.valueOf(dataArray.get(16)));
//            txtPoshana.setText(String.valueOf(dataArray.get(17)));
//            txtWiyali.setText(String.valueOf(dataArray.get(18)));
//            txtTransferOut.setText(String.valueOf(dataArray.get(19)));
//            txtCreditCard.setText(String.valueOf(dataArray.get(20)));
//            txtTotal.setText(String.valueOf(dataArray.get(21)));
//            txtExpences.setText(String.valueOf(dataArray.get(23)));
//            txtOtherCashReceipts.setText(String.valueOf(dataArray.get(24)));
//            txtPurchases.setText(String.valueOf(dataArray.get(22)));

            statusCreditor.setText(txtCreditor.getId() + "");
            statusSubSection1.setText(txtSection.getId() + "");
            statusSubSection2.setText(txtSoldSection.getId() + "");
            stausTrnID.setText(txtNumber.getText());
        } catch (ParseException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public C14B9HD(String code, List dataArray, int i) {
        this(code);
        if (i == 1) {
            txtSection.setId(Integer.parseInt(String.valueOf(dataArray.get(4))));
            txtSection.setText(String.valueOf(dataArray.get(5)));
            txtCreditor.setId(Integer.parseInt(String.valueOf(dataArray.get(6))));
            txtCreditor.setText(String.valueOf(dataArray.get(7)));
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdtDate = new com.toedter.calendar.JDateChooser();
        txtNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt9BNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSection = new org.nbs.components.HiddenIDTextField();
        txtCreditor = new org.nbs.components.HiddenIDTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoldSection = new org.nbs.components.HiddenIDTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        txtDTCR = new org.nbs.components.HiddenIDTextField();
        txtDTReason = new org.nbs.components.HiddenIDTextField();
        txtDTDR = new org.nbs.components.HiddenIDTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalCR = new javax.swing.JTextField();
        txtTotalDR = new javax.swing.JTextField();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        statusGLTrnID = new javax.swing.JLabel();
        stausTrnID = new javax.swing.JLabel();
        statusSubSection1 = new javax.swing.JLabel();
        statusSubSection2 = new javax.swing.JLabel();
        statusCreditor = new javax.swing.JLabel();
        statusUserName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(883, 0));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        jPanel2.setBackground(new java.awt.Color(0, 51, 136));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("මුදල් වෙළඳාම");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(698, 698, 698))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("දිනය :");

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("අංකය :");

        jdtDate.setBackground(new java.awt.Color(255, 255, 255));
        jdtDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(151, 151, 255)));
        jdtDate.setDateFormatString("yyyy-MM-dd");

        txtNumber.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("9B අංකය :");

        txt9BNumber.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txt9BNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt9BNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt9BNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt9BNumberFocusLost(evt);
            }
        });
        txt9BNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt9BNumberActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("අංශය :");

        txtSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSection.setEnabled(false);
        txtSection.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSectionActionPerformed(evt);
            }
        });
        txtSection.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSectionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSectionFocusLost(evt);
            }
        });

        txtCreditor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCreditor.setEnabled(false);
        txtCreditor.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtCreditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditorActionPerformed(evt);
            }
        });
        txtCreditor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCreditorFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("ණයගැති :");

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("විකිණූ අංශය :");

        txtSoldSection.setEditable(false);
        txtSoldSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSoldSection.setEnabled(false);
        txtSoldSection.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtSoldSection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoldSectionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoldSectionKeyReleased(evt);
            }
        });
        txtSoldSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoldSectionActionPerformed(evt);
            }
        });
        txtSoldSection.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoldSectionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoldSectionFocusLost(evt);
            }
        });

        jButton5.setText("...");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("...");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("...");
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt9BNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSoldSection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCreditor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton5)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jdtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt9BNumber, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7)
                    .addComponent(txtSoldSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        btnNew.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnNew.setText("නව");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnSave.setText("සේව්");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnConfirm.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnConfirm.setText("තහවුරු කරන්න");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnNew))
                    .addComponent(btnConfirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "කාරණය", "හර", "බැර", "RecID", "ReasonID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
            Table1.getColumnModel().getColumn(0).setMinWidth(539);
            Table1.getColumnModel().getColumn(0).setPreferredWidth(539);
            Table1.getColumnModel().getColumn(0).setMaxWidth(539);
            Table1.getColumnModel().getColumn(1).setMinWidth(158);
            Table1.getColumnModel().getColumn(1).setPreferredWidth(158);
            Table1.getColumnModel().getColumn(1).setMaxWidth(158);
            Table1.getColumnModel().getColumn(2).setMinWidth(158);
            Table1.getColumnModel().getColumn(2).setPreferredWidth(158);
            Table1.getColumnModel().getColumn(2).setMaxWidth(158);
            Table1.getColumnModel().getColumn(3).setMinWidth(0);
            Table1.getColumnModel().getColumn(3).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(3).setMaxWidth(0);
            Table1.getColumnModel().getColumn(4).setMinWidth(0);
            Table1.getColumnModel().getColumn(4).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

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
            Table2.getColumnModel().getColumn(0).setMinWidth(539);
            Table2.getColumnModel().getColumn(0).setPreferredWidth(539);
            Table2.getColumnModel().getColumn(0).setMaxWidth(539);
            Table2.getColumnModel().getColumn(1).setMinWidth(158);
            Table2.getColumnModel().getColumn(1).setPreferredWidth(158);
            Table2.getColumnModel().getColumn(1).setMaxWidth(158);
            Table2.getColumnModel().getColumn(2).setMinWidth(158);
            Table2.getColumnModel().getColumn(2).setPreferredWidth(158);
            Table2.getColumnModel().getColumn(2).setMaxWidth(158);
            Table2.getColumnModel().getColumn(3).setMinWidth(0);
            Table2.getColumnModel().getColumn(3).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(3).setMaxWidth(0);
            Table2.getColumnModel().getColumn(4).setMinWidth(0);
            Table2.getColumnModel().getColumn(4).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 1));

        txtDTCR.setEditable(false);
        txtDTCR.setText("0.00");
        txtDTCR.setBackground(new java.awt.Color(191, 214, 235));
        txtDTCR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTCR.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDTCR.setHorizontalAlignment(11);
        txtDTCR.setMaximumSize(new java.awt.Dimension(158, 31));
        txtDTCR.setMinimumSize(new java.awt.Dimension(158, 31));
        txtDTCR.setPreferredSize(new java.awt.Dimension(158, 31));
        txtDTCR.setSize(new java.awt.Dimension(158, 31));
        txtDTCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTCRActionPerformed(evt);
            }
        });
        txtDTCR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTCRFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTCRFocusLost(evt);
            }
        });

        txtDTReason.setEditable(false);
        txtDTReason.setBackground(new java.awt.Color(191, 214, 235));
        txtDTReason.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTReason.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtDTReason.setMaximumSize(new java.awt.Dimension(539, 31));
        txtDTReason.setMinimumSize(new java.awt.Dimension(539, 31));
        txtDTReason.setPreferredSize(new java.awt.Dimension(539, 31));
        txtDTReason.setSize(new java.awt.Dimension(539, 31));
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

        txtDTDR.setEditable(false);
        txtDTDR.setText("0.00");
        txtDTDR.setBackground(new java.awt.Color(191, 214, 235));
        txtDTDR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDTDR.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtDTDR.setHorizontalAlignment(11);
        txtDTDR.setMaximumSize(new java.awt.Dimension(158, 31));
        txtDTDR.setMinimumSize(new java.awt.Dimension(158, 31));
        txtDTDR.setPreferredSize(new java.awt.Dimension(158, 31));
        txtDTDR.setSize(new java.awt.Dimension(158, 31));
        txtDTDR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTDRActionPerformed(evt);
            }
        });
        txtDTDR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDTDRFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDTDRFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtDTReason, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTDR, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDTCR, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDTDR, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtDTCR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDTReason, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel8.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel8.setText("එකතුව :");

        txtTotalCR.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtTotalCR.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTotalCR.setText("0.00");
        txtTotalCR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalCR.setMaximumSize(new java.awt.Dimension(158, 2147483647));
        txtTotalCR.setMinimumSize(new java.awt.Dimension(158, 27));
        txtTotalCR.setPreferredSize(new java.awt.Dimension(158, 27));

        txtTotalDR.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtTotalDR.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTotalDR.setText("0.00");
        txtTotalDR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalDR.setMaximumSize(new java.awt.Dimension(158, 2147483647));
        txtTotalDR.setMinimumSize(new java.awt.Dimension(158, 27));
        txtTotalDR.setPreferredSize(new java.awt.Dimension(158, 27));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalDR, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtTotalCR, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalCR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalDR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jXStatusBar1Layout = new javax.swing.GroupLayout(jXStatusBar1);
        jXStatusBar1.setLayout(jXStatusBar1Layout);
        jXStatusBar1Layout.setHorizontalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar1Layout.createSequentialGroup()
                .addComponent(statusGLTrnID, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stausTrnID, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusSubSection1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusSubSection2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusCreditor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXStatusBar1Layout.setVerticalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusGLTrnID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(stausTrnID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusSubSection1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusSubSection2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusCreditor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!"".equals(txtNumber.getText())) {
            HDID = txtNumber.getText();
        }
        saveHD(2);
//        int i = JOptionPane.showConfirmDialog(this, "<html><p>ගණුදෙනුව සම්පූර්ණයි. නව ගණුදෙනුවක් සිදු කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, "<html><p>ගණුදෙනුව සම්පූර්ණයි.</p></html>", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
//        if (i == JOptionPane.YES_OPTION) {
//            int i2 = JOptionPane.showConfirmDialog(this, "<html><p>අංශය වෙනස් කරනවාද?</p></html>", "වෙනස් කරනවාද?", JOptionPane.INFORMATION_MESSAGE);
//            if (i2 == JOptionPane.NO_OPTION) {
//                String section = txtSection.getText();
//                int sid = txtSection.getId();
//                String creditor = txtCreditor.getText();
//                int cid = txtCreditor.getId();
//                C14B9HD c14B9HD = new C14B9HD(code);
//
//                C14B9HD.txtSection.setId(sid);
//                C14B9HD.txtSection.setText(section);
//                C14B9HD.txtCreditor.setId(cid);
//                C14B9HD.txtCreditor.setText(creditor);
//                c14B9HD.setVisible(true);
//                this.dispose();
//                CommonJurnalForm.ItemRefresh.doClick();
//
//            } else {
//                this.dispose();
//                CommonJurnalForm.ItemRefresh.doClick();
//                new C14B9HD(code).setVisible(true);
//            }
//        } else {
        this.dispose();
        CommonJurnalForm.ItemRefresh.doClick();
//        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSectionActionPerformed
        jButton5.doClick();
    }//GEN-LAST:event_txtSectionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 5, "අනු අංශය").setVisible(true);
        txtDTCR.setEditable(true);
        txtDTDR.setEditable(true);
        txtDTReason.setEditable(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 6, "පාර්ශවකරු").setVisible(true);
        txtDTCR.setEditable(true);
        txtDTDR.setEditable(true);
        txtDTReason.setEditable(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 7, "අනු අංශය").setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtCreditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditorActionPerformed
        jButton6.doClick();
    }//GEN-LAST:event_txtCreditorActionPerformed

    private void txtSoldSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoldSectionActionPerformed
        jButton7.doClick();
    }//GEN-LAST:event_txtSoldSectionActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        btnSave.setEnabled(true);
        saveHD(1);
        txtDTCR.setEditable(true);
        txtDTDR.setEditable(true);
        txtDTReason.setEditable(true);
        btnNew.setEnabled(false);
    }//GEN-LAST:event_btnNewActionPerformed

    private void txt9BNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt9BNumberActionPerformed
        if (txt9BNumber.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "9B අංකය ඇතුලත් කරන්න", "අංකය නොමැත", JOptionPane.ERROR_MESSAGE);
        } else {
            txtSection.grabFocus();
            enableTextFields();
        }
    }//GEN-LAST:event_txt9BNumberActionPerformed

    private void txt9BNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt9BNumberFocusGained
        setBackgroundAtFocusGained(txt9BNumber);
    }//GEN-LAST:event_txt9BNumberFocusGained

    private void txt9BNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt9BNumberFocusLost
        setBackgroundAtFocusLost(txt9BNumber);
    }//GEN-LAST:event_txt9BNumberFocusLost

    private void txtSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSectionFocusGained
        setBackgroundAtFocusGained(txtSection);
    }//GEN-LAST:event_txtSectionFocusGained

    private void txtSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSectionFocusLost
        setBackgroundAtFocusLost(txtSection);
    }//GEN-LAST:event_txtSectionFocusLost

    private void txtCreditorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditorFocusGained
        setBackgroundAtFocusGained(txtCreditor);
    }//GEN-LAST:event_txtCreditorFocusGained

    private void txtCreditorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditorFocusLost
        setBackgroundAtFocusLost(txtCreditor);
    }//GEN-LAST:event_txtCreditorFocusLost

    private void txtSoldSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoldSectionFocusGained
        setBackgroundAtFocusGained(txtSoldSection);
    }//GEN-LAST:event_txtSoldSectionFocusGained

    private void txtSoldSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoldSectionFocusLost
        setBackgroundAtFocusLost(txtSoldSection);
    }//GEN-LAST:event_txtSoldSectionFocusLost

    private void txtSoldSectionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoldSectionKeyReleased

    }//GEN-LAST:event_txtSoldSectionKeyReleased

    private void txtSoldSectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoldSectionKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtCash.grabFocus();
//        }
    }//GEN-LAST:event_txtSoldSectionKeyPressed

    private void Table1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table1ComponentMoved
//        jScrollPane2.getViewport().setViewPosition(jScrollPane1.getViewport().getViewPosition());
    }//GEN-LAST:event_Table1ComponentMoved

    private void Table1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyReleased

    }//GEN-LAST:event_Table1KeyReleased

    private void txtDTReasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTReasonActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 33, "කාරණය", "F9B").setVisible(true);
    }//GEN-LAST:event_txtDTReasonActionPerformed

    private void txtDTReasonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTReasonFocusGained
//        txtDTLegderAccount.selectAll();
        if (txtSection.getId() == 0 || txtCreditor.getId() == 0) {
            JOptionPane.showMessageDialog(this, "අංශය හෝ ණයගැතියා ඇතුලත් කර නොමැත. කරුණාකර පිරික්සන්න.", "කරුණාකර පිරික්සන්න.", JOptionPane.ERROR_MESSAGE);
            txtDTCR.setEditable(false);
            txtDTDR.setEditable(false);
            txtDTReason.setEditable(false);
            txtSection.grabFocus();
        } else {
            txtDTReason.setBackground(Color.YELLOW);
            txtDTCR.setEditable(true);
            txtDTDR.setEditable(true);
            txtDTReason.setEditable(true);
        }
    }//GEN-LAST:event_txtDTReasonFocusGained

    private void txtDTReasonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTReasonFocusLost
        txtDTReason.select(0, 0);
        txtDTReason.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTReasonFocusLost

    private void txtDTDRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTDRActionPerformed
        txtDTCR.grabFocus();
        FormatConstants.decimalFormat.format(Double.parseDouble(txtDTDR.getText()));
    }//GEN-LAST:event_txtDTDRActionPerformed

    private void txtDTDRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTDRFocusGained

        txtDTDR.setBackground(Color.YELLOW);

        if (txtDTReason.getId() == 0) {
            JOptionPane.showMessageDialog(this, "කාරණය ඇතුලත් කර නොමැත. කරුණාකර පිරික්සන්න.", "කරුණාකර පිරික්සන්න.", JOptionPane.ERROR_MESSAGE);
            txtDTReason.grabFocus();
        } else {
            if (txtDTDR.getText().trim().isEmpty()) {
                txtDTDR.setText("0.00");
            }
            txtDTDR.selectAll();
        }

    }//GEN-LAST:event_txtDTDRFocusGained

    private void txtDTDRFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTDRFocusLost
        txtDTDR.select(0, 0);
        txtDTDR.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTDRFocusLost

    private void txtDTCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTCRActionPerformed
        if (btnSave.isEnabled()) {
            FormatConstants.decimalFormat.format(Double.parseDouble(txtDTCR.getText()));
            saveDT();
            saveHD(2);
            viewDT(HDID);
            getTotal();
        } else {
            JOptionPane.showMessageDialog(this, "නව අංකයක් ලබා ගන්න", "නව අංකයක් ලබා ගන්න", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDTCRActionPerformed

    private void txtDTCRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTCRFocusGained

        txtDTCR.setBackground(Color.YELLOW);
        if (txtDTCR.getText().trim().isEmpty()) {
            txtDTCR.setText("0.00");
        }
        txtDTCR.selectAll();
    }//GEN-LAST:event_txtDTCRFocusGained

    private void txtDTCRFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDTCRFocusLost
        txtDTCR.select(0, 0);
        txtDTCR.setBackground(new Color(191, 214, 235));
    }//GEN-LAST:event_txtDTCRFocusLost

    private void Table2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseReleased

    }//GEN-LAST:event_Table2MouseReleased

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        if (Table2.getSelectedRow() > -1) {
            int selectedRow = Table2.getSelectedRow();
            txtDTReason.setText(String.valueOf(Table2.getValueAt(selectedRow, 0)));
            txtDTDR.setText(String.valueOf(Table2.getValueAt(selectedRow, 1)));
            txtDTCR.setText(String.valueOf(Table2.getValueAt(selectedRow, 2)));
            txtDTReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 4))));
            dtid = Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 3)));
            txtDTReason.grabFocus();
        }
    }//GEN-LAST:event_Table2MouseClicked

    private void Table2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyReleased

    }//GEN-LAST:event_Table2KeyReleased

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String[] buttons = {"Yes", "No"};
        int i = JOptionPane.showOptionDialog(this, "<html><p><span style='color:red'>" + btnConfirm.getText().split(" ")[0] + " කළ පසු වෙනස් කල නොහැක.</span> " + btnConfirm.getText().split(" ")[0] + " කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
        if (i == JOptionPane.YES_OPTION) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(1);
                inputs.add(GLCodes.B9);
                inputs.add(HDID);
                inputs.add(GeneralUserLogin.data.getUsername());
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Confirm_Stock", 0);
            } catch (SQLException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
            CommonJurnalForm.ItemRefresh.doClick();
        } else {
            this.dispose();
            CommonJurnalForm.ItemRefresh.doClick();
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(C14B9HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(C14B9HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(C14B9HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(C14B9HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new C14B9HD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static org.jdesktop.swingx.JXTable Table1;
    private org.jdesktop.swingx.JXTable Table2;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private com.toedter.calendar.JDateChooser jdtDate;
    private javax.swing.JLabel statusCreditor;
    private javax.swing.JLabel statusGLTrnID;
    private javax.swing.JLabel statusSubSection1;
    private javax.swing.JLabel statusSubSection2;
    private javax.swing.JLabel statusUserName;
    private javax.swing.JLabel stausTrnID;
    private javax.swing.JTextField txt9BNumber;
    public static org.nbs.components.HiddenIDTextField txtCreditor;
    public static org.nbs.components.HiddenIDTextField txtDTCR;
    public static org.nbs.components.HiddenIDTextField txtDTDR;
    public static org.nbs.components.HiddenIDTextField txtDTReason;
    private javax.swing.JTextField txtNumber;
    public static org.nbs.components.HiddenIDTextField txtSection;
    public static org.nbs.components.HiddenIDTextField txtSoldSection;
    private javax.swing.JTextField txtTotalCR;
    private javax.swing.JTextField txtTotalDR;
    // End of variables declaration//GEN-END:variables

    private void initTxtFields() {
//        SelectAtFocusFormattedTextField fields[] = {txtCash, txtCredit, txtCreditCard, txtCreditLoan, txtPoshana, txtSamurdhi, txtTransferOut, txtWiyali, txtPurchases, txtExpences, txtOtherCashReceipts, txtTotal};
//        for (SelectAtFocusFormattedTextField field : fields) {
//            field.setText("0.00");
//        }
    }

    private void getTotal() {
        double totalDR = 0;
        double totalCR = 0;

        for (int i = 0; i < dtm.getRowCount(); i++) {
            totalDR += Double.parseDouble(String.valueOf(Table2.getValueAt(i, 1)));
            totalCR += Double.parseDouble(String.valueOf(Table2.getValueAt(i, 2)));
        }
        txtTotalDR.setText(FormatConstants.decimalFormat.format(totalDR));
        txtTotalCR.setText(FormatConstants.decimalFormat.format(totalCR));
    }

    private void setBackgroundAtFocusGained(JTextField field) {
        field.setBackground(Color.YELLOW);
    }

    private void setBackgroundAtFocusLost(JTextField field) {
        field.setBackground(new Color(242, 241, 240));
    }

    private void enableTextFields() {
        JTextField fields[] = {txtCreditor, txtDTCR, txtDTDR, txtDTReason, txtSection, txtTotalCR, txtTotalDR};

        for (JTextField field : fields) {
            field.setEnabled(true);
        }
    }

    private boolean isOK() {
        boolean ok = false;
        boolean ok1 = false;
        boolean ok2 = false;
//        boolean ok3 = false;
        if (txtSection.getId() == 0) {
            ok1 = false;
            JOptionPane.showMessageDialog(this, "අංශය ඇතුලත් කරන්න", "අංශය ඇතුලත් කරන්න", JOptionPane.ERROR_MESSAGE);
        } else {
            ok1 = true;
        }
        if (txtCreditor.getId() == 0) {
            ok2 = false;
            JOptionPane.showMessageDialog(this, "කළමණාකරු ඇතුලත් කරන්න", "කළමණාකරු ඇතුලත් කරන්න", JOptionPane.ERROR_MESSAGE);
        } else {
            ok2 = true;
        }

        ok = ok1 && ok2;
        return ok;
    }
    private String HDID = "0";

    private void saveHD(int type) {
        if (type == 1) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(HDID);
                inputs.add(txt9BNumber.getText());
                inputs.add(FormatConstants.dateFormat.format(jdtDate.getDate()));
                inputs.add(GLCodes.B9);
                inputs.add(txtSection.getId());
                inputs.add(txtSoldSection.getId());
                inputs.add(txtCreditor.getId());
                inputs.add(GeneralUserLogin.data.getUsername());
                inputs.add("@recid");

                List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F9BHD", 1);
                HDID = String.valueOf(output.get(0));
                txtNumber.setText(HDID);

            } catch (RemoteException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (isOK()) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(HDID);
                inputs.add(txt9BNumber.getText());
                inputs.add(FormatConstants.dateFormat.format(jdtDate.getDate()));
                inputs.add(GLCodes.B9);
                inputs.add(txtSection.getId());
                inputs.add(txtSoldSection.getId());
                inputs.add(txtCreditor.getId());
                inputs.add(GeneralUserLogin.data.getUsername());
                inputs.add("@recid");

                List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F9BHD", 1);
                HDID = String.valueOf(output.get(0));
                txtNumber.setText(HDID);

            } catch (RemoteException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private int dtid = 0;

    private void saveDT() {
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(dtid);
            inputs.add(HDID);
            inputs.add(txtDTReason.getId());
            double dr = FormatConstants.decimalFormat.parse(txtDTDR.getText()).doubleValue();
            double cr = FormatConstants.decimalFormat.parse(txtDTCR.getText()).doubleValue();
            double paymentValue = dr + cr;
            String formatedPaymentValue = FormatConstants.decimalFormat.format(paymentValue);
            inputs.add(FormatConstants.decimalFormat.parse(formatedPaymentValue).doubleValue());
            inputs.add(dr);
            inputs.add(cr);
            inputs.add(GeneralUserLogin.data.getUsername());
            ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F9BDT", 0);
            dtid = 0;
            txtDTReason.setId(0);
            txtDTReason.setText("");
            txtDTDR.setText("0.00");
            txtDTCR.setText("0.00");
            txtDTReason.grabFocus();

        } catch (SQLException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewDT(String HDID) {
        try {
            dtm.setRowCount(0);
            List<Object> inputs = new ArrayList<>();
            inputs.add(2);
            inputs.add(HDID);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_View9B_Edit", 5);
            for (List<Object> output : outputs) {
                Vector v = new Vector();
                for (Object object : output) {
                    v.add(object);
                }
                dtm.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(C14B9HD.class.getName()).log(Level.SEVERE, null, ex);
        }
        getTotal();
    }

}
