package components;

import algorithms.IResultDiff;
import java.io.File;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class FileOverView {

    private File file;
    private IResultDiff resultILCS;
    private int percentageSimilarity;

    public IResultDiff getResultILCS() {
        return resultILCS;
    }

    public void setResultILCS(IResultDiff resultILCS) {
        this.resultILCS = resultILCS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getPercentageSimilarity() {
        return percentageSimilarity;
    }

    public void setPercentageSimilarity(int percentageSimilarity) {
        this.percentageSimilarity = percentageSimilarity;
    }
}
