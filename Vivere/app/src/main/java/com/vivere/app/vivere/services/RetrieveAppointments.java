package com.vivere.app.vivere.services;

import android.os.AsyncTask;

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

/**
 * Created by georg on 14-Nov-16.
 * This class retrieves appointments from the remote server
 */

public class RetrieveAppointments extends AsyncTask<String,Void,String>{

    String JSON_STRING;
    String json_url;

    @Override
    protected void onPreExecute(){
        json_url = "http://35.160.125.84/rest/";
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
        //parse json here
    }
}
