package com.vivere.app.vivere;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vivere.app.vivere.adapters.ExamSearchAdapter;

/**
 * Created by kyria_000 on 5/12/2016.
 */

public class addMedication extends AppCompatActivity {

    private TextView cancel;
    private TextView add;
    private EditText med_name;
    private EditText med_dur;
    private EditText med_freq;
    private EditText med_dose;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medication);

        cancel = (TextView) findViewById(R.id.medic_cancel);
        add = (TextView) findViewById(R.id.medic_add);
        med_name = (EditText) findViewById(R.id.medication_name);
        med_dur = (EditText) findViewById(R.id.medication_duration);
        med_freq = (EditText) findViewById(R.id.medication_frequency);
        med_dose = (EditText) findViewById(R.id.medication_dose);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new medication
            }
        });
    }

}
