package components;

/**
 *
 * @author Fernanda Floriano Silva
 */
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
//------------------------------------------------------------------------------
public class diffProjectAplication extends SingleFrameApplication {

    @Override
    protected void startup() {
        show(new MainILCS());
    }
    //--------------------------------------------------------------------------

    @Override
    protected void configureWindow(java.awt.Window root) {
    }
    //--------------------------------------------------------------------------

    public static diffProjectAplication getApplication() {
        return Application.getInstance(diffProjectAplication.class);
    }
    //--------------------------------------------------------------------------

    public static void main(String[] args) {
        launch(diffProjectAplication.class, args);
    }
}
//==============================================================================
