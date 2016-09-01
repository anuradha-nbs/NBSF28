/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common.search;

import Sources.TableColumnFontChanger;
import com.nbs.impl.ServerConnection;
import com.nbs.view.F28.Payments.CommonCashReceiptCash;
import com.nbs.view.F28.Payments.CommonDRNote;
import com.nbs.view.F28.Payments.CommonGLAccountOpenningBalances;
import com.nbs.view.F28.Payments.CommonRuralBank9E;
import com.nbs.view.F28.Stock.C14B9HD;
import com.nbs.view.Reports.ReportParameterGrid;
import com.nbs.view.Reports.ReportsPanel;
import com.nbs.view.common.AmaliKeyBoard;
import com.nbs.view.common.DualConvertorAmali;
import com.nbs.view.common.GeneralUserLogin;
import java.awt.Font;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static com.nbs.view.common.GeneralUserLogin.data;
import com.nbs.view.common.UnicodeKeyBoard;
import com.nbs.view.manager.AddCurrentAccount;
import com.nbs.view.manager.CommonJurnalForm;
import com.nbs.view.manager.CommonJurnalFormComponentStateSave;
import com.nbs.view.manager.GL.CurrentAccountControl;
import com.nbs.view.manager.TargetStock;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author mmh
 */
public class FindAccount5Data extends javax.swing.JDialog {

    /**
     * Creates new form FrmGenDepDepositMasterSearchDialog
     */
    private int type = 0;
    private final DefaultTableModel dtm;
    private int acctType = 0;
    private JTextField fromtxt;
    private String AcctLevel="5";

    public FindAccount5Data(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        lblBranchName.setText(GeneralUserLogin.branchname);
        lblTitle.setFont(new java.awt.Font("Iskoola pota", 1, 25));
        dtm = (DefaultTableModel) tblDepCustomer.getModel();
        tblDepCustomer.getTableHeader().setFont(new Font("Iskoola pota", 0, 13));
        txtSearchData.requestFocus();

        jButton1.doClick();
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 5, new java.awt.Color(190, 121, 0), Color.WHITE);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 2, new java.awt.Color(3, 130, 42), Color.WHITE);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 4, new java.awt.Color(3, 130, 42), Color.WHITE);
        TableColumnFontChanger.setAmalee(tblDepCustomer, 3);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 6);
//        tblDepCustomer.setDefaultRenderer(Object.class, cellRenderer);
    }

    public FindAccount5Data(java.awt.Frame parent, boolean modal, int type) {
        this(parent, modal);

        this.type = type;
        if (type == 6 && CommonCashReceiptCash.save) {
            dispose();
        } else if (type == 0) {
            tblDepCustomer.setComponentPopupMenu(null);
        }
        jButton1.doClick();
    }

    public FindAccount5Data(java.awt.Frame parent, boolean modal, int type, int AcctType) {
        this(parent, modal, type);
        this.acctType = AcctType;
        jButton1.doClick();
    }

    public FindAccount5Data(JDialog parent, boolean modal, int type, int AcctType) {
        this(parent, modal, type);
        this.acctType = AcctType;
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 2);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 4);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 5);

        jButton1.doClick();
    }

    public FindAccount5Data(JDialog parent, boolean modal, int type) {
        this(parent, modal);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 2, new java.awt.Color(3, 130, 42), Color.WHITE);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 5, new java.awt.Color(190, 121, 0), Color.WHITE);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 4, new java.awt.Color(3, 130, 42), Color.WHITE);
        TableColumnFontChanger.setAmalee(tblDepCustomer, 3);
        this.type = type;
        if (type == 6 && CommonCashReceiptCash.save) {
            dispose();
        }
        jButton1.doClick();
    }

    public FindAccount5Data(JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        lblBranchName.setText(GeneralUserLogin.branchname);
        lblTitle.setFont(new java.awt.Font("Iskoola pota", 1, 25));
        dtm = (DefaultTableModel) tblDepCustomer.getModel();
        tblDepCustomer.getTableHeader().setFont(new Font("Iskoola pota", 0, 13));
        txtSearchData.requestFocus();

        jButton1.doClick();
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 5, new java.awt.Color(190, 121, 0), Color.WHITE);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 2, new java.awt.Color(3, 130, 42), Color.WHITE);
        TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 4, new java.awt.Color(3, 130, 42), Color.WHITE);
        TableColumnFontChanger.setAmalee(tblDepCustomer, 3);

//        tblDepCustomer.setDefaultRenderer(Object.class, cellRenderer);
    }

    public FindAccount5Data(JDialog parent, boolean modal, int type, String AcctLevel) {
        this(parent, modal, type);
        this.AcctLevel = AcctLevel;
        jButton1.doClick();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Use = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        checkAllCust = new javax.swing.JCheckBox();
        txtSearchData = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDepCustomer = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        lblBranchName = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        Use.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        Use.setText("භාවිතා කරන්න");
        Use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UseActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Use);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(java.awt.Color.white);

        lblTitle.setBackground(new java.awt.Color(0, 81, 136));
        lblTitle.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblTitle.setForeground(java.awt.Color.white);
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("GL Account Search");
        lblTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTitle.setOpaque(true);

        checkAllCust.setBackground(new java.awt.Color(220, 234, 233));
        checkAllCust.setText("All GL");
        checkAllCust.setOpaque(true);
        checkAllCust.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkAllCustStateChanged(evt);
            }
        });
        checkAllCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkAllCustMouseReleased(evt);
            }
        });
        checkAllCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAllCustActionPerformed(evt);
            }
        });

        txtSearchData.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtSearchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchDataActionPerformed(evt);
            }
        });
        txtSearchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchDataKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchDataKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(22, 116, 191));
        jButton1.setForeground(java.awt.Color.white);
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1350, 402));

        tblDepCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nAcctLevelID5", "nAcctLevelID1", "cDescriptionUni_L1", "cCAAcctNo_L5", "cDescriptionSin_L5", "cDescriptionUni_L5", "DescriptionL5Uni"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDepCustomer.setComponentPopupMenu(jPopupMenu1);
        tblDepCustomer.setRowHeight(25);
        tblDepCustomer.getTableHeader().setResizingAllowed(false);
        tblDepCustomer.getTableHeader().setReorderingAllowed(false);
        tblDepCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDepCustomerMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepCustomerMouseClicked(evt);
            }
        });
        tblDepCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDepCustomerKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDepCustomerKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDepCustomer);
        if (tblDepCustomer.getColumnModel().getColumnCount() > 0) {
            tblDepCustomer.getColumnModel().getColumn(0).setMinWidth(100);
            tblDepCustomer.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblDepCustomer.getColumnModel().getColumn(0).setMaxWidth(100);
            tblDepCustomer.getColumnModel().getColumn(1).setMinWidth(100);
            tblDepCustomer.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblDepCustomer.getColumnModel().getColumn(1).setMaxWidth(100);
            tblDepCustomer.getColumnModel().getColumn(2).setMinWidth(150);
            tblDepCustomer.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblDepCustomer.getColumnModel().getColumn(2).setMaxWidth(150);
            tblDepCustomer.getColumnModel().getColumn(3).setMinWidth(300);
            tblDepCustomer.getColumnModel().getColumn(3).setPreferredWidth(300);
            tblDepCustomer.getColumnModel().getColumn(3).setMaxWidth(300);
            tblDepCustomer.getColumnModel().getColumn(4).setMinWidth(350);
            tblDepCustomer.getColumnModel().getColumn(4).setPreferredWidth(350);
            tblDepCustomer.getColumnModel().getColumn(4).setMaxWidth(350);
            tblDepCustomer.getColumnModel().getColumn(5).setMinWidth(350);
            tblDepCustomer.getColumnModel().getColumn(5).setPreferredWidth(350);
            tblDepCustomer.getColumnModel().getColumn(5).setMaxWidth(350);
            tblDepCustomer.getColumnModel().getColumn(6).setMinWidth(0);
            tblDepCustomer.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblDepCustomer.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jButton2.setBackground(new java.awt.Color(14, 126, 207));
        jButton2.setForeground(java.awt.Color.white);
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblBranchName.setBackground(new java.awt.Color(220, 234, 233));
        lblBranchName.setOpaque(true);

        lblSearch.setBackground(new java.awt.Color(220, 234, 233));
        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSearch.setText("ID");
        lblSearch.setOpaque(true);

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.setMaximumSize(new java.awt.Dimension(100, 27));
        jTextField1.setMinimumSize(new java.awt.Dimension(100, 27));
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 27));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField2.setMaximumSize(new java.awt.Dimension(100, 27));
        jTextField2.setMinimumSize(new java.awt.Dimension(100, 27));
        jTextField2.setPreferredSize(new java.awt.Dimension(100, 27));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField3.setMaximumSize(new java.awt.Dimension(150, 27));
        jTextField3.setMinimumSize(new java.awt.Dimension(150, 27));
        jTextField3.setPreferredSize(new java.awt.Dimension(150, 27));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField4.setMaximumSize(new java.awt.Dimension(300, 27));
        jTextField4.setMinimumSize(new java.awt.Dimension(300, 27));
        jTextField4.setPreferredSize(new java.awt.Dimension(300, 27));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField5.setMaximumSize(new java.awt.Dimension(350, 27));
        jTextField5.setMinimumSize(new java.awt.Dimension(350, 27));
        jTextField5.setPreferredSize(new java.awt.Dimension(350, 27));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField6.setMaximumSize(new java.awt.Dimension(350, 27));
        jTextField6.setMinimumSize(new java.awt.Dimension(350, 27));
        jTextField6.setPreferredSize(new java.awt.Dimension(350, 27));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel1.setText("F2 = යතුරුපුවරුව F3 = පරිවර්තකය");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkAllCust, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lblBranchName, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBranchName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkAllCust, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkAllCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAllCustActionPerformed

    }//GEN-LAST:event_checkAllCustActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            dtm.setRowCount(0);
            List<Object> inputs = new ArrayList<Object>();
            inputs.add(this.acctType);
            inputs.add(jTextField1.getText());
            inputs.add(jTextField2.getText());
            inputs.add(jTextField3.getText());
            inputs.add(jTextField4.getText());
            inputs.add(jTextField5.getText());
            inputs.add(jTextField6.getText());
            inputs.add(AcctLevel);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_FindData_Act_ChartOfAcctL5", 7);
            for (List<Object> output : outputs) {
                Object[] data = output.toArray();
                dtm.addRow(data);
            }
//            if (this.acctType == 2) {
            TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 4);
            TableColumnFontChanger.setIskolaPotha(tblDepCustomer, 5);
//            }
        } catch (RemoteException ex) {
            Logger.getLogger(FindAccount5Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FindAccount5Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FindAccount5Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchDataActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_txtSearchDataActionPerformed

    private void tblDepCustomerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDepCustomerKeyReleased
        if (tblDepCustomer.getSelectedRow() > -1 && evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            selectItem();
        }

    }//GEN-LAST:event_tblDepCustomerKeyReleased

    private void tblDepCustomerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepCustomerMouseReleased

    }//GEN-LAST:event_tblDepCustomerMouseReleased

    private void txtSearchDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDataKeyReleased

    }//GEN-LAST:event_txtSearchDataKeyReleased

    private void checkAllCustStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkAllCustStateChanged

    }//GEN-LAST:event_checkAllCustStateChanged

    private void checkAllCustMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAllCustMouseReleased
    }//GEN-LAST:event_checkAllCustMouseReleased

    private void tblDepCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepCustomerMouseClicked

    }//GEN-LAST:event_tblDepCustomerMouseClicked

    private void UseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UseActionPerformed
        if (tblDepCustomer.getSelectedRow() > -1) {
            selectItem();
        }
    }//GEN-LAST:event_UseActionPerformed

    private void txtSearchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            new AmaliKeyBoard(this, true, txtSearchData).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            new UnicodeKeyBoard(this, true, txtSearchData).setVisible(true);
        }
    }//GEN-LAST:event_txtSearchDataKeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        dtm.setRowCount(0);
        jButton1.doClick();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            new AmaliKeyBoard(this, true, jTextField4).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            new DualConvertorAmali(this, true, jTextField4).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            fromtxt = jTextField4;
            tblDepCustomer.grabFocus();
            tblDepCustomer.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            new AmaliKeyBoard(this, true, jTextField5).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            new DualConvertorAmali(this, true, jTextField5).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            fromtxt = jTextField5;
            tblDepCustomer.grabFocus();
            tblDepCustomer.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            new UnicodeKeyBoard(this, true, jTextField6).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            new DualConvertorAmali(this, true, jTextField6).setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            fromtxt = jTextField6;
            tblDepCustomer.grabFocus();
            tblDepCustomer.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            fromtxt = jTextField1;
            tblDepCustomer.grabFocus();
            tblDepCustomer.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void tblDepCustomerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDepCustomerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (tblDepCustomer.getSelectedRow() == 0) {
                fromtxt.grabFocus();
                tblDepCustomer.getSelectionModel().clearSelection();
            }
        }
    }//GEN-LAST:event_tblDepCustomerKeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            fromtxt = jTextField2;
            tblDepCustomer.grabFocus();
            tblDepCustomer.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            fromtxt = jTextField3;
            tblDepCustomer.grabFocus();
            tblDepCustomer.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FindAccount5Data dialog = new FindAccount5Data(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem Use;
    private javax.swing.JCheckBox checkAllCust;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblBranchName;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblDepCustomer;
    private javax.swing.JTextField txtSearchData;
    // End of variables declaration//GEN-END:variables
private void setDataToDepLoanType(int keyCode) {

    }

    private void setDataToDepSavingsType(int keyCode) {

    }

    private void setDataToDepositeType(int keyCode) {

    }

    private void setFrmLoantypeManager(int keyCode) {

    }

    private void setFrmSavingType(int keyCode) {

    }

    private void serFrmJorRanCom(int keyCode) {

    }

    private void setFrmJoirnalTrans(int keyCode) {

    }

    private void setFrmJoirnalTrans1(int keyCode) {

    }

    private void selectItem() {
        switch (type) {
            case 1:
                int selectedRow = tblDepCustomer.getSelectedRow();
                data.setCashInHandID(Integer.parseInt(tblDepCustomer.getValueAt(selectedRow, 0).toString()));
                break;
            case 2:
                CommonDRNote.txtDTGLAcct.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                CommonDRNote.txtDTGLAcctDescription.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 6).toString());
                CommonDRNote.txtDTGLAcct.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CommonDRNote.txtDTDrCr.grabFocus();
                break;
            case 3:
                CommonDRNote.txtDebitAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 6).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                CommonDRNote.txtDebitAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 4:
                CommonDRNote.txtBankCrr.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 6).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                CommonDRNote.txtBankCrr.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 5:
                ReasonDialog.txtReasonGLCode.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                ReasonDialog.txtReasonGLCode.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 6:
                CommonCashReceiptCash.txtReasonorGlAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                CommonCashReceiptCash.txtReasonorGlAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CommonCashReceiptCash.isCheck.grabFocus();
//                    CommonCashReceipt.Table1.editCellAt(0, 2);
                break;
            case 7:
                CommonRuralBank9E.txtDTLegderAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 6).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                CommonRuralBank9E.txtDTLegderAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CommonRuralBank9E.txtDTDR.grabFocus();

                break;
            case 8:
                C14B9HD.txtDTReason.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 4).toString());
                C14B9HD.txtDTReason.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                C14B9HD.txtDTDR.grabFocus();

                break;
            case 9:
                CommonGLAccountOpenningBalances.txtDebitAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 6).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 4).toString());
                CommonGLAccountOpenningBalances.txtDebitAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CommonGLAccountOpenningBalances.txtDebitAccount.grabFocus();
                break;
            case 10:
                CommonJurnalForm.txtCurrentAct.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                CommonJurnalForm.txtCurrentAct.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CommonJurnalForm.ItemRefresh.doClick();
//                CommonJurnalFormComponentStateSave.setCAID(CommonJurnalForm.txtCurrentAct.getId());
//                CommonJurnalFormComponentStateSave.setCAText(CommonJurnalForm.txtCurrentAct.getText());
                break;
            case 11:
                SubSectionDialog.txtGLAcct.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 5).toString());
                SubSectionDialog.txtGLAcct.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 12:
                CommonCashReceiptCash.txtRelatedGLAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                CommonCashReceiptCash.txtRelatedGLAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 13:
                ReportsPanel.txtDrGLAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                ReportsPanel.txtDrGLAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 14:
                ReportParameterGrid.hiddenIDTextField4.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                ReportParameterGrid.hiddenIDTextField4.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 15:
                CommonCashReceiptCash.txtReasonorGlAccount1.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                CommonCashReceiptCash.txtReasonorGlAccount1.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CommonCashReceiptCash.isCheck.grabFocus();
                break;
            case 16:
                CurrentAccountControl.txtCA.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                CurrentAccountControl.txtCA.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                CurrentAccountControl.hiddenIDTextField2.grabFocus();
                break;
            case 17:
                ReportsPanel.txtCrGLAccount.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString());
                ReportsPanel.txtCrGLAccount.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 18:
                AddCurrentAccount.txtAcct5No.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 3).toString());
                AddCurrentAccount.txtAcct5No.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            case 19:
                TargetStock.txtACT5.setText(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 2).toString() + " - " + tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 6).toString());
                TargetStock.txtACT5.setId(Integer.parseInt(String.valueOf(tblDepCustomer.getValueAt(tblDepCustomer.getSelectedRow(), 0))));
                break;
            default:
                break;
        }
        dispose();
    }
}
