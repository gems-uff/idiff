package diff;

import diff.Grain.Situation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ResultLCS
 * @author Fernanda Floriano Silva
 */
public class ResultLCS implements IResultDiff {

    private List<Grain> arrayFileVersionOne;
    private List<Grain> arrayFileVersionTwo;
    private List<Grain> lcs;
    private List<Grain> result;

    /**
     * 
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
     *
     * @throws DiffException
     */
    private void loadResult() throws DiffException {
        result = new ArrayList();
        int i = 0;
        int j = 0;

        Iterator itF1 = arrayFileVersionOne.iterator();
        Iterator itF2 = arrayFileVersionTwo.iterator();

        Grain grainF1 = getNext(itF1);
        Grain grainF2 = getNext(itF2);

        while ((grainF1 != null) || (grainF2 != null)) {
            if (isContext(grainF1)) {
                //Context
                setGrainsBean(grainF1, i++, Grain.Situation.UNCHANGED, 1);
                grainF1 = getNext(itF1);
                //Context
            } else if (isContext(grainF2)) {
                setGrainsBean(grainF2, j++, Grain.Situation.UNCHANGED, 2);
                grainF2 = getNext(itF2);

            } else if (isRemoving(grainF1)) {
                //Removed
                setGrainsBean(grainF1, i++, Grain.Situation.REMOVED, 1);
                grainF1 = getNext(itF1);
            } else if (isAdding(grainF2)) {
                //Addition
                setGrainsBean(grainF2, j++, Grain.Situation.ADDED, 2);
                grainF2 = getNext(itF2);
            } else {
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
            }
        }
    }

    /**
     *
     * @param grainF1
     * @param grainF2
     * @return
     */
    /*   private boolean isContext(Grain grainF1, Grain grainF2) {
    if ((grainF1 == null) || (grainF2 == null)) {
    return false;
    }
    return (lcs.contains(grainF1)) && (lcs.contains(grainF2));
    }*/
    private boolean isContext(Grain grain) {
        if (grain == null) {
            return false;
        }
        return (lcs.contains(grain));
    }

    /**
     *
     * @param grainF2
     * @return
     */
    private boolean isAdding(Grain grainF2) {
        if ((grainF2 == null)) {
            return false;
        }
        return !lcs.contains(grainF2);
    }

    /**
     *
     * @param grainF1
     * @return
     */
    private boolean isRemoving(Grain grainF1) {
        if ((grainF1 == null)) {
            return false;
        }
        return !lcs.contains(grainF1);
    }

    /**
     *
     * @param grain
     * @param id
     * @param situation
     * @param idFile
     */
    public void setGrainsBean(Grain grain, int id, Situation situation, int idFile) {
        grain.setSituation(situation);
        if (idFile == 1) {
            grain.setIndexGrainFileV1(id);
        } else {
            grain.setIndexGrainFileV2(id);
        }
        addGrain(grain);
    }

    /**
     *
     * @param grain
     */
    private void addGrain(Grain grain) {
        result.add(grain);
    }

    /**
     *
     * @return
     */
    public List<Grain> getFileVersionOne() {
        return arrayFileVersionOne;
    }

    /**
     *
     * @param arrayFileVersionOne
     */
    public void setFileVersionOne(List<Grain> arrayFileVersionOne) {
        this.arrayFileVersionOne = arrayFileVersionOne;
    }

    /**
     *
     * @return
     */
    public List<Grain> getFileVersionTwo() {
        return arrayFileVersionTwo;
    }

    /**
     *
     * @param arrayFileVersionTwo
     */
    public void setFileVersionTwo(List<Grain> arrayFileVersionTwo) {
        this.arrayFileVersionTwo = arrayFileVersionTwo;
    }

    /**
     *
     * @return
     */
    public List<Grain> getLcs() {
        return lcs;
    }

    /**
     *
     * @param lcs
     */
    public void setLcs(List<Grain> lcs) {
        this.lcs = lcs;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Grain> getResult() {
        return result;
    }

    /**
     *
     * @param it
     * @return
     */
    public Grain getNext(Iterator it) {
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
