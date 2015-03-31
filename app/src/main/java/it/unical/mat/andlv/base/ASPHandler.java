package it.unical.mat.andlv.base;

import android.content.Context;

import java.util.ArrayList;

import it.unical.mat.andlv.AnswerSet;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>ASPHandler is an Abstract class. It provides generic methods for an Answer Set Program execution handling.
 * Get an Answer Set Program and its options for the execution. Provide a public methos to start the Answer Set Program execution. </>
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
     * @param rowInput
     */
    public void addRowInput(String rowInput){
        this.program = rowInput;
    }

    /**
     *  Get Answer Set Program from filesystem
     * @param
     */
    /*public void addFileInput(String filePath){
        File programFromPath = new File(filePath);
        String aspProgram = fileToString(programFromPath);
        this.program = this.program.concat(aspProgram + "\n");
    }*/

    /**
     * Generate String from File
     * @param file
     * @return
     */
    /*private String fileToString(File file){
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
    }*/

    /**
     * Abstract method for result parsing and AnswerSets creation
     * @param outputToParse
     * @return ArrayList<AnswerSet> Contains {@link it.unical.mat.andlv.AnswerSet} objects generated from an Answer Set Program String output
     * @see it.unical.mat.andlv.AnswerSet
     * @see java.util.ArrayList
     */
    abstract protected ArrayList<AnswerSet> parseResult(String outputToParse);

    /**
     * Abstract method called from an {@link it.unical.mat.andlv.OutputReceiver} when a result is notify
     * @param aspServiceOut
     */
    abstract public void receive(String aspServiceOut);


}
