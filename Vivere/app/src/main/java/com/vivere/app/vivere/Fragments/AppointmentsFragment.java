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
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.models.MedicalSpecialist;
import com.vivere.app.vivere.services.GetMedicalSpecialist;
import com.vivere.app.vivere.services.RetrieveAppointments;
import com.vivere.app.vivere.viewAppointment;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class AppointmentsFragment extends Fragment {

    private View view;
    private ListView appList;
    private ArrayList<Appointment> appointments= new ArrayList<>();
    private AppointmentsAdapter appAdapter;
    private FloatingActionButton appActionBtn;
    public  DatabaseHelper db;

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
        appAdapter = new AppointmentsAdapter(getActivity(),AppointmentsFragment.this,R.layout.appointments_item);
        appList = (ListView) view.findViewById(R.id.appointmentsListView);
        appList.setAdapter(appAdapter);
        db = new DatabaseHelper(getContext());


        /**
         * This is the creation of appointments.
         * It should be moved to a splash screen or something
         * We should retrieve medical specialist for all the appoi
         */
        RetrieveAppointments retrieveAppointments = new RetrieveAppointments();
        retrieveAppointments.execute(db.getPatient("john").getUsername());

        //GetMedicalSpecialist getMedicalSpecialist = new GetMedicalSpecialist();
        //getMedicalSpecialist.execute("strange");

        appointments = db.getAppointments("john");
        Timestamp today = new Timestamp(System.currentTimeMillis());

        int count =0;
        while(count<appointments.size()){
            Appointment app = appointments.get(count);
            if(app.getDate().after(today)) {
                MedicalSpecialist ms = db.getMedicalSpecialist(app.getDoctor());
                if (ms == null) {
                    GetMedicalSpecialist getMedicalSpecialist = new GetMedicalSpecialist();
                    getMedicalSpecialist.execute(app.getDoctor());
                } else {
                    app.setDoctorName("Dr " + ms.getName() + " " + ms.getSurname());
                    System.out.println("haha "+"Dr " + ms.getName() + " " + ms.getSurname());
                    setListData(app);
                }
                count++;
            }else{
                appointments.remove(count);
            }
        }

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

    public void onItemClick(int pos){
        // Do something on item click
        Intent intent = new Intent(getActivity(), viewAppointment.class);
        intent.putExtra("Doctor",appointments.get(pos).getDoctorName());
        intent.putExtra("date",appointments.get(pos).getDate().toString());
        intent.putExtra("desc",appointments.get(pos).getDescription());
        startActivity(intent);
    }

    public void setListData(Appointment app){
        appAdapter.add(app);
    }
}
