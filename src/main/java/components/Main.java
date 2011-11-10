package components;

/**
 *
 * @author Sisi
 */
public class Main {

    public static void main(String args[]) {
        // Show splash window
        Splash splash = new Splash();
        splash.setVisible(true);

        splash.setMessage("    Starting IDIFF...");
        splash.setMessage("    Wait...");


        for (int i = 0; i < 500000; i++) {
        }
        FileSelection.setInstance();

        splash.dispose();

    }
}
