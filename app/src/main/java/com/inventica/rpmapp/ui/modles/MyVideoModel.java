package com.inventica.rpmapp.ui.modles;


import org.openapitools.client.model.GetVideoDetailsModel;

import java.util.ArrayList;

public class MyVideoModel {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<GetVideoDetailsModel> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<GetVideoDetailsModel> dataList) {
        this.dataList = dataList;
    }

    String title;

    public MyVideoModel(String title, ArrayList<GetVideoDetailsModel> dataList) {
        this.title = title;
        this.dataList = dataList;
    }

    private ArrayList<GetVideoDetailsModel> dataList = new ArrayList<>();
}

