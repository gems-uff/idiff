package components;

import algorithms.Grain;
import algorithms.GrainBean;
import algorithms.IResultDiff;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GranularityComponent {

    private static Element grainLeft;
    private static Element grainRight;

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
                setUnchangedGranularity(grain1.getGrainBean(), basePane, grain2.getGrainBean(), comparedPane);
            }
        }
    }

    public void setDifferences(IResultDiff result, JTextPane basePane, JTextPane comparedPane) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    setRemovedGranularity(grain.getGrainBean(), basePane);
                } else {
                    setAddedGranularity(grain.getGrainBean(), comparedPane);
                }
            }
        }
    }

    private void setAddedGranularity(GrainBean grain, JTextPane pane) {
        StyledDocument doc = pane.getStyledDocument();
        IDIFFStyles.setAddStyle(doc);
        doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle("AddStyle"), true);

    }

    private void setRemovedGranularity(GrainBean grain, JTextPane pane) {
        StyledDocument doc = pane.getStyledDocument();
        IDIFFStyles.setRemoveStyle(doc);
        doc.setCharacterAttributes(grain.getStartPosition(), grain.getLength(), pane.getStyle("RemoveStyle"), true);
    }

    private void setMovedGranularity(final GrainBean grainBeanLeft, final JTextPane basePane, JScrollPane baseScroll, final GrainBean grainBeanRight, final JTextPane comparedPane, JScrollPane comparedScroll) {
        StyledDocument leftDoc = basePane.getStyledDocument();
        StyledDocument rightDoc = comparedPane.getStyledDocument();
        IDIFFStyles.setMoveStyle(leftDoc);
        IDIFFStyles.setMoveStyle(rightDoc);
        leftDoc.setCharacterAttributes(grainBeanLeft.getStartPosition(), grainBeanLeft.getLength(), basePane.getStyle("MoveStyle"), true);
        rightDoc.setCharacterAttributes(grainBeanRight.getStartPosition(), grainBeanRight.getLength(), comparedPane.getStyle("MoveStyle"), true);

        basePane.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                setStyle(basePane, grainBeanLeft, "MoveStyle");
                setStyle(comparedPane, grainBeanRight, "MoveStyle");
            }
        });

        basePane.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point pt = new Point(e.getX(), e.getY());
                int pos = basePane.viewToModel(pt);
                if (pos >= 0) {
                    StyledDocument doc = basePane.getStyledDocument();
                    Element elem = doc.getCharacterElement(pos);
                    if (elem != null) {
                        if (elem != null) {
                            setStyle(basePane, grainBeanLeft, "HighLightStyle");
                            setStyle(comparedPane, grainBeanRight, "HighLightStyle");
                        } else {
                            setStyle(basePane, grainBeanLeft, "MoveStyle");
                            setStyle(comparedPane, grainBeanRight, "MoveStyle");
                       
                        }
                    }
                }

            }
        });
    }

    public void setStyle(JTextPane pane, GrainBean grainBean, String type) {
        StyledDocument doc = pane.getStyledDocument();
        if (type.equals("HighLightStyle")) {
            IDIFFStyles.setHighLightStyle(doc);
            doc.setCharacterAttributes(grainBean.getStartPosition(), grainBean.getLength(), pane.getStyle(type), true);
        } else {
            IDIFFStyles.setMoveStyle(doc);
            doc.setCharacterAttributes(grainBean.getStartPosition(), grainBean.getLength(), pane.getStyle(type), true);
        }
    }

    private static void changeColor(JTextPane pane, GrainBean grainBean, String type,Color color, Element grain) {
        if (grain != null) {
            HTMLDocument doc = (HTMLDocument) pane.getDocument();
            int start = grain.getStartOffset();
            int end = grain.getEndOffset();
            StyleContext ss = doc.getStyleSheet();
            Style style = ss.addStyle("HighlightedHyperlink", null);
            style.addAttribute(StyleConstants.Foreground, color);
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private void setUnchangedGranularity(GrainBean grainBeanLeft, JTextPane basePane, GrainBean grainBeanRight, JTextPane comparedPane) {
        StyledDocument leftDoc = basePane.getStyledDocument();
        StyledDocument rightDoc = comparedPane.getStyledDocument();

        IDIFFStyles.setUnchangedStyle(leftDoc);
        IDIFFStyles.setUnchangedStyle(rightDoc);

        leftDoc.setCharacterAttributes(grainBeanLeft.getStartPosition(), grainBeanLeft.getLength(), basePane.getStyle("UnchangedStyle"), true);
        rightDoc.setCharacterAttributes(grainBeanRight.getStartPosition(), grainBeanRight.getLength(), comparedPane.getStyle("UnchangedStyle"), true);

    }

    private void setHighLightGranularity(GrainBean grainBeanLeft, JTextPane basePane, GrainBean grainBeanRight, JTextPane comparedPane) {
        StyledDocument leftDoc = basePane.getStyledDocument();
        StyledDocument rightDoc = comparedPane.getStyledDocument();
        IDIFFStyles.setHighLightStyle(leftDoc);
        IDIFFStyles.setHighLightStyle(rightDoc);
        leftDoc.setCharacterAttributes(grainBeanLeft.getStartPosition(), grainBeanLeft.getLength(), basePane.getStyle("HighLightStyle"), true);
        rightDoc.setCharacterAttributes(grainBeanRight.getStartPosition(), grainBeanRight.getLength(), comparedPane.getStyle("HighLightStyle"), true);
    }
}
