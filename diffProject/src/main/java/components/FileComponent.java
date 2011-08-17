package components;

import algorithms.ILCSBean;
import algorithms.IResultDiff;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * FileComponent
 * @author Fernanda Floriano Silva
 */
public class FileComponent {

    private GranularityComponent granularityComponent = new GranularityComponent();

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
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        int id = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            char[] letras = line.toCharArray();
            for (int i = 0; i < letras.length; i++) {
                JLabel label = new JLabel(line);
                label.setBackground(ColorLine.getUnchangedColor());
                label.setOpaque(true);
                editorPane.add("" + id + i, label);
            }
            line = line + letras.length;
        }
    }

    public void repaint(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, JEditorPane comparedFileEditorPane, JScrollPane comparedFileScrollPane) {
        granularityComponent.setDifferencedGranularity(result, baseFileEditorPane, comparedFileEditorPane);
        granularityComponent.setMovedGranularity(ilcsBean, result, baseFileEditorPane, baseFileScrollPane, comparedFileEditorPane, comparedFileScrollPane);
    }
}