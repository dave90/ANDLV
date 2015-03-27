package it.unical.mat.dlv;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public abstract class ASPHandler {


    protected String options;
    protected String program;

    public ASPHandler(){
        this.options = "";
        this.program = "";
    }

    /**
     * @param asCallback
     */
    public abstract void start(AnswerSetCallback asCallback);

    /**
     *
     * @param options
     */
    public void addOption(String options){
        this.options = options;
    }

    /**
     *
     * @param program
     */
    public void addRowInput(String program){
        this.program = program;
    }

    /**
     *
     * @param file
     */
    public void addFileInput(String file){//file path
            //TODO
    }

    /**
     * Parse result and create an AswerSet Object
     * @param outputToParse
     */

    abstract protected String parseResult(String outputToParse); //return ArrayList<AswerSet> TODO

    abstract protected void receive(String aspServiceOut);

}
