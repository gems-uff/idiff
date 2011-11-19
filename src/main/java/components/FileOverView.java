package components;

import algorithms.IResultDiff;
import java.io.File;
import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class FileOverView {

    private File file;
    private List<IResultDiff> resultILCS;
    private String percentageSimilarity;

    public List<IResultDiff> getResultILCS() {
        return resultILCS;
    }

    public void setResultILCS(List<IResultDiff> resultILCS) {
        this.resultILCS = resultILCS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPercentageSimilarity() {
        return percentageSimilarity;
    }

    public void setPercentageSimilarity(String percentageSimilarity) {
        this.percentageSimilarity = percentageSimilarity;
    }
}
