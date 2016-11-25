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
import com.vivere.app.vivere.models.Advice;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class HistoryFragment extends Fragment {

    private View view;
    private ListView advList;
    private ArrayList<Advice> history= new ArrayList<>();
    private HistoryAdapter histAdapter;

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

        /*Demo data - later retrieve from database*/
        Date d = new Date();
        d.setTime(System.currentTimeMillis());
        Advice adv = new Advice();
        adv.setDate(d);
        adv.setDoctor("Dr Strange");
        adv.setType("Prostate Exam");
        adv.setResults("Fine dude dont worry");
        adv.setAdvice("Drink More Water");

        history.add(adv);
        setListData(adv);

        return view;

    }

    public void updateAdapter(int pos) {
        history.remove(pos);
        histAdapter.notifyDataSetChanged(); //update adapter
    }

    public void onItemClick(int pos){
        // Do something on item click
        //Intent intent = new Intent(getActivity(), viewAppointment.class);
        //intent.putExtra("video_id",videos.get(mPosition).getId());
        //startActivity(intent);
    }

    public void setListData(Advice app){
        histAdapter.add(app);
    }
}
