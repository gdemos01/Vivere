package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Giorgos on 25-Nov-16.
 */

public class viewAppointment extends AppCompatActivity {

    private TextView doctorName;
    private TextView dateView;
    private TextView descView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_appointment);

        doctorName = (TextView) findViewById(R.id.tvDoctorNameAppView);
        dateView = (TextView) findViewById(R.id.appViewDate);
        descView = (TextView) findViewById(R.id.appViewDesc);

        Intent i = getIntent();
        String doctor = i.getExtras().getString("Doctor");
        String date = i.getExtras().getString("date");
        String desc = i.getExtras().getString("desc");

        doctorName.setText(doctor);
        dateView.setText(date);
        descView.setText(desc);
    }
}
