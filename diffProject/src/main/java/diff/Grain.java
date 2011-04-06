package diff;

/**
 * Grain
 * @author Fernanda Floriano Silva
 */
public class Grain {

    private String grain;
    private Situation situation = Situation.UNCHANGED;
    private int indexGrainFileV1 = -1;
    private int indexGrainFileV2 = -1;
    private LevelGranularity levelGrain;

    /**
     * Possible situation of grains
     */
    public enum Situation {

        UNCHANGED,
        CHANGED,
        ADDED,
        REMOVED,
        REFACTORED
    }

    /**
     *
     */
    public enum LevelGranularity {

        FILE,
        LINE,
        WORD,
        CHARACTER
    }

    /**
     *
     */
    public Grain() {
    }

    /**
     *
     * @param grain
     */
    public Grain(String grain) {
        this.grain = grain;
    }

    /**
     *
     * @param levelGrain
     * @param grain
     */
    public Grain(LevelGranularity levelGrain, String grain) {
        this.levelGrain = levelGrain;
        this.grain = grain;
    }

    /**
     *
     * @param grain
     * @param situation
     */
    public Grain(String grain, Situation situation) {
        this.grain = grain;
        this.situation = situation;
    }

    /**
     *
     * @param levelGrain
     */
    public Grain(LevelGranularity levelGrain) {
        this.levelGrain = levelGrain;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return ((this.grain.compareTo(((Grain) o).grain)) == 0);
    }

    /**
     *
     * @return
     */
    public String getGrain() {
        return grain;
    }

    /**
     *
     * @param grain
     */
    public void setGrain(String grain) {
        this.grain = grain;
    }

    /**
     *
     * @return
     */
    public Situation getSituation() {
        return situation;
    }

    /**
     *
     * @param situation
     */
    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    /**
     *
     * @return
     */
    public int getIndexGrainFileV1() {
        return indexGrainFileV1;
    }

    /**
     *
     * @param indexGrainFileV1
     */
    public void setIndexGrainFileV1(int indexGrainFileV1) {
        this.indexGrainFileV1 = indexGrainFileV1;
    }

    /**
     *
     * @return
     */
    public int getIndexGrainsFileV1() {
        return indexGrainFileV2;
    }

    /**
     *
     * @param indexGrainFileV1
     */
    public void setIndexGrainFileV2(int indexGrainFileV1) {
        this.indexGrainFileV2 = indexGrainFileV1;
    }

    /**
     *
     * @return
     */
    public LevelGranularity getLevelGrain() {
        return levelGrain;
    }

    /**
     *
     * @param levelGrain
     */
    public void setLevelGrain(LevelGranularity levelGrain) {
        this.levelGrain = levelGrain;
    }

    /**
     *
     * @throws DiffException
     */
    public void reduceGranularity() throws DiffException {
        switch (this.getLevelGrain()) {
            case FILE:
                this.setLevelGrain(LevelGranularity.LINE);
                break;
            case LINE:
                this.setLevelGrain(LevelGranularity.WORD);
                break;
            case WORD:
                this.setLevelGrain(LevelGranularity.CHARACTER);
                break;
            case CHARACTER:
                throw new DiffException(DiffException.MSG_INVALID_REDUCTION);
            default:
                throw new DiffException(DiffException.MSG_INVALID_GRAIN);
        }
    }

    /**
     *
     * @return
     */
    public boolean canReduceGranularity() {
        if (this.levelGrain.equals(LevelGranularity.CHARACTER)) {
            return false;
        } else {
            return true;
        }
    }
}
