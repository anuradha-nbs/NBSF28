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
import java.awt.Dialog;
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

/**
 *
 * @author Bhanuka Krish
 */
public class AB16Buyings extends javax.swing.JFrame {

    private String code;
    private DateFormat df;
    private String id;
//    private String status;

    /**
     * Creates new form fram
     */
    public AB16Buyings() {
        initComponents();
        setTitle("Server on " + GeneralUserLogin.data.getIP());

        df = new SimpleDateFormat("yyyy-MM-dd");
        jdt16BDate.setDate(GeneralUserLogin.data.getSystemDate());
        jdtDueDate.setDate(GeneralUserLogin.data.getSystemDate());
        txtSection.grabFocus();
        statusUserName.setText(GeneralUserLogin.data.getUserCode() + "-" + GeneralUserLogin.data.getUsername());
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        btnApprove.setVisible(false);
//        jLabel20.setVisible(false);
//        txtStoresValue.setVisible(false);
//        jLabel21.setVisible(false);
    }

    public AB16Buyings(String code) {
        this();
        this.code = code;
        if (code.equals("01-01")) {
            statusGLTrnID.setText(GLCodes.AB16BUYINGS + "");
        } else if (code.equals("01-02")) {
            statusGLTrnID.setText(GLCodes.AB16CASHPURCHASE + "");
//            ((TitledBorder) jLabel21.getBorder()).setTitle("මුදලට ගැණුම්");
        } else if (code.equals("01-03")) {
            statusGLTrnID.setText(GLCodes.AB16PURCHASEDADVANCE + "");
        } else if (code.equals("01-04")) {
            statusGLTrnID.setText(GLCodes.AB16STORESRECEIVINGS + "");
        }

    }

    public AB16Buyings(String code, String id) {
        this(code);
        this.id = id;
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(id);
            List<Object> output = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_ViewStock_16B_Edit", 32);
            System.out.println(output);
            txtNumber.setText(String.valueOf(output.get(0)));
            txtSection.setId(Integer.parseInt(String.valueOf(output.get(2))));
            txtSection.setText(String.valueOf(output.get(3)));
            txtSupplier.setId(Integer.parseInt(String.valueOf(output.get(4))));
            txtSupplier.setText(String.valueOf(output.get(5)));
            switch (String.valueOf(output.get(8))) {
                case "1":
                case "2":
                    btnApprove.setEnabled(true);
                    btnSave.setEnabled(true);
                    break;
                //case "2":
                case "11":
                    btnApprove.setEnabled(false);
                    btnSave.setEnabled(false);
                    btnNew.setEnabled(false);
                    break;
                default:
                    break;
            }
            status.setText(String.valueOf(output.get(8)));
            jdt16BDate.setDate(df.parse(String.valueOf(output.get(12))));
            txt16Number.setText(String.valueOf(output.get(10)));
            txtBillNumber.setText(String.valueOf(output.get(11)));
            txtBillValue.setText(String.valueOf(output.get(13)));
            txtBillValueForSales.setText(String.valueOf(output.get(14)));
            txtDiscount.setText(String.valueOf(output.get(15)));
            txtBuyingExpences.setText(String.valueOf(output.get(16)));
            txtFreeRecieving.setText(String.valueOf(output.get(17)));
            txtReturns.setText(String.valueOf(output.get(18)));
            txtCreditTotalPayable.setText(String.valueOf(output.get(19)));
//            if (code.equals("01-02")) {
//                txtStoresValue.setText(String.valueOf(output.get(21)));
//            } else {
//                txtStoresValue.setText(String.valueOf(output.get(20)));
//            }
            txtCreditPeriod.setText(String.valueOf(output.get(22)));
            if (!(String.valueOf(output.get(23)).equals("null"))) {
                jdtDueDate.setDate(df.parse(String.valueOf(output.get(23))));
            }
            statusTrnID.setText("ID " + txtNumber.getText());
            statusGLTrnID.setText("GL " + String.valueOf(output.get(1)));
            statusSectionID.setText("S " + txtSection.getId() + "");
            statusSupplierID.setText("O " + txtSupplier.getId() + "");
            btnApprove.setText(String.valueOf(output.get(31)));
            btnApprove.setVisible(true);
            btnNew.setVisible(false);
//            if (btnApprove.getText().equals("null") || btnApprove.getText().equals("")) {
//                btnApprove.setEnabled(false);
//                btnNew.setEnabled(false);
//                btnSave.setEnabled(false);
//            }

//            btnNew.setEnabled(false);
//            btnSave.setEnabled(true);
        } catch (ParseException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
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

        confirmDialog = new javax.swing.JDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBillNumber = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        txtSupplier = new org.nbs.components.HiddenIDTextField();
        txt16Number = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jdtDueDate = new com.toedter.calendar.JDateChooser();
        jdt16BDate = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSection = new org.nbs.components.HiddenIDTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        statusTrnID = new javax.swing.JLabel();
        statusGLTrnID = new javax.swing.JLabel();
        statusSectionID = new javax.swing.JLabel();
        statusSupplierID = new javax.swing.JLabel();
        statusUserName = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        txtBillValue = new javax.swing.JFormattedTextField();
        txtBuyingExpences = new javax.swing.JFormattedTextField();
        txtDiscount = new javax.swing.JFormattedTextField();
        txtReturns = new javax.swing.JFormattedTextField();
        txtCreditTotalPayable = new javax.swing.JFormattedTextField();
        txtFreeRecieving = new javax.swing.JFormattedTextField();
        txtBillValueForSales = new javax.swing.JFormattedTextField();
        txtCreditPeriod = new javax.swing.JTextField();
        bCash = new javax.swing.JRadioButton();
        bLoan = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        javax.swing.GroupLayout confirmDialogLayout = new javax.swing.GroupLayout(confirmDialog.getContentPane());
        confirmDialog.getContentPane().setLayout(confirmDialogLayout);
        confirmDialogLayout.setHorizontalGroup(
            confirmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        confirmDialogLayout.setVerticalGroup(
            confirmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 81, 136));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("16 ඒ / බී ගැනුම් ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(691, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 0, 15)); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel5.setText("අංකය ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 25));

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel6.setText("අංශය");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 25));

        jLabel7.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel7.setText("සැපැයුම්කරු");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, 25));

        jLabel8.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel8.setText("බිල් අංකය");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, 25));

        txtBillNumber.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        txtBillNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1)));
        txtBillNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBillNumberActionPerformed(evt);
            }
        });
        getContentPane().add(txtBillNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 180, 26));

        txtNumber.setEditable(false);
        txtNumber.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        txtNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumber.setText("0");
        txtNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1)));
        txtNumber.setEnabled(false);
        txtNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumberActionPerformed(evt);
            }
        });
        getContentPane().add(txtNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 180, 26));

        txtSupplier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1)));
        txtSupplier.setFont(new java.awt.Font("AMALEE", 1, 17)); // NOI18N
        txtSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierActionPerformed(evt);
            }
        });
        txtSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSupplierFocusGained(evt);
            }
        });
        getContentPane().add(txtSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 660, 26));

        txt16Number.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        txt16Number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1)));
        txt16Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt16NumberActionPerformed(evt);
            }
        });
        getContentPane().add(txt16Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 180, 26));

        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel9.setText("16 අංකය");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 25));

        jLabel10.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel10.setText("ණය සිමාව");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, -1, 25));

        jLabel11.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel11.setText("දිනය");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, 25));

        jdtDueDate.setDateFormatString("yyyy-MM-dd");
        jdtDueDate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(jdtDueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 170, 25));

        jdt16BDate.setDateFormatString("yyyy-MM-dd");
        jdt16BDate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        getContentPane().add(jdt16BDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 170, 25));

        jLabel12.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel12.setText("ගෙවියයුතු දිනය ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, -1, 25));

        jLabel14.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel14.setText("බිල් වටිනාකම");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 25));

        jLabel15.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel15.setText("ගැණුම් වියදම් ");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, 25));

        jLabel17.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel17.setText("ආපසු යැවුම්");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, 25));

        jLabel18.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel18.setText("ණය හිමි මුළු ගෙවීම්");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, 25));

        txtSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1)));
        txtSection.setFont(new java.awt.Font("AMALEE", 1, 17)); // NOI18N
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
        getContentPane().add(txtSection, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 660, 26));

        jLabel22.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel22.setText("නොමිලේ ලැබීම");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, -1, 25));

        jLabel23.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel23.setText("බිල් වටිනාකම");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 100, 25));

        jLabel24.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        jLabel24.setText("වට්ටම්");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 25));

        btnSave.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnSave.setText("සේව්");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        btnSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSaveKeyReleased(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 510, 160, 35));

        btnApprove.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnApprove.setText("අනුමත කරන්න");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        getContentPane().add(btnApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 310, 35));

        btnNew.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        btnNew.setText("නව");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 510, 140, 35));

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setOpaque(true);
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 840, 50));

        jButton5.setText("...");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 50, -1));

        jButton6.setText("...");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, 50, -1));

        statusTrnID.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        statusTrnID.setForeground(new java.awt.Color(252, 11, 11));
        statusTrnID.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        statusGLTrnID.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        statusGLTrnID.setForeground(new java.awt.Color(128, 12, 12));
        statusGLTrnID.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        statusSectionID.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        statusSectionID.setForeground(new java.awt.Color(66, 19, 138));
        statusSectionID.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        statusSupplierID.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        statusSupplierID.setForeground(new java.awt.Color(17, 51, 249));
        statusSupplierID.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        statusUserName.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        statusUserName.setForeground(new java.awt.Color(17, 51, 249));
        statusUserName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        status.setFont(new java.awt.Font("TlwgTypewriter", 1, 18)); // NOI18N
        status.setForeground(new java.awt.Color(17, 51, 249));
        status.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jXStatusBar1Layout = new javax.swing.GroupLayout(jXStatusBar1);
        jXStatusBar1.setLayout(jXStatusBar1Layout);
        jXStatusBar1Layout.setHorizontalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar1Layout.createSequentialGroup()
                .addComponent(statusTrnID, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusGLTrnID, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusSectionID, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXStatusBar1Layout.setVerticalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusTrnID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusGLTrnID, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(statusSectionID, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(statusSupplierID, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(statusUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        );

        getContentPane().add(jXStatusBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 564, 880, 30));

        txtBillValue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBillValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtBillValue.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtBillValue.setText("0.00");
        txtBillValue.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtBillValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBillValueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBillValueFocusLost(evt);
            }
        });
        txtBillValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBillValueActionPerformed(evt);
            }
        });
        getContentPane().add(txtBillValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 230, 26));

        txtBuyingExpences.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuyingExpences.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtBuyingExpences.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtBuyingExpences.setText("0.00");
        txtBuyingExpences.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtBuyingExpences.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuyingExpencesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuyingExpencesFocusLost(evt);
            }
        });
        txtBuyingExpences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuyingExpencesActionPerformed(evt);
            }
        });
        getContentPane().add(txtBuyingExpences, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 230, 26));

        txtDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDiscount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDiscount.setText("0.00");
        txtDiscount.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtDiscount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiscountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiscountFocusLost(evt);
            }
        });
        txtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountActionPerformed(evt);
            }
        });
        getContentPane().add(txtDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 230, 26));

        txtReturns.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtReturns.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtReturns.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtReturns.setText("0.00");
        txtReturns.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtReturns.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtReturnsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtReturnsFocusLost(evt);
            }
        });
        txtReturns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReturnsActionPerformed(evt);
            }
        });
        getContentPane().add(txtReturns, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 230, 26));

        txtCreditTotalPayable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCreditTotalPayable.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtCreditTotalPayable.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCreditTotalPayable.setText("0.00");
        txtCreditTotalPayable.setEnabled(false);
        txtCreditTotalPayable.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtCreditTotalPayable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditTotalPayableFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCreditTotalPayableFocusLost(evt);
            }
        });
        txtCreditTotalPayable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditTotalPayableActionPerformed(evt);
            }
        });
        getContentPane().add(txtCreditTotalPayable, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 230, 26));

        txtFreeRecieving.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtFreeRecieving.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtFreeRecieving.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtFreeRecieving.setText("0.00");
        txtFreeRecieving.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtFreeRecieving.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFreeRecievingFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFreeRecievingFocusLost(evt);
            }
        });
        txtFreeRecieving.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFreeRecievingActionPerformed(evt);
            }
        });
        getContentPane().add(txtFreeRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, 230, 26));

        txtBillValueForSales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBillValueForSales.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtBillValueForSales.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtBillValueForSales.setText("0.00");
        txtBillValueForSales.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        txtBillValueForSales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBillValueForSalesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBillValueForSalesFocusLost(evt);
            }
        });
        txtBillValueForSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBillValueForSalesActionPerformed(evt);
            }
        });
        getContentPane().add(txtBillValueForSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 230, 26));

        txtCreditPeriod.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtCreditPeriod.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCreditPeriod.setText("0");
        txtCreditPeriod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditPeriodFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCreditPeriodFocusLost(evt);
            }
        });
        txtCreditPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditPeriodActionPerformed(evt);
            }
        });
        getContentPane().add(txtCreditPeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 230, 120, -1));

        buttonGroup1.add(bCash);
        bCash.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        bCash.setSelected(true);
        bCash.setText("මුදලට");
        getContentPane().add(bCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        buttonGroup1.add(bLoan);
        bLoan.setFont(new java.awt.Font("Iskoola Pota", 0, 16)); // NOI18N
        bLoan.setText("ණයට");
        getContentPane().add(bLoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        jLabel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "ණයට ගැණුම්", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 410, 180));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "විකුණුම්   මිලට", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 420, 180));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 255));
        jLabel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2), "සාමාන්‍ය තොරතුරු", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 840, 230));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        jLabel13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLabel13FocusGained(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 860, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBillNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBillNumberActionPerformed
        txtCreditPeriod.grabFocus();
    }//GEN-LAST:event_txtBillNumberActionPerformed

    private void txtNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumberActionPerformed

    private void txtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierActionPerformed
        jButton6.doClick();
    }//GEN-LAST:event_txtSupplierActionPerformed

    private void txt16NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt16NumberActionPerformed
        txtBillNumber.grabFocus();
    }//GEN-LAST:event_txt16NumberActionPerformed

    private void txtSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSectionActionPerformed
        jButton5.doClick();
    }//GEN-LAST:event_txtSectionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 3, "අනු අංශය").setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new FindSectionOrOutsider(this, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 4, "පාර්ශවකරු").setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save16B();
//        String[] buttons = {"Yes", "No"};
//        int i = JOptionPane.showOptionDialog(this, "<html><p>ගණුදෙනුව සම්පූර්ණයි. නව ගණුදෙනුවක් සිදු කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);

//        if (i == JOptionPane.YES_OPTION) {
//            String section = txtSection.getText();
//            int sid = txtSection.getId();
//            AB16Buyings aB16Buyings = new AB16Buyings(code);
//            int i2 = JOptionPane.showOptionDialog(this, "<html><p>අංශය වෙනස් කරනවාද?</p></html>", "වෙනස් කරනවාද?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
//            if (i2 == JOptionPane.NO_OPTION) {
//
//                this.dispose();
//                CommonJurnalForm.ItemRefresh.doClick();
//                AB16Buyings.txtSection.setId(sid);
//                AB16Buyings.txtSection.setText(section);
//                aB16Buyings.setVisible(true);
//                aB16Buyings.btnNew.doClick();
//
//            } else {
//                this.dispose();
//                CommonJurnalForm.ItemRefresh.doClick();
//                aB16Buyings.setVisible(true);
//                aB16Buyings.btnNew.doClick();
//            }
//        } else {
//        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtBillValueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBillValueFocusGained
        txtBillValue.selectAll();
    }//GEN-LAST:event_txtBillValueFocusGained

    private void txtBillValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBillValueFocusLost
        txtBillValue.select(0, 0);
    }//GEN-LAST:event_txtBillValueFocusLost

    private void txtBuyingExpencesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuyingExpencesFocusGained
        txtBuyingExpences.selectAll();
    }//GEN-LAST:event_txtBuyingExpencesFocusGained

    private void txtBuyingExpencesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuyingExpencesFocusLost
        txtBuyingExpences.select(0, 0);
    }//GEN-LAST:event_txtBuyingExpencesFocusLost

    private void txtDiscountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiscountFocusGained
        txtDiscount.selectAll();
    }//GEN-LAST:event_txtDiscountFocusGained

    private void txtDiscountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiscountFocusLost
        txtDiscount.select(0, 0);
    }//GEN-LAST:event_txtDiscountFocusLost

    private void txtReturnsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReturnsFocusGained
        txtReturns.selectAll();
    }//GEN-LAST:event_txtReturnsFocusGained

    private void txtReturnsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReturnsFocusLost
        txtReturns.select(0, 0);
    }//GEN-LAST:event_txtReturnsFocusLost

    private void txtCreditTotalPayableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditTotalPayableFocusGained
        txtCreditTotalPayable.selectAll();
    }//GEN-LAST:event_txtCreditTotalPayableFocusGained

    private void txtCreditTotalPayableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditTotalPayableFocusLost
        txtCreditTotalPayable.select(0, 0);
    }//GEN-LAST:event_txtCreditTotalPayableFocusLost

    private void txtFreeRecievingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFreeRecievingFocusGained
        txtFreeRecieving.selectAll();
    }//GEN-LAST:event_txtFreeRecievingFocusGained

    private void txtFreeRecievingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFreeRecievingFocusLost
        txtFreeRecieving.select(0, 0);
    }//GEN-LAST:event_txtFreeRecievingFocusLost

    private void txtBillValueForSalesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBillValueForSalesFocusGained
        txtBillValueForSales.selectAll();
    }//GEN-LAST:event_txtBillValueForSalesFocusGained

    private void txtBillValueForSalesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBillValueForSalesFocusLost
        txtBillValueForSales.select(0, 0);
    }//GEN-LAST:event_txtBillValueForSalesFocusLost

    private void txtBillValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBillValueActionPerformed
        totalPayable();
        txtCreditTotalPayable.grabFocus();
        txtBuyingExpences.grabFocus();
    }//GEN-LAST:event_txtBillValueActionPerformed

    private void txtBuyingExpencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuyingExpencesActionPerformed
        totalPayable();
        txtCreditTotalPayable.grabFocus();
        txtDiscount.grabFocus();
    }//GEN-LAST:event_txtBuyingExpencesActionPerformed

    private void txtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountActionPerformed
        totalPayable();
        txtCreditTotalPayable.grabFocus();
        txtReturns.grabFocus();
    }//GEN-LAST:event_txtDiscountActionPerformed

    private void txtReturnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReturnsActionPerformed
        totalPayable();
        txtCreditTotalPayable.grabFocus();
//        txtStoresValue.grabFocus();
    }//GEN-LAST:event_txtReturnsActionPerformed

    private void txtCreditTotalPayableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditTotalPayableActionPerformed
//        txtStoresValue.grabFocus();
    }//GEN-LAST:event_txtCreditTotalPayableActionPerformed

    private void txtFreeRecievingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFreeRecievingActionPerformed
        txtBillValueForSales.grabFocus();
    }//GEN-LAST:event_txtFreeRecievingActionPerformed

    private void txtCreditPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditPeriodActionPerformed
        txtBillValue.grabFocus();
    }//GEN-LAST:event_txtCreditPeriodActionPerformed

    private void jLabel13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLabel13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13FocusGained

    private void txtCreditPeriodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditPeriodFocusGained
        txtCreditPeriod.selectAll();
    }//GEN-LAST:event_txtCreditPeriodFocusGained

    private void txtCreditPeriodFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditPeriodFocusLost
        txtCreditPeriod.select(0, txtCreditPeriod.getText().length() - 1);
    }//GEN-LAST:event_txtCreditPeriodFocusLost

    private void txtBillValueForSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBillValueForSalesActionPerformed
        btnSave.grabFocus();
    }//GEN-LAST:event_txtBillValueForSalesActionPerformed

    private void btnSaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSaveKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSave.doClick();
        }
    }//GEN-LAST:event_btnSaveKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        CommonJurnalForm.cmbConfirmed.actionPerformed(new ActionEvent(null, 1, ""));
//CommonJurnalForm.cmbConfirmed
    }//GEN-LAST:event_formWindowClosing

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            doSave();
            btnSave.setEnabled(true);
            btnNew.setEnabled(false);
        } catch (ParseException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        String[] buttons = {"Yes", "No"};
        int i = JOptionPane.showOptionDialog(this, "<html><p><span style='color:red'>" + btnApprove.getText().split(" ")[0] + " කළ පසු වෙනස් කල නොහැක.</span> " + btnApprove.getText().split(" ")[0] + " කරනවාද?</p></html>", "සාර්ථකයි", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[1]);
        if (i == JOptionPane.YES_OPTION) {
            try {
                List<Object> inputs = new ArrayList<>();
                inputs.add(status.getText());
                if (code.equals("01-01")) {
                    inputs.add(GLCodes.AB16BUYINGS);
                } else if (code.equals("01-02")) {
                    inputs.add(GLCodes.AB16CASHPURCHASE);
                } else if (code.equals("01-03")) {
                    inputs.add(GLCodes.AB16PURCHASEDADVANCE);
                } else if (code.equals("01-04")) {
                    inputs.add(GLCodes.AB16STORESRECEIVINGS);
                }
                inputs.add(id);
                inputs.add(GeneralUserLogin.data.getUsername());
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
        } else {
            this.dispose();
            CommonJurnalForm.ItemRefresh.doClick();
        }
    }//GEN-LAST:event_btnApproveActionPerformed

    private void txtSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSectionFocusGained
        txtSection.selectAll();
    }//GEN-LAST:event_txtSectionFocusGained

    private void txtSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSectionFocusLost
        txtSection.select(0, 0);
    }//GEN-LAST:event_txtSectionFocusLost

    private void txtSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSupplierFocusGained
        txtSupplier.selectAll();
    }//GEN-LAST:event_txtSupplierFocusGained

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AB16Buyings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AB16Buyings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AB16Buyings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AB16Buyings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AB16Buyings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bCash;
    private javax.swing.JRadioButton bLoan;
    private javax.swing.JButton btnApprove;
    private static javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDialog confirmDialog;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private com.toedter.calendar.JDateChooser jdt16BDate;
    private com.toedter.calendar.JDateChooser jdtDueDate;
    public static javax.swing.JLabel status;
    public static javax.swing.JLabel statusGLTrnID;
    public static javax.swing.JLabel statusSectionID;
    public static javax.swing.JLabel statusSupplierID;
    public static javax.swing.JLabel statusTrnID;
    public static javax.swing.JLabel statusUserName;
    public static javax.swing.JTextField txt16Number;
    private javax.swing.JTextField txtBillNumber;
    private javax.swing.JFormattedTextField txtBillValue;
    private javax.swing.JFormattedTextField txtBillValueForSales;
    private javax.swing.JFormattedTextField txtBuyingExpences;
    private javax.swing.JTextField txtCreditPeriod;
    private javax.swing.JFormattedTextField txtCreditTotalPayable;
    private javax.swing.JFormattedTextField txtDiscount;
    private javax.swing.JFormattedTextField txtFreeRecieving;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JFormattedTextField txtReturns;
    public static org.nbs.components.HiddenIDTextField txtSection;
    public static org.nbs.components.HiddenIDTextField txtSupplier;
    // End of variables declaration//GEN-END:variables

    private void totalPayable() {
        try {
            double billvalue = FormatConstants.numberFormat1.parse(txtBillValue.getText()).doubleValue();
            double buyingExpences = FormatConstants.numberFormat1.parse(txtBuyingExpences.getText()).doubleValue();
            double discount = FormatConstants.numberFormat1.parse(txtDiscount.getText()).doubleValue();
            double returns = FormatConstants.numberFormat1.parse(txtReturns.getText()).doubleValue();

            double totalPlus = billvalue + buyingExpences;
            double totalMinus = discount + returns;

            double totalPayable = totalPlus - totalMinus;
            if (totalPayable < 0) {
                JOptionPane.showMessageDialog(this, "මෙහි ඇතුලත් කර ඇති ගෙවීම්  අගයන් වැරදිය", "මෙහි ඇතුලත් කර ඇති ගෙවීම්  අගයන් වැරදිය", JOptionPane.ERROR_MESSAGE);
            }
            txtCreditTotalPayable.setText(FormatConstants.decimalFormat.format(totalPayable));

        } catch (ParseException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void save16B() {
        try {
            if (!txt16Number.getText().trim().equals("") && txtSection.getId() != 0 && txtSupplier.getId() != 0) {
                if (FormatConstants.numberFormat1.parse(txtCreditTotalPayable.getText()).doubleValue() >= 0) {
                    doSave();
                    JOptionPane.showMessageDialog(this, "<html><p>ගණුදෙනුව සම්පූර්ණයි.</p></html>", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
                    CommonJurnalForm.ItemRefresh.doClick();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "මෙහි ඇතුලත් කර ඇති ගෙවීම්  අගයන් වැරදිය", "මෙහි ඇතුලත් කර ඇති ගෙවීම්  අගයන් වැරදිය", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "අංශය,සැපයුම්කරු හෝ 16 අංකය ඇතුලත් කර නැත", "අංශය හෝ සැපයුම්කරු ඇතුලත් කර නැත", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doSave() throws ParseException {

        List<Object> inputs = new ArrayList<>();

        try {
            inputs.add(txtNumber.getText());
            inputs.add(GeneralUserLogin.data.getSystemDate());
            inputs.add("");
            if (code.equals("01-01")) {
                inputs.add(GLCodes.AB16BUYINGS);
            } else if (code.equals("01-02")) {
                inputs.add(GLCodes.AB16CASHPURCHASE);
            } else if (code.equals("01-03")) {
                inputs.add(GLCodes.AB16PURCHASEDADVANCE);
            } else if (code.equals("01-04")) {
                inputs.add(GLCodes.AB16STORESRECEIVINGS);
            }
            inputs.add(txtSection.getId());
            inputs.add(txtSupplier.getId());
            inputs.add(txt16Number.getText());
            inputs.add(txtBillNumber.getText());
            inputs.add(df.format(jdt16BDate.getDate()));
            inputs.add(numberFormat1.parse(txtBillValue.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtBillValueForSales.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtDiscount.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtBuyingExpences.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtFreeRecieving.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtReturns.getText()).doubleValue());
            inputs.add(numberFormat1.parse(txtCreditTotalPayable.getText()).doubleValue());
            inputs.add(0);//numberFormat1.parse(txtStoresValue.getText()).doubleValue());
            inputs.add(txtCreditPeriod.getText());
            inputs.add(df.format(jdtDueDate.getDate()));
            inputs.add(GeneralUserLogin.data.getUsername());
            if (bCash.isSelected()) {
                inputs.add(1);
            } else {
                inputs.add(0);
            }
            inputs.add("@recID");

            List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_F16B", 1);
            txtNumber.setText(String.valueOf(output.get(0)));

        } catch (RemoteException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AB16Buyings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
