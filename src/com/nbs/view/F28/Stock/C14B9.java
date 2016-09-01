/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.F28.Stock;

import com.nbs.view.common.FormatConstants;
import Sources.GLCodes;
import com.nbs.impl.ServerConnection;
import static com.nbs.view.common.FormatConstants.numberFormat1;
import com.nbs.view.common.GeneralUserLogin;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.search.FindSectionOrOutsider;
import com.nbs.view.manager.CommonJurnalForm;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.nbs.components.SelectAtFocusFormattedTextField;

/**
 *
 * @author mmh
 */
public class C14B9 extends javax.swing.JFrame {

    private DateFormat df;

    private String code;
    private int ID = 0;

    /**
     * Creates new form F17
     */
    public C14B9() {
        initComponents();
        setTitle("Server on " + GeneralUserLogin.data.getIP());

        initTxtFields();
        df = new SimpleDateFormat("yyyy-MM-dd");
        jdtDate.setDate(GeneralUserLogin.data.getSystemDate());
        txt9BNumber.grabFocus();
        statusUserName.setText(GeneralUserLogin.data.getUserCode() + "-" + GeneralUserLogin.data.getUsername());
    }

    public C14B9(String code) {
        this();
        this.code = code;
        statusGLTrnID.setText(GLCodes.B9 + "");
        btnConfirm.setVisible(false);
    }

    public C14B9(String code, List dataArray) {
        this(code);
        try {
            txtNumber.setText(String.valueOf(dataArray.get(0)));
            ID = Integer.parseInt(String.valueOf(dataArray.get(0)));
            txt9BNumber.setText(String.valueOf(dataArray.get(2)));
            jdtDate.setDate(df.parse(String.valueOf(dataArray.get(1))));
            txtSection.setId(Integer.parseInt(String.valueOf(dataArray.get(4))));
            txtSection.setText(String.valueOf(dataArray.get(5)));
            txtSoldSection.setId(Integer.parseInt(String.valueOf(dataArray.get(8))));
            txtSoldSection.setText(String.valueOf(dataArray.get(9)));
            txtCreditor.setId(Integer.parseInt(String.valueOf(dataArray.get(6))));
            txtCreditor.setText(String.valueOf(dataArray.get(7)));
            txtCash.setText(String.valueOf(dataArray.get(13)));
            txtCredit.setText(String.valueOf(dataArray.get(14)));
            txtCreditLoan.setText(String.valueOf(dataArray.get(15)));
            txtSamurdhi.setText(String.valueOf(dataArray.get(16)));
            txtPoshana.setText(String.valueOf(dataArray.get(17)));
            txtWiyali.setText(String.valueOf(dataArray.get(18)));
            txtTransferOut.setText(String.valueOf(dataArray.get(19)));
            txtCreditCard.setText(String.valueOf(dataArray.get(20)));
            txtTotal.setText(String.valueOf(dataArray.get(21)));
            txtExpences.setText(String.valueOf(dataArray.get(23)));
            txtOtherCashReceipts.setText(String.valueOf(dataArray.get(24)));
            txtPurchases.setText(String.valueOf(dataArray.get(22)));

            statusCreditor.setText(txtCreditor.getId() + "");
            statusSubSection1.setText(txtSection.getId() + "");
            statusSubSection2.setText(txtSoldSection.getId() + "");
            stausTrnID.setText(txtNumber.getText());
        } catch (ParseException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public C14B9(String code, List dataArray, int i) {
        this(code);
        if (i == 1) {
            txtSection.setId(Integer.parseInt(String.valueOf(dataArray.get(4))));
            txtSection.setText(String.valueOf(dataArray.get(5)));
            txtCreditor.setId(Integer.parseInt(String.valueOf(dataArray.get(6))));
            txtCreditor.setText(String.valueOf(dataArray.get(7)));
        }
    }

    public C14B9(String code, String id) {
        this(code);
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(id);
            ID = Integer.parseInt(id);
            List dataArray = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_View9B_Edit_OldFormat", 45);
            try {
                txtNumber.setText(String.valueOf(dataArray.get(0)));
                txt9BNumber.setText(String.valueOf(dataArray.get(2)));
                jdtDate.setDate(df.parse(String.valueOf(dataArray.get(1))));
                txtSection.setId(Integer.parseInt(String.valueOf(dataArray.get(4))));
                txtSection.setText(String.valueOf(dataArray.get(5)));
                txtSoldSection.setId(Integer.parseInt(String.valueOf(dataArray.get(8))));
                txtSoldSection.setText(String.valueOf(dataArray.get(9)));
                txtCreditor.setId(Integer.parseInt(String.valueOf(dataArray.get(6))));
                txtCreditor.setText(String.valueOf(dataArray.get(7)));
                txtCash.setText(String.valueOf(dataArray.get(13)));
                txtCredit.setText(String.valueOf(dataArray.get(14)));
                txtCreditLoan.setText(String.valueOf(dataArray.get(15)));
                txtSamurdhi.setText(String.valueOf(dataArray.get(16)));
                txtPoshana.setText(String.valueOf(dataArray.get(17)));
                txtWiyali.setText(String.valueOf(dataArray.get(18)));
                txtTransferOut.setText(String.valueOf(dataArray.get(19)));
                txtCreditCard.setText(String.valueOf(dataArray.get(20)));
                txtTotal.setText(String.valueOf(dataArray.get(21)));
                txtExpences.setText(String.valueOf(dataArray.get(23)));
                txtOtherCashReceipts.setText(String.valueOf(dataArray.get(24)));
                txtPurchases.setText(String.valueOf(dataArray.get(22)));

                statusCreditor.setText(txtCreditor.getId() + "");
                statusSubSection1.setText(txtSection.getId() + "");
                statusSubSection2.setText(txtSoldSection.getId() + "");
                stausTrnID.setText(txtNumber.getText());
                status.setText(String.valueOf(dataArray.get(12)));
                if (status.getText().equals("2")) {
                    btnNew.setEnabled(false);
                    btnSave.setEnabled(false);
                }
                btnConfirm.setText(String.valueOf(dataArray.get(44)));
                btnConfirm.setVisible(true);
                enableTextFields();
            } catch (ParseException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtCash = new org.nbs.components.SelectAtFocusFormattedTextField();
        txtCredit = new org.nbs.components.SelectAtFocusFormattedTextField();
        txtCreditLoan = new org.nbs.components.SelectAtFocusFormattedTextField();
        txtSamurdhi = new org.nbs.components.SelectAtFocusFormattedTextField();
        txtPoshana = new org.nbs.components.SelectAtFocusFormattedTextField();
        txtWiyali = new org.nbs.components.SelectAtFocusFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTotal = new org.nbs.components.SelectAtFocusFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTransferOut = new org.nbs.components.SelectAtFocusFormattedTextField();
        txtCreditCard = new org.nbs.components.SelectAtFocusFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtPurchases = new org.nbs.components.SelectAtFocusFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        txtExpences = new org.nbs.components.SelectAtFocusFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        txtOtherCashReceipts = new org.nbs.components.SelectAtFocusFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        statusGLTrnID = new javax.swing.JLabel();
        stausTrnID = new javax.swing.JLabel();
        statusSubSection1 = new javax.swing.JLabel();
        statusSubSection2 = new javax.swing.JLabel();
        statusCreditor = new javax.swing.JLabel();
        statusUserName = new javax.swing.JLabel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
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
        txtNumber.setText("0");
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
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt9BNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSoldSection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCreditor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(txtNumber))
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2), "වෙළඳාම", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("තැන්පත් ණයට :");

        jLabel13.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("සමෘද්ධි මුද්දර :");

        jLabel14.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("පෝෂණ මුද්දර :");

        jLabel15.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("වියළි සලාක මුද්දර :");

        jLabel16.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("ණයට :");

        jLabel17.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("මුදලට :");

        txtCash.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCash.setEnabled(false);
        txtCash.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtCash.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtCash.setHorizontalAlignment(11);
        txtCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCashActionPerformed(evt);
            }
        });

        txtCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCredit.setEnabled(false);
        txtCredit.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtCredit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtCredit.setHorizontalAlignment(11);
        txtCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditActionPerformed(evt);
            }
        });

        txtCreditLoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCreditLoan.setEnabled(false);
        txtCreditLoan.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtCreditLoan.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtCreditLoan.setHorizontalAlignment(11);
        txtCreditLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditLoanActionPerformed(evt);
            }
        });

        txtSamurdhi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSamurdhi.setEnabled(false);
        txtSamurdhi.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtSamurdhi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtSamurdhi.setHorizontalAlignment(11);
        txtSamurdhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSamurdhiActionPerformed(evt);
            }
        });

        txtPoshana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPoshana.setEnabled(false);
        txtPoshana.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtPoshana.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPoshana.setHorizontalAlignment(11);
        txtPoshana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPoshanaActionPerformed(evt);
            }
        });

        txtWiyali.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtWiyali.setEnabled(false);
        txtWiyali.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtWiyali.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtWiyali.setHorizontalAlignment(11);
        txtWiyali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWiyaliActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("මුළු වෙළඳාම් :");

        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotal.setEnabled(false);
        txtTotal.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtTotal.setHorizontalAlignment(11);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCash, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(txtCredit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCreditLoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSamurdhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPoshana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtWiyali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSamurdhi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPoshana, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWiyali, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2), "වෙනත්", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 1, 15), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText("ණයපත් :");

        jLabel23.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("මාරු යැවීම් :");

        txtTransferOut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTransferOut.setEnabled(false);
        txtTransferOut.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtTransferOut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtTransferOut.setHorizontalAlignment(11);
        txtTransferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransferOutActionPerformed(evt);
            }
        });

        txtCreditCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCreditCard.setEnabled(false);
        txtCreditCard.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtCreditCard.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtCreditCard.setHorizontalAlignment(11);
        txtCreditCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTransferOut, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTransferOut, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnConfirm.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnConfirm.setText("අනුමත කරන්න");
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

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 1, 15), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText("මුදලට ගැණුම් :");

        txtPurchases.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPurchases.setEnabled(false);
        txtPurchases.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtPurchases.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPurchases.setHorizontalAlignment(11);
        txtPurchases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPurchasesActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText("F2 වියදම් :");

        txtExpences.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtExpences.setEnabled(false);
        txtExpences.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtExpences.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtExpences.setHorizontalAlignment(11);
        txtExpences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExpencesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPurchases, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpences, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPurchases, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpences, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 1, 15), new java.awt.Color(51, 51, 255))); // NOI18N

        txtOtherCashReceipts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtOtherCashReceipts.setEnabled(false);
        txtOtherCashReceipts.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtOtherCashReceipts.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtOtherCashReceipts.setHorizontalAlignment(11);
        txtOtherCashReceipts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOtherCashReceiptsActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("වෙනත් ලැබීම් :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOtherCashReceipts, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtherCashReceipts, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXStatusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (isOK()) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(ID);
                inputs.add(txt9BNumber.getText());
                inputs.add(df.format(jdtDate.getDate()));
                inputs.add(GLCodes.B9);
                inputs.add(txtSection.getId());
                inputs.add(txtSoldSection.getId());
                inputs.add(txtCreditor.getId());
                inputs.add(numberFormat1.parse(txtCash.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtCredit.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtCreditLoan.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtSamurdhi.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtPoshana.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtWiyali.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtTransferOut.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtCreditCard.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtTotal.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtPurchases.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtExpences.getText()).doubleValue());
                inputs.add(numberFormat1.parse(txtOtherCashReceipts.getText()).doubleValue());
                inputs.add(GeneralUserLogin.data.getUsername());
                inputs.add("@recid");

                List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F9B", 1);
                txtNumber.setText(String.valueOf(output.get(0)));
                ID = Integer.parseInt(String.valueOf(output.get(0)));
//                String[] buttons = {"Yes", "No"};
//                int i = JOptionPane.showOptionDialog(this, "<html><p>ගණුදෙනුව සම්පූර්ණයි. නව ගණුදෙනුවක් සිදු කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
                JOptionPane.showMessageDialog(this, "<html><p>ගණුදෙනුව සම්පූර්ණයි.</p></html>", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
//                if (i == JOptionPane.YES_OPTION) {
//                    int i2 = JOptionPane.showOptionDialog(this, "<html><p>අංශය වෙනස් කරනවාද?</p></html>", "වෙනස් කරනවාද?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
//                    if (i2 == JOptionPane.NO_OPTION) {
//                        String section = txtSection.getText();
//                        int sid = txtSection.getId();
//                        String creditor = txtCreditor.getText();
//                        int cid = txtCreditor.getId();
//                        ID = 0;
//                        C14B9 c14B9 = new C14B9(code);
//
//                        c14B9.txtSection.setId(sid);
//                        c14B9.txtSection.setText(section);
//                        c14B9.txtCreditor.setId(cid);
//                        c14B9.txtCreditor.setText(creditor);
//
//                        c14B9.setVisible(true);
//                        this.dispose();
//                        CommonJurnalForm.ItemRefresh.doClick();
//                    } else {
//                        this.dispose();
//                        new C14B9(code).setVisible(true);
//                        ID = 0;
//                        CommonJurnalForm.ItemRefresh.doClick();
//                    }
//                } else {
                this.dispose();
                ID = 0;
                CommonJurnalForm.ItemRefresh.doClick();
//                }
            } catch (RemoteException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSectionActionPerformed
        jButton5.doClick();
    }//GEN-LAST:event_txtSectionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 40, "අනු අංශය").setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 41, "පාර්ශවකරු").setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 42, "අනු අංශය").setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCashActionPerformed
        txtCredit.grabFocus();
        getTotal();
    }//GEN-LAST:event_txtCashActionPerformed

    private void txtCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditActionPerformed
        txtCreditLoan.grabFocus();
        getTotal();
    }//GEN-LAST:event_txtCreditActionPerformed

    private void txtCreditLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditLoanActionPerformed
        txtSamurdhi.grabFocus();
        getTotal();
    }//GEN-LAST:event_txtCreditLoanActionPerformed

    private void txtSamurdhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSamurdhiActionPerformed
        txtPoshana.grabFocus();
        getTotal();
    }//GEN-LAST:event_txtSamurdhiActionPerformed

    private void txtWiyaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWiyaliActionPerformed
        txtTransferOut.grabFocus();
        getTotal();
    }//GEN-LAST:event_txtWiyaliActionPerformed

    private void txtPoshanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPoshanaActionPerformed
        txtWiyali.grabFocus();
        getTotal();
    }//GEN-LAST:event_txtPoshanaActionPerformed

    private void txtTransferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransferOutActionPerformed
        txtCreditCard.grabFocus();
    }//GEN-LAST:event_txtTransferOutActionPerformed

    private void txtCreditCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditCardActionPerformed
        txtPurchases.grabFocus();
    }//GEN-LAST:event_txtCreditCardActionPerformed

    private void txtCreditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditorActionPerformed
        jButton6.doClick();
    }//GEN-LAST:event_txtCreditorActionPerformed

    private void txtSoldSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoldSectionActionPerformed
        jButton7.doClick();
    }//GEN-LAST:event_txtSoldSectionActionPerformed

    private void txtPurchasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPurchasesActionPerformed
        txtExpences.grabFocus();
    }//GEN-LAST:event_txtPurchasesActionPerformed

    private void txtExpencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExpencesActionPerformed
        txtOtherCashReceipts.grabFocus();
    }//GEN-LAST:event_txtExpencesActionPerformed

    private void txtOtherCashReceiptsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtherCashReceiptsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtherCashReceiptsActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            List inputs = new ArrayList<>();
            inputs.add(ID);
            inputs.add(txt9BNumber.getText());
            inputs.add(df.format(jdtDate.getDate()));
            inputs.add(GLCodes.B9);
            inputs.add(txtSection.getId());
            inputs.add(txtSoldSection.getId());
            inputs.add(txtCreditor.getId());
            inputs.add(numberFormat1.parse(txtCash.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtCredit.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtCreditLoan.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtSamurdhi.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtPoshana.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtWiyali.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtTransferOut.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtCreditCard.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtTotal.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtPurchases.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtExpences.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtOtherCashReceipts.getText()).doubleValue());
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add("@recid");

            List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F9B", 1);
            txtNumber.setText(String.valueOf(output.get(0)));
            ID = Integer.parseInt(String.valueOf(output.get(0)));
            txtSection.grabFocus();
            enableTextFields();
        } catch (ParseException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCash.grabFocus();
        }
    }//GEN-LAST:event_txtSoldSectionKeyPressed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String[] buttons = {"Yes", "No"};
        int i = JOptionPane.showOptionDialog(this, "<html><p><span style='color:red'>" + btnConfirm.getText().split(" ")[0] + " කළ පසු වෙනස් කල නොහැක.</span> " + btnConfirm.getText().split(" ")[0] + " කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
        if (i == JOptionPane.YES_OPTION) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(1);
                inputs.add(GLCodes.B9);
                inputs.add(ID);
                inputs.add(GeneralUserLogin.data.getUsername());
                System.out.println(inputs);
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Confirm_Stock", 0);
                JOptionPane.showMessageDialog(this, "සාර්ථකයි", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                CommonJurnalForm.ItemRefresh.doClick();
            } catch (SQLException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(C14B9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(C14B9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(C14B9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(C14B9.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new C14B9().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private com.toedter.calendar.JDateChooser jdtDate;
    private javax.swing.JLabel status;
    private javax.swing.JLabel statusCreditor;
    private javax.swing.JLabel statusGLTrnID;
    private javax.swing.JLabel statusSubSection1;
    private javax.swing.JLabel statusSubSection2;
    private javax.swing.JLabel statusUserName;
    private javax.swing.JLabel stausTrnID;
    private javax.swing.JTextField txt9BNumber;
    public static org.nbs.components.SelectAtFocusFormattedTextField txtCash;
    private org.nbs.components.SelectAtFocusFormattedTextField txtCredit;
    private org.nbs.components.SelectAtFocusFormattedTextField txtCreditCard;
    private org.nbs.components.SelectAtFocusFormattedTextField txtCreditLoan;
    public static org.nbs.components.HiddenIDTextField txtCreditor;
    private org.nbs.components.SelectAtFocusFormattedTextField txtExpences;
    private javax.swing.JTextField txtNumber;
    private org.nbs.components.SelectAtFocusFormattedTextField txtOtherCashReceipts;
    private org.nbs.components.SelectAtFocusFormattedTextField txtPoshana;
    private org.nbs.components.SelectAtFocusFormattedTextField txtPurchases;
    private org.nbs.components.SelectAtFocusFormattedTextField txtSamurdhi;
    public static org.nbs.components.HiddenIDTextField txtSection;
    public static org.nbs.components.HiddenIDTextField txtSoldSection;
    private org.nbs.components.SelectAtFocusFormattedTextField txtTotal;
    private org.nbs.components.SelectAtFocusFormattedTextField txtTransferOut;
    private org.nbs.components.SelectAtFocusFormattedTextField txtWiyali;
    // End of variables declaration//GEN-END:variables

    private void initTxtFields() {
        SelectAtFocusFormattedTextField fields[] = {txtCash, txtCredit, txtCreditCard, txtCreditLoan, txtPoshana, txtSamurdhi, txtTransferOut, txtWiyali, txtPurchases, txtExpences, txtOtherCashReceipts, txtTotal};
        for (SelectAtFocusFormattedTextField field : fields) {
            field.setText("0.00");
        }
    }

    private void getTotal() {
        SelectAtFocusFormattedTextField fields[] = {txtCash, txtCredit, txtCreditCard, txtCreditLoan, txtPoshana, txtSamurdhi, txtTransferOut, txtWiyali};
        double total = 0;
        for (SelectAtFocusFormattedTextField field : fields) {
            try {
                Number number = numberFormat1.parse(field.getText());
                total += number.doubleValue();
            } catch (ParseException ex) {
                Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtTotal.setText(numberFormat1.format(total));
    }

    private void setBackgroundAtFocusGained(JTextField field) {
        field.setBackground(Color.YELLOW);
    }

    private void setBackgroundAtFocusLost(JTextField field) {
        field.setBackground(new Color(242, 241, 240));
    }

    private void enableTextFields() {
        JTextField fields[] = {txtCash, txtCredit, txtCreditCard, txtCreditLoan, txtPoshana, txtSamurdhi, txtTransferOut, txtWiyali, txtPurchases, txtExpences, txtOtherCashReceipts, txtSection, txtCreditor, txtSoldSection};

        for (JTextField field : fields) {
            field.setEnabled(true);
        }
    }

    private boolean isOK() {
        boolean ok = false;
        try {
            boolean ok1 = false;
            boolean ok2 = false;
            boolean ok3 = false;

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
            if (FormatConstants.numberFormat1.parse(txtTransferOut.getText()).doubleValue() > 0) {
                if (txtSoldSection.getId() == 0) {
                    JOptionPane.showMessageDialog(this, "විකිණූ අංශය ඇතුලත් කරන්න", "විකිණූ අංශය ඇතුලත් කරන්න", JOptionPane.ERROR_MESSAGE);
                } else {
                    ok3 = true;
                }
            } else {
                ok3 = true;
            }
            ok = ok1 && ok2 && ok3;

        } catch (ParseException ex) {
            Logger.getLogger(C14B9.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

}
