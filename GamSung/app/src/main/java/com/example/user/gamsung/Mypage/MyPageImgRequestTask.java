package com.example.user.gamsung.Mypage;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.gamsung.ImgSearch.ImageRequestTask;
import com.example.user.gamsung.network.HttpRequest;
import com.example.user.gamsung.network.response.ImageSearchResult;
import com.example.user.gamsung.network.response.MypageImgResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class MyPageImgRequestTask extends AsyncTask<String, Integer, MypageImgResult> {


    private MyPageImgRequestTask.MyPageImgRequestTaskHandler handler;


    public interface MyPageImgRequestTaskHandler{
        public void onSuccessAppAsyncTask(MypageImgResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public MyPageImgRequestTask(MyPageImgRequestTask.MyPageImgRequestTaskHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MypageImgResult doInBackground(String... strings) {
        String path = strings[0];
        MypageImgResult result  = null;

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "GET",null);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, MypageImgResult.class);

            Log.d("result = ", String.valueOf(result));
            Log.d("http", "str > " + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(MypageImgResult mypageImgResult) {
        super.onPostExecute(mypageImgResult);


        if(mypageImgResult != null){
            handler.onSuccessAppAsyncTask(mypageImgResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
