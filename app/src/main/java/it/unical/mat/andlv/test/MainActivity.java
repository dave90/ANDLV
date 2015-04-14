package it.unical.mat.andlv.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.base.AnswerSet;
import it.unical.mat.andlv.base.AnswerSetCallback;
import it.unical.mat.andlv.base.AnswerSets;
import it.unical.mat.andlv.dlv.DLVHandler;
import it.unical.mat.dlv.R;

/**
 * <p>Test MainActivity for andlv </p>
 */
public class MainActivity extends Activity implements AnswerSetCallback{

    ASPHandler handler;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = new TextView(this);
        setContentView(R.layout.activity_main);
        handler = new DLVHandler();

        handler.addRawInput(BurningCalories.program);

        //handler.addRawInput(BurningCalories.program_part1);

        //handler.addRawInput(BurningCalories.program_part2);

        /*handler.addRawInput(Edb.TRUE_FACT);*/

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String file=dir.getAbsolutePath()+"/11449_true_detected_activity.lp";
        handler.addFileInput(file);

        handler.addOption("-filter=activity_to_do");

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
       List<AnswerSet> answerSetList=answerSets.getAnswerSetsList();
       text.setText(answerSetList.get(0).getAnswerSet());
       setContentView(text);
       /* for(AnswerSet answerSet:answerSetList){
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
