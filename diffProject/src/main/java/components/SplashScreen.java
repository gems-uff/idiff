package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class SplashScreen extends JDialog {

    JLabel statusLabel;
    JLabel memoryLabel;

    /**
     * Construct a modal splash screen
     */
    public SplashScreen(JFrame owner) {
        super(owner, true);
        this.setLocationRelativeTo(owner);
        this.init();
    }

    /**
     * Construct a non-modal splash screen
     */
    public SplashScreen() {
        super();
        this.init();
    }

    /**
     * Create/Configure the splash screen
     */
    private void init() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setBackground(new Color(245, 245, 245));

        memoryLabel = new JLabel();
        memoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(memoryLabel, BorderLayout.NORTH);

        JLabel mainLabel = new JLabel("IDIFF", SwingConstants.CENTER);
        mainLabel.setFont(new Font("SansSerif", Font.PLAIN, 72));
        panel.add(mainLabel, BorderLayout.CENTER);

        statusLabel = new JLabel();
        statusLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panel.add(statusLabel, BorderLayout.SOUTH);

        this.setUndecorated(true);
        this.setContentPane(panel);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    }

    /**
     * Write a new status message
     */
    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    /**
     * @see java.awt.Component#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        this.setLocationRelativeTo(this.getOwner());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        super.setVisible(visible);
    }
}