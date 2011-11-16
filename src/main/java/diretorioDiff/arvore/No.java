/**
 * 
 */
package diretorioDiff.arvore;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import diretorioDiff.resultados.ResultadoArquivo;
import diretorioDiff.resultados.TipoResultado;

/**
 * @author eborel
 *
 */
public class No extends DefaultMutableTreeNode {

	/**
	 * Serialização
	 */
	private static final long serialVersionUID = -7645350569868965158L;

	private final int id;
	
	private final boolean base;
	
	private List<ResultadoArquivo> resultados = new ArrayList<ResultadoArquivo>();

	private boolean selected = false;
	
	private boolean baseSelection = false;

	private int idStart = -1;

    public List<ResultadoArquivo> getResultados() {
        return resultados;
    }

	/**
	 * @param nomeNo
	 * @param idNo 
	 * @param base 
	 */
	public No(String nomeNo, int idNo, boolean base) {
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

	/**
	 * @return the color
	 */
	public Color getColor() {
		if (!resultados.isEmpty()) {
			if (baseSelection) {
				return resultados.get(0).getTipo().getColor();
			} 
			
			if (idStart != -1) {
				return resultados.get(0).getTipo().getHigthLigthcolor();
			}
		}
		
		return null;
	}

	public boolean isDirectory() {
		if (children().hasMoreElements()) {
			return true;
		} else {
			for (ResultadoArquivo resultado : resultados) {
				return resultado.isDirectory();
			}
		}
		
		return false;
	}

	public String getToolType() {
		String text = "";
		
		if (!resultados.isEmpty()) {
			ResultadoArquivo resultadoArquivo = resultados.get(0);
			if (!isBase()) {
				if (resultadoArquivo.haveFrom()) {
					if (resultadoArquivo.isEscolhaHungaro()) {
						text += " * ";
					}
					
					text += " ";
					text += resultadoArquivo.getSimilaridade();
					text += "%";
				}
			}
		}
		
		return text;
	}

	ResultadoArquivo getResultInReference() {
		if (idStart != -1) {
			if(isBase()) {
				for (ResultadoArquivo resultadoArq1 : resultados) {
					if(resultadoArq1.isIdTo(idStart)) {
						return resultadoArq1;
					}
				}
			} else {
				for (ResultadoArquivo resultadoArq1 : resultados) {
					if(resultadoArq1.isIdFrom(idStart)) {
						return resultadoArq1;
					}
				}
			}
		}
		
		return null;
	}
	
	public void addResultado(ResultadoArquivo resultado) {
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
		
		for (ResultadoArquivo resultado : resultados) {
			if (isBase() && resultado.haveTo()) {
				ids.add(resultado.getPara().getId());
			} else if (!isBase() && resultado.haveFrom()){
				ids.add(resultado.getBase().getId());
			}
		}
		
		return ids;
	}
		
	public boolean isModified() {
		if (isDirectory()) {
			if (children != null) {
				for (Object obj : children) {
					if (obj instanceof No) {
						No no = (No) obj;
						
						if (no.isModified()) {
							return true;
						}
					}
				}
			}
		}
		
		for (ResultadoArquivo resultado: resultados) {
			if (resultado.getTipo() != TipoResultado.UNCHANGED) {
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
		this.baseSelection  = true;
	}

	public void setIdStart(int idStart) {
		this.idStart = idStart;
	}

	public boolean isHugarian() {
		ResultadoArquivo resultInReference = getResultInReference();
		if (resultInReference == null) {
			return false;
		}
		
		return resultInReference.isEscolhaHungaro(); 
	}

	public int getSimilaridade() {
		ResultadoArquivo resultInReference = getResultInReference();
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
			if (obj instanceof No) {
				No no = (No) obj;
				
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
		if (!(obj instanceof No)) {
			return false;
		}
		No other = (No) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
