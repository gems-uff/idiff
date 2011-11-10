package algorithms;

import java.io.File;
import java.util.List;

/**
 * IDiff
 * @author Fernanda Floriano Silva
 */
public interface IDiff {

    /**
     * Constructor
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param grain
     * @param iLCSBean
     * @return List<Grain>
     * @throws DiffException 
     */
    public List<Grain> idiff(File fileVersionOne, File fileVersionTwo, Grain grain, ILCSBean iLCSBean) throws DiffException;
}
