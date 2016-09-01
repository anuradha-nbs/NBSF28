package Sources;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ExpandOrCollapseTree {

    /**
     *
     * @param tree tree to be expanded
     * 
     * this method expands all nodes of a tree
     */
    public static void expandAll(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root));
    }

    private static void expandAll(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path);
            }
        }
        tree.expandPath(parent);
        // tree.collapsePath(parent);
    }

    /**
     *
     * @param tree tree to be collapsed
     * 
     * this method collapses all nodes of a tree
     */
    public static void collapseAll(JTree tree) {
        int row = tree.getRowCount() - 1;
        while (row >= 1) {
            tree.collapseRow(row);
            row--;
        }
    }
}
