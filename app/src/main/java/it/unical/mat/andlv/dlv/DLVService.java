package it.unical.mat.andlv.dlv;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;

import it.unical.mat.andlv.base.ASPService;

/**
 */

/**
 * <p>DLVService extends {@link it.unical.mat.andlv.base.ASPService} and contains specific method for a DLV Solver execution.</p>
 *
 * @see java.io.File
 * @see java.io.FileOutputStream
 * @see android.app.IntentService
 */


public class DLVService extends ASPService {

    private static final String FILENAME = "tmp_program";


    //load the static library that contains DLV code compiled for arm processors
    static{
        System.loadLibrary("dlvJNI");
    }
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
        File file = new File(this.getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(program.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = dlvMain(options + " " + file.getAbsolutePath());
        return result;
    }

    /**
     * Native function for DLV invocation
     * @param filePath the path of a temporary file storing DLV program
     * @return String result computed from DLV
     */
    private native String dlvMain(String filePath);

    /**
     * Close DLVService and kill the process corresponding.
     * @see android.app.IntentService
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());//kill the process corresponding to this DLVService
    }
}

