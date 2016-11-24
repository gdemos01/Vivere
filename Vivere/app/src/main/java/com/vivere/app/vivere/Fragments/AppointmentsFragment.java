package com.vivere.app.vivere.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.adapters.AppointmentsAdapter;
import com.vivere.app.vivere.addAppointment;
import com.vivere.app.vivere.models.Appointment;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 11/11/2016.
 */

public class AppointmentsFragment extends Fragment {

    private View view;
    private ListView appList;
    private ArrayList<Appointment> appointments= new ArrayList<>();
    private AppointmentsAdapter appAdapter;
    private FloatingActionButton appActionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null)
        {
            view = inflater.inflate(R.layout.tab_fragment_appointments, container, false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        appActionBtn = (FloatingActionButton) view.findViewById(R.id.appointmentsActionBtn);
        appAdapter = new AppointmentsAdapter(getActivity(),R.layout.appointments_item);
        appList = (ListView) view.findViewById(R.id.appointmentsListView);
        appList.setAdapter(appAdapter);

        /*Demo data - later retrieve from database*/
        Date d = new Date();
        Appointment app = new Appointment("Dr Strange","Giorgos",d,"Hurts");
        appointments.add(app);
        setListData(app);
        Appointment app2 = new Appointment("Dr Who","Kakos",d,"Hurts");
        appointments.add(app2);
        setListData(app2);

        appActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addAppointment.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void updateAdapter(int pos) {

        appointments.remove(pos);
        appAdapter.notifyDataSetChanged(); //update adapter
    }

    public void setListData(Appointment app){
        appAdapter.add(app);
    }
}
