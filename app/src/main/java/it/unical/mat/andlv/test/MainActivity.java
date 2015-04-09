package it.unical.mat.andlv.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.base.AnswerSetCallback;
import it.unical.mat.andlv.base.AnswerSets;
import it.unical.mat.andlv.dlv.DLVHandler;
import it.unical.mat.dlv.R;

/**
 * <p>Test MainActivity for andlv </p>
 */
public class MainActivity extends Activity implements AnswerSetCallback{

    ASPHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new DLVHandler();
        handler.addRawInput(BurningCalories.program_part1);
        //ASPMapper.getInstance().registerClass(ActivityToDo.class);
        //handler.addOption("-pfilter=activity_to_do");
        //File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //String file=dir.getAbsolutePath()+"/100000_random_detected_activity.lp";
        //handler.addFileInput(file);
        handler.start(getApplicationContext(),this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callback(AnswerSets answerSets) {
       /*List<AnswerSet> answerSetList=answerSets.getAnswerSetsList();
        for(AnswerSet answerSet:answerSetList){
            Log.i("RESULT ",answerSet.getAnswerSet());
            try {
                for(Object obj:answerSet.getAnswerObjects())
                    Log.i("ATOM",obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
