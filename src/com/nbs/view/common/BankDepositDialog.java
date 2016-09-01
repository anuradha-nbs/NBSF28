/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common;

import Sources.TableColumnFontChanger;
import com.nbs.impl.ServerConnection;
import com.nbs.view.F28.Payments.CommonCashReceiptCash;
import static com.nbs.view.F28.Payments.CommonCashReceiptCash.txtReasonorGlAccount;
import static com.nbs.view.F28.Payments.CommonCashReceiptCash.txtReasonorGlAccount1;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmh
 */
public class BankDepositDialog extends javax.swing.JDialog {

    private DefaultTableModel dtm;
    private String hdid;
    private int glid;
    private String glText;
    private String Date;
    private String code;

    /**
     * Creates new form BankDepositDialog
     */
    public BankDepositDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dtm = (DefaultTableModel) jXTable1.getModel();
//        FixedColumnTable fixedColumnTable = new FixedColumnTable(1, jScrollPane1);
    }

    public BankDepositDialog(JDialog parent, boolean modal, String hdid, int glid, String glText, String Date, String code) {
        super(parent, modal);
        initComponents();
        this.hdid = hdid;
        this.glid = glid;
        this.glText = glText;
        this.Date = Date;
        this.code = code;
        dtm = (DefaultTableModel) jXTable1.getModel();
        dtm.setColumnIdentifiers(new String[]{"#","dTrnDate", "cDescriptionUni_L5", "bCheque", "cRemarks", "nCr", "n5000", "n2000", "n1000", "n500", "n100", "n50", "n20", "n10", "nCoines", "nDTID"});
        loadTable(hdid, glid, glText, Date);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblDetails = new javax.swing.JLabel();
        optAllCA = new javax.swing.JCheckBox();
        optAllDays = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jLabel2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 81, 136));

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("මුදල් බැංකුගත කිරීම");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(254, 217, 179));

        lblDetails.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N

        optAllCA.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        optAllCA.setText("සියලු ජංගම ගිණුම්");
        optAllCA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                optAllCAItemStateChanged(evt);
            }
        });
        optAllCA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optAllCAStateChanged(evt);
            }
        });
        optAllCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAllCAActionPerformed(evt);
            }
        });
        optAllCA.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                optAllCAPropertyChange(evt);
            }
        });

        optAllDays.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        optAllDays.setText("සියලු දින");
        optAllDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAllDaysActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optAllCA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optAllDays)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optAllCA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(optAllDays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "", "Title 2", "Title 3", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jXTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jXTable1);
        if (jXTable1.getColumnModel().getColumnCount() > 0) {
            jXTable1.getColumnModel().getColumn(0).setMinWidth(20);
            jXTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jXTable1.getColumnModel().getColumn(0).setMaxWidth(20);
            jXTable1.getColumnModel().getColumn(14).setMinWidth(0);
            jXTable1.getColumnModel().getColumn(14).setPreferredWidth(0);
            jXTable1.getColumnModel().getColumn(14).setMaxWidth(0);
            jXTable1.getColumnModel().getColumn(15).setMinWidth(0);
            jXTable1.getColumnModel().getColumn(15).setPreferredWidth(0);
            jXTable1.getColumnModel().getColumn(15).setMaxWidth(0);
        }

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel2.setText("Total :");

        lblTotal.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optAllCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAllCAActionPerformed
        loadTable(hdid, glid, glText, Date);
    }//GEN-LAST:event_optAllCAActionPerformed

    private void optAllDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAllDaysActionPerformed
        loadTable(hdid, glid, glText, Date);
    }//GEN-LAST:event_optAllDaysActionPerformed

    private void optAllCAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optAllCAStateChanged

    }//GEN-LAST:event_optAllCAStateChanged

    private void optAllCAPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_optAllCAPropertyChange

    }//GEN-LAST:event_optAllCAPropertyChange

    private void optAllCAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optAllCAItemStateChanged
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            loadTable(hdid, glid, glText, Date);
//        } else {
//            loadTable(hdid, glid, glText, Date);
//        }
    }//GEN-LAST:event_optAllCAItemStateChanged
    private int dtid = 0;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (Boolean.parseBoolean(String.valueOf(jXTable1.getValueAt(i, 0)))) {
                try {
                    List<Object> inputs = new ArrayList<>();
                    inputs.add(dtid);
                    inputs.add(hdid);
                    inputs.add(CommonCashReceiptCash.txtSubSection.getId());
                    inputs.add(CommonCashReceiptCash.txtOutSider.getId());
                    inputs.add(CommonCashReceiptCash.statusGLCode.getText());
                    if (code.equals("02-01")) {
                        inputs.add(CommonCashReceiptCash.txtReasonorGlAccount.getId());
                    } else {
                        inputs.add(4);
                    }
                    inputs.add(Boolean.parseBoolean(String.valueOf(dtm.getValueAt(i, 3))));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 4)));
                    if (code.equals("02-03")) {
                        inputs.add(CommonCashReceiptCash.txtReasonorGlAccount.getId());
                    } else {
                        inputs.add(CommonCashReceiptCash.txtReasonorGlAccount1.getId());
                    }
                    inputs.add("");
                    switch (code) {
                        case "02-01":
                            inputs.add(FormatConstants.numberFormat1.parse(String.valueOf(dtm.getValueAt(i, 5))).doubleValue());
                            inputs.add(0);
                            inputs.add(FormatConstants.numberFormat1.parse(String.valueOf(dtm.getValueAt(i, 5))).doubleValue());
                            break;
                        case "02-03":
                            inputs.add(FormatConstants.numberFormat1.parse(String.valueOf(dtm.getValueAt(i, 5))).doubleValue());
                            inputs.add(FormatConstants.numberFormat1.parse(String.valueOf(dtm.getValueAt(i, 5))).doubleValue());
                            inputs.add(0);
                            break;
                    }
                    inputs.add(String.valueOf(dtm.getValueAt(i, 6)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 7)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 8)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 9)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 10)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 11)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 12)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 13)));
                    inputs.add(String.valueOf(dtm.getValueAt(i, 14)));
                    inputs.add(0);
                    inputs.add(0);
                    inputs.add(GeneralUserLogin.data.getUsername());
                    if (code.equals("02-01")) {
                        if (txtReasonorGlAccount.getId() != 0 && txtReasonorGlAccount1.getId() != 0) {
                            ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranDT", 0);
                            dtid = 0;
                        } else {
                            JOptionPane.showMessageDialog(this, "කාරණය හෝ ගිණුම් විස්තරය ඇතුලත් කර නොමැත");
                            txtReasonorGlAccount.grabFocus();
                        }
                    } else if (code.equals("02-03")) {
                        if (txtReasonorGlAccount.getId() != 0) {
                            ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranDT", 0);
                            dtid = 0;
                        } else {
                            JOptionPane.showMessageDialog(this, "කාරණය හෝ ගිණුම් විස්තරය ඇතුලත් කර නොමැත");
                            txtReasonorGlAccount.grabFocus();
                        }
                    }
                    List in = new ArrayList<>();
                    in.add(String.valueOf(dtm.getValueAt(i, 15)));
                    ServerConnection.getServerConnector().insertData(in, "ssp_F28_Insert_CashierTranDT_Banked", 0);
                } catch (RemoteException ex) {
                    Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BankDepositDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankDepositDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankDepositDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankDepositDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BankDepositDialog dialog = new BankDepositDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JLabel lblDetails;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JCheckBox optAllCA;
    private javax.swing.JCheckBox optAllDays;
    // End of variables declaration//GEN-END:variables

    private void loadTable(String hdid, int glid, String glText, String Date) {
        try {
//            initComponents();
//            dtm = (DefaultTableModel) jXTable1.getModel();
            dtm.setRowCount(0);
            TableColumnFontChanger.setIskolaPotha(jXTable1, 1);
            setLocationRelativeTo(null);
            lblDetails.setText("මුදල් තැන්පත් කිරීම් " + hdid + " සඳහා ජංගම ගිණුම් අංක " + glText + "(" + glid + ")");
            List<Object> inputs = new ArrayList<>();
            inputs.add(Date);

            if (optAllCA.isSelected()) {
                inputs.add(0);
            } else {
                inputs.add(glid);
            }
            if (optAllDays.isSelected()) {
                inputs.add(1);
            } else {
                inputs.add(0);
            }
            System.out.println("kv" + inputs);
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewCashierPending", 15);
            for (List<Object> output : outputs) {
                Vector v = new Vector();
                v.add(false);
                for (Object object : output) {
                    v.add(object);
                }
                dtm.addRow(v);
            }
            jXTable1.packAll();
            getTotal();
//            FixedColumnTable fixedColumnTable = new FixedColumnTable(1, jScrollPane1);
        } catch (SQLException ex) {
            Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getTotal() {
        int rowCount = dtm.getRowCount();
        double total = 0;
        for (int i = 0; i < rowCount; i++) {
            try {
                total += FormatConstants.decimalFormat.parse(String.valueOf(dtm.getValueAt(i, 5))).doubleValue();
            } catch (ParseException ex) {
                Logger.getLogger(BankDepositDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lblTotal.setText(FormatConstants.decimalFormat.format(total));
    }
}
