package com.company.testproject.presenter.login;

import com.company.testproject.R;
import com.company.testproject.manager.ApiManager;
import com.company.testproject.manager.IApiManager;
import com.company.testproject.manager.callback.LoginUserApiCallback;
import com.company.testproject.ui.login.LoginView;

public class LoginPresenter implements ILoginPresenter {

    private IApiManager apiManager;
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
        apiManager = ApiManager.getInstance();
    }

    @Override
    public void onLoginButtonClicked(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            view.showShortSnackbar(R.string.error_some_fields_empty);
        } else {
            apiManager.loginUser(login, password, new LoginUserApiCallback() {
                @Override
                public void onLoginUserSuccess(String code) {
                    view.showMainScreen(code);
                }

                @Override
                public void onLoginUserError(String message) {
                    if (message.isEmpty()) {
                        view.showShortSnackbar(R.string.error_wrong_login_or_password);
                    } else {
                        view.showShortSnackbar(R.string.error_server_access);
                    }

                }
            });
        }
    }
}
