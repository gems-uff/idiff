/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

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

    public static void setMoveStyle(StyledDocument doc) {
        Style style = doc.addStyle("MoveStyle", null);
        StyleConstants.setBackground(style, GranularityColor.getMovedColor());
        StyleConstants.setForeground(style, GranularityColor.getForegroundWhite());
    }

    public static void setAddStyle(StyledDocument doc) {
        Style style = doc.addStyle("AddStyle", null);
        StyleConstants.setBackground(style, GranularityColor.getAddedColor());
        StyleConstants.setForeground(style, GranularityColor.getForegroundBlack());

    }

    public static void setRemoveStyle(StyledDocument doc) {
        Style style = doc.addStyle("RemoveStyle", null);
        StyleConstants.setBackground(style, GranularityColor.getRemovedColor());
        StyleConstants.setForeground(style, GranularityColor.getForegroundBlack());

    }

    public static void setHighLightStyle(StyledDocument doc) {
        Style style = doc.addStyle("HighLightStyle", null);
        StyleConstants.setBackground(style, GranularityColor.getHighLightColor());
        StyleConstants.setForeground(style, GranularityColor.getForegroundHighLight());

    }

    public static void setUnchangedStyle(StyledDocument doc) {
        Style style = doc.addStyle("UnchangedStyle", null);
        StyleConstants.setBackground(style, GranularityColor.getUnchangedColor());
        StyleConstants.setForeground(style, GranularityColor.getForegroundBlack());

    }
}
