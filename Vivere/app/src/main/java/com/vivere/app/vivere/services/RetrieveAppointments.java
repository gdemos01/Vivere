package com.vivere.app.vivere.services;

import android.os.AsyncTask;

import com.vivere.app.vivere.Fragments.AppointmentsFragment;
import com.vivere.app.vivere.MainActivity;
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

/**
 * Created by georg on 14-Nov-16.
 * This class retrieves appointments from the remote server
 */

public class RetrieveAppointments extends AsyncTask<String,Void,String>{

    String JSON_STRING;
    String json_url;
    DatabaseHelper dbH;

    @Override
    protected void onPreExecute(){
        json_url = "http://35.160.125.84/rest/getPatientsApointments.php";
        dbH = MainActivity.mydb;
    }

    @Override
    protected String doInBackground(String... args) {
        String username = args[0];

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
                    URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
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
                    Appointment appointment = new Appointment();
                    JSONObject jo = jsonArray.getJSONObject(count);
                    appointment.setPatient(jo.getString("pusername"));
                    appointment.setDoctor(jo.getString("msusername"));
                    appointment.setDate(Timestamp.valueOf(jo.getString("date")));
                    appointment.setDescription(jo.getString("description"));
                    dbH.addAppointment(appointment);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /*
        JSONObject jsonObject;
        JSONArray jsonArray;
        if(json_string==null){
           // Toast.makeText(getApplicationContext(),"No jsason",Toast.LENGTH_SHORT).show();
        }else{
            try {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    channel_id = JO.getString("channel_id");
                    Rank = JO.getInt("Rank");
                    Popular p = new Popular();
                    p.setChannel_id(channel_id);
                    p.setRank(Rank);
                    count++;
                    InitialActivity.tempDatabase.addPopularChannel(p);
                    retrieveChannel(channel_id);
                    // for(int za = 0; za<10;za++) System.out.println("ADDING CHANNEL NO -> "+count);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         */
    }
}
