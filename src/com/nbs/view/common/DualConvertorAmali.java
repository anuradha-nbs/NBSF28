/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common;

import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.text.JTextComponent;

/**
 *
 * @author mmh
 */
public class DualConvertorAmali extends javax.swing.JDialog {

    private int nVowels;
    private String consonants[];
    private String consonantsUni[];
    private String vowels[];
    private String vowelsUni[];
    private String vowelModifiersUni[];
    private String specialConsonants[];
    private String specialConsonantsUni[];
    private String specialCharUni[];
    private String specialChar[];
    JTextComponent amaliTextComponent;

    /**
     * Creates new form DualConvertorAmali
     */
    DualConvertorAmali(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        consonants = new String[46];
        consonantsUni = new String[46];
        vowels = new String[26];
        vowelsUni = new String[26];
        vowelModifiersUni = new String[26];
        specialConsonants = new String[6];
        specialConsonantsUni = new String[6];
        specialCharUni = new String[2];
        specialChar = new String[2];

        vowelsUni[0] = "ඌ";
        vowels[0] = "oo";
        vowelModifiersUni[0] = "ූ";
        vowelsUni[1] = "ඕ";
        vowels[1] = "o\\)";
        vowelModifiersUni[1] = "ෝ";
        vowelsUni[2] = "ඕ";
        vowels[2] = "oe";
        vowelModifiersUni[2] = "ෝ";
        vowelsUni[3] = "ආ";
        vowels[3] = "aa";
        vowelModifiersUni[3] = "ා";
        vowelsUni[4] = "ආ";
        vowels[4] = "a\\)";
        vowelModifiersUni[4] = "ා";
        vowelsUni[5] = "ඈ";
        vowels[5] = "Aa";
        vowelModifiersUni[5] = "ෑ";
        vowelsUni[6] = "ඈ";
        vowels[6] = "A\\)";
        vowelModifiersUni[6] = "ෑ";
        vowelsUni[7] = "ඈ";
        vowels[7] = "ae";
        vowelModifiersUni[7] = "ෑ";
        vowelsUni[8] = "ඊ";
        vowels[8] = "ii";
        vowelModifiersUni[8] = "ී";
        vowelsUni[9] = "ඊ";
        vowels[9] = "i\\)";
        vowelModifiersUni[9] = "ී";
        vowelsUni[10] = "ඊ";
        vowels[10] = "ie";
        vowelModifiersUni[10] = "ී";
        vowelsUni[11] = "ඊ";
        vowels[11] = "ee";
        vowelModifiersUni[11] = "ී";
        vowelsUni[12] = "ඒ";
        vowels[12] = "ea";
        vowelModifiersUni[12] = "ේ";
        vowelsUni[13] = "ඒ";
        vowels[13] = "e\\)";
        vowelModifiersUni[13] = "ේ";
        vowelsUni[14] = "ඒ";
        vowels[14] = "ei";
        vowelModifiersUni[14] = "ේ";
        vowelsUni[15] = "ඌ";
        vowels[15] = "uu";
        vowelModifiersUni[15] = "ූ";
        vowelsUni[16] = "ඌ";
        vowels[16] = "u\\)";
        vowelModifiersUni[16] = "ූ";
        vowelsUni[17] = "ඖ";
        vowels[17] = "au";
        vowelModifiersUni[17] = "ෞ";
        vowelsUni[18] = "ඇ";
        vowels[18] = "/a";
        vowelModifiersUni[18] = "ැ";

        vowelsUni[19] = "අ";
        vowels[19] = "a";
        vowelModifiersUni[19] = "";
        vowelsUni[20] = "ඇ";
        vowels[20] = "A";
        vowelModifiersUni[20] = "ැ";
        vowelsUni[21] = "ඉ";
        vowels[21] = "i";
        vowelModifiersUni[21] = "ි";
        vowelsUni[22] = "එ";
        vowels[22] = "e";
        vowelModifiersUni[22] = "ෙ";
        vowelsUni[23] = "උ";
        vowels[23] = "u";
        vowelModifiersUni[23] = "ු";
        vowelsUni[24] = "ඔ";
        vowels[24] = "o";
        vowelModifiersUni[24] = "ො";
        vowelsUni[25] = "ඓ";
        vowels[25] = "I";
        vowelModifiersUni[25] = "ෛ";
        nVowels = 26;

        specialConsonantsUni[0] = "ං";
        specialConsonants[0] = "/n/g";
        specialConsonantsUni[1] = "ඃ";
        specialConsonants[1] = "/h/g";
        specialConsonantsUni[2] = "ඞ";
        specialConsonants[2] = "/N/g";
        specialConsonantsUni[3] = "ඍ";
        specialConsonants[3] = "/R/g";
        //special characher Repaya
        specialConsonantsUni[4] = "ර්" + "\u200D";
        specialConsonants[4] = "/R/g";
        specialConsonantsUni[5] = "ර්" + "\u200D";
        specialConsonants[5] = "/r/g";

        consonantsUni[0] = "ඬ";
        consonants[0] = "nnd";
        consonantsUni[1] = "ඳ";
        consonants[1] = "nndh";
        consonantsUni[2] = "ඟ";
        consonants[2] = "nng";
        consonantsUni[3] = "ථ";
        consonants[3] = "Th";
        consonantsUni[4] = "ධ";
        consonants[4] = "Dh";
        consonantsUni[5] = "ඝ";
        consonants[5] = "gh";
        consonantsUni[6] = "ඡ";
        consonants[6] = "Ch";
        consonantsUni[7] = "ඵ";
        consonants[7] = "ph";
        consonantsUni[8] = "භ";
        consonants[8] = "bh";
        consonantsUni[9] = "ශ";
        consonants[9] = "sh";
        consonantsUni[10] = "ෂ";
        consonants[10] = "Sh";
        consonantsUni[11] = "ඥ";
        consonants[11] = "GN";
        consonantsUni[12] = "ඤ";
        consonants[12] = "KN";
        consonantsUni[13] = "ළු";
        consonants[13] = "Lu";
        consonantsUni[14] = "ද";
        consonants[14] = "dh";
        consonantsUni[15] = "ච";
        consonants[15] = "ch";
        consonantsUni[16] = "ඛ";
        consonants[16] = "kh";
        consonantsUni[17] = "ත";
        consonants[17] = "th";

        consonantsUni[18] = "ට";
        consonants[18] = "t";
        consonantsUni[19] = "ක";
        consonants[19] = "k";
        consonantsUni[20] = "ඩ";
        consonants[20] = "d";
        consonantsUni[21] = "න";
        consonants[21] = "n";
        consonantsUni[22] = "ප";
        consonants[22] = "p";
        consonantsUni[23] = "බ";
        consonants[23] = "b";
        consonantsUni[24] = "ම";
        consonants[24] = "m";
        consonantsUni[25] = "‍ය";
        consonants[25] = "\\u005C" + "y";
        consonantsUni[26] = "‍ය";
        consonants[26] = "Y";
        consonantsUni[27] = "ය";
        consonants[27] = "y";
        consonantsUni[28] = "ජ";
        consonants[28] = "j";
        consonantsUni[29] = "ල";
        consonants[29] = "l";
        consonantsUni[30] = "ව";
        consonants[30] = "v";
        consonantsUni[31] = "ව";
        consonants[31] = "w";
        consonantsUni[32] = "ස";
        consonants[32] = "s";
        consonantsUni[33] = "හ";
        consonants[33] = "h";
        consonantsUni[34] = "ණ";
        consonants[34] = "N";
        consonantsUni[35] = "ළ";
        consonants[35] = "L";
        consonantsUni[36] = "ඛ";
        consonants[36] = "K";
        consonantsUni[37] = "ඝ";
        consonants[37] = "G";
        consonantsUni[38] = "ඨ";
        consonants[38] = "T";
        consonantsUni[39] = "ඪ";
        consonants[39] = "D";
        consonantsUni[40] = "ඵ";
        consonants[40] = "P";
        consonantsUni[41] = "ඹ";
        consonants[41] = "B";
        consonantsUni[42] = "ෆ";
        consonants[42] = "f";
        consonantsUni[43] = "ඣ";
        consonants[43] = "q";
        consonantsUni[44] = "ග";
        consonants[44] = "g";
        //last because we need to ommit this in dealing with Rakaransha
        consonantsUni[45] = "ර";
        consonants[45] = "r";

        specialCharUni[0] = "ෲ";
        specialChar[0] = "ruu";
        specialCharUni[1] = "ෘ";
        specialChar[1] = "ru";
    }

    public DualConvertorAmali(Frame owner, boolean modal, JTextComponent amaliTextComponent) {
        this(owner, modal);
        this.amaliTextComponent = amaliTextComponent;
    }

    public DualConvertorAmali(Dialog owner, boolean modal, JTextComponent amaliTextComponent) {
        super(owner, modal);
        initComponents();
        consonants = new String[46];
        consonantsUni = new String[46];
        vowels = new String[26];
        vowelsUni = new String[26];
        vowelModifiersUni = new String[26];
        specialConsonants = new String[6];
        specialConsonantsUni = new String[6];
        specialCharUni = new String[2];
        specialChar = new String[2];

        vowelsUni[0] = "ඌ";
        vowels[0] = "oo";
        vowelModifiersUni[0] = "ූ";
        vowelsUni[1] = "ඕ";
        vowels[1] = "o\\)";
        vowelModifiersUni[1] = "ෝ";
        vowelsUni[2] = "ඕ";
        vowels[2] = "oe";
        vowelModifiersUni[2] = "ෝ";
        vowelsUni[3] = "ආ";
        vowels[3] = "aa";
        vowelModifiersUni[3] = "ා";
        vowelsUni[4] = "ආ";
        vowels[4] = "a\\)";
        vowelModifiersUni[4] = "ා";
        vowelsUni[5] = "ඈ";
        vowels[5] = "Aa";
        vowelModifiersUni[5] = "ෑ";
        vowelsUni[6] = "ඈ";
        vowels[6] = "A\\)";
        vowelModifiersUni[6] = "ෑ";
        vowelsUni[7] = "ඈ";
        vowels[7] = "ae";
        vowelModifiersUni[7] = "ෑ";
        vowelsUni[8] = "ඊ";
        vowels[8] = "ii";
        vowelModifiersUni[8] = "ී";
        vowelsUni[9] = "ඊ";
        vowels[9] = "i\\)";
        vowelModifiersUni[9] = "ී";
        vowelsUni[10] = "ඊ";
        vowels[10] = "ie";
        vowelModifiersUni[10] = "ී";
        vowelsUni[11] = "ඊ";
        vowels[11] = "ee";
        vowelModifiersUni[11] = "ී";
        vowelsUni[12] = "ඒ";
        vowels[12] = "ea";
        vowelModifiersUni[12] = "ේ";
        vowelsUni[13] = "ඒ";
        vowels[13] = "e\\)";
        vowelModifiersUni[13] = "ේ";
        vowelsUni[14] = "ඒ";
        vowels[14] = "ei";
        vowelModifiersUni[14] = "ේ";
        vowelsUni[15] = "ඌ";
        vowels[15] = "uu";
        vowelModifiersUni[15] = "ූ";
        vowelsUni[16] = "ඌ";
        vowels[16] = "u\\)";
        vowelModifiersUni[16] = "ූ";
        vowelsUni[17] = "ඖ";
        vowels[17] = "au";
        vowelModifiersUni[17] = "ෞ";
        vowelsUni[18] = "ඇ";
        vowels[18] = "/a";
        vowelModifiersUni[18] = "ැ";

        vowelsUni[19] = "අ";
        vowels[19] = "a";
        vowelModifiersUni[19] = "";
        vowelsUni[20] = "ඇ";
        vowels[20] = "A";
        vowelModifiersUni[20] = "ැ";
        vowelsUni[21] = "ඉ";
        vowels[21] = "i";
        vowelModifiersUni[21] = "ි";
        vowelsUni[22] = "එ";
        vowels[22] = "e";
        vowelModifiersUni[22] = "ෙ";
        vowelsUni[23] = "උ";
        vowels[23] = "u";
        vowelModifiersUni[23] = "ු";
        vowelsUni[24] = "ඔ";
        vowels[24] = "o";
        vowelModifiersUni[24] = "ො";
        vowelsUni[25] = "ඓ";
        vowels[25] = "I";
        vowelModifiersUni[25] = "ෛ";
        nVowels = 26;

        specialConsonantsUni[0] = "ං";
        specialConsonants[0] = "/n/g";
        specialConsonantsUni[1] = "ඃ";
        specialConsonants[1] = "/h/g";
        specialConsonantsUni[2] = "ඞ";
        specialConsonants[2] = "/N/g";
        specialConsonantsUni[3] = "ඍ";
        specialConsonants[3] = "/R/g";
        //special characher Repaya
        specialConsonantsUni[4] = "ර්" + "\u200D";
        specialConsonants[4] = "/R/g";
        specialConsonantsUni[5] = "ර්" + "\u200D";
        specialConsonants[5] = "/r/g";

        consonantsUni[0] = "ඬ";
        consonants[0] = "nnd";
        consonantsUni[1] = "ඳ";
        consonants[1] = "nndh";
        consonantsUni[2] = "ඟ";
        consonants[2] = "nng";
        consonantsUni[3] = "ථ";
        consonants[3] = "Th";
        consonantsUni[4] = "ධ";
        consonants[4] = "Dh";
        consonantsUni[5] = "ඝ";
        consonants[5] = "gh";
        consonantsUni[6] = "ඡ";
        consonants[6] = "Ch";
        consonantsUni[7] = "ඵ";
        consonants[7] = "ph";
        consonantsUni[8] = "භ";
        consonants[8] = "bh";
        consonantsUni[9] = "ශ";
        consonants[9] = "sh";
        consonantsUni[10] = "ෂ";
        consonants[10] = "Sh";
        consonantsUni[11] = "ඥ";
        consonants[11] = "GN";
        consonantsUni[12] = "ඤ";
        consonants[12] = "KN";
        consonantsUni[13] = "ළු";
        consonants[13] = "Lu";
        consonantsUni[14] = "ද";
        consonants[14] = "dh";
        consonantsUni[15] = "ච";
        consonants[15] = "ch";
        consonantsUni[16] = "ඛ";
        consonants[16] = "kh";
        consonantsUni[17] = "ත";
        consonants[17] = "th";

        consonantsUni[18] = "ට";
        consonants[18] = "t";
        consonantsUni[19] = "ක";
        consonants[19] = "k";
        consonantsUni[20] = "ඩ";
        consonants[20] = "d";
        consonantsUni[21] = "න";
        consonants[21] = "n";
        consonantsUni[22] = "ප";
        consonants[22] = "p";
        consonantsUni[23] = "බ";
        consonants[23] = "b";
        consonantsUni[24] = "ම";
        consonants[24] = "m";
        consonantsUni[25] = "‍ය";
        consonants[25] = "\\u005C" + "y";
        consonantsUni[26] = "‍ය";
        consonants[26] = "Y";
        consonantsUni[27] = "ය";
        consonants[27] = "y";
        consonantsUni[28] = "ජ";
        consonants[28] = "j";
        consonantsUni[29] = "ල";
        consonants[29] = "l";
        consonantsUni[30] = "ව";
        consonants[30] = "v";
        consonantsUni[31] = "ව";
        consonants[31] = "w";
        consonantsUni[32] = "ස";
        consonants[32] = "s";
        consonantsUni[33] = "හ";
        consonants[33] = "h";
        consonantsUni[34] = "ණ";
        consonants[34] = "N";
        consonantsUni[35] = "ළ";
        consonants[35] = "L";
        consonantsUni[36] = "ඛ";
        consonants[36] = "K";
        consonantsUni[37] = "ඝ";
        consonants[37] = "G";
        consonantsUni[38] = "ඨ";
        consonants[38] = "T";
        consonantsUni[39] = "ඪ";
        consonants[39] = "D";
        consonantsUni[40] = "ඵ";
        consonants[40] = "P";
        consonantsUni[41] = "ඹ";
        consonants[41] = "B";
        consonantsUni[42] = "ෆ";
        consonants[42] = "f";
        consonantsUni[43] = "ඣ";
        consonants[43] = "q";
        consonantsUni[44] = "ග";
        consonants[44] = "g";
        //last because we need to ommit this in dealing with Rakaransha
        consonantsUni[45] = "ර";
        consonants[45] = "r";

        specialCharUni[0] = "ෲ";
        specialChar[0] = "ruu";
        specialCharUni[1] = "ෘ";
        specialChar[1] = "ru";
        this.amaliTextComponent = amaliTextComponent;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAma = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSinglish = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtAma.setColumns(20);
        txtAma.setFont(new java.awt.Font("AMALEE", 0, 18)); // NOI18N
        txtAma.setRows(5);
        txtAma.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtAma);

        txtSinglish.setColumns(20);
        txtSinglish.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtSinglish.setRows(5);
        txtSinglish.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSinglishKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(txtSinglish);

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel1.setText("සිංග්ලිශ් :");

        jLabel3.setFont(new java.awt.Font("AMALEE", 1, 22)); // NOI18N
        jLabel3.setText("wud,s #");

        jButton1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jButton1.setText("Set");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSinglishKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSinglishKeyReleased
        startText();
    }//GEN-LAST:event_txtSinglishKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
            java.util.logging.Logger.getLogger(DualConvertorAmali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DualConvertorAmali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DualConvertorAmali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DualConvertorAmali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DualConvertorAmali dialog = new DualConvertorAmali(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JTextArea txtAma;
    private static javax.swing.JTextArea txtSinglish;
    // End of variables declaration//GEN-END:variables
private void createAmalee(String text) {
        String replaced = "";
        text = text.replace(",", "￦");
        text = text.replace(".", "�");
        text = text.replace("(", "￫");
        text = text.replace(")", "￩");
        text = text.replace("%", "ￕ");
        text = text.replace("/", "$");
        text = text.replace("–", "ￔ");
        text = text.replace("?", "ￓ");
        text = text.replace("!", "ￒ");
        text = text.replace("=", "ￏ");
        text = text.replace("'", "ￎ");
        text = text.replace("+", "ￍ");
        text = text.replace(":", "ￌ");
        text = text.replace("÷", "ￋ");
        text = text.replace(";", "ﾶ");
        text = text.replace("ත්‍රෛ", "ff;%");
        text = text.replace("ශෛ", "ffY");
        text = text.replace("චෛ", "ffp");
        text = text.replace("ජෛ", "ffc");
        text = text.replace("කෛ", "ffl");
        text = text.replace("මෛ", "ffu");
        text = text.replace("පෛ", "ffm");
        text = text.replace("දෛ", "ffo");
        text = text.replace("තෛ", "ff;");
        text = text.replace("නෛ", "ffk");
        text = text.replace("ධෛ", "ffO");
        text = text.replace("වෛ", "ffj");
        text = text.replace("ප්‍රෞ", "fm%!");
        text = text.replace("ෂ්‍යෝ", "fIHda");
        text = text.replace("ඡ්‍යෝ", "fPHda");
        text = text.replace("ඪ්‍යෝ", "fVHda");
        text = text.replace("ඝ්‍යෝ", "f>Hda");
        text = text.replace("ඛ්‍යෝ", "fLHda");
        text = text.replace("ළ්‍යෝ", "f<Hda");
        text = text.replace("ඵ්‍යෝ", "fMHda");
        text = text.replace("ඨ්‍යෝ", "fGHda");
        text = text.replace("ශ්‍යෝ", "fYHda");
        text = text.replace("ක්‍ෂ්‍යෝ", "fÌHda");
        text = text.replace("බ්‍යෝ", "fnHda");
        text = text.replace("ච්‍යෝ", "fpHda");
        text = text.replace("ඩ්‍යෝ", "fâHda");
        text = text.replace("ෆ්‍යෝ", "f*Hda");
        text = text.replace("ග්‍යෝ", "f.Hda");
        text = text.replace("ජ්‍යෝ", "fcHda");
        text = text.replace("ක්‍යෝ", "flHda");
        text = text.replace("ල්‍යෝ", "f,Hda");
        text = text.replace("ම්‍යෝ", "fuHda");
        text = text.replace("න්‍යෝ", "fkHda");
        text = text.replace("ප්‍යෝ", "fmHda");
        text = text.replace("ද්‍යෝ", "foHda");
        text = text.replace("ස්‍යෝ", "fiHda");
        text = text.replace("ට්‍යෝ", "fgHda");
        text = text.replace("ව්‍යෝ", "fjHda");
        text = text.replace("ත්‍යෝ", "f;Hda");
        text = text.replace("භ්‍යෝ", "fNHda");
        text = text.replace("ධ්‍යෝ", "fOHda");
        text = text.replace("ථ්‍යෝ", "f:Hda");
        text = text.replace("ෂ්‍යො", "fIHd");
        text = text.replace("ශ්‍යො", "fYHd");
        text = text.replace("ඛ්‍යො", "fLHd");
        text = text.replace("ක්‍ෂ්‍යො", "fÌHd");
        text = text.replace("බ්‍යො", "fnHd");
        text = text.replace("ව්‍යො", "fjHd");
        text = text.replace("ඩ්‍යො", "fvHd");
        text = text.replace("ෆ්‍යො", "f*Hd");
        text = text.replace("ග්‍යො", "f.Hd");
        text = text.replace("ජ්‍යො", "fcHd");
        text = text.replace("ක්‍යො", "flHd");
        text = text.replace("ම්‍යො", "fuHd");
        text = text.replace("ප්‍යො", "fmHd");
        text = text.replace("ද්‍යො", "foHd");
        text = text.replace("ස්‍යො", "fiHd");
        text = text.replace("ට්‍යො", "fgHd");
        text = text.replace("ව්‍යො", "fjHd");
        text = text.replace("ත්‍යො", "f;Hd");
        text = text.replace("භ්‍යො", "fNHd");
        text = text.replace("ධ්‍යො", "fOHd");
        text = text.replace("ථ්‍යො", "f:Hd");
        text = text.replace("ෂ්‍යෙ", "fIH");
        text = text.replace("ඡ්‍යෙ", "fPH");
        text = text.replace("ළ්‍යෙ", "f<H");
        text = text.replace("ණ්‍යෙ", "fKH");
        text = text.replace("ච්‍යෙ", "fpH");
        text = text.replace("ල්‍යෙ", "f,H");
        text = text.replace("න්‍යෙ", "fkH");
        text = text.replace("ශ්‍යෙ", "fYH");
        text = text.replace("ඛ්‍යෙ", "fLH");
        text = text.replace("ක්‍ෂ්යෙ", "fÌH");
        text = text.replace("බ්‍යෙ", "fnH");
        text = text.replace("ඩ්‍යෙ", "fvH");
        text = text.replace("ෆ්‍යෙ", "f*H");
        text = text.replace("ග්‍යෙ", "f.H");
        text = text.replace("ජ්‍යෙ", "fcH");
        text = text.replace("ක්‍යෙ", "flH");
        text = text.replace("ම්‍යෙ", "fuH");
        text = text.replace("ප්‍යෙ", "fmH");
        text = text.replace("ද්‍යෙ", "foH");
        text = text.replace("ස්‍යෙ", "fiH");
        text = text.replace("ට්‍යෙ", "fgH");
        text = text.replace("ව්‍යෙ", "fjH");
        text = text.replace("ත්‍යෙ", "f;H");
        text = text.replace("භ්‍යෙ", "fNH");
        text = text.replace("ධ්‍යෙ", "fOH");
        text = text.replace("ථ්‍යෙ", "f:H");
        text = text.replace("ෂ්‍රෝ", "fI%da");
        text = text.replace("ඝ්‍රෝ", "f>%da");
        text = text.replace("ශ්‍රෝ", "fY%da");
        text = text.replace("ක්‍ෂ්‍රෝ", "fÌ%da");
        text = text.replace("බ්‍රෝ", "fn%da");
        text = text.replace("ඩ්‍රෝ", "fv%da");
        text = text.replace("ෆ්‍රෝ", "f*%da");
        text = text.replace("ග්‍රෝ", "f.%da");
        text = text.replace("ක්‍රෝ", "fl%da");
        text = text.replace("ප්‍රෝ", "fm%da");
        text = text.replace("ද්‍රෝ", "føda");
        text = text.replace("ස්‍රෝ", "fi%da");
        text = text.replace("ට්‍රෝ", "fg%da");
        text = text.replace("ත්‍රෝ", "f;%da");
        text = text.replace("ශ්‍රො", "fY%d");
        text = text.replace("ඩ්‍රො", "fv%d");
        text = text.replace("ෆ්‍රො", "f*%d");
        text = text.replace("ග්‍රො", "f.%d");
        text = text.replace("ක්‍රො", "fl%d");
        text = text.replace("ප්‍රො", "fm%d");
        text = text.replace("ද්‍රො", "fød");
        text = text.replace("ස්‍රො", "fi%d");
        text = text.replace("ට්‍රො", "fg%d");
        text = text.replace("ත්‍රො", "f;%d");
        text = text.replace("ශ්‍රේ", "fYa%");
        text = text.replace("බ්‍රේ", "fí%");
        text = text.replace("ඩ්‍රේ", "fâ%");
        text = text.replace("ෆ්‍රේ", "f*a%");
        text = text.replace("ග්‍රේ", "f.a%");
        text = text.replace("ක්‍රේ", "fla%");
        text = text.replace("ප්‍රේ", "fma%");
        text = text.replace("ද්‍රේ", "føa");
        text = text.replace("ස්‍රේ", "fia%");
        text = text.replace("ත්‍රේ", "f;a%");
        text = text.replace("ධ්‍රේ", "fè%");
        text = text.replace("ෂ්‍රෙ", "fI%");
        text = text.replace("ශ්‍රෙ", "fY%");
        text = text.replace("බ්‍රෙ", "fn%");
        text = text.replace("ෆ්‍රෙ", "f*%");
        text = text.replace("ග්‍රෙ", "f.%");
        text = text.replace("ක්‍රෙ", "fl%");
        text = text.replace("ප්‍රෙ", "fm%");
        text = text.replace("ද්‍රෙ", "fø");
        text = text.replace("ස්‍රෙ", "fi%");
        text = text.replace("ත්‍රෙ", "f;%");
        text = text.replace("භ්‍රෙ", "fN%");
        text = text.replace("ධ්‍රෙ", "fO%");
        text = text.replace("්‍ය", "H");
        text = text.replace("්‍ර", "%");
        text = text.replace("ෂෞ", "fI!");
        text = text.replace("ඡෞ", "fP!");
        text = text.replace("ශෞ", "fY!");
        text = text.replace("බෞ", "fn!");
        text = text.replace("චෞ", "fp!");
        text = text.replace("ඩෞ", "fv!");
        text = text.replace("ෆෞ", "f*!");
        text = text.replace("ගෞ", "f.!");
        text = text.replace("ජෞ", "fc!");
        text = text.replace("කෞ", "fl!");
        text = text.replace("ලෞ", "f,!");
        text = text.replace("මෞ", "fu!");
        text = text.replace("නෞ", "fk!");
        text = text.replace("පෞ", "fm!");
        text = text.replace("දෞ", "fo!");
        text = text.replace("රෞ", "fr!");
        text = text.replace("සෞ", "fi!");
        text = text.replace("ටෞ", "fg!");
        text = text.replace("තෞ", "f;!");
        text = text.replace("භෞ", "fN!");
        text = text.replace("ඤෞ", "f[!");
        text = text.replace("ෂෝ", "fIda");
        text = text.replace("ඹෝ", "fUda");
        text = text.replace("ඡෝ", "fPda");
        text = text.replace("ඪෝ", "fVda");
        text = text.replace("ඝෝ", "f>da");
        text = text.replace("ඛෝ", "fLda");
        text = text.replace("ළෝ", "f<da");
        text = text.replace("ඟෝ", "fÛda");
        text = text.replace("ණෝ", "fKda");
        text = text.replace("ඵෝ", "fMda");
        text = text.replace("ඨෝ", "fGda");
        text = text.replace("ඬෝ", "fËda");
        text = text.replace("ශෝ", "fYda");
        text = text.replace("ඥෝ", "f{da");
        text = text.replace("ඳෝ", "f|da");
        text = text.replace("ක්‍ෂෝ", "fÌda");
        text = text.replace("බෝ", "fnda");
        text = text.replace("චෝ", "fpda");
        text = text.replace("ඩෝ", "fvda");
        text = text.replace("ෆෝ", "f*da");
        text = text.replace("ගෝ", "f.da");
        text = text.replace("හෝ", "fyda");
        text = text.replace("ජෝ", "fcda");
        text = text.replace("කෝ", "flda");
        text = text.replace("ලෝ", "f,da");
        text = text.replace("මෝ", "fuda");
        text = text.replace("නෝ", "fkda");
        text = text.replace("පෝ", "fmda");
        text = text.replace("දෝ", "foda");
        text = text.replace("රෝ", "frda");
        text = text.replace("සෝ", "fida");
        text = text.replace("ටෝ", "fgda");
        text = text.replace("වෝ", "fjda");
        text = text.replace("තෝ", "f;da");
        text = text.replace("භෝ", "fNda");
        text = text.replace("යෝ", "fhda");
        text = text.replace("ඤෝ", "f[da");
        text = text.replace("ධෝ", "fOda");
        text = text.replace("ථෝ", "f:da");
        text = text.replace("ෂො", "fId");
        text = text.replace("ඹො", "fUd");
        text = text.replace("ඡො", "fPd");
        text = text.replace("ඪො", "fVd");
        text = text.replace("ඝො", "f>d");
        text = text.replace("ඛො", "fLd");
        text = text.replace("ළො", "f<d");
        text = text.replace("ඟො", "fÕd");
        text = text.replace("ණො", "fKd");
        text = text.replace("ඵො", "fMd");
        text = text.replace("ඨො", "fGd");
        text = text.replace("ඬො", "fËd");
        text = text.replace("ශො", "fYd");
        text = text.replace("ඥො", "f{d");
        text = text.replace("ඳො", "f|d");
        text = text.replace("ක්‍ෂො", "fÌd");
        text = text.replace("බො", "fnd");
        text = text.replace("චො", "fpd");
        text = text.replace("ඩො", "fvd");
        text = text.replace("ෆො", "f*d");
        text = text.replace("ගො", "f.d");
        text = text.replace("හො", "fyd");
        text = text.replace("ජො", "fcd");
        text = text.replace("කො", "fld");
        text = text.replace("ලො", "f,d");
        text = text.replace("මො", "fud");
        text = text.replace("නො", "fkd");
        text = text.replace("පො", "fmd");
        text = text.replace("දො", "fod");
        text = text.replace("රො", "frd");
        text = text.replace("සො", "fid");
        text = text.replace("ටො", "fgd");
        text = text.replace("වො", "fjd");
        text = text.replace("තො", "f;d");
        text = text.replace("භො", "fNd");
        text = text.replace("යො", "fhd");
        text = text.replace("ඤො", "f[d");
        text = text.replace("ධො", "fOd");
        text = text.replace("ථො", "f:d");
        text = text.replace("ෂේ", "fIa");
        text = text.replace("ඹේ", "fò");
        text = text.replace("ඡේ", "fþ");
        text = text.replace("ඪේ", "f\\a");
        text = text.replace("ඝේ", "f>a");
        text = text.replace("ඛේ", "fÄ");
        text = text.replace("ළේ", "f<a");
        text = text.replace("ඟේ", "fÛa");
        text = text.replace("ණේ", "fKa");
        text = text.replace("ඵේ", "fMa");
        text = text.replace("ඨේ", "fGa");
        text = text.replace("ඬේ", "få");
        text = text.replace("ශේ", "fYa");
        text = text.replace("ඥේ", "f{a");
        text = text.replace("ඳේ", "f|a");
        text = text.replace("ක්‍ෂේ", "fÌa");
        text = text.replace("බේ", "fí");
        text = text.replace("චේ", "fÉ");
        text = text.replace("ඩේ", "fâ");
        text = text.replace("ෆේ", "f*");
        text = text.replace("ගේ", "f.a");
        text = text.replace("හේ", "fya");
        text = text.replace("පේ", "fma");
        text = text.replace("කේ", "fla");
        text = text.replace("ලේ", "f,a");
        text = text.replace("මේ", "fï");
        text = text.replace("නේ", "fka");
        text = text.replace("ජේ", "f–");
        text = text.replace("දේ", "foa");
        text = text.replace("රේ", "f¾");
        text = text.replace("සේ", "fia");
        text = text.replace("ටේ", "fÜ");
        text = text.replace("වේ", "fõ");
        text = text.replace("තේ", "f;a");
        text = text.replace("භේ", "fNa");
        text = text.replace("යේ", "fha");
        text = text.replace("ඤේ", "f[a");
        text = text.replace("ධේ", "fè");
        text = text.replace("ථේ", "f:a");
        text = text.replace("ෂෙ", "fI");
        text = text.replace("ඹෙ", "fU");
        text = text.replace("ඓ", "ft");
        text = text.replace("ඡෙ", "fP");
        text = text.replace("ඪෙ", "fV");
        text = text.replace("ඝෙ", "f>");
        text = text.replace("ඛෙ", "fn");
        text = text.replace("ළෙ", "f<");
        text = text.replace("ඟෙ", "fÛ");
        text = text.replace("ණෙ", "fK");
        text = text.replace("ඵෙ", "fM");
        text = text.replace("ඨෙ", "fG");
        text = text.replace("ඬෙ", "fË");
        text = text.replace("ශෙ", "fY");
        text = text.replace("ඥෙ", "f{");
        text = text.replace("ඳෙ", "fË");
        text = text.replace("ක්‍ෂෙ", "fÌ");
        text = text.replace("බෙ", "fn");
        text = text.replace("චෙ", "fp");
        text = text.replace("ඩෙ", "fv");
        text = text.replace("ෆෙ", "f*");
        text = text.replace("ගෙ", "f.");
        text = text.replace("හෙ", "fy");
        text = text.replace("ජෙ", "fc");
        text = text.replace("කෙ", "fl");
        text = text.replace("ලෙ", "f,");
        text = text.replace("මෙ", "fu");
        text = text.replace("නෙ", "fk");
        text = text.replace("පෙ", "fm");
        text = text.replace("දෙ", "fo");
        text = text.replace("රෙ", "fr");
        text = text.replace("සෙ", "fi");
        text = text.replace("ටෙ", "fg");
        text = text.replace("වෙ", "fj");
        text = text.replace("තෙ", "f;");
        text = text.replace("භෙ", "fN");
        text = text.replace("යෙ", "fh");
        text = text.replace("ඤෙ", "f[");
        text = text.replace("ධෙ", "fO");
        text = text.replace("ථෙ", "f:");
        text = text.replace("තු", ";=");
        text = text.replace("ගු", ".=");
        text = text.replace("කු", "l=");
        text = text.replace("තූ", ";+");
        text = text.replace("ගූ", ".+");
        text = text.replace("කූ", "l+");
        text = text.replace("රු", "re");
        text = text.replace("රූ", "rE");
        text = text.replace("ආ", "wd");
        text = text.replace("ඇ", "we");
        text = text.replace("ඈ", "wE");
        text = text.replace("ඌ", "W!");
        text = text.replace("ඖ", "T!");
        text = text.replace("ඒ", "ta");
        text = text.replace("ඕ", "´");
        text = text.replace("ඳි", "¢");
        text = text.replace("ඳී", "£");
        text = text.replace("දූ", "¥");
        text = text.replace("දී", "§");
        text = text.replace("ලූ", "¨");
        text = text.replace("ර්‍ය", "©");
        text = text.replace("ඳූ", "ª");
        text = text.replace("ර්", "¾");
        text = text.replace("ඨි", "À");
        text = text.replace("ඨී", "Á");
        text = text.replace("ඡී", "Â");
        text = text.replace("ඛ්", "Ä");
        text = text.replace("ඛි", "Å");
        text = text.replace("ලු", "Æ");
        text = text.replace("ඛී", "Ç");
        text = text.replace("දි", "È");
        text = text.replace("ච්", "É");
        text = text.replace("ජ්", "Ê");
        text = text.replace("රී", "Í");
        text = text.replace("ඪී", "Î");
        text = text.replace("ඪී", "Ð,");
        text = text.replace("චි", "Ñ");
        text = text.replace("ථී", "Ò");
        text = text.replace("ථී", "Ó");
        text = text.replace("ජී", "Ô");
        text = text.replace("චී", "Ö");
        text = text.replace("ඞ්", "Ù");
        text = text.replace("ඵී", "Ú");
        text = text.replace("ට්", "Ü");
        text = text.replace("ඵි", "Ý");
        text = text.replace("රි", "ß");
        text = text.replace("ටී", "à");
        text = text.replace("ටි", "á");
        text = text.replace("ඩ්", "â");
        text = text.replace("ඩී", "ã");
        text = text.replace("ඩි", "ä");
        text = text.replace("ඬ්", "å");
        text = text.replace("ඬි", "ç");
        text = text.replace("ධ්", "è");
        text = text.replace("ඬී", "é");
        text = text.replace("ධි", "ê");
        text = text.replace("ධී", "ë");
        text = text.replace("බි", "ì");
        text = text.replace("බ්", "í");
        text = text.replace("බී", "î");
        text = text.replace("ම්", "ï");
        text = text.replace("ජි", "ð");
        text = text.replace("මි", "ñ");
        text = text.replace("ඹ්", "ò");
        text = text.replace("මී", "ó");
        text = text.replace("ඹි", "ô");
        text = text.replace("ව්", "õ");
        text = text.replace("ඹී", "ö");
        text = text.replace("ඳු", "÷");
        text = text.replace("ද්‍ර", "ø");
        text = text.replace("වී", "ù");
        text = text.replace("වි", "ú");
        text = text.replace("ඞ්", "û");
        text = text.replace("ඞී", "ü");
        text = text.replace("ඡි", "ý");
        text = text.replace("ඡ්", "þ");
        text = text.replace("දු", "ÿ");
        text = text.replace("ජ්", "–");
        text = text.replace("ර්‍ණ", "“");
        text = text.replace("ණී", "”");
        text = text.replace("ජී", "„");
        text = text.replace("ඡි", "‰");
        text = text.replace("ඩි", "");
        text = text.replace("ඤු", "™");
        text = text.replace("ග", ".");
        text = text.replace("ළු", "¿");
        text = text.replace("ෂ", "I");
        text = text.replace("ං", "x");
        text = text.replace("ඃ", "#");
        text = text.replace("ඹ", "U");
        text = text.replace("ඡ", "P");
        text = text.replace("ඪ", "V");
        text = text.replace("ඝ", ">");
        text = text.replace("ඊ", "B");
        text = text.replace("ඣ", "CO");
        text = text.replace("ඛ", "L");
        text = text.replace("ළ", "<");
        text = text.replace("ඟ", "Û");
        text = text.replace("ණ", "K");
        text = text.replace("ඵ", "M");
        text = text.replace("ඨ", "G");
        text = text.replace("ඃ", "#");
        text = text.replace("\"", ",");
        text = text.replace("/", "$");
        text = text.replace(")", "&");
        text = text.replace(":", "(");
        text = text.replace("-", ")");
        text = text.replace("ෆ", "*");
        text = text.replace("ල", ",");
        text = text.replace("-", "-");
        text = text.replace("රැ", "/");
        text = text.replace("ථ", ":");
        text = text.replace("ත", ";");
        text = text.replace("ළ", "<");
        text = text.replace("ඝ", ">");
        text = text.replace("රෑ", "?");
        text = text.replace("ඊ", "B");
        text = text.replace("ක‍", "C");
        text = text.replace("‍ෘ", "D");
        text = text.replace("ෑ", "E");
        text = text.replace("ත‍", "F");
        text = text.replace("ඨ", "G");
        text = text.replace("්‍ය", "H");
        text = text.replace("ෂ", "I");
        text = text.replace("න‍", "J");
        text = text.replace("ණ", "K");
        text = text.replace("ඛ", "L");
        text = text.replace("ඵ", "M");
        text = text.replace("භ", "N");
        text = text.replace("ධ", "O");
        text = text.replace("ඡ", "P");
        text = text.replace("ඍ", "R");
        text = text.replace("ඔ", "T");
        text = text.replace("ඹ", "U");
        text = text.replace("ඪ", "V");
        text = text.replace("උ", "W");
        text = text.replace("ශ", "Y");
        text = text.replace("ඤ", "[");
        text = text.replace("ඉ", "b");
        text = text.replace("ජ", "c");
        text = text.replace("ට", "g");
        text = text.replace("ය", "h");
        text = text.replace("ස", "i");
        text = text.replace("ව", "j");
        text = text.replace("න", "k");
        text = text.replace("ක", "l");
        text = text.replace("ප", "m");
        text = text.replace("බ", "n");
        text = text.replace("ද", "o");
        text = text.replace("ච", "p");
        text = text.replace("ර", "r");
        text = text.replace("එ", "t");
        text = text.replace("ම", "u");
        text = text.replace("ඩ", "v");
        text = text.replace("අ", "w");
        text = text.replace("හ", "y");
        text = text.replace("ඥ", "{");
        text = text.replace("ඳ", "|");
        text = text.replace("ක්‍ෂ", "Ì");
        text = text.replace("ැ", "e");
        text = text.replace("ෑ", "E");
        text = text.replace("ෙ", "f");
        text = text.replace("ු", "q");
        text = text.replace("ි", "s");
        text = text.replace("ූ", "Q");
        text = text.replace("ී", "S");
        text = text.replace("ෘ", "D");
        text = text.replace("ෲ", "DD");
        text = text.replace("ෟ", "!");
        text = text.replace("ා", "d");
        text = text.replace("්", "a");
        text = text.replace("￦", "\"");
        text = text.replace("�", "'");
        text = text.replace("￫", "^");
        text = text.replace("￩", "&");
        text = text.replace("ￔ", ")");
        text = text.replace("ￓ", "@");
        text = text.replace("ￒ", "`");
        text = text.replace("ￏ", "}");
        text = text.replace("ￎ", "~");
        text = text.replace("ￍ", "¤");
        text = text.replace("ￌ", "•");
        text = text.replace("ￊ", "›");
        text = text.replace("ﾶ", "∙");
        text = text.replace("ￕ", "]");
        txtAma.setText(text);
        this.amaliTextComponent.setText(text);
    }
private void startText() {
        String s = null, r, v;
        String text;
        text = txtSinglish.getText();
        //special consonents
        for (int i = 0; i < specialConsonants.length; i++) {

            text = text.replace(specialConsonants[i], specialConsonantsUni[i]);
        }
//        //consonents + special Chars
        for (int i = 0; i < specialCharUni.length; i++) {
            for (int j = 0; j < consonants.length; j++) {
                s = consonants[j] + specialChar[i];
                v = consonantsUni[j] + specialCharUni[i];
                text = text.replace(s, v);
            }
        }
////    //consonants + Rakaransha + vowel modifiers
        for (int j = 0; j < consonants.length; j++) {
            for (int i = 0; i < vowels.length; i++) {
                s = consonants[j] + "r" + vowels[i];
                v = consonantsUni[j] + "්‍ර" + vowelModifiersUni[i];

                text = text.replace(s, v);
            }
            s = consonants[j] + "r";
            v = consonantsUni[j] + "්‍ර";

            text = text.replace(s, v);
        }
////    //consonents + vowel modifiers
        for (int i = 0; i < consonants.length; i++) {
            for (int j = 0; j < nVowels; j++) {
                s = consonants[i] + vowels[j];
                v = consonantsUni[i] + vowelModifiersUni[j];

                text = text.replace(s, v);
            }
        }
////
////    //consonents + HAL
        for (int i = 0; i < consonants.length; i++) {
            //r = new RegExp(consonants[i], "g");
            text = text.replace(consonants[i], consonantsUni[i] + "්");
        }
////        
////    //vowels
        for (int i = 0; i < vowels.length; i++) {
            text = text.replace(vowels[i], vowelsUni[i]);
        }
        createAmalee(text);
    }
}
