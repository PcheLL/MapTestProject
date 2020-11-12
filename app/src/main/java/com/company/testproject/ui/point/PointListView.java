package com.company.testproject.ui.point;

import com.company.testproject.model.PointInfoDataList;

import java.util.List;

public interface PointListView {
    void updatePointListAdapter(List<String> data);

    void showMapScreen(PointInfoDataList data);
}
