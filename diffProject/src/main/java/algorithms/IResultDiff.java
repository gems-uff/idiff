package algorithms;

import java.util.List;

/**
 * IResultDiff
 * @author Fernanda Floriano Silva
 */
public interface IResultDiff {

    /**
     * Get Grains From
     * @return List<Grain>
     */
    List<Grain> getGrainsFrom();

    /**
     * Get Grains To
     * @return List<Grain>
     */
    List<Grain> getGrainsTo();
}
