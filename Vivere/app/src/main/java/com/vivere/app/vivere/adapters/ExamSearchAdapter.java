package com.vivere.app.vivere.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.addExam;
import com.vivere.app.vivere.models.MedicalSpecialist;

import java.util.ArrayList;

/**
 * Created by kyria_000 on 1/12/2016.
 */

public class ExamSearchAdapter extends ArrayAdapter {

    ArrayList<MedicalSpecialist> data = new ArrayList<MedicalSpecialist>();
    addExam addExam;
    int searchSize;

    public ExamSearchAdapter(Context context, int resource){
        super(context,resource);
        this.addExam= (addExam) context;
    }

    public void add(MedicalSpecialist ms){
        super.add(ms);
        data.add(ms);
    }

    public void setSearchSize(int s){
        this.searchSize = s;
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public MedicalSpecialist getItem(int position){
        return data.get(position);
    }


    public class msHolder{
        ImageView drImage;
        TextView drName;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        final ExamSearchAdapter.msHolder mHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.ms_search_item,parent,false);
            mHolder = new ExamSearchAdapter.msHolder();
            mHolder.drImage = (ImageView) row.findViewById(R.id.imgDoctorSearch);
            mHolder.drName = (TextView) row.findViewById(R.id.tvDoctorNameSearch);
            row.setTag(mHolder);
        }else{
            mHolder = (ExamSearchAdapter.msHolder) row.getTag();
        }

        mHolder.drName.setText("Dr "+data.get(position).getSurname()+" "+data.get(position).getName());
        row.setOnClickListener(new ExamSearchAdapter.OnItemClickListener(position));
        return  row;
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            arg0.setBackgroundColor(Color.parseColor("#A6D7D5"));
            addExam.onItemClick(mPosition);
        }
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
        addExam.updateAdapter();
    }
}
