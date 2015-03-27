package it.unical.mat.dlv;

import android.util.Log;

/**
 * Created by Dario Campisano on 27/03/2015.
 */
public class CallbackProva implements AnswerSetCallback {
    public CallbackProva(){}
    @Override
    public void callback(String AnswerSet) {
        Log.i("info", "Result from callback:" + AnswerSet);
    }
}
