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
import com.vivere.app.vivere.addExamDate;
import com.vivere.app.vivere.addExamTime;

/**
 * Created by kyria_000 on 1/12/2016.
 */

public class addExam extends AppCompatActivity {

    private Spinner dropdown;
    private TextView selectDate;
    private TextView cancel;
    private ExamSearchAdapter searchAdapter;
    private ListView listView;
    private EditText searchView;
    private EditText type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exam);

        type = (EditText) findViewById(R.id.exam_type) ;
        searchView = (EditText) findViewById(R.id.exam_msSearchView);
        dropdown = (Spinner) findViewById(R.id.exam_ms_Spinner);
        selectDate = (TextView) findViewById(R.id.exam_selectDate);
        cancel = (TextView) findViewById(R.id.exam_cancelFromMain);
        searchAdapter = new ExamSearchAdapter(this,R.layout.ms_search_item);
        listView = (ListView)findViewById(R.id.examAppListView);
        listView.setAdapter(searchAdapter);

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
                //Search
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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
                        Intent intent = new Intent(addExam.this,addExamDate.class);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void updateAdapter() { searchAdapter.notifyDataSetChanged(); //update adapter
    }

}
