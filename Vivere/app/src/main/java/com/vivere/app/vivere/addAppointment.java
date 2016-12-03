package com.vivere.app.vivere;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.vivere.app.vivere.adapters.SearchAdapter;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.services.SearchMedicalSpecialist;

/**
 * Created by Giorgos on 24/11/2016.
 */

public class addAppointment extends AppCompatActivity {

    private Spinner dropdown;
    private TextView selectDate;
    private TextView cancel;
    public static SearchAdapter searchAdapter;
    private ListView listView;
    private EditText searchView;
    public SearchMedicalSpecialist sms ;
    private DatabaseHelper db;
    private String ms_selected;
    private EditText appDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);

        searchView = (EditText) findViewById(R.id.msSearchView);
        dropdown = (Spinner) findViewById(R.id.appSpinner);
        selectDate = (TextView) findViewById(R.id.selectDate);
        cancel = (TextView) findViewById(R.id.cancelFromMain);
        searchAdapter = new SearchAdapter(this,R.layout.ms_search_item);
        listView = (ListView)findViewById(R.id.addAppListView);
        listView.setAdapter(searchAdapter);
        db = new DatabaseHelper(this);
        ms_selected = null;
        appDesc = (EditText)findViewById(R.id.appDesc);


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setText("");
                searchView.setTextColor(Color.parseColor("#000000"));
            }
        });

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchAdapter.clearData();
                String str = charSequence.toString();
                    if(str.length()!=0) {
                        if (sms != null) {
                            sms.cancel(true);
                        }
                        sms = new SearchMedicalSpecialist();
                        sms.execute(str, dropdown.getSelectedItem().toString());
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                TextView msTv = (TextView) view;
                String msType = msTv.getText().toString();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

                selectDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String desc = appDesc.getText().toString();
                        Intent intent = new Intent(addAppointment.this,addAppointmentDate.class);
                        intent.putExtra("msusername",ms_selected);
                        intent.putExtra("description",desc);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onItemClick(int pos){
        db.addMedicalSpecialist(searchAdapter.getItem(pos));
        ms_selected = searchAdapter.getItem(pos).getMsusername();
    }

    public void updateAdapter() { searchAdapter.notifyDataSetChanged(); //update adapter
    }

}
