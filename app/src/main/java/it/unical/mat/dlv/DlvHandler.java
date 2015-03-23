package it.unical.mat.dlv;

import android.content.Context;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public class DlvHandler extends ASPHandler{

    /**
     * @param ASPSolver Answer set program solver used
     * @param context
     */
    public DlvHandler(ASPSolverService ASPSolver, Context context) {
        super(ASPSolver, context);
    }

    /**
     *
     * @return
     */
    @Override
    protected String generateInputProgram() {
        return null; //TODO
    }

    /**
     *
     * @return
     */
    @Override
    protected String generateInputOptions() {
        return null; //TODO
    }

    /**
     *
     * @param outputToParse
     * @return
     */
    @Override
    protected String parseResult(String outputToParse) {
        return null; //TODO
    }


}
