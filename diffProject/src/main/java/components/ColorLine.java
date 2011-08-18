package components;

import java.awt.Color;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class ColorLine {

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

    public static Color getHighLight() {
        return Color.decode("##FCFF00");
    }
}