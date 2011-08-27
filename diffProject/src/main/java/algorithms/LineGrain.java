package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LineGrain
 * @author Fernanda Floriano Silva
 */
public class LineGrain extends Grain {

    /**
     * Constructor
     * @param line
     * @param idReference
     */
    public LineGrain(String line, int idReference, int start) {
        super(LevelGranularity.LINE, line, idReference, new GrainBean(start, line.length()));
    }

    /**
     * Constructor
     */
    public LineGrain() {
        super(LevelGranularity.LINE);
    }

    /**
     * Start Line Grain
     * @param file
     * @return List<Grain>
     * @throws IOException
     */
    public List<Grain> start(File file, ILCSBean ilcsb) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int id = 0;
        int idStart =0;
        while ((line = reader.readLine()) != null) {
            if (!((ilcsb.isRemoveEmptyLine()) && (line.isEmpty()))) {
                id++;
                finalList.add(new LineGrain(ilcsb.verifyParameters(line), id, idStart));
                idStart = idStart + line.length();
            }
        }
        return finalList;
    }
    
    /**
     * Start Line Granularity
     * @param baseFile
     * @param comparedFile
     * @throws IOException
     * @throws DiffException
     */
    public void startLineGranularity(File baseFile, File comparedFile, ILCSBean ilcsb) throws IOException, DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(baseFile, ilcsb));
            Algorithm.getComparator().setColumnFileTwo(this.start(comparedFile, ilcsb));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_LINE_GRANULARITY);
        }
    }
}
