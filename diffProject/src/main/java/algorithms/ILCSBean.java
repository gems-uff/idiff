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
    private boolean showGUIDifferences;
    private boolean showGUIMoves;

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
     * isShowGUIDifferences
     * @return boolean
     */
    public boolean isShowGUIDifferences() {
        return showGUIDifferences;
    }

    /**
     * setShowGUIDifferences
     * @param showGUIDifferences 
     */
    public void setShowGUIDifferences(boolean showGUIDifferences) {
        this.showGUIDifferences = showGUIDifferences;
    }

    /**
     * isShowGUIMoves
     * @return boolean
     */
    public boolean isShowGUIMoves() {
        return showGUIMoves;
    }

    /**
     * Set Show GUI Moves
     * @param showGUIMoves 
     */
    public void setShowGUIMoves(boolean showGUIMoves) {
        this.showGUIMoves = showGUIMoves;
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
}
