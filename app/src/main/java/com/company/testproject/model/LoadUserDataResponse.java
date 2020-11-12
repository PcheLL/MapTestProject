package com.company.testproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoadUserDataResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("page")
    private float page;
    @SerializedName("data")
    List<PointInfoDataList> pointInfoData;

    public List<PointInfoDataList> getPointInfoData() {
        return pointInfoData;
    }

    public void setPointInfoData(List<PointInfoDataList> data) {
        if (pointInfoData == null) {
            pointInfoData = data;
        } else {
            pointInfoData.addAll(data);
        }
    }
}
