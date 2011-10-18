/**
 * 
 */
package diretorioDiff.arvore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
		
		this.associada = associada;
		if (associada != null) {
			associada.associada = this;
		}
		
		this.base = associada == null;

		getSelectionModel().setSelectionMode(
				TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

		nodes = carregarListaNos(getModel().getRoot());

		setCellRenderer(new NodeRenderer());

		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent paramMouseEvent) {
				//executeEvent(paramMouseEvent);
			}

			@Override
			public void mouseDragged(MouseEvent paramMouseEvent) {
				
			}
		});
		
		addMouseListener(new MouseListener() {
				
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				executeEvent(paramMouseEvent);				
			}

			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}
		});

		setToolTipText("");
		
	}

	@Override
	public String getToolTipText(MouseEvent evt) {
		
		if(resultado != null) {
			TreePath curPath = getPathForLocation(evt.getX(), evt.getY());
			if (curPath != null) {
				Object obj = curPath.getLastPathComponent();
	
				if (obj instanceof No) {
					No no = (No) obj;
					if (no.getId() != -1 && no.isShowColor() ) {
						return no.getToolType();
					}
				}
			}
		}
		return null;
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
				node.setBase(isBase());
				
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
		desativarCoresNos();

		if (associada != null) {
			associada.desativarCoresNos();
			associada.setSelectionPaths(new TreePath[0]);

			if (resultado != null) {

				TreePath tp = getPathForLocation(me.getX(), me.getY());

				if (tp != null) {
					TreePath path = new TreePath(tp.getPath());

					Object object = path.getLastPathComponent();

					if (object instanceof No) {
						No no = (No) object;
						no.setShowColor(true);
						
						associada.selecionarNos(no.getIdsRelacionados());
					}
				} else {
					setSelectionPaths(new TreePath[0]);
				}
			}
			associada.repaint();
		}
		repaint();
	}

	public void selecionarNos(List<Integer> ids) {
		if (ids.isEmpty()) {
			return;
		}

		List<TreePath> paths = new ArrayList<TreePath>();

		for (Integer id : ids) {

			if (id.intValue() > 0) {
				No no = getNo(id.intValue());
				no.setShowColor(true);

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
	private void desativarCoresNos() {
		for (No no : getNodes()) {
			no.setShowColor(false);
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
			for (ResultadoArquivo resultadoArquivo : resultado.getResultadosArquivo()) {
				if (isBase()) {
					if(resultadoArquivo.haveFrom()) {
						getNo(resultadoArquivo.getBase().getId()).addResultado(resultadoArquivo);
					}
				} else {
					if(resultadoArquivo.haveTo()) {
						getNo(resultadoArquivo.getPara().getId()).addResultado(resultadoArquivo);
					}
				}
			}
		}
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
}
