package ddiff.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.util.List;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import ddiff.results.ResultArchive;
import ddiff.results.TypeResult;
import idiff.resources.IDIFFColor;

public class NodeRenderer extends DefaultTreeCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = -1067924469099958702L;
    private int threshold = 0;

    public NodeRenderer(int threshold) {
        super();
        this.threshold = threshold;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        Component base = super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);

        if (value instanceof Node) {
            Node no = (Node) value;

            JCheckBox checkbox = new JCheckBox();

            checkbox.setSelected(no.isSelected());

            checkbox.setOpaque(false);

            JLabel label = new JLabel(getIcon(no, expanded));
            label.setOpaque(false);
            label.setText(getText());
            label.setFont(getFont());

            JLabel extraText = new JLabel(getExtraText(no));
            extraText.setOpaque(false);
            //extraText.setFont(getFont());
            extraText.setForeground(IDIFFColor.getExtraTextColor());

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
                if ((no.getSimilaridade() != 0) && (isLessThreshold(no))) {
                    if (no.isBase()) {
                        checkPanel.setBackground(IDIFFColor.getRemovedColor());
                    } else {
                        checkPanel.setBackground(IDIFFColor.getAddedColor());
                    }
                } else {
                    checkPanel.setBackground(color);
                }
            } else {
                checkPanel.setOpaque(false);
            }
            if (no.isDirectory()) {
                checkbox.setVisible(false);
            }
            mainPanel.revalidate();
            return mainPanel;
        }

        return base;
    }

    private boolean isLessThreshold(Node no) {
        return isLessThanThreshold(no.getSimilaridade()) || isLessThanThreshold(getHighSimilarity(no));
    }

    private int getHighSimilarity(Node no) {
        int highSimilarity = 0;
        if (no.isModified()) {
            if (!(no.isDirectory() && no.children().hasMoreElements())) {
                List<ResultArchive> resultados = no.getResultados();
                for (int i = 0; i < resultados.size(); i++) {
                    ResultArchive resultArchive = resultados.get(i);
                    if (!isLessThanThreshold(resultArchive.getSimilaridade())) {
                        highSimilarity = resultArchive.getSimilaridade();
                    }
                }
            }
        }
        return (highSimilarity != 0 ? highSimilarity : no.getSimilaridade());
    }

    private String getExtraText(Node no) {
        String text = "";
        if (no.isModified()) {
            if (no.isDirectory() && no.children().hasMoreElements()) {
                text += " *";
            } else {
                ResultArchive resultado = no.getResultInReference();
                if (resultado != null) {
                    if (!resultado.getTipo().equals(TypeResult.MOVED)) {
                        text += " (" + no.getSimilaridade() + "% similar";

                        if (no.isHugarian()) {
                            text += " - Best Choice";
                        }

                        text += ")";
                        if (isLessThanThreshold(no.getSimilaridade())) {
                            text += " - Less than Threshold";
                        }
                    } else {
                        text += " (100% similar";
                        if (resultado.getBase().getArquivo().getName() == null ? resultado.getPara().getArquivo().getName() == null : resultado.getBase().getArquivo().getName().equals(resultado.getPara().getArquivo().getName())) {
                            text += " - Same File Name";
                        }
                        text += ")";
                    }
                }
            }
        }
        return text;
    }

    private boolean isLessThanThreshold(int similarity) {
        return (similarity <= threshold);
    }

    private Color getColor(Node no) {
        for (ResultArchive resultado : no.getResultados()) {
            if (no.isBaseSelection() || no.getIdStart() != -1) {

                return resultado.getTipo().getHigthLigthcolor();
            }

            return resultado.getTipo().getColor();
        }

        return null;
    }

    private Icon getIcon(Node no, boolean expanded) {
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