package diff;

import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class ApplicationTest extends TestCase {

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    public void testMainLine() throws Exception {
     System.out.println(" 1 ) Iniciando teste para verificação de LCS com granularidade LINHA");
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        Diff diff = new Diff(new File(dir + "Linha1.txt"), new File(dir + "Linha2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainWord() throws Exception {
     System.out.println(" 2 ) Iniciando teste para verificação de LCS com granularidade PALAVRA");
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        Diff diff = new Diff(new File(dir + "Palavra1.txt"), new File(dir + "Palavra2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);

    }

    public void testMainCharacter() throws Exception {
        System.out.println(" 3 ) Iniciando teste para verificação de LCS com granularidade CARACTER");
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        Diff diff = new Diff(new File(dir + "Caracter1.txt"), new File(dir + "Caracter2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
    public void testMainCharacter2() throws Exception {
        System.out.println(" 4 ) Iniciando teste para verificação de LCS com granularidade CARACTER");
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        Diff diff = new Diff(new File(dir + "Caracter1.txt"), new File(dir + "Caracter3.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }

    public void testMainEqualFile() throws Exception {
        System.out.println(" 5 ) Iniciando teste para verificação de LCS com todas as granularidades");
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        Diff diff = new Diff(new File(dir + "Caracter1.txt"), new File(dir + "Caracter1.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
       public void testAllGrain() throws Exception {
        System.out.println(" 6 ) Iniciando teste para verificação de LCS com todas as granularidades");
        String dir = "C:/Users/Sisi/Desktop/Testes LCS/";
        Diff diff = new Diff(new File(dir + "teste1.txt"), new File(dir + "teste2.txt"));
        Grain grain = new Grain(Grain.LevelGranularity.FILE);
        IResultDiff compare = diff.compare(grain);
    }
}
