package com.vivere.app.vivere.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivere.app.vivere.Fragments.HistoryFragment;
import com.vivere.app.vivere.R;
import com.vivere.app.vivere.models.Advice;

import java.util.ArrayList;

/**
 * Created by georg on 25-Nov-16.
 */

public class HistoryAdapter extends ArrayAdapter {
    ArrayList<Advice> data = new ArrayList<>();
    HistoryFragment histFragment;
    Context histActivity;

    public HistoryAdapter(Context context, HistoryFragment hist ,int resource){
        super(context,resource);
        this.histFragment = hist;
        this.histActivity = context;
    }

    public void add(Advice advice){
        super.add(advice);
        data.add(advice);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Advice getItem(int position){
        return data.get(position);
    }

    public class AdviceHolder{
        TextView advTitle;
        ImageView advButton;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        final AdviceHolder adviceHolder;
        if(row==null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.history_item,parent,false);
            adviceHolder = new AdviceHolder();
            adviceHolder.advTitle= (TextView) row.findViewById(R.id.tvAdvTitle);
            adviceHolder.advButton= (ImageView) row.findViewById(R.id.imgAdvItemButton);
            row.setTag(adviceHolder);
        }else{
            adviceHolder = (AdviceHolder) row.getTag();
        }
        /***
         * Add real data here including deletion button action
         */

        adviceHolder.advTitle.setText(data.get(position).getType() + " - " + data.get(position).getDate());

        adviceHolder.advButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the exam
            }
        });

        return row;
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
    }
}

