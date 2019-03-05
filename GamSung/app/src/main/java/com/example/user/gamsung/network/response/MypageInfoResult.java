package com.example.user.gamsung.network.response;

import com.example.user.gamsung.vo.MypageInfoVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MypageInfoResult {

    @SerializedName("userInfo")
    public ArrayList<MypageInfoVO> mypageInfo = new ArrayList<MypageInfoVO>();

    public ArrayList<MypageInfoVO> getMypageInfo() {
        return mypageInfo;
    }

    public void setMypageInfo(ArrayList<MypageInfoVO> mypageInfo) {
        this.mypageInfo = mypageInfo;
    }
}
