package com.example.user.gamsung.ImgSearch;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.gamsung.login.JoinAsyncTask;
import com.example.user.gamsung.network.HttpRequest;
import com.example.user.gamsung.network.response.ImageSearchResult;
import com.example.user.gamsung.network.response.JoinResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageRequestTask extends AsyncTask<String, Integer, ImageSearchResult> {


    private ImageRequestTaskHandler handler;


    public interface ImageRequestTaskHandler{
        public void onSuccessAppAsyncTask(ImageSearchResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public ImageRequestTask(ImageRequestTask.ImageRequestTaskHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ImageSearchResult doInBackground(String... strings) {
        String path = strings[0];
        int userid = Integer.parseInt(strings[1]);
        String image = strings[2];
        String location = strings[3];

        ImageSearchResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userid);
        params.put("image", image);
        params.put("location",location);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, ImageSearchResult.class);

            Log.d("result = ", String.valueOf(result));
            Log.d("http", "str > " + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(ImageSearchResult serverSuccessCheckResult) {
        super.onPostExecute(serverSuccessCheckResult);


        if(serverSuccessCheckResult != null){
            handler.onSuccessAppAsyncTask(serverSuccessCheckResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
