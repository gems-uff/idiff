package ddiff.tree;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import ddiff.results.ResultArchive;
import ddiff.results.TypeResult;

/**
 * @author eborel
 *
 */
public class Node extends DefaultMutableTreeNode {

    /**
     * Serialização
     */
    private static final long serialVersionUID = -7645350569868965158L;
    private final int id;
    private final boolean base;
    private List<ResultArchive> resultados = new ArrayList<ResultArchive>();
    private boolean selected = false;
    private boolean baseSelection = false;
    private int idStart = -1;

    public List<ResultArchive> getResultados() {
        return resultados;
    }

    /**
     * @param nomeNo
     * @param idNo 
     * @param base 
     */
    public Node(String nomeNo, int idNo, boolean base) {
        super(nomeNo);
        this.id = idNo;
        this.base = base;
    }

    /**
     * @return Getter para <code>id</code>.
     */
    public int getId() {
        return id;
    }

    public boolean isDirectory() {
        if (children().hasMoreElements()) {
            return true;
        } else {
            for (ResultArchive resultado : resultados) {
                return resultado.isDirectory();
            }
        }

        return false;
    }

    ResultArchive getResultInReference() {
        if (idStart != -1) {
            if (isBase()) {
                for (ResultArchive resultadoArq1 : resultados) {
                    if (resultadoArq1.isIdTo(idStart)) {
                        return resultadoArq1;
                    }
                }
            } else {
                for (ResultArchive resultadoArq1 : resultados) {
                    if (resultadoArq1.isIdFrom(idStart)) {
                        return resultadoArq1;
                    }
                }
            }
        }

        return null;
    }

    public void addResultado(ResultArchive resultado) {
        resultados.add(resultado);
    }

    public void clearResultados() {
        resultados.clear();
    }

    /**
     * @return the base
     */
    public boolean isBase() {
        return base;
    }

    public List<Integer> getIdsRelacionados() {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for (ResultArchive resultado : resultados) {
            if (isBase() && resultado.haveTo()) {
                ids.add(resultado.getPara().getId());
            } else if (!isBase() && resultado.haveFrom()) {
                ids.add(resultado.getBase().getId());
            }
        }

        return ids;
    }

    public boolean isModified() {
        if (isDirectory()) {
            if (children != null) {
                for (Object obj : children) {
                    if (obj instanceof Node) {
                        Node no = (Node) obj;

                        if (no.isModified()) {
                            return true;
                        }
                    }
                }
            }
        }

        for (ResultArchive resultado : resultados) {
            if (resultado.getTipo() != TypeResult.UNCHANGED) {
                return true;
            }
        }

        return false;
    }

    public void clearSelection() {
        this.baseSelection = false;
        this.idStart = -1;
        this.selected = false;
    }

    public void select() {
        this.baseSelection = true;
    }

    public void setIdStart(int idStart) {
        this.idStart = idStart;
    }

    public boolean isHugarian() {
        ResultArchive resultInReference = getResultInReference();
        if (resultInReference == null) {
            return false;
        }

        return resultInReference.isEscolhaHungaro();
    }

    public int getSimilaridade() {
        ResultArchive resultInReference = getResultInReference();
        if (resultInReference == null) {
            return 0;
        }

        return resultInReference.getSimilaridade();
    }

    /**
     * @return the baseSelection
     */
    public boolean isBaseSelection() {
        return baseSelection;
    }

    /**
     * @return the idStart
     */
    public int getIdStart() {
        return idStart;
    }

    public boolean haveChildModified() {
        for (Object obj : children) {
            if (obj instanceof Node) {
                Node no = (Node) obj;

                if (no.isModified()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node other = (Node) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}
