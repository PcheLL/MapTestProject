package com.company.testproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PointInfoDataList implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private String country;
    @SerializedName("lat")
    private float lat;
    @SerializedName("lon")
    private float lon;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
