package components;

/**
 * Main
 * @author Fernanda Floriano Silva
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {
        Splash splash = new Splash();
        splash.setVisible(true);
        splash.setMessage("Starting IDIFF...");
        FileSelection.getInstance();
        splash.dispose();
        Thread.sleep(10);
    }
}