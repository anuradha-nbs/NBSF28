/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import static Resources.SelectLang.textBundle;
import com.nbs.view.manager.GL.AccountLevels;
import com.nbs.view.manager.GL.GLAccountsTransfer;
import com.nbs.view.manager.GL.GeneralSubAccount1;
import com.nbs.view.manager.GL.GeneralSubAccount2;
import com.nbs.view.manager.GL.GeneralSubAccount3;
import com.nbs.view.manager.GL.GeneralSubAccount4;

/**
 *
 * @author mmh
 */
public class GeneralTreeControl extends javax.swing.JFrame {

    /**
     * Creates new form GeneralTreeControl
     */
    DefaultTreeModel dtm;
    DefaultMutableTreeNode node;
    public static AccountLevels levels;

    public GeneralTreeControl() {
        initComponents();
        initializeResources();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        GenerateJTree fgjt = new GenerateJTree();
        treePanel.removeAll();
//
        fgjt.setSize(treePanel.getWidth(), treePanel.getHeight());
        treePanel.add(fgjt);
        fgjt.setVisible(true);
        levels = new AccountLevels();
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
        MainHeader = new javax.swing.JButton();
        SubHeader = new javax.swing.JButton();
        Header = new javax.swing.JButton();
        Header2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        treePanel = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButton14 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jXStatusBar2 = new org.jdesktop.swingx.JXStatusBar();
        jLabel13 = new javax.swing.JLabel();
        statusMFID = new javax.swing.JLabel();
        statusGLID = new javax.swing.JLabel();
        clockLabel1 = new org.nbs.components.ClockLabel("hh:mm:ss a");
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(137, 201, 250));

        MainHeader.setBackground(new java.awt.Color(14, 91, 47));
        MainHeader.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        MainHeader.setForeground(java.awt.Color.white);
        MainHeader.setText("මූලික ශීර්ෂය");
        MainHeader.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MainHeader.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        MainHeader.setOpaque(true);
        MainHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainHeaderActionPerformed(evt);
            }
        });

        SubHeader.setBackground(new java.awt.Color(14, 91, 47));
        SubHeader.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        SubHeader.setForeground(java.awt.Color.white);
        SubHeader.setText("උප ශීර්ෂය");
        SubHeader.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubHeader.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SubHeader.setOpaque(true);
        SubHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubHeaderActionPerformed(evt);
            }
        });

        Header.setBackground(new java.awt.Color(14, 91, 47));
        Header.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        Header.setForeground(java.awt.Color.white);
        Header.setText("ශීර්ෂය");
        Header.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Header.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Header.setOpaque(true);
        Header.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeaderActionPerformed(evt);
            }
        });

        Header2.setBackground(new java.awt.Color(14, 91, 47));
        Header2.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        Header2.setForeground(java.awt.Color.white);
        Header2.setText("ශීර්ෂය2");
        Header2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Header2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Header2.setOpaque(true);
        Header2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Header2ActionPerformed(evt);
            }
        });

        jButton7.setBackground(java.awt.Color.white);
        jButton7.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton7.setText("ගිණුම්");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(java.awt.Color.white);
        jButton8.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton8.setText("වෙනස් කිරිම");
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(java.awt.Color.white);
        jButton9.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton9.setText("වාර්තාව");
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(java.awt.Color.white);
        jButton10.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton10.setText("වාර්තාව 1");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(java.awt.Color.white);
        jButton11.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton11.setText("වාර්තාව 2");
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(java.awt.Color.white);
        jButton12.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton12.setText("වාර්තාව 3");
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(java.awt.Color.white);
        jButton13.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton13.setText("වාර්තාව 4");
        jButton13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        treePanel.setBackground(new java.awt.Color(137, 201, 250));
        treePanel.setLayout(new java.awt.GridLayout(1, 0));

        jDesktopPane1.setBackground(java.awt.Color.white);

        jButton14.setBackground(java.awt.Color.white);
        jButton14.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton14.setText("Transfer GL");
        jButton14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Iskoola Pota", 1, 13)); // NOI18N
        jButton1.setText("Open GL Balance");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(MainHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(SubHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(Header2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(treePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDesktopPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(MainHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Header2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );

        jXStatusBar2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(253, 16, 4));
        jLabel13.setText("ONLINE");

        statusMFID.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        statusMFID.setForeground(new java.awt.Color(253, 16, 4));

        statusGLID.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        statusGLID.setForeground(new java.awt.Color(253, 16, 4));

        clockLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        clockLabel1.setForeground(new java.awt.Color(11, 35, 121));

        javax.swing.GroupLayout jXStatusBar2Layout = new javax.swing.GroupLayout(jXStatusBar2);
        jXStatusBar2.setLayout(jXStatusBar2Layout);
        jXStatusBar2Layout.setHorizontalGroup(
            jXStatusBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(32, 32, 32)
                .addComponent(statusMFID, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(statusGLID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clockLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jXStatusBar2Layout.setVerticalGroup(
            jXStatusBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusMFID, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addComponent(statusGLID, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addComponent(clockLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXStatusBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXStatusBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MainHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainHeaderActionPerformed
        GeneralSubAccount1 account1 = new GeneralSubAccount1();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(account1);
        account1.setSize(jDesktopPane1.getSize());
        account1.setVisible(true);
    }//GEN-LAST:event_MainHeaderActionPerformed

    private void SubHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubHeaderActionPerformed
        GeneralSubAccount2 account2 = new GeneralSubAccount2();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(account2);
        account2.setSize(jDesktopPane1.getSize());
        account2.setVisible(true);
    }//GEN-LAST:event_SubHeaderActionPerformed

    private void HeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeaderActionPerformed
        GeneralSubAccount3 account3 = new GeneralSubAccount3();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(account3);
        account3.setSize(jDesktopPane1.getSize());
        account3.setVisible(true);
    }//GEN-LAST:event_HeaderActionPerformed

    private void Header2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Header2ActionPerformed
        GeneralSubAccount4 account4 = new GeneralSubAccount4();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(account4);
        account4.setSize(jDesktopPane1.getSize());
        account4.setVisible(true);
    }//GEN-LAST:event_Header2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new GLAccountsTransfer().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new GLOpeningBalance().setVisible(true);
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

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralTreeControl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Header;
    private javax.swing.JButton Header2;
    private javax.swing.JButton MainHeader;
    private javax.swing.JButton SubHeader;
    private org.nbs.components.ClockLabel clockLabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar2;
    private javax.swing.JLabel statusGLID;
    private javax.swing.JLabel statusMFID;
    public static javax.swing.JPanel treePanel;
    // End of variables declaration//GEN-END:variables

    private void initializeResources() {
     MainHeader.setText(textBundle.getTextBundle().getString("MainHeader"));
     SubHeader.setText(textBundle.getTextBundle().getString("SubHeader"));
     Header.setText(textBundle.getTextBundle().getString("Header"));
     Header2.setText(textBundle.getTextBundle().getString("Header2"));
     jButton7.setText(textBundle.getTextBundle().getString("Accounts"));
     jButton8.setText(textBundle.getTextBundle().getString("edit"));
     jButton9.setText(textBundle.getTextBundle().getString("report"));
     jButton10.setText(textBundle.getTextBundle().getString("report")+"1");
     jButton11.setText(textBundle.getTextBundle().getString("report")+"2");
     jButton12.setText(textBundle.getTextBundle().getString("report")+"3");
     jButton13.setText(textBundle.getTextBundle().getString("report")+"4");
    }
}
