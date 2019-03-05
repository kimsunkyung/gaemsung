package com.example.user.gamsung.vo;

import com.google.gson.annotations.SerializedName;

public class ImageSearchVO {

    @SerializedName("img")
    private String img = "";

    @SerializedName("url")
    private String url = "";

    public String getimg() {
        return img;
    }

    public void setimg(String img) {
        this.img = img;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }


}