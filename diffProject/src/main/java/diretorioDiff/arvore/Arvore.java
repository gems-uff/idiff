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
import javax.swing.tree.TreePath;

import diretorioDiff.resultados.Resultado;
import javax.swing.tree.TreeSelectionModel;

/**
 * @author eborel
 * 
 */
public class Arvore extends JTree {

	/**
	 * Serializa��o.
	 */
	private static final long serialVersionUID = -4235871073840441251L;

	private static int idNodeTemp = 0;

	private Arvore associada = null;

	private List<No> nodes = new ArrayList<No>();

	private Resultado resultado = null;

	private boolean base = false;

	/**
	 * @param arquivo
	 */
	public Arvore(File arquivo, boolean base) {
		this(arquivo);
		this.base = base;
	}

	/**
	 * @param arquivo
	 */
	public Arvore(File arquivo) {
		super(loadNodes(arquivo));

		getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

		nodes = carregarListaNos(getModel().getRoot());

		addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				doMouseClicked(me);
			}
		}));
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

	private void doMouseClicked(MouseEvent me) {
		if(associada != null && resultado != null) {
		
			TreePath tp = getPathForLocation(me.getX(), me.getY());
	
			if (tp != null) {
				TreePath path = new TreePath(tp.getPath());
	
				Object object = path.getLastPathComponent();
	
				if (object instanceof No) {
					No no = (No) object;
					
					List<Integer> ids = resultado.getToFilesIds(no.getId());
					associada.selecionarNos(ids);
				}
			}
		}
	}

	public void selecionarNos(List<Integer> idNos) {
            if (idNos.isEmpty()) {
                return;
            }

            List<TreePath> paths = new ArrayList<TreePath>();

		for (Integer idNo: idNos) {
                    if (idNo != -1) {
                        No no = getNo(idNo);
                        
                        TreePath path = new TreePath(getNo(idNo).getPath());
                        paths.add(path);
                        expandParents(path);
                    }
                }

		setSelectionPaths(paths.toArray(new TreePath[paths.size()]));
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

		No node = new No(nomeNo, idNodeTemp);
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

		return node;
	}

	/**
	 * @return Getter para <code>associada</code>.
	 */
	public Arvore getAssociada() {
		return associada;
	}

	/**
	 * Setter para <code>associada</code>.
	 * 
	 * @param <code>associada</code> novo valor
	 */
	public void setAssociada(Arvore associada) {
		this.associada = associada;
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
}
