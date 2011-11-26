package details;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Legend {

    private static final long serialVersionUID = 1L;
    private JPanel panel = new JPanel(new FlowLayout());
    private JTextField boxColor = new JTextField();
    private JLabel message = new JLabel();

    public Legend(String message, Color color) {
        setMessage(message);
        setBoxColor(color);
        setLegend();
    }

    public JPanel getLegend() {
        return this.panel;
    }

    private void setLegend() {
        this.panel.add(this.boxColor);
        this.panel.add(this.message);

    }

    private void setMessage(String message) {
        this.message.setText(" " + message);
        this.message.setFont(new Font("Default", Font.BOLD, 12));
    }

    private void setBoxColor(Color color) {
        this.boxColor.setPreferredSize(new Dimension(25, 25));
        this.boxColor.setMinimumSize(new Dimension(25, 25));
        this.boxColor.setMaximumSize(new Dimension(25, 25));
        this.boxColor.setEditable(false);
        this.boxColor.setBackground(color);
    }

}
