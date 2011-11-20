/**
 * 
 */
package diretorioDiff.arvore;

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

import diretorioDiff.DiretorioDiffException;
import diretorioDiff.Util;
import diretorioDiff.resultados.Resultado;
import diretorioDiff.resultados.ResultadoArquivo;

/**
 * @author eborel
 * 
 */
public class Arvore extends JTree {

	/**
	 * Serialização.
	 */
	private static final long serialVersionUID = -4235871073840441251L;

	private int idNodeTemp = 0;

	private Arvore associada = null;

	private List<No> nodes = new ArrayList<No>();

	private Resultado resultado = null;

	private boolean base = false;

	private DefaultTreeModel modelTree;

	private NodeRenderer renderer;

	private No selectedNode;

	public No getSelectedNode() {
		return selectedNode;
	}

	private Arvore(File diretorio) {
		this(diretorio, null);
	}

	private Arvore(File diretorio, Arvore associada) {
		super();

		this.associada = associada;
		if (associada != null) {
			associada.associada = this;
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
				executeEvent(paramMouseEvent);
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

	public static Arvore getBaseTree(File directory)
			throws DiretorioDiffException {
		if (Util.isNotValidDirectory(directory)) {
			throw new DiretorioDiffException("Invalid directory.");
		}

		return new Arvore(directory);
	}

	public static Arvore getComparedTree(File directory, Arvore base)
			throws DiretorioDiffException {
		if (Util.isNotValidDirectory(directory)) {
			throw new DiretorioDiffException("Invalid directory.");
		}

		if (base == null || !base.isBase()) {
			throw new DiretorioDiffException("Invalid base tree.");
		}

		return new Arvore(directory, base);
	}

	/**
	 * @param me
	 */
	private void executeEvent(MouseEvent me) {

		TreePath tp = getPathForLocation(me.getX(), me.getY());

		if (tp != null && tp.getLastPathComponent() instanceof No) {
			if (associada != null && resultado != null) {
				No no = (No) tp.getLastPathComponent();
				if (no.getId() != -1) {

					if (!no.isBaseSelection() && no.getIdStart() == -1) {
						clearNodeSelection();
						no.select();

						associada.selecionarNos(no.getIdsRelacionados(),
								no.getId());
					}

					if (isCheckBoxArea(me)) {
						setSelectedNode(no);
					}
				}
			}
		} else {
			clearNodeSelected();
			clearNodeSelection();
			associada.clearNodeSelection();
			associada.clearNodeSelected();
			associada.reloadTree(true);
		}

		reloadTree(true);
	}

	private void clearNodeSelected() {
		if (selectedNode != null) {
			selectedNode.clearSelection();
			selectedNode = null;
		}
	}

	private void setSelectedNode(No no) {

		if (no.isBaseSelection() || no.getIdStart() != -1) {
			if (selectedNode != null) {
				if (!selectedNode.equals(no)) {
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
		for (No no : getNodes()) {
			no.clearSelection();
		}
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
			TreeNode[] path = ((DefaultMutableTreeNode) modelTree.getRoot())
					.getPath();
			expandedDescendants = getExpandedDescendants(new TreePath(path));
		}

		TreePath[] paths = getSelectionPaths();
		modelTree.reload();
		setSelectionPaths(paths);

		if (expandedDescendants != null) {
			while (expandedDescendants.hasMoreElements()) {
				TreePath treePath = (TreePath) expandedDescendants
						.nextElement();

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
					No no = getNo(id.intValue());
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
	private No getNo(int idNo2) {
		if (idNo2 > 0 && idNo2 <= nodes.size()) {
			return nodes.get(idNo2 - 1);
		}

		return null;
	}

	private No loadNodes(File arquivo) {
		idNodeTemp = 0;
		return loadNodes(arquivo, false);
	}

	private No loadNodes(File arquivo, boolean removeBase) {
		String nomeNo = arquivo.getAbsolutePath();
		if (removeBase) {
			nomeNo = nomeNo.replace(arquivo.getParent(), "");

			if (nomeNo.startsWith(File.separator)) {
				nomeNo = nomeNo.replace(File.separator, "");
			}
		}

		No node;
		File[] filhos = arquivo.listFiles();

		if (arquivo.isDirectory() && filhos.length > 0) {
			node = new No(nomeNo, -1, isBase());
			for (File filho : filhos) {
				if (!filho.isHidden()) {
					node.add(loadNodes(filho, true));
				}
			}
		} else {
			idNodeTemp++;
			node = new No(nomeNo, idNodeTemp, isBase());
		}

		if (node.getId() != -1) {
			nodes.add(node);
		}

		return node;
	}

	/**
	 * @return Getter para <code>associada</code>.
	 */
	public Arvore getAssociada() {
		return associada;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;

		if (resultado != null) {
			for (ResultadoArquivo resultadoArquivo : resultado
					.getResultadosArquivo()) {
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

	public Resultado getResultado() {
		return resultado;
	}

	/**
	 * @return the nodes
	 */
	public List<No> getNodes() {
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

	public No getNoSelecionado() {
		TreePath[] ps = getSelectionPaths();

		if (ps.length > 0) {
			Object obj = ps[0].getLastPathComponent();

			if (obj instanceof No) {
				return (No) obj;
			}
		}

		return null;
	}

	public void expandNodesWithDiff() {
		for (No no : nodes) {
			if (no.isModified()) {
				expandParents(new TreePath(no.getPath()));
			}
		}
	}
}
