package com.example.user.gamsung.login;

import android.os.AsyncTask;

import com.example.user.gamsung.network.HttpRequest;
import com.example.user.gamsung.network.response.JoinResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class JoinAsyncTask extends AsyncTask<String, Integer, JoinResult> {


    private JoinResultHandler handler;


    public interface JoinResultHandler{
        public void onSuccessAppAsyncTask(JoinResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public JoinAsyncTask(JoinResultHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JoinResult doInBackground(String... strings) {
        String path = strings[0];
        String userid = strings[1];
        String password = strings[2];
        int userage = Integer.parseInt(strings[4]);
        String usergender = strings[3];

        JoinResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userid);
        params.put("pwd", password);
        params.put("age", userage);
        params.put("gender", usergender);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, JoinResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(JoinResult serverSuccessCheckResult) {
        super.onPostExecute(serverSuccessCheckResult);


        if(serverSuccessCheckResult != null){
            handler.onSuccessAppAsyncTask(serverSuccessCheckResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
