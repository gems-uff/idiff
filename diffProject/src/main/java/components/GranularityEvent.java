package components;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
//TODO Corrigir esta classe
public class GranularityEvent {

    public GranularityEvent() {
    }

   public void addEvent(JLabel textLeft, JLabel textRight, JTextPane leftPane, JScrollPane leftScroll, final JTextPane rightPane, final JScrollPane rightScroll, int leftId, int rightId) {
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.PAGE_AXIS));
        addMouseEvent(textLeft, rightPane, rightScroll, leftPane, leftId, rightId);
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
        addMouseEvent(textLeft, leftPane, leftScroll, rightPane, rightId, leftId);
        setPane(textRight, rightPane);
        addAdjustmentEvent(leftScroll, rightScroll, leftId);
        addAdjustmentEvent(rightScroll, leftScroll, rightId);
    }

    private void addMouseEvent(JLabel line, final JTextPane rightPane, final JScrollPane rightScroll, JTextPane leftPane, final int id, final int id2) {
        line.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setBackground(GranularityColor.getHighLight());
                label.setOpaque(true);

                JLabel reference = (JLabel) rightPane.getComponent(id - 1);//Aqui entra a referencia do outro lado
                reference.setBackground(GranularityColor.getHighLight());
                label.setOpaque(true);

                rightScroll.getVerticalScrollBar().setValue(reference.getY());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setBackground(GranularityColor.getMovedColor());
                label.setOpaque(true);
                rightPane.getComponent(id - 1).setBackground(GranularityColor.getMovedColor());
            }
        });

        leftPane.add(line);
    }

    private void addAdjustmentEvent(JScrollPane baseFileScrollPane, final JScrollPane comparedFileScrollPane, final int id) {
        baseFileScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int newPosition = id - 1;
                int start = comparedFileScrollPane.getVerticalScrollBar().getValue();
                int end = (int) (comparedFileScrollPane.getSize().getHeight() + start);
                if (newPosition > end || newPosition < start) {
                    comparedFileScrollPane.getVerticalScrollBar().setValue(newPosition);
                }
            }
        });
    }

    private void setPane(JLabel line, final JTextPane rightPane) {
        line.setBackground(GranularityColor.getMovedColor());
        line.setOpaque(true);
        rightPane.add(line);
    }
}
