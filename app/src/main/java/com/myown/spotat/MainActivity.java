package com.myown.spotat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.myown.spotat.utils.AppGlobals;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    Toolbar toolBar = null;
    Context context = null;
    AppGlobals appGlobals = null;
    boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();
        setClickListener();
    }

    private void initializeWidgets() {
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        context = getApplicationContext();
        appGlobals = AppGlobals.getInstance(context);
        debugMode = appGlobals.debugMode;
    }

    private void setClickListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
