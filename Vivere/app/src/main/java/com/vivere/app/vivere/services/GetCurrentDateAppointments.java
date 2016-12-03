package com.vivere.app.vivere.services;

import android.os.AsyncTask;
import android.text.format.Time;

import com.vivere.app.vivere.MainActivity;
import com.vivere.app.vivere.addAppointmentTime;
import com.vivere.app.vivere.db.DatabaseHelper;
import com.vivere.app.vivere.models.Appointment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Giorgos on 03-Dec-16.
 */

public class GetCurrentDateAppointments  extends AsyncTask<String,Void,String> {

    String JSON_STRING;
    String json_url;

    @Override
    protected void onPreExecute(){
        json_url = "http://35.160.125.84/rest/getSpecificDateAppointments.php";
    }

    @Override
    protected String doInBackground(String... args) {
        String date_started = args[0];
        String date_finished = args[1];

        try {
            //Opening a connection to the server
            URL url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Passing username to the request
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data_string =
                    URLEncoder.encode("date_started","UTF-8")+"="+URLEncoder.encode(date_started,"UTF-8")
                    +"&"+URLEncoder.encode("date_finished","UTF-8")+"="+URLEncoder.encode(date_finished,"UTF-8");
            bufferedWriter.write(data_string);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            //Receiving JSON answer to our request
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while((JSON_STRING= bufferedReader.readLine())!=null){
                stringBuilder.append(JSON_STRING+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        System.out.println(result);
        JSONObject jsonObject;
        JSONArray jsonArray;
        if(result==null){
            System.out.println("No JSON returned");
        }else{
            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count =0;
                while(count<jsonArray.length()){
                    JSONObject jo = jsonArray.getJSONObject(count);
                    Timestamp t = Timestamp.valueOf(jo.getString("date"));
                    addAppointmentTime.reservedTimes.add(t);
                    System.out.println("haha "+t);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
