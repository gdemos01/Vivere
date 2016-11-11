package com.vivere.app.vivere.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.models.Appointment;

import java.util.ArrayList;

/**
 * Created by georg on 11-Nov-16.
 */

public class AppointmentsAdapter extends ArrayAdapter {
    ArrayList<Appointment> data = new ArrayList<>();
    Context appActivity;

    public AppointmentsAdapter(Context context, int resource){
        super(context,resource);
        this.appActivity = context;
    }

    public void add(Appointment app){
        super.add(app);
        data.add(app);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Appointment getItem(int position){
        return data.get(position);
    }

    public class AppointmentHolder{
        ImageView doctorImg;
        TextView doctorName;
        ImageView appButton;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        AppointmentHolder appHolder;
        if(row==null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.appointments_item,parent,false);
            appHolder = new AppointmentHolder();
            appHolder.doctorImg= (ImageView) row.findViewById(R.id.imgDoctor);
            appHolder.doctorName= (TextView) row.findViewById(R.id.tvDoctorName);
            appHolder.appButton= (ImageView) row.findViewById(R.id.imgAppItemButton);
            row.setTag(appHolder);
        }else{
            appHolder = (AppointmentHolder) row.getTag();
        }
        /***
         * Add real data here including deletion button action
         */

        appHolder.doctorName.setText(data.get(position).getDoctor());

        appHolder.appButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the appointment
            }
        });

        return row;
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
        //appActivity.updateAdapter();
    }

}
