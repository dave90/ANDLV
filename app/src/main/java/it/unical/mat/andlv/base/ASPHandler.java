package it.unical.mat.andlv.base;

import android.content.Context;

import java.util.Set;

import it.unical.mat.andlv.base.mapper.ASPMapper;

/**
 * <p>ASPHandler is an Abstract class. It provides generic methods for an Answer Set Program execution handling.
 * Get an Answer Set Program and its options for the execution. Provide a public methos to start the Answer Set Program execution. </p>
 */
public abstract class ASPHandler {

    protected StringBuilder options;//stores program options
    protected StringBuilder program;//store an entire ASP program

    public ASPHandler(){
        this.options = new StringBuilder();
        this.program = new StringBuilder();
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
        this.options.append(options);
    }

    public void addInput(Object inputObj){
        try{
            program.append(ASPMapper.getInstance().getString(inputObj)).append(".");
        }
        catch(Exception e){}
    }

    public void addInput(Set<Object> inputObjs){
        for(Object inputObj:inputObjs)
            addInput(inputObj);
    }

    /**
     *  Add an Answer Set Program input
     * @param rawInput
     */
    public void addRawInput(String rawInput){
        program.append(rawInput);
    }


    /**
     * Abstract method called from an {@link OutputReceiver} when a result is notify
     * @param aspServiceOut
     */
    abstract protected void receive(String aspServiceOut);


}
