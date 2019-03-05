package com.example.user.gamsung.network.response;

import com.example.user.gamsung.vo.LoginVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResult {

    @SerializedName("success")
    public boolean success = false;

    @SerializedName("userid")
    private int userid = 0;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getUserid() {return userid; }

    public void setUserid(int userid) {this.userid = userid;}
}
