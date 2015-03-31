package it.unical.mat.andlv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.unical.mat.andlv.base.ASPHandler;
import it.unical.mat.andlv.base.AnswerSetCallback;
import it.unical.mat.andlv.dlv.DLVHandler;
import it.unical.mat.dlv.R;

/**
 * Created by Dario Campisano on 23/03/2015.
 * <p>Test MainActivity for andlv </>
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnswerSetCallback callb = new CallbackProva();
        ASPHandler DLVHand = new DLVHandler();
        DLVHand.addRowInput("a(1). b(X):-a(X).");
        DLVHand.start(getApplicationContext(),callb);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
