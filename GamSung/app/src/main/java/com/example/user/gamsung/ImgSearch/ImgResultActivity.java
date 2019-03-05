package com.example.user.gamsung.ImgSearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.gamsung.MainActivity;
import com.example.user.gamsung.R;
import com.example.user.gamsung.network.ApiValue;
import com.example.user.gamsung.network.response.ImageSearchResult;
import com.example.user.gamsung.network.response.JoinResult;
import com.example.user.gamsung.vo.ImageSearchVO;

import static com.example.user.gamsung.login.LoginActivity.real_user_id;

public class ImgResultActivity extends AppCompatActivity {
    SharedPreferences mPref;
    private ImgListAdapter imgListAdapter;
    private RecyclerView recyclerView;
    String base_img,location;
    Bitmap decodedBitmap;
    LinearLayout search_window,search_result;
    ImageView input_img,research;
    private View.OnClickListener itemActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("v.getTag = ",String.valueOf(v.getTag()));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(v.getTag())));
            startActivity(intent);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_result);
        recyclerView = findViewById(R.id.img_result_list);
        input_img = findViewById(R.id.input_img);
        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        search_window = findViewById(R.id.search_window);
        search_result = findViewById(R.id.search_result);
        research = findViewById(R.id.research);
        research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImgResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        imgListAdapter = new ImgListAdapter(getApplicationContext(),itemActivityListener);
        recyclerView.setAdapter(imgListAdapter);
        setImage();
        input_img.setImageBitmap(decodedBitmap);
        base_img = mPref.getString("image", "0");
        location = mPref.getString("location", "0");
        Log.d("base_img", base_img);
        Log.d("location", location);
        server();
    }

    public void setImage(){

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        base_img = mPref.getString("image", "0");
        byte[] decodedByteArray = Base64.decode(base_img, Base64.NO_WRAP);
        decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
        decodedBitmap = decodedBitmap.copy(Bitmap.Config.ARGB_8888, true);
    }
    @Override
    public void onResume() {
        super.onResume();

        imgListAdapter.notifyDataSetChanged();
    }

    public void server() {

        ImageRequestTask requestTask = new ImageRequestTask(new ImageRequestTask.ImageRequestTaskHandler() {
            @Override
            public void onSuccessAppAsyncTask(ImageSearchResult result) {
                if(result.imageinfo != null && result.imageinfo.size() > 0) {
                    search_window.setBackgroundColor(getResources().getColor(R.color.Color_white));
                    imgListAdapter.setImgData(result.imageinfo);
                    imgListAdapter.notifyDataSetChanged();
                    search_result.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    imgListAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(getApplicationContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(getApplicationContext(), "사용자가 해당 작업을 중지하였습니다.", Toast.LENGTH_LONG);
            }

        });


        requestTask.execute(ApiValue.API_IMAGE_SEARCH,real_user_id, base_img, location);


    }

}
