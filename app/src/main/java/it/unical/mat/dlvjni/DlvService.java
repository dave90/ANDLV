package it.unical.mat.dlvjni;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;

import it.unical.mat.dlv.ASPService;

/**
 * Created by Dario Campisano on 25/03/2015.
 */

/**
 * <p>DlvService extends {@link it.unical.mat.dlv.ASPService} and contains a specific Dlv ASP Solver.</p>
 *
 * @see java.io.File
 * @see java.io.FileOutputStream
 */


public class DlvService extends ASPService {


    static{
        System.loadLibrary("dlvJNI");
    }


    /**
     * @param program
     * @param options
     * @return
     */
    @Override
    protected String handleActionSolve(String program, String options) {
        Log.i("info", "Launch service");
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
     *
     * @param filePath
     * @return
     */
    private native String dlvMain(String filePath);

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("info", "Service killed");
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

