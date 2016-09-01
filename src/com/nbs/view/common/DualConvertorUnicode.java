/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.text.JTextComponent;

/**
 *
 * @author mmh
 */
public class DualConvertorUnicode extends javax.swing.JDialog {

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
    JTextComponent unicodeTextComponent;

    /**
     * Creates new form DualConvertorUnicode
     */
    DualConvertorUnicode(java.awt.Frame parent, boolean modal) {
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

    public DualConvertorUnicode(Frame owner, boolean modal, JTextComponent unicodeTextComponent) {
        this(owner, modal);
        this.unicodeTextComponent = unicodeTextComponent;
    }

    public DualConvertorUnicode(Dialog owner, boolean modal, JTextComponent unicodeTextComponent) {
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
        this.unicodeTextComponent = unicodeTextComponent;
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
        selectedCopy = new javax.swing.JMenuItem();
        allCopy = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        selectedCopy1 = new javax.swing.JMenuItem();
        allCopy1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtUni = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSinglish = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        selectedCopy.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        selectedCopy.setText("Copy Selected");
        selectedCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(selectedCopy);

        allCopy.setText("Copy All");
        allCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(allCopy);

        selectedCopy1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        selectedCopy1.setText("Copy Selected");
        selectedCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedCopy1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(selectedCopy1);

        allCopy1.setText("Copy All");
        allCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCopy1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(allCopy1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtUni.setColumns(20);
        txtUni.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtUni.setRows(5);
        txtUni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtUni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUniKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtUni);

        txtSinglish.setColumns(20);
        txtSinglish.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        txtSinglish.setRows(5);
        txtSinglish.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSinglish.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSinglishKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(txtSinglish);

        jLabel1.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel1.setText("සිංග්ලිශ් :");

        jLabel2.setFont(new java.awt.Font("Iskoola Pota", 0, 18)); // NOI18N
        jLabel2.setText("යුනිකෝඩ් :");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUniKeyReleased
        //        createAmalee();
    }//GEN-LAST:event_txtUniKeyReleased

    private void txtSinglishKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSinglishKeyReleased
        startText();
    }//GEN-LAST:event_txtSinglishKeyReleased

    private void selectedCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedCopyActionPerformed
        String selectedText = txtUni.getSelectedText();
        copy(selectedText);
    }//GEN-LAST:event_selectedCopyActionPerformed

    private void allCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCopyActionPerformed
        copy(txtUni.getText());
    }//GEN-LAST:event_allCopyActionPerformed

    private void selectedCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedCopy1ActionPerformed

    }//GEN-LAST:event_selectedCopy1ActionPerformed

    private void allCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCopy1ActionPerformed

    }//GEN-LAST:event_allCopy1ActionPerformed

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
            java.util.logging.Logger.getLogger(DualConvertorUnicode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DualConvertorUnicode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DualConvertorUnicode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DualConvertorUnicode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DualConvertorUnicode dialog = new DualConvertorUnicode(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem allCopy;
    private javax.swing.JMenuItem allCopy1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem selectedCopy;
    private javax.swing.JMenuItem selectedCopy1;
    private static javax.swing.JTextArea txtSinglish;
    private static javax.swing.JTextArea txtUni;
    // End of variables declaration//GEN-END:variables

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

        txtUni.setText(text);
        this.unicodeTextComponent.setText(text);
    }

    private void copy(String text) {
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        systemClipboard.setContents(selection, null);
    }
}
