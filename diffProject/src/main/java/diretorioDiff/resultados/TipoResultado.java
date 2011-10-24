package diretorioDiff.resultados;

import java.awt.Color;


public enum TipoResultado {
	
	UNCHANGED(Color.WHITE, new Color(240,240,240), "Unchanged"),
	
	REMOVED(new Color(255,174,185), new Color(205,140,149), "Removed"),

	ADDED(new Color(193,255,193), new Color(155,205,155), "Added"),
		
	MOVED(new Color(126,192,238), new Color(108,166,205), "Moved"),
	
	CHANGED(new Color(255, 215, 0), new Color(205, 173, 0), "Changed");

	private final Color color;
	private final Color higthLigthcolor;
	private final String label;

	private TipoResultado (Color color, Color higthLigthcolor, String label) {
		this.higthLigthcolor = higthLigthcolor;
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

	public Color getHigthLigthcolor() {
		return higthLigthcolor;
	}
}
