package components;

import java.awt.dnd.DnDConstants;

/**
 * DnDUtils
 * @author Fernanda Floriano Silva
 */
class DnDUtils {

    public static String showActions(int action) {
        String actions = "";
        if ((action & (DnDConstants.ACTION_LINK | DnDConstants.ACTION_COPY_OR_MOVE)) == 0) {
            return "None";
        }

        if ((action & DnDConstants.ACTION_COPY) != 0) {
            actions += "Copy ";
        }

        if ((action & DnDConstants.ACTION_MOVE) != 0) {
            actions += "Move ";
        }

        if ((action & DnDConstants.ACTION_LINK) != 0) {
            actions += "Link";
        }

        return actions;
    }

    public static void debugPrintln(String s) {
            System.out.println(s);
    }
}

