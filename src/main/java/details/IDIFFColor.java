package details;

import java.awt.Color;

/**
 * GranularityColor
 * @author Fernanda Floriano Silva
 */
public class IDIFFColor {

    /**
     * Get removed color
     * @return Color
     */
    public static Color getRemovedColor() {
        return Color.decode("#FFAEB9");
    }

    /**
     * Get added color
     * @return Color
     */
    public static Color getAddedColor() {
        return Color.decode("#C1FFC1");
    }

    /**
     * Get moved color
     * @return Color
     */
    public static Color getMovedColor() {
        return Color.decode("#CAE1FF");
    }

        public static Color getHighLightColor() {
        return Color.decode("#355E79");
    }
        
    /**
     * Get unchanged color
     * @return Color
     */
    public static Color getUnchangedColor() {
        return Color.decode("#FFFFFF");
    }

    /**
     * Get foreground color
     * @return Color
     */
    public static Color getForegroundBlack() {
        return Color.decode("#000000");
    }

    public static Color getDisabledForeground() {
        return Color.decode("#bebebe");
    }

    public static Color getSimilarityColor() {
        return Color.decode("#FFFF99");
    }

    public static Color getSimilarityHighLightColor() {
        return Color.decode("#FFCC66");
    }
}
