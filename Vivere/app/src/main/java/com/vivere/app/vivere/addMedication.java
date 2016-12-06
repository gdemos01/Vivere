package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Medication;
import com.vivere.app.vivere.services.InsertMedication;

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
    private DatabaseHelper db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medication);

        cancel = (TextView) findViewById(R.id.medic_cancel);
        add = (TextView) findViewById(R.id.medic_add);
        med_name = (EditText) findViewById(R.id.medication_name);
        med_dur = (EditText) findViewById(R.id.medication_duration);
        med_freq = (EditText) findViewById(R.id.medication_frequency);
        med_dose = (EditText) findViewById(R.id.medication_dose);

        db = new DatabaseHelper(this);

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
                Medication medic = new Medication();

                medic.setUsername("john");
                medic.setDose(Integer.parseInt(med_dose.getText().toString()));
                medic.setDuration(Integer.parseInt(med_dur.getText().toString()));
                medic.setName(med_name.getText().toString());
                medic.setFrequency(med_freq.getText().toString());
                medic.setTimestaken(0);

                InsertMedication insertMedication = new InsertMedication();
                insertMedication.execute(medic.getUsername(), medic.getName(), medic.getDuration()
                        + "", medic.getFrequency(), medic.getDose() + "", medic.getTimestaken() + "");

                db.addMedication(medic);

                Intent intent = new Intent(addMedication.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
