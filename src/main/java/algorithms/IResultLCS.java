package algorithms;

import java.util.Iterator;
import java.util.List;

/**
 * IResultLCS
 * @author Fernanda Floriano Silva
 */
public interface IResultLCS {

    /**
     * Get List with File Version One
     * @return  List<Grain>
     */
    List<Grain> getFileVersionOne();

    /**
     * Get List with File Version Two
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
    Grain getNext(Iterator<Grain> it);
}
