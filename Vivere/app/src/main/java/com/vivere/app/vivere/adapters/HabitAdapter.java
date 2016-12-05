package com.vivere.app.vivere.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivere.app.vivere.Fragments.HabitsFragment;
import com.vivere.app.vivere.R;
import com.vivere.app.vivere.models.Habit;

import java.util.ArrayList;

/**
 * Created by User on 27/11/2016.
 */

public class HabitAdapter extends ArrayAdapter{

    ArrayList<Habit> data = new ArrayList<>();
    HabitsFragment habitsFragment;
    Context habitsActivity;

    public HabitAdapter(Context context, Fragment homeFragment, int resource){
        super(context,resource);
        this.habitsFragment = (HabitsFragment) homeFragment;
        this.habitsActivity = context;
    }

    public void add(Habit hab){
        super.add(hab);
        data.add(hab);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Habit getItem(int position){
        return data.get(position);
    }

    public class HabitHolder{
        com.github.lzyzsd.circleprogress.DonutProgress donutProgress;
        TextView txtHabit;
        ImageView btnHabit;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        final HabitHolder habitHolder;
        if(row==null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.habit_item,parent,false);
            habitHolder = new HabitHolder();
            habitHolder.donutProgress= (com.github.lzyzsd.circleprogress.DonutProgress)
                    row.findViewById(R.id.item_donut_progress);
            habitHolder.txtHabit= (TextView) row.findViewById(R.id.tvItemHabit);
            habitHolder.btnHabit= (ImageView) row.findViewById(R.id.imgItemHabitBtn);
            row.setTag(habitHolder);
        }else{
            habitHolder = (HabitHolder) row.getTag();
        }
        /***
         * Add real data here including deletion button action
         */

        habitHolder.donutProgress.setProgress(data.get(position).calculatePercentage());
        habitHolder.txtHabit.setText(data.get(position).getHname());
        habitHolder.btnHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                if(data.size()!=0) {
                    habitsFragment.db.deleteHabit(data.get(position).getHname());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            data.remove(position);
                            habitsFragment.updateAdapter(position);
                        }
                    }, 300);
                }
            }
        });
        row.setOnClickListener(new OnItemClickListener(position));
        return row;
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            habitsFragment.onItemClick(mPosition);
        }
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
        //appActivity.updateAdapter();
    }
}
