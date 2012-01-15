/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import idiff.Splash;
import ddiff.DDiffException;
import ddiff.ProgressMessager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eraldo
 */
public class Processo extends Thread{
    private SplashScreen s;
    private MainDDiff aThis;
    private DDiffProgress dp;

    Processo(SplashScreen s, MainDDiff aThis) {
        this.s = s;
        this.aThis = aThis;
    }

    Processo(MainDDiff aThis) {
        this.aThis = aThis;
        
        this.s = new SplashScreen();
    }
    
    ProgressMessager p;
    
    Processo(ProgressMessager p, MainDDiff aThis) {
        this.aThis = aThis;
        this.p = p;
    }
    
    Processo(DDiffProgress dp, MainDDiff aThis) {
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
        
            //s.setVisible(true);     

            try {
                aThis.loadTree();
            } catch (DDiffException ex) {
                Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
            }
            aThis.execute();
            Splash.close();
            //s.setVisible(false);
            aThis.setVisible(true);
            
        }
    }
    
}
