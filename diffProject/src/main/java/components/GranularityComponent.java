package components;

import algorithms.Grain;
import algorithms.ILCSBean;
import algorithms.IResultDiff;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GranularityComponent {

    public GranularityComponent() {
    }

    public void setMovedGranularity(ILCSBean ilcsBean, IResultDiff result, JTextPane basePane, JScrollPane baseScroll, JTextPane comparedPane, JScrollPane comparedScroll) {
        /*Document htmlComponent = baseFileEditorPane.getDocument();
        
        Iterator<Grain> it1 = result.getGrainsFrom().iterator();
        Iterator<Grain> it2 = result.getGrainsTo().iterator();
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();
            JLabel line1 = (JLabel) baseFileEditorPane.getComponent(getReference(grain1));
            JLabel line2 = (JLabel) comparedFileEditorPane.getComponent(getReference(grain2));

            if (((grain1 != null) || (grain2 != null)) && (!grain1.getOriginalReference().equals(grain2.getOriginalReference()))) {
                line1.setBackground(GranularityColor.getMovedColor());
                line1.setOpaque(true);

                line2.setBackground(GranularityColor.getMovedColor());
                line2.setOpaque(true);

                //(new EventLine()).addEvent(line1, line2, baseFileEditorPane, baseFileScrollPane, comparedFileEditorPane, comparedFileScrollPane, getReference(grain1), getReference(grain2));
            } else {
                line1.setBackground(GranularityColor.getUnchangedColor());
                line1.setOpaque(true);
                line2.setBackground(GranularityColor.getUnchangedColor());
                line2.setOpaque(true);
            }
        }*/
    }

    public void setDifferencedGranularity(IResultDiff result, JTextPane basePane, JTextPane comparedPane) {
        /*       Document htmlComponent = baseFileEditorPane.getDocument();

        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    JLabel label = ((JLabel) baseFileEditorPane.getComponent(getReference(grain)));
                    label.setBackground(GranularityColor.getRemovedColor());
                    label.setOpaque(true);
                } else { //Grain.Situation.ADDED
                    JLabel label = ((JLabel) comparedFileEditorPane.getComponent(getReference(grain)));
                    label.setBackground(GranularityColor.getAddedColor());
                    label.setOpaque(true);
                }
            }
        }*/
    }

    private int getReference(Grain grain) {
        return grain.getOriginalReference().get(0) - 1;
    }
}
