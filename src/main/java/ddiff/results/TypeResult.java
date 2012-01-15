package ddiff.results;

import idiff.resources.IDIFFColor;
import java.awt.Color;

public enum TypeResult {

    UNCHANGED(IDIFFColor.getUnchangedColor(), new Color(240, 240, 240), "Unchanged"),
    REMOVED(IDIFFColor.getRemovedColor(), IDIFFColor.getRemovedColor(), "Removed"),
    ADDED(IDIFFColor.getAddedColor(), IDIFFColor.getAddedColor(), "Added"),
    MOVED(IDIFFColor.getMovedColor(), IDIFFColor.getHighLightColor(), "Moved"),
    CHANGED(IDIFFColor.getSimilarityColor(), IDIFFColor.getSimilarityHighLightColor(), "Changed");
    
    private final Color color;
    private final Color higthLigthcolor;
    private final String label;

    private TypeResult(Color color, Color higthLigthcolor, String label) {
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
