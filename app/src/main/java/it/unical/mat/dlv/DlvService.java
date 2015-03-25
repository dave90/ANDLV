package it.unical.mat.dlv;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * <p>DlvService extends {@link it.unical.mat.dlv.ASPSolverService} and contains a specific Dlv ASP Solver.</p>
 * @see java.io.File
 * @see java.io.FileOutputStream
 */

public class DlvService extends ASPSolverService {

    /*static{
        System.loadLibrary("dlvJNI");
    }*/

    /**
     * @param program
     * @param options
     * @return
     */
    @Override
    String handleActionSolve(String program, String options) {
        Log.i("info","Launch service");
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
        //TODO call dlvMain correctly
        //String result = dlvMain(options + " " + file.getAbsolutePath());
        String result = "RISULTATO";
        Log.i("info", "Result = " + result);
        return result;
    }

    /**
     * Define istructions to do on onDestroy() call
     */
    @Override
    void onDestroyAction() {
        Log.i("info","Service killed");
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     *
     * @param filePath
     * @return
     */
    //private native String dlvMain(String filePath);
}
