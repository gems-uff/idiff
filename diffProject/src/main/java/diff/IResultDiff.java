package diff;

import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public interface IResultDiff {

    public List<Grain> getResult();

    public List<Grain> getFileVersionOne();

    public List<Grain> getFileVersionTwo();

    public void setFileVersionOne(List<Grain> arrayFile);

    public void setFileVersionTwo(List<Grain> arrayFile);

    public List<Grain> getLcs();

}
