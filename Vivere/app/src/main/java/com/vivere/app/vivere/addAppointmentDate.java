package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by georg on 29-Nov-16.
 */

public class addAppointmentDate extends AppCompatActivity {

    private TextView selectTime;
    private TextView cancel;
    private DatePicker datePicker;
    private String ms_selected;
    private String desc;
    public Calendar calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment_date);

        selectTime = (TextView) findViewById(R.id.selectTime);
        cancel = (TextView) findViewById(R.id.cancelFromDate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        Intent intent = getIntent();
        ms_selected = intent.getExtras().getString("msusername");
        desc = intent.getExtras().getString("description");

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        datePicker.setMinDate(System.currentTimeMillis()-1000);
        datePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                calendar = new GregorianCalendar(year, month, day);
            }
        });

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
                if(calendar==null){
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth();
                    int year = datePicker.getYear();
                    System.out.println("haha "+day+" "+month+" "+year);
                    calendar = new GregorianCalendar(year, month, day);
                }
                Intent intent = new Intent(addAppointmentDate.this,addAppointmentTime.class);
                intent.putExtra("msusername",ms_selected);
                intent.putExtra("date",calendar.getTimeInMillis());
                intent.putExtra("description",desc);
                startActivity(intent);
            }
        });


    }
}
