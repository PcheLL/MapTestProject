package com.company.testproject.manager;

import com.company.testproject.manager.callback.LoadUserDataApiCallback;
import com.company.testproject.manager.callback.LoginUserApiCallback;
import com.company.testproject.model.LoadUserDataResponse;
import com.company.testproject.model.LoginUserResponse;
import com.company.testproject.utils.Const;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager implements IApiManager {
    private static ApiManager instance = null;
    private AppApi api;

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        api = retrofit.create(AppApi.class);
    }

    @Override
    public void loginUser(String login, String password, final LoginUserApiCallback callback) {
        api.loginUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginUserResponse>() {
                    @Override
                    public void onSuccess(LoginUserResponse response) {
                        if (response.getStatus().equals("ok")) {
                            callback.onLoginUserSuccess(response.getCode());
                        } else {
                            callback.onLoginUserError("");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onLoginUserError(e.getMessage());
                    }
                });
    }

    @Override
    public void loadUserData(String code, int numberPage, LoadUserDataApiCallback callback) {
        api.loadUserData(code, numberPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoadUserDataResponse>() {
                    @Override
                    public void onSuccess(LoadUserDataResponse response) {
                        callback.onLoadUserDataSuccess(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onLoadUserDataError(e.getMessage());
                    }
                });
    }
}
