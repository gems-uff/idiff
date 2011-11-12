/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Fernanda Floriano Silva
 */
@SuppressWarnings("serial")
public class FileTreeNode extends DefaultMutableTreeNode {

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

    @SuppressWarnings("unchecked")
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
            ArrayList<FileTreeNode> list = new ArrayList<FileTreeNode>();
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
                Arrays.sort(nodes, new Comparator<Object>() {

                    @Override
                    @SuppressWarnings(value = "EqualsWhichDoesntCheckParameterClass")
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
