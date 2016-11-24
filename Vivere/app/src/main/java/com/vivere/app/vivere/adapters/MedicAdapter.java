package com.vivere.app.vivere.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivere.app.vivere.R;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.models.Medication;

import java.util.ArrayList;

/**
 * Created by kyria_000 on 24/11/2016.
 */

public class MedicAdapter  extends ArrayAdapter {
    ArrayList<Medication> data = new ArrayList<>();
    Context medicActivity;

    public MedicAdapter(Context context, int resource){
        super(context,resource);
        this.medicActivity = context;
    }

    public void add(Medication medication){
        super.add(medication);
        data.add(medication);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Medication getItem(int position){
        return data.get(position);
    }

    public class MedicHolder{
        TextView MedicTitle;
        ImageView MedicButton;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        MedicAdapter.MedicHolder medicHolder;
        if(row==null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.medication_item,parent,false);
            medicHolder = new MedicAdapter.MedicHolder();
            medicHolder.MedicTitle= (TextView) row.findViewById(R.id.tvMedicName);
            medicHolder.MedicButton= (ImageView) row.findViewById(R.id.imgMedicItemButton);
            row.setTag(medicHolder);
        }else{
            medicHolder = (MedicAdapter.MedicHolder) row.getTag();
        }
        /***
         * Add real data here including deletion button action
         */

        medicHolder.MedicTitle.setText(data.get(position).getName());

        medicHolder.MedicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete current medication item
            }
        });

        return row;
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
        //medicActivity.updateAdapter();
    }
}
