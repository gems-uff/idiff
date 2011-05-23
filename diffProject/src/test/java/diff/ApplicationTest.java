package diff;

import algorithms.Diff;
import algorithms.IResultDiff;
import algorithms.Grain;
import java.io.File;
import junit.framework.TestCase;
/**
 *
 * @author Fernanda Floriano Silva
 */
public class ApplicationTest extends TestCase {

    public void testMainLine() throws Exception {
        System.out.println(" 1 ) Starting test for LCS with LINE granularity verifications");
        Diff diff = new Diff(new File("tests/Linha1.txt"), new File("tests/Linha2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainWord() throws Exception {
        System.out.println(" 2 ) Starting test for LCS with WORD granularity verifications");
        Diff diff = new Diff(new File("tests/Palavra1.txt"), new File("tests/Palavra2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);

    }

    public void testMainCharacter() throws Exception {
        System.out.println(" 3 ) Starting test for LCS with CHARACTER granularity verifications");
        Diff diff = new Diff(new File("tests/Caracter1.txt"), new File("tests/Caracter2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainCharacter2() throws Exception {
        System.out.println(" 4 ) Starting test for LCS with granularity CHARACTER verifications");
        Diff diff = new Diff(new File("tests/Caracter1.txt"), new File("tests/Caracter3.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainEqualFile() throws Exception {
        System.out.println(" 5 ) Starting test for LCS with the same files verifications");
        Diff diff = new Diff(new File("tests/Caracter1.txt"), new File("tests/Caracter1.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testAllGrain() throws Exception {
        System.out.println(" 6 ) Starting test for LCS with all granularities verifications");
        Diff diff = new Diff(new File("tests/teste1.txt"), new File("tests/teste2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
    
    
}