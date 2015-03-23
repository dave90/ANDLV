package it.unical.mat.dlv;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Dario Campisano on 23/03/2015.
 */

public class DlvService extends ASPSolverService {

    static{
        System.loadLibrary("dlvJNI");
    }

    @Override
    String handleActionSolve(String program, String option) {
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

        String result = dlvMain(option + " " + file.getAbsolutePath());

        return result;
    }

    private native String dlvMain(String filePath);
}
