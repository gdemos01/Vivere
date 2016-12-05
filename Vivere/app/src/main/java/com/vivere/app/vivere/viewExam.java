package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by kyria_000 on 5/12/2016.
 */

public class viewExam extends AppCompatActivity {

    private TextView doctorName;
    private TextView vdate;
    private TextView type;
    private TextView results;
    private TextView advice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exam);

        doctorName = (TextView) findViewById(R.id.tvDoctorNameExamView);
        vdate = (TextView) findViewById(R.id.exViewDate);
        type = (TextView) findViewById(R.id.exViewType);
        results = (TextView) findViewById(R.id.exViewResults);
        advice = (TextView) findViewById(R.id.exViewAdvice);


        Intent i = getIntent();
        String sdoctor = i.getExtras().getString("Doctor");
        String sdate = i.getExtras().getString("date");
        String stype = i.getExtras().getString("type");
        String sresults = i.getExtras().getString("results");
        String sadvice = i.getExtras().getString("advice");

        doctorName.setText(sdoctor);
        vdate.setText(sdate);
        type.setText(stype);
        results.setText(sresults);
        advice.setText(sadvice);
    }
}
