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

	public NodeRenderer() {
		super();
		backgroundColor2 = getBackground();
		backgroundSelectionColor2 = getBackgroundSelectionColor();
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
			
			if(no.getId() != -1){			
				if(no.getColor() != null) {
					setBackground(no.getColor());
					setBackgroundSelectionColor(no.getColor());
				} else {
					setBackground(backgroundColor2);
					setBackgroundSelectionColor(backgroundSelectionColor2);
				}
				
				if(!no.getToolType().trim().isEmpty()) {
					setToolTipText(no.getToolType());
				} else {
					setToolTipText(null);
				}
			} else {
				setBackground(backgroundColor2);
				setBackgroundSelectionColor(backgroundSelectionColor2);
				setToolTipText(null);
			}
			
			if (no.isDirectory()) {
				if (expanded) {
					setIcon(getDefaultOpenIcon());
				} else {
					setIcon(getDefaultClosedIcon());
				}
			}
		}
		
		return this;
	}

}
