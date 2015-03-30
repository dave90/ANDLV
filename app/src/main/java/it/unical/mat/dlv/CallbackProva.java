package it.unical.mat.dlv;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dario Campisano on 27/03/2015.
 */
public class CallbackProva implements AnswerSetCallback {
    public CallbackProva(){}
    @Override
    public void callback(ArrayList<AnswerSet> answerSets) {
        for(int i = 0; i < answerSets.size();i++){
            Log.i("CallbackProva.callback", " Answer Set" + (i + 1) + " " + answerSets.get(i).getAnswerSet());
            if(answerSets.get(i).isWeightMapEmpty()){
                Log.i("CallbackProva.callback", " Answer Set"+ (i + 1) +" weight: "+ " unknown");
            }
        }
    }
}
