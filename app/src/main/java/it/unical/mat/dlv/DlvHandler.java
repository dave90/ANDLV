package it.unical.mat.dlv;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    protected ArrayList<AnswerSet> parseResult(String outputToParse){
        /*base version parser. Only for answerSets and not for weights
        ArrayList<AnswerSet> answerSets = new ArrayList<AnswerSet>();
        /*Pattern pattern = Pattern.compile("[{](.)*[}]");
        Matcher matcher = pattern.matcher(outputToParse);
        while (matcher.find()) {
            AnswerSet answerSet = new AnswerSet(matcher.group());
            answerSets.add(answerSet);
        }*/
        //multi Thread version parser
        ArrayList<AnswerSet> answerSets = answerSetsParser(outputToParse);
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

    //utility func. for parsing with two worker thread
    private ArrayList<AnswerSet> answerSetsParser(String outputToParse){
        Log.i("DlvHandler","MultiThread parsing start ...");
        ArrayList<AnswerSet> answerSets = new ArrayList<AnswerSet>();
        String start = "DLV [build BEN/Mar 26 2015   gcc 4.8]\n";
        outputToParse = outputToParse.replace(start, ""); //delete String start from output

        final CyclicBarrier barrier = new CyclicBarrier(3); //waits until worker threads end your task

        DlvAnswerSetMatcher answerSetsWorker = new DlvAnswerSetMatcher(outputToParse,barrier);
        answerSetsWorker.start();//starts DlvAnswerSetMatcher

        DlvWeightMatcher weightsWorker = new DlvWeightMatcher(outputToParse,barrier);
        weightsWorker.start();//starts DlvWeightMatcher

        try {
            barrier.await();//waits Threads end
        } catch (InterruptedException e) {
            Log.e("DlvHandler","CyclicBarrier ERROR");
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            Log.e("DlvHandler", "CyclicBarrier ERROR");
            e.printStackTrace();
        }

        //answer sets and weights index will be the same in case of weights matching
        for (int i = 0; i < answerSetsWorker.getAnswerSets().size(); i++) {
            if (weightsWorker.getWeightMaps().isEmpty()) { //if there aren't weights  match for answer sets
                answerSets.add(new AnswerSet(answerSetsWorker.getAnswerSets().get(i)));//crete and add in ArrayList the AnswerSet i object without weight
            } else { //if there are weights match for answer sets
                answerSets.add(new AnswerSet(answerSetsWorker.getAnswerSets().get(i), weightsWorker.getWeightMaps().get(i))); //crete and add in ArrayList the AnswerSet i object with its weight
            }
        }
        Log.i("DlvHandler","MultiThread parsing end");
        return answerSets;
    }

}
