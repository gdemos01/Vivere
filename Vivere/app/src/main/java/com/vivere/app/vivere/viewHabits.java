package com.vivere.app.vivere;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by User on 27/11/2016.
 */

public class viewHabits extends AppCompatActivity {

    TextView habitName;
    TextView habitType;
    com.github.lzyzsd.circleprogress.DonutProgress donutProgress;
    TextView daysDone;
    TextView daysToGo;
    TextView date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_habits);

        habitName = (TextView) findViewById(R.id.habitTitle);
        habitType = (TextView) findViewById(R.id.habitType);
        donutProgress = (com.github.lzyzsd.circleprogress.DonutProgress)
                findViewById(R.id.viewHabitDoughtnut);
        daysDone = (TextView) findViewById(R.id.tvDaysDone);
        daysToGo = (TextView) findViewById(R.id.tvDaystoGo);
        date = (TextView) findViewById(R.id.tvDate);

        Intent i = getIntent();
        int dtg = i.getExtras().getInt("daysToGo");
        int dd = i.getExtras().getInt("daysDone");
        int per = (int)((float)dd/66*100);
        Timestamp t = new Timestamp(i.getExtras().getLong("date"));
        long daysToadd = ((long)dtg * 24 * 3600 * 1000+t.getTime());
        Date d = new Date();
        d.setTime(daysToadd);
        String[] parts = d.toString().split(" ");
        String da = parts[2] +" "+ parts[1];

        String habT = i.getExtras().getString("habitType");
        if(habT.contains("Gain")){
            habitType.setTextColor(Color.parseColor("#97CE68"));
        }

        habitName.setText(i.getExtras().getString("habitName"));
        habitType.setText(habT);
        donutProgress.setProgress(per);
        daysDone.setText(String.valueOf(dd));
        daysToGo.setText(String.valueOf(dtg));
        date.setText(da);

    }

}
