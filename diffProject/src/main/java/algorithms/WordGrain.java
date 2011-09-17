package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * WordGrain
 * @author Fernanda Floriano Silva
 */
public class WordGrain extends Grain {

    /**
     * Constructor
     * @param word
     * @param idReference
     * @param grainBean 
     */
    public WordGrain(String word, int idReference, GrainBean grainBean) {
        super(LevelGranularity.WORD, word, idReference, grainBean);
    }

    /**
     * Constructor
     */
    public WordGrain() {
        super(LevelGranularity.WORD);
    }

    /**
     * Start word grain
     * @param list
     * @return List<Grain>
     * @throws IOException 
     */
    public List<Grain> start(List<Grain> list) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);
        for (Iterator it = list.iterator(); it.hasNext();) {
            Grain grain = (Grain) it.next();
            if (grain != null) {
                String[] token = grain.getGrain().split(" ");
                //       StringTokenizer token = new StringTokenizer(grain.getGrain());
                int idReference = 0;
                int startPosition = grain.getGrainBean().getStartPosition();
                for (int i = 0; i < token.length; i++) {
                    //while (token.hasMoreTokens()) {
                    idReference++;
                    WordGrain wordGrain = setData(token[i], grain, idReference, startPosition);
                    if (!wordGrain.getGrain().equals("")) {
                        finalList.add(wordGrain);
                    }
                    startPosition = setStartPosition(startPosition, wordGrain.getGrain());
                }
            }

        }
        return finalList;
    }

    /**
     * Set Granularity Start Position 
     * @param startPosition
     * @param word
     * @return 
     */
    private int setStartPosition(int startPosition, String word) {
        return (startPosition + word.length() + 1);
    }

    /**
     * Set word grain data
     * @param token
     * @param grain
     * @param idReference
     * @param startPosition
     * @return WordGrain
     */
    private WordGrain setData(String token, Grain grain, int idReference, int startPosition) {
        // String nextToken = token.nextToken();
        String nextToken = token;
        GrainBean grainBean = new GrainBean(startPosition, nextToken.length());
        WordGrain wordGrain = new WordGrain(nextToken, grain.getOriginalReference().get(0), grainBean);
        wordGrain.setOriginalReference(idReference);
        wordGrain.setIdFile(grain.getIdFile());
        return wordGrain;
    }

    /**
     * Start Word Granularity
     * @param listFileFrom
     * @param listFileTo
     * @throws DiffException 
     */
    public void startWordGranularity(List<Grain> listFileFrom, List<Grain> listFileTo) throws DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(listFileFrom));
            Algorithm.getComparator().setColumnFileTwo(this.start(listFileTo));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_WORD_GRANULARITY);
        }
    }
}
