package com.example.user.gamsung.ImgSearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.gamsung.R;
import com.example.user.gamsung.login.LoginActivity;
import com.example.user.gamsung.network.ApiValue;
import com.example.user.gamsung.network.response.JoinResult;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class ImgFragment extends Fragment implements View.OnClickListener {
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;
    SharedPreferences mPref;
    ImageButton search_img,search_start;
    String base_img,search_locat;
    EditText location;
    private boolean isSelectImage = false;

    public static ImgFragment createFragment(){

        Bundle bundle = new Bundle();

        ImgFragment fragment = new ImgFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imgsearch, container, false);
        search_img = view.findViewById(R.id.search_img);
        location = view.findViewById(R.id.search_locat);
        search_start = view.findViewById(R.id.search_start);
        context = container.getContext();
        mPref = PreferenceManager.getDefaultSharedPreferences(getContext());


        search_img.setOnClickListener(this);
        search_start.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.search_img:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;

            case R.id.search_start:
                search_locat = location.getText().toString();
                if(search_locat.isEmpty()){
                    Toast.makeText(context, "검색 장소를 입력해주세요", Toast.LENGTH_LONG);
                    return;
                }
                if(!isSelectImage){
                    Toast.makeText(context, "검색할 이미지를 입력해주세요", Toast.LENGTH_LONG);
                    return;
                }
                search_locat = location.getText().toString();
                SharedPreferences.Editor editor1 = mPref.edit();
                editor1.putString("location", search_locat);
                editor1.commit();
                Intent intent2 = new Intent(getActivity(), ImgResultActivity.class);
                startActivity(intent2);
                getActivity().finish();
                break;
        }
    }

    public static String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContext().getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    base_img = getBase64String(img);
                    Log.d("base img",base_img);
                    SharedPreferences.Editor editor1 = mPref.edit();
                    editor1.putString("image", base_img);

                    editor1.commit();

                    in.close();
                    // 이미지 표시
                    isSelectImage = true;
                    search_img.setImageBitmap(img);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
