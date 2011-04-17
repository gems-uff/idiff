package diff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class LineGrain extends Grain {

    public LineGrain() {
        super(LevelGranularity.LINE);
    }

    /**
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public List<Grain> start(File file) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();

        finalList.add(null);
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            finalList.add(new Grain(LevelGranularity.LINE, line));
        }
        return finalList;
    }
}
