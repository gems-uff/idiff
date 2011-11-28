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
     * @param fileFrom
     * @param fileTo
     * @param paneFrom
     * @param paneTo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void showFiles(File fileFrom, File fileTo, JTextPane paneFrom, JTextPane paneTo) throws FileNotFoundException, IOException {
        submitFile(fileFrom, paneFrom);
        submitFile(fileTo, paneTo);
    }

    /**
     * Submit File
     * @param file
     * @param editorPane
     * @return boolean
     * @throws MalformedURLException
     * @throws IOException 
     */
    public boolean submitFile(File file, JTextPane editorPane) throws MalformedURLException, IOException {
        java.net.URL transferURL = file.toURI().toURL();
        editorPane.setPage(transferURL);
        return true;
    }

    /**
     * Repaint
     * @param result
     * @param paneFrom
     * @param scrollFrom
     * @param paneTo
     * @param scrollTo
     * @param ilcsb  
     */
    public void repaint(IResultDiff result, JTextPane paneFrom, JScrollPane scrollFrom, JTextPane paneTo, JScrollPane scrollTo, ILCSBean ilcsb) {
        granularityComponent.setMoves(result, paneFrom, paneTo, ilcsb.getPerspective(), scrollFrom, scrollTo);
        if (ilcsb.getPerspective() == 1) {
            granularityComponent.setDifferences(result, paneFrom, paneTo, "RemoveStyle", "AddStyle");

        } else {
            granularityComponent.setDifferences(result, paneFrom, paneTo, "DisabledStyle", "DisabledStyle");

        }
    }

    public void clear(JTextPane paneFrom, JTextPane paneTo) {
        granularityComponent.clean(paneFrom, paneTo);
    }
}
