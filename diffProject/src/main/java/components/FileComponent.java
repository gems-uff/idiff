package components;

import algorithms.IResultDiff;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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
     * @param basePane
     * @param comparedPane
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void showFiles(File baseFile, File comparedFile, JTextPane basePane, JTextPane comparedPane) throws FileNotFoundException, IOException {
        submitFile(baseFile, basePane);
        submitFile(comparedFile, comparedPane);
    }

    private boolean submitFile(File file, JTextPane editorPane) throws MalformedURLException, IOException {
        java.net.URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
        return true;
    }

    void repaint(IResultDiff result, JTextPane leftPane, JScrollPane leftScrollPane, JTextPane rightPane, JScrollPane rightScrollPane) {
        granularityComponent.setDifferences(result, leftPane, rightPane);
        granularityComponent.setMoves(result, leftPane, leftScrollPane, rightPane, rightScrollPane);
        // granularityComponent.setTest(result, leftPane, leftScrollPane, rightPane, rightScrollPane);

    }
}
