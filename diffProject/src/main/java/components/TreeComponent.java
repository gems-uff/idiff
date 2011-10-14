package components;

import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

  //  private String selectedDir;
    public static final Insets defaultScrollInsets = new Insets(8, 8, 8, 8);
    protected Insets scrollInsets = defaultScrollInsets;

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
        // Populate the root node with its subdirectories
        boolean addedNodes = rootNode.populateDirectories(true);
        tree.setModel(new DefaultTreeModel(rootNode));
        // Listen for Tree Selection Events
        tree.addTreeExpansionListener(new TreeExpansionHandler());

    }

    // Returns the full pathname for a path, or null if not a known path
    public String getPathName(TreePath path) {
        Object o = path.getLastPathComponent();
        if (o instanceof FileTreeNode) {
            return ((FileTreeNode) o).fullName;
        }
        return null;
    }

    // Adds a new node to the tree after construction.
    // Returns the inserted node, or null if the parent
    // directory has not been expanded.
    public FileTreeNode addNode(FileTreeNode parent, String name, JTree tree) {
        int index = parent.addNode(name);
        if (index != -1) {
            ((DefaultTreeModel) tree.getModel()).nodesWereInserted(parent,
                    new int[]{index});
            return (FileTreeNode) parent.getChildAt(index);
        }

        // No node was created
        return null;
    }

    // Autoscrolling support
    public void setScrollInsets(Insets insets) {
        this.scrollInsets = insets;
    }

    public Insets getScrollInsets() {
        return scrollInsets;
    }

    // Inner class that represents a node in this file system tree
    public static class FileTreeNode extends DefaultMutableTreeNode {

        public FileTreeNode(String parent, String name)
                throws SecurityException, FileNotFoundException {
            this.name = name;

            // See if this node exists and whether it is a directory
            fullName = parent == null ? name : parent + File.separator + name;

            File f = new File(fullName);
            if (f.exists() == false) {
                throw new FileNotFoundException("File " + fullName
                        + " does not exist");
            }

            isDir = f.isDirectory();

            // Hack for Windows which doesn't consider a drive to be a
            // directory!
            if (isDir == false && f.isFile() == false) {
                isDir = true;
            }
        }

        // Override isLeaf to check whether this is a directory
        @Override
        public boolean isLeaf() {
            return !isDir;
        }

        // Override getAllowsChildren to check whether this is a directory
        @Override
        public boolean getAllowsChildren() {
            return isDir;
        }

        // Return whether this is a directory
        public boolean isDir() {
            return isDir;
        }

        // Get full path
        public String getFullName() {
            return fullName;
        }

        // For display purposes, we return our own name
        @Override
        public String toString() {
            return name;
        }

        // If we are a directory, scan our contents and populate
        // with children. In addition, populate those children
        // if the "descend" flag is true. We only descend once,
        // to avoid recursing the whole subtree.
        // Returns true if some nodes were added
        boolean populateDirectories(boolean descend) {
            boolean addedNodes = false;

            // Do this only once
            if (populated == false) {
                File f;
                try {
                    f = new File(fullName);
                } catch (SecurityException e) {
                    populated = true;
                    return false;
                }

                if (interim == true) {
                    // We have had a quick look here before:
                    // remove the dummy node that we added last time
                    removeAllChildren();
                    interim = false;
                }

                String[] names = f.list(); // Get list of contents

                // Process the contents
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
                                // Only add one node if not descending
                                break;
                            }
                        } catch (Throwable t) {
                            // Ignore phantoms or access problems
                        }
                    }
                }
                if (addedNodes == true) {
                    // Now sort the list of contained files and directories
                    Object[] nodes = list.toArray();
                    Arrays.sort(nodes, new Comparator() {

                        @Override
                        public boolean equals(Object o) {
                            return false;
                        }

                        @Override
                        public int compare(Object o1, Object o2) {
                            FileTreeNode node1 = (FileTreeNode) o1;
                            FileTreeNode node2 = (FileTreeNode) o2;

                            // Directories come first
                            if (node1.isDir != node2.isDir) {
                                return node1.isDir ? -1 : +1;
                            }

                            // Both directories or both files -
                            // compare based on pathname
                            return node1.fullName.compareTo(node2.fullName);
                        }

                        @Override
                        public int hashCode() {
                            int hash = 3;
                            return hash;
                        }
                    });

                    // Add sorted items as children of this node
                    for (int j = 0; j < nodes.length; j++) {
                        this.add((FileTreeNode) nodes[j]);
                    }
                }

                // If we were scanning to get all subdirectories,
                // or if we found no content, there is no
                // reason to look at this directory again, so
                // set populated to true. Otherwise, we set interim
                // so that we look again in the future if we need to
                if (descend == true || addedNodes == false) {
                    populated = true;
                } else {
                    // Just set interim state
                    interim = true;
                }
            }
            return addedNodes;
        }

        // Adding a new file or directory after
        // constructing the FileTree. Returns
        // the index of the inserted node.
        public int addNode(String name) {
            // If not populated yet, do nothing
            if (populated == true) {
                // Do not add a new node if
                // the required node is already there
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    FileTreeNode node = (FileTreeNode) getChildAt(i);
                    if (node.name.equals(name)) {
                        // Already exists - ensure
                        // we repopulate
                        if (node.isDir()) {
                            node.interim = true;
                            node.populated = false;
                        }
                        return -1;
                    }
                }

                // Add a new node
                try {
                    FileTreeNode node = new FileTreeNode(fullName, name);
                    add(node);
                    return childCount;
                } catch (Exception e) {
                }
            }
            return -1;
        }
        protected String name; // Name of this component
        protected String fullName; // Full pathname
        protected boolean populated;// true if we have been populated
        protected boolean interim; // true if we are in interim state
        protected boolean isDir; // true if this is a directory
    }

    // Inner class that handles Tree Expansion Events
    protected class TreeExpansionHandler implements TreeExpansionListener {

        @Override
        public void treeExpanded(TreeExpansionEvent evt) {
            TreePath path = evt.getPath(); // The expanded path
            JTree tree = (JTree) evt.getSource(); // The tree

            // Get the last component of the path and
            // arrange to have it fully populated.
            FileTreeNode node = (FileTreeNode) path.getLastPathComponent();
            if (node.populateDirectories(true)) {
                ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
            }
        }

        @Override
        public void treeCollapsed(TreeExpansionEvent evt) {
            // Nothing to do
        }
    }
}

class FileListTransferable implements Transferable {

    public FileListTransferable(File[] files) {
        fileList = new ArrayList();
        fileList.addAll(Arrays.asList(files));
    }

    // Implementation of the Transferable interface
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.javaFileListFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor fl) {
        return fl.equals(DataFlavor.javaFileListFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor fl) {
        if (!isDataFlavorSupported(fl)) {
            return null;
        }

        return fileList;
    }
    List fileList; // The list of files
}
