package com.example.user.gamsung;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mainPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private ImageView tabHome,tabImg,tabMy,tabSetting;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        tabHome = findViewById(R.id.tab_home);
        tabImg = findViewById(R.id.tab_img);
        tabMy = findViewById(R.id.tab_my);
        tabSetting = findViewById(R.id.tab_settings);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        tabHome.setOnClickListener(this);
        tabImg.setOnClickListener(this);
        tabMy.setOnClickListener(this);
        tabSetting.setOnClickListener(this);

        tabHome.setSelected(true);

        mainViewPagerAdapter = new MainViewPagerAdapter(this.getSupportFragmentManager());

        mainPager = (ViewPager) findViewById(R.id.main_viewpager);
        mainPager.setOffscreenPageLimit(4);
        mainPager.setAdapter(mainViewPagerAdapter);
        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tabHome.setSelected(true);
                        tabImg.setSelected(false);
                        tabMy.setSelected(false);
                        tabSetting.setSelected(false);
                        break;

                    case 1:
                        tabHome.setSelected(false);
                        tabMy.setSelected(false);
                        tabImg.setSelected(true);
                        tabSetting.setSelected(false);
                        break;

                    case 2:
                        tabHome.setSelected(false);
                        tabMy.setSelected(true);
                        tabImg.setSelected(false);
                        tabSetting.setSelected(false);
                        break;
                    case 3:
                        tabHome.setSelected(false);
                        tabMy.setSelected(false);
                        tabImg.setSelected(false);
                        tabSetting.setSelected(true);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        int position = 0;

        switch (v.getId()){

            case R.id.tab_home:
                tabHome.setSelected(true);
                tabMy.setSelected(false);
                tabImg.setSelected(false);
                tabSetting.setSelected(false);
                position = 0;
                break;

            case R.id.tab_img:
                tabHome.setSelected(false);
                tabMy.setSelected(false);
                tabImg.setSelected(true);
                tabSetting.setSelected(false);
                position = 1;
                break;

            case R.id.tab_my:
                tabHome.setSelected(false);
                tabMy.setSelected(true);
                tabImg.setSelected(false);
                tabSetting.setSelected(false);
                position = 2;
                break;
            case R.id.tab_settings:
                tabHome.setSelected(false);
                tabMy.setSelected(false);
                tabImg.setSelected(false);
                tabSetting.setSelected(true);
                position = 3;
                break;

        }

        mainPager.setCurrentItem(position, true);
    }

}