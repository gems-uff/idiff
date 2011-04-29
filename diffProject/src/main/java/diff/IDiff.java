package diff;

import java.io.File;

/**
 * IDiff
 * @author Fernanda Floriano Silva
 */
public interface IDiff {

    /**
     * Diff
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param grain
     * @throws DiffException
     */
    public void diff(File fileVersionOne, File fileVersionTwo, Grain grain) throws DiffException;
}
