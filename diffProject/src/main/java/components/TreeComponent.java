package components;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 * TreeComponent
 * @author Fernanda Floriano Silva
 */
public class TreeComponent extends JPanel {

    private String selectedDir;

    /**
     * Construct Tree
     * @param tree
     * @param dir
     * @param scrollPane
     * @param name 
     */
    public void constructTree(JTree tree, File dir, JScrollPane scrollPane, String name) {
        setFeatures(scrollPane, name, dir, tree);
        addTree(tree, dir.getParentFile(), scrollPane, name, dir.getName());
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
        scrollPane.setBorder(BorderFactory.createTitledBorder(name + "(" + dir.getAbsolutePath() + ")"));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
    }

    /**
     * Add Tree
     * @param tree
     * @param dir
     * @param scrollPane
     * @param name
     * @param fileName 
     */
    public void addTree(JTree tree, File dir, JScrollPane scrollPane, String name, String fileName) {
        DefaultMutableTreeNode dmTreeNode = addTreeNodes(null, dir, fileName);
        tree.setModel(new DefaultTreeModel(dmTreeNode));
        tree.addTreeSelectionListener(
                new TreeSelectionListener() {

                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                        setSelectedDir(node.toString());
                    }
                });
    }

    /**
     * Add Tree Nodes
     * @param curTop
     * @param dir
     * @param name
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode addTreeNodes(DefaultMutableTreeNode curTop, File dir, String name) {
        String curPath = dir.getPath();

        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) {
            curTop.add(curDir);
        }
        List ol = initializeOlList(dir);
        List files = new ArrayList();
        addNode(ol, curDir, files);
        addFileNode(files, curDir);

        return curDir;
    }

    /**
     * Add File Node
     * @param files
     * @param curDir 
     */
    private void addFileNode(List files, DefaultMutableTreeNode curDir) {
        for (int fnum = 0; fnum < files.size(); fnum++) {
            curDir.add(new DefaultMutableTreeNode(files.get(fnum)));
        }
    }

    /**
     * Add Node
     * @param ol
     * @param curPath
     * @param curDir
     * @param files 
     */
    private void addNode(List ol, DefaultMutableTreeNode curDir, List files) {
        File f = null;
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.get(i);
            String newPath = File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory()) {
                addTreeNodes(curDir, f, null);
            } else {
                files.add(thisObject);
            }
        }
    }

    /**
     * Initialize Ol List
     * @param dir
     * @return List
     */
    private List initializeOlList(File dir) {
        List ol = new ArrayList();
        ol.addAll(Arrays.asList(dir.list()));
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        return ol;
    }

    /**
     * Get Selected Dir
     * @return String
     */
    public String getSelectedDir() {
        return selectedDir;
    }

    /**
     * Set Selected Dir
     * @param selectedDir 
     */
    public void setSelectedDir(String selectedDir) {
        this.selectedDir = selectedDir;
    }
}
