package diff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class WordGrain extends Grain {

    public WordGrain() {
        super(LevelGranularity.WORD);
    }

    /**
     *
     * @param list
     * @return
     * @throws IOException
     */
    public List<Grain> start(List<Grain> list) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);

        for (Iterator it = list.iterator(); it.hasNext();) {
            Grain grain = (Grain) it.next();
            if (grain != null) {
                StringTokenizer token = new StringTokenizer(grain.getGrain());
                while (token.hasMoreTokens()) {
                    finalList.add(new Grain(LevelGranularity.WORD, token.nextToken()));
                }
            }

        }
        return finalList;
    }
}
