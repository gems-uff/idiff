package gui.components.resources;

import gui.components.TreeComponent;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class TreeExpansionHandler implements TreeExpansionListener {

    private TreeComponent outer;

    /**
     * TreeExpansionHandler
     */
    public TreeExpansionHandler() {
    }

    /**
     * TreeExpansionHandler
     * @param outer 
     */
    public TreeExpansionHandler(TreeComponent outer) {
        this.outer = outer;
    }

    /**
     * treeExpanded
     * @param evt 
     */
    @Override
    public void treeExpanded(TreeExpansionEvent evt) {
        TreePath path = evt.getPath();
        JTree tree = (JTree) evt.getSource();
        FileTreeNode node = (FileTreeNode) path.getLastPathComponent();
        if (node.populateDirectories(true)) {
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
        }
    }

    /**
     * treeCollapsed
     * @param evt 
     */
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
