package details;

import java.awt.Point;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Scroll {

    /**
     * Adjustment Scroll
     * @param scrollFrom
     * @param scrollTo  
     */
    public static void adjustmentScroll(JScrollPane scrollFrom, JScrollPane scrollTo) {
        adjustment(scrollFrom, scrollTo, scrollFrom.getHorizontalScrollBar());
        adjustment(scrollFrom, scrollTo, scrollFrom.getVerticalScrollBar());
        adjustment(scrollTo, scrollFrom, scrollTo.getHorizontalScrollBar());
        adjustment(scrollTo, scrollFrom, scrollTo.getVerticalScrollBar());
    }

    /**
     * 
     * @param scrollFrom
     * @param scrollTo 
     */
    public static void removeAdjustmentScroll(JScrollPane scrollFrom, JScrollPane scrollTo) {
        remove(scrollFrom.getHorizontalScrollBar());
        remove(scrollFrom.getVerticalScrollBar());
        remove(scrollTo.getHorizontalScrollBar());
        remove(scrollTo.getVerticalScrollBar());
    }

    /**
     * Adjustment Horizontal Scroll
     * @param scrollTo
     * @param scrollFrom 
     */
    private static void adjustment(final JScrollPane scrollTo, final JScrollPane scrollFrom, final JScrollBar scrollBar) {
        scrollBar.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0) {
                Point point = scrollTo.getViewport().getViewPosition();
                scrollFrom.getViewport().setViewPosition(point);
            }
        });
    }

    /**
     * 
     * @param scrollBar 
     */
    private static void remove(final JScrollBar scrollBar) {
        for (int i = 0; i < scrollBar.getAdjustmentListeners().length; i++) {
            scrollBar.removeAdjustmentListener(scrollBar.getAdjustmentListeners()[i]);
        }
    }
}