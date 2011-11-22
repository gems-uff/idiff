package details;

import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Fernanda Floriano
 */
public class Menu {

    private JPopupMenu menu = new JPopupMenu();

    public Menu(String item) {
        menu.add(new JMenuItem(item));
    }

    public JPopupMenu getMenu() {
        return menu;
    }

    public void show(Component component, int x, int y) {
        menu.show(component, x, y);
    }
}
