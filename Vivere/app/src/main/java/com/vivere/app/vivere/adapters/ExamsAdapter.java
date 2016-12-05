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

import com.vivere.app.vivere.Fragments.AppointmentsFragment;
import com.vivere.app.vivere.Fragments.ExamsFragment;
import com.vivere.app.vivere.R;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.services.DeleteExam;

import java.util.ArrayList;

/**
 * Created by kyria_000 on 24/11/2016.
 */

public class ExamsAdapter extends ArrayAdapter {
    ArrayList<Exam> data = new ArrayList<>();
    ExamsFragment examsFragment;
    Context examActivity;

    public ExamsAdapter(Context context, Fragment homeFragment, int resource){
        super(context,resource);
        this.examsFragment = (ExamsFragment) homeFragment;
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
                final Handler handler = new Handler();
                if(data.size()!=0) {
                    // Delete from databases as well
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Exam a = data.get(position);
                            DeleteExam delEx = new DeleteExam();
                            delEx.execute(a.getId()+"");
                            System.out.println(a);
                            examsFragment.db.deleteExam(a.getId());
                            data.remove(position);
                            examsFragment.updateAdapter(position);
                        }
                    }, 300);
                }
            }
        });
        row.setOnClickListener(new ExamsAdapter.OnItemClickListener(position));
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
            examsFragment.onItemClick(mPosition);
        }
    }

    public void clearData(){
        for(int i=0;i<data.size();i++){
            data.remove(i);
        }
        //examActivity.updateAdapter();
    }
}
