package ilcs.grain;

import ilcs.algorithms.Algorithm;
import ilcs.DiffException;
import ilcs.grain.GrainBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CharacterGrain
 * @author Fernanda Floriano Silva
 */
public class CharacterGrain extends Grain {

    /**
     * Constructor
     * @param character
     * @param idReference
     * @param grainBean  
     */
    public CharacterGrain(String character, int idReference, GrainBean grainBean) {
        super(LevelGranularity.CHARACTER, character, idReference, grainBean);
    }

    /**
     * Constructor
     */
    public CharacterGrain() {
        super(LevelGranularity.CHARACTER);
    }

    /**
     * Start Character Grain
     * @param list
     * @return List<Grain>
     * @throws IOException
     */
    public List<Grain> start(List<Grain> list) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);
        for (Iterator<Grain> it = list.iterator(); it.hasNext();) {
            Grain grain = it.next();

            if (grain != null) {
                char[] letras = grain.getGrain().toCharArray();
                int startPosition = grain.getGrainBean().getStartPosition();

                for (int i = 0; i < letras.length; i++) {
                    CharacterGrain charGrain = setData(letras, i, grain, startPosition);
                    finalList.add(charGrain);
                    startPosition++;
                }
            }
        }
        return finalList;
    }

    /**
     * Set Character Data
     * @param letras
     * @param i
     * @param grain
     * @param startPosition
     * @return CharacterGrain
     */
    private CharacterGrain setData(char[] letras, int i, Grain grain, int startPosition) {
        GrainBean grainBean = new GrainBean(startPosition, 1);

        CharacterGrain charGrain = new CharacterGrain(Character.toString(letras[i]), grain.getOriginalReference().get(0), grainBean);
        charGrain.setOriginalReference(grain.getOriginalReference().get(1));
        charGrain.setOriginalReference(i + 1);
        charGrain.setIdFile(grain.getIdFile());
        return charGrain;
    }

    /**
     * Start Character Granularity
     * @param listFileFrom
     * @param listFileTo
     * @throws DiffException
     */
    public void startCharacterGranularity(List<Grain> listFileFrom, List<Grain> listFileTo) throws DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(listFileFrom));
            Algorithm.getComparator().setColumnFileTwo(this.start(listFileTo));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_CHARACTER_GRANULARITY);
        }
    }
}
