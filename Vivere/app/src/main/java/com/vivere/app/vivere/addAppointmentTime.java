package com.vivere.app.vivere;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vivere.app.vivere.adapters.AvailableHoursAdapter;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.notification.NotificationPublisher;
import com.vivere.app.vivere.services.GetCurrentDateAppointments;
import com.vivere.app.vivere.services.InsertAppointment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Giorgos on 29-Nov-16.
 */

public class addAppointmentTime extends AppCompatActivity {

    private TextView cancel;
    private TextView make;
    private ListView hours;
    private ArrayList<String> avHours= new ArrayList<>();
    private AvailableHoursAdapter avAdapter;
    private int selectedTime;
    private String ms_selected;
    private String description;
    private long appMilliTime;
    public static ArrayList<Timestamp> reservedTimes;
    public List list;
    private DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment_time);

        make = (TextView)findViewById(R.id.makeApp);

        reservedTimes = new ArrayList<>();
        Intent intent = getIntent();
        ms_selected=intent.getExtras().getString("msusername");
        appMilliTime = intent.getExtras().getLong("date");
        description = intent.getExtras().getString("description");

        long oneDay = (long)(24*60*60*1000);
        Timestamp currentDay = new Timestamp(appMilliTime);
        Timestamp nextDay = new Timestamp(appMilliTime+oneDay);

        GetCurrentDateAppointments getCurDateApp = new GetCurrentDateAppointments();
        getCurDateApp.execute(currentDay.toString(),nextDay.toString());

        list = Arrays.asList(getResources().getStringArray(R.array.available_hours));
        db = new DatabaseHelper(this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean reserved;
                for(int i=0;i<list.size();i++){
                    reserved = false;
                    String t = list.get(i).toString();
                    for (int j=0;j<reservedTimes.size();j++){
                        if(reservedTimes.get(j).toString().contains(t)){
                            reserved = true;
                            break;
                        }
                    }
                    if(!reserved) avHours.add(t);
                }
                setListData();
            }

            public void setListData(){
                for(int i=0;i<avHours.size();i++){
                    avAdapter.add(avHours.get(i));
                }
            }

        },1000);

        avAdapter = new AvailableHoursAdapter(this,R.layout.hours_item);
        hours = (ListView) findViewById(R.id.hoursList);
        hours.setAdapter(avAdapter);

        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appointment app = new Appointment();
                app.setPatient("john");
                app.setDoctor(ms_selected);
                app.setDescription(description);
                String parts[] = avHours.get(selectedTime).split(":");
                long addTime = (long) (Integer.parseInt(parts[0])*60*60*1000);
                long addMin = (long) (Integer.parseInt(parts[1])*60*1000);
                appMilliTime+= addTime;
                appMilliTime+= addMin;
                Timestamp t = new Timestamp(appMilliTime);
                app.setDate(t);
                app.setAdvice(" ");
                InsertAppointment insertAppointment = new InsertAppointment();
                insertAppointment.execute("john",ms_selected,t.toString(),description);
                db.addAppointment(app);

                /**
                 * Scheduling notifications for 1 day before and 1 hour before
                 */
                long oneDayBefore = app.getDate().getTime() - (long)(24*60*60*1000);
                long oneHourBefore = app.getDate().getTime() -(long)(60*60*1000);
                int randomid = (int) (Math.random() * 2000000000);
                int randomid2 = (int) (Math.random() * 2000000000);
                int randomid3 = (int) (Math.random() * 2000000000);

                scheduleNotification(getNotification("Appointment Reminder",app.getDescription(),
                        app.getDate().toString()),5000,15,false,800+randomid);
                scheduleNotification(getNotification("Appointment Reminder",app.getDescription(),
                        app.getDate().toString()),oneDayBefore,15,false,800+randomid2);
                scheduleNotification(getNotification("Appointment Reminder",app.getDescription(),
                        app.getDate().toString()),oneHourBefore,15,false,800+randomid3);

                Intent intent = new Intent(addAppointmentTime.this,MainActivity.class);
                startActivity(intent);
            }
        });

        cancel = (TextView) findViewById(R.id.cancelFromTime);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addAppointmentTime.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onItemClick(int pos){
        avAdapter.setSelectedItem(pos);
        avAdapter.notifyDataSetChanged();
        selectedTime = avAdapter.getSelectedItem();
    }

    public void scheduleNotification(Notification notification, long delay,
                                     int timeInterval,boolean isScheduled,int notificationID) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, notificationID);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
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

    public Notification getNotification(String title, String content, String sub) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setContentText(content);
        builder.setSubText(sub);
        builder.setSmallIcon(R.drawable.vivere_logo_bw2);
        return builder.build();
    }

}
