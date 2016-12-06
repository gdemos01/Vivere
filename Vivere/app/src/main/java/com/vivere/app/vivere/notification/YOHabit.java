package com.vivere.app.vivere.notification;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.vivere.app.vivere.MainActivity;
import com.vivere.app.vivere.R;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Habit;

/**
 * Created by kyria_000 on 6/12/2016.
 */

public class YOHabit extends Activity {

    private DatabaseHelper db;

    public YOHabit() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(this);

        setContentView(R.layout.yes_habit);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 4000);

        String habit_name = getIntent().getExtras().getString("habit_name");

        Habit habit = db.getHabit(habit_name, "john");
        habit.setDaysdone(habit.getDaysdone() + 1);
        habit.setDaystogo(habit.getDaystogo() - 1);

        db.addHabit(habit);
        db.close();

    }
}
