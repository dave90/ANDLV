package it.unical.mat.andlv;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread. ASPService is an Abstract class that provide the basic functions
 * starting an Answer Set Program generic execution with its options.
 * <p></>
 * @see android.app.IntentService
 */
public abstract class ASPService extends IntentService {
    //Intent messages/actions to start ASPService and to notify the result to the OutputReceiver
    public static final String ACTION_SOLVE = "it.unical.mat.andlv.SOLVE";

    public static final String PROGRAM = "it.unical.mat.andlv.PROGRAM";
    public static final String OPTION = "it.unical.mat.andlv.OPTION";
    public static final String SOLVER_RESULT = "it.unical.mat.andlv.SOLVER_RESULT";

    public static final String RESULT_NOTIFICATION = "it.unical.mat.andlv.RESULT_NOTIFICATION";

    /**
     * Constructor name the worker thread
     * @see android.app.IntentService
     */
    public ASPService() {
        super("ASPService");//worker thread named "ASPService"
    }

    /**
     * Execute IntentService worker thread through the Intent start received and publish the result
     * sending an Intent to the {@link it.unical.mat.andlv.OutputReceiver}
     * @param intent
     * @see android.content.Intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SOLVE.equals(action)) {
                final String program = intent.getStringExtra(PROGRAM);//get the String Answer Set Program from the start Intent
                final String options = intent.getStringExtra(OPTION);//get the options for the Answer Set Program execution
                final String result = handleActionSolve(program, options);// the abstract method that starts Answer Set Program the execution
                publishResults(result); //Send the result with a Broadcast Intent
            }
        }
    }

    /**
     * Send a Broadcast Intent with the Answer Set Program result
     * @param result
     * @see android.content.BroadcastReceiver
     * @see it.unical.mat.andlv.OutputReceiver
     * @see android.content.Intent
     */
    private void publishResults(String result) {
        Intent intent = new Intent(RESULT_NOTIFICATION); //create a new Intent with specific RESULT_NOTIFIACTION
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
