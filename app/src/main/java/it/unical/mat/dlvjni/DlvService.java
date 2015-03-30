package it.unical.mat.dlvjni;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;

import it.unical.mat.andlv.ASPService;

/**
 * Created by Dario Campisano on 25/03/2015.
 */

/**
 * <p>DlvService extends {@link it.unical.mat.andlv.ASPService} and contains a specific Dlv ASP Solver.</p>
 *
 * @see java.io.File
 * @see java.io.FileOutputStream
 */


public class DlvService extends ASPService {


    static{
        System.loadLibrary("dlvJNI");
    }

    public DlvService() {

    }

    /** Call dlvMain native function in a worker thread
     * @param program
     * @param options
     * @return
     */
    @Override
    protected String handleActionSolve(String program, String options) {
        Log.i("DlvSevice.handleAc[..]", "Launch service");
        String filename = "myfile";
        File file = new File(this.getFilesDir(), filename);


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
     * Native function for DLV execution
     * @param filePath
     * @return
     */
    private native String dlvMain(String filePath);

    /**
     * Close DlvService and kill the process.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DlvSevice.handleAc[..]", "DlvService killed");
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

