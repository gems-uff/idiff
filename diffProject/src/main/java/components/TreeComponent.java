package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * TreeComponent
 * @author Fernanda Floriano Silva
 */
public class TreeComponent extends JPanel {

    /**
     * Construct Tree
     * @param tree
     * @param dir
     * @param scrollPane
     * @param name 
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
     */
    public void addTree(JTree tree, File dir, JScrollPane scrollPane, String name, String fileName) throws SecurityException, FileNotFoundException {
        FileTreeNode rootNode = new FileTreeNode(null, dir.getParent());
        if (rootNode.populateDirectories(true)) {
            tree.setModel(new DefaultTreeModel(rootNode));
            tree.addTreeExpansionListener(new TreeExpansionHandler());
        }
    }

    public static class FileTreeNode extends DefaultMutableTreeNode {

        public FileTreeNode(String parent, String name) throws SecurityException, FileNotFoundException {
            this.name = name;
            fullName = parent == null ? name : parent + File.separator + name;

            File f = new File(fullName);
            isDir = f.isDirectory();
        }

        @Override
        public boolean isLeaf() {
            return !isDir;
        }

        @Override
        public boolean getAllowsChildren() {
            return isDir;
        }

        public boolean isDir() {
            return isDir;
        }

        public String getFullName() {
            return fullName;
        }

        @Override
        public String toString() {
            return name;
        }

        boolean populateDirectories(boolean descend) {
            boolean addedNodes = false;

            if (populated == false) {
                File f;
                try {
                    f = new File(fullName);
                } catch (SecurityException e) {
                    populated = true;
                    return false;
                }

                if (interim == true) {
                    removeAllChildren();
                    interim = false;
                }

                String[] names = f.list();

                ArrayList list = new ArrayList();
                if (names != null) {
                    for (int i = 0; i < names.length; i++) {
                        String nameLocal = names[i];
                        File d = new File(fullName, nameLocal);
                        try {
                            FileTreeNode node = new FileTreeNode(fullName, nameLocal);
                            list.add(node);
                            if (descend && d.isDirectory()) {
                                node.populateDirectories(false);
                            }
                            addedNodes = true;
                            if (descend == false) {
                                break;
                            }
                        } catch (Throwable t) {
                        }
                    }
                }
                if (addedNodes == true) {
                    Object[] nodes = list.toArray();
                    Arrays.sort(nodes, new Comparator() {

                        @Override
                        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
                        public boolean equals(Object o) {
                            return false;
                        }

                        @Override
                        public int compare(Object o1, Object o2) {
                            FileTreeNode node1 = (FileTreeNode) o1;
                            FileTreeNode node2 = (FileTreeNode) o2;

                            if (node1.isDir != node2.isDir) {
                                return node1.isDir ? -1 : +1;
                            }
                            return node1.fullName.compareTo(node2.fullName);
                        }

                        @Override
                        public int hashCode() {
                            int hash = 3;
                            return hash;
                        }
                    });

                    for (int j = 0; j < nodes.length; j++) {
                        this.add((FileTreeNode) nodes[j]);
                    }
                }

                if (descend == true || addedNodes == false) {
                    populated = true;
                } else {
                    interim = true;
                }
            }
            return addedNodes;
        }

        public int addNode(String name) {
            if (populated == true) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    FileTreeNode node = (FileTreeNode) getChildAt(i);
                    if (node.name.equals(name)) {
                        if (node.isDir()) {
                            node.interim = true;
                            node.populated = false;
                        }
                        return -1;
                    }
                }

                try {
                    FileTreeNode node = new FileTreeNode(fullName, name);
                    add(node);
                    return childCount;
                } catch (Exception e) {
                }
            }
            return -1;
        }
        protected String name;
        protected String fullName;
        protected boolean populated;
        protected boolean interim;
        protected boolean isDir;
    }

    protected class TreeExpansionHandler implements TreeExpansionListener {

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
    }
}