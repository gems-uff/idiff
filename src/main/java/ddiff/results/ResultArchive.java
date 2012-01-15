package ddiff.results;

import java.util.ArrayList;
import java.util.List;

import ilcs.grain.Grain;
import ddiff.Archive;


public class ResultArchive {

	private Archive base;
	private Archive para;
	private TypeResult tipo;
	private int similaridade = 0;
	private boolean escolhaHungaro = false;
	private List<Grain> grainsFrom = new ArrayList<Grain>();
	private List<Grain> grainsTo = new ArrayList<Grain>();

	/**
	 * Cria uma classe para representar o resultado da compara��o entre arquivos
	 * @param arquivo1
	 * @param arquivo2
	 * @param tipo
	 */
	public ResultArchive(Archive arquivo1, Archive arquivo2,
			TypeResult tipo) {
		this.base = arquivo1;
		this.para = arquivo2;
		this.tipo = tipo;
		
	}

	public ResultArchive(Archive arquivo, TypeResult tipo) {
		
		switch (tipo) {
			case REMOVED:
				base = arquivo;			
				break;
	
			case ADDED:
				para = arquivo;
				break;
				
			default:
				break;
		}
		
		this.tipo = tipo;
	}

	public ResultArchive(Archive base, Archive comparado, int similaridade) {
		this.base = base;
		this.para = comparado;
		this.similaridade = similaridade;		
		
		
		if (similaridade == Result.PERCENTUAL_IDENTICO) {
			if (base.getPathRelativo().equalsIgnoreCase(comparado.getPathRelativo())) {
				this.tipo = TypeResult.UNCHANGED;				
			} else {
				this.tipo = TypeResult.MOVED;
			}
		} else {
			this.tipo = TypeResult.CHANGED;
		}	
	}

	public ResultArchive(Archive base, Archive comparado,
			int similaridade, List<Grain> grainsFrom, List<Grain> grainsTo) {
		this(base, comparado, similaridade);
		this.grainsFrom = grainsFrom;
		this.grainsTo = grainsTo;
	}

	/**
	 * Setter para o campo similaridade
	 * @param similaridade
	 */
	public void setSimilaridade(int similaridade) {
		this.similaridade  = similaridade;
	}

	/**
	 * Setter para o campo escolhaHungaro
	 * @param escolhaHungaro
	 */
	public void setEscolhaHungaro(boolean escolhaHungaro) {
		this.escolhaHungaro = escolhaHungaro;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("TIPO: ").append(tipo).append(" ");
		sb.append("SIMILARIDADE: ").append(getSimilaridade()).append(" ");
		sb.append("HUNGARO: ").append(isEscolhaHungaro()).append(" ");
		sb.append("\n");

		switch (tipo) {
			case ADDED:
				
				sb.append("ADD: ").append(para.getPathRelativo()).append(" ");
				sb.append("\n");
				
				break;
				
			case REMOVED:
				
				sb.append("DEL: ").append(base.getPathRelativo()).append(" ");
				sb.append("\n");
				
				break;
	
			default:
				sb.append("DE: ").append(base.getPathRelativo()).append(" ");
				sb.append("\n");
				sb.append("PARA: ").append(para.getPathRelativo()).append(" ");
				sb.append("\n");
				break;
		}
		
		return sb.toString();
	}

	/**
	 * @return the similaridade
	 */
	public int getSimilaridade() {
		return similaridade;
	}

	/**
	 * @return the escolhaHungaro
	 */
	public boolean isEscolhaHungaro() {
		return escolhaHungaro;
	}

	/**
	 * @return the base
	 */
	public Archive getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(Archive base) {
		this.base = base;
	}

	/**
	 * @return the para
	 */
	public Archive getPara() {
		return para;
	}

	/**
	 * @return the tipo
	 */
	public TypeResult getTipo() {
		return tipo;
	}

	public boolean haveTo() {
		return getPara() != null;
	}
	
	public boolean haveFrom() {
		return getBase() != null;
	}

	public boolean isDirectory() {
		if (haveFrom()) {
			return getBase().isDirectory();
		}
		
		if (haveTo()) {
			return getPara().isDirectory();
		}
		
		return false;
	}

	public boolean isIdFrom(int id) {
		if (haveFrom() && getBase().getId() == id) {
			return true;
		}
		
		return false;
	}
	
	public boolean isIdTo(int id) {
		if (haveTo() && getPara().getId() == id) {
			return true;
		}
		
		return false;
	}

	/**
	 * @return the grainsFrom
	 */
	public List<Grain> getGrainsFrom() {
		return grainsFrom;
	}

	/**
	 * @return the grainsTo
	 */
	public List<Grain> getGrainsTo() {
		return grainsTo;
	}
}
