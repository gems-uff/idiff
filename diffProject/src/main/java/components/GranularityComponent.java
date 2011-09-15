package components;

import algorithms.Grain;
import algorithms.GrainBean;
import algorithms.IResultDiff;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GranularityComponent {

    /**
     * Constructor
     */
    public GranularityComponent() {
    }

    /**
     * Set Moves
     * @param result
     * @param paneFrom
     * @param scrollFrom
     * @param paneTo
     * @param scrollTo 
     */
    public void setMoves(IResultDiff result, JTextPane paneFrom, JScrollPane scrollFrom, JTextPane paneTo, JScrollPane scrollTo) {
        Iterator<Grain> itFrom = result.getGrainsFrom().iterator();
        Iterator<Grain> itTo = result.getGrainsTo().iterator();
        while (itFrom.hasNext() || itTo.hasNext()) {
            Grain grainFrom = itFrom.next();
            Grain grainTo = itTo.next();
            if (((grainFrom != null) || (grainTo != null)) && (!grainFrom.getOriginalReference().equals(grainTo.getOriginalReference()))) {
                setMovedGranularity(grainFrom.getGrainBean(), paneFrom, scrollFrom, grainTo.getGrainBean(), paneTo, scrollTo);
            } else {
                setStyle(paneFrom, grainFrom.getGrainBean(), "UnchangedStyle");
                setStyle(paneTo, grainTo.getGrainBean(), "UnchangedStyle");
            }
        }
    }

    /**
     * Set differences (adds and differences)
     * @param result
     * @param paneFrom
     * @param paneTo 
     */
    public void setDifferences(IResultDiff result, JTextPane paneFrom, JTextPane paneTo) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    setStyle(paneFrom, grain.getGrainBean(), "RemoveStyle");
                } else {
                    setStyle(paneTo, grain.getGrainBean(), "AddStyle");
                }
            }
        }
    }

    /**
     * Set Mouse Adapter
     * @param paneFrom
     * @param paneTo 
     */
    private void setMouseAdapter(final JTextPane paneFrom, final JTextPane paneTo) {
        paneFrom.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                removeHighLight(paneFrom);
                removeHighLight(paneTo);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeHighLight(paneFrom);
                removeHighLight(paneTo);
            }
        });
    }

    /**
     * Set Mouse Motion
     * @param paneFrom
     * @param grainBeanFrom
     * @param scrollFrom
     * @param grainBeanTo
     * @param paneTo
     * @param scrollTo 
     */
    private void setMouseMotion(final JTextPane paneFrom, final GrainBean grainBeanFrom, final JScrollPane scrollFrom, final GrainBean grainBeanTo, final JTextPane paneTo, final JScrollPane scrollTo) {
        paneFrom.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point pt = new Point(e.getX(), e.getY());
                setHighLightPoint(pt, grainBeanFrom, paneFrom, grainBeanTo, paneTo);
            }
        });
    }

    /**
     * Set style
     * @param pane
     * @param grain
     * @param style 
     */
    private void setStyle(JTextPane pane, GrainBean grain, String style) {
        StyledDocument doc = pane.getStyledDocument();
        IDIFFStyles.addStyle(doc, style);
        doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle(style), true);
    }

    /**
     * Remove HighLight
     * @param pane 
     */
    private void removeHighLight(JTextPane pane) {
        Highlighter hlb = pane.getHighlighter();
        hlb.removeAllHighlights();
    }

    /**
     * Set Moved Granularity
     * @param grainBeanFrom
     * @param paneFrom
     * @param scrollFrom
     * @param grainBeanTo
     * @param paneTo
     * @param scrollTo 
     */
    private void setMovedGranularity(final GrainBean grainBeanFrom, final JTextPane paneFrom, final JScrollPane scrollFrom,
            final GrainBean grainBeanTo, final JTextPane paneTo, final JScrollPane scrollTo) {

        setStyle(paneFrom, grainBeanFrom, "MoveStyle");
        setMouseAdapter(paneFrom, paneTo);
        setMouseMotion(paneFrom, grainBeanFrom, scrollFrom, grainBeanTo, paneTo, scrollTo);

        setStyle(paneTo, grainBeanTo, "MoveStyle");
        setMouseAdapter(paneTo, paneFrom);
        setMouseMotion(paneTo, grainBeanTo, scrollTo, grainBeanFrom, paneFrom, scrollFrom);
    }

    /**
     * Set HighLight Point
     * @param pt
     * @param grainBeanFrom
     * @param paneFrom
     * @param grainBeanTo
     * @param paneTo 
     */
    private void setHighLightPoint(Point pt, GrainBean grainBeanFrom, JTextPane paneFrom, GrainBean grainBeanTo, JTextPane paneTo) {
        if ((grainBeanFrom.getStartPosition() <= paneFrom.viewToModel(pt)) && (paneFrom.viewToModel(pt) <= grainBeanFrom.getStartPosition() + grainBeanFrom.getLength())) {
            setHighLight(paneFrom, grainBeanFrom.getStartPosition(), grainBeanFrom.getStartPosition() + grainBeanFrom.getLength());
            setHighLight(paneTo, grainBeanTo.getStartPosition(), grainBeanTo.getStartPosition() + grainBeanFrom.getLength());
        }
    }

    /**
     * Set HighLight
     * @param pane
     * @param begin
     * @param end 
     */
    private void setHighLight(JTextPane pane, int begin, int end) {
        Highlighter hl = pane.getHighlighter();
        hl.removeAllHighlights();
        pane.select(begin, end);
        try {
            hl.addHighlight(begin, end, DefaultHighlighter.DefaultPainter);
        } catch (BadLocationException ex) {
            Logger.getLogger(GranularityComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
