/**
 * 
 */
package ddiff.tree;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ddiff.DDiffException;
import ddiff.Util;
import ddiff.results.Result;
import ddiff.results.ResultArchive;

/**
 * @author eborel
 * 
 */
public class Tree extends JTree {

    /**
     * Serialização.
     */
    private static final long serialVersionUID = -4235871073840441251L;
    private int idNodeTemp = 0;
    private Tree associada = null;
    private List<Node> nodes = new ArrayList<Node>();
    private Result resultado = null;
    private boolean base = false;
    private DefaultTreeModel modelTree;
    private NodeRenderer renderer;
    private Node selectedNode = null;

    public Node getSelectedNode() {
        return selectedNode;
    }

    private Tree(File diretorio) {
        this(diretorio, null);
    }

    public void setAssociada(Tree associada) {
        this.associada = associada;
    }

    private Tree(File diretorio, Tree associada) {
        super();

        this.associada = associada;

        if (associada != null) {
            associada.setAssociada(this);
        }
        this.base = associada == null;

        modelTree = new DefaultTreeModel(loadNodes(diretorio));
        setModel(modelTree);
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        renderer = new NodeRenderer();
        setCellRenderer(renderer);

        setUI(new BasicTreeUI());
        adicionarEventos();
    }

    /**
     * 
     */
    private void adicionarEventos() {
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent paramMouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent paramMouseEvent) {
                executeEvent(paramMouseEvent, true);
            }

            @Override
            public void mouseReleased(MouseEvent paramMouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent paramMouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent paramMouseEvent) {
            }
        });
    }

    public static Tree getBaseTree(File directory)
            throws DDiffException {
        if (Util.isNotValidDirectory(directory)) {
            throw new DDiffException("Invalid directory.");
        }

        return new Tree(directory);
    }

    public static Tree getComparedTree(File directory, Tree base)
            throws DDiffException {
        if (Util.isNotValidDirectory(directory)) {
            throw new DDiffException("Invalid directory.");
        }

        if (base == null || !base.isBase()) {
            throw new DDiffException("Invalid base tree.");
        }

        return new Tree(directory, base);
    }

    /**
     * @param me
     */
    private void executeEvent(MouseEvent me, boolean click) {

        TreePath tp = getPathForLocation(me.getX(), me.getY());

        if (tp != null && tp.getLastPathComponent() instanceof Node) {
            if (associada != null && resultado != null) {
                Node no = (Node) tp.getLastPathComponent();
                if (no.getId() != -1) {
                    if (!no.isBaseSelection() && no.getIdStart() == -1) {
                        clearNodeSelection();
                        no.select();
                        associada.selecionarNos(no.getIdsRelacionados(), no.getId());
                    }
                    if (isCheckBoxArea(me) && click) {
                        setSelectedNode(no);
                    }
                }
            }
        } else {
            clearNodeSelection();
            clearSelectedNode();
            associada.clearNodeSelection();
            associada.reloadTree(true);
        }

        reloadTree(true);
    }

    public void clearSelectedNode() {
        if (selectedNode != null) {
            selectedNode.setSelected(false);
            selectedNode = null;
        }
    }

    /*    public void setSelectedNode(Node no) {
    Node selected = selectedNode;
    if (selected != null && no.isSelected()) {
    clearSelectedNode();
    if (selected.equals(no)) {//desmarcar
    no.setSelected(false);
    return;
    }
    }
    no.setSelected(!no.isSelected());
    selectedNode = no;
    }
     */
    private void setSelectedNode(Node no) {
        if (no.isBaseSelection() || no.getIdStart() != -1) {
            if (selectedNode != null) {
                if (selectedNode.equals(no)) {
                    if (no.isSelected()) {
                        clearSelectedNode();
                    }
                    no.setSelected(false);
                    return;
                } else {
                    selectedNode.setSelected(false);
                    selectedNode = null;
                }
            }
            no.setSelected(!no.isSelected());
            selectedNode = no;
        }
    }

    /**
     * 
     */
    private void clearNodeSelection() {
        for (Node no : getNodes()) {
            no.clearSelection();
        }
        selectedNode = null;
    }

    /**
     * @param me
     * @return
     */
    private boolean isCheckBoxArea(MouseEvent me) {
        Rectangle localRectangle = getPathBounds(getPathForLocation(me.getX(),
                me.getY()));

        Point point = me.getPoint();
        Dimension size = localRectangle.getSize();
        localRectangle.setSize(14, (int) size.getHeight());
        return localRectangle.contains(point);
    }

    private void reloadTree(boolean keepOldExpanded) {
        Enumeration<TreePath> expandedDescendants = null;
        if (keepOldExpanded) {
            TreeNode[] path = ((DefaultMutableTreeNode) modelTree.getRoot()).getPath();
            expandedDescendants = getExpandedDescendants(new TreePath(path));
        }

        TreePath[] paths = getSelectionPaths();
        modelTree.reload();
        setSelectionPaths(paths);

        if (expandedDescendants != null) {
            while (expandedDescendants.hasMoreElements()) {
                TreePath treePath = (TreePath) expandedDescendants.nextElement();

                expandParents(treePath);
            }
        }
    }

    public void selecionarNos(List<Integer> ids, int idStart) {
        clearNodeSelection();
        TreePath[] treePaths = getSelectionPaths();

        if (!ids.isEmpty()) {
            List<TreePath> paths = new ArrayList<TreePath>();

            for (Integer id : ids) {

                if (id.intValue() > 0) {
                    Node no = getNo(id.intValue());
                    no.setIdStart(idStart);

                    TreePath path = new TreePath(no.getPath());
                    paths.add(path);
                    expandParents(path);
                }
            }

            treePaths = paths.toArray(new TreePath[paths.size()]);
        }

        setSelectionPaths(treePaths);
        reloadTree(false);
    }

    /**
     * @param treeNode
     */
    private void expandParents(TreePath treeNode) {
        if (treeNode == null) {
            return;
        }

        setExpandedState(treeNode, true);

        expandParents(treeNode.getParentPath());
    }

    /**
     * @param idNo2
     */
    private Node getNo(int idNo2) {
        if (idNo2 > 0 && idNo2 <= nodes.size()) {
            return nodes.get(idNo2 - 1);
        }

        return null;
    }

    private Node loadNodes(File arquivo) {
        idNodeTemp = 0;
        return loadNodes(arquivo, false);
    }

    private Node loadNodes(File arquivo, boolean removeBase) {
        String nomeNo = arquivo.getAbsolutePath();
        if (removeBase) {
            nomeNo = nomeNo.replace(arquivo.getParent(), "");

            if (nomeNo.startsWith(File.separator)) {
                nomeNo = nomeNo.replace(File.separator, "");
            }
        }

        Node node;
        File[] filhos = arquivo.listFiles();

        if (arquivo.isDirectory() && filhos != null && filhos.length > 0) {
            node = new Node(nomeNo, -1, isBase());
            for (File filho : filhos) {
                if (!filho.isHidden()) {
                    node.add(loadNodes(filho, true));
                }
            }
        } else {
            idNodeTemp++;
            node = new Node(nomeNo, idNodeTemp, isBase());
        }

        if (node.getId() != -1) {
            nodes.add(node);
        }

        return node;
    }

    /**
     * @return Getter para <code>associada</code>.
     */
    public Tree getAssociada() {
        return associada;
    }

    public void setResultado(Result resultado) {
        this.resultado = resultado;

        if (resultado != null) {
            for (ResultArchive resultadoArquivo : resultado.getResultadosArquivo()) {
                if (isBase()) {
                    if (resultadoArquivo.haveFrom()) {
                        getNo(resultadoArquivo.getBase().getId()).addResultado(
                                resultadoArquivo);
                    }
                } else {
                    if (resultadoArquivo.haveTo()) {
                        getNo(resultadoArquivo.getPara().getId()).addResultado(
                                resultadoArquivo);
                    }
                }
            }
        }

        modelTree.reload();
    }

    public Result getResultado() {
        return resultado;
    }

    /**
     * @return the nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * @return the base
     */
    public boolean isBase() {
        return base;
    }

    public void expandAll(boolean expand) {
        TreeNode root = (TreeNode) getModel().getRoot();

        // Traverse tree from root
        expandAll(new TreePath(root), expand);
    }

    private void expandAll(TreePath parent, boolean expand) {
        // Traverse children
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(path, expand);
            }
        }

        // Expansion or collapse must be done bottom-up
        if (expand) {
            expandPath(parent);
        } else {
            collapsePath(parent);
        }
    }

    public Node getNoSelecionado() {
        TreePath[] ps = getSelectionPaths();

        if (ps.length > 0) {
            Object obj = ps[0].getLastPathComponent();

            if (obj instanceof Node) {
                return (Node) obj;
            }
        }

        return null;
    }

    public void expandNodesWithDiff() {
        for (Node no : nodes) {
            if (no.isModified()) {
                expandParents(new TreePath(no.getPath()));
            }
        }
    }
}
