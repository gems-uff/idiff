package details;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Legend
 * @author Fernanda Floriano Silva
 */
public class Legend {

    private JTextField colorBox = new JTextField();
    private JLabel fileName = new JLabel();

    public Legend(Color color, String fileName, JPanel panel) {
        setColorBox(color);
        setFileName(fileName);
        panel.add(this.colorBox);
        panel.add(this.fileName);
    }

    private void setColorBox(Color color) {
        this.colorBox.setBackground(color);
        this.colorBox.setSize(12, 28);
    }

    public JTextField getColorBox() {
        return colorBox;
    }


    public JLabel getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName.setText(fileName);
    }
}
