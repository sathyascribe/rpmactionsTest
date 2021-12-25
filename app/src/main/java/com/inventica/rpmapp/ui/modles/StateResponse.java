package com.inventica.rpmapp.ui.modles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateResponse {


    @SerializedName("provinceStateId")
    @Expose
    private Integer provinceStateId;
    @SerializedName("provinceStateName")
    @Expose
    private String provinceStateName;

    public Integer getProvinceStateId() {
        return provinceStateId;
    }

    public void setProvinceStateId(Integer provinceStateId) {
        this.provinceStateId = provinceStateId;
    }

    public String getProvinceStateName() {
        return provinceStateName;
    }

    public void setProvinceStateName(String provinceStateName) {
        this.provinceStateName = provinceStateName;
    }

    @Override
    public String toString() {
        return provinceStateName;
    }
}
