package it.unical.mat.dlv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public abstract class ASPHandler {

    private String file; //a single input file
    protected ArrayList<String> rowInputs; //rowInputs inserted
    protected ArrayList<String> options; //options inserted
    private ASPSolverService ASPSolver; //ASP solver used
    private AnswerSetCallback asCallback; //Developer Callback
    private Context context; //Application context

    private String outputToParse;

    /**
     *
     * @param ASPSolver Answer set program solver used
     */
    public ASPHandler(ASPSolverService ASPSolver, Context context){
        this.ASPSolver = ASPSolver;
        this.context = context;
    }

    /**
     * Starts {@link #ASPSolver} and sets the callback
     * @param asCallback
     */
    public void start(AnswerSetCallback asCallback){
            //TODO
            this.asCallback = asCallback;
            ASPSolver.startSolverService(this.context,generateInputProgram(),generateInputOptions());
    }

    public void addOption(String option){
        this.options.add(option);
    }

    public void addRowInput(String rowInput){
        this.rowInputs.add(rowInput);
    }

    public void addFileInput(String file){
        this.file = file;
    }

    public void addInput(Object obj){
        //TODO
    }

    public void addInput(Objects set){
        //TODO
    }

    /**
     *
     * @return A String containing input program
     */
    abstract protected String generateInputProgram();

    /**
     *
     * @return A String containing program options
     */
    abstract protected String generateInputOptions();

    /**
     * Parse result and create an AswerSet Object
     */
    abstract protected String parseResult(String outputToParse); //return AswerSet Obj TODO

    /**
     * Get and process result
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();


            if (bundle != null) {
                outputToParse = bundle.getString(ASPSolver.SOLVER_RESULT);
                if (outputToParse != null) {
                    String output = parseResult(outputToParse); //TODO parseResult will return an AswerSet object
                    asCallback.callback(output);
                }
            }
        }
    };


}
