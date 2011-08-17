package components;

import algorithms.Grain;
import algorithms.ILCSBean;
import algorithms.IResultDiff;
import java.util.Iterator;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GranularityComponent {

    public GranularityComponent() {
    }

    public void setMovedGranularity(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, JEditorPane comparedFileEditorPane, JScrollPane comparedFileScrollPane) {
        Iterator<Grain> it1 = result.getGrainsFrom().iterator();
        Iterator<Grain> it2 = result.getGrainsTo().iterator();
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();
            JLabel line1 = (JLabel) baseFileEditorPane.getComponent(getReference(grain1));
            JLabel line2 = (JLabel) comparedFileEditorPane.getComponent(getReference(grain2));

            if (((grain1 != null) || (grain2 != null)) && (!grain1.getOriginalReference().equals(grain2.getOriginalReference()))) {
                line1.setBackground(ColorLine.getMovedColor());
                line1.setOpaque(true);
                line2.setBackground(ColorLine.getMovedColor());
                line2.setOpaque(true);
                
                (new EventLine()).addEvent(line1, line2, baseFileEditorPane, baseFileScrollPane, comparedFileEditorPane, comparedFileScrollPane);
            } else {
                line1.setBackground(ColorLine.getUnchangedColor());
                line1.setOpaque(true);
                line2.setBackground(ColorLine.getUnchangedColor());
                line2.setOpaque(true);
            }
        }
    }    

    public void setDifferencedGranularity(IResultDiff result, JEditorPane baseFileEditorPane, JEditorPane comparedFileEditorPane) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    JLabel label = ((JLabel) baseFileEditorPane.getComponent(getReference(grain)));
                    label.setBackground(ColorLine.getRemovedColor());
                    label.setOpaque(true);
                } else { //Grain.Situation.ADDED
                    JLabel label = ((JLabel) comparedFileEditorPane.getComponent(getReference(grain)));
                    label.setBackground(ColorLine.getAddedColor());
                    label.setOpaque(true);
                }
            }
        }
    }

    private int getReference(Grain grain) {
        return grain.getOriginalReference().get(0) - 1;
    }
}
