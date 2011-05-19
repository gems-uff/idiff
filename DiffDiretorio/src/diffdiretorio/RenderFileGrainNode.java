/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diffdiretorio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Eraldo
 */
public class RenderFileGrainNode implements TreeCellRenderer  {

    DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component returnValue = null;
        if ((value != null) && (value instanceof DiffDiretorioView.GrainTreeNode)) {

            DiffDiretorioView.GrainTreeNode no = (DiffDiretorioView.GrainTreeNode) value;
                JPanel renderer = new JPanel();

                for (String valor : no.getConteudo()) {
                    JLabel valorL = new JLabel(valor);
                    renderer.add(valorL);
                }

                if (selected) {
                    renderer.setBackground(Color.RED);
                } else {
                    renderer.setBackground(Color.GRAY);
                }
                renderer.setEnabled(tree.isEnabled());
                returnValue = renderer;
        }
        if (returnValue == null) {
            returnValue = defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded,
                    leaf, row, hasFocus);
        }
        return returnValue;
    }

}
