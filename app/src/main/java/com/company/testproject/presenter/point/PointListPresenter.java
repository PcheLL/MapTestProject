package com.company.testproject.presenter.point;

import com.company.testproject.manager.ApiManager;
import com.company.testproject.manager.IApiManager;
import com.company.testproject.manager.callback.LoadUserDataApiCallback;
import com.company.testproject.model.LoadUserDataResponse;
import com.company.testproject.model.PointInfoDataList;
import com.company.testproject.ui.point.PointListView;

import java.util.ArrayList;
import java.util.List;

public class PointListPresenter implements IPointListPresenter {

    private PointListView view;
    private IApiManager apiManager;
    private LoadUserDataResponse userData;
    private int numberPage = 1;
    private boolean loadingUserData = false;
    private String code;

    public PointListPresenter(PointListView view, String code) {
        this.view = view;
        this.code = code;
        apiManager = ApiManager.getInstance();
        userData = new LoadUserDataResponse();
    }

    @Override
    public void loadPointData() {
        if (!loadingUserData) {
            setLoadingUserData(true);
            apiManager.loadUserData(code, numberPage, new LoadUserDataApiCallback() {
                @Override
                public void onLoadUserDataSuccess(LoadUserDataResponse result) {
                    userData.setPointInfoData(result.getPointInfoData());
                    view.updatePointListAdapter(getAllPointName(result.getPointInfoData()));
                    numberPage++;
                    setLoadingUserData(false);
                }

                @Override
                public void onLoadUserDataError(String message) {
                    setLoadingUserData(false);
                }
            });
        }
    }

    public void setLoadingUserData(boolean loadingUserData) {
        this.loadingUserData = loadingUserData;
    }

    @Override
    public void showMapScreen(int position) {
        view.showMapScreen(userData.getPointInfoData().get(position));
    }

    private List<String> getAllPointName(List<PointInfoDataList> pointData) {
        List<String> pointNames = new ArrayList<>();
        for (PointInfoDataList data : pointData) {
            pointNames.add(data.getName());
        }
        return pointNames;
    }
}
