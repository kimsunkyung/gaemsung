package com.example.user.gamsung.Mypage;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.gamsung.network.HttpRequest;
import com.example.user.gamsung.network.response.MypageInfoResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MypageInfoRequestTask extends AsyncTask<String, Integer, MypageInfoResult> {


    private MypageInfoRequestTask.MypageInfoRequestTaskHandler handler;


    public interface MypageInfoRequestTaskHandler{
        public void onSuccessAppAsyncTask(MypageInfoResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public MypageInfoRequestTask(MypageInfoRequestTask.MypageInfoRequestTaskHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MypageInfoResult doInBackground(String... strings) {
        String path = strings[0];
        MypageInfoResult result  = null;

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "GET",null);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, MypageInfoResult.class);

            Log.d("result = ", String.valueOf(result));
            Log.d("http", "str > " + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(MypageInfoResult mypageInfoResult) {
        super.onPostExecute(mypageInfoResult);


        if(mypageInfoResult != null){
            handler.onSuccessAppAsyncTask(mypageInfoResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
