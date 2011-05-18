package algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Grain
 * @author Fernanda Floriano Silva
 */
public class Grain {

    private String grainText;
    private List<Integer> originalReference = new ArrayList<Integer>();
    private Situation situation = Situation.UNCHANGED;
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
     * Level Granularity
     */
    public enum LevelGranularity {

        FILE,
        LINE,
        WORD,
        CHARACTER
    }

    /**
     * Constructor
     * @param levelGrain
     * @param grain
     * @param originalReference
     */
    public Grain(LevelGranularity levelGrain, String grain, int originalReference) {
        this.levelGrain = levelGrain;
        this.grainText = grain;
        this.originalReference.add(originalReference);
    }

    /**
     * Constructor
     * @param levelGrain
     */
    public Grain(LevelGranularity levelGrain) {
        this.levelGrain = levelGrain;
    }

    /**
     * Equals
     * @param o
     * @return
     */
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return ((this.grainText.compareTo(((Grain) o).grainText)) == 0);
    }

    /**
     * Get Grain
     * @return String
     */
    public String getGrain() {
        return this.grainText;
    }

    /**
     * Set Grain
     * @param grainText
     */
    public void setGrain(String grainText) {
        this.grainText = grainText;
    }

    /**
     * Get Situation
     * @return Situation
     */
    public Situation getSituation() {
        return this.situation;
    }

    /**
     * Set Situation
     * @param situation
     */
    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    /**
     * Get Level Grain
     * @return LevelGranularity
     */
    public LevelGranularity getLevelGrain() {
        return this.levelGrain;
    }

    /**
     * Set Level Grain
     * @param levelGrain
     */
    public void setLevelGrain(LevelGranularity levelGrain) {
        this.levelGrain = levelGrain;
    }

    /**
     * Reduce Granularity
     * @throws DiffException
     */
    public void reduceGranularity() throws DiffException {
        switch (this.getLevelGrain()) {
            case FILE:
                setLevelGrain(LevelGranularity.LINE);
                break;
            case LINE:
                setLevelGrain(LevelGranularity.WORD);
                break;
            case WORD:
                setLevelGrain(LevelGranularity.CHARACTER);
                break;
            case CHARACTER:
                throw new DiffException(DiffException.MSG_INVALID_REDUCTION);
            default:
                throw new DiffException(DiffException.MSG_INVALID_GRAIN);
        }
    }

    /**
     * Can Reduce Granularity
     * @return boolean
     */
    public boolean canReduceGranularity() {
        return (!this.levelGrain.equals(LevelGranularity.CHARACTER));
    }

    /**
     * Init Granularity
     * @param fileVersionOne
     * @param fileVersionTwo
     * @param levelGrain
     * @param result
     * @throws DiffException
     * @throws IOException
     */
    public void init(File fileVersionOne, File fileVersionTwo, Grain.LevelGranularity levelGrain, IResultLCS result) throws DiffException, IOException {
        System.out.println();
        LCSBean[][] arrayLcs = null;
        switch (levelGrain) {
            case LINE:
                System.out.println("...Start Algorithm LCS with Line Grain...");
                new LineGrain().startLineGranularity(fileVersionOne, fileVersionTwo);
                break;
            case WORD:
                System.out.println("...Start Algorithm LCS with Word Grain...");
                new WordGrain().startWordGranularity(result.getFileVersionOne(), result.getFileVersionTwo());
                break;
            case CHARACTER:
                System.out.println("...Start Algorithm LCS with Character Grain...");
                new CharacterGrain().startCharacterGranularity(result.getFileVersionOne(), result.getFileVersionTwo());
                break;
            default:
                throw new DiffException(DiffException.MSG_INVALID_SITUATION);
        }
    }

    /**
     * Get Original Reference
     * @return List<Integer>
     */
    public List<Integer> getOriginalReference() {
        return originalReference;
    }

    /**
     * Set Original Reference
     * @param originalReference
     */
    public void setOriginalReference(int originalReference) {
        this.originalReference.add(originalReference);
    }
}
