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
import com.vivere.app.vivere.adapters.MedicAdapter;
import com.vivere.app.vivere.addMedication;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.models.Medication;
import com.vivere.app.vivere.services.DeleteExam;
import com.vivere.app.vivere.services.DeleteMedication;
import com.vivere.app.vivere.services.RetrieveExams;
import com.vivere.app.vivere.services.RetrieveMedication;
import com.vivere.app.vivere.viewExam;
import com.vivere.app.vivere.viewMedication;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class MedicationFragment extends Fragment {
    public DatabaseHelper db;
    private View view;
    private ListView medicList;
    private ArrayList<Medication> medications = new ArrayList<>();
    private MedicAdapter medicAdapter;
    private FloatingActionButton medicActionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.tab_fragment_medication, container, false);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        medicActionBtn = (FloatingActionButton) view.findViewById(R.id.medicationActionBtn);
        medicAdapter = new MedicAdapter(getActivity(), MedicationFragment.this, R.layout.medication_item);
        medicList = (ListView) view.findViewById(R.id.medicationListView);
        medicList.setAdapter(medicAdapter);

        db = new DatabaseHelper(getContext());

        /*Demo data - later retrieve from database*/
//        Medication m = new Medication();
//        m.setUsername("john");
//        m.setDose(3);
//        m.setDuration(7);
//        m.setFrequency("2W");
//        m.setLastupdated(Date.valueOf("2016-12-11"));
//        m.setName("Panadol");
//        m.setTimestaken(2);
//
//        medications.add(m);
//        setListData(m);
//
//        Medication m2 = new Medication();
//        m2.setUsername("bill");
//        m2.setDose(3);
//        m2.setDuration(7);
//        m2.setFrequency("2W");
//        m2.setLastupdated(Date.valueOf("2016-11-11"));
//        m2.setName("Nurofen");
//        m2.setTimestaken(2);
//
//        medications.add(m2);
//        setListData(m2);

        RetrieveMedication retrieveMedication = new RetrieveMedication();
        retrieveMedication.execute(db.getPatient("john").getUsername());

        medications = new ArrayList<Medication>();
        ArrayList<Medication> tmpmeds = db.getMediations();
        System.out.println(tmpmeds);

        Timestamp today = new Timestamp(System.currentTimeMillis());

        int count = 0;
        while (count < tmpmeds.size()) {
            Medication med = tmpmeds.get(count);

            String parts[] = med.getFrequency().split(":");
//            if (med.getTimestaken() == (Integer.parseInt(parts[0])
//                    * med.getDuration() * calculateTotalTimes(med.getFrequency()))) {
//                //delete from remote
//                Medication medd = tmpmeds.get(count);
//                DeleteMedication delMed = new DeleteMedication();
//                delMed.execute(medd.getUsername(), medd.getName());
//
//                //delete from local
//                db.deleteMedication(medd.getName());
//
//            } else {
                //show medication in list
                medications.add(med);
                setListData(med);
            //}
            count++;
        }


        medicActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addMedication.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void updateAdapter(int pos) {

        medications.remove(pos);
        medicAdapter.notifyDataSetChanged(); //update adapter
    }

    public void onItemClick(int pos) {
        // Do something on item click
        Intent intent = new Intent(getActivity(), viewMedication.class);
        intent.putExtra("name", medications.get(pos).getName());
        intent.putExtra("duration", medications.get(pos).getDuration()+"");
        intent.putExtra("frequency", medications.get(pos).getFrequency());
        intent.putExtra("dose", medications.get(pos).getDose()+"");
        intent.putExtra("timestaken", medications.get(pos).getTimestaken()+"");
        startActivity(intent);
    }

    public void setListData(Medication medication) {
        medicAdapter.add(medication);
    }

    public int calculateTotalTimes(String freq) {

        //note that the duration describes the number of times that have to take it
        int days = 0;
        String parts[] = freq.split(":");
        days = Integer.parseInt(parts[0]);

        if (parts[1].compareTo("D") == 0) {
            days = Integer.parseInt(parts[0]) * 7;
        } else if (parts[1].compareTo("W") == 0) {
            days = Integer.parseInt(parts[0]);
        }

        return days;
    }
}
