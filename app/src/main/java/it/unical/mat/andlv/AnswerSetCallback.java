package it.unical.mat.andlv;

import java.util.ArrayList;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>AnswerSetCallback is an interface. It is an Interface of a callback called when the result is available</p>
 */
public interface AnswerSetCallback {
    /** Callback for result handling
     * @param answerSets ArrayList of Answer Set objects
     */
    public void callback(ArrayList<AnswerSet> answerSets);//TODO AnswerSet answerSet public void callback(ArrayList<AnswerSet>)
}
