package components;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
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

    public List<FileOverView> sort(List<FileOverView> list) {
        Collections.sort(list, new Comparator<FileOverView>() {
            @Override
            public int compare(FileOverView o1, FileOverView o2) {
                FileOverView p1 = o1;
                FileOverView p2 = o2;
                return p1.getPercentageSimilarity() < p2.getPercentageSimilarity() ? -1 : (p1.getPercentageSimilarity() > p2.getPercentageSimilarity() ? +1 : 0);
            }
        });

        return list;
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
