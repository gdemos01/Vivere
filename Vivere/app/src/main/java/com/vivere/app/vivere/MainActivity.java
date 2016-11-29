package com.vivere.app.vivere;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vivere.app.vivere.adapters.myPagerAdapter;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.models.Inheritance;
import com.vivere.app.vivere.models.Patient;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
                showNotificationWReply("Habit title", "The question goes here?");
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

        // Our Demo patient
        Patient patient = new Patient();
        patient.setUsername("john");
        patient.setName("John");
        patient.setSurname("Smith");
        patient.setAge(60);
        patient.setCoutnry("Cyprus");
        patient.setGender("M");
        patient.setNationality("Greek");
        patient.setPassword("1234");
        mydb.addPatient(patient);


        //TODO Test any method of the database here using also the toString() method

//        Inheritance ms = new Inheritance();
//
//        ms.setIname("prostate");
//        ms.setUsername("bob");
//
//        mydb.addInheritance(ms);
//        System.out.println(mydb.getInheritance("prostate", "bob"));
        //System.out.println(mydb.getAppointment("bob", "jack", Timestamp.valueOf("2016-11-29 23:02:14.297")));

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

    public void showNotificationWReply(String title, String content) {
        //Create Notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);

        //For YES reply button
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Key for the string that's delivered in the action's intent.
        final String KEY_TEXT_REPLY = "key_text_reply";
        String replyLabel = getResources().getString(R.string.notification_replylabel);
        android.app.RemoteInput remoteInput = new android.app.RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        // Create the reply action and add the remote input.
        Notification.Action action =
                new Notification.Action.Builder(R.drawable.ic_thumb_up_black_24dp,
                        getString(R.string.yes), contentIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        //For NO reply button
        Intent notificationIntent2 = new Intent(this, MainActivity.class);
        PendingIntent contentIntent2 = PendingIntent.getActivity(this, 0, notificationIntent2,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent2);

        // Create the reply action and add the remote input.
        Notification.Action action2 =
                new Notification.Action.Builder(R.drawable.ic_thumb_down_black_24dp,
                        getString(R.string.no), contentIntent2)
                        .addRemoteInput(remoteInput)
                        .build();

        // Build the notification and add the action.
        Notification newMessageNotification =
                new Notification.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.vivere_logo_bw2)
                        .setContentTitle(title)
                        .setContentText(content)
                        .addAction(action)
                        .addAction(action2)
                        .build();

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify(22, builder.build());
        manager.notify(22, newMessageNotification);
    }

    public void showNotification(String title, String content) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.vivere_logo_bw2);
        builder.setContentTitle(title);
        builder.setContentText(content);

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
