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
 * Created by kyria_000 on 6/12/2016.
 */

public class InsertMedication extends AsyncTask<String, Void, String> {

    String url_str;


    @Override
    protected void onPreExecute() {
        //Service URL
        url_str = "http://35.160.125.84/rest/insertMedication.php";
    }

    @Override
    protected String doInBackground(String... args) {

        String username = args[0];
        String name = args[1];
        String duration = args[2];
        String frequency = args[3];
        String dose = args[4];
        String timestaken = args[5];
        String outcome;

        try {
            URL url = new URL(url_str);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setDoOutput(true);
            OutputStream outputStream = httpUrlConnection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data_string = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                    URLEncoder.encode("duration", "UTF-8") + "=" + URLEncoder.encode(duration, "UTF-8") + "&" +
                    URLEncoder.encode("frequency", "UTF-8") + "=" + URLEncoder.encode(frequency, "UTF-8") + "&" +
                    URLEncoder.encode("dose", "UTF-8") + "=" + URLEncoder.encode(dose, "UTF-8") ;
            bufferedWriter.write(data_string);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((outcome = bufferedReader.readLine()) != null) {
                stringBuilder.append(outcome + "\n");
            }
            inputStream.close();
            httpUrlConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("Result: " + result);
        System.out.println("Medication inserted successfully.");
    }
}