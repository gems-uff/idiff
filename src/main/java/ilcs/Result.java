package ilcs;

import ilcs.result.IResultDiff;
import ilcs.grain.Grain;
import java.util.ArrayList;
import java.util.List;

/**
 * Result
 * @author Fernanda Floriano Silvan
 */
public class Result implements IResultDiff {

    private List<Grain> grainsFrom = new ArrayList<Grain>();
    private List<Grain> grainsTo = new ArrayList<Grain>();
    private List<Grain> differences = new ArrayList<Grain>();
    private static Result resultInstance;

    /**
     * Constructor
     * @return Result
     */
    public static Result getResult() {
        if (resultInstance == null) {
            resultInstance = new Result();
        }
        return resultInstance;
    }

    /**
     * Clean Result
     */
    @Override
    public void cleanResult() {
        resultInstance = null;
    }

    /**
     * Get references from the source file
     * @return List<Grain>
     */
    @Override
    public List<Grain> getGrainsFrom() {
        return grainsFrom;
    }

    /**
     * Get reference of the target file
     * @param grainFrom 
     */
    public void setGrainsFrom(Grain grainFrom) {
        this.grainsFrom.add(grainFrom);
    }

    /**
     * Get Grains To
     * @return 
     */
    @Override
    public List<Grain> getGrainsTo() {
        return grainsTo;
    }

    /**
     * Set Grains To
     * @param grainTo 
     */
    public void setGrainsTo(Grain grainTo) {
        this.grainsTo.add(grainTo);
    }

    /**
     * Get list with Differences
     * @return List<Grain
     */
    @Override
    public List<Grain> getDifferences() {
        return differences;
    }

    /**
     * Set Differences
     * @param differences 
     */
    public void setDifferences(List<Grain> differences) {
        this.differences = differences;
    }

    public static void clean() {
        resultInstance = null;
    }
}
