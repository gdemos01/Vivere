package com.vivere.app.vivere;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.vivere.app.vivere.Fragments.AppointmentsFragment;

/**
 * Created by Giorgos on 29-Nov-16.
 */

public class addAppointmentTime extends AppCompatActivity {

    private TextView cancel;
    private TextView make;
    private ListView hours;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment_time);

        hours = (ListView) findViewById(R.id.hoursList);
        cancel = (TextView) findViewById(R.id.cancelFromTime);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addAppointmentTime.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
