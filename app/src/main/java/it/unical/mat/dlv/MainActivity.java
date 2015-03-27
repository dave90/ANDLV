package it.unical.mat.dlv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Dario Campisano on 23/03/2015.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CallbackProva callb = new CallbackProva();
        DlvHandler dlvHandler = new DlvHandler(this);
        dlvHandler.addRowInput("a(1). b(X):-a(X).");
        dlvHandler.start(callb);
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
