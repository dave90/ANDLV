package it.unical.mat.dlv;

import android.content.Context;

/**
 * Created by Brain At Work on 23/03/2015.
 */
public class DlvHandler extends ASPHandler{

    /**
     * @param ASPSolver Answer set program solver used
     * @param context
     */
    public DlvHandler(ASPSolverService ASPSolver, Context context) {
        super(ASPSolver, context);
    }

    @Override
    protected String generateInputProgram() {
        return null; //TODO
    }

    @Override
    protected String generateInputOptions() {
        return null; //TODO
    }

    @Override
    protected String parseResult(String outputToParse) {
        return null; //TODO
    }


}
