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

import java.util.ArrayList;

/**
 * Created by kyria_000 on 24/11/2016.
 */

public class ExamsAdapter extends ArrayAdapter {
    ArrayList<Exam> data = new ArrayList<>();
    Context examActivity;

    public ExamsAdapter(Context context, int resource){
        super(context,resource);
        this.examActivity = context;
    }

    public void add(Exam exam){
        super.add(exam);
        data.add(exam);
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Exam getItem(int position){
        return data.get(position);
    }

    public class ExamHolder{
        TextView examTitle;
        ImageView examButton;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        ExamsAdapter.ExamHolder examHolder;
        if(row==null){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.exams_item,parent,false);
            examHolder = new ExamsAdapter.ExamHolder();
            examHolder.examTitle= (TextView) row.findViewById(R.id.tvExamTitle);
            examHolder.examButton= (ImageView) row.findViewById(R.id.imgExamItemButton);
            row.setTag(examHolder);
        }else{
            examHolder = (ExamsAdapter.ExamHolder) row.getTag();
        }
        /***
         * Add real data here including deletion button action
         */

        examHolder.examTitle.setText(data.get(position).getType() + " - " + data.get(position).getTimestamp());

        examHolder.examButton.setOnClickListener(new View.OnClickListener() {
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
        //examActivity.updateAdapter();
    }
}
