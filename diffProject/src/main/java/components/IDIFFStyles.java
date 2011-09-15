package components;

import java.awt.Color;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class IDIFFStyles {

    public IDIFFStyles() {
    }

    public static void setStyleData(String styleName, StyledDocument doc, Color backgroundColor) {
        Style style = doc.addStyle(styleName, null);
        StyleConstants.setBackground(style, backgroundColor);
        StyleConstants.setForeground(style, GranularityColor.getForegroundBlack());
    }

    public static void setMoveStyle(StyledDocument doc) {
        setStyleData("MoveStyle", doc, GranularityColor.getMovedColor());
    }

    public static void setAddStyle(StyledDocument doc) {
        setStyleData("AddStyle", doc, GranularityColor.getAddedColor());
    }

    public static void setRemoveStyle(StyledDocument doc) {
        setStyleData("RemoveStyle", doc, GranularityColor.getRemovedColor());
    }

    public static void setUnchangedStyle(StyledDocument doc) {
        setStyleData("UnchangedStyle", doc, GranularityColor.getUnchangedColor());
    }

    static void addStyle(StyledDocument doc, String style) {
        switch (style.length()) {
            case 8:
                IDIFFStyles.setAddStyle(doc);
                break;
            case 9:
                IDIFFStyles.setMoveStyle(doc);
                break;
            case 11:
                IDIFFStyles.setRemoveStyle(doc);
                break;
            case 14:
                IDIFFStyles.setUnchangedStyle(doc);
                break;
        }
    }
}
