package it.unical.mat.andlv.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.base.AnswerSet;
import it.unical.mat.andlv.base.AnswerSetCallback;
import it.unical.mat.andlv.base.AnswerSets;
import it.unical.mat.andlv.base.mapper.ASPMapper;
import it.unical.mat.andlv.dlv.DLVHandler;
import it.unical.mat.andlv.test.Person;
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
        handler.addRowInput("a(0,34).a(1,34). p(X,\"CIAO\"):-a(X,_).");
        handler.addOption("-pfilter=p");
        ASPMapper.getInstance().registerClass(Person.class);
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
        for(AnswerSet answerSet:answerSetList){
            Log.i("RESULT ",answerSet.getAnswerSet());
            try {
                for(Object obj:answerSet.getAnswerObjects())
                    System.out.println(obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
