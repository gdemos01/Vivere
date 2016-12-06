package com.vivere.app.vivere.notification;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.vivere.app.vivere.R;

/**
 * Created by kyria_000 on 6/12/2016.
 */

public class NOHabit extends Activity {

    public NOHabit() {
        System.out.println("NO CLASS HELLO");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.no_habit);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 4000);

        System.out.println("NO CLASS HELLO");

    }
}
