package details;

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
        StyleConstants.setForeground(style, IDIFFColor.getForegroundBlack());
    }

    /**
     * Set move style
     * @param doc 
     */
    public static void setMoveStyle(StyledDocument doc) {
        setStyleData("MoveStyle", doc, IDIFFColor.getMovedColor());
    }

    /**
     * Set disabled style
     * @param doc 
     */
    public static void setDisabledStyle(StyledDocument doc) {
        Style style = doc.addStyle("DisabledStyle", null);
        StyleConstants.setForeground(style, IDIFFColor.getDisabledForeground());
    }

    /**
     * Set add style
     * @param doc 
     */
    public static void setAddStyle(StyledDocument doc) {
        setStyleData("AddStyle", doc, IDIFFColor.getAddedColor());
    }

    /**
     * Set remove style
     * @param doc 
     */
    public static void setRemoveStyle(StyledDocument doc) {
        setStyleData("RemoveStyle", doc, IDIFFColor.getRemovedColor());
    }

    /**
     * Set unchanged style
     * @param doc 
     */
    public static void setUnchangedStyle(StyledDocument doc) {
        setStyleData("UnchangedStyle", doc, IDIFFColor.getUnchangedColor());
    }

    /**
     * Set refactory style
     * @param doc
     * @param color  
     */
    public static void setRefactoryStyle(StyledDocument doc, Color color) {
        setStyleData("RefactoringStyle", doc, color);
    }
    
    public static void setCleanGranularityStyle(StyledDocument doc, Color color) {
        setStyleData("CleanGranularity", doc, color);
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
    
    public static void setStyle(JTextPane pane, GrainBean grain, String style, Color color) {
        StyledDocument doc = pane.getStyledDocument();
        IDIFFStyles.setCleanGranularityStyle(doc, IDIFFColor.getUnchangedColor());
        IDIFFStyles.setRefactoryStyle(doc, color);
        doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle(style), true);
    }
}
