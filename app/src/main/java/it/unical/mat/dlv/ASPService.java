package it.unical.mat.dlv;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 */
public abstract class ASPService extends IntentService {
    //Intent message
    public static final String ACTION_SOLVE = "it.unical.mat.action.SOLVE";

    public static final String PROGRAM = "it.unical.mat.extra.PROGRAM";
    public static final String OPTION = "it.unical.mat.extra.OPTION";
    public static final String SOLVER_RESULT = "it.unical.mat.extra.SOLVER_RESULT";

    protected static final String RESULT_NOTIFICATION = "it.unical.mat.notification.RESULT_NOTIFICATION";

    public ASPService() {
        super("ASPService");
    }//TODO verificare costruttore

    /**
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SOLVE.equals(action)) {
                final String program = intent.getStringExtra(PROGRAM);
                final String options = intent.getStringExtra(OPTION);
                final String result = handleActionSolve(program, options);
                publishResults(result);
            }
        }
    }

    /**
     *
     * @param result
     */
    private void publishResults(String result) {
        Intent intent = new Intent(RESULT_NOTIFICATION);
        intent.putExtra(SOLVER_RESULT, result);
        sendBroadcast(intent);
    }

    /**
     * Handle action ACTION_SOLVE in the provided background thread with the provided
     * parameters.
     * @param program
     * @param option
     */
    protected abstract String handleActionSolve(String program, String option) ;

}