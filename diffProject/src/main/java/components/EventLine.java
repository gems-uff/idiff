/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class EventLine {

    public EventLine() {
    }

    public void addEvent(JLabel textLeft, JLabel textRight, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, final JEditorPane comparedFileEditorPane, final JScrollPane comparedFileScrollPane) {
        baseFileEditorPane.setLayout(new BoxLayout(baseFileEditorPane, BoxLayout.PAGE_AXIS));
        addMouseEvent(textLeft, comparedFileEditorPane, comparedFileScrollPane, baseFileEditorPane);
        comparedFileEditorPane.setLayout(new BoxLayout(comparedFileEditorPane, BoxLayout.PAGE_AXIS));
        setEditorPane(textRight, comparedFileEditorPane);
        addAdjustmentEvent(baseFileScrollPane, comparedFileScrollPane);
    }

    private void addMouseEvent(JLabel linha, final JEditorPane comparedFileEditorPane, final JScrollPane comparedFileScrollPane, JEditorPane baseFileEditorPane) {
        linha.setBackground(Color.WHITE);
        linha.setOpaque(true);

        linha.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.BLACK);
                label.setBackground(Color.decode("#ffc0cb"));

                JLabel labelReferente = (JLabel) comparedFileEditorPane.getComponent(1);//Aqui entra a referencia do outro lado

                labelReferente.setForeground(Color.BLACK);
                labelReferente.setBackground(Color.decode("#ffc0cb"));

                comparedFileScrollPane.getVerticalScrollBar().setValue(labelReferente.getY());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.BLACK);
                label.setBackground(Color.decode("#b0c4de"));

                comparedFileEditorPane.getComponent(1).setForeground(Color.BLACK);//Aqui entra a referencia do outro lado - getComponent
                comparedFileEditorPane.getComponent(1).setBackground(Color.WHITE);//Aqui entra a referencia do outro lado - getComponent

            }
        });

        baseFileEditorPane.add(linha);
    }

    private void addAdjustmentEvent(JScrollPane baseFileScrollPane, final JScrollPane comparedFileScrollPane) {
        baseFileScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int novaPosicao = e.getValue() * 2;
                int posicaoInicial = comparedFileScrollPane.getVerticalScrollBar().getValue();
                int posicaoFinal = (int) (comparedFileScrollPane.getSize().getHeight() + posicaoInicial);

                if (novaPosicao > posicaoFinal || novaPosicao < posicaoInicial) {
                    comparedFileScrollPane.getVerticalScrollBar().setValue(novaPosicao);
                }
            }
        });
    }

    private void setEditorPane(JLabel linha, final JEditorPane comparedFileEditorPane) {
        linha.setBackground(Color.WHITE);
        linha.setOpaque(true);
        comparedFileEditorPane.add(linha);
    }


}
