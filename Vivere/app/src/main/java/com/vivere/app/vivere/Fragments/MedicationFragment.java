package com.vivere.app.vivere.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.adapters.MedicAdapter;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.models.Medication;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class MedicationFragment extends Fragment {
    private View view;
    private ListView medicList;
    private ArrayList<Medication> medications= new ArrayList<>();
    private MedicAdapter medicAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null)
        {
            view = inflater.inflate(R.layout.tab_fragment_medication, container, false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        medicAdapter = new MedicAdapter(getActivity(),R.layout.medication_item);
        medicList = (ListView) view.findViewById(R.id.medicationListView);
        medicList.setAdapter(medicAdapter);

        /*Demo data - later retrieve from database*/
        Medication m = new Medication();
        m.setUsername("john");
        m.setDose(3);
        m.setDuration(7);
        m.setFrequency("2W");
        m.setLastupdated(Date.valueOf("2016-12-11"));
        m.setName("Panadol");
        m.setTimestaken(2);

        medications.add(m);
        setListData(m);

        Medication m2 = new Medication();
        m2.setUsername("bill");
        m2.setDose(3);
        m2.setDuration(7);
        m2.setFrequency("2W");
        m2.setLastupdated(Date.valueOf("2016-11-11"));
        m2.setName("Nurofen");
        m2.setTimestaken(2);

        medications.add(m2);
        setListData(m2);

        return view;
    }

    public void updateAdapter(int pos) {

        medications.remove(pos);
        medicAdapter.notifyDataSetChanged(); //update adapter
    }

    public void setListData(Medication medication){
        medicAdapter.add(medication);
    }
}
