package it.unical.mat.dlv;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    /** Execute the Answer Set Program and get AnswerSetCallback implemented
     * @param asCallback
     */
    public abstract void start(AnswerSetCallback asCallback);

    /**
     * Add an Answer Set Program option for execution
     * @param options
     */
    public void addOption(String options){
        this.options = this.options.concat( options + " " );
    }

    /**
     *  Add an Answer Set Program row input
     * @param rowInput
     */
    public void addRowInput(String rowInput){
        this.program = this.program.concat(rowInput + "\n");
    }

    /**
     *  Get Answer Set Program from filesystem
     * @param
     */
    public void addFileInput(String filePath){
        File programFromPath = new File(filePath);
        String aspProgram = fileToString(programFromPath);
        this.program = this.program.concat(aspProgram + "\n");
    }

    /**
     * Create String from File
     * @param file
     * @return
     */
    private String fileToString(File file){
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {

                    text.append(line + "\n");
            }
            br.close();
        }
        catch (IOException e) {
            Log.e("ASPHandler.fileToString","Error reading file: " + file.getAbsolutePath());
        }
        return text.toString();
    }

    /**
     * Receive an output to parse
     * @param outputToParse
     * @return ArrayList<AnswerSet> Contains Answer sets generated from an Answer Set Program String output
     */
    abstract protected ArrayList<AnswerSet> parseResult(String outputToParse); //return ArrayList<AswerSet> TODO

    /**
     * Receive output, call parseResult(String outputToParse) and finally the method AnswerSetCallback.callback(AnswerSet answerSet)
     * @param aspServiceOut
     */
    abstract protected void receive(String aspServiceOut);


}
