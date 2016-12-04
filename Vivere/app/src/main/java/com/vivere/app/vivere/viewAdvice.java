package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Giorgos on 29/11/2016.
 */

public class viewAdvice extends AppCompatActivity {

    private TextView type;
    private TextView doctor;
    private TextView date;
    private TextView results;
    private TextView advice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_advice);
        type = (TextView) findViewById(R.id.advViewType);
        doctor = (TextView) findViewById(R.id.advViewDoctorName);
        date = (TextView) findViewById(R.id.advViewDate);
        results = (TextView) findViewById(R.id.advViewResults);
        advice = (TextView) findViewById(R.id.advViewAdvice);

        Intent intent = getIntent();
        String t = intent.getExtras().getString("type");
        String doc = intent.getExtras().getString("doctor");
        String da = intent.getExtras().getString("date");
        String res = intent.getExtras().getString("results");
        String adv = intent.getExtras().getString("advice");

        type.setText(t);
        doctor.setText(doc);
        date.setText(da);
        results.setText(res);
        advice.setText(adv);
    }
}
