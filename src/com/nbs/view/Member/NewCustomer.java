/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.Member;

import com.nbs.impl.ServerConnection;
import static com.nbs.view.Member.NewCustomer1.*;
import static com.nbs.view.Member.NewCustomer2.*;
import com.nbs.view.common.GeneralUserLogin;
import com.toedter.calendar.JDateChooser;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import org.nbs.resources.Item;

/**
 *
 * @author mmh
 */
public class NewCustomer extends javax.swing.JFrame {

    static JPanel frames[] = new JPanel[3];
    private int visibleFrame = -1;
    private int previousFrame = -1;
    private int nextFrame;
    private int type = 0;
    private SimpleDateFormat df;
    public static long customerID;
    public static String CIFNo;
    public static String MFID = "0";

    public static String getCIFNo() {
        return CIFNo;
    }

    public static void setCIFNo(String CIFNo) {
        NewCustomer.CIFNo = CIFNo;
    }

    /**
     * Creates new form NewCustomer
     */
    public NewCustomer() {
        initComponents();
        frames[0] = new NewCustomer1();
        frames[1] = new NewCustomer2();
        frames[2] = new NewCustomer3();
        changeNextFrame();
        initDates();
        finish.setVisible(false);
        loadCombos();
    }

    public NewCustomer(int type) {

        this();
        this.type = type;

    }

    public NewCustomer(String MFID) {
        this();
        NewCustomer.MFID = MFID;
        jButton1.setEnabled(false);
        List<Object> inputs;
        inputs = new ArrayList<Object>();
        
        try {
            inputs.add(8);
            inputs.add(MFID);
            List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_Mem_LoadCombo_MemberMaster", 45);
            NewCustomer.MFID = String.valueOf(outputs.get(0));
            ((DefaultComboBoxModel) cmbShop.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(1).toString()), outputs.get(2).toString()));
            txtMemNo.setText(String.valueOf(outputs.get(3)));
            txtF10No.setText(String.valueOf(outputs.get(4)));
            txtMemFee.setText(String.valueOf(outputs.get(5)));
            jdtMemDate.setDate(df.parse(String.valueOf(outputs.get(6))));
            txtNIC.setText(String.valueOf(outputs.get(7)));
            txtFullNameAma.setText(String.valueOf(outputs.get(8)));
            txtAddress_1.setText(String.valueOf(outputs.get(9)));
            txtAddress_2.setText(String.valueOf(outputs.get(10)));
            txtAddress_3.setText(String.valueOf(outputs.get(11)));
            txtAddress_4.setText(String.valueOf(outputs.get(12)));
            txtCIFNo.setText(String.valueOf(outputs.get(13)));
            txtLastName.setText(String.valueOf(outputs.get(14)));
            txtPreferredName.setText(String.valueOf(outputs.get(15)));
            jdtNIC_Isu.setDate(df.parse(String.valueOf(outputs.get(17))));
            if (Integer.parseInt(String.valueOf(outputs.get(18))) == 1) {
                optMale.setSelected(true);
            }else{
                optFemale.setSelected(true);
            }
            txtTel_1.setText(String.valueOf(outputs.get(19)));
            txtTel_2.setText(String.valueOf(outputs.get(20)));
            txtEmail.setText(String.valueOf(outputs.get(21)));
            txtRsAddress_1.setText(String.valueOf(outputs.get(22)));
            txtRsAddress_2.setText(String.valueOf(outputs.get(23)));
            txtRsAddress_3.setText(String.valueOf(outputs.get(24)));
            txtRsAddress_4.setText(String.valueOf(outputs.get(25)));
            txtRsTel_1.setText(String.valueOf(outputs.get(26)));
            txtRsTel_2.setText(String.valueOf(outputs.get(27)));
            txtRsEmail.setText(String.valueOf(outputs.get(28)));
            ((DefaultComboBoxModel) cmbMemStatus.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(33).toString()), outputs.get(34).toString()));
            ((DefaultComboBoxModel) cmbPosition.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(35).toString()), outputs.get(36).toString()));
            ((DefaultComboBoxModel) cmbCivilStatus.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(37).toString()), outputs.get(38).toString()));
            ((DefaultComboBoxModel) cmbNationality.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(39).toString()), outputs.get(40).toString()));
            ((DefaultComboBoxModel) cmbReligion.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(41).toString()), outputs.get(42).toString()));
            ((DefaultComboBoxModel) cmbTitle.getModel()).setSelectedItem(new Item(Integer.parseInt(outputs.get(43).toString()), outputs.get(44).toString()));
            
        } catch (RemoteException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel3 = new javax.swing.JPanel();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        online = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        finish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jXStatusBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        online.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        online.setForeground(new java.awt.Color(253, 16, 4));
        online.setText("ONLINE");

        javax.swing.GroupLayout jXStatusBar1Layout = new javax.swing.GroupLayout(jXStatusBar1);
        jXStatusBar1.setLayout(jXStatusBar1Layout);
        jXStatusBar1Layout.setHorizontalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXStatusBar1Layout.createSequentialGroup()
                .addComponent(online, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(937, 937, 937))
        );
        jXStatusBar1Layout.setVerticalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(online, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(179, 209, 217));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.black);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a2.png"))); // NOI18N
        jLabel1.setText("පාරිභෝගික තොරතුරු - පුද්ගල");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(332, 96));

        viewPanel.setBackground(java.awt.Color.white);
        viewPanel.setMinimumSize(new java.awt.Dimension(1082, 471));
        viewPanel.setLayout(new java.awt.GridLayout(1, 0));

        previous.setBackground(new java.awt.Color(209, 124, 63));
        previous.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        previous.setForeground(java.awt.Color.white);
        previous.setText("ආපසු");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setBackground(new java.awt.Color(209, 124, 63));
        next.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        next.setForeground(java.awt.Color.white);
        next.setText("ඉදිරියට");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        finish.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        finish.setText("අවසන් කරන්න");
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                    .addComponent(viewPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 822, Short.MAX_VALUE)
                        .addComponent(finish)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previous)
                    .addComponent(next)
                    .addComponent(finish)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 687, Short.MAX_VALUE)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(26, 26, 26)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1134, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );

        setSize(new java.awt.Dimension(1144, 749));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed

        if (type == 2 && getVisibleFrame() == 0) {
            editFirstSave();
        } else if (getVisibleFrame() == 1) {
            saveSecondSave();
        } else if (getVisibleFrame() == 2) {
            saveThirdSave();
        }
        changeNextFrame();
    }//GEN-LAST:event_nextActionPerformed

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        changePreviousFrame();
    }//GEN-LAST:event_previousActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
        this.dispose();
    }//GEN-LAST:event_finishActionPerformed

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
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton finish;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    public static javax.swing.JButton next;
    public static javax.swing.JLabel online;
    public static javax.swing.JButton previous;
    public static javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

    public int getVisibleFrame() {
        return visibleFrame;
    }

    public void setVisibleFrame(int visibleFrame) {
        this.visibleFrame = visibleFrame;
    }

    public int getPreviousFrame() {
        return previousFrame;
    }

    public void setPreviousFrame(int previousFrame) {
        this.previousFrame = previousFrame;
    }

    public int getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(int nextFrame) {
        this.nextFrame = nextFrame;
    }

    private void changeNextFrame() {
        int vf = getVisibleFrame();
        int pf = getPreviousFrame();
        int nf = getNextFrame();

        vf++;
        pf++;
        nf++;

        if (vf < 0) {
            vf = 0;
        } else if (vf == 2) {
            previous.setVisible(false);
            next.setVisible(false);
            finish.setVisible(true);
            vf = 1;
        }
        if (pf < 0) {
            pf = 0;
        }
        if (nf <= 0) {
            nf = 1;
        }

        setVisibleFrame(vf);
        setPreviousFrame(pf);
        setNextFrame(nf);

        viewPanel.removeAll();
        frames[getVisibleFrame()].setVisible(true);
        viewPanel.add(frames[getVisibleFrame()]);
        viewPanel.repaint();
        viewPanel.revalidate();

//        if (rootPaneCheckingEnabled) {
//            
//        }
    }

    private void changePreviousFrame() {
        int vf = getVisibleFrame();
        int pf = getPreviousFrame();
        int nf = getNextFrame();

        vf--;
        pf--;
        nf--;

        if (vf < 0) {
            vf = 0;
        }
        if (pf < 0) {
            pf = 0;
        }
        if (nf < 1) {
            nf = 1;
        }

        setVisibleFrame(vf);
        setPreviousFrame(pf);
        setNextFrame(nf);

        viewPanel.removeAll();
        frames[getVisibleFrame()].setVisible(true);
        viewPanel.add(frames[getVisibleFrame()]);
        viewPanel.repaint();
        viewPanel.revalidate();
    }

    private void initDates() {
        df = new SimpleDateFormat("yyyy-MM-dd");
        JDateChooser dateChoosers[] = {jdtDOB, jdtJoin, jdtMemDate, jdtNIC_Isu};
        for (JDateChooser dateChooser : dateChoosers) {
            dateChooser.setDate(new Date());
        }

    }

    public static long getCustomerID() {
        return customerID;
    }

    public static void setCustomerID(long customerID) {
        NewCustomer.customerID = customerID;
    }

    private void setSearchData(List<Object> outputs) {

    }

    private void editFirstSave() {
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(0);
            inputs.add(cmbShop.getSelectedItem().getId());
            inputs.add(txtNIC.getText());
            inputs.add(df.format(jdtNIC_Isu.getDate()));
            inputs.add(df.format(jdtDOB.getDate()));
            inputs.add(txtFullNameAma.getText());
            inputs.add(cmbTitle.getSelectedItem().getId());
            inputs.add(txtPreferredName.getText());
            inputs.add(txtInitials.getText());
            inputs.add(txtLastName.getText());
            if (optMale.isSelected()) {
                inputs.add(1);
            } else {
                inputs.add(0);
            }
            inputs.add(df.format(jdtJoin.getDate()));
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add("@primaryID");
            inputs.add("@cifNo");
            
            ServerConnection.getServerConnector().insertData(inputs, "ssp_Mem_Insert_MemberMaster_1", 2);
            
        } catch (RemoteException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveSecondSave() {
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(CIFNo);
            inputs.add(txtAddress_1.getText());
            inputs.add(txtAddress_2.getText());
            inputs.add(txtAddress_3.getText());
            inputs.add(txtAddress_4.getText());
            inputs.add(txtTel_1.getText());
            inputs.add(txtTel_2.getText());
            inputs.add(txtEmail.getText());
            inputs.add(txtRsAddress_1.getText());
            inputs.add(txtRsAddress_2.getText());
            inputs.add(txtRsAddress_3.getText());
            inputs.add(txtRsAddress_4.getText());
            inputs.add(txtRsTel_1.getText());
            inputs.add(txtRsTel_2.getText());
            inputs.add(txtRsEmail.getText());
            inputs.add(cmbCivilStatus.getSelectedItem().getId());
            inputs.add(cmbNationality.getSelectedItem().getId());
            inputs.add(cmbReligion.getSelectedItem().getId());
            inputs.add(txtMemNo.getText());
            inputs.add(df.format(jdtMemDate.getDate()));
            inputs.add(cmbMemStatus.getSelectedItem().getId());
            inputs.add(cmbPosition.getSelectedItem().getId());
            inputs.add(txtMemFee.getText());
            inputs.add(txtF10No.getText());
            inputs.add(GeneralUserLogin.data.getUsername());

            ServerConnection.getServerConnector().insertData(inputs, "ssp_Mem_Insert_MemberMaster_2", 0);

        } catch (RemoteException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveThirdSave() {

    }

    private void setImages() {

    }

    private void loadCombos() {
        List<Object> inputs = new ArrayList<>();
        List<List<Object>> outputs;
        try {
            //Area
            inputs.add(1);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            Vector v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer1.cmbShop.setModel(new DefaultComboBoxModel<>(v));
            //Title
            inputs.clear();
            inputs.add(2);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer1.cmbTitle.setModel(new DefaultComboBoxModel<>(v));
            //Civil Status
            inputs.clear();
            inputs.add(3);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer2.cmbCivilStatus.setModel(new DefaultComboBoxModel<>(v));
            //Nationality
            inputs.clear();
            inputs.add(4);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer2.cmbNationality.setModel(new DefaultComboBoxModel<>(v));
            //Religion
            inputs.clear();
            inputs.add(5);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer2.cmbReligion.setModel(new DefaultComboBoxModel<>(v));
            //Status
            inputs.clear();
            inputs.add(6);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer2.cmbMemStatus.setModel(new DefaultComboBoxModel<>(v));
            //Position
            inputs.clear();
            inputs.add(7);
            inputs.add(0);
            outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
            v = new Vector();
            for (List<Object> output : outputs) {
                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
            }
            NewCustomer2.cmbPosition.setModel(new DefaultComboBoxModel<>(v));
        } catch (RemoteException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
