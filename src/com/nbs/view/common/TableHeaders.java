/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmh
 */
public class TableHeaders {

    public static final DefaultTableModel SUBSECTIONFINDER_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "කේතය", "අනු අංශය", "ප්‍රධාන අංශය"}, 1) {
        Class[] types = new Class[]{Integer.class, String.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel SUBSECTIONFINDER_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", "", "", ""}, 0) {
        Class[] types = new Class[]{Integer.class, String.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    public static final DefaultTableModel OUTSIDERFINDER_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "ලි.ප./ජා.හැ. අංකය","කේතය", "නම( සිංහල )", "නම(English)"}, 1) {
        Class[] types = new Class[]{Integer.class, String.class, String.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    public static final DefaultTableModel OUTSIDERFINDER_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", "", "", "", ""}, 0) {
        Class[] types = new Class[]{Integer.class, String.class, String.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel REASON_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "වර්ගය", "කාරණය( සිංහල )","කාරණය( Unicode )","Act5","විස්තරය"}, 1) {
        Class[] types = new Class[]{String.class, String.class, String.class,String.class,String.class,String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
        boolean[] canEdit = new boolean[]{
            true, true, true,false,false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    public static final DefaultTableModel REASON_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", "", "","","",""}, 0) {
        Class[] types = new Class[]{String.class, String.class, String.class, String.class, String.class,String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
    };
    
    public static final DefaultTableModel SECTIONFINDER_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "අංශය", "අංශය(Eng)","Super Group"}, 1) {
        Class[] types = new Class[]{String.class, String.class, String.class,String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel SECTIONFINDER_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", "", "",""}, 0) {
        Class[] types = new Class[]{String.class, String.class, String.class,String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    
    public static final DefaultTableModel PERFORMANCEAPPRAISALSECTIONFINDER_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "අංශය", "අංශය(Eng)"}, 1) {
        Class[] types = new Class[]{String.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel PERFORMANCEAPPRAISALSECTIONFINDER_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", "", ""}, 0) {
        Class[] types = new Class[]{String.class, String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    
    public static final DefaultTableModel OUTSIDERTYPEFINDER_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "වර්ගය"}, 1) {
        Class[] types = new Class[]{String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel OUTSIDERTYPEFINDER_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", ""}, 0) {
        Class[] types = new Class[]{String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    
    public static final DefaultTableModel B16FIND_TABLE_MODEL = new DefaultTableModel(new String[]{"ID","දිනය","16B අංකය","බිල් අංකය","පාර්ශවකරු","මුදල","අංශය"}, 1) {
        Class[] types = new Class[]{String.class,String.class, String.class,String.class,String.class,Double.class,String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel B16FIND_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", "","", "","", ""}, 0) {
        Class[] types = new Class[]{String.class,String.class, String.class,String.class,String.class,Double.class,String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    public static final DefaultTableModel B16FINDER_TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "වර්ගය"}, 1) {
        Class[] types = new Class[]{String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };

    public static final DefaultTableModel B16FINDER_TABLE_MODEL2 = new DefaultTableModel(new String[]{"", ""}, 0) {
        Class[] types = new Class[]{String.class, String.class};

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

    };
    
}
