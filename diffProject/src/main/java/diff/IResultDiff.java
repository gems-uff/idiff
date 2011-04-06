package diff;

import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public interface IResultDiff {

    /**
     *
     * @return
     */
    public List<Grain> getResult();

    /**
     *
     * @return
     */
    public List<Grain> getFileVersionOne();

    /**
     *
     * @return
     */
    public List<Grain> getFileVersionTwo();

    /**
     *
     * @param arrayFile
     */
    public void setFileVersionOne(List<Grain> arrayFile);

    /**
     *
     * @param arrayFile
     */
    public void setFileVersionTwo(List<Grain> arrayFile);

    /**
     *
     * @return
     */
    public List<Grain> getLcs();
}
