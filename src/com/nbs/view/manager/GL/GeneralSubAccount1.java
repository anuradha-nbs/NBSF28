/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager.GL;

import Sources.TableColumnFontChanger;
import com.nbs.impl.ServerConnection;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import static Resources.SelectLang.textBundle;
import Sources.ComboBoxForeGroundColorFixer;
import com.nbs.view.common.AmaliKeyBoard;
import com.nbs.view.common.UnicodeKeyBoard;
import static com.nbs.view.manager.GLTree.levels;
import com.nbs.view.manager.GeneralTreeControl;
import com.nbs.view.manager.GenerateJTree;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author mmh
 */
public class GeneralSubAccount1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form GeneralSubAccount1
     */
    private DefaultTableModel dtm;

//    private static final int area[] = {1,3};
    public GeneralSubAccount1() {
        initComponents();
        levels = new AccountLevels();
        initializeResources();
        BasicInternalFrameUI frameUI = (BasicInternalFrameUI) this.getUI();
        frameUI.setNorthPane(null);
        dtm = (DefaultTableModel) Table.getModel();
        TableColumnFontChanger.setAmalee(Table, 3);
        TableColumnFontChanger.setIskolaPotha(Table, 4);
        loadDataTCcmbMainAccLevel();
        ComboBoxForeGroundColorFixer.fixColor(cmb_Main_Acc_Level, 1);
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
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmb_Main_Acc_Level = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new org.jdesktop.swingx.JXTable();
        txtMainAccNo = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNoteNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDescriptionSin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescriptionEng = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNo1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNo2 = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.white);

        jPanel2.setBackground(new java.awt.Color(0, 88, 136));
        jPanel2.setPreferredSize(new java.awt.Dimension(5, 50));

        jLabel10.setBackground(new java.awt.Color(0, 88, 136));
        jLabel10.setFont(new java.awt.Font("Iskoola Pota", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("මුලික ශිර්ෂ ලේජර සම්බන්ධතා");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Account Level 1 :");

        cmb_Main_Acc_Level.setFont(new java.awt.Font("Iskoola Pota", 1, 16)); // NOI18N
        cmb_Main_Acc_Level.setForeground(new java.awt.Color(6, 53, 144));
        cmb_Main_Acc_Level.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_Main_Acc_LevelItemStateChanged(evt);
            }
        });
        cmb_Main_Acc_Level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_Main_Acc_LevelActionPerformed(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        Table.setForeground(new java.awt.Color(128, 0, 0));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Sub Account No", "Account No", "Main Account Des", "Main Account Des Uni", "TBL Order", "PNL Order", "Note No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Table.setRowHeight(25);
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setMinWidth(50);
            Table.getColumnModel().getColumn(0).setPreferredWidth(50);
            Table.getColumnModel().getColumn(0).setMaxWidth(50);
            Table.getColumnModel().getColumn(1).setMinWidth(0);
            Table.getColumnModel().getColumn(1).setPreferredWidth(0);
            Table.getColumnModel().getColumn(1).setMaxWidth(0);
            Table.getColumnModel().getColumn(2).setMinWidth(150);
            Table.getColumnModel().getColumn(2).setPreferredWidth(150);
            Table.getColumnModel().getColumn(2).setMaxWidth(150);
            Table.getColumnModel().getColumn(3).setMinWidth(250);
            Table.getColumnModel().getColumn(3).setPreferredWidth(250);
            Table.getColumnModel().getColumn(3).setMaxWidth(500);
            Table.getColumnModel().getColumn(4).setMinWidth(250);
            Table.getColumnModel().getColumn(4).setPreferredWidth(250);
            Table.getColumnModel().getColumn(4).setMaxWidth(500);
        }

        txtMainAccNo.setEditable(false);
        txtMainAccNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMainAccNo.setForeground(new java.awt.Color(190, 121, 0));
        txtMainAccNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSave.setBackground(new java.awt.Color(52, 112, 150));
        btnSave.setFont(new java.awt.Font("Iskoola Pota", 1, 11)); // NOI18N
        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.setOpaque(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ගිණුම් අංකය :");

        jLabel5.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("සටහන් අංකය :");

        txtNoteNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNoteNo.setText("0.00");
        txtNoteNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNoteNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoteNoFocusLost(evt);
            }
        });
        txtNoteNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoteNoActionPerformed(evt);
            }
        });
        txtNoteNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoteNoKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("ගිණුම් විස්තරය :");

        txtDescriptionSin.setFont(new java.awt.Font("AMALEE", 1, 16)); // NOI18N
        txtDescriptionSin.setForeground(new java.awt.Color(190, 121, 0));
        txtDescriptionSin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDescriptionSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescriptionSinActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ගිණුම් විස්තරය(Eng) :");

        txtDescriptionEng.setFont(new java.awt.Font("Iskoola Pota", 1, 16)); // NOI18N
        txtDescriptionEng.setForeground(new java.awt.Color(190, 121, 0));
        txtDescriptionEng.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDescriptionEng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescriptionEngActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ශේෂ පත්‍රයේ පිලිවෙල :");

        txtNo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNo1.setText("0.00");
        txtNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNo1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNo1FocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("ශේෂ පිරික්සුමේ පිලිවෙල :");

        txtNo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNo2.setText("0.00");
        txtNo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNo2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNo2FocusLost(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Iskoola Pota", 1, 11)); // NOI18N
        btnNew.setText("New");
        btnNew.setEnabled(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(523, 523, 523)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNo2))
                            .addComponent(txtDescriptionEng)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMainAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNoteNo))
                            .addComponent(txtDescriptionSin))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_Main_Acc_Level, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(258, 258, 258))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_Main_Acc_Level, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoteNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMainAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtDescriptionSin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtDescriptionEng)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNo2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(txtNo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(603, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_Main_Acc_LevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_Main_Acc_LevelActionPerformed
        selectcmb1();
    }//GEN-LAST:event_cmb_Main_Acc_LevelActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        if (Table.getSelectedRow() > -1) {
            int selectedRow = Table.getSelectedRow();

            txtMainAccNo.setText(Table.getValueAt(selectedRow, 2).toString());
            txtNoteNo.setText(Table.getValueAt(selectedRow, 7).toString());
            txtDescriptionSin.setText(Table.getValueAt(selectedRow, 3).toString());
            txtDescriptionEng.setText(Table.getValueAt(selectedRow, 4).toString());
            txtNo1.setText(Table.getValueAt(selectedRow, 6).toString());
            txtNo2.setText(Table.getValueAt(selectedRow, 5).toString());

            levels.setAcctLevelID2(Table.getValueAt(Table.getSelectedRow(), 0).toString());
            btnSave.setEnabled(true);
        }
    }//GEN-LAST:event_TableMouseClicked

    private void TableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseReleased

    }//GEN-LAST:event_TableMouseReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        List<Object> outputs = SaveSubAccounts.saveSubAccount(2, levels.getAcctLevelID2(), levels.getAcctLevelID1(), txtMainAccNo.getText(), txtDescriptionSin.getText(), txtDescriptionEng.getText(), Double.parseDouble(txtNo2.getText()), Double.parseDouble(txtNo1.getText()), Double.parseDouble(txtNoteNo.getText()), 2);
        txtDescriptionEng.setText("");
        txtDescriptionSin.setText("");
        txtMainAccNo.setText("");
        txtNo1.setText("0.00");
        txtNo2.setText("0.00");
        txtNoteNo.setText("0.00");
        selectcmb1();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cmb_Main_Acc_LevelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_Main_Acc_LevelItemStateChanged

    }//GEN-LAST:event_cmb_Main_Acc_LevelItemStateChanged

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        List<Object> outputs = SaveSubAccounts.saveSubAccount(2, "0", levels.getAcctLevelID1(), "", "", "", 0, 0, 0, 2);
        txtDescriptionEng.setText("");
        txtDescriptionSin.setText("");
        txtMainAccNo.setText("");
        txtNo1.setText("0.00");
        txtNo2.setText("0.00");
        txtNoteNo.setText("0.00");
        txtMainAccNo.setText(outputs.get(1).toString());
        jLabel3.setText(outputs.get(0).toString());
        levels.setAcctLevelID2(outputs.get(0).toString());
        btnNew.setEnabled(false);
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtNoteNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoteNoKeyReleased

    }//GEN-LAST:event_txtNoteNoKeyReleased

    private void txtNoteNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoteNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoteNoActionPerformed

    private void txtNoteNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoteNoFocusLost
        if ("".equals(txtNoteNo.getText())) {
            txtNoteNo.setText("0.00");
        }
    }//GEN-LAST:event_txtNoteNoFocusLost

    private void txtNo1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNo1FocusLost
        if ("".equals(txtNo1.getText())) {
            txtNo1.setText("0.00");
        }
    }//GEN-LAST:event_txtNo1FocusLost

    private void txtNo2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNo2FocusLost
        if ("".equals(txtNo2.getText())) {
            txtNo2.setText("0.00");
        }
    }//GEN-LAST:event_txtNo2FocusLost

    private void txtDescriptionSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionSinActionPerformed
        java.awt.Container c = this.getParent();
        while (!(c instanceof javax.swing.JFrame) && (c != null)) {
            c = c.getParent();
        }
        JFrame owner = null;
        if (c != null) {
            owner = (javax.swing.JFrame) c;

        }
        new AmaliKeyBoard(owner, true, txtDescriptionSin, txtDescriptionEng,false).setVisible(true);
    }//GEN-LAST:event_txtDescriptionSinActionPerformed

    private void txtDescriptionEngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionEngActionPerformed
        java.awt.Container c = this.getParent();
        while (!(c instanceof javax.swing.JFrame) && (c != null)) {
            c = c.getParent();
        }
        JFrame owner = null;
        if (c != null) {
            owner = (javax.swing.JFrame) c;

        }
        new UnicodeKeyBoard(owner, true, txtDescriptionSin, txtDescriptionEng,false).setVisible(true);
    }//GEN-LAST:event_txtDescriptionEngActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralSubAccount1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTable Table;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmb_Main_Acc_Level;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDescriptionEng;
    private javax.swing.JTextField txtDescriptionSin;
    private javax.swing.JTextField txtMainAccNo;
    private javax.swing.JTextField txtNo1;
    private javax.swing.JTextField txtNo2;
    private javax.swing.JTextField txtNoteNo;
    // End of variables declaration//GEN-END:variables

    private void refreshTree() {
        GenerateJTree fgjt = new GenerateJTree();
        GeneralTreeControl.treePanel.removeAll();
        fgjt.setVisible(true);
        fgjt.setSize(GeneralTreeControl.treePanel.getSize());
        GeneralTreeControl.treePanel.add(fgjt);
    }

    private void loadDataTCcmbMainAccLevel() {
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(1);
            inputs.add(0);

            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_bnk_LoadCombo_ActTree", 4);
            Vector v = new Vector();
            for (List<Object> output : outputs) {
                v.add(output.get(1) + " " + output.get(3));
            }
            cmb_Main_Acc_Level.setModel(new DefaultComboBoxModel(v));
        } catch (RemoteException ex) {
            Logger.getLogger(GeneralSubAccount1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSubAccount1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSubAccount1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeResources() {
        jLabel10.setText(textBundle.getTextBundle().getString("mainHeaderGL"));
        jLabel1.setText(textBundle.getTextBundle().getString("acctL1"));
        jLabel4.setText(textBundle.getTextBundle().getString("accNo"));
        jLabel5.setText(textBundle.getTextBundle().getString("noteNo"));
        jLabel6.setText(textBundle.getTextBundle().getString("acctDesc"));
        jLabel7.setText(textBundle.getTextBundle().getString("acctDesc") + "(Eng)");
        jLabel8.setText(textBundle.getTextBundle().getString("pnlOrder"));
        jLabel9.setText(textBundle.getTextBundle().getString("tblOrder"));
        btnNew.setText(textBundle.getTextBundle().getString("new"));
        btnSave.setText(textBundle.getTextBundle().getString("save"));
    }

    private void selectcmb1() {
        try {
            dtm.setRowCount(0);
            List<Object> inputs = new ArrayList<>();
            inputs.add(6);
            inputs.add(cmb_Main_Acc_Level.getSelectedItem().toString().split(" ")[0]);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_bnk_LoadCombo_ActTree", 8);
            for (List<Object> output : outputs) {
                Object[] rowData = output.toArray();
                dtm.addRow(rowData);
            }
            Table.packAll();
            btnNew.setEnabled(true);
            levels.setAcctLevelID1(cmb_Main_Acc_Level.getSelectedItem().toString().split(" ")[0]);
        } catch (RemoteException ex) {
            Logger.getLogger(GeneralSubAccount1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralSubAccount1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralSubAccount1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}