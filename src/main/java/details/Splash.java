package details;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class Splash {

    static void renderSplashFrame(Graphics2D g, int frame, String str) {
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120, 140, 200, 40);
        g.setPaintMode();
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString(str, 20, 305);
    }

    public static void initSplash(String msgSplash) {
        final SplashScreen splash = SplashScreen.getSplashScreen();
        Graphics2D g = splash.createGraphics();
        renderSplashFrame(g, 1, msgSplash);
        splash.update();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        splash.close();
    }
}
