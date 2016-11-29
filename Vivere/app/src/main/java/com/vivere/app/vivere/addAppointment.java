package com.vivere.app.vivere;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.vivere.app.vivere.Fragments.AppointmentsFragment;

/**
 * Created by User on 24/11/2016.
 */

public class addAppointment extends AppCompatActivity {

    private Spinner dropdown;
    private TextView selectDate;
    private TextView cancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);

        dropdown = (Spinner) findViewById(R.id.appSpinner);
        selectDate = (TextView) findViewById(R.id.selectDate);
        cancel = (TextView) findViewById(R.id.cancelFromMain);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView msTv = (TextView) view;
                String msType = msTv.getText().toString();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

                selectDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(addAppointment.this,addAppointmentDate.class);
                        startActivity(intent);

                    }
                });

                /*
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                datePicker.init(2016, 12, 1, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth() + 1;
                        int year = datePicker.getYear();
                        System.out.println("haha "+day+" "+month+" "+year);
                    }
                });
                */

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
