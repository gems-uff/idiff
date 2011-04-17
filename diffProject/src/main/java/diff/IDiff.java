package diff;

import java.io.File;

/**
 * IDiff
 *@author Fernanda Floriano Silva
 */
public interface IDiff {

    /**
     *
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param grain
     * @return
     * @throws DiffException
     */
    public IResultDiff diff(File fileVersionOne, File fileVersionTwo, Grain grain) throws DiffException;
}
