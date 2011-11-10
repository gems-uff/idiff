package components;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Main {

    public static void main(String args[]) {
        Splash splash = new Splash();
        splash.setVisible(true);
        splash.setMessage("    Starting IDIFF...");

        FileSelection.setInstance();

        splash.dispose();

    }
}
