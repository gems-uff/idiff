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

    public GranularityComponent() {
    }

    public void setMoves(IResultDiff result, JTextPane basePane, JScrollPane baseScroll, JTextPane comparedPane, JScrollPane comparedScroll) {
        Iterator<Grain> it1 = result.getGrainsFrom().iterator();
        Iterator<Grain> it2 = result.getGrainsTo().iterator();
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();
            if (((grain1 != null) || (grain2 != null)) && (!grain1.getOriginalReference().equals(grain2.getOriginalReference()))) {
                setMovedGranularity(grain1.getGrainBean(), basePane, baseScroll, grain2.getGrainBean(), comparedPane, comparedScroll);
            } else {
                setStyle(basePane, grain1.getGrainBean(), "UnchangedStyle");
                setStyle(comparedPane, grain2.getGrainBean(), "UnchangedStyle");
            }
        }
    }

    public void setDifferences(IResultDiff result, JTextPane basePane, JTextPane comparedPane) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    setStyle(basePane, grain.getGrainBean(), "RemoveStyle");
                } else {
                    setStyle(comparedPane, grain.getGrainBean(), "AddStyle");
                }
            }
        }
    }

    private void setMouseAdapter(final JTextPane pane1, final JTextPane pane2) {
        pane1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                removeHighLight(pane1);
                removeHighLight(pane2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeHighLight(pane1);
                removeHighLight(pane2);
            }
        });
    }

    private void setMouseMotion(final JTextPane pane1, final GrainBean grainBean1, final JScrollPane scroll1, final GrainBean grainBean2, final JTextPane pane2, final JScrollPane scroll2) {
        pane1.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point pt = new Point(e.getX(), e.getY());
                setHighLightPoint(pt, grainBean1, pane1, scroll1, grainBean2, pane2, scroll2);
            }
        });
    }

    private void setStyle(JTextPane pane, GrainBean grain, String style) {
        StyledDocument doc = pane.getStyledDocument();
        IDIFFStyles.addStyle(doc, style);
        doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle(style), true);
    }

    private void removeHighLight(JTextPane pane) {
        Highlighter hlb = pane.getHighlighter();
        hlb.removeAllHighlights();
    }

    private void setMovedGranularity(final GrainBean grainBeanLeft, final JTextPane basePane, final JScrollPane baseScroll,
            final GrainBean grainBeanRight, final JTextPane comparedPane, final JScrollPane comparedScroll) {

        setStyle(basePane, grainBeanLeft, "MoveStyle");
        setMouseAdapter(basePane, comparedPane);
        setMouseMotion(basePane, grainBeanLeft, baseScroll, grainBeanRight, comparedPane, comparedScroll);

        setStyle(comparedPane, grainBeanRight, "MoveStyle");
        setMouseAdapter(comparedPane, basePane);
        setMouseMotion(comparedPane, grainBeanRight, comparedScroll, grainBeanLeft, basePane, baseScroll);
    }

    private void setHighLightPoint(Point pt, GrainBean grainBeanBase, JTextPane basePane, JScrollPane baseScroll,
            GrainBean grainBeanCompared, JTextPane comparedPane, JScrollPane comparedScroll) {
        if ((grainBeanBase.getStartPosition() <= basePane.viewToModel(pt)) && (basePane.viewToModel(pt) <= grainBeanBase.getStartPosition() + grainBeanBase.getLength())) {
            setHighLight(basePane, grainBeanBase.getStartPosition(), grainBeanBase.getStartPosition() + grainBeanBase.getLength());
            setHighLight(comparedPane, grainBeanCompared.getStartPosition(), grainBeanCompared.getStartPosition() + grainBeanBase.getLength());
        }
    }

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
