package com.company.testproject.manager.callback;

public interface LoginUserApiCallback {
    void onLoginUserSuccess(String code);

    void onLoginUserError(String message);
}
