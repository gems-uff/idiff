package components;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 * TreeComponent
 * @author Fernanda Floriano Silva
 */
public class TreeComponent extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Construct Tree
     * @param tree
     * @param dir
     * @param scrollPane
     * @param name
     * @throws SecurityException
     * @throws FileNotFoundException  
     */
    public void constructTree(JTree tree, File dir, JScrollPane scrollPane, String name) throws SecurityException, FileNotFoundException {
        setFeatures(scrollPane, name, dir, tree);
        addTree(tree, dir.getAbsoluteFile(), scrollPane, name, dir.getName());
        selectNode(dir, tree);
    }

    /**
     * Select Node
     * @param dir
     * @param tree 
     */
    private void selectNode(File dir, JTree tree) {
        tree.setSelectionRow(tree.getRowForPath(tree.getNextMatch(dir.getName().trim(), 0, Position.Bias.Forward)));
        tree.scrollRowToVisible(tree.getRowForPath(tree.getNextMatch(dir.getName().trim(), 0, Position.Bias.Forward)));
    }

    /**
     * Set Features
     * @param scrollPane
     * @param name
     * @param dir
     * @param tree 
     */
    private void setFeatures(JScrollPane scrollPane, String name, File dir, JTree tree) {
        scrollPane.setBorder(BorderFactory.createTitledBorder(name + "(" + dir.getName() + ")"));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
    }

    /**
     * Add Tree
     * @param tree
     * @param dir
     * @param scrollPane
     * @param name
     * @param fileName
     * @throws SecurityException
     * @throws FileNotFoundException  
     */
    public void addTree(JTree tree, File dir, JScrollPane scrollPane, String name, String fileName) throws SecurityException, FileNotFoundException {
        FileTreeNode rootNode = new FileTreeNode(null, dir.getParent());
        if (rootNode.populateDirectories(true)) {
            tree.setModel(new DefaultTreeModel(rootNode));
            tree.addTreeExpansionListener(new TreeExpansionHandler(this));
        }
    }
}