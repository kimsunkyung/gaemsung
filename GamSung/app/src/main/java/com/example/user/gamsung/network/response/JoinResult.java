package com.example.user.gamsung.network.response;

import com.google.gson.annotations.SerializedName;

public class JoinResult {
    @SerializedName("success")
    public boolean success = false;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
