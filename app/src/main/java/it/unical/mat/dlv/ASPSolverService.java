package it.unical.mat.dlv;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 */
public abstract class ASPSolverService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    protected static final String ACTION_SOLVE = "it.unical.mat.dlv.action.SOLVE";

    // TODO: Rename parameters
    protected static final String PROGRAM = "it.unical.mat.dlv.extra.PROGRAM";
    protected static final String OPTION = "it.unical.mat.dlv.extra.OPTION";
    protected static final String SOLVER_RESULT = "it.unical.mat.dlv.extra.SOLVER_RESULT";

    protected static final String RESULT_NOTIFICATION = "it.unical.mat.dlv.notification.RESULT_NOTIFICATION";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     */
    /*// TODO: Customize helper method
    public static void startSolverService(Context context, String program, String options) {
        Intent intent = new Intent(context, ASPSolverService.class);
        intent.setAction(ACTION_SOLVE);
        intent.putExtra(PROGRAM, program);
        intent.putExtra(OPTION, options);
        context.startService(intent);
    }*/

    public ASPSolverService() {
        super("ASPSolverService");
    }

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

    protected abstract void onDestroyAction();

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyAction();
    }
}
