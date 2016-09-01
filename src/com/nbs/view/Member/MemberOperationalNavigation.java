/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.Member;

import javax.swing.JPanel;

/**
 *
 * @author mmh
 */
public class MemberOperationalNavigation {

    /**
     *
     * @param code code in the tree
     * @param child child panel to which data will be loaded
     * @param parent parent panel to which the child should be loaded
     * @param node type of node
     */
    public static void navigateTo(String code, JPanel child, JPanel parent, int node) {

        viewPanel(child, parent, node);

    }

    private static void viewPanel(JPanel child, JPanel parent, int node) {
        parent.removeAll();
        child.setSize(parent.getSize());
        parent.add(child);
        parent.repaint();
        parent.revalidate();
        com.nbs.view.manager.ManagerOperational.node = node;
    }

    public static void navigateTo(JPanel child, JPanel parent) {

        viewPanel(child, parent);

    }

    private static void viewPanel(JPanel child, JPanel parent) {
        parent.removeAll();
        child.setSize(parent.getSize());
        parent.add(child);
        parent.repaint();
        parent.revalidate();
    }
}
