package components;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
     * @param basedFile
     * @param comparedFile
     * @param baseFileEditorPane
     * @param comparedFileEditorPane
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void showFiles(File baseFile, File comparedFile, JEditorPane baseEditorPane, JEditorPane comparedEditorPane) throws FileNotFoundException, IOException {
        submitFile(baseFile, baseEditorPane);
        submitFile(comparedFile, comparedEditorPane);
    }

    public void repaintFiles(String basefile, String comparefile, JEditorPane baseFileEditorPane, JEditorPane comparedFileEditorPane, JScrollPane baseScrollPane, JScrollPane comparedScrollPane) throws FileNotFoundException, IOException {
        repaintFile(basefile, comparefile, baseFileEditorPane, comparedFileEditorPane, baseScrollPane, comparedScrollPane);
    }

    private JLabel eventPanelLeft(JEditorPane editorPaneLeft, String texto, final JEditorPane editorPaneRight, final JScrollPane paneRight) {
        editorPaneLeft.setLayout(new BoxLayout(editorPaneLeft, BoxLayout.PAGE_AXIS));
        JLabel linha = new JLabel(texto);
        linha.setBackground(Color.WHITE);
        linha.setOpaque(true);
        linha.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.RED);
                JLabel labelReferente = (JLabel) editorPaneRight.getComponent(2);
                labelReferente.setForeground(Color.GREEN);
                paneRight.getVerticalScrollBar().setValue(labelReferente.getY());
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.RED);
                label.setBackground(Color.YELLOW);

                JLabel labelReferente = (JLabel) editorPaneRight.getComponent(2);

                labelReferente.setForeground(Color.GREEN);
                labelReferente.setBackground(Color.YELLOW);

                paneRight.getVerticalScrollBar().setValue(labelReferente.getY());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.BLACK);
                label.setBackground(Color.WHITE);

                editorPaneRight.getComponent(2).setForeground(Color.BLACK);
                editorPaneRight.getComponent(2).setBackground(Color.WHITE);

            }
        });
        return linha;
    }

    private JLabel eventPanelRight(String texto2) {
        JLabel linha2 = new JLabel(texto2);
        linha2.setBackground(Color.WHITE);
        linha2.setOpaque(true);
        linha2.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.RED);
            }
        });
        return linha2;
    }

    /**
     * Submit FileComponent
     * @param editorPane
     * @param file
     * @return boolean
     * @throws MalformedURLException
     * @throws IOException 
     */
    private void submitFile(File file, JEditorPane editorPane) throws MalformedURLException, IOException {
        final URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
    }

    private void repaintFile(String file, String file2, JEditorPane editorPaneLeft, final JEditorPane editorPaneRight, JScrollPane paneLeft, final JScrollPane paneRight) throws MalformedURLException, IOException {
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String texto;
        while ((texto = buffReader.readLine()) != null) {
            JLabel linha = eventPanelLeft(editorPaneLeft, texto, editorPaneRight, paneRight);

            editorPaneLeft.add(linha);
        }

        editorPaneRight.setLayout(new BoxLayout(editorPaneRight, BoxLayout.PAGE_AXIS));
    
        FileReader reader2 = new FileReader(file2);
        BufferedReader buffReader2 = new BufferedReader(reader2);
        String texto2;
        while ((texto2 = buffReader2.readLine()) != null) {
            JLabel linha2 = eventPanelRight(texto2);

            editorPaneRight.add(linha2);
        }

        paneLeft.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int novaPosicao = e.getValue() * 2;
                int posicaoInicial = paneRight.getVerticalScrollBar().getValue();
                int posicaoFinal = (int) (paneRight.getSize().getHeight() + posicaoInicial);

                if (novaPosicao > posicaoFinal || novaPosicao < posicaoInicial) {
                    paneRight.getVerticalScrollBar().setValue(novaPosicao);
                }
            }
        });

    }

    public void ler(String arquivo) throws IOException {
        FileReader reader = new FileReader(arquivo);
        BufferedReader buffReader = new BufferedReader(reader);
        String linha;
        while ((linha = buffReader.readLine()) != null) {
            System.out.println(linha);
        }
        reader.close();

    }
}