package algorithms;

import java.io.File;
import java.util.List;

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
    public List<Grain> diff(File fileVersionOne, File fileVersionTwo, Grain grain) throws DiffException;
}
