package it.unical.mat.dlv;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public abstract class ASPHandler {

    private String file; //a single input file
    protected ArrayList<String> rowInputs; //rowInputs inserted
    protected ArrayList<String> options; //options inserted
    protected ASPSolverService ASPSolver; //ASP solver used
    private AnswerSetCallback asCallback; //Developer Callback
    private Context context; //Application context

    private String outputToParse;//Store result to parse

    /**
     *
     * @param context
     */
    public ASPHandler(Context context){
        this.context = context;
        this.ASPSolver = createASPSolverService();
    }

    /**
     * Starts {@link #ASPSolver} and sets the callback
     * @param asCallback
     */
    public void start(AnswerSetCallback asCallback){
        //TODO
        this.asCallback = asCallback;
        //startASPSolverService(generateInputProgram(), generateInputOptions());
        startASPSolverService("", "");
    }

    /**
     *
     * @param option
     */
    public void addOption(String option){
        this.options.add(option);
    }

    /**
     *
     * @param rowInput
     */
    public void addRowInput(String rowInput){
        this.rowInputs.add(rowInput);
    }

    /**
     *
     * @param file
     */
    public void addFileInput(String file){
        this.file = file;
    }

    /**
     *
     * @param obj
     */
    public void addInput(Object obj){
        //TODO
    }

    /**
     *
     * @param set
     */
    public void addInput(Objects set){
        //TODO
    }

    /**
     * Utility function waiting ASPSolverService has finished its task.
     * It's dangerous to start another ASPSolverService until there's another ASPSolverService in runnning services queue.
     */
    private void killingDlvService(){
        Log.i("info", "Verifying if "+ASPSolver.getClass().getName()+" is already Running ...");
        //Intent intent = new Intent(this,DlvService.class);
        //stopService(intent);
        boolean isServiceRunning = true;
        while (isServiceRunning) {

            //get device active service list
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            isServiceRunning = false;
            for (ActivityManager.RunningServiceInfo processInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (processInfo.service.getClassName().equals(ASPSolver.getClass().getName())) {//TODO
                    isServiceRunning = true;
                    Log.i("info", "Wait " + processInfo.service.getClassName() + " is already running!");
                    break;
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.i("info","New ASPSolverService can be started ...");
    }

    /**
     * Notify an output and call AnswerSetCallback
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                outputToParse = bundle.getString(ASPSolver.SOLVER_RESULT);
                if (outputToParse != null) {
                    String output = parseResult(outputToParse); //TODO parseResult will return an AswerSet object
                    Log.i("info","Call to callback(..)");
                    asCallback.callback(output);
                }
            }
        }
    };

    private void startASPSolverService(String program, String options) {
        killingDlvService();
        Intent intent = new Intent(context, ASPSolver.getClass());
        intent.setAction(ASPSolver.ACTION_SOLVE);
        intent.putExtra(ASPSolver.PROGRAM, program);
        intent.putExtra(ASPSolver.OPTION, options);
        context.registerReceiver(receiver, new IntentFilter(ASPSolver.RESULT_NOTIFICATION));
        context.startService(intent);
    }

    /**
     * Parse result and create an AswerSet Object
     * @param outputToParse
     */
    abstract protected String parseResult(String outputToParse); //return AswerSet Obj TODO

    /**
     * @return "new ASPSolverService();". ASPSolverService implemented.
     */
    abstract protected ASPSolverService createASPSolverService();

}
