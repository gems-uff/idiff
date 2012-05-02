package ilcs.result;

import ilcs.DiffException;
import ilcs.grain.Grain;
import java.util.Iterator;
import java.util.List;

/**
 * ResultLCS
 * @author Fernanda Floriano Silva
 */
public class ResultLCS implements IResultLCS {

    private List<Grain> arrayFileVersionOne;
    private List<Grain> arrayFileVersionTwo;
    private List<Grain> lcs;

    /**
     * Constructor
     * @param arrayFileVersionOne
     * @param arrayFileVersionTwo
     * @param lcs
     * @throws DiffException 
     */
    public ResultLCS(List<Grain> arrayFileVersionOne, List<Grain> arrayFileVersionTwo, List<Grain> lcs) throws DiffException {
        this.arrayFileVersionOne = arrayFileVersionOne;
        this.arrayFileVersionTwo = arrayFileVersionTwo;
        this.lcs = lcs;

        loadResult();
    }

    /**
     * Verify if is same start position
     * @param grain
     * @param lcsList
     * @param i
     * @return 
     */
    private boolean isSameStartPosition(Grain grain, List<Grain> lcsList, int i) {
        if (grain.getGrainBean().getStartPosition() == lcsList.get(i).getGrainBean().getStartPosition()) {
            if (grain.getIdFile() == lcsList.get(i).getIdFile()) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Load ilcs results
     * @throws DiffException
     */
    private void loadResult() throws DiffException {
        Iterator<Grain> itF1 = this.arrayFileVersionOne.iterator();
        Iterator<Grain> itF2 = this.arrayFileVersionTwo.iterator();

        Grain grainF1 = getNext(itF1);
        Grain grainF2 = getNext(itF2);

        while ((grainF1 != null) || (grainF2 != null)) {
            if (isContext(grainF1)) {
                //Context
                grainF1.setSituation(Grain.Situation.UNCHANGED);
                grainF1 = getNext(itF1);
                //Context
            } else if (isContext(grainF2)) {
                grainF2.setSituation(Grain.Situation.UNCHANGED);
                grainF2 = getNext(itF2);
            } else if (isRemoving(grainF1)) {
                //Removed
                grainF1.setSituation(Grain.Situation.REMOVED);
                grainF1 = getNext(itF1);
            } else if (isAdding(grainF2)) {
                //Addition
                grainF2.setSituation(Grain.Situation.ADDED);
                grainF2 = getNext(itF2);
            } else {
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
            }
        }
    }

    /* Verify if is Context
     * @param grain
     * @return boolean
     */
    private boolean isContext(Grain grain) {
        if (grain == null) {
            return false;
        }
        return (lcsContains(grain));
    }

    /**
     * Verify if lcs contais a grain
     * @param grain
     * @return boolean
     */
    private boolean lcsContains(Grain grain) {
        if (this.lcs.contains(grain)) {
            return comparePosition(this.lcs, grain);
        }
        return false;
    }

    /**
     * Compare Position
     * @param lcsList
     * @param grain
     * @return 
     */
    private boolean comparePosition(List<Grain> lcsList, Grain grain) {
        for (int i = 0; i < lcsList.size(); i++) {
            if (isSameStartPosition(grain, lcsList, i)) { //|| isSameReference(grain, lcsList, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if is Adding
     * @param grain
     * @return boolean
     */
    private boolean isAdding(Grain grain) {
        if ((grain == null)) {
            return false;
        }
        return (!lcsContains(grain));
    }

    /**
     * Verify if is Removing
     * @param grain
     * @return boolean
     */
    private boolean isRemoving(Grain grain) {
        if ((grain == null)) {
            return false;
        }
        return (!lcsContains(grain));
    }

    /**
     * Get List with File Version One
     * @return List<Grain>
     */
    @Override
    public List<Grain> getFileVersionOne() {
        return this.arrayFileVersionOne;
    }

    /**
     * Get List with File Version Two
     * @return List<Grain>
     */
    @Override
    public List<Grain> getFileVersionTwo() {
        return this.arrayFileVersionTwo;
    }

    /**
     * Get List with LCS
     * @return List<Grain>
     */
    @Override
    public List<Grain> getLcs() {
        return this.lcs;
    }

    /**
     * Get Next
     * @param it
     * @return Grain
     */
    @Override
    public Grain getNext(Iterator<Grain> it) {
        Object obj = null;
        if (it.hasNext()) {
            obj = it.next();
            if (obj == null) {
                return getNext(it);
            }
        }
        return (Grain) obj;
    }
}