package gui.components;

import gui.components.resources.GrainHighLight;
import idiff.resources.Listener;
import idiff.resources.IDIFFStyles;
import ilcs.grain.Grain;
import ilcs.grain.GrainBean;
import ilcs.result.IResultDiff;
import idiff.resources.IDIFFColor;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class GranularityComponent {

    public GranularityComponent() {
    }

    /**
     * Set Moves
     * @param result
     * @param paneFrom
     * @param paneTo
     * @param perspective
     * @param scrollFrom
     * @param scrollTo  
     */
    public void setMoves(IResultDiff result, JTextPane paneFrom, JTextPane paneTo, int perspective, final JScrollPane scrollFrom, final JScrollPane scrollTo) {
        Iterator<Grain> itFrom = result.getGrainsFrom().iterator();
        Iterator<Grain> itTo = result.getGrainsTo().iterator();
        while (itFrom.hasNext() || itTo.hasNext()) {
            Grain grainFrom = itFrom.next();
            Grain grainTo = itTo.next();
            if (verifyConditions(grainFrom, grainTo, perspective)) {
                setMovedGranularity(grainFrom.getGrainBean(), paneFrom, grainTo.getGrainBean(), paneTo, scrollFrom, scrollTo);
            } else {
                setUnchangedGranularity(grainFrom.getGrainBean(), paneFrom, grainTo.getGrainBean(), paneTo, scrollFrom, scrollTo);
            }
        }
    }

    private boolean isSamePosition(Grain grain1, Grain grain2) {
             return (grain1.getOriginalReference().equals(grain2.getOriginalReference()));        
    }
    /**
     * 
     * @param grain1
     * @param grain2
     * @param perpective
     * @return 
     */
    private boolean verifyConditions(Grain grain1, Grain grain2, int perpective) {
        boolean condition = (grain1 != null) || (grain2 != null);
        if (perpective == 1) {
            return ((condition) && (verifyIdIteration(grain1, grain2)) && (!isSamePosition(grain1, grain2)));
        } else {
            return condition;
        }
    }

    private boolean verifyIdIteration(Grain grain1, Grain grain2) {
        return (grain1.getIdIteration() != 1) && (grain2.getIdIteration() != 1);
    }

    /**
     * Set differences (adds and differences)
     * @param result
     * @param paneFrom
     * @param paneTo
     * @param removeStyle
     * @param addStyle  
     */
    public void setDifferences(IResultDiff result, JTextPane paneFrom, JTextPane paneTo, String removeStyle, String addStyle) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                switch (grain.getSituation()) {
                    case REMOVED:
                        IDIFFStyles.setStyle(paneFrom, grain.getGrainBean(), removeStyle);
                        break;
                    case ADDED:
                        IDIFFStyles.setStyle(paneTo, grain.getGrainBean(), addStyle);
                        break;
                }
            }
        }
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
    private void setMovedGranularity(final GrainBean grainBeanFrom, final JTextPane paneFrom, final GrainBean grainBeanTo, final JTextPane paneTo, final JScrollPane scrollFrom, final JScrollPane scrollTo) {

        IDIFFStyles.setStyle(paneFrom, grainBeanFrom, "MoveStyle");
        Listener.setMouseAdapter(paneFrom, paneTo, scrollFrom, scrollTo);
        Listener.setMouseMotion(paneFrom, grainBeanFrom, grainBeanTo, paneTo, scrollFrom, scrollTo);

        IDIFFStyles.setStyle(paneTo, grainBeanTo, "MoveStyle");
        Listener.setMouseAdapter(paneTo, paneFrom, scrollFrom, scrollTo);
        Listener.setMouseMotion(paneTo, grainBeanTo, grainBeanFrom, paneFrom, scrollFrom, scrollTo);
    }

    /**
     * 
     * @param paneFrom
     * @param paneTo 
     */
    void clean(JTextPane paneFrom, JTextPane paneTo) {
        GrainHighLight.removeAllHighLight(paneFrom, paneTo);
        IDIFFStyles.setStyle(paneFrom, new GrainBean(0, paneFrom.getText().length()), "UnchangedStyle");
        IDIFFStyles.setStyle(paneTo, new GrainBean(0, paneTo.getText().length()), "UnchangedStyle");
    }

    void clean(JTextPane pane) {
        Listener.cleanMouseListener(pane);
        GrainHighLight.removeHighLight(pane);
        IDIFFStyles.setStyle(pane, new GrainBean(0, pane.getText().length()), "UnchangedStyle");
    }

    /**
     * 
     * @param grainBeanFrom
     * @param paneFrom
     * @param grainBeanToF
     * @param paneTo
     * @param scrollFrom
     * @param scrollTo 
     */
    private void setUnchangedGranularity(final GrainBean grainBeanFrom, final JTextPane paneFrom, final GrainBean grainBeanTo, final JTextPane paneTo, final JScrollPane scrollFrom, final JScrollPane scrollTo) {
        IDIFFStyles.setStyle(paneFrom, grainBeanFrom, "UnchangedStyle");
        Listener.cleanMouseListener(paneFrom, paneTo, scrollFrom, scrollTo);
        IDIFFStyles.setStyle(paneTo, grainBeanTo, "UnchangedStyle");
        Listener.cleanMouseListener(paneTo, paneFrom, scrollFrom, scrollTo);
    }

    /**
     * 
     * @param grainBean
     * @param pane
     * @param textField 
     */
    public static void setCleanGranularity(final GrainBean grainBean, final JTextPane pane, final JTextField textField) {
        IDIFFStyles.setStyle(pane, grainBean, "CleanGranularity", IDIFFColor.getUnchangedColor());
        Listener.cleanMouseListener(pane);
    }

    public static void setRefactoryGranularity(GrainBean grainBean, JTextPane pane, JScrollPane scrollPane, Color color, String fileName) {
        IDIFFStyles.setStyle(pane, grainBean, "RefactoringStyle", color);
        Listener.setMouseAdapter(pane);
        Listener.setMouseMotion(pane, grainBean, color, fileName);
    }
}
