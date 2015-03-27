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

    public DlvHandler(Context context){
        this.context = context;
        dlvService = new DlvService();
    }

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

    @Override
    protected String parseResult(String outputToParse) {
        return outputToParse;
    }

    @Override
    protected void receive(String aspServiceOut){
        String out =  parseResult(aspServiceOut);
        asCallbask.callback(out);
    }
    void killingDlvService(){
        Log.i("info", "Verifying if ASPService is already Running ...");
        boolean isServiceRunning = true;
        while (isServiceRunning) {

            //get device active service list
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            isServiceRunning = false;
            for (ActivityManager.RunningServiceInfo processInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (processInfo.service.getClassName().equals(dlvService.getClass().getName())) {
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
        Log.i("info","New ASPService can be started ...");
    }
}
