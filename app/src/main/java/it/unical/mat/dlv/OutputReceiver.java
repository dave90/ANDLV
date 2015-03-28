package it.unical.mat.dlv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Dario Campisano on 27/03/2015.
 */
public class OutputReceiver extends BroadcastReceiver{

    private ASPHandler aspHandler;

    public OutputReceiver() {
        super();
    }

    /**
     * Notify the result to ASPHandler
     * @param aspHandler
     */
    public OutputReceiver(ASPHandler aspHandler){
        this.aspHandler = aspHandler;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String ASPResult = bundle.getString(ASPService.SOLVER_RESULT);
            if (ASPResult != null) {
                Log.i("info", "Call to callback()");
                aspHandler.receive(ASPResult);
            }
        }
    }
}
