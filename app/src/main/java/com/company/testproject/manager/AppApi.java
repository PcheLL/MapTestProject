package com.company.testproject.manager;

import com.company.testproject.model.LoadUserDataResponse;
import com.company.testproject.model.LoginUserResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {

    @GET("/test/auth.cgi")
    Single<LoginUserResponse> loginUser(
            @Query("username") String login,
            @Query("password") String password);

    @GET("/test/data.cgi")
    Single<LoadUserDataResponse> loadUserData(@Query("code") String code, @Query("p") int numberPage);

}
