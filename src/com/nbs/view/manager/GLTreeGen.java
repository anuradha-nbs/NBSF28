/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.manager;

import com.nbs.impl.ServerConnection;
import static com.nbs.view.manager.GLTree.jTree1;
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
import java.sql.SQLException;

/**
 *
 * @author mmh
 */
public class GLTreeGen {

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
        root.setUserObject("Manager");
        jTree1.setFont(new Font("Iskoola Pota", Font.PLAIN, 17));
        
//        if (accessLevel == AccessLevels.MANAGER) {
            buildManagerTree(jTree1, accessLevel);
//        }
        jTree1.setCellRenderer(new OwnRenderer());
        ToolTipManager.sharedInstance().registerComponent(jTree1);
    }

    private void buildManagerTree(JTree jTree1, int accesslevel) {
        try {
            ArrayList<Object> inputs = new ArrayList<Object>();
            inputs.add(1);
            inputs.add(0);
            inputs.add(0);
            List<List<Object>> parents = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_GL_LoadCombo_GLTree", 2);
            for (int i = 0; i < parents.size(); i++) {
                List<Object> list = parents.get(i);
                parent = new DefaultMutableTreeNode(list.get(1));
                inputs.removeAll(inputs);
                inputs.add(2);
                inputs.add(list.get(0));
                inputs.add(0);
                List<List<Object>> childern = ServerConnection.getServerConnector().searchMultipleResults(inputs, "ssp_GL_LoadCombo_GLTree", 2);
                for (int j = 0; j < childern.size(); j++) {
                    List<Object> list1 = childern.get(j);
                    child = new DefaultMutableTreeNode(list1.get(1));
                    
                    parent.add(child);
                }
                root.add(parent);
//                cellColour();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GLTreeGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GLTreeGen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GLTreeGen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    private void cellColour() {
//        jTree1.setCellRenderer(new DefaultTreeCellRenderer() {
//            public Component getTreeCellRendererComponent(JTree pTree, Object pValue, boolean pIsSelected, boolean pIsExpanded, boolean pIsLeaf, int pRow, boolean pHasFocus) {
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) pValue;
//                super.getTreeCellRendererComponent(pTree, pValue, pIsSelected, pIsExpanded, pIsLeaf, pRow, pHasFocus);
//                System.out.println("1cell");
//                switch (node.getLevel()) {
//                    case 1:
//                        setForeground(new java.awt.Color(200, 9, 250));
//                        break;
//                    case 2:
//                        setForeground(new java.awt.Color(128, 0, 0));
//                        break;
//                    case 3:
//                        setForeground(new java.awt.Color(3, 130, 42));
//                        break;
//                    case 4:
//                        setForeground(new java.awt.Color(0,191,0));
//                        break;
//                    default:
//                        break;
//                }
//                return (this);
//            }
//        });
//    }

    private static class OwnRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            switch (node.getLevel()) {
                case 1:
                    setForeground(new java.awt.Color(6, 53, 144));
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
                if (sel) {
                    setForeground(Color.white);
                }
            setToolTipText(value.toString());
            UIManager.put("ToolTip.background", Color.yellow);
            UIManager.put("ToolTip.font", new Font("Iskoola Pota", Font.PLAIN, 17));
            return this;
        }
    }
}
