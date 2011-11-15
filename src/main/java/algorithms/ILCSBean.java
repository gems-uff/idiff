package algorithms;

import java.io.File;

/**
 * ILCSBean
 * @author Fernanda Floriano Silva
 */
public class ILCSBean {

    private File fileFrom;
    private File fileTo;
    private String granularity;
    private String tags;
    private int perspective; // Default - ILCS

    /**
     * Constructor
     * @param fileFrom
     * @param fileTo 
     */
    public ILCSBean(File fileFrom, File fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    /**
     * Constructor
     */
    public ILCSBean() {
    }

    /**
     * Get Granularity
     * @return String
     */
    public String getGranularity() {
        return granularity;
    }

    /**
     * Set Granularity
     * @param granularity 
     */
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    /**
     * Get File From 
     * @return File
     */
    public File getFileFrom() {
        return fileFrom;
    }

    /**
     * Set File From 
     * @param fileFrom 
     */
    public void setFileFrom(File fileFrom) {
        this.fileFrom = fileFrom;
    }

    /**
     * Get File To
     * @return File
     */
    public File getFileTo() {
        return fileTo;
    }

    /**
     * Set File To
     * @param fileTo 
     */
    public void setFileTo(File fileTo) {
        this.fileTo = fileTo;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public int getPerspective() {
        return perspective;
    }

    public void setPerspective(int perspective) {
        this.perspective = perspective;
    }
}
