/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Sisi
 */
class TreeExpansionHandler implements TreeExpansionListener {
    private TreeComponent outer;

    protected TreeExpansionHandler(TreeComponent outer) {
        this.outer = outer;
    }

    @Override
    public void treeExpanded(TreeExpansionEvent evt) {
        TreePath path = evt.getPath();
        JTree tree = (JTree) evt.getSource();
        FileTreeNode node = (FileTreeNode) path.getLastPathComponent();
        if (node.populateDirectories(true)) {
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
        }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent evt) {
    }

    /**
     * @return the outer
     */
    public TreeComponent getOuter() {
        return outer;
    }

    /**
     * @param outer the outer to set
     */
    public void setOuter(TreeComponent outer) {
        this.outer = outer;
    }
    
}
