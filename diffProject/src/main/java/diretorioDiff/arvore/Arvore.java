/**
 * 
 */
package diretorioDiff.arvore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import diretorioDiff.DiretorioDiffException;
import diretorioDiff.Util;
import diretorioDiff.resultados.Resultado;
import diretorioDiff.resultados.ResultadoArquivo;

import javax.swing.tree.TreeSelectionModel;

/**
 * @author eborel
 * 
 */
public class Arvore extends JTree {

	/**
	 * Serialização.
	 */
	private static final long serialVersionUID = -4235871073840441251L;

	private static int idNodeTemp = 0;

	private Arvore associada = null;

	private List<No> nodes = new ArrayList<No>();

	private Resultado resultado = null;

	private boolean base = false;

	private Arvore(File diretorio) {
		this(diretorio, null);
	}

	private Arvore(File diretorio, Arvore associada) {
		super(loadNodes(diretorio));

		getSelectionModel().setSelectionMode(
				TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

		nodes = carregarListaNos(getModel().getRoot());

		setCellRenderer(new NodeRenderer());

		addMouseListener((new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				executeEvent(e);
			}
		}));

		this.associada = associada;
		if (associada != null) {
			associada.associada = this;
		}

		this.base = associada == null;
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
	 * @param root
	 */
	private List<No> carregarListaNos(Object obj) {
		List<No> nos = new ArrayList<No>();

		if (obj instanceof DefaultMutableTreeNode) {
			if (obj instanceof No) {
				No node = (No) obj;

				if (node.getId() != -1) {
					nos.add(node);
				}

				Enumeration children = node.children();
				while (children.hasMoreElements()) {
					nos.addAll(carregarListaNos(children.nextElement()));
				}
			}

		}

		return nos;
	}

	/**
	 * @param me
	 */
	private void executeEvent(MouseEvent me) {
		clearNodesColors();

		if (associada != null) {
			associada.clearNodesColors();
			associada.setSelectionPaths(new TreePath[0]);
			associada.repaint();

			if (resultado != null) {

				TreePath tp = getPathForLocation(me.getX(), me.getY());

				if (tp != null) {
					TreePath path = new TreePath(tp.getPath());

					Object object = path.getLastPathComponent();

					if (object instanceof No) {
						No no = (No) object;

						List<ResultadoArquivo> resultados = new ArrayList<ResultadoArquivo>();

						if (isBase()) {
							resultados = resultado.getResultadosByFrom(no
									.getId());
						} else {
							resultados = resultado
									.getResultadosByTo(no.getId());
						}

						if (resultados.size() > 0) {
							ResultadoArquivo resultadoArquivo = resultados.get(0);
							no.setColor(resultadoArquivo.getTipo().getColor());

							if (!isBase()) {
								String text = "";
								if(resultadoArquivo.isEscolhaHungaro()){
									text += "Hungarian Sugestion - ";
								}
								text += "(" + resultadoArquivo.getSimilaridade() + "%)";
								no.setToolType(text);
							}
						}

						associada.selecionarNos(resultados);
					}
				}  else {
					setSelectionPaths(new TreePath[0]);
				}
			}
		}
		repaint();
	}

	public void selecionarNos(List<ResultadoArquivo> resultados) {
		if (resultados.isEmpty()) {
			return;
		}

		List<TreePath> paths = new ArrayList<TreePath>();

		for (ResultadoArquivo resultado : resultados) {

			int id = -1;
			if (!isBase() && resultado.haveTo()) {
				id = resultado.getPara().getId();
			} else if (isBase() && resultado.haveFrom()) {
				id = resultado.getBase().getId();
			}

			if (id > 0) {
				No no = getNo(id);
				no.setColor(resultado.getTipo().getColor());

				TreePath path = new TreePath(no.getPath());
				paths.add(path);
				expandParents(path);
			}
		}

		setSelectionPaths(paths.toArray(new TreePath[paths.size()]));
	}

	/**
	 * 
	 */
	private void clearNodesColors() {
		TreePath[] selectionPaths = getSelectionPaths();
		if (selectionPaths != null) {
			for (TreePath treePath : selectionPaths) {
				Object lastPathComponent = treePath.getLastPathComponent();
				if (lastPathComponent instanceof No) {
					No no = (No) lastPathComponent;
					no.setColor(null);
					no.setToolType("");
				}
			}
		}
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

	private static No loadNodes(File arquivo) {
		idNodeTemp = 0;
		return loadNodes(arquivo, false);
	}

	private static No loadNodes(File arquivo, boolean removeBase) {
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
			node = new No(nomeNo, -1);
			for (File filho : filhos) {
				if (!filho.isHidden()) {
					node.add(loadNodes(filho, true));
				}
			}
		} else {
			idNodeTemp++;
			node = new No(nomeNo, idNodeTemp);
		}

		node.setDirectory(arquivo.isDirectory());

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
}
