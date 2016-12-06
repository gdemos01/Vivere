package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by kyria_000 on 6/12/2016.
 */

public class viewMedication extends AppCompatActivity {

    private TextView vname;
    private TextView vduration;
    private TextView vfrequency;
    private TextView vdose;
    private TextView vtimestaken;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_medication);

        vname = (TextView) findViewById(R.id.med_vname);
        vduration = (TextView) findViewById(R.id.med_vduration);
        vfrequency = (TextView) findViewById(R.id.med_vfrequency);
        vdose = (TextView) findViewById(R.id.med_vdose);
        vtimestaken = (TextView) findViewById(R.id.med_vtimestaken);


        Intent i = getIntent();
        String mname = i.getExtras().getString("name");
        String mduration = i.getExtras().getString("duration");
        String mfreq = i.getExtras().getString("frequency");
        String mdose = i.getExtras().getString("dose");
        String mtimes = i.getExtras().getString("timestaken");

        vname.setText(mname);
        vduration.setText(mduration);
        vfrequency.setText(mfreq);
        vdose.setText(mdose);
        vtimestaken.setText(mtimes);
    }
}