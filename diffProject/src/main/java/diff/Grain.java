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
    private int idGrain;

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
     * Constructor
     * @param grain
     */
    public Grain(String grain) {
        this.grain = grain;
    }

    public Grain(int idGrain, String nameGrain) {
        this.idGrain = idGrain;
        System.out.println("Diff Algorithm starting at the granularity " + nameGrain);
    }

    /**
     * Constructor
     * @param grain
     * @param situation
     */
    public Grain(String grain, Situation situation) {
        this.grain = grain;
        this.situation = situation;
    }

    public Grain(int idGrain) {
        this.idGrain = idGrain;
    }

    /**
     * Verifies the equality
     * @param o
     * @return
     */
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return ((this.grain.compareTo(((Grain) o).grain)) == 0);
    }

    /**
     * Get grain
     * @return
     */
    public String getGrain() {
        return grain;
    }

    /**
     * Set Grain
     * @param grain
     */
    public void setGrain(String grain) {
        this.grain = grain;
    }

    /**
     * Get Situation
     * @return
     */
    public Situation getSituation() {
        return situation;
    }

    /**
     * Set Situation
     * @param situation
     */
    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    /**
     * Get file version one grain's index
     * @return
     */
    public int getIndexGrainFileV1() {
        return indexGrainFileV1;
    }

    /**
     * Set file version one grain's index
     * @param indexGrainFileV1
     */
    public void setIndexGrainFileV1(int indexGrainFileV1) {
        this.indexGrainFileV1 = indexGrainFileV1;
    }

    /**
     * Get file version two grain's index
     * @return
     */
    public int getIndexGrainsFileV1() {
        return indexGrainFileV2;
    }

    /**
     * Set file version two grain's index
     * @param indexGrainFileV2
     */
    public void setIndexGrainFileV2(int indexGrainFileV1) {
        this.indexGrainFileV2 = indexGrainFileV1;
    }

    public int getIdGrain() {
        return idGrain;
    }

    public void setIdGrain(int idGrain) {
        this.idGrain = idGrain;
    }

    public void reduceGranularity(Grain grain) {
        setIdGrain(grain.getIdGrain() - 1);

    }

    public boolean canReduceGranularity(Grain grain) {
        if (grain.idGrain <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
