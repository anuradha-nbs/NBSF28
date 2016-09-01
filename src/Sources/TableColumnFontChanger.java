/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mmh
 */
public class TableColumnFontChanger {

    public static void setAmalee(JTable jTable, int colNUm) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font("AMALEE", Font.PLAIN, 16);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(font);
                return this;
            }
        };
        jTable.getColumnModel().getColumn(colNUm).setCellRenderer(r);
    }

    public static void setAmalee(JTable jTable, int colNUm, int size) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font("AMALEE", Font.PLAIN, size);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(font);
                return this;
            }
        };
        jTable.getColumnModel().getColumn(colNUm).setCellRenderer(r);
    }

    public static void setIskolaPotha(JTable jTable, int colNUm) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font("Iskoola Pota", 1, 16);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(font);

                return this;
            }
        };
        jTable.getColumnModel().getColumn(colNUm).setCellRenderer(r);
    }

    public static void setIskolaPotha(JTable jTable, int colNUm, Color foreGround, Color selectionForeGround) {
        final Color thisForeGround = foreGround;
        final Color thisSelectionForeGround = selectionForeGround;
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font("Iskoola Pota", 1, 16);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                tableCellRendererComponent.setFont(font);
                if (isSelected) {
                    tableCellRendererComponent.setForeground(thisSelectionForeGround);
                } else {
                    tableCellRendererComponent.setForeground(thisForeGround);
                }

                return tableCellRendererComponent;
            }
        };
        jTable.getColumnModel().getColumn(colNUm).setCellRenderer(r);
    }

    public static void setIskolaPotha(JTable jTable, int colNUm, Color foreGround, Color selectionForeGround, int size) {
        final Color thisForeGround = foreGround;
        final Color thisSelectionForeGround = selectionForeGround;
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font("Iskoola Pota", 1, size);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                tableCellRendererComponent.setFont(font);

                if (isSelected) {
                    tableCellRendererComponent.setForeground(thisSelectionForeGround);

                } else {
                    tableCellRendererComponent.setForeground(thisForeGround);
                }
                return tableCellRendererComponent;
            }
        };
        jTable.getColumnModel().getColumn(colNUm).setCellRenderer(r);
    }

    public static void setIskolaPotha(JTable jTable, int colNUm, int size) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font("Iskoola Pota", Font.PLAIN, size);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                tableCellRendererComponent.setFont(font);
                return tableCellRendererComponent;
            }
        };
        jTable.getColumnModel().getColumn(colNUm).setCellRenderer(r);
    }

    public static void setFont(JTable jTable, int colNum, Font font) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                tableCellRendererComponent.setFont(font);
                return tableCellRendererComponent;
            }
        };
        jTable.getColumnModel().getColumn(colNum).setCellRenderer(r);
    }

    public static void setFont(JTable jTable, int colNum, Font font, Color foreGround, Color selectionForeGround) {
        final Color thisForeGround = foreGround;
        final Color thisSelectionForeGround = selectionForeGround;
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                tableCellRendererComponent.setFont(font);
                if (isSelected) {
                    setForeground(thisSelectionForeGround);
                } else {
                    setForeground(thisForeGround);
                }
                return tableCellRendererComponent;
            }
        };
        jTable.getColumnModel().getColumn(colNum).setCellRenderer(r);
    }
}
