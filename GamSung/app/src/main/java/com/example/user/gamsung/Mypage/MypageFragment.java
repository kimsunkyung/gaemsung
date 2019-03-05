package com.example.user.gamsung.Mypage;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.gamsung.ImgSearch.ImageRequestTask;
import com.example.user.gamsung.ImgSearch.ImgListAdapter;
import com.example.user.gamsung.login.LoginActivity;
import com.example.user.gamsung.R;
import com.example.user.gamsung.network.ApiValue;
import com.example.user.gamsung.network.response.ImageSearchResult;
import com.example.user.gamsung.network.response.MypageImgResult;
import com.example.user.gamsung.network.response.MypageInfoResult;
import com.example.user.gamsung.vo.ImageSearchVO;
import com.example.user.gamsung.vo.MypageImgVO;
import com.example.user.gamsung.vo.MypageInfoVO;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.gamsung.login.LoginActivity.real_user_id;

public class MypageFragment extends Fragment implements View.OnClickListener {
    private MypageListAdapter mypageListAdapter;
    private RecyclerView recyclerView;
    private List<MypageInfoVO> InfoData = new ArrayList<MypageInfoVO>();

    public void setInfoData(ArrayList<MypageInfoVO> InfoData){
        this.InfoData = InfoData;
    }
    TextView mypage_id,mypage_gender,mypage_age;

    public static MypageFragment createFragment(){
        Bundle bundle = new Bundle();
        MypageFragment fragment = new MypageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private View.OnClickListener itemActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        mypage_id = view.findViewById(R.id.mypage_id);
        mypage_gender = view.findViewById(R.id.mypage_gender);
        mypage_age = view.findViewById(R.id.mypage_age);

        recyclerView = view.findViewById(R.id.mypage_img_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mypageListAdapter = new MypageListAdapter(getContext(),itemActivityListener);
        recyclerView.setAdapter(mypageListAdapter);
        mypageInfo();
        mypageImg();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }
    @Override
    public void onResume() {
        super.onResume();

        mypageListAdapter.notifyDataSetChanged();
    }

    public void mypageImg() {

        MyPageImgRequestTask requestTask = new MyPageImgRequestTask(new MyPageImgRequestTask.MyPageImgRequestTaskHandler() {
            @Override
            public void onSuccessAppAsyncTask(MypageImgResult result) {
                if(result.mypageImginfo != null && result.mypageImginfo.size() > 0) {
                    mypageListAdapter.setImgData(result.mypageImginfo);
                    mypageListAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    mypageListAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(getContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(getContext(), "사용자가 해당 작업을 중지하였습니다.", Toast.LENGTH_LONG);
            }

        });


        requestTask.execute(ApiValue.API_MYPAGE_IMG+real_user_id);

    }



    public void mypageInfo() {

        MypageInfoRequestTask requestTask = new MypageInfoRequestTask(new MypageInfoRequestTask.MypageInfoRequestTaskHandler() {
            @Override
            public void onSuccessAppAsyncTask(MypageInfoResult result) {
                if(result.getMypageInfo() != null) {
                    Log.d("user_id = ", result.getMypageInfo().get(0).getUser_name());
                    Log.d("user_age = ", String.valueOf(result.getMypageInfo().get(0).getUser_age()));
                    mypage_id.setText(result.getMypageInfo().get(0).getUser_name());
                    mypage_age.setText(String.valueOf(result.getMypageInfo().get(0).getUser_age()));
                    if (result.getMypageInfo().get(0).getUser_gender().equals("f")) {
                        mypage_gender.setText("여자");
                    } else {
                        mypage_gender.setText("남자");
                    }
                }
            }

            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(getContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(getContext(), "사용자가 해당 작업을 중지하였습니다.", Toast.LENGTH_LONG);
            }

        });


        requestTask.execute(ApiValue.API_MYPAGE_INFO+real_user_id);

    }
}
