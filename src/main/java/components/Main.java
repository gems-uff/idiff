package components;

import details.Splash;

public class Main {

    public static void main(String args[]) {
        Splash.initSplash("Starting IDIFF...");
        FileSelection.getInstance();
    }
}