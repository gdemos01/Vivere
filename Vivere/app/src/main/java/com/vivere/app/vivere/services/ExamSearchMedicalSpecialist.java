package com.vivere.app.vivere.services;

import android.os.AsyncTask;

import com.vivere.app.vivere.addExam;
import com.vivere.app.vivere.models.Appointment;
import com.vivere.app.vivere.models.Exam;
import com.vivere.app.vivere.models.MedicalSpecialist;

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

/**
 * Created by kyria_000 on 6/12/2016.
 */

public class ExamSearchMedicalSpecialist extends AsyncTask<String,Void,String> {
    String JSON_STRING;
    String json_url;
    String name;
    String type;


    @Override
    protected void onPreExecute(){
        json_url = "http://35.160.125.84/rest/searchMedicalSpecialistByNameAndType.php";
    }

    @Override
    protected String doInBackground(String... args) {
        name = args[0];
        type = args[1];
        try {
            URL url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")
                    +"&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8");
            bufferedWriter.write(data_string);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while((JSON_STRING = buf.readLine())!=null){
                stringBuilder.append(JSON_STRING+"\n");
            }

            buf.close();
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
                    MedicalSpecialist ms = new MedicalSpecialist();
                    //Exam exam = new Exam();
                    JSONObject jo = jsonArray.getJSONObject(count);
                    ms.setMsusername(jo.getString("username"));
                    ms.setName(jo.getString("name"));
                    ms.setSurname(jo.getString("surname"));
                    ms.setSpeciality(jo.getString("speciality"));
                    ms.setAddress(jo.getString("address"));
                    ms.setTelephone(jo.getInt("telephone"));
                    ms.setType(jo.getString("type"));
                    addExam.searchAdapter.add(ms);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
