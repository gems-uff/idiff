package components;

import algorithms.GrainBean;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * IDIFFStyles
 * @author Fernanda Floriano Silva
 */
public class IDIFFStyles {

    /**
     * Constructor
     */
    public IDIFFStyles() {
    }

    /**
     * Set style
     * @param styleName
     * @param doc
     * @param backgroundColor 
     */
    public static void setStyleData(String styleName, StyledDocument doc, Color backgroundColor) {
        Style style = doc.addStyle(styleName, null);
        StyleConstants.setBackground(style, backgroundColor);
        StyleConstants.setForeground(style, GranularityColor.getForegroundBlack());
    }

    /**
     * Set move style
     * @param doc 
     */
    public static void setMoveStyle(StyledDocument doc) {
        setStyleData("MoveStyle", doc, GranularityColor.getMovedColor());
    }

    /**
     * Set disabled style
     * @param doc 
     */
    public static void setDisabledStyle(StyledDocument doc) {
        Style style = doc.addStyle("DisabledStyle", null);
        StyleConstants.setForeground(style, GranularityColor.getDisabledForeground());
    }

    /**
     * Set add style
     * @param doc 
     */
    public static void setAddStyle(StyledDocument doc) {
        setStyleData("AddStyle", doc, GranularityColor.getAddedColor());
    }

    /**
     * Set remove style
     * @param doc 
     */
    public static void setRemoveStyle(StyledDocument doc) {
        setStyleData("RemoveStyle", doc, GranularityColor.getRemovedColor());
    }

    /**
     * Set unchanged style
     * @param doc 
     */
    public static void setUnchangedStyle(StyledDocument doc) {
        setStyleData("UnchangedStyle", doc, GranularityColor.getUnchangedColor());
    }

    /** 
     * Add style
     * @param doc
     * @param style 
     */
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
            case 13:
                IDIFFStyles.setDisabledStyle(doc);
                break;
            case 14:
                IDIFFStyles.setUnchangedStyle(doc);
                break;
        }
    }
    /**
     * Set style
     * @param pane
     * @param grain
     * @param style 
     */
    public static void setStyle(JTextPane pane, GrainBean grain, String style) {
        StyledDocument doc = pane.getStyledDocument();
        IDIFFStyles.addStyle(doc, style);
        doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle(style), true);
    }

}