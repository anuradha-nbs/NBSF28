/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common.search;

import Sources.ReportFileLocator;
import Sources.TableCellListener;
import Sources.TableColumnFontChanger;
import com.nbs.connection.DB;
import com.nbs.impl.ServerConnection;
import com.nbs.view.Member.NewCustomer;
import com.nbs.view.common.GeneralUserLogin;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mmh
 */
public class GeneralCustomerRegistry extends javax.swing.JPanel {

    private DefaultTableModel dtm;
//    private static int areas[] = {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private final DefaultTableModel defaultTableModel = new DefaultTableModel(new String[]{"ID", "AreaCode", "AreaName", "Status", "ShopNo", "F10ReceiptNo", "MemberFee", "MembershipDate", "IDNo", "FullName", "AddLine1", "AddLine2", "AddLine3", "AddLine4"}, 1);

    /**
     * Creates new form GeneralCustomerRegistry
     */
    public GeneralCustomerRegistry() {
        initComponents();
        Table1.setModel(defaultTableModel);
        Table1.packAll();
        Table1.setShowGrid(true);
        Table2.setShowGrid(true);
        dtm = (DefaultTableModel) Table2.getModel();
        TableColumnFontChanger.setAmalee(Table2, 1);
        TableColumnFontChanger.setAmalee(Table2, 2);
        TableColumnFontChanger.setAmalee(Table2, 7);
        TableColumnFontChanger.setAmalee(Table2, 8);
        TableColumnFontChanger.setAmalee(Table2, 9);
        TableColumnFontChanger.setAmalee(Table2, 10);
        TableColumnFontChanger.setAmalee(Table2, 11);
        
        TableColumnFontChanger.setAmalee(Table1, 1);
        TableColumnFontChanger.setAmalee(Table1, 2);
        TableColumnFontChanger.setAmalee(Table1, 7);
        TableColumnFontChanger.setAmalee(Table1, 8);
        TableColumnFontChanger.setAmalee(Table1, 9);
        TableColumnFontChanger.setAmalee(Table1, 10);
        TableColumnFontChanger.setAmalee(Table1, 11);

        JTextField field1 = (JTextField) Table1.getCellEditor(0, 2).getTableCellEditorComponent(Table1, "", true, 0, 1);
        field1.setFont(new Font("AMALEE", Font.PLAIN, 16));
        JTextField field2 = (JTextField) Table1.getCellEditor(0, 7).getTableCellEditorComponent(Table1, "", true, 0, 7);
        field2.setFont(new Font("AMALEE", Font.PLAIN, 16));
        JTextField field3 = (JTextField) Table1.getCellEditor(0, 8).getTableCellEditorComponent(Table1, "", true, 0, 8);
        field3.setFont(new Font("AMALEE", Font.PLAIN, 16));
        JTextField field4 = (JTextField) Table1.getCellEditor(0, 9).getTableCellEditorComponent(Table1, "", true, 0, 9);
        field4.setFont(new Font("AMALEE", Font.PLAIN, 16));
        JTextField field5 = (JTextField) Table1.getCellEditor(0, 10).getTableCellEditorComponent(Table1, "", true, 0, 10);
        field5.setFont(new Font("AMALEE", Font.PLAIN, 16));
        JTextField field6 = (JTextField) Table1.getCellEditor(0, 11).getTableCellEditorComponent(Table1, "", true, 0, 11);
        field6.setFont(new Font("AMALEE", Font.PLAIN, 16));

//        JComboBox comboBox = (JComboBox) Table1.getCellRenderer(0, 1).getTableCellRendererComponent(Table1, "", true, true, 0, 1);
//        List<Object> inputs = new ArrayList<>();
//        try {
//            inputs.add(1);
//            inputs.add(0);
//            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_LoadCombo_MemberMaster", 2);
//            Vector v = new Vector();
//            for (List<Object> output : outputs) {
//                v.add(new Item(Integer.parseInt(String.valueOf(output.get(0))), String.valueOf(output.get(1))));
//            }
//            comboBox.setModel(new DefaultComboBoxModel(v));
//        } catch (RemoteException ex) {
//            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                TableCellListener tcl = (TableCellListener) e.getSource();
                searchData();
            }

        };
        TableCellListener listener = new TableCellListener(Table1, action);
        searchData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerRegistryPopUpMenu = new javax.swing.JPopupMenu();
        Edit = new javax.swing.JMenuItem();
        Print = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new org.jdesktop.swingx.JXTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        Edit.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        Edit.setText("වෙනස් කරන්න");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });
        customerRegistryPopUpMenu.add(Edit);

        Print.setText("මුද්‍රණය");
        Print.setToolTipText("");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });
        customerRegistryPopUpMenu.add(Print);

        jPanel1.setBackground(new java.awt.Color(0, 81, 136));

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("සාමාජික නාම ලේඛණය");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jCheckBox1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jCheckBox1.setText("සියලු");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1))
        );

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table2.setComponentPopupMenu(customerRegistryPopUpMenu);
        Table2.getTableHeader().setReorderingAllowed(false);
        Table2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Table2ComponentResized(evt);
            }
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Table2ComponentMoved(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);
        if (Table2.getColumnModel().getColumnCount() > 0) {
            Table2.getColumnModel().getColumn(1).setMinWidth(75);
            Table2.getColumnModel().getColumn(1).setPreferredWidth(75);
            Table2.getColumnModel().getColumn(1).setMaxWidth(75);
            Table2.getColumnModel().getColumn(3).setMinWidth(60);
            Table2.getColumnModel().getColumn(3).setPreferredWidth(60);
            Table2.getColumnModel().getColumn(3).setMaxWidth(60);
            Table2.getColumnModel().getColumn(4).setMinWidth(70);
            Table2.getColumnModel().getColumn(4).setPreferredWidth(70);
            Table2.getColumnModel().getColumn(4).setMaxWidth(70);
            Table2.getColumnModel().getColumn(5).setMinWidth(150);
            Table2.getColumnModel().getColumn(5).setPreferredWidth(150);
            Table2.getColumnModel().getColumn(5).setMaxWidth(150);
            Table2.getColumnModel().getColumn(6).setMinWidth(100);
            Table2.getColumnModel().getColumn(6).setPreferredWidth(100);
            Table2.getColumnModel().getColumn(6).setMaxWidth(100);
            Table2.getColumnModel().getColumn(7).setMinWidth(125);
            Table2.getColumnModel().getColumn(7).setPreferredWidth(125);
            Table2.getColumnModel().getColumn(7).setMaxWidth(125);
            Table2.getColumnModel().getColumn(9).setMinWidth(300);
            Table2.getColumnModel().getColumn(9).setPreferredWidth(300);
            Table2.getColumnModel().getColumn(9).setMaxWidth(300);
            Table2.getColumnModel().getColumn(10).setMinWidth(200);
            Table2.getColumnModel().getColumn(10).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(10).setMaxWidth(200);
            Table2.getColumnModel().getColumn(11).setMinWidth(200);
            Table2.getColumnModel().getColumn(11).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(11).setMaxWidth(200);
            Table2.getColumnModel().getColumn(12).setMinWidth(200);
            Table2.getColumnModel().getColumn(12).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(12).setMaxWidth(200);
            Table2.getColumnModel().getColumn(13).setMinWidth(200);
            Table2.getColumnModel().getColumn(13).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(13).setMaxWidth(200);
        }

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14"
            }
        ));
        Table1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table1.setRowHeight(29);
        Table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Table1);
        if (Table1.getColumnModel().getColumnCount() > 0) {
            Table1.getColumnModel().getColumn(1).setMinWidth(75);
            Table1.getColumnModel().getColumn(1).setPreferredWidth(75);
            Table1.getColumnModel().getColumn(1).setMaxWidth(75);
            Table1.getColumnModel().getColumn(3).setMinWidth(60);
            Table1.getColumnModel().getColumn(3).setPreferredWidth(60);
            Table1.getColumnModel().getColumn(3).setMaxWidth(60);
            Table1.getColumnModel().getColumn(4).setMinWidth(70);
            Table1.getColumnModel().getColumn(4).setPreferredWidth(70);
            Table1.getColumnModel().getColumn(4).setMaxWidth(70);
            Table1.getColumnModel().getColumn(5).setMinWidth(100);
            Table1.getColumnModel().getColumn(5).setPreferredWidth(100);
            Table1.getColumnModel().getColumn(5).setMaxWidth(100);
            Table1.getColumnModel().getColumn(6).setMinWidth(100);
            Table1.getColumnModel().getColumn(6).setPreferredWidth(100);
            Table1.getColumnModel().getColumn(6).setMaxWidth(100);
            Table1.getColumnModel().getColumn(7).setMinWidth(125);
            Table1.getColumnModel().getColumn(7).setPreferredWidth(125);
            Table1.getColumnModel().getColumn(7).setMaxWidth(125);
            Table1.getColumnModel().getColumn(9).setMinWidth(300);
            Table1.getColumnModel().getColumn(9).setPreferredWidth(300);
            Table1.getColumnModel().getColumn(9).setMaxWidth(300);
            Table1.getColumnModel().getColumn(10).setMinWidth(200);
            Table1.getColumnModel().getColumn(10).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(10).setMaxWidth(200);
            Table1.getColumnModel().getColumn(11).setMinWidth(200);
            Table1.getColumnModel().getColumn(11).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(11).setMaxWidth(200);
            Table1.getColumnModel().getColumn(12).setMinWidth(200);
            Table1.getColumnModel().getColumn(12).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(12).setMaxWidth(200);
            Table1.getColumnModel().getColumn(13).setMinWidth(200);
            Table1.getColumnModel().getColumn(13).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(13).setMaxWidth(200);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(1, 1, 1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel2.setText("මුළු ගණන :");

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 5, 5));

        jButton1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton1.setText("නව");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        if (Table2.getSelectedRow() > -1) {
            new NewCustomer(Table2.getValueAt(Table2.getSelectedRow(), 0).toString()).setVisible(true);
        }
    }//GEN-LAST:event_EditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new NewCustomer(1).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Table2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table2ComponentMoved
        jScrollPane1.getViewport().setViewPosition(jScrollPane2.getViewport().getViewPosition());
    }//GEN-LAST:event_Table2ComponentMoved

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        try {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            JasperReport jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("Rep_F28_Member_Registry.jrxml"));
            Map params = new HashMap<Object, Object>();
            params.put("MPCSName", GeneralUserLogin.data.getBranchName());
            params.put("nBranchID", GeneralUserLogin.data.getBranchID());
            JRParameter[] parameters = jasperReport.getParameters();
            for (JRParameter parameter : parameters) {
                String name = parameter.getName();
                if (name.startsWith("cCol")) {
                    int colIndex = Integer.parseInt(name.substring(4)) - 1;
                    params.put(name, String.valueOf(Table1.getValueAt(0, colIndex)).equals("null") || String.valueOf(Table1.getValueAt(0, colIndex)) == null ? "" : String.valueOf(Table1.getValueAt(0, colIndex)));
                }
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DB.getConnection());
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_PrintActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            searchAll();
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void Table2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table2ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_Table2ComponentResized

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("R038_Mem_List.jrxml"));
            Map params = new HashMap();
            params.put("MPCSName", GeneralUserLogin.data.getBranchName());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DB.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Edit;
    private javax.swing.JMenuItem Print;
    private org.jdesktop.swingx.JXTable Table1;
    private org.jdesktop.swingx.JXTable Table2;
    private javax.swing.JPopupMenu customerRegistryPopUpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private void searchData() {
        dtm.setRowCount(0);
        int selectedRow = Table1.getSelectedRow();
        List<Object> inputs = new ArrayList<>();
        inputs.add(GeneralUserLogin.data.getBranchID());
        inputs.add(String.valueOf(Table1.getValueAt(0, 0)).equals("null") || String.valueOf(Table1.getValueAt(0, 0)) == null ? "" : String.valueOf(Table1.getValueAt(0, 0)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 1)).equals("null") || String.valueOf(Table1.getValueAt(0, 1)) == null ? "" : String.valueOf(Table1.getValueAt(0, 1)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 2)).equals("null") || String.valueOf(Table1.getValueAt(0, 2)) == null ? "" : String.valueOf(Table1.getValueAt(0, 2)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 3)).equals("null") || String.valueOf(Table1.getValueAt(0, 3)) == null ? "" : String.valueOf(Table1.getValueAt(0, 3)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 4)).equals("null") || String.valueOf(Table1.getValueAt(0, 4)) == null ? "" : String.valueOf(Table1.getValueAt(0, 4)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 5)).equals("null") || String.valueOf(Table1.getValueAt(0, 5)) == null ? "" : String.valueOf(Table1.getValueAt(0, 5)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 6)).equals("null") || String.valueOf(Table1.getValueAt(0, 6)) == null ? "" : String.valueOf(Table1.getValueAt(0, 6)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 7)).equals("null") || String.valueOf(Table1.getValueAt(0, 7)) == null ? "" : String.valueOf(Table1.getValueAt(0, 7)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 8)).equals("null") || String.valueOf(Table1.getValueAt(0, 8)) == null ? "" : String.valueOf(Table1.getValueAt(0, 8)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 9)).equals("null") || String.valueOf(Table1.getValueAt(0, 9)) == null ? "" : String.valueOf(Table1.getValueAt(0, 9)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 10)).equals("null") || String.valueOf(Table1.getValueAt(0, 10)) == null ? "" : String.valueOf(Table1.getValueAt(0, 10)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 11)).equals("null") || String.valueOf(Table1.getValueAt(0, 11)) == null ? "" : String.valueOf(Table1.getValueAt(0, 11)));
        inputs.add(String.valueOf(Table1.getValueAt(0, 12)).equals("null") || String.valueOf(Table1.getValueAt(0, 12)) == null ? "" : String.valueOf(Table1.getValueAt(0, 12)));

        inputs.add(1);
        try {
            int i = 0;
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_FindData_MemberShipMaster", 13);
            for (List<Object> output : outputs) {
                Vector v = new Vector();
                for (Object object : output) {
                    v.add(object);
                }
                dtm.addRow(v);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        Table2.packAll();
        TableColumnModel columns = Table2.getColumnModel();
        for (int i = 0; i < Table2.getColumnCount(); i++) {
            Table1.getColumnModel().getColumn(i).setPreferredWidth(columns.getColumn(i).getPreferredWidth());
            Table1.getColumnModel().getColumn(i).setMaxWidth(columns.getColumn(i).getMaxWidth());
            Table1.getColumnModel().getColumn(i).setMinWidth(columns.getColumn(i).getMinWidth());
        }
        jScrollPane1.getViewport().setViewPosition(jScrollPane2.getViewport().getViewPosition());
        getTotal();
    }

    private void getTotal() {
        jLabel3.setText(dtm.getRowCount() + "");
    }

    private void searchAll() {
        dtm.setRowCount(0);
        List<Object> inputs = new ArrayList<>();
        inputs.add(GeneralUserLogin.data.getBranchID());
        for (int i = 0; i < 14; i++) {
            inputs.add("");
        }
        inputs.add(1);
        try {
            int i = 0;
            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_Mem_FindData_MemberShipMaster", 13);
            for (List<Object> output : outputs) {
                Vector v = new Vector();
                for (Object object : output) {
                    v.add(object);
                }
                dtm.addRow(v);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneralCustomerRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        Table2.packAll();
        TableColumnModel columns = Table2.getColumnModel();
        for (int i = 0; i < Table2.getColumnCount(); i++) {
            Table1.getColumnModel().getColumn(i).setPreferredWidth(columns.getColumn(i).getPreferredWidth());
            Table1.getColumnModel().getColumn(i).setMaxWidth(columns.getColumn(i).getMaxWidth());
            Table1.getColumnModel().getColumn(i).setMinWidth(columns.getColumn(i).getMinWidth());
        }
        jScrollPane1.getViewport().setViewPosition(jScrollPane2.getViewport().getViewPosition());
        getTotal();
    }
}
