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
import com.vivere.app.vivere.adapters.HabitAdapter;
import com.vivere.app.vivere.addHabit;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Habit;
import com.vivere.app.vivere.viewHabits;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Giorgos on 11/11/2016.
 */

public class HabitsFragment extends Fragment {

    View view;
    private ListView habList;
    private ArrayList<Habit> habits= new ArrayList<>();
    private HabitAdapter habAdapter;
    private FloatingActionButton habActionBtn;
    public DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null)
        {
            view = inflater.inflate(R.layout.tab_fragment_habits, container, false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        db = new DatabaseHelper(getContext());
        habActionBtn = (FloatingActionButton) view.findViewById(R.id.habitsActionBtn);
        habAdapter = new HabitAdapter(getActivity(),HabitsFragment.this,R.layout.habit_item);
        habList = (ListView) view.findViewById(R.id.habitsListView);
        habList.setAdapter(habAdapter);


        habits = db.getHabits();
        for(int i=0;i<habits.size();i++){
            setListData(habits.get(i));
        }

        habActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addHabit.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public void updateAdapter(int pos) {
        habits.remove(pos);
        habAdapter.notifyDataSetChanged(); //update adapter
    }

    public void onItemClick(int pos){
        Intent intent = new Intent(getActivity(), viewHabits.class);
        Habit hab = habits.get(pos);
        intent.putExtra("habitName",hab.getHname());
        intent.putExtra("habitType",hab.getType());
        intent.putExtra("habitProg",hab.calculatePercentage());
        intent.putExtra("daysDone",hab.getDaysdone());
        intent.putExtra("daysToGo",hab.getDaystogo());
        intent.putExtra("date",hab.getTimestamp().getTime());
        startActivity(intent);
    }

    public void setListData(Habit app){
        habAdapter.add(app);
    }


}
