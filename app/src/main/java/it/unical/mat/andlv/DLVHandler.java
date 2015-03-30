package it.unical.mat.andlv;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unical.mat.dlvjni.DlvService;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>DLVHandler is an implementation of an ASPHandeler specifically used for a DLV execution handling </>
 */
public class DLVHandler extends ASPHandler {
    private Context context;

    private OutputReceiver receiver;
    private DlvService dlvService;
    private AnswerSetCallback asCallbask;

    /**
     *
     */
    public DLVHandler(){

        dlvService = new DlvService();
    }

    /** Execute the Answer Set Program and get AnswerSetCallback implemented
     * @param asCallback
     */
    @Override
    public void start(Context context,AnswerSetCallback asCallback) {
        this.asCallbask = asCallback;
        this.context = context;
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
     * Receive output, call parseResult(String outputToParse) and finally the method AnswerSetCallback.callback(ArrayList<AnswerSet>)
     * @param aspServiceOut
     */
    @Override
    protected void receive(String aspServiceOut){
        ArrayList<AnswerSet> answerSets =  parseResult(aspServiceOut);
        asCallbask.callback(answerSets);
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
