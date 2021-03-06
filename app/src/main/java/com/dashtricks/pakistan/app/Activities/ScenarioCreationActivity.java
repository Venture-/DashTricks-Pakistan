package com.dashtricks.pakistan.app.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import com.dashtricks.pakistan.app.R;

public class ScenarioCreationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_creation);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
//        np.setMaxValue(100);    // set maximum val
//        np.setMinValue(1);      // set minimum val
//        np.setValue(5);         // currently defaulting to 50 new fridges
//        np.setEnabled(false);   // not allowing user input
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scenario_creation, menu);
        return true;
    }

    /**
     * Open WebView
     */
    public void visualize(View view) {
        Intent i = new Intent(this, VisualizationActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
