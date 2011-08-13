package components;

import algorithms.Grain;
import algorithms.ILCSBean;
import algorithms.IResultDiff;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
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
 * FileComponent
 * @author Fernanda Floriano Silva
 */
public class FileComponent {

    /**
     * Constructor
     */
    public FileComponent() {
    }

    /**
     * Show Files
     * @param baseFile
     * @param comparedFile
     * @param baseEditorPane
     * @param comparedEditorPane
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void showFiles(File baseFile, File comparedFile, JEditorPane baseEditorPane, JEditorPane comparedEditorPane) throws FileNotFoundException, IOException {
        submitFile(baseFile, baseEditorPane);
        submitFile(comparedFile, comparedEditorPane);
    }

    private void submitFile(File file, JEditorPane editorPane) throws MalformedURLException, IOException {
        final URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
    }

    public void repaint(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, JEditorPane comparedFileEditorPane, JScrollPane comparedFileScrollPane) {
        setDifferenceColor(ilcsBean, result, baseFileEditorPane, comparedFileEditorPane);
        setMoveColor(ilcsBean, result, baseFileEditorPane, baseFileScrollPane, comparedFileEditorPane, comparedFileScrollPane);
    }

    private void setDifferenceColor(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JEditorPane comparedFileEditorPane) {
        for (Iterator<Grain> it = result.getDifferences().iterator(); it.hasNext();) {
            Grain grain = it.next();
            if (grain != null) {
                if ((grain.getSituation()).equals(Grain.Situation.REMOVED)) {
                    setRemovedLine(new JLabel("teste"));
                } else {
                    setAddedLine(new JLabel("teste"));
                }
            }
        }
    }

    private void setMoveColor(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, JEditorPane comparedFileEditorPane, JScrollPane comparedFileScrollPane) {
        Iterator<Grain> it1 = result.getGrainsFrom().iterator();
        Iterator<Grain> it2 = result.getGrainsTo().iterator();
        while (it1.hasNext() || it2.hasNext()) {
            Grain grain1 = it1.next();
            Grain grain2 = it2.next();
            if ((grain1 != null) || (grain2 != null)) {
                if (!grain1.getOriginalReference().equals(grain2.getOriginalReference())) {
                    setMovedLines(new JLabel("teste"));
                } else {
                    setUnchangedLines(new JLabel("teste"));
                }
            }
        }
    }

    private JLabel setRemovedLine(JLabel label) {
        System.out.println("teste");
        return new JLabel("teste");
    }

    private JLabel setAddedLine(JLabel labe) {
        System.out.println("teste");
        return new JLabel("teste");
    }

    private JLabel setMovedLines(JLabel labe) {
        System.out.println("teste");
        return new JLabel("teste");
    }

    private JLabel setUnchangedLines(JLabel labe) {
        System.out.println("teste");
        return new JLabel("teste");
    }

    private void addEvent(String textLeft, String textRight, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, final JEditorPane comparedFileEditorPane, final JScrollPane comparedFileScrollPane) {
        baseFileEditorPane.setLayout(new BoxLayout(baseFileEditorPane, BoxLayout.PAGE_AXIS));
        addMouseEvent(textLeft, comparedFileEditorPane, comparedFileScrollPane, baseFileEditorPane);
        comparedFileEditorPane.setLayout(new BoxLayout(comparedFileEditorPane, BoxLayout.PAGE_AXIS));
        setEditorPane(textRight, comparedFileEditorPane);
        addAdjustmentEvent(baseFileScrollPane, comparedFileScrollPane);
    }

    private void addMouseEvent(String textLeft, final JEditorPane comparedFileEditorPane, final JScrollPane comparedFileScrollPane, JEditorPane baseFileEditorPane) {
        JLabel linha = new JLabel(textLeft);
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

    private void setEditorPane(String textRight, final JEditorPane comparedFileEditorPane) {
        JLabel linha = new JLabel(textRight);
        linha.setBackground(Color.WHITE);
        linha.setOpaque(true);
        comparedFileEditorPane.add(linha);
    }
}