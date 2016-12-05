package com.vivere.app.vivere;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Habit;

import java.sql.Timestamp;

/**
 * Created by Giorgos on 05-Dec-16.
 */

public class addHabit extends AppCompatActivity {

    private EditText addHab;
    private RadioGroup radioGroup;
    private RadioButton radioSelected;
    private TextView letsDoThis;
    private DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        radioGroup = (RadioGroup) findViewById(R.id.habitRadio);
        letsDoThis = (TextView) findViewById(R.id.letsDoThisBtn);
        addHab = (EditText) findViewById(R.id.addHabitEditText);
        db = new DatabaseHelper(this);

        addHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHab.setText("");
                addHab.setTextColor(Color.parseColor("#000000"));
            }
        });

        letsDoThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioChoice = radioGroup.getCheckedRadioButtonId();
                radioSelected = (RadioButton)findViewById(radioChoice);
                Habit habit = new Habit();
                habit.setDaysdone(0);
                habit.setDaystogo(66);
                habit.setHname(addHab.getText().toString());
                habit.setType(radioSelected.getText().toString());
                habit.setUsername("john");
                Timestamp t = new Timestamp(System.currentTimeMillis());
                habit.setTimestamp(t);
                habit.setLastupdated(t);
                db.addHabit(habit);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(addHabit.this,MainActivity.class);
                        startActivity(intent);
                    }
                },300);
            }
        });
    }
}
