package diretorioDiff.arvore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import diretorioDiff.resultados.ResultadoArquivo;
import diretorioDiff.resultados.TipoResultado;
import java.awt.event.MouseListener;

public class NodeRenderer extends DefaultTreeCellRenderer {

	private static final Color EXTRA_TEXT_COLOR = Color.RED;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1067924469099958702L;
	
	public NodeRenderer() {
		super();
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		Component base = super.getTreeCellRendererComponent(tree, value, sel,
				expanded, leaf, row, hasFocus);

		if (value instanceof No) {
			No no = (No) value;
			JCheckBox checkbox = new JCheckBox();
                        
                        checkbox.setSelected(no.isSelected());
			
                        checkbox.setOpaque(false);

			JLabel label = new JLabel(getIcon(no, expanded));
			label.setOpaque(false);
			label.setText(getText());
			label.setFont(getFont());

			JLabel extraText = new JLabel(getExtraText(no));
			extraText.setOpaque(false);
			extraText.setFont(getFont());
			extraText.setForeground(EXTRA_TEXT_COLOR);

			JPanel mainPanel = new JPanel(new BorderLayout());
			mainPanel.setOpaque(false);

			JPanel checkPanel = new JPanel(new BorderLayout());

			if (no.getChildCount() == 0) {
				checkPanel.add(checkbox, BorderLayout.WEST);
				checkPanel.add(label, BorderLayout.EAST);

				mainPanel.add(checkPanel, BorderLayout.WEST);
				mainPanel.add(extraText, BorderLayout.EAST);
			} else {
				checkPanel.add(label, BorderLayout.WEST);

				mainPanel.add(checkPanel, BorderLayout.WEST);
				mainPanel.add(extraText, BorderLayout.EAST);
			}

			Color color = getColor(no);
			if (color != null) {
				checkPanel.setBackground(color);
			} else {
				checkPanel.setOpaque(false);
			}

			mainPanel.revalidate();
			return mainPanel;
		}

		return base;
	}

	private String getExtraText(No no) {
		String text = "";
		if (no.isModified()) {
			if (no.isDirectory() && no.children().hasMoreElements()) {
				text += " *";
			} else {
				ResultadoArquivo resultado = no.getResultInReference();
				if (resultado != null && !resultado.getTipo().equals(TipoResultado.MOVED)) {
                                        text += " (" + no.getSimilaridade() + "% similar";
                                    
					if (no.isHugarian()) {
						text += " - Best Choice";
					}

					text += ")";
				}
			}
		}

		return text;
	}

	private Color getColor(No no) {
		for (ResultadoArquivo resultado : no.getResultados()) {
			if (no.isBaseSelection() || no.getIdStart() != -1) {
				return resultado.getTipo().getHigthLigthcolor();
			}

			return resultado.getTipo().getColor();
		}

		return null;
	}

	private Icon getIcon(No no, boolean expanded) {
		if (no.isDirectory()) {
			if (expanded) {
				return getDefaultOpenIcon();
			} else {
				return getDefaultClosedIcon();
			}
		}

		return getDefaultLeafIcon();
	}
}
