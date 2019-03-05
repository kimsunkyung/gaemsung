package com.example.user.gamsung.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MypageImgVO {


    @SerializedName("main_img")
    private String main_img = "";

    @SerializedName("date")
    private String date = "";

    @SerializedName("subs")
    private ArrayList<ImageSearchVO> sub_img_info = new ArrayList<>();


    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ImageSearchVO> getSub_img_info() {
        return sub_img_info;
    }

    public void setSub_img_info(ArrayList<ImageSearchVO> sub_img_info) {
        this.sub_img_info = sub_img_info;
    }



}
