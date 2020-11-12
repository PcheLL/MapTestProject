package com.company.testproject.manager.callback;

import com.company.testproject.model.LoadUserDataResponse;

public interface LoadUserDataApiCallback {
    void onLoadUserDataSuccess(LoadUserDataResponse result);

    void onLoadUserDataError(String message);
}
