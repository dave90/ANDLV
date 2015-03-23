package it.unical.mat.dlv;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 *
 *
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 */
public abstract class ASPSolverService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SOLVE = "it.unical.mat.dlv.action.SOLVE";

    // TODO: Rename parameters
    private static final String PROGRAM = "it.unical.mat.dlv.extra.PROGRAM";
    private static final String OPTION = "it.unical.mat.dlv.extra.OPTION";
    private static final String SOLVER_RESULT = "it.unical.mat.dlv.extra.SOLVER_RESULT";

    private static final String RESULT_NOTIFICATION = "it.unical.mat.dlv.notification.RESULT_NOTIFICATION";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startSolverService(Context context, String program, String option) {
        Intent intent = new Intent(context, ASPSolverService.class);
        intent.setAction(ACTION_SOLVE);
        intent.putExtra(PROGRAM, program);
        intent.putExtra(OPTION, option);
        context.startService(intent);
    }

    public ASPSolverService() {
        super("ASPSolverService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SOLVE.equals(action)) {
                final String program = intent.getStringExtra(PROGRAM);
                final String option = intent.getStringExtra(OPTION);
                final String result = handleActionSolve(program, option);
                publishResults(result);
            }
        }
    }

    private void publishResults(String result) {
        Intent intent = new Intent(RESULT_NOTIFICATION);
        intent.putExtra(SOLVER_RESULT, result);
        sendBroadcast(intent);
    }

    /**
     * Handle action ACTION_SOLVE in the provided background thread with the provided
     * parameters.
     */
    abstract String handleActionSolve(String program, String option) ;
}
