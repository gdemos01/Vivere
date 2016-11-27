package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

        habitName.setText(i.getExtras().getString("habitName"));
        habitType.setText(i.getExtras().getString("habitType"));
        //donutProgress.setProgress();
        daysDone.setText(dd);
        daysToGo.setText(dtg);

    }

}
