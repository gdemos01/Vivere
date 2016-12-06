package com.vivere.app.vivere.notification;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.vivere.app.vivere.R;

/**
 * Created by kyria_000 on 6/12/2016.
 */

public class YOHabit extends Activity {

    public YOHabit() {
        System.out.println("YO CLASS HELLO");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.yes_habit);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 4000);

        System.out.println("YO CLASS HELLO");
        System.out.println("MESATZ " + getIntent().getExtras().getString("helloval"));


    }
}
