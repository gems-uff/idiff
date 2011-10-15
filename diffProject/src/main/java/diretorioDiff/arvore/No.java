/**
 * 
 */
package diretorioDiff.arvore;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author eborel
 *
 */
public class No extends DefaultMutableTreeNode {

	private final int id;

	/**
	 * @param nomeNo
	 * @param idNo 
	 */
	public No(String nomeNo, int idNo) {
		super(nomeNo);
		this.id = idNo;
	}

	/**
	 * @return Getter para <code>id</code>.
	 */
	public int getId() {
		return id;
	}

}
