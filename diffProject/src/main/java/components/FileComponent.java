package components;

import algorithms.ILCSBean;
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

    public void repaint(ILCSBean ilcsBean, IResultDiff result, JTextPane basePane, JScrollPane baseScroll, JTextPane comparedPane, JScrollPane comparedScroll) {
        granularityComponent.setDifferences(result, basePane, comparedPane);
        granularityComponent.setMoves(result, basePane, baseScroll, comparedPane, comparedScroll);
    }
}