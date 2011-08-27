package components;

import algorithms.Grain;
import algorithms.GrainBean;
import algorithms.IResultDiff;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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

    private void setMovedGranularity(GrainBean grainBeanLeft, JTextPane basePane, JScrollPane baseScroll, GrainBean grainBeanRight, JTextPane comparedPane, JScrollPane comparedScroll)  {
        StyledDocument leftDoc = basePane.getStyledDocument();
        StyledDocument rightDoc = comparedPane.getStyledDocument();
        IDIFFStyles.setMoveStyle(leftDoc);
        IDIFFStyles.setMoveStyle(rightDoc);
        leftDoc.setCharacterAttributes(grainBeanLeft.getStartPosition(), grainBeanLeft.getLength(), basePane.getStyle("MoveStyle"), true);
        rightDoc.setCharacterAttributes(grainBeanRight.getStartPosition(), grainBeanRight.getLength(), comparedPane.getStyle("MoveStyle"), true);
   
    }

    private void setUnchangedGranularity(GrainBean grainBeanLeft, JTextPane basePane, GrainBean grainBeanRight, JTextPane comparedPane) {
        StyledDocument leftDoc = basePane.getStyledDocument();
        StyledDocument rightDoc = comparedPane.getStyledDocument();

        IDIFFStyles.setUnchangedStyle(leftDoc);
        IDIFFStyles.setUnchangedStyle(rightDoc);

        leftDoc.setCharacterAttributes(grainBeanLeft.getStartPosition(), grainBeanLeft.getLength(), basePane.getStyle("UnchangedStyle"), true);
        rightDoc.setCharacterAttributes(grainBeanRight.getStartPosition(), grainBeanRight.getLength(), comparedPane.getStyle("UnchangedStyle"), true);

    }

    public void mouseClicked(MouseEvent e, JTextPane pane) {   
                pane.setCaretPosition(pane.viewToModel(e.getPoint()));   
                System.out.println(pane.getCaretPosition());   
            }  

}
