package com.example.user.gamsung.network.response;

import com.example.user.gamsung.ImgSearch.ImageRequestTask;
import com.example.user.gamsung.vo.ImageSearchVO;
import com.example.user.gamsung.vo.LoginVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageSearchResult {


    @SerializedName("subs")
    public ArrayList<ImageSearchVO> imageinfo = new ArrayList<ImageSearchVO>();

    public ArrayList<ImageSearchVO> getImageInfo() {
        return imageinfo;
    }

    public void setImageinfo(ArrayList<ImageSearchVO> imageInfo) {
        this.imageinfo = imageInfo;
    }
}
