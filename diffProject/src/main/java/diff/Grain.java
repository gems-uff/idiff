package diff;

/**
 * Grain
 * @author Fernanda Floriano Silva
 */
public class Grain {

    private String grainText;
    private Situation situation = Situation.UNCHANGED;
    private int idGrainFile1 = -1;
    private int idGrainFile2 = -1;
    private LevelGranularity levelGrain;

    /**
     * Possible situation of grains
     */
    public enum Situation {

        UNCHANGED,
        CHANGED,
        ADDED,
        REMOVED
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
     * @param levelGrain
     * @param grainText
     */
    public Grain(LevelGranularity levelGrain, String grain) {
        this.levelGrain = levelGrain;
        this.grainText = grain;
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
        return ((this.grainText.compareTo(((Grain) o).grainText)) == 0);
    }

    /**
     *
     * @return
     */
    public String getGrain() {
        return grainText;
    }

    /**
     *
     * @param grainText
     */
    public void setGrain(String grainText) {
        this.grainText = grainText;
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
    public int getIdGrainFile1() {
        return idGrainFile1;
    }

    /**
     *
     * @param idGrainFile1
     */
    public void setIdGrainFile1(int idGrainFile1) {
        this.idGrainFile1 = idGrainFile1;
    }

    /**
     *
     * @return
     */
    public int getIdGrainsFile1() {
        return idGrainFile2;
    }

    /**
     *
     * @param idGrainFile1
     */
    public void setIdGrainFile2(int idGrainFile2) {
        this.idGrainFile2 = idGrainFile2;
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
        return (!this.levelGrain.equals(LevelGranularity.CHARACTER));
    }
}