/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.resources;

import ddiff.DDiffException;
import ddiff.ProgressMessager;
import gui.DDiffProgress;
import gui.MainDDiff;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eraldo
 */
public class Processo extends Thread {

    private SplashScreen s;
    private MainDDiff aThis;
    private DDiffProgress dp;

    public Processo(SplashScreen s, MainDDiff aThis) {
        this.s = s;
        this.aThis = aThis;
    }

    public Processo(MainDDiff aThis) {
        this.aThis = aThis;

        this.s = new SplashScreen();
    }
    ProgressMessager p;

    public Processo(ProgressMessager p, MainDDiff aThis) {
        this.aThis = aThis;
        this.p = p;
    }

    public Processo(DDiffProgress dp, MainDDiff aThis) {
        this.aThis = aThis;
        this.dp = dp;
    }

    public void run() {
        if (dp != null) {

            dp.setMessage("");

            int i = 0;
            while (!dp.isVisible() && i < 1000) {
                i++;
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                dp.setMessage("Loading directories tree.");
                aThis.loadTree();
            } catch (DDiffException ex) {
                Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
            }
            aThis.execute();
            dp.setVisible(false);
        } else {
            try {
                aThis.loadTree();
            } catch (DDiffException ex) {
                Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
            }
            aThis.execute();
            SplashScreen.close();
            aThis.setVisible(true);

        }
    }
}
