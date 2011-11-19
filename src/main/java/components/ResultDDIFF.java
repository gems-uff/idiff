package components;

import java.io.File;
import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class ResultDDIFF {

    private File file;
    private List<FileOverView> fileOverView;

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fileOverView
     */
    public List<FileOverView> getFileOverView() {
        return fileOverView;
    }

    /**
     * @param fileOverView the fileOverView to set
     */
    public void setFileOverView(List<FileOverView> fileOverView) {
        this.fileOverView = fileOverView;
    }
}
