package it.unical.mat.andlv.base;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>ASPService is an {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread. ASPService class provides the basic functions
 * starting an Answer Set Program generic execution with its options.</p>
 * @see android.app.IntentService
 * @see android.content.Intent
 * @see android.content.BroadcastReceiver
 */
public abstract class ASPService extends IntentService {
    //Intent messages/actions/extras for ASPService start and BroadcastReceiver communication
    public static final String ACTION_SOLVE = "it.unical.mat.andlv.SOLVE";

    public static final String PROGRAM = "it.unical.mat.andlv.PROGRAM";
    public static final String OPTION = "it.unical.mat.andlv.OPTION";
    public static final String SOLVER_RESULT = "it.unical.mat.andlv.SOLVER_RESULT";

    public static final String RESULT_NOTIFICATION = "it.unical.mat.andlv.RESULT_NOTIFICATION";

    /**
     * Constructor
     * @see android.app.IntentService
     */
    public ASPService() {
        super("ASPService");//worker thread named "ASPService"
    }

    /**
     * Execute IntentService worker thread through the start Intent received and publish the result
     * sending an Intent to the {@link OutputReceiver}
     * @param intent
     * @see android.content.Intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction(); //get action sent with the Intent
            if (ACTION_SOLVE.equals(action)) { //verify is an ACTION_SOLVE action and eventually get extra String sent in the Intent
                final String program = intent.getStringExtra(PROGRAM);//get a String with PROGRAM tag that indentify program String
                final String options = intent.getStringExtra(OPTION);//get a String with OPTION tag that indentify options String
                final String result = handleActionSolve(program, options);//call the abstract method that handle a specific solve action
                publishResults(result); //Send the result with a Broadcast Intent
            }
        }
    }

    /**
     * Send a Broadcast {@link android.content.Intent} with the Answer Set Program result
     * @param result
     * @see android.content.BroadcastReceiver
     * @see OutputReceiver
     * @see android.content.Intent
     */
    private void publishResults(String result) {
        Intent intent = new Intent(RESULT_NOTIFICATION); //create a new Intent with specific RESULT_NOTIFICATION
        intent.putExtra(SOLVER_RESULT, result);//put the result in the Intent
        sendBroadcast(intent);//send a Brodcast Intent
    }

    /**
     * Handle action ACTION_SOLVE in the provided background thread with the provided
     * parameters.
     * @param program
     * @param option
     */
    protected abstract String handleActionSolve(String program, String option) ;

}