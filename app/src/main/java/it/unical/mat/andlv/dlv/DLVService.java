package it.unical.mat.andlv.dlv;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;

import it.unical.mat.andlv.base.ASPService;

/**
 * Created by Dario Campisano on 25/03/2015.
 */

/**
 * <p>DLVService extends {@link it.unical.mat.andlv.base.ASPService} and contains specific method for a DLV Solver execution.</p>
 *
 * @see java.io.File
 * @see java.io.FileOutputStream
 * @see android.app.IntentService
 */


public class DLVService extends ASPService {

    //load the static library that contains DLV code compiled for arm processors
    /*static{
        System.loadLibrary("dlvJNI");
    }*/
    /**
     * Default Constructor {@link it.unical.mat.andlv.base.ASPService}
     */

    public DLVService() {super();}

    /** Call dlvMain native function in separate worker thread
     * @param program appropriate String containing a DLV program
     * @param options appropriate String containing the options for DLV program
     * @return String result computed
     */
    @Override
    protected String handleActionSolve(String program, String options) {
        Log.i("DlvSevice", "Launch service");
        String filename = "dlvfile";//temporary file name
        File file = new File(this.getFilesDir(), filename);//create the temporary file

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(program.getBytes());//writes program String on File file
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //String result = dlvMain(options + " " + file.getAbsolutePath());//call to DLV. Returns String DLV result
        String result = "RES";
        return result;//return a String containing output result
    }

    /**
     * Native function for DLV invocation
     * @param filePath the path of a temporary file storing DLV program
     * @return String result computed from DLV
     */
    //sprivate native String dlvMain(String filePath);

    /**
     * Close DLVService and kill the process corresponding.
     * @see android.app.IntentService
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DlvSevice", "DlvService killed");
        android.os.Process.killProcess(android.os.Process.myPid());//kill the process corresponding to this DLVService
    }
}

