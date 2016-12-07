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
import com.vivere.app.vivere.adapters.ExamAvailableHoursAdapter;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.notification.NOHabit;
import com.vivere.app.vivere.notification.NotificationPublisher;
import com.vivere.app.vivere.notification.YOHabit;
import com.vivere.app.vivere.services.InsertAppointment;
import com.vivere.app.vivere.services.InsertExam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kyria_000 on 1/12/2016.
 */

public class addExamTime extends AppCompatActivity {

    private TextView cancel;
    private TextView add;
    private ListView hours;
    private ArrayList<String> avHours = new ArrayList<>();
    private ExamAvailableHoursAdapter avAdapter;
    private int selectedTime;
    private String type;
    private String date;


    private String ms_selected;
    private String description;
    private long appMilliTime;
    public static ArrayList<Timestamp> reservedTimes;
    public List list;
    private DatabaseHelper db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exam_time);

        List list = Arrays.asList(getResources().getStringArray(R.array.available_hours));
        avHours.addAll(list);
        avAdapter = new ExamAvailableHoursAdapter(this, R.layout.hours_item);
        hours = (ListView) findViewById(R.id.exam_hoursList);
        hours.setAdapter(avAdapter);
        setListData();

        add = (TextView) findViewById(R.id.add_exam);
        cancel = (TextView) findViewById(R.id.exam_cancelFromTime);
        db = new DatabaseHelper(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addExamTime.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = getIntent();
                ms_selected = inte.getExtras().getString("msusername");
                type = inte.getExtras().getString("type");
                appMilliTime = inte.getExtras().getLong("date");

                String parts[] = avHours.get(selectedTime).split(":");
                long addTime = (long) (Integer.parseInt(parts[0]) * 60 * 60 * 1000);
                long addMin = (long) (Integer.parseInt(parts[1]) * 60 * 1000);
                appMilliTime += addTime;
                appMilliTime += addMin;

                Timestamp t = new Timestamp(appMilliTime);

                Exam exam = new Exam();
                exam.setUsername("john");
                exam.setMsusername(ms_selected);//??
                exam.setTimestamp(t);
                exam.setAdvice(" ");
                exam.setResults(" ");
                exam.setType(type);

                InsertExam insertExam = new InsertExam();
                insertExam.execute("john", ms_selected, type, t.toString());
                db.addExam(exam);

                //Schedule Notifications

                //Notification for the exam a day before
                long onedaybefore = exam.getTimestamp().getTime() - (long) (24 * 60 * 60 * 1000);
                long onehourbefore = exam.getTimestamp().getTime() - (long) (24 * 60 * 60 * 1000);

                int randomid = (int) (Math.random() * 2000000000);
                scheduleNotification(getNotification(exam.getType() + " Exam",
                        "You have an exam with Dr. " + exam.getMsusername() + " in 24 hours."),
                        onedaybefore, 1, 500 + randomid, false);

                //Notification for the exam an hour before
                randomid = (int) (Math.random() * 2000000000);
                scheduleNotification(getNotification(exam.getType() + " Exam",
                        "You have an exam with Dr. " + exam.getMsusername() + " in 1 hour."),
                        onehourbefore, 1, 500 + randomid, false);

                Intent intent = new Intent(addExamTime.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onItemClick(int pos) {
        avAdapter.setSelectedItem(pos);
        avAdapter.notifyDataSetChanged();
        selectedTime = avAdapter.getSelectedItem();
        // Do something on item click
        //Intent intent = new Intent(getActivity(), viewAppointment.class);
        //intent.putExtra("Doctor",appointments.get(pos).getDoctorName());
        //intent.putExtra("date",appointments.get(pos).getDate().toString());
        //intent.putExtra("desc",appointments.get(pos).getDescription());
        //startActivity(intent);
    }

    public void setListData() {
        for (int i = 0; i < avHours.size(); i++) {
            avAdapter.add(avHours.get(i));
        }
    }


    /**
     * A specific notification creator by a specific title and context given and curret action for
     * the yes/no responses.
     *
     * @param title
     * @param content
     * @return
     */

    public Notification getNotification(String title, String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(content);
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