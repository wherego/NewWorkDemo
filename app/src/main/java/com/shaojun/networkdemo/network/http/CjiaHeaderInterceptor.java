package com.shaojun.networkdemo.network.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

class CjiaHeaderInterceptor implements Interceptor {

    public CjiaHeaderInterceptor() {
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request newRequest;
        newRequest = chain.request().newBuilder()
                .addHeader("header", "{\"applicationCode\" : \"Tenement-iOS\",  \"clientId\" : \"27e508c131ad4026a48aeacc154eef63\",  \"sourceId\" : \"D0000007\",  \"version\" : \"1.7\"}")
//                .addHeader("sourceId", "D0000000")
//                .addHeader("clientId", "64804fc19939450394ddd842f5d19c0b")
//                .addHeader("applicationCode", "PMS")
//                .addHeader("version", "1.2.0")
                .addHeader("userToken", "a1375f70-746a-4029-a35f-cff5aef6f49f")

                .build();
        return chain.proceed(newRequest);

    }

}