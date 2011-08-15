package components;

import algorithms.ILCSBean;
import algorithms.IResultDiff;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
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
        final URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
    }

    public void repaint(ILCSBean ilcsBean, IResultDiff result, JEditorPane baseFileEditorPane, JScrollPane baseFileScrollPane, JEditorPane comparedFileEditorPane, JScrollPane comparedFileScrollPane) {
        granularityComponent.setDifferenceColor(ilcsBean, result, baseFileEditorPane, comparedFileEditorPane);
        granularityComponent.setMoveColor(ilcsBean, result, baseFileEditorPane, baseFileScrollPane, comparedFileEditorPane, comparedFileScrollPane);
    }
}