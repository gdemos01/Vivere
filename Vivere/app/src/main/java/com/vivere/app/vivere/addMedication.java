package com.vivere.app.vivere;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Medication;
import com.vivere.app.vivere.notification.NotificationPublisher;
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

                //Schedule Notifications

                for (int i = 0; i < medic.getDose(); i++) {
                    int randomid = (int) (Math.random() * 2000000000);
                    scheduleNotification(getNotification("Medication Reminder", medic.getName(),
                            "Did you take your meds?"), 0, 60, 200 + randomid, true);
                }


                Intent intent = new Intent(addMedication.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public Notification getNotification(String title, String content, String subtext) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSubText(subtext);
        builder.setSmallIcon(R.drawable.vivere_logo_bw2);
        return builder.build();
    }

    public void scheduleNotification(Notification notification, long delay, int timeInterval, int notificationID, boolean isScheduled) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, notificationID);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //boolean isScheduled = true; //turn it to false for DEBUG
        if (!isScheduled) {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        } else {
            switch (timeInterval) {
                case 15: {
                    //15 min
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                            AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
                    break;
                }
                case 30: {
                    //30 min
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
                            AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);
                    break;
                }
                case 60: {
                    //60 min
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + (AlarmManager.INTERVAL_HOUR * 8),
                            AlarmManager.INTERVAL_HOUR * 8, pendingIntent);
                    break;
                }
                default: {//for a whole day
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY,
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                    break;
                }
            }


        }
    }

}
