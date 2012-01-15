package components;

import ilcs.grain.GrainBean;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GrainHighLight {

    /**
     * Set HighLight Point
     * @param pt
     * @param grainBeanFrom
     * @param paneFrom
     * @param grainBeanTo
     * @param paneTo 
     */
    public static void setHighLightPoint(Point pt, GrainBean grainBeanFrom, JTextPane paneFrom, GrainBean grainBeanTo, JTextPane paneTo) {
        if ((grainBeanFrom.getStartPosition() <= paneFrom.viewToModel(pt)) && (paneFrom.viewToModel(pt) <= grainBeanFrom.getStartPosition() + grainBeanFrom.getLength())) {
            setHighLight(paneFrom, grainBeanFrom.getStartPosition(), grainBeanFrom.getStartPosition() + grainBeanFrom.getLength());
            setHighLight(paneTo, grainBeanTo.getStartPosition(), grainBeanTo.getStartPosition() + grainBeanTo.getLength());
        }
    }

    /**
     * 
     * @param pt
     * @param grainBean
     * @param pane 
     */
    public static void setHighLightPoint(Point pt, GrainBean grainBean, JTextPane pane) {
        if ((grainBean.getStartPosition() <= pane.viewToModel(pt)) && (pane.viewToModel(pt) <= grainBean.getStartPosition() + grainBean.getLength())) {
            setHighLight(pane, grainBean.getStartPosition(), grainBean.getStartPosition() + grainBean.getLength());
        }
    }

    /**
     * Set HighLight
     * @param pane
     * @param begin
     * @param end 
     */
    public static void setHighLight(JTextPane pane, int begin, int end) {
        Highlighter hl = pane.getHighlighter();
        hl.removeAllHighlights();
        pane.select(begin, end);
        try {
            hl.addHighlight(begin, end, DefaultHighlighter.DefaultPainter);
        } catch (BadLocationException ex) {
            Logger.getLogger(GranularityComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Remove HighLight
     * @param pane 
     */
    public static void removeHighLight(JTextPane pane) {
        Highlighter hlb = pane.getHighlighter();
        hlb.removeAllHighlights();
    }

    /**
     * Remove All HighLight
     * @param paneFrom
     * @param paneTo 
     */
    public static void removeAllHighLight(final JTextPane paneFrom, final JTextPane paneTo) {
        removeHighLight(paneFrom);
        removeHighLight(paneTo);
    }
}
