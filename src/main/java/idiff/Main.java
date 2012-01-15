package idiff;

import gui.FileSelection;

public class Main {

    public static void main(String args[]) {
        Splash.initSplash("Starting IDIFF...");
        FileSelection.getInstance();
    }
}