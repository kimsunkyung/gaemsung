package com.example.user.gamsung.vo;

import com.google.gson.annotations.SerializedName;

public class MypageInfoVO {

    @SerializedName("username")
    private String user_name = "";

    @SerializedName("age")
    private int user_age;

    @SerializedName("gender")
    private String user_gender = "";

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }


}
