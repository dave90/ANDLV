package it.unical.mat.andlv.base;

import android.content.Context;

/**
 * <p>ASPHandler is an Abstract class. It provides generic methods for an Answer Set Program execution handling.
 * Get an Answer Set Program and its options for the execution. Provide a public methos to start the Answer Set Program execution. </p>
 */
public abstract class ASPHandler {

    protected String options;//stores program options
    protected String program;//store an entire ASP program

    public ASPHandler(){
        this.options = "";
        this.program = "";
    }

    /** Execute the Answer Set Program and get AnswerSetCallback implemented
     * that is called for the result handling
     * @param asCallback
     * @param context
     * @see it.unical.mat.andlv.base.AnswerSetCallback
     * @see android.content.Context
     */
    public abstract void start(Context context,AnswerSetCallback asCallback);

    /**
     * Add an Answer Set Program option for sexecution
     * @param options
     */
    public void addOption(String options){
        this.options = options;
    }

    /**
     *  Add an Answer Set Program input
     * @param rawInput
     */
    public void addRawInput(String rawInput){
        this.program = rawInput;
    }


    /**
     * Abstract method called from an {@link OutputReceiver} when a result is notify
     * @param aspServiceOut
     */
    abstract protected void receive(String aspServiceOut);


}
