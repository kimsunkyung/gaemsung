package com.example.user.gamsung.vo;

import com.google.gson.annotations.SerializedName;

public class LoginVO {

    @SerializedName("user_id")
    private String user_id = "";

    @SerializedName("user_name")
    private String user_name = "";

    @SerializedName("user_email")
    private String user_email = "";


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
