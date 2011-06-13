/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class TreeComponent {

    /**
     * Constructor
     */
    public TreeComponent() {
    }

    /**
     * Create Nodes
     * @param treePath
     * @param dirTree
     * @param scrollPane
     * @param name 
     */
    public void createTreeNodes(String treePath, JTree dirTree, JScrollPane scrollPane, String name) {
        String path[] = treePath.split("\\\\");
        DefaultMutableTreeNode root = addChildTreeNodes(path);
        dirTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        dirTree.setModel(new DefaultTreeModel(root));
        expandTreeNodes(dirTree);
        dirTree.setSelectionRow(path.length - 1);
        scrollPane.setBorder(BorderFactory.createTitledBorder(name + "(" + path[path.length - 1] + ")"));
    }

    /**
     * Expand All
     * @param tree 
     */
    private void expandTreeNodes(JTree tree) {
        int row = 0;
        while (row < tree.getRowCount()) {
            tree.expandRow(row);
            row++;
        }
    }

    /**
     * Add Child Nodes
     * @param path
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode addChildTreeNodes(String path[]) {
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(path[path.length - 1]);
        for (int i = path.length - 1; i > 0; i--) {
            child = addChild(new DefaultMutableTreeNode(path[i - 1]), child);
        }
        return child;
    }

    /**
     * Add Child
     * @param parent
     * @param child
     * @return DefaultMutableTreeNode
     */
    private DefaultMutableTreeNode addChild(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
        parent.add(child);
        return parent;
    }
}
