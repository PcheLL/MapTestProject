package com.company.testproject.manager;

import com.company.testproject.manager.callback.LoadUserDataApiCallback;
import com.company.testproject.manager.callback.LoginUserApiCallback;

public interface IApiManager {
    void loginUser(String login, String password, LoginUserApiCallback callback);

    void loadUserData(String code, int numberPage, final LoadUserDataApiCallback callback);
}
