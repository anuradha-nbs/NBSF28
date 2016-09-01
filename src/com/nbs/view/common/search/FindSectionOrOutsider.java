/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common.search;

import Sources.CheckNull;
import Sources.TableColumnFontChanger;
import com.nbs.impl.ServerConnection;
import com.nbs.view.F28.Payments.CommonCashReceiptCash;
import com.nbs.view.F28.Payments.CommonCashReceiptStamp;
import com.nbs.view.F28.Payments.CommonDRNote;
import static com.nbs.view.F28.Payments.CommonDRNote.txtDTSubSection;
import com.nbs.view.F28.Payments.CommonDRNoteResource;
import com.nbs.view.F28.Payments.CommonGLAccountOpenningBalances;
import com.nbs.view.F28.Payments.CommonRuralBank9E;
import com.nbs.view.F28.Stock.AB16Buyings;
import com.nbs.view.F28.Stock.AB16BuyingsNewFormat;
import com.nbs.view.F28.Stock.AB16StoresBuyings;
import com.nbs.view.F28.Stock.C14B9;
import com.nbs.view.F28.Stock.C14B9HD;
import com.nbs.view.F28.Stock.F17;
import com.nbs.view.F28.Stock.F18;
import com.nbs.view.F28.Stock.F19;
import com.nbs.view.F28.Stock.StockBalance;
import com.nbs.view.Reports.AuditReportPanel;
import com.nbs.view.Reports.ReportsPanel;
import com.nbs.view.common.AmaliKeyBoard;
import static com.nbs.view.common.AmaliKeyBoard.emptyUnicodeField;
import com.nbs.view.common.CHQMaxInfoResource;
import com.nbs.view.common.FormatConstants;
import com.nbs.view.common.GeneralUserLogin;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.UnicodeKeyBoard;
import com.nbs.view.manager.CommonJurnalForm;
import com.nbs.view.manager.ClosingStock;
import com.nbs.view.manager.ClosingStockNewFormat;
import com.nbs.view.manager.TargetStock;
import com.nbs.view.manager.TargetStockOld;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.nbs.resources.Item;

/**
 *
 * @author mmh
 */
public class FindSectionOrOutsider extends javax.swing.JDialog {

    private int type;
    private DefaultTableModel dtm1;
    private DefaultTableModel dtm2;
    static int reasonID = 0;
    static int sectionID = 0;
    private String reasonSubType = "ALL";
    private int outsiderID = 0;
    private int subSectionTypeId = 0;
    static String creditorID = "0";
    static String subSectionMFID = "0";
    private final boolean isAmali = true;
    private String DRNoteHDID;

    public FindSectionOrOutsider(Dialog owner, boolean modal) {
        super(owner, modal);
        initComponents();
        refreshItem.setVisible(false);
        txtSearchData.grabFocus();

    }

    public FindSectionOrOutsider(JFrame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        refreshItem.setVisible(false);
        txtSearchData.grabFocus();
    }

    /**
     * Creates new form FindSectionOrOutsider
     *
     * @param owner
     * @param modal
     * @param model1
     * @param model2
     * @param type
     * @param Title
     */
    public FindSectionOrOutsider(Dialog owner, boolean modal, DefaultTableModel model1, DefaultTableModel model2, int type, String Title) {
        this(owner, modal);
        createForm(model1, model2, type, Title);
    }

    public FindSectionOrOutsider(JFrame owner, DefaultTableModel model1, DefaultTableModel model2, int type, String Title) {
        this(owner, true);
        createForm(model1, model2, type, Title);

    }

    public FindSectionOrOutsider(JFrame owner, DefaultTableModel model1, DefaultTableModel model2, int type, String Title, String reasonType) {
        this(owner, true);
        this.reasonSubType = reasonType;
        createForm(model1, model2, type, Title);

    }

    public FindSectionOrOutsider(Dialog owner, boolean modal, DefaultTableModel model1, DefaultTableModel model2, int type, String Title, String reasonType) {
        this(owner, modal);
        this.reasonSubType = reasonType;
        createForm(model1, model2, type, Title);

    }

    public FindSectionOrOutsider(JFrame owner, DefaultTableModel model1, DefaultTableModel model2, int type, String Title, int[] data) {
        this(owner, true);
        this.subSectionTypeId = data[0];
        this.outsiderID = data[1];
        createForm(model1, model2, type, Title);

    }

    public FindSectionOrOutsider(Dialog owner, boolean modal, DefaultTableModel model1, DefaultTableModel model2, int type, String Title, int[] data) {
        this(owner, modal);
        this.subSectionTypeId = data[0];
        this.outsiderID = data[1];
        createForm(model1, model2, type, Title);

    }

    public FindSectionOrOutsider(DefaultTableModel model1, DefaultTableModel model2, int type, String Title) {
        this(null, model1, model2, type, Title);
    }

    public FindSectionOrOutsider(DefaultTableModel model1, DefaultTableModel model2, int type, String Title, String reasonType) {
        this(null, true, model1, model2, type, Title, reasonType);
    }

    public FindSectionOrOutsider(JDialog parent, boolean modal, DefaultTableModel model1, DefaultTableModel model2, int type, String title, int[] data, String hdID) {
        this(parent, modal, model1, model2, type, title, data);
        this.DRNoteHDID = hdID;
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
        jMenuItem1 = new javax.swing.JMenuItem();
        refreshItem = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new org.jdesktop.swingx.JXTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new org.jdesktop.swingx.JXTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        txtSearchData = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPopupMenu1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPopupMenu1ComponentShown(evt);
            }
        });

        jMenuItem1.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        jMenuItem1.setText("භාවිතා කරන්න");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        refreshItem.setText("jMenuItem3");
        refreshItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(refreshItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentHidden(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 81, 136));

        title.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(254, 254, 254));
        title.setText("සාමාජික නාම ලේඛණය");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table2.setComponentPopupMenu(jPopupMenu1);
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
        Table2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Table2ComponentResized(evt);
            }
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Table2ComponentMoved(evt);
            }
        });
        Table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table1.setRowHeight(29);
        Table1.getTableHeader().setReorderingAllowed(false);
        Table1.setVisibleRowCount(1);
        Table1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Table1ComponentMoved(evt);
            }
        });
        jScrollPane1.setViewportView(Table1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(1, 1, 1))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnNew.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        btnNew.setText("නව");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        btnEdit.setText("සංස්කරණය");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        txtSearchData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSearchData.setMinimumSize(new java.awt.Dimension(2, 29));
        txtSearchData.setPreferredSize(new java.awt.Dimension(2, 29));
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

        jLabel37.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel37.setText("                       F2 = අමාලි යතුරුපුවරුව F3 = යුනිකෝඩ් යතුරුපුවරුව F4 = නැවත සැකසීම");

        jTextField1.setEditable(false);

        btnAdd.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("වටිනාකම :");

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("ගෙවන මුදල :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSearchData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jTextField1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtSearchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Table2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseReleased

    }//GEN-LAST:event_Table2MouseReleased

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        double amount = 0;
        if (dtm1.equals(TableHeaders.B16FIND_TABLE_MODEL)) {
            if (Table2.getSelectedRow() > -1) {
                amount = Double.parseDouble(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 5)));
                jTextField1.setText(FormatConstants.decimalFormat.format(amount));
                jTextField2.setText(jTextField1.getText());
            }
        }
    }//GEN-LAST:event_Table2MouseClicked

    private void Table2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table2ComponentResized
        int columnCount = Table2.getColumnCount();

        for (int i = 0; i < columnCount; i++) {
            Table1.getColumnModel().getColumn(i).setPreferredWidth(Table2.getColumnModel().getColumn(i).getWidth());
        }
    }//GEN-LAST:event_Table2ComponentResized

    private void Table2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table2ComponentMoved
        jScrollPane1.getViewport().setViewPosition(jScrollPane2.getViewport().getViewPosition());
    }//GEN-LAST:event_Table2ComponentMoved

    private void Table1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table1ComponentMoved
//        jScrollPane2.getViewport().setViewPosition(jScrollPane1.getViewport().getViewPosition());
    }//GEN-LAST:event_Table1ComponentMoved

    private void Table2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setData();
        }
    }//GEN-LAST:event_Table2KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (Table2.getSelectedRow() > -1) {
            setData();
        } else {
            JOptionPane.showMessageDialog(this, "පෙලක් තෝරන්න", "පෙලක් තෝරන්න", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jPopupMenu1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPopupMenu1ComponentShown

    }//GEN-LAST:event_jPopupMenu1ComponentShown

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        if (dtm1 == TableHeaders.REASON_TABLE_MODEL) {
            new ReasonDialog(this, true).setVisible(true);
        } else if (dtm1 == TableHeaders.SECTIONFINDER_TABLE_MODEL) {
            new SectionDialog(this, true).setVisible(true);
        } else if (dtm1 == TableHeaders.OUTSIDERFINDER_TABLE_MODEL) {
            new OutsiderDialog(this, true).setVisible(true);
        } else if (dtm1 == TableHeaders.SUBSECTIONFINDER_TABLE_MODEL) {
            new SubSectionDialog(this, true).setVisible(true);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (Table2.getSelectedRow() > -1) {
            if (dtm1 == TableHeaders.REASON_TABLE_MODEL) {
                ReasonDialog reasonDialog = new ReasonDialog(this, true);
                reasonID = Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0)));
                reasonDialog.txtReasonUnicode.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                reasonDialog.txtReasonAmali.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                reasonDialog.txtReasonGLCode.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 4)) + " - " + String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 5)));
                reasonDialog.txtReasonWargaya.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 1)));
                if (String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 4)).equals("null") || String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 4)) == null || String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 4)).equals("")) {
                    reasonDialog.txtReasonGLCode.setId(0);
                } else {
                    reasonDialog.txtReasonGLCode.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 4))));
                }

                reasonDialog.setVisible(true);
            } else if (dtm1 == TableHeaders.SECTIONFINDER_TABLE_MODEL) {
                SectionDialog sectionDialog = new SectionDialog(this, true);
                sectionID = Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0)));
                sectionDialog.txtSectionUnicode.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                sectionDialog.txtSectionAmali.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 1)));
                sectionDialog.txtSuperGroup.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));

                sectionDialog.setVisible(true);
            } else if (dtm1 == TableHeaders.OUTSIDERFINDER_TABLE_MODEL) {
                OutsiderDialog outsiderDialog = new OutsiderDialog(this, true, String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0)));
                outsiderDialog.setVisible(true);
            } else if (dtm1 == TableHeaders.SUBSECTIONFINDER_TABLE_MODEL) {
                new SubSectionDialog(this, true, String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))).setVisible(true);
//new NewJFrame(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(),0))).setVisible(true);
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jPanel1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentHidden

    }//GEN-LAST:event_jPanel1ComponentHidden

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated

    }//GEN-LAST:event_formWindowDeactivated

    private void txtSearchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchDataActionPerformed
        searchData();
    }//GEN-LAST:event_txtSearchDataActionPerformed

    private void txtSearchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDataKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F2:
                new AmaliKeyBoard(this, true, txtSearchData, emptyUnicodeField, true).setVisible(true);
                break;
            case KeyEvent.VK_F3:
                new UnicodeKeyBoard(this, true, UnicodeKeyBoard.emptyAmaliField, txtSearchData, false).setVisible(true);
                break;
            case KeyEvent.VK_F4:
                txtSearchData.setFont(new Font("Ubuntu", Font.PLAIN, 15));
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txtSearchDataKeyPressed

    private void txtSearchDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDataKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Table2.grabFocus();
            Table2.getSelectionModel().setSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_txtSearchDataKeyReleased

    private void Table2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (Table2.getSelectedRow() == 0) {
                txtSearchData.grabFocus();
                Table2.getSelectionModel().clearSelection();
            }
        }
    }//GEN-LAST:event_Table2KeyPressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (Table2.getSelectedRow() > -1) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(this.DRNoteHDID);
                inputs.add(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0)));
                inputs.add(FormatConstants.decimalFormat.parse(jTextField1.getText()).doubleValue());
                inputs.add(FormatConstants.decimalFormat.parse(jTextField2.getText()).doubleValue());
                inputs.add(GeneralUserLogin.data.getUsername());
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F16B_Payments", 0);

                if (type == 38) {
                    CommonDRNoteResource.showDT(this.DRNoteHDID);
                } else if (type == 100) {
                    CHQMaxInfoResource.showDT(DRNoteHDID);
                }
                int confirm = JOptionPane.showConfirmDialog(this, "තවත් 16B අංකයක් භාවිතා කරනවාද?", "තවත් 16B අංකයක් භාවිතා කරනවාද?", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new FindSectionOrOutsider(null, true, TableHeaders.B16FIND_TABLE_MODEL, TableHeaders.B16FIND_TABLE_MODEL, 38, "16B අංකය සෙවීම", new int[]{2, txtDTSubSection.getId()}).setVisible(true);
                    this.dispose();
                } else {
                    this.dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void refreshItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshItemActionPerformed
        searchData();
    }//GEN-LAST:event_refreshItemActionPerformed

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
            java.util.logging.Logger.getLogger(FindSectionOrOutsider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindSectionOrOutsider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindSectionOrOutsider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindSectionOrOutsider.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FindSectionOrOutsider dialog = new FindSectionOrOutsider(new JDialog(), true);
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
    private org.jdesktop.swingx.JXTable Table1;
    private org.jdesktop.swingx.JXTable Table2;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JMenuItem refreshItem;
    private javax.swing.JLabel title;
    public static javax.swing.JTextField txtSearchData;
    // End of variables declaration//GEN-END:variables

    private void defineColumnWidths(DefaultTableModel model) {
        if (model == TableHeaders.SUBSECTIONFINDER_TABLE_MODEL) {
            Table1.getColumnModel().getColumn(0).setMaxWidth(50);
            Table1.getColumnModel().getColumn(1).setMaxWidth(75);
            Table2.getColumnModel().getColumn(0).setMaxWidth(50);
            Table2.getColumnModel().getColumn(1).setMaxWidth(75);

            if (isAmali) {
                TableColumnFontChanger.setAmalee(Table2, 2);
                TableColumnFontChanger.setAmalee(Table1, 2);
                TableColumnFontChanger.setAmalee(Table2, 3);
                TableColumnFontChanger.setAmalee(Table1, 3);
            } else {
                TableColumnFontChanger.setIskolaPotha(Table2, 2);
                TableColumnFontChanger.setIskolaPotha(Table1, 2);
                TableColumnFontChanger.setIskolaPotha(Table2, 3);
                TableColumnFontChanger.setIskolaPotha(Table1, 3);
            }

        } else if (model == TableHeaders.OUTSIDERFINDER_TABLE_MODEL) {
            Table1.getColumnModel().getColumn(0).setMaxWidth(50);
            Table1.getColumnModel().getColumn(0).setMinWidth(50);
            Table1.getColumnModel().getColumn(1).setMinWidth(150);
            Table1.getColumnModel().getColumn(1).setMaxWidth(150);
            Table1.getColumnModel().getColumn(2).setMinWidth(75);
            Table1.getColumnModel().getColumn(2).setMaxWidth(75);
            Table2.getColumnModel().getColumn(0).setMaxWidth(50);
            Table2.getColumnModel().getColumn(0).setMinWidth(50);
            Table2.getColumnModel().getColumn(1).setMinWidth(150);
            Table2.getColumnModel().getColumn(1).setMaxWidth(150);
            Table2.getColumnModel().getColumn(2).setMinWidth(75);
            Table2.getColumnModel().getColumn(2).setMaxWidth(75);
            TableColumnFontChanger.setAmalee(Table2, 3);
            TableColumnFontChanger.setAmalee(Table1, 3);
            TableColumnFontChanger.setIskolaPotha(Table1, 4);
            TableColumnFontChanger.setIskolaPotha(Table2, 4);
        } else if (model == TableHeaders.REASON_TABLE_MODEL) {
            Table1.getColumnModel().getColumn(0).setMaxWidth(50);
            Table1.getColumnModel().getColumn(0).setMinWidth(50);
            Table2.getColumnModel().getColumn(0).setMaxWidth(50);
            Table2.getColumnModel().getColumn(0).setMinWidth(50);
            Table1.getColumnModel().getColumn(1).setMaxWidth(50);
            Table1.getColumnModel().getColumn(1).setMinWidth(50);
            Table2.getColumnModel().getColumn(1).setMaxWidth(50);
            Table2.getColumnModel().getColumn(1).setMinWidth(50);
            Table1.getColumnModel().getColumn(4).setMaxWidth(50);
            Table1.getColumnModel().getColumn(4).setMinWidth(50);
            Table2.getColumnModel().getColumn(4).setMaxWidth(50);
            Table2.getColumnModel().getColumn(4).setMinWidth(50);
            Table1.getColumnModel().getColumn(3).setMaxWidth(200);
            Table1.getColumnModel().getColumn(3).setMinWidth(200);
            Table2.getColumnModel().getColumn(3).setMaxWidth(200);
            Table2.getColumnModel().getColumn(3).setMinWidth(200);
            TableColumnFontChanger.setIskolaPotha(Table1, 1);
            TableColumnFontChanger.setIskolaPotha(Table2, 1);
            TableColumnFontChanger.setAmalee(Table1, 2);
            TableColumnFontChanger.setAmalee(Table2, 2);
            TableColumnFontChanger.setIskolaPotha(Table1, 3);
            TableColumnFontChanger.setIskolaPotha(Table2, 3);
            TableColumnFontChanger.setIskolaPotha(Table1, 5);
            TableColumnFontChanger.setIskolaPotha(Table2, 5);
        } else if (model == TableHeaders.SECTIONFINDER_TABLE_MODEL) {
            jScrollPane1.setVisible(false);
            Table1.getColumn(0).setMaxWidth(50);
            Table1.getColumn(0).setMinWidth(50);
            Table2.getColumn(0).setMaxWidth(50);
            Table2.getColumn(0).setMinWidth(50);
            TableColumnFontChanger.setAmalee(Table2, 1);
            TableColumnFontChanger.setIskolaPotha(Table2, 2);
        } else if (model == TableHeaders.PERFORMANCEAPPRAISALSECTIONFINDER_TABLE_MODEL) {
            jScrollPane1.setVisible(false);
            Table1.getColumn(0).setMaxWidth(50);
            Table1.getColumn(0).setMinWidth(50);
            Table2.getColumn(0).setMaxWidth(50);
            Table2.getColumn(0).setMinWidth(50);
            TableColumnFontChanger.setAmalee(Table2, 1);
            TableColumnFontChanger.setIskolaPotha(Table2, 2);
        } else if (model == TableHeaders.OUTSIDERTYPEFINDER_TABLE_MODEL) {
            jScrollPane1.setVisible(false);
            Table1.getColumn(0).setMaxWidth(50);
            Table1.getColumn(0).setMinWidth(50);
            Table2.getColumn(0).setMaxWidth(50);
            Table2.getColumn(0).setMinWidth(50);
            TableColumnFontChanger.setIskolaPotha(Table2, 1);
        } else if (model == TableHeaders.B16FIND_TABLE_MODEL) {
            TableColumnFontChanger.setAmalee(Table2, 4);
            TableColumnFontChanger.setAmalee(Table2, 6);
            jScrollPane1.setVisible(false);
            Table2.packAll();
        }
    }

    private void searchData() {

        dtm2.setRowCount(0);
        if (dtm1 == TableHeaders.OUTSIDERFINDER_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(txtSearchData.getText());
                inputs.add("");
                inputs.add("");
                inputs.add("");
                inputs.add("");
                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_FindData_Act_OutsiderMaster", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.SUBSECTIONFINDER_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                if (isAmali) {
                    inputs.add(1);
                } else {
                    inputs.add(2);
                }
                inputs.add(txtSearchData.getText());
                inputs.add("");
                inputs.add("");

                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_FindData_Act_Sections_Sub", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.REASON_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(reasonSubType);
                inputs.add(txtSearchData.getText());
                inputs.add("");

                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_FindData_Act_Trn_Reason", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.SECTIONFINDER_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(5);
                inputs.add(null);
                inputs.add(null);
                inputs.add(0);
                inputs.add(0);
                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.PERFORMANCEAPPRAISALSECTIONFINDER_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(8);
                inputs.add(null);
                inputs.add(null);
                inputs.add(0);
                inputs.add(0);
                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.OUTSIDERTYPEFINDER_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(4);
                inputs.add(null);
                inputs.add(null);
                inputs.add(0);
                inputs.add(0);

                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.B16FIND_TABLE_MODEL) {
            List<Object> inputs = new ArrayList<>();
            try {
                inputs.add(subSectionTypeId);
                inputs.add(outsiderID);

                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_FindData_F16B_ForPayments", dtm1.getColumnCount());
                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dtm1 == TableHeaders.B16FINDER_TABLE_MODEL) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(9);
                inputs.add(null);
                inputs.add(null);
                inputs.add(0);
                inputs.add(0);
                List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewStock", 2);

                for (List<Object> output : outputs) {
                    Vector v = new Vector();
                    for (Object object : output) {
                        v.add(object);
                    }
                    dtm2.addRow(v);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AB16BuyingsNewFormat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AB16BuyingsNewFormat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(AB16BuyingsNewFormat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setData() {
        switch (type) {
            case 1:
                ((DefaultComboBoxModel) CommonJurnalForm.section.getModel()).setSelectedItem(new Item(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))), String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                dispose();
                break;
            case 2:
                ((DefaultComboBoxModel) CommonJurnalForm.outsider.getModel()).setSelectedItem(new Item(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))), String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3))));
                dispose();
                break;
            case 3:
                AB16Buyings.txtSection.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                AB16Buyings.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16Buyings.statusSectionID.setText("S " + AB16Buyings.txtSection.getId() + "");
                AB16Buyings.txtSupplier.grabFocus();
                dispose();
                break;
            case 4:
                AB16Buyings.txtSupplier.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                AB16Buyings.txtSupplier.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16Buyings.statusSupplierID.setText("O " + AB16Buyings.txtSupplier.getId() + "");
                AB16Buyings.txt16Number.grabFocus();
                dispose();
                break;
            case 5:
                C14B9HD.txtSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                C14B9HD.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9HD.txtCreditor.grabFocus();
                dispose();
                break;
            case 6:
                C14B9HD.txtCreditor.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                C14B9HD.txtCreditor.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9HD.txtSoldSection.grabFocus();
                dispose();
                break;
            case 7:
                C14B9HD.txtSoldSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                C14B9HD.txtSoldSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9HD.txtDTReason.grabFocus();
                dispose();
                break;
            case 40:
                C14B9.txtSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                C14B9.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9.txtCreditor.grabFocus();
                dispose();
                break;
            case 41:
                C14B9.txtCreditor.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                C14B9.txtCreditor.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9.txtSoldSection.grabFocus();
                dispose();
                break;
            case 42:
                C14B9.txtSoldSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                C14B9.txtSoldSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9.txtCash.grabFocus();
                dispose();
                break;
            case 8:
                F17.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                F17.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F17.statusSubSectionID.setText(F17.txtSubSection.getId() + "");
                F17.txtOutsider.grabFocus();
                dispose();
                break;
            case 9:
                F17.txtOutsider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                F17.txtOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F17.statusOutsiderID.setText(F17.txtOutsider.getId() + "");
                F17.txtProfit.grabFocus();
                dispose();
                break;
            case 10:
                F18.txtSubSectionOut.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                F18.txtSubSectionOut.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F18.statusOutSubSectionID.setText(F18.txtSubSectionOut.getId() + "");
                F18.txtOutsiderOut.grabFocus();
                dispose();
                break;
            case 11:
                F18.txtOutsiderOut.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                F18.txtOutsiderOut.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F18.statusOutOutsiderID.setText(F18.txtOutsiderOut.getId() + "");
                F18.txtOutBuy.grabFocus();
                dispose();
                break;
            case 12:
                F18.txtSubSectionIn.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                F18.txtSubSectionIn.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F18.statusInSubSectionID.setText(F18.txtSubSectionIn.getId() + "");
                F18.txtOutsiderIn.grabFocus();
                dispose();
                break;
            case 13:
                F18.txtOutsiderIn.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                F18.txtOutsiderIn.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F18.statusInOutsiderID.setText(F18.txtOutsiderIn.getId() + "");
                F18.txtInBuy.grabFocus();
                dispose();
                break;
            case 14:
                F19.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                F19.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                F19.statusSubSectionID.setText(F19.txtSubSection.getId() + "");
                F19.txtPurchase.grabFocus();
                dispose();
                break;
            case 16:
                CommonDRNote.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                CommonDRNote.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.statusSubsection.setText(CommonDRNote.txtSubSection.getId() + "");
                CommonDRNote.txtOutSider.grabFocus();
                //
                 {
                    try {
                        List<Object> inputs = new ArrayList<>();
                        inputs.add(CommonDRNote.txtSubSection.getId());
                        List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_FindCA_and_OutSider", 5);

                        CommonDRNote.txtBankCrr.setId(Integer.parseInt(String.valueOf(outputs.get(2))));
                        CommonDRNote.txtBankCrr.setText(String.valueOf(outputs.get(3)));
                        if (String.valueOf(outputs.get(4)).equals("0")) {
                            CommonDRNote.txtBankCrr.setEnabled(false);
                        } else if (String.valueOf(outputs.get(4)).equals("1")) {
                            CommonDRNote.txtBankCrr.setEnabled(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dispose();

                break;
            case 17:
                CommonDRNote.txtOutSider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonDRNote.txtOutSider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.statusOutsider.setText(CommonDRNote.txtOutSider.getId() + "");
                CommonDRNote.txtReason.grabFocus();
                dispose();
                break;
            case 18:
                CommonDRNote.txtDTSubSection.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonDRNote.txtDTSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));

                dispose();
                 {
                    try {
                        List<Object> inputs = new ArrayList<>();
                        inputs.add(CommonDRNote.txtDTSubSection.getId());
                        List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_FindCA_and_OutSider", 5);
                        if (outputs.get(0) == null) {

                        } else {
                            CommonDRNote.txtDTOutsider.setId(Integer.parseInt(String.valueOf(outputs.get(0))));
                            CommonDRNote.txtDTOutsider.setText(String.valueOf(outputs.get(1)));
                        }
                        CommonDRNote.txtDTReason.grabFocus();
                    } catch (RemoteException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 19:
                CommonDRNote.txtDTOutsider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonDRNote.txtDTOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.txtDTReason.grabFocus();
                dispose();
                break;
            case 20:
                CommonDRNote.txtDTReason.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonDRNote.txtDTReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.txtDTRemarks.grabFocus();
                dispose();
                break;
            case 21:
                CommonDRNote.txtReason.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonDRNote.txtReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.statusReason.setText(CommonDRNote.txtReason.getId() + "");
                CommonDRNote.txtDTReason.setText(CommonDRNote.txtReason.getText());
                CommonDRNote.txtDTReason.setId(CommonDRNote.txtReason.getId());
                CommonDRNote.txtDTSubSection.setText(CommonDRNote.txtSubSection.getText());
                CommonDRNote.txtDTSubSection.setId(CommonDRNote.txtSubSection.getId());
                CommonDRNote.txtDTOutsider.setText(CommonDRNote.txtOutSider.getText());
                CommonDRNote.txtDTOutsider.setId(CommonDRNote.txtOutSider.getId());

                CommonDRNote.btn16BAdd.grabFocus();
                CommonDRNote.jTabbedPane1.setSelectedIndex(0);
                dispose();
                break;
            case 43:
                CommonDRNote.txtReason.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonDRNote.txtReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.statusReason.setText(CommonDRNote.txtReason.getId() + "");
                CommonDRNote.txtDTReason.setText(CommonDRNote.txtReason.getText());
                CommonDRNote.txtDTReason.setId(CommonDRNote.txtReason.getId());
                CommonDRNote.txtDTSubSection.setText(CommonDRNote.txtSubSection.getText());
                CommonDRNote.txtDTSubSection.setId(CommonDRNote.txtSubSection.getId());
                CommonDRNote.txtDTOutsider.setText(CommonDRNote.txtOutSider.getText());
                CommonDRNote.txtDTOutsider.setId(CommonDRNote.txtOutSider.getId());

                CommonDRNote.btn16BAdd.grabFocus();
                CommonDRNote.jTabbedPane1.setSelectedIndex(1);
                dispose();
                break;
            case 44:
                CommonDRNote.txtReason.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonDRNote.txtReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.statusReason.setText(CommonDRNote.txtReason.getId() + "");
                CommonDRNote.txtDTReason.setText(CommonDRNote.txtReason.getText());
                CommonDRNote.txtDTReason.setId(CommonDRNote.txtReason.getId());
                CommonDRNote.txtDTSubSection.setText(CommonDRNote.txtSubSection.getText());
                CommonDRNote.txtDTSubSection.setId(CommonDRNote.txtSubSection.getId());
                CommonDRNote.txtDTOutsider.setText(CommonDRNote.txtOutSider.getText());
                CommonDRNote.txtDTOutsider.setId(CommonDRNote.txtOutSider.getId());

                CommonDRNote.btn16BAdd.grabFocus();
//                CommonDRNote.jTabbedPane1.setSelectedIndex(1);
                dispose();
                break;
            case 22:
                CommonCashReceiptCash.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                CommonCashReceiptCash.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonCashReceiptCash.txtOutSider.grabFocus();
                dispose();
                 {
                    try {
                        List<Object> inputs = new ArrayList<>();
                        inputs.add(CommonCashReceiptCash.txtSubSection.getId());
                        List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_FindCA_and_OutSider", 5);
                        if (outputs.get(0) == null) {

                        } else {
                            CommonCashReceiptCash.txtOutSider.setId(Integer.parseInt(String.valueOf(outputs.get(0))));
                            CommonCashReceiptCash.txtOutSider.setText(String.valueOf(outputs.get(1)));
                        }

                        CommonCashReceiptCash.txtRelatedGLAccount.setId(Integer.parseInt(String.valueOf(outputs.get(2))));
                        CommonCashReceiptCash.txtRelatedGLAccount.setText(String.valueOf(outputs.get(3)));
                        if (String.valueOf(outputs.get(4)).equals("0")) {
                            CommonCashReceiptCash.txtRelatedGLAccount.setEnabled(false);
                        } else if (String.valueOf(outputs.get(4)).equals("1")) {
                            CommonCashReceiptCash.txtRelatedGLAccount.setEnabled(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 23:
                CommonCashReceiptCash.txtOutSider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonCashReceiptCash.txtOutSider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonCashReceiptCash.txtReasonorGlAccount.grabFocus();
                dispose();
                break;
            case 24:
                CommonCashReceiptCash.txtReasonorGlAccount.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonCashReceiptCash.txtReasonorGlAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonCashReceiptCash.txtReasonorGlAccount1.grabFocus();
                 {
                    try {
                        List<Object> inputs = new ArrayList<>();
                        inputs.add(CommonCashReceiptCash.txtReasonorGlAccount.getId());
                        List<Object> output = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_FindGL_From_Reason", 2);

                        if (!CheckNull.isNullable(output)) {
                            if (!output.isEmpty()) {
                                CommonCashReceiptCash.txtReasonorGlAccount1.setId(Integer.parseInt(String.valueOf(output.get(0))));
                                CommonCashReceiptCash.txtReasonorGlAccount1.setText(String.valueOf(output.get(1)));
                                CommonCashReceiptCash.isCheck.grabFocus();
                            } else {
                                CommonCashReceiptCash.txtReasonorGlAccount1.setId(0);
                                CommonCashReceiptCash.txtReasonorGlAccount1.setText("");
                            }
                        } else {
                            CommonCashReceiptCash.txtReasonorGlAccount1.setId(0);
                            CommonCashReceiptCash.txtReasonorGlAccount1.setText("");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dispose();
                break;
            case 25:
                CommonCashReceiptStamp.txtReasonOrGLAccount.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonCashReceiptStamp.txtReasonOrGLAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonCashReceiptStamp.txtOther.grabFocus();
                dispose();
                break;
            case 26:
                CommonCashReceiptStamp.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                CommonCashReceiptStamp.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonCashReceiptStamp.txtOutSider.grabFocus();
                dispose();
                break;
            case 27:
                CommonCashReceiptStamp.txtOutSider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonCashReceiptStamp.txtOutSider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonCashReceiptStamp.txtReasonOrGLAccount.grabFocus();
                dispose();
                break;
            case 28:
                CommonDRNote.txtDTRemarks.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonDRNote.txtDTRemarks.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.txtDTDrCr.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 5)));
                CommonDRNote.txtDTGLAcct.grabFocus();
                dispose();
                break;
            case 29:
                CommonGLAccountOpenningBalances.txtDTSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                CommonGLAccountOpenningBalances.txtDTSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonGLAccountOpenningBalances.txtDTOutsider.grabFocus();
                dispose();
                break;
            case 30:
                CommonGLAccountOpenningBalances.txtDTOutsider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonGLAccountOpenningBalances.txtDTOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonGLAccountOpenningBalances.txtDTDR.grabFocus();
                dispose();
                break;
            case 31:
                CommonRuralBank9E.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                CommonRuralBank9E.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonRuralBank9E.txtOutSider.grabFocus();
                dispose();
                break;
            case 32:
                CommonRuralBank9E.txtOutSider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                CommonRuralBank9E.txtOutSider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonRuralBank9E.txtDTLegderAccount.grabFocus();
                dispose();
                break;
            case 33:
                C14B9HD.txtDTReason.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                C14B9HD.txtDTReason.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                C14B9HD.txtDTDR.grabFocus();
                dispose();
                break;
            case 34:
                ClosingStock.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                ClosingStock.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                ClosingStock.month1.grabFocus();
                dispose();
                break;
            case 35:
                SubSectionDialog.txtSubContactPerson.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                SubSectionDialog.txtSubContactPerson.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                SubSectionDialog.txtGLAcct.grabFocus();
                this.dispose();
                break;
            case 36:
                ReportsPanel.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                ReportsPanel.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                this.dispose();
                break;
            case 37:
                ReportsPanel.txtOutsider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                ReportsPanel.txtOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                this.dispose();
                break;
            case 38:
//                CommonDRNote.txt
                break;
            case 45:
                AB16StoresBuyings.txtSection.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                AB16StoresBuyings.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16StoresBuyings.txtStores.grabFocus();
                dispose();
                break;
            case 46:
                AB16StoresBuyings.txtSupplier.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                AB16StoresBuyings.txtSupplier.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16StoresBuyings.txt16Number.grabFocus();
                dispose();
                break;
            case 47:
                AB16StoresBuyings.txtStores.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                AB16StoresBuyings.txtStores.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16StoresBuyings.txtSupplier.grabFocus();
                dispose();
                break;
            case 48:
                F19.txtSupplier.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                F19.txtSupplier.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
//                F19.statusOutSiderID.setText(F19.txtOutsider.getId() + "");
                F19.txtSubSection.grabFocus();
                dispose();
                break;
            case 49:
                CommonDRNote.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                CommonDRNote.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonDRNote.statusSubsection.setText(CommonDRNote.txtSubSection.getId() + "");
                CommonDRNote.txtOutSider.grabFocus();
                //
                 {
                    try {
                        List<Object> inputs = new ArrayList<>();
                        inputs.add(CommonDRNote.txtSubSection.getId());
                        List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_FindCA_and_OutSider", 5);

                        CommonDRNote.txtBankCrr.setId(Integer.parseInt(String.valueOf(outputs.get(2))));
                        CommonDRNote.txtBankCrr.setText(String.valueOf(outputs.get(3)));
                        if (String.valueOf(outputs.get(4)).equals("0")) {
                            CommonDRNote.txtBankCrr.setEnabled(false);
                        } else if (String.valueOf(outputs.get(4)).equals("1")) {
                            CommonDRNote.txtBankCrr.setEnabled(true);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dispose();
                 {
                    try {
                        List<Object> inputs = new ArrayList<>();
                        inputs.add(CommonDRNote.txtSubSection.getId());
                        List<Object> outputs = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_FindCA_and_OutSider", 5);
                        if (outputs.get(0) == null) {

                        } else {
                            CommonDRNote.txtOutSider.setId(Integer.parseInt(String.valueOf(outputs.get(0))));
                            CommonDRNote.txtOutSider.setText(String.valueOf(outputs.get(1)));
                        }
                        CommonDRNote.txtReason.grabFocus();
                    } catch (RemoteException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FindSectionOrOutsider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 50:
                CommonDRNote.txtDTSubSection.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                CommonDRNote.txtDTSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));

                dispose();

                break;
            case 51:
                ReportsPanel.txtSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 1))));
                ReportsPanel.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                dispose();
                break;
            case 52:
                F19.txtSupplier.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                F19.txtSupplier.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
//                F19.statusOutSiderID.setText(F19.txtOutsider.getId() + "");
                F19.txtSubSection.grabFocus();
                dispose();
                break;
            case 53:
                TargetStockOld.txtSubSectionHeader.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                TargetStockOld.txtSubSectionHeader.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                dispose();
                break;
            case 54:
                AuditReportPanel.txtSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 1))));
                AuditReportPanel.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                dispose();
                break;
            case 55:
                AuditReportPanel.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                AuditReportPanel.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                this.dispose();
                break;
            case 56:
                AB16BuyingsNewFormat.txtSection.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                AB16BuyingsNewFormat.txtSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
//                AB16BuyingsNewFormat.statusSectionID.setText("S " + AB16Buyings.txtSection.getId() + "");
                AB16BuyingsNewFormat.txt16Number.grabFocus();
                dispose();
                break;
            case 57:
                AB16BuyingsNewFormat.txtOutSider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                AB16BuyingsNewFormat.txtOutSider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16BuyingsNewFormat.txtInvoiceNo1.grabFocus();
                dispose();
                break;
            case 58:
                AB16BuyingsNewFormat.txtSectionFrom.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                AB16BuyingsNewFormat.txtSectionFrom.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                AB16BuyingsNewFormat.txtInvoiceNo2.grabFocus();
                dispose();
                break;
            case 59:
                AB16BuyingsNewFormat.txtSectionFrom.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2)));
                AB16BuyingsNewFormat.txtSectionFrom.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
//                AB16BuyingsNewFormat.statusSectionID.setText("S " + AB16Buyings.txtSection.getId() + "");
                AB16BuyingsNewFormat.txtInvoiceNo2.grabFocus();
                dispose();
                break;
            case 60:
                CommonJurnalForm.txtCurrentAct.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 1)));
                CommonJurnalForm.txtCurrentAct.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                CommonJurnalForm.ItemRefresh.doClick();
                this.dispose();
                break;
            case 61:
                StockBalance.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                StockBalance.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                StockBalance.statusSubSectionID.setText(StockBalance.txtSubSection.getId() + "");
                StockBalance.txtRefNumber.grabFocus();
                dispose();
                break;
            case 62:
                StockBalance.txtOutsider.setText(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 3)));
                StockBalance.txtOutsider.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                StockBalance.statusOutsiderID.setText(StockBalance.txtOutsider.getId() + "");
                StockBalance.txtProfit.grabFocus();
                dispose();
                break;
            case 63:
                TargetStock.txtSubSectionHeader.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                TargetStock.txtSubSectionHeader.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                TargetStock.txtACT5.grabFocus();
                dispose();
                break;
            case 64:
                ClosingStockNewFormat.txtSubSection.setText(String.valueOf(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 2))));
                ClosingStockNewFormat.txtSubSection.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(Table2.getSelectedRow(), 0))));
                ClosingStockNewFormat.txtBuying.grabFocus();
                dispose();
                break;
            default:
                break;
        }
        if (dtm2.equals(TableHeaders.B16FIND_TABLE_MODEL2)) {
            btnAdd.doClick();
        }
    }
    JTextField field1, field2, field3;

    private void createForm(DefaultTableModel model1, DefaultTableModel model2, int type, String Title) {
        dtm1 = model1;
        dtm2 = model2;
        Table1.setModel(model1);
        Table2.setModel(model2);
        model1.setRowCount(0);
        Table1.setShowGrid(true);
        Table2.setShowGrid(true);
        defineColumnWidths(model1);
        Table1.getTableHeader().setFont(new Font("Iskoola pota", Font.PLAIN, 16));
        Table2.getTableHeader().setFont(new Font("Iskoola pota", Font.PLAIN, 16));
        this.type = type;
        title.setText(Title);
        searchData();
        if (type != 100 && type != 101 && type != 102) {
            if (!model1.equals(TableHeaders.REASON_TABLE_MODEL) || !model2.equals(TableHeaders.REASON_TABLE_MODEL2)) {
                btnEdit.setVisible(false);
                btnNew.setVisible(false);
            }

        }
        if (!model1.equals(TableHeaders.B16FIND_TABLE_MODEL)) {
            jTextField1.setVisible(false);
            jTextField2.setVisible(false);
            btnAdd.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
        } else {
            btnNew.setVisible(false);
            btnEdit.setVisible(false);
        }
    }

    private void focusGained(FocusEvent evt) {
        evt.getComponent().setBackground(Color.YELLOW);
    }

    private void focusLost(FocusEvent evt) {
        evt.getComponent().setBackground(Color.WHITE);
    }

//    private void setCreditorDataEdit(OutsiderDialog outsiderDialog, List<Object> output) {
//        creditorID = String.valueOf(output.get(0));
//        HiddenItemComboBoxSelector.setSelectedItem(outsiderDialog.cmbOutsiderType, Integer.parseInt(String.valueOf(output.get(1))), String.valueOf(output.get(2)));
//        outsiderDialog.txtOutsiderCode.setText(String.valueOf(output.get(3)));
//        outsiderDialog.txtRefNo.setText(String.valueOf(output.get(4)));
//        outsiderDialog.txtOutsiderNameAmali.setText(String.valueOf(output.get(5)));
//        outsiderDialog.txtOutsiderName.setText(String.valueOf(output.get(6)));
//        outsiderDialog.txtContactPerson.setText(String.valueOf(output.get(7)));
//        outsiderDialog.txtOutsiderAddress1.setText(String.valueOf(output.get(8)));
//        outsiderDialog.txtOutsiderAddress2.setText(String.valueOf(output.get(9)));
//        outsiderDialog.txtOutsiderAddress3.setText(String.valueOf(output.get(10)));
//        outsiderDialog.txtOutsiderAddress4.setText(String.valueOf(output.get(11)));
//        outsiderDialog.txtOutsiderTP1.setText(String.valueOf(output.get(12)));
//        outsiderDialog.txtOutsiderTP2.setText(String.valueOf(output.get(13)));
//        outsiderDialog.txtOutsiderEmail.setText(String.valueOf(output.get(14)));
//        outsiderDialog.txtDebtorDayCount.setText(String.valueOf(output.get(15)));
//        outsiderDialog.txtDebtorCreditLimit.setText(String.valueOf(output.get(16)));
//        outsiderDialog.txtCreditorDayCount.setText(String.valueOf(output.get(17)));
//        outsiderDialog.txtCreditorCreditLimit.setText(String.valueOf(output.get(18)));
//        removeNullInOutSider(outsiderDialog);
//    }
//    private void removeNullInOutSider(OutsiderDialog outsiderDialog) {
//        JTextField textFields[] = {outsiderDialog.txtContactPerson, outsiderDialog.txtCreditorCreditLimit, outsiderDialog.txtCreditorDayCount, outsiderDialog.txtDebtorCreditLimit, outsiderDialog.txtDebtorDayCount, outsiderDialog.txtOutsiderAddress1, outsiderDialog.txtOutsiderAddress2, outsiderDialog.txtOutsiderAddress3, outsiderDialog.txtOutsiderAddress4, outsiderDialog.txtOutsiderCode, outsiderDialog.txtOutsiderEmail, outsiderDialog.txtOutsiderName, outsiderDialog.txtOutsiderNameAmali, outsiderDialog.txtOutsiderTP1, outsiderDialog.txtOutsiderTP2, outsiderDialog.txtRefNo,};
//        for (JTextField textField : textFields) {
//            JTextFieldRemoveNull.removeNull(textField);
//        }
//    }
}
