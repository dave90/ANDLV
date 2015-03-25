package it.unical.mat.dlv;

import android.content.Context;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public class DlvHandler extends ASPHandler{

    /**
     * @param context
     */
    public DlvHandler(Context context) { super(context); }

    /**
     *
     * @param outputToParse
     * @return
     */
    @Override
    protected String parseResult(String outputToParse) {
        return "risultato"; //TODO
    }

    /**
     *
     * @return Instance of ASPSolverService chosen
     */
    @Override
    protected ASPSolverService createASPSolverService() {
        return new DlvService();
    }

}
