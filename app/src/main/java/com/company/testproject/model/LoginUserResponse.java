package com.company.testproject.model;

import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {
    @SerializedName("status")
    private String Status;
    @SerializedName("code")
    private String Code;

    public String getStatus() {
        return Status;
    }

    public String getCode() {
        return Code;
    }
}
