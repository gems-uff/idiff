package components;

import java.awt.Color;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GranularityColor {

    public static Color getRemovedColor() {
        return Color.decode("#FFAEB9");
    }

    public static Color getAddedColor() {
        return Color.decode("#C1FFC1");
    }

    public static Color getMovedColor() {
        return Color.decode("#B0C4DE");
    }

    public static Color getUnchangedColor() {
        return Color.decode("#FFFFFF");
    }

    public static Color getHighLightColor() {
        return Color.decode("#FCFF00");
    }

    public static Color getForegroundBlack() {
        return Color.decode("#000000");
    }

    public static Color getForegroundHighLight() {
        return Color.decode("#4D0135");
    }

    public static Color getForegroundWhite() {
        return Color.decode("#FFFFFF");
    }
}
