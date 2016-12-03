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
import com.vivere.app.vivere.addAppointment;
import com.vivere.app.vivere.models.MedicalSpecialist;

import java.util.ArrayList;

/**
 * Created by Giorgos on 30-Nov-16.
 */

public class SearchAdapter extends ArrayAdapter {

    ArrayList<MedicalSpecialist> data = new ArrayList<MedicalSpecialist>();
    addAppointment addAppointment;
    int searchSize;

    public SearchAdapter(Context context, int resource){
        super(context,resource);
        this.addAppointment= (addAppointment) context;
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
        final msHolder mHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.ms_search_item,parent,false);
            mHolder = new msHolder();
            mHolder.drImage = (ImageView) row.findViewById(R.id.imgDoctorSearch);
            mHolder.drName = (TextView) row.findViewById(R.id.tvDoctorNameSearch);
            row.setTag(mHolder);
        }else{
            mHolder = (msHolder) row.getTag();
        }

        mHolder.drName.setText("Dr "+data.get(position).getSurname()+" "+data.get(position).getName());
        row.setOnClickListener(new SearchAdapter.OnItemClickListener(position));
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
            addAppointment.onItemClick(mPosition);
        }
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
        addAppointment.updateAdapter();
    }
}
