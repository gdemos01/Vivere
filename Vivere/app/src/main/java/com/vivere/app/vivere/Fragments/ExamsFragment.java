package com.vivere.app.vivere.Fragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.adapters.ExamsAdapter;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.addExam;
import com.vivere.app.vivere.models.MedicalSpecialist;
import com.vivere.app.vivere.services.GetMedicalSpecialist;
import com.vivere.app.vivere.services.RetrieveAppointments;
import com.vivere.app.vivere.services.RetrieveExams;
import com.vivere.app.vivere.viewExam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Giorgos on 11/11/2016.
 */

public class ExamsFragment extends Fragment {

    public DatabaseHelper db;
    private View view;
    private ListView examList;
    private ArrayList<Exam> allexams = new ArrayList<>();
    private ExamsAdapter examAdapter;
    private FloatingActionButton examActionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.tab_fragment_exams, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        examActionBtn = (FloatingActionButton) view.findViewById(R.id.examsActionBtn);
        examAdapter = new ExamsAdapter(getActivity(), ExamsFragment.this, R.layout.exams_item);
        examList = (ListView) view.findViewById(R.id.examsListView);
        examList.setAdapter(examAdapter);

        db = new DatabaseHelper(getContext());

//        /*Demo data - later retrieve from database*/
//        Exam exam = new Exam();
//        exam.setId(1234);
//        exam.setAdvice("You will die.");
//        exam.setResults("Death");
//        exam.setTimestamp(Timestamp.valueOf("2013-10-10 10:49:29"));
//        exam.setType("Checkup");
//        exam.setUsername("john");
//        exam.setMsusername("drdoom");
//
//        exams.add(exam);
//        setListData(exam);
//
//        Exam exam2 = new Exam();
//        exam2.setId(2335);
//        exam2.setAdvice("You will die.");
//        exam2.setResults("Death");
//        exam2.setTimestamp(Timestamp.valueOf("2013-10-09 10:49:29"));
//        exam2.setType("Analysis");
//        exam2.setUsername("andrew");
//        exam2.setMsusername("drkelly");

//        exams.add(exam2);
//        setListData(exam2);

        RetrieveExams retrieveExams = new RetrieveExams();
        retrieveExams.execute(db.getPatient("john").getUsername());

        //GetMedicalSpecialist getMedicalSpecialist = new GetMedicalSpecialist();
        //getMedicalSpecialist.execute("strange");

        allexams = new ArrayList<Exam>();
        ArrayList<Exam> tmpexs = db.getExams();
        System.out.println(tmpexs);
        Timestamp today = new Timestamp(System.currentTimeMillis());

        int count = 0;
        while (count < tmpexs.size()) {
            Exam ex = tmpexs.get(count);
            if (ex.getTimestamp().after(today)) {
                allexams.add(ex);
                setListData(ex);
            }
            count++;
        }

        examActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addExam.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void updateAdapter(int pos) {

        allexams.remove(pos);
        examAdapter.notifyDataSetChanged(); //update adapter
    }

    public void onItemClick(int pos){
        // Do something on item click
        Intent intent = new Intent(getActivity(), viewExam.class);
        intent.putExtra("Doctor",allexams.get(pos).getMsusername());
        intent.putExtra("date",allexams.get(pos).getTimestamp().toString());
        intent.putExtra("type",allexams.get(pos).getType());
        intent.putExtra("results",allexams.get(pos).getResults());
        intent.putExtra("advice",allexams.get(pos).getAdvice());
        startActivity(intent);
    }

    public void setListData(Exam exam) {
        examAdapter.add(exam);
    }
}
