package com.vivere.app.vivere;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.RemoteInput;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.SQLiteDatabase;


import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.utils.*;

import com.vivere.app.vivere.adapters.myPagerAdapter;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.models.Habit;
import com.vivere.app.vivere.models.Illness;
import com.vivere.app.vivere.models.MedicalSpecialist;
import com.vivere.app.vivere.models.Medication;
import com.vivere.app.vivere.models.Patient;

import java.sql.Date;
import java.sql.Timestamp;

import static com.vivere.app.vivere.utils.VivereNotification.*;

public class MainActivity extends AppCompatActivity {

    //Database declaration
    public static DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_today_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_local_pharmacy_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_invert_colors_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_assignment_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_sentiment_satisfied_white_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final myPagerAdapter adapter =
                new myPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //initialize the database
        mydb = new DatabaseHelper(this);
        SQLiteDatabase db = mydb.getWritableDatabase();

        //TODO Test any method of the database here using also the toString() method
//        MedicalSpecialist ms = new MedicalSpecialist();
//        ms.setType("Chemist");
//        ms.setTelephone(99999915);
//        ms.setAddress("Adamou Christofi, 15, Aglantzia, 2109 Nicosia");
//        ms.setSpeciality("Chemist");
//        ms.setSurname("Smith");
//        ms.setName("Jack");
//        ms.setPassword("hittheroadjack");
//        ms.setMsusername("jack");
//
//        mydb.addMedicalSpecialist(ms);
//        System.out.println(mydb.getMedicalSpecialist("jack"));
//
//        Exam m = new Exam();
//        m.setMsusername("jack");
//        m.setUsername("bob");
//        m.setType("Analysis");
//        m.setAdvice("You won''t die yet.");
//        m.setTimestamp(Timestamp.valueOf("2016-09-09 10:09:34"));
//        m.setId(123);
//        m.setResults("The results are here.");
//
//        mydb.addExam(m);
//        System.out.println(mydb.getExam(123));

        //addNotification();
    }

    public void addNotificationWReply() {
        //Create Notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.exams_flaticon);
        builder.setContentTitle("Notifications Example");
        builder.setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Key for the string that's delivered in the action's intent.
        final String KEY_TEXT_REPLY = "key_text_reply";
        String replyLabel = getResources().getString(R.string.app_name);
        android.app.RemoteInput remoteInput = new android.app.RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        // Create the reply action and add the remote input.
        Notification.Action action =
                new Notification.Action.Builder(R.drawable.ic_account_circle_black_24dp,
                        getString(R.string.notification_label), contentIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        // Build the notification and add the action.
        // Build the notification and add the action.
        Notification newMessageNotification =
                new Notification.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_account_circle_black_24dp)
                        .setContentTitle("TITLE")
                        .setContentText("Content Text")
                        .addAction(action)
                        .build();

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify(22, builder.build());
        manager.notify(22, newMessageNotification);
    }

    public void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.exams_flaticon);
        builder.setContentTitle("Notifications Example");
        builder.setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);


        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
