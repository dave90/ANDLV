package it.unical.mat.dlv;

import java.io.File;
import java.io.FileOutputStream;

/**
 * <p>DlvService extends {@link it.unical.mat.dlv.ASPSolverService} and contains a specific Dlv ASP Solver.</p>
 * @see java.io.File
 * @see java.io.FileOutputStream
 */

public class DlvService extends ASPSolverService {

    static{
        System.loadLibrary("dlvJNI");
    }

    /**
     * @param program
     * @param options
     * @return
     */
    @Override
    String handleActionSolve(String program, String options) {
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
}
