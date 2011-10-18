package diretorioDiff.resultados;

import java.awt.Color;


public enum TipoResultado {
	
	ADICIONADO(Color.CYAN, "ADD"),
	
	DELETADO(Color.RED, "DEL"),
	
	EDITADO(Color.ORANGE, "EDIT"),
	
	MOVIDO(Color.YELLOW, "MOVE"), 
	
	INALTERADO(Color.LIGHT_GRAY, "UNCHANGED");

	private final Color color;
	private final String label;

	private TipoResultado (Color color, String label) {
		this.color = color;
		this.label = label;		
	}

	public Color getColor() {
		return color;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
}
