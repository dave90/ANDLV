package it.unical.mat.andlv.dlv;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;

import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.base.AnswerSetCallback;
import it.unical.mat.andlv.base.OutputReceiver;

/**
 * <p>DLVHandler is an implementation of an {@link it.unical.mat.andlv.base.ASPHandler} used for a DLV ASP solver execution handling.It uses
 * an {@link it.unical.mat.andlv.base.OutputReceiver} class that notify a result from a {@link it.unical.mat.andlv.base.ASPService} implementation class that provides a native method invoching DLV in an appropriate
 * working thread. An {@link it.unical.mat.andlv.base.AnswerSetCallback} class is used for result handling.
 * .</p>
 * @see android.app.IntentService
 * @see android.content.BroadcastReceiver
 */
public class DLVHandler extends ASPHandler {
    private Context context; //application context
    private AnswerSetCallback asCallbask; //callback for result
    private OutputReceiver receiver;


    /**
     * Constructor inizialize the {@link DLVService}
     */
    public DLVHandler(){
        super();
        receiver= new OutputReceiver(this);
    }

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
        killingDlvService();
        Intent intent = new Intent(context, DLVService.class);
        intent.setAction(DLVService.ACTION_SOLVE);
        intent.putExtra(DLVService.PROGRAM, this.program.toString());
        intent.putExtra(DLVService.OPTION, this.options.toString());
        intent.putExtra(DLVService.FILES, (ArrayList<String>)this.filesPaths);
        context.registerReceiver(receiver, new IntentFilter(DLVService.RESULT_NOTIFICATION));
        Log.i(getClass().getName()," start service");
        context.startService(intent);
    }

    /**
     * Called from the {@link OutputReceiver}.
     * Calls the {@link it.unical.mat.andlv.base.AnswerSetCallback} callback function for result handling.
     * @param aspServiceOut
     * @see it.unical.mat.andlv.base.AnswerSetCallback
     */
    @Override
    protected void receive(String aspServiceOut){
        asCallbask.callback(new DLVAnswerSets(aspServiceOut));
    }

    /**
     * Check if a service is already running and waits until service is killed.
     * @see android.app.ActivityManager
     */
    void killingDlvService(){

        boolean isServiceRunning = true;

        while (isServiceRunning) {

            //get device active service list
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            isServiceRunning = false;
            //see if DLVService is in running service list
            for (ActivityManager.RunningServiceInfo processInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (processInfo.service.getClassName().equals(DLVService.class.getName())) {
                    isServiceRunning = true;
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
