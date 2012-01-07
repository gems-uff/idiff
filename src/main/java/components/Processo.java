/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import details.Splash;
import diretorioDiff.DiretorioDiffException;
import diretorioDiff.ProgressMessager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eraldo
 */
public class Processo extends Thread{
    private final SplashScreen s;
    private final MainDDiff aThis;

    Processo(SplashScreen s, MainDDiff aThis) {
        this.s = s;
        this.aThis = aThis;
    }

    Processo(MainDDiff aThis) {
        this.aThis = aThis;
        
        this.s = new SplashScreen();
    }


    public void run() {
        //s.setVisible(true);     
        
        try {
            aThis.loadTree();
        } catch (DiretorioDiffException ex) {
            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
        }
        aThis.execute();
        //s.setVisible(false);
        aThis.setVisible(true);
    }
    
}
