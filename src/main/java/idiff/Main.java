package idiff;

import gui.FileSelection;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String args[]) {
        logger.info("Starting application ...");
        FileSelection.getInstance();
    }
}