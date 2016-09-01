/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * frm_F28_CashReceipt.java
 *
 * Created on Aug 7, 2013, 9:59:39 PM
 */
package com.nbs.view.F28.Payments;

import Sources.GLCodes;
import Sources.ReportFileLocator;
import Sources.TableColumnFontChanger;
import Sources.TableColumnRightAlign;
import com.nbs.connection.DB;
import com.nbs.impl.ServerConnection;
import com.nbs.view.common.FormatConstants;
import com.nbs.view.common.GeneralUserLogin;
import static com.nbs.view.common.GeneralUserLogin.data;
import com.nbs.view.common.TableHeaders;
import com.nbs.view.common.search.FindAccount5Data;
import com.nbs.view.common.search.FindSectionOrOutsider;
import com.nbs.view.manager.CommonJurnalForm;
import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mmh
 */
public class CommonCashReceiptStamp extends javax.swing.JDialog {

    /**
     * Creates new form frm_F28_CashReceipt
     */
    public static String code;
    public static int inputReasonID = 0;
    public static int inputGLAcctID = 0;
    String hdID = "0";
    DefaultTableModel dtm1;
    DefaultTableModel dtm2;
    public static boolean save = false;

    public CommonCashReceiptStamp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtTotal.setText("0.00");
        setTitle("Server on " + GeneralUserLogin.data.getIP());

        statusUserName.setText(GeneralUserLogin.data.getUserCode() + "-" + GeneralUserLogin.data.getUsername());
        txtTrnDate.setText(FormatConstants.dateFormat.format(GeneralUserLogin.data.getSystemDate()));
        dtm1 = (DefaultTableModel) Table1.getModel();
        dtm2 = (DefaultTableModel) Table2.getModel();
        Table1.getTableHeader().setFont(new Font("Iskoola Pota", Font.BOLD, 16));
        Table2.setShowGrid(true);
        TableColumnFontChanger.setAmalee(Table2, 1);
        TableColumnFontChanger.setIskolaPotha(Table2, 0);
        TableColumnRightAlign.alignMany(Table2, new int[]{3, 4, 5, 6});
    }

    public CommonCashReceiptStamp(java.awt.Frame parent, boolean modal, String code) {
        this(parent, modal);
        this.code = code;
        switch (code) {

            case "02-02":
                title.setText("මුද්දර ලැබීම්");
                Table1.getColumn(0).setMaxWidth(0);
                Table2.getColumn(0).setMaxWidth(0);
                Table1.getColumn(6).setMaxWidth(0);
                Table2.getColumn(6).setMaxWidth(0);

                statusGLCode.setText(GLCodes.STAMPRECEIVINGS + "");

                break;
            case "02-04":
                title.setText("මුද්දර බැඳීම්");
                Table1.getColumn(1).setMaxWidth(0);
                Table2.getColumn(1).setMaxWidth(0);
                Table1.getColumn(5).setMaxWidth(0);
                Table2.getColumn(5).setMaxWidth(0);

                statusGLCode.setText(GLCodes.STAMPTIES + "");

                break;
        }
    }

    private CommonCashReceiptStamp(java.awt.Frame parent, boolean modal, String code, int type) {
        this(parent, modal, code);
        saveHD();
    }

    public CommonCashReceiptStamp(java.awt.Frame parent, boolean b, String code, String id) {
        this(parent, b, code);

        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(1);
            inputs.add(id);
            List<Object> output = ServerConnection.getServerConnector().searchSingleResult(inputs, "ssp_F28_LoadCombo_ViewCashierEdit", 10);

            txtNo2.setText(String.valueOf(output.get(0)));
            txtTrnDate.setText(String.valueOf(output.get(1)));
            txtNo1.setText(String.valueOf(output.get(2)));
            txtF10No.setText(String.valueOf(output.get(3)));
            txtF9DNo.setText(String.valueOf(output.get(4)));
            txtSubSection.setId(Integer.parseInt(String.valueOf(output.get(5))));
            txtSubSection.setText(String.valueOf(output.get(6)));
            txtOutSider.setId(Integer.parseInt(String.valueOf(output.get(7))));
            txtOutSider.setText(String.valueOf(output.get(8)));
            txtTotal.setText(FormatConstants.decimalFormat.format(Double.parseDouble(String.valueOf(output.get(9)))));

            viewDT(id);

        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public CommonCashReceiptStamp(java.awt.Frame parent, boolean b, String code, String id, int statusID) {
        this(parent, b, code, id);
        if (statusID != 1) {
            JButton buttons[] = {jButton1, jButton2, jButton3, jButton4};
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
            Table2.setEnabled(false);
            JTextField textFields[] = {txtDrCr, txtF10No, txtF9DNo, txtNo1, txtNo2, txtOther, txtOutSider, txtReasonOrGLAccount, txtStampCount, txtStampValue, txtSubSection, txtTotal, txtTrnDate};
            for (JTextField textField : textFields) {
                textField.setEnabled(false);
            }
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
        title = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtTotal = new org.nbs.components.SelectAtFocusFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtOutSider = new org.nbs.components.HiddenIDTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtF9DNo = new javax.swing.JTextField();
        txtTrnDate = new javax.swing.JTextField();
        txtF10No = new javax.swing.JTextField();
        txtSubSection = new org.nbs.components.HiddenIDTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtNo1 = new javax.swing.JTextField();
        txtNo2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        txtReasonOrGLAccount = new org.nbs.components.HiddenIDTextField();
        txtOther = new javax.swing.JTextField();
        txtStampValue = new javax.swing.JTextField();
        txtStampCount = new javax.swing.JTextField();
        txtDrCr = new javax.swing.JTextField();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        jLabel1 = new javax.swing.JLabel();
        statusGLCode = new javax.swing.JLabel();
        statusUserName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1209, 725));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 88, 136));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        title.setFont(new java.awt.Font("Iskoola Pota", 0, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("මුදල් ලැබීම");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Iskoola Pota", 0, 21), java.awt.Color.white)); // NOI18N

        jButton1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton1.setText("නිව්");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton2.setText("සේව්");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton3.setText("මුද්‍රණය");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtTotal.setText("0.00");
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtTotal.setHorizontalAlignment(4);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.black);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("TOTAL");

        jButton4.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton4.setText("ස්ථීර");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 81, 136)));

        txtOutSider.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtOutSider.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtOutSider.setForeground(java.awt.Color.black);
        txtOutSider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOutSiderKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOutSiderKeyReleased(evt);
            }
        });
        txtOutSider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOutSiderActionPerformed(evt);
            }
        });
        txtOutSider.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOutSiderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOutSiderFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel3.setText("නම");

        jLabel4.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel4.setText("දිනය");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("F 10");

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel6.setText("අංශය");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("F 9D No :");

        txtF9DNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtF9DNo.setText("0");
        txtF9DNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtF9DNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtF9DNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtF9DNoFocusLost(evt);
            }
        });
        txtF9DNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtF9DNoActionPerformed(evt);
            }
        });
        txtF9DNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtF9DNoKeyReleased(evt);
            }
        });

        txtTrnDate.setEditable(false);
        txtTrnDate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTrnDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtF10No.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtF10No.setText("0");
        txtF10No.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtF10No.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtF10NoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtF10NoFocusLost(evt);
            }
        });
        txtF10No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtF10NoActionPerformed(evt);
            }
        });

        txtSubSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSubSection.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtSubSection.setForeground(java.awt.Color.black);
        txtSubSection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubSectionKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubSectionKeyReleased(evt);
            }
        });
        txtSubSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubSectionActionPerformed(evt);
            }
        });
        txtSubSection.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubSectionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSubSectionFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTrnDate)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtF10No)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtF9DNo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubSection, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOutSider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtF9DNo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtOutSider, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTrnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtSubSection, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtF10No, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 81, 136)));

        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 1, 18)); // NOI18N
        jLabel9.setText("අංකය :");

        txtNo1.setEditable(false);
        txtNo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNo1.setText("0");
        txtNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNo2.setEditable(false);
        txtNo2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNo2.setText("0");
        txtNo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(null);

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ගිණුම් විස්තරය", "කාරණය", "වෙනත්", "මුද්දරයක වටිනාකම", "ගණන", "බැර", "හර", "", ""
            }
        ));
        Table1.setRowHeight(29);
        Table1.setSelectionBackground(new java.awt.Color(113, 114, 125));
        Table1.setSelectionForeground(new java.awt.Color(1, 1, 1));
        Table1.getTableHeader().setReorderingAllowed(false);
        Table1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Table1ComponentMoved(evt);
            }
        });
        Table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Table1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Table1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Table1);
        if (Table1.getColumnModel().getColumnCount() > 0) {
            Table1.getColumnModel().getColumn(0).setMinWidth(0);
            Table1.getColumnModel().getColumn(0).setPreferredWidth(400);
            Table1.getColumnModel().getColumn(0).setMaxWidth(400);
            Table1.getColumnModel().getColumn(1).setMinWidth(0);
            Table1.getColumnModel().getColumn(1).setPreferredWidth(400);
            Table1.getColumnModel().getColumn(1).setMaxWidth(400);
            Table1.getColumnModel().getColumn(2).setMinWidth(250);
            Table1.getColumnModel().getColumn(2).setPreferredWidth(250);
            Table1.getColumnModel().getColumn(2).setMaxWidth(250);
            Table1.getColumnModel().getColumn(3).setMinWidth(170);
            Table1.getColumnModel().getColumn(3).setPreferredWidth(170);
            Table1.getColumnModel().getColumn(3).setMaxWidth(170);
            Table1.getColumnModel().getColumn(4).setMinWidth(200);
            Table1.getColumnModel().getColumn(4).setPreferredWidth(200);
            Table1.getColumnModel().getColumn(4).setMaxWidth(200);
            Table1.getColumnModel().getColumn(5).setMinWidth(0);
            Table1.getColumnModel().getColumn(5).setPreferredWidth(250);
            Table1.getColumnModel().getColumn(5).setMaxWidth(250);
            Table1.getColumnModel().getColumn(6).setMinWidth(0);
            Table1.getColumnModel().getColumn(6).setPreferredWidth(250);
            Table1.getColumnModel().getColumn(6).setMaxWidth(250);
            Table1.getColumnModel().getColumn(7).setMinWidth(0);
            Table1.getColumnModel().getColumn(7).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(7).setMaxWidth(0);
            Table1.getColumnModel().getColumn(8).setMinWidth(0);
            Table1.getColumnModel().getColumn(8).setPreferredWidth(0);
            Table1.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            Table2.getColumnModel().getColumn(0).setMinWidth(0);
            Table2.getColumnModel().getColumn(0).setPreferredWidth(400);
            Table2.getColumnModel().getColumn(0).setMaxWidth(400);
            Table2.getColumnModel().getColumn(1).setMinWidth(0);
            Table2.getColumnModel().getColumn(1).setPreferredWidth(400);
            Table2.getColumnModel().getColumn(1).setMaxWidth(400);
            Table2.getColumnModel().getColumn(2).setMinWidth(250);
            Table2.getColumnModel().getColumn(2).setPreferredWidth(250);
            Table2.getColumnModel().getColumn(2).setMaxWidth(250);
            Table2.getColumnModel().getColumn(3).setMinWidth(170);
            Table2.getColumnModel().getColumn(3).setPreferredWidth(170);
            Table2.getColumnModel().getColumn(3).setMaxWidth(170);
            Table2.getColumnModel().getColumn(4).setMinWidth(200);
            Table2.getColumnModel().getColumn(4).setPreferredWidth(200);
            Table2.getColumnModel().getColumn(4).setMaxWidth(200);
            Table2.getColumnModel().getColumn(5).setMinWidth(0);
            Table2.getColumnModel().getColumn(5).setPreferredWidth(250);
            Table2.getColumnModel().getColumn(5).setMaxWidth(250);
            Table2.getColumnModel().getColumn(6).setMinWidth(0);
            Table2.getColumnModel().getColumn(6).setPreferredWidth(250);
            Table2.getColumnModel().getColumn(6).setMaxWidth(250);
            Table2.getColumnModel().getColumn(7).setMinWidth(0);
            Table2.getColumnModel().getColumn(7).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(7).setMaxWidth(0);
            Table2.getColumnModel().getColumn(8).setMinWidth(0);
            Table2.getColumnModel().getColumn(8).setPreferredWidth(0);
            Table2.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2));

        txtReasonOrGLAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtReasonOrGLAccount.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtReasonOrGLAccount.setMaximumSize(new java.awt.Dimension(350, 30));
        txtReasonOrGLAccount.setMinimumSize(new java.awt.Dimension(350, 30));
        txtReasonOrGLAccount.setPreferredSize(new java.awt.Dimension(350, 30));
        txtReasonOrGLAccount.setSize(new java.awt.Dimension(350, 30));
        txtReasonOrGLAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReasonOrGLAccountActionPerformed(evt);
            }
        });
        txtReasonOrGLAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtReasonOrGLAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtReasonOrGLAccountFocusLost(evt);
            }
        });

        txtOther.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtOther.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtOther.setMaximumSize(new java.awt.Dimension(250, 30));
        txtOther.setMinimumSize(new java.awt.Dimension(250, 30));
        txtOther.setPreferredSize(new java.awt.Dimension(250, 30));
        txtOther.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOtherFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOtherFocusLost(evt);
            }
        });
        txtOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOtherActionPerformed(evt);
            }
        });

        txtStampValue.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtStampValue.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtStampValue.setText("0.00");
        txtStampValue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtStampValue.setMaximumSize(new java.awt.Dimension(170, 30));
        txtStampValue.setMinimumSize(new java.awt.Dimension(170, 30));
        txtStampValue.setPreferredSize(new java.awt.Dimension(170, 30));
        txtStampValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStampValueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStampValueFocusLost(evt);
            }
        });
        txtStampValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStampValueActionPerformed(evt);
            }
        });

        txtStampCount.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtStampCount.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtStampCount.setText("0");
        txtStampCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtStampCount.setMaximumSize(new java.awt.Dimension(200, 30));
        txtStampCount.setMinimumSize(new java.awt.Dimension(200, 30));
        txtStampCount.setPreferredSize(new java.awt.Dimension(200, 30));
        txtStampCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStampCountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStampCountFocusLost(evt);
            }
        });
        txtStampCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStampCountActionPerformed(evt);
            }
        });

        txtDrCr.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtDrCr.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDrCr.setText("0.00");
        txtDrCr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDrCr.setMaximumSize(new java.awt.Dimension(223, 30));
        txtDrCr.setMinimumSize(new java.awt.Dimension(223, 30));
        txtDrCr.setPreferredSize(new java.awt.Dimension(223, 30));
        txtDrCr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDrCrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDrCrFocusLost(evt);
            }
        });
        txtDrCr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDrCrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtReasonOrGLAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtStampValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtStampCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtDrCr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReasonOrGLAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStampValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStampCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDrCr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setText("ONLINE");

        statusUserName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        javax.swing.GroupLayout jXStatusBar1Layout = new javax.swing.GroupLayout(jXStatusBar1);
        jXStatusBar1.setLayout(jXStatusBar1Layout);
        jXStatusBar1Layout.setHorizontalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXStatusBar1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusGLCode, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jXStatusBar1Layout.setVerticalGroup(
            jXStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusGLCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXStatusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txtSubSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubSectionActionPerformed
    new FindSectionOrOutsider(this, true, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL, TableHeaders.SUBSECTIONFINDER_TABLE_MODEL2, 26, "අනු අංශය").setVisible(true);
}//GEN-LAST:event_txtSubSectionActionPerformed

private void txtSubSectionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSectionKeyReleased

}//GEN-LAST:event_txtSubSectionKeyReleased

private void txtSubSectionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubSectionKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_txtSubSectionKeyTyped

private void txtOutSiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutSiderActionPerformed
    new FindSectionOrOutsider(this, true, TableHeaders.OUTSIDERFINDER_TABLE_MODEL, TableHeaders.OUTSIDERFINDER_TABLE_MODEL2, 27, "පාර්ශවකරු").setVisible(true);
}//GEN-LAST:event_txtOutSiderActionPerformed

private void txtOutSiderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOutSiderKeyReleased

}//GEN-LAST:event_txtOutSiderKeyReleased

private void txtOutSiderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOutSiderKeyTyped
    //  char c=evt.getKeyChar();
    //  evt.setKeyChar(Character.toUpperCase(c));

}//GEN-LAST:event_txtOutSiderKeyTyped

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    saveHD();
    txtF10No.grabFocus();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    saveHD();
    int i = JOptionPane.showConfirmDialog(this, "නව ගණුදෙනුවක් සිදු කරනවාද?", "නව ගණුදෙනුවක් සිදු කරනවාද?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (i == JOptionPane.YES_OPTION) {
        this.dispose();
        CommonJurnalForm.ItemRefresh.doClick();
        new CommonCashReceiptStamp(null, true, code, 1).setVisible(true);
    } else {
        this.dispose();
        CommonJurnalForm.ItemRefresh.doClick();
    }
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    try {
        JasperReport compileReport = JasperCompileManager.compileReport(ReportFileLocator.locateReportFile("/R007_F28_F10.jrxml"));
        Map params = new HashMap<Object, Object>();
        params.put("Oth_Trn_HDID", Integer.parseInt(txtNo2.getText()));
        params.put("MPCSName", data.getBranchName());
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DB.getConnection());
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewer.setVisible(true);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JRException ex) {
        Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    confirm(txtNo2.getText(), statusGLCode.getText());
}//GEN-LAST:event_jButton4ActionPerformed

private void txtF9DNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtF9DNoKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtF9DNoKeyReleased

    private void Table1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Table1ComponentMoved
        jScrollPane2.getViewport().setViewPosition(jScrollPane1.getViewport().getViewPosition());
    }//GEN-LAST:event_Table1ComponentMoved

    private void Table1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyReleased

    }//GEN-LAST:event_Table1KeyReleased

    private void Table2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseReleased

    }//GEN-LAST:event_Table2MouseReleased

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        if (Table2.getSelectedRow() > -1) {
            int selectedRow = Table2.getSelectedRow();
            if (code.equals("02-02")) {
                txtReasonOrGLAccount.setText(String.valueOf(Table2.getValueAt(selectedRow, 1)));
            } else {
                txtReasonOrGLAccount.setText(String.valueOf(Table2.getValueAt(selectedRow, 0)));
            }
            txtOther.setText(String.valueOf(Table2.getValueAt(selectedRow, 2)));
            txtStampValue.setText(String.valueOf(Table2.getValueAt(selectedRow, 3)));
            txtStampCount.setText(String.valueOf(Table2.getValueAt(selectedRow, 4)));
            if (code.equals("02-02")) {
                txtDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 5)));
            } else {
                txtDrCr.setText(String.valueOf(Table2.getValueAt(selectedRow, 6)));
            }
            txtReasonOrGLAccount.setId(Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 7))));
            dtid = Integer.parseInt(String.valueOf(Table2.getValueAt(selectedRow, 8)));
        }
    }//GEN-LAST:event_Table2MouseClicked

    private void Table2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyReleased

    }//GEN-LAST:event_Table2KeyReleased

    private void Table1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Table1KeyTyped

    private void Table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyPressed

    }//GEN-LAST:event_Table1KeyPressed

    private void txtF10NoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF10NoActionPerformed
        txtF9DNo.grabFocus();
    }//GEN-LAST:event_txtF10NoActionPerformed

    private void txtF9DNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF9DNoActionPerformed
        txtSubSection.grabFocus();
    }//GEN-LAST:event_txtF9DNoActionPerformed

    private void txtReasonOrGLAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReasonOrGLAccountActionPerformed
        if (code.equals("02-02")) {
            new FindSectionOrOutsider(this, true, TableHeaders.REASON_TABLE_MODEL, TableHeaders.REASON_TABLE_MODEL2, 25, "කාරණය", "F10").setVisible(true);
        } else if (code.equals("02-04")) {
            new FindAccount5Data(this, true, 6).setVisible(true);
        }
    }//GEN-LAST:event_txtReasonOrGLAccountActionPerformed

    private void txtOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtherActionPerformed
        txtStampValue.grabFocus();
    }//GEN-LAST:event_txtOtherActionPerformed

    private void txtStampValueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStampValueFocusGained
        txtStampValue.selectAll();
        txtStampValue.setBackground(Color.YELLOW);
        if (txtReasonOrGLAccount.getId() == 0) {
            if (code.equals("02-02")) {
                JOptionPane.showMessageDialog(this, "කාරණය ඇතුලත් කර නොමැත", "කාරණය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
            } else if (code.equals("02-04")) {
                JOptionPane.showMessageDialog(this, "ගිණුම් විස්තරය ඇතුලත් කර නොමැත", "ගිණුම් විස්තරය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
            }
            txtReasonOrGLAccount.grabFocus();
        }
    }//GEN-LAST:event_txtStampValueFocusGained

    private void txtStampValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStampValueFocusLost
        txtStampValue.select(0, 0);
        txtStampValue.setBackground(Color.WHITE);
        calStamps();
    }//GEN-LAST:event_txtStampValueFocusLost

    private void txtStampValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStampValueActionPerformed
        txtStampCount.grabFocus();
    }//GEN-LAST:event_txtStampValueActionPerformed

    private void txtStampCountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStampCountFocusGained
        txtStampCount.selectAll();
        txtStampCount.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtStampCountFocusGained

    private void txtStampCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStampCountFocusLost
        txtStampCount.select(0, 0);
        txtStampCount.setBackground(Color.WHITE);
        calStamps();
    }//GEN-LAST:event_txtStampCountFocusLost

    private void txtStampCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStampCountActionPerformed
        txtDrCr.grabFocus();
    }//GEN-LAST:event_txtStampCountActionPerformed

    private void txtDrCrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDrCrFocusGained
        txtDrCr.selectAll();
        txtDrCr.setBackground(Color.YELLOW);
        calStamps();
    }//GEN-LAST:event_txtDrCrFocusGained

    private void txtDrCrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDrCrFocusLost
        txtDrCr.select(0, 0);
        txtDrCr.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDrCrFocusLost

    private void txtF10NoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF10NoFocusGained
        txtF10No.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtF10NoFocusGained

    private void txtF10NoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF10NoFocusLost
        txtF10No.setBackground(Color.WHITE);
        if (txtF10No.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "F10 අංකය ඇතුලත් කර නොමැත", "අංකය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
            txtF10No.grabFocus();
        }
    }//GEN-LAST:event_txtF10NoFocusLost

    private void txtF9DNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF9DNoFocusGained
        txtF9DNo.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtF9DNoFocusGained

    private void txtF9DNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtF9DNoFocusLost
        txtF9DNo.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtF9DNoFocusLost

    private void txtSubSectionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubSectionFocusGained
        txtSubSection.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtSubSectionFocusGained

    private void txtSubSectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubSectionFocusLost
        txtSubSection.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtSubSectionFocusLost

    private void txtOutSiderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOutSiderFocusGained
        txtOutSider.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtOutSiderFocusGained

    private void txtOutSiderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOutSiderFocusLost
        txtOutSider.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtOutSiderFocusLost

    private void txtReasonOrGLAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonOrGLAccountFocusGained
        txtReasonOrGLAccount.setBackground(Color.YELLOW);
    }//GEN-LAST:event_txtReasonOrGLAccountFocusGained

    private void txtReasonOrGLAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtReasonOrGLAccountFocusLost
        txtReasonOrGLAccount.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtReasonOrGLAccountFocusLost

    private void txtOtherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtherFocusGained
        txtOther.setBackground(Color.YELLOW);
        if (txtReasonOrGLAccount.getId() == 0) {
            if (code.equals("02-02")) {
                JOptionPane.showMessageDialog(this, "කාරණය ඇතුලත් කර නොමැත", "කාරණය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
            } else if (code.equals("02-04")) {
                JOptionPane.showMessageDialog(this, "ගිණුම් විස්තරය ඇතුලත් කර නොමැත", "ගිණුම් විස්තරය ඇතුලත් කර නොමැත", JOptionPane.ERROR_MESSAGE);
            }
            txtReasonOrGLAccount.grabFocus();
        }
    }//GEN-LAST:event_txtOtherFocusGained

    private void txtOtherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOtherFocusLost
        txtOther.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtOtherFocusLost

    private void txtDrCrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDrCrActionPerformed
        txtReasonOrGLAccount.grabFocus();
        saveHD();
        saveDT();
        viewDT(txtNo2.getText());
        getTotal();
        initRowData();
    }//GEN-LAST:event_txtDrCrActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        CommonJurnalForm.ItemRefresh.doClick();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JDialog dialog = new CommonCashReceiptStamp(new javax.swing.JFrame(), true);
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
    public static org.jdesktop.swingx.JXTable Table1;
    private org.jdesktop.swingx.JXTable Table2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private javax.swing.JLabel statusGLCode;
    private javax.swing.JLabel statusUserName;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtDrCr;
    private javax.swing.JTextField txtF10No;
    private javax.swing.JTextField txtF9DNo;
    private javax.swing.JTextField txtNo1;
    private javax.swing.JTextField txtNo2;
    public static javax.swing.JTextField txtOther;
    public static org.nbs.components.HiddenIDTextField txtOutSider;
    public static org.nbs.components.HiddenIDTextField txtReasonOrGLAccount;
    private javax.swing.JTextField txtStampCount;
    private javax.swing.JTextField txtStampValue;
    public static org.nbs.components.HiddenIDTextField txtSubSection;
    private org.nbs.components.SelectAtFocusFormattedTextField txtTotal;
    private javax.swing.JTextField txtTrnDate;
    // End of variables declaration//GEN-END:variables

    private void saveHD() {
        List<Object> inputs = new ArrayList<>();
        try {
            inputs.add(txtNo2.getText());
            inputs.add(txtTrnDate.getText());
            inputs.add(txtNo1.getText());
            inputs.add(txtF10No.getText());
            inputs.add(txtF9DNo.getText());
            inputs.add(txtSubSection.getId());
            inputs.add(statusGLCode.getText());
            inputs.add(txtOutSider.getId());
            inputs.add(1);
            inputs.add(FormatConstants.decimalFormat.parse(txtTotal.getText()).doubleValue());
            switch (code) {
                case "02-02":
                    inputs.add(FormatConstants.decimalFormat.parse(txtTotal.getText()).doubleValue());
                    inputs.add(0);
                    break;
                case "02-04":
                    inputs.add(0);
                    inputs.add(FormatConstants.decimalFormat.parse(txtTotal.getText()).doubleValue());
                    break;
            }
            inputs.add("");
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add("@F10");
            inputs.add("@recID");
            inputs.add("@billNo");
            List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranHD", 3);
//            hdID = String.valueOf(output.get(0));
            txtNo1.setText(String.valueOf(output.get(2)));
            txtNo2.setText(String.valueOf(output.get(1)));
            txtF10No.setText(String.valueOf(output.get(0)));

        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int dtid = 0;

    private void saveDT() {

        try {
            List inputs = new ArrayList<>();
            inputs.add(dtid);
            inputs.add(txtNo2.getText());
            inputs.add(txtSubSection.getId());
            inputs.add(txtOutSider.getId());
            inputs.add(statusGLCode.getText());
            if (code.equals("02-02")) {
                inputs.add(txtReasonOrGLAccount.getId());
            } else {
                inputs.add(0);
            }
            inputs.add(false);
            inputs.add(txtOther.getText());
            if (code.equals("02-04")) {
                inputs.add(txtReasonOrGLAccount.getId());
            } else {
                inputs.add(0);
            }
            inputs.add("");
            switch (code) {
                case "02-02":
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    inputs.add(0);
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    break;
                case "02-04":
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    inputs.add(0);
                    inputs.add(FormatConstants.numberFormat1.parse(txtDrCr.getText()).doubleValue());
                    break;
            }
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(0);
            inputs.add(FormatConstants.numberFormat1.parse(txtStampValue.getText()).doubleValue());
            inputs.add(txtStampCount.getText());
            inputs.add(GeneralUserLogin.data.getUsername());
            if (txtReasonOrGLAccount.getId() != 0 ) {
                ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Insert_CashierTranDT", 0);
                dtid = 0;
            }else{
                JOptionPane.showMessageDialog(this, "ගිණුම් විස්තරය ඇතුලත් කර නොමැත");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void calStamps() {
        double stampValue = Double.parseDouble(txtStampValue.getText());
        double stampCount = Double.parseDouble(txtStampCount.getText());
        double stampTotal = stampValue * stampCount;
        txtDrCr.setText(FormatConstants.numberFormat1.format(stampTotal));
    }

    private void getTotal() {
        double total = 0;
        if (code.equals("02-02")) {
            for (int i = 0; i < Table2.getRowCount(); i++) {
                try {
                    total += FormatConstants.decimalFormat.parse(String.valueOf(Table2.getValueAt(i, 5))).doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (code.equals("02-04")) {
            for (int i = 0; i < Table2.getRowCount(); i++) {
                try {
                    total += FormatConstants.decimalFormat.parse(String.valueOf(Table2.getValueAt(i, 6))).doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        txtTotal.setText(FormatConstants.decimalFormat.format(total));
    }

    private void viewDT(String id) {
        try {
            dtm2.setRowCount(0);
            List<Object> inputs = new ArrayList<>();
            inputs.add(3);
            inputs.add(id);

            List<List<Object>> outputs = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_ViewCashierEdit", 9);
            for (List rowData : outputs) {
                Vector v = new Vector();
                for (Object object : rowData) {
                    v.add(object);
                }
                dtm2.addRow(v);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptStamp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initRowData() {
        JTextField fields[] = {txtStampValue, txtStampCount, txtDrCr};
        for (JTextField field : fields) {
            field.setText("0");
        }
        txtReasonOrGLAccount.setText("");
        txtReasonOrGLAccount.setId(0);
        txtOther.setText("");
        txtDrCr.setText("0.00");
    }

    private void confirm(String recID, String glcode) {
        try {
            List<Object> inputs = new ArrayList<>();
            inputs.add(glcode);
            inputs.add(recID);
            inputs.add(GeneralUserLogin.data.getUsername());
            inputs.add("@HDID");
            List<Object> output = ServerConnection.getServerConnector().insertData(inputs, "ssp_F28_Create_Journals_All", 1);
            if (Integer.parseInt(String.valueOf(output.get(0))) > 0) {
                JOptionPane.showMessageDialog(this, "ස්ථිර කිරීම සාර්ථකයි", "සාර්ථකයි", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "ස්ථිර කිරීම අසාර්ථකයි", "අසාර්ථකයි", JOptionPane.ERROR_MESSAGE);
            }
            CommonJurnalForm.ItemRefresh.doClick();
        } catch (SQLException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CommonCashReceiptCash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
