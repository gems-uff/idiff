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

    static Color getDisabledForeground() {
        return Color.decode("#bebebe");
    }

    static Color getSimilarityColor() {
        return Color.decode("#FFFF00");
    }

    static Color getSimilarityHighLightColor() {
        return Color.decode("#FFAE42");
    }
}
