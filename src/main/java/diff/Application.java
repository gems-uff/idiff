package diff;

import java.io.File;

/**
 * Test Application
 * @author Fernanda Floriano Silva
 *
 */
public class Application {

    /**
     * Test Application
     * @param args
     * @throws DiffException
     */
    public static void main(String[] args) throws DiffException {
        Diff diff = new Diff(new File("C:/Users/Sisi/Desktop/teste1.txt"), new File("C:/Users/Sisi/Desktop/teste2.txt"));
        Grain grain = new Grain(4, "File");//4 - id File Grain
        IResultDiff compare = diff.compare(grain);
    }
}
