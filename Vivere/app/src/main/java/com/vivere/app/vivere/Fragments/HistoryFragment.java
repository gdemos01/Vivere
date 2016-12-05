package com.vivere.app.vivere.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.adapters.HistoryAdapter;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Advice;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.models.MedicalSpecialist;
import com.vivere.app.vivere.services.GetMedicalSpecialist;
import com.vivere.app.vivere.viewAdvice;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class HistoryFragment extends Fragment {

    private View view;
    private ListView advList;
    private ArrayList<Advice> history= new ArrayList<>();
    private HistoryAdapter histAdapter;
    private ArrayList<Appointment> appointments=new ArrayList<>();
    private ArrayList<Exam> exams = new ArrayList<>();
    public DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null)
        {
            view = inflater.inflate(R.layout.tab_fragment_history, container, false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        histAdapter = new HistoryAdapter( getActivity(),HistoryFragment.this,R.layout.history_item);
        advList = (ListView) view.findViewById(R.id.adviceListView);
        advList.setAdapter(histAdapter);
        db = new DatabaseHelper(getContext());

        appointments = db.getAppointments("john");
        exams = db.getExams();
        Timestamp today = new Timestamp(System.currentTimeMillis());

        int i =0;
        while(i<appointments.size()){
            Appointment app = appointments.get(i);
            if(app.getDate().before(today)){
                MedicalSpecialist ms = db.getMedicalSpecialist(app.getDoctor());
                if (ms == null) {
                    GetMedicalSpecialist getMedicalSpecialist = new GetMedicalSpecialist();
                    getMedicalSpecialist.execute(app.getDoctor());
                } else {
                    app.setDoctorName("Dr " + ms.getName() + " " + ms.getSurname());
                    Advice advice = new Advice();
                    advice.setPatient("john");
                    advice.setDate(app.getDate());
                    advice.setAdvice(app.getAdvice());
                    advice.setDoctor(app.getDoctor());
                    advice.setDoctorName(app.getDoctorName());
                    advice.setResults("-");
                    advice.setType("Appointment");
                    history.add(advice);
                    setListData(advice);
                }
                i++;
            }else{
                appointments.remove(i);
            }
        }

        i=0;
        while(i<exams.size()){
            Exam exam = exams.get(i);
            String doctorName="";
            if(exam.getTimestamp().before(today)){
                MedicalSpecialist ms = db.getMedicalSpecialist(exam.getMsusername());
                if (ms == null) {
                    GetMedicalSpecialist getMedicalSpecialist = new GetMedicalSpecialist();
                    getMedicalSpecialist.execute(exam.getMsusername());
                } else {
                    doctorName = "Dr " + ms.getName() + " " + ms.getSurname();
                    Advice advice = new Advice();
                    advice.setDoctor(exam.getMsusername());
                    advice.setType(exam.getType());
                    advice.setAdvice(exam.getAdvice());
                    advice.setResults(exam.getResults());
                    advice.setDate(exam.getTimestamp());
                    advice.setDoctorName(doctorName);
                    advice.setExamId(exam.getId());
                    advice.setPatient("john");
                    history.add(advice);
                    setListData(advice);
                }
                i++;
            }else{
                exams.remove(i);
            }
        }
        return view;

    }

    public void updateAdapter(int pos) {
        history.remove(pos);
        histAdapter.notifyDataSetChanged(); //update adapter
    }

    public void onItemClick(int pos){
        Advice advice = history.get(pos);
        Intent intent = new Intent(getActivity(), viewAdvice.class);
        intent.putExtra("type",advice.getType());
        intent.putExtra("doctor",advice.getDoctorName());
        intent.putExtra("date",advice.getDate().toString());
        intent.putExtra("results",advice.getResults());
        intent.putExtra("advice",advice.getAdvice());
        startActivity(intent);
    }

    public void setListData(Advice app){
        histAdapter.add(app);
    }
}
