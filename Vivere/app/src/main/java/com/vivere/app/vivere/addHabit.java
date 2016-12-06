package com.vivere.app.vivere;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Habit;
import com.vivere.app.vivere.notification.NOHabit;
import com.vivere.app.vivere.notification.NotificationPublisher;
import com.vivere.app.vivere.notification.YOHabit;

import java.sql.Timestamp;

/**
 * Created by Giorgos on 05-Dec-16.
 */

public class addHabit extends AppCompatActivity {

    private EditText addHab;
    private RadioGroup radioGroup;
    private RadioButton radioSelected;
    private TextView letsDoThis;
    private DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        radioGroup = (RadioGroup) findViewById(R.id.habitRadio);
        letsDoThis = (TextView) findViewById(R.id.letsDoThisBtn);
        addHab = (EditText) findViewById(R.id.addHabitEditText);
        db = new DatabaseHelper(this);

        addHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHab.setText("");
                addHab.setTextColor(Color.parseColor("#000000"));
            }
        });

        letsDoThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioChoice = radioGroup.getCheckedRadioButtonId();
                radioSelected = (RadioButton) findViewById(radioChoice);
                Habit habit = new Habit();
                habit.setDaysdone(0);
                habit.setDaystogo(66);
                habit.setHname(addHab.getText().toString());
                habit.setType(radioSelected.getText().toString());
                habit.setUsername("john");
                Timestamp t = new Timestamp(System.currentTimeMillis());
                habit.setTimestamp(t);
                habit.setLastupdated(t);
                db.addHabit(habit);
                scheduleNotification(getNotificationYesNo(habit.getHname(),
                        "Have you been loyal to your goal today?", habit.getHname()), 5000, 1); //once a day
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(addHabit.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 300);
            }
        });
    }

    /**
     * A specific notification creator by a specific title and context given and curret action for
     * the yes/no responses.
     *
     * @param title
     * @param content
     * @return
     */
    public Notification getNotificationYesNo(String title, String content, String habitName) {
        Notification.Builder builder = new Notification.Builder(this);


        //For YES reply button
        Intent notificationIntent = new Intent(this, YOHabit.class);
        notificationIntent.putExtra("habit_name", habitName);
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
        Intent notificationIntent2 = new Intent(this, NOHabit.class);
        notificationIntent2.putExtra("habit_name", habitName);
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
        builder.setSmallIcon(R.drawable.vivere_logo_bw2)
                .setContentTitle(title)
                .setContentText(content)
                .addAction(action)
                .addAction(action2);

        // Add as notification
        //NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify(22, builder.build());
        //manager.notify(22, newMessageNotification);


        return builder.build();
    }

    public void scheduleNotification(Notification notification, int delay, int timeInterval) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1111);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        boolean isScheduled = true; //turn it to false for DEBUG
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
                            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HOUR,
                            AlarmManager.INTERVAL_HOUR, pendingIntent);
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
