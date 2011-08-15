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

    private ColorLine colorLine = new ColorLine();

    public GranularityComponent() {
    }

    public void setDifferenceColor(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JEditorPane comparedFileEditorPane) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    colorLine.setRemovedColor(new JLabel("teste"));
                } else {
                    colorLine.setAddedColor(new JLabel("teste"));
                }
            }
        }
    }

    public void setMoveColor(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, JEditorPane comparedFileEditorPane, JScrollPane comparedFileScrollPane) {
        Iterator<Grain> it1 = result.getGrainsFrom().iterator();
        Iterator<Grain> it2 = result.getGrainsTo().iterator();
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();
            if ((grain1 != null) || (grain2 != null)) {
                if (!grain1.getOriginalReference().equals(grain2.getOriginalReference())) {
                    colorLine.setMovedColor(new JLabel("teste"));
                } else {
                    colorLine.setUnchangedColor(new JLabel("teste"));
                }
            }
        }
    }
}
