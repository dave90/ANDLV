package it.unical.mat.andlv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.base.ASPService;

/**
 * Created by Dario Campisano on 27/03/2015.
 * <p>OutputReceiver is an implementation of a BrodcastReceiver class. It is used to receive, in this case, a result from an ASPService</>
 */
public class OutputReceiver extends BroadcastReceiver{

    private ASPHandler aspHandler; //A generic ASPHandeler

    /**
     * Default constructor.
     * @see android.content.BroadcastReceiver
     */
    public OutputReceiver() {
        super();
    }

    /**
     * Costructor initializing a generic {@link it.unical.mat.andlv.base.ASPHandler}
     * @param aspHandler ASP handler to notify of an available result
     */
    public OutputReceiver(ASPHandler aspHandler){
        this.aspHandler = aspHandler;
    }

    /**
     * onReceive method get a Broadcast {@link android.content.Intent} sent, then send result to {@link it.unical.mat.andlv.base.ASPService} initialized with the {@link OutputReceiver} Constructor
     * @param context Application Context
     * @param intent get ASPService execution result when it is ready
     * @see android.content.Context
     * @see android.content.Intent
     * @see android.os.Bundle
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String ASPResult = bundle.getString(ASPService.SOLVER_RESULT);//get result String with SOLVER_RESULT tag from a object
            if (ASPResult != null) {
                Log.i("OutputReceiver", "Call to ASPHandler.receive(String) method");
                aspHandler.receive(ASPResult);//Send result to the generic ASPHandler through receive() method
            }
        }
    }
}
