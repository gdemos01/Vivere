package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vivere.app.vivere.adapters.AvailableHoursAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Giorgos on 29-Nov-16.
 */

public class addAppointmentTime extends AppCompatActivity {

    private TextView cancel;
    private TextView make;
    private ListView hours;
    private ArrayList<String> avHours= new ArrayList<>();
    private AvailableHoursAdapter avAdapter;
    private int selectedTime;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment_time);

        List list = Arrays.asList(getResources().getStringArray(R.array.available_hours));
        avHours.addAll(list);
        avAdapter = new AvailableHoursAdapter(this,R.layout.hours_item);
        hours = (ListView) findViewById(R.id.hoursList);
        hours.setAdapter(avAdapter);
        setListData();

        cancel = (TextView) findViewById(R.id.cancelFromTime);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addAppointmentTime.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onItemClick(int pos){
        avAdapter.setSelectedItem(pos);
        avAdapter.notifyDataSetChanged();
        selectedTime = avAdapter.getSelectedItem();
        // Do something on item click
        //Intent intent = new Intent(getActivity(), viewAppointment.class);
        //intent.putExtra("Doctor",appointments.get(pos).getDoctorName());
        //intent.putExtra("date",appointments.get(pos).getDate().toString());
        //intent.putExtra("desc",appointments.get(pos).getDescription());
        //startActivity(intent);
    }

    public void setListData(){
        for(int i=0;i<avHours.size();i++){
            avAdapter.add(avHours.get(i));
        }
    }
}
