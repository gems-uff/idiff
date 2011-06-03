package algorithms;

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

    public static Result getResult() {
        if (resultInstance == null) {
            resultInstance = new Result();
        }
        return resultInstance;
    }

    @Override
    public void cleanResult() {
    resultInstance  = null;
}
/**
 * Get Grains From
 * @return List<Grain>
 */
@Override
        public List<Grain> getGrainsFrom() {
        return grainsFrom;
    }

    /**
     * Set Grains From
     * @param grainFrom
     */
    public void setGrainsFrom(Grain grainFrom) {
        this.grainsFrom.add(grainFrom);
    }

    /**
     * Get Grains To
     * @return List<Grain>
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

    @Override
        public List<Grain> getDifferences() {
        return differences;
    }

    public void setDifferences(List<Grain> differences) {
        this.differences = differences;
    }
}
