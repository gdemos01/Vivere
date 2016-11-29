package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vivere.app.vivere.Fragments.AppointmentsFragment;

/**
 * Created by georg on 29-Nov-16.
 */

public class addAppointmentDate extends AppCompatActivity {

    private TextView selectTime;
    private TextView cancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment_date);

        selectTime = (TextView) findViewById(R.id.selectTime);
        cancel = (TextView) findViewById(R.id.cancelFromDate);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addAppointmentDate.this,MainActivity.class);
                startActivity(intent);
            }
        });

        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addAppointmentDate.this,addAppointmentTime.class);
                startActivity(intent);
            }
        });
    }
}
