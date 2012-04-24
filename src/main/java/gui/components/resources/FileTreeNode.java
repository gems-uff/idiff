package gui.components.resources;

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
public class FileTreeNode extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param parent
     * @param name
     * @throws SecurityException
     * @throws FileNotFoundException 
     */
    public FileTreeNode(String parent, String name) throws SecurityException, FileNotFoundException {
        this.name = name;
        fullName = parent == null ? name : parent + File.separator + name;
        File f = new File(getFullName());
        isDir = f.isDirectory();
    }

    /**
     * 
     * @return 
     */
    @Override
    public boolean isLeaf() {
        return !isIsDir();
    }

    /**
     * 
     * @return 
     */
    @Override
    public boolean getAllowsChildren() {
        return isIsDir();
    }

    /**
     * 
     * @return 
     */
    public boolean isDir() {
        return isIsDir();
    }

    /**
     * 
     * @return 
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * 
     * @param descend
     * @return 
     */
    public boolean populateDirectories(boolean descend) {
        boolean addedNodes = false;
        if (isPopulated() == false) {
            File f;
            try {
                f = new File(getFullName());
            } catch (SecurityException e) {
                setPopulated(true);
                return false;
            }
            if (isInterim() == true) {
                removeAllChildren();
                setInterim(false);
            }
            String[] names = f.list();
            ArrayList<FileTreeNode> list = new ArrayList<FileTreeNode>();
            if (names != null) {
                for (int i = 0; i < names.length; i++) {
                    String nameLocal = names[i];
                    File d = new File(getFullName(), nameLocal);
                    try {
                        FileTreeNode node = new FileTreeNode(getFullName(), nameLocal);
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
                    public boolean equals(Object o) {
                        return false;
                    }

                    @Override
                    public int compare(Object o1, Object o2) {
                        FileTreeNode node1 = (FileTreeNode) o1;
                        FileTreeNode node2 = (FileTreeNode) o2;
                        if (node1.isIsDir() != node2.isIsDir()) {
                            return node1.isIsDir() ? -1 : +1;
                        }
                        return node1.getFullName().compareTo(node2.getFullName());
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
                setPopulated(true);
            } else {
                setInterim(true);
            }
        }
        return addedNodes;
    }

    /**
     * 
     * @param name
     * @return 
     */
    public int addNode(String name) {
        if (isPopulated() == true) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                FileTreeNode node = (FileTreeNode) getChildAt(i);
                if (node.getName().equals(name)) {
                    if (node.isDir()) {
                        node.setInterim(true);
                        node.setPopulated(false);
                    }
                    return -1;
                }
            }
            try {
                FileTreeNode node = new FileTreeNode(getFullName(), name);
                add(node);
                return childCount;
            } catch (Exception e) {
            }
        }
        return -1;
    }
    private String name;
    private String fullName;
    private boolean populated;
    private boolean interim;
    private boolean isDir;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the populated
     */
    public boolean isPopulated() {
        return populated;
    }

    /**
     * @param populated the populated to set
     */
    public void setPopulated(boolean populated) {
        this.populated = populated;
    }

    /**
     * @return the isDir
     */
    public boolean isIsDir() {
        return isDir;
    }

    /**
     * @param isDir the isDir to set
     */
    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    /**
     * @return the interim
     */
    public boolean isInterim() {
        return interim;
    }

    /**
     * @param interim the interim to set
     */
    public void setInterim(boolean interim) {
        this.interim = interim;
    }
}
