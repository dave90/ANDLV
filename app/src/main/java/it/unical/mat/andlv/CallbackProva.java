package it.unical.mat.andlv;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dario Campisano on 27/03/2015.
 * <p>Test implementation of an {@link it.unical.mat.andlv.AnswerSetCallback} class</p>
 */
public class CallbackProva implements AnswerSetCallback {
    public CallbackProva(){}
    @Override
    public void callback(ArrayList<AnswerSet> answerSets) {
        for(int i = 0; i < answerSets.size();i++){
            Log.i("CallbackProva.callback", " Answer Set" + (i + 1) + " " + answerSets.get(i).getAnswerSet());
        }
    }
}
