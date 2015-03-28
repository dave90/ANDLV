package it.unical.mat.dlv;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.util.Log;

import it.unical.mat.dlvjni.DlvService;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public class DlvHandler extends ASPHandler{
    private Context context;

    private OutputReceiver receiver;
    private DlvService dlvService;
    private AnswerSetCallback asCallbask;

    /**
     *
     * @param context Application Context
     */
    public DlvHandler(Context context){
        this.context = context;
        dlvService = new DlvService();
    }

    /** Execute the Answer Set Program and get AnswerSetCallback implemented
     * @param asCallback
     */
    @Override
    public void start(AnswerSetCallback asCallback) {
        this.asCallbask = asCallback;
        receiver = new OutputReceiver(this);
        killingDlvService();
        Intent intent = new Intent(context, dlvService.getClass());
        intent.setAction(dlvService.ACTION_SOLVE);
        intent.putExtra(dlvService.PROGRAM, this.program);
        intent.putExtra(dlvService.OPTION, this.options);
        context.registerReceiver(receiver, new IntentFilter(DlvService.RESULT_NOTIFICATION));
        context.startService(intent);
    }

    /**
     * Receive an output to parse
     * @param outputToParse
     * @return ArrayList<AnswerSet> Contains Answer sets generated from an Answer Set Program String output
     */
    @Override
    protected String parseResult(AnswerSet outputToParse) {
        return outputToParse.getAnswerSet();
    }

    /**
     * Receive output, call parseResult(String outputToParse) and finally the method AnswerSetCallback.callback(AnswerSet answerSet)
     * @param aspServiceOut
     */
    @Override
    protected void receive(String aspServiceOut){
        AnswerSet answerSet = new AnswerSet(aspServiceOut);
        String out =  parseResult(answerSet);
        asCallbask.callback(out);
    }

    /**
     * Check if a service is already running and stops until service will killed
     */
    void killingDlvService(){
        Log.i("info", "Verifying if " + dlvService.getClass().getName() + " is already Running ...");
        boolean isServiceRunning = true;
        while (isServiceRunning) {

            //get device active service list
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            isServiceRunning = false;
            for (ActivityManager.RunningServiceInfo processInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (processInfo.service.getClassName().equals(dlvService.getClass().getName())) {
                    isServiceRunning = true;
                    Log.i("DlvHandler.killing[..]", "Wait " + processInfo.service.getClassName() + " is already running!");
                    break;
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.i("DlvHandler.killing[..]","New ASPService can be started ...");
    }
}
