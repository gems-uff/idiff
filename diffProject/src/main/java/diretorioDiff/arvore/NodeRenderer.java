package diretorioDiff.arvore;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class NodeRenderer extends DefaultTreeCellRenderer  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1067924469099958702L;
	private final Color backgroundColor2;
	private final Color backgroundSelectionColor2;
	private Color backgroundNonSelectionColor2;

	public NodeRenderer() {
		super();
		backgroundColor2 = getBackground();
		backgroundSelectionColor2 = getBackgroundSelectionColor();
		backgroundNonSelectionColor2 = getBackgroundNonSelectionColor();
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree,  
            Object value,  
            boolean sel,  
            boolean expanded,  
            boolean leaf,  
            int row,  
            boolean hasFocus) {

		super.getTreeCellRendererComponent(  
				tree, value, sel,  
				expanded, leaf, row,  
				hasFocus);
		
		if (value instanceof No) {
			No no = (No) value;
			
			if(no.isShowColor() && no.getId() != -1) {
				setBackgroundSelectionColor(no.getColor());
				setBackgroundNonSelectionColor(no.getColor());
			} else {
				setBackgroundSelectionColor(backgroundSelectionColor2);
				setBackgroundNonSelectionColor(backgroundNonSelectionColor2);
			}
			
			if (no.isDirectory()) {
				if (expanded) {
					setIcon(getDefaultOpenIcon());
				} else {
					setIcon(getDefaultClosedIcon());
				}
			}
		} else {
			setBackground(backgroundColor2);
			setBackgroundSelectionColor(backgroundSelectionColor2);
			setBackgroundSelectionColor(backgroundNonSelectionColor2);
		}
		
		return this;
	}

}
