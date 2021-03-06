package com.vivere.app.vivere.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.addExamTime;

import java.util.ArrayList;

/**
 * Created by kyria_000 on 1/12/2016.
 */

public class ExamAvailableHoursAdapter extends ArrayAdapter {

    ArrayList<String> data = new ArrayList<>();
    addExamTime addTimeActivity;
    int selectedItem;

    public ExamAvailableHoursAdapter(Context context, int resource){
        super(context,resource);
        this.addTimeActivity = (addExamTime)  context;
        this.selectedItem = -1;
    }

    public void add(String app){
        super.add(app);
        data.add(app);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public String getItem(int position){
        return data.get(position);
    }

    public class timeHolder{
        TextView timeView;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        final ExamAvailableHoursAdapter.timeHolder tHolder;
        if(row==null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.hours_item,parent,false);
            tHolder = new ExamAvailableHoursAdapter.timeHolder();
            tHolder.timeView = (TextView) row.findViewById(R.id.tv_availHour);
            row.setTag(tHolder);
        }else{
            tHolder = (ExamAvailableHoursAdapter.timeHolder) row.getTag();
        }

        if(position==selectedItem){
            tHolder.timeView.setBackgroundColor(Color.parseColor("#A6D7D5"));
        }else{
            tHolder.timeView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        tHolder.timeView.setText(data.get(position));

        row.setOnClickListener(new ExamAvailableHoursAdapter.OnItemClickListener(position));
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
            addTimeActivity.onItemClick(mPosition);
        }
    }

    public void setSelectedItem(int sel){
        this.selectedItem = sel;
    }

    public int getSelectedItem(){return  this.selectedItem;}

}
