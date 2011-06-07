package components;

import java.awt.Font;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class TreeViewRenderer implements javax.swing.tree.TreeCellRenderer {

    private javax.swing.tree.DefaultTreeCellRenderer cellRenderer;
    private java.awt.Color bkgSelectionColor = null;
    private java.awt.Color bkgNonSelectionColor = null;

    /**
     * TreeViewRenderer constructor comment.
     */
    public TreeViewRenderer() {
        super();
        cellRenderer = new javax.swing.tree.DefaultTreeCellRenderer();
        cellRenderer.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        bkgSelectionColor = cellRenderer.getBackgroundSelectionColor();
        bkgNonSelectionColor = cellRenderer.getBackgroundNonSelectionColor();
    }

    /**
     * Sets the value of the current tree cell to <code>value</code>.
     * If <code>selected</code> is true, the cell will be drawn as if
     * selected. If <code>expanded</code> is true the node is currently
     * expanded and if <code>leaf</code> is true the node represets a
     * leaf anf if <code>hasFocus</code> is true the node currently has
     * focus. <code>tree</code> is the JTree the receiver is being
     * configured for.
     * Returns the Component that the renderer uses to draw the value.
     *
     * @return Component that the renderer uses to draw the value.
     */
    @Override
    public java.awt.Component getTreeCellRendererComponent(
            javax.swing.JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        javax.swing.JLabel aRenderedObject = (javax.swing.JLabel) cellRenderer.getTreeCellRendererComponent(
                tree, value, selected, expanded, leaf, row, hasFocus);
        aRenderedObject.setFont(new java.awt.Font(Font.SANS_SERIF, row ,row));
        return aRenderedObject;
    }
}
