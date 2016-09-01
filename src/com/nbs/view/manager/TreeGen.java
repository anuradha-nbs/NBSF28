/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager;

import com.nbs.constants.AccessLevels;
import com.nbs.impl.ServerConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import static com.nbs.view.manager.ManagerOperational.jTree1;
import java.sql.SQLException;

/**
 *
 * @author mmh
 */
public class TreeGen {

    DefaultTreeModel model;
    DefaultMutableTreeNode root;
    Map<Integer, String> level1;
    HashMap<Integer, List<String>> level2;
    List<String> l;
    DefaultMutableTreeNode parent;
    DefaultMutableTreeNode child;

    public void generateTree(JTree jTree1, int accessLevel) {
        model = (DefaultTreeModel) jTree1.getModel();
        root = (DefaultMutableTreeNode) model.getRoot();
//        if (AccessLevels.MANAGER == accessLevel) {
        String name = AccessLevels.class.getFields()[accessLevel - 1].getName();
        root.setUserObject(name);
//        } else if(AccessLevels.STOCK == accessLevel){
//            root.setUserObject("Stock");
//        }
        jTree1.setFont(new Font("Iskoola Pota", Font.PLAIN, 17));
        buildManagerTree(jTree1, accessLevel);
        jTree1.setCellRenderer(new OwnRenderer());
        ToolTipManager.sharedInstance().registerComponent(jTree1);
    }

    private void buildManagerTree(JTree jTree1, int accesslevel) {
        try {
            ArrayList<Object> inputs = new ArrayList<Object>();
            inputs.add(1);
            inputs.add(0);
            inputs.add(accesslevel);
            List<List<Object>> parents = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_F28Tree", 2);
            for (int i = 0; i < parents.size(); i++) {
                List<Object> list = parents.get(i);
                parent = new DefaultMutableTreeNode(list.get(1));
                inputs.removeAll(inputs);
                inputs.add(2);
                inputs.add(list.get(0));
                inputs.add(accesslevel);
                List<List<Object>> childern = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_F28_LoadCombo_F28Tree", 2);
                for (int j = 0; j < childern.size(); j++) {
                    List<Object> list1 = childern.get(j);
                    child = new DefaultMutableTreeNode(list1.get(1));

                    parent.add(child);
                }
                root.add(parent);
                cellColour();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TreeGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TreeGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreeGen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cellColour() {
        jTree1.setCellRenderer(new DefaultTreeCellRenderer() {
            public Component getTreeCellRendererComponent(JTree pTree, Object pValue, boolean pIsSelected, boolean pIsExpanded, boolean pIsLeaf, int pRow, boolean pHasFocus) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) pValue;
                super.getTreeCellRendererComponent(pTree, pValue, pIsSelected, pIsExpanded, pIsLeaf, pRow, pHasFocus);

                setBackgroundSelectionColor(new Color(0, 114, 198));
                switch (node.getLevel()) {
                    case 1:
                        setForeground(new java.awt.Color(0, 0, 210));
                        break;
                    case 2:
                        setForeground(new java.awt.Color(128, 0, 0));
                        break;
                    case 3:
                        setForeground(new java.awt.Color(3, 130, 42));
                        break;
                    case 4:
                        setForeground(new java.awt.Color(3, 130, 42));
                        break;
                    default:
                        break;
                }

                if (pIsSelected) {
                    setForeground(Color.white);
                }
                return (this);
            }
        });
    }

    private static class OwnRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            setBackgroundSelectionColor(new Color(0, 114, 198));
            switch (node.getLevel()) {
                case 1:
                    setForeground(new java.awt.Color(0, 0, 210));
                    break;
                case 2:
                    setForeground(new java.awt.Color(128, 0, 0));
                    break;
                case 3:
                    setForeground(new java.awt.Color(3, 130, 42));
                    break;
                case 4:
                    setForeground(new java.awt.Color(3, 130, 42));
                    break;
                default:
                    break;
            }
            setToolTipText(value.toString());
            if (sel) {
                setForeground(Color.white);
            }
            UIManager.put("ToolTip.background", Color.yellow);
            UIManager.put("ToolTip.font", new Font("Iskoola Pota", Font.PLAIN, 17));
            return this;
        }
    }
}
