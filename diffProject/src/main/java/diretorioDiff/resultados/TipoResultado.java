package diretorioDiff.resultados;

import java.awt.Color;


public enum TipoResultado {
	
	ADICIONADO(Color.CYAN),
	
	DELETADO(Color.RED),
	
	EDITADO(Color.ORANGE),
	
	MOVIDO(Color.YELLOW), 
	
	INALTERADO(Color.LIGHT_GRAY);

	private final Color color;

	private TipoResultado (Color color) {
		this.color = color;		
	}

	public Color getColor() {
		return color;
	}
}
