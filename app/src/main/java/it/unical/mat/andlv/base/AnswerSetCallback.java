package it.unical.mat.andlv.base;

import java.util.ArrayList;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>AnswerSetCallback an Interface of a callback, called when the result is available</p>
 */
public interface AnswerSetCallback {
    /** callback function for result handling
     * @param answerSets {@link java.util.ArrayList} of {@link AnswerSet} objects
     * @see AnswerSet
     */
    public void callback(ArrayList<AnswerSet> answerSets);
}
