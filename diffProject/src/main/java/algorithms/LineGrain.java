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
     * @param start 
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
     * @param ilcsb
     * @return
     * @throws IOException 
     */
    public List<Grain> start(File file, ILCSBean ilcsb, int idFile) throws IOException {
        List<Grain> finalList = new ArrayList<Grain>();
        finalList.add(null);
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int id = 0;
        int idStart = 0;
        while ((line = reader.readLine()) != null) {
            if (!((ilcsb.isRemoveEmptyLine()) && (line.isEmpty()))) {
                id++;
                LineGrain lineGrain = new LineGrain(ilcsb.verifyParameters(line), id, idStart);
                lineGrain.setIdFile(idFile);
                finalList.add(lineGrain);
                idStart = idStart + line.length() + 1;
            }
        }
        return finalList;
    }

    /**
     * Start Line Granularity
     * @param fileFrom
     * @param fileTo
     * @param ilcsb
     * @throws IOException
     * @throws DiffException 
     */
    public void startLineGranularity(File fileFrom, File fileTo, ILCSBean ilcsb) throws IOException, DiffException {
        try {
            Algorithm.getComparator().setLinesFileOne(this.start(fileFrom, ilcsb, 1));
            Algorithm.getComparator().setColumnFileTwo(this.start(fileTo, ilcsb, 2));
        } catch (IOException ex) {
            throw new DiffException(ex, DiffException.MSG_INVALID_START_LINE_GRANULARITY);
        }
    }
}
