package com.vivere.app.vivere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Giorgos on 07/12/2016.
 * Spash screen for the application
 */

public class splashActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },2000);
    }
}
