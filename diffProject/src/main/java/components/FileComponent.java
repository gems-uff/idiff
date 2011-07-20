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

    private void submitFile(File file, JEditorPane editorPane) throws MalformedURLException, IOException {
        final URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
    }

    public void repaintFiles(String basefile, String comparefile, JEditorPane baseFileEditorPane, JEditorPane comparedFileEditorPane, JScrollPane baseScrollPane, JScrollPane comparedScrollPane) throws FileNotFoundException, IOException {
        printLines(basefile, baseFileEditorPane, comparedFileEditorPane, baseScrollPane);
        adjustmentPaneEvent(baseScrollPane, comparedScrollPane);
        printLines(comparefile, comparedFileEditorPane, baseFileEditorPane, comparedScrollPane);
        adjustmentPaneEvent(comparedScrollPane, baseScrollPane);
    }

    private void printLines(String file, JEditorPane editorPaneLeft, final JEditorPane editorPaneRight, final JScrollPane paneRight) throws IOException, FileNotFoundException {
        BufferedReader buffReader = new BufferedReader(new FileReader(file));
        String text;
        while ((text = buffReader.readLine()) != null) {
            JLabel line = eventPanelLeft(text, editorPaneRight, paneRight);
            editorPaneLeft.add(line);
        }
        editorPaneRight.setLayout(new BoxLayout(editorPaneRight, BoxLayout.PAGE_AXIS));
    }

    //TODO Alterar - versão para testes
    private void adjustmentPaneEvent(JScrollPane paneLeft, final JScrollPane paneRight) {
        paneLeft.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int newPosition = e.getValue() * 2;
                int startPosition = paneRight.getVerticalScrollBar().getValue();
                int finalPosition = (int) (paneRight.getSize().getHeight() + startPosition);

                if (newPosition > finalPosition || newPosition < startPosition) {
                    paneRight.getVerticalScrollBar().setValue(newPosition);
                }
            }
        });
    }

    private JLabel eventPanelLeft(String text, final JEditorPane editorPaneRight, final JScrollPane paneRight) {
        JLabel line = new JLabel(text);
        line.setBackground(Color.WHITE);
        line.setOpaque(true);
        line.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.RED);
                label.setBackground(Color.YELLOW);

                JLabel relativeLabel = (JLabel) editorPaneRight.getComponent(2);

                relativeLabel.setForeground(Color.GREEN);
                relativeLabel.setBackground(Color.YELLOW);

                paneRight.getVerticalScrollBar().setValue(relativeLabel.getY());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.BLACK);
                label.setBackground(Color.WHITE);

                editorPaneRight.getComponent(2).setForeground(Color.BLACK);
                editorPaneRight.getComponent(2).setBackground(Color.WHITE);

            }

            @Override
            public void mouseClicked(MouseEvent me) {
                JLabel label = (JLabel) me.getComponent();
                label.setForeground(Color.RED);
                JLabel relativeLabel = (JLabel) editorPaneRight.getComponent(2);
                relativeLabel.setForeground(Color.GREEN);
                paneRight.getVerticalScrollBar().setValue(relativeLabel.getY());
            }
        });
        return line;
    }
}