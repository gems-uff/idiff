package idiff.resources;

import ilcs.grain.GrainBean;
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
    public static final int ADD_STYLE = 8;
    public static final int DISABLE_STYLE = 13;
    public static final int MOVE_STYLE = 9;
    public static final int REMOVE_STYLE = 11;
    public static final int UNCHANGED_STYLE = 14;

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

    /**
     * 
     * @param doc 
     */
    public static void setCleanGranularityStyle(StyledDocument doc) {
        setStyleData("CleanGranularity", doc, IDIFFColor.getUnchangedColor());
    }

    /** 
     * Add style
     * @param doc
     * @param style 
     */
    static void addStyle(StyledDocument doc, String style) {
        switch (style.length()) {
            case ADD_STYLE:
                IDIFFStyles.setAddStyle(doc);
                break;
            case MOVE_STYLE:
                IDIFFStyles.setMoveStyle(doc);
                break;
            case REMOVE_STYLE:
                IDIFFStyles.setRemoveStyle(doc);
                break;
            case DISABLE_STYLE:
                IDIFFStyles.setDisabledStyle(doc);
                break;
            case UNCHANGED_STYLE:
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

    /**
     * 
     * @param pane
     * @param grain
     * @param style
     * @param color 
     */
    public static void setStyle(JTextPane pane, GrainBean grain, String style, Color color) {
        if (style.equals("CleanGranularity")) {
            StyledDocument doc = pane.getStyledDocument();
            IDIFFStyles.setCleanGranularityStyle(doc);
            doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle(style), true);
        } else {
            StyledDocument doc = pane.getStyledDocument();
            IDIFFStyles.setRefactoryStyle(doc, color);
            doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle(style), true);
        }
    }
}
