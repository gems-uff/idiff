package diff;

import java.util.Iterator;
import java.util.List;

/**
 * IResultLCS
 * @author Fernanda Floriano Silva
 */
public interface IResultLCS {

    /**
     * Get File Version One
     * @return  List<Grain>
     */
    List<Grain> getFileVersionOne();

    /**
     * Get File Version Two
     * @return  List<Grain>
     */
    List<Grain> getFileVersionTwo();

    /**
     * Get Lcs
     * @return List<Grain
     */
    List<Grain> getLcs();

    /**
     * Get Next
     * @param it
     * @return Grain
     */
    Grain getNext(Iterator it);
}
