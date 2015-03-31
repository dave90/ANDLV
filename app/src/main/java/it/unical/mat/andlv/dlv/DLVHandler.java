package it.unical.mat.andlv.dlv;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unical.mat.andlv.OutputReceiver;
import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.AnswerSet;
import it.unical.mat.andlv.base.AnswerSetCallback;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>DLVHandler is an implementation of an {@link it.unical.mat.andlv.base.ASPHandler} used for a DLV ASP solver execution handling.It uses
 * an {@link it.unical.mat.andlv.OutputReceiver} class that notify a result from a {@link it.unical.mat.andlv.base.ASPService} implementation class that provides a native method invoching DLV in an appropriate
 * working thread. An {@link it.unical.mat.andlv.base.AnswerSetCallback} class is used for result handling.
 * .</p>
 * @see android.app.IntentService
 * @see android.content.BroadcastReceiver
 */
public class DLVHandler extends ASPHandler {
    private Context context; //application context
    private BroadcastReceiver receiver; //OutputReceiver is a BroadcastReceiver used to notify the result from DLVService
    private AnswerSetCallback asCallbask; //callback for result

    /**
     * Constructor inizialize the {@link it.unical.mat.andlv.dlv.DLVService}
     */
    public DLVHandler(){super();}

    /** Starts DLVService and initialize Application {@link Context} and {@link it.unical.mat.andlv.base.AnswerSetCallback}
     * @param asCallback AnswerSetCallback object
     * @param context Context object
     * @see it.unical.mat.andlv.base.AnswerSetCallback
     * @see android.content.Context
     */
    @Override
    public void start(Context context, AnswerSetCallback asCallback) {
        this.asCallbask = asCallback;
        this.context = context;
        receiver = new OutputReceiver(this);
        killingDlvService();
        Intent intent = new Intent(context, DLVService.class);
        intent.setAction(DLVService.ACTION_SOLVE);
        intent.putExtra(DLVService.PROGRAM, this.program);
        intent.putExtra(DLVService.OPTION, this.options);
        context.registerReceiver(receiver, new IntentFilter(DLVService.RESULT_NOTIFICATION));
        context.startService(intent);
    }

    /**
     * Receive an outputToParse String that contains the execution result.
     * @param outputToParse
     * @return ArrayList<AnswerSet> Contains {@link it.unical.mat.andlv.AnswerSet} object generated from an Answer Set Program String output
     * @see java.util.ArrayList
     * @see it.unical.mat.andlv.base.AnswerSetCallback
     */
    protected ArrayList<AnswerSet> parseResult(String outputToParse){
        //base version parser. Only for answerSets and not for weights
        ArrayList<AnswerSet> answerSets = new ArrayList<AnswerSet>();
        Pattern pattern = Pattern.compile("[{](.*)[}]");
        Matcher matcher = pattern.matcher(outputToParse);
        while (matcher.find()) {
            AnswerSet answerSet = new AnswerSet(matcher.group());
            answerSets.add(answerSet);
        }

        return answerSets;
    }
    /**
     * Called from the {@link OutputReceiver}.
     * Calls the {@link it.unical.mat.andlv.base.AnswerSetCallback} callback function for result handling.
     * @param aspServiceOut
     * @see it.unical.mat.andlv.base.AnswerSetCallback
     */
    @Override
    public void receive(String aspServiceOut){
        ArrayList<AnswerSet> answerSets =  parseResult(aspServiceOut);
        asCallbask.callback(answerSets);
    }

    /**
     * Check if a service is already running and waits until service is killed.
     * @see android.app.ActivityManager
     */
    void killingDlvService(){
        Log.i("DLVHandler", "Verifying if " + DLVService.class.getName() + " is already Running ...");

        boolean isServiceRunning = true;

        while (isServiceRunning) {

            //get device active service list
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            isServiceRunning = false;
            //see if DLVService is in running service list
            for (ActivityManager.RunningServiceInfo processInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (processInfo.service.getClassName().equals(DLVService.class.getName())) {
                    isServiceRunning = true;
                    Log.i("DlvHandler", "Wait " + processInfo.service.getClassName() + " is already running!");
                    break;
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.i("DlvHandler","New ASPService can be started ...");
    }

}
