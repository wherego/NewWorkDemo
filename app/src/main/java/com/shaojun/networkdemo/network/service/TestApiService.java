package com.shaojun.networkdemo.network.service;


import com.shaojun.networkdemo.network.http.Response;
import com.shaojun.networkdemo.network.service.models.MeiZhi;
import com.shaojun.networkdemo.network.service.models.User;
import com.shaojun.networkdemo.network.service.models.request.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 */
public interface TestApiService {
    @GET("http://gank.io/api/data/福利/10/{page}")
    Call<Response<List<MeiZhi>>> testGetMeiZhiList(@Path("page") int page);

    @POST("service/user/login/1")
    Call<Response<User>> login(@Body Login login);
}