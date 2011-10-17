/**
 * 
 */
package diretorioDiff.arvore;

import java.awt.Color;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author eborel
 *
 */
public class No extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7645350569868965158L;

	private final int id;
	
	private Color color = null;

	private boolean directory = false;
	
	private String toolType = "";
	
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

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public String getToolType() {
		return toolType;
	}
}
