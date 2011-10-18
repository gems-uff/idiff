/**
 * 
 */
package diretorioDiff.arvore;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import diretorioDiff.resultados.ResultadoArquivo;

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
	
	private List<ResultadoArquivo> resultados = new ArrayList<ResultadoArquivo>();

	private boolean base = false;

	private boolean showColor = false;

        public List<ResultadoArquivo> getResultados() {
            return resultados;
        }

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
	 * @return the color
	 */
	public Color getColor() {
		if (resultados.isEmpty()) {
			return null;
		}
		
		return resultados.get(0).getTipo().getColor();
	}

	public boolean isDirectory() {
		if (resultados.isEmpty()) {
			return false;
		}
		
		return resultados.get(0).isDirectory();
	}

	public String getToolType() {
		String text = "";
		
		if (!resultados.isEmpty()) {
			ResultadoArquivo resultadoArquivo = resultados.get(0);
			if (isBase()) {
				text = resultadoArquivo.getTipo().getLabel();
			} else {
				if (resultadoArquivo.haveFrom()) {
					if (resultadoArquivo.isEscolhaHungaro()) {
						text += "Hungarian Sugestion - ";
					}
					
					text += "(";
					text += resultadoArquivo.getSimilaridade();
					text += "%)";	
				} else {
					text = resultadoArquivo.getTipo().getLabel();
				}
			}
		}
		
		return text;
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

	/**
	 * @param base the base to set
	 */
	public void setBase(boolean base) {
		this.base = base;
	}

	public void setShowColor(boolean showColor) {
		this.showColor = showColor;	
	}

	/**
	 * @return the showColor
	 */
	public boolean isShowColor() {
		return showColor;
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
}
