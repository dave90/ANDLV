package it.unical.mat.andlv;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>ASPHandler is a generic class. It provides generic methods for an Answer Set Program execution handling</>
 */
public abstract class ASPHandler {


    protected String options;//stores program options
    protected String program;//store an entire ASP program

    public ASPHandler(){
        this.options = "";
        this.program = "";
    }

    /** Execute the Answer Set Program and get AnswerSetCallback implemented
     * @param asCallback
     * @param context
     */
    public abstract void start(Context context,AnswerSetCallback asCallback);

    /**
     * Add an Answer Set Program option for execution
     * @param options
     */
    public void addOption(String options){
        this.options = options;
    }

    /**
     *  Add an Answer Set Program row input
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
     * Create String from File
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
     * Receive an output to parse
     * @param outputToParse
     * @return ArrayList<AnswerSet> Contains Answer sets generated from an Answer Set Program String output
     */
    abstract protected ArrayList<AnswerSet> parseResult(String outputToParse);

    /**
     * Receive output.It should call parseResult(String outputToParse) and finally the method AnswerSetCallback.callback(AnswerSet answerSet)
     * @param aspServiceOut
     */
    abstract protected void receive(String aspServiceOut);


}
