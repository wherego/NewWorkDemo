package com.shaojun.networkdemo.network.http;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class HeaderInterceptor implements Interceptor {
    private Context context;

    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request newRequest;
//        String tokenOld = XmlDB.getInstance(context).getString(MConstants.KEY_USER_TOKEN, "");
//        if (!TextUtils.isEmpty(tokenOld)) {
//            JLog.d("token:" + tokenOld);
//            //统一设置请求Header
//            newRequest = chain.request().newBuilder()
//                    .addHeader(MConstants.KEY_USER_TOKEN, tokenOld)
//                    .build();
//        } else {
//            JLog.d("token为空");
//            newRequest = chain.request().newBuilder()
//                    .build();
//        }
//        okhttp3.Response response = chain.proceed(newRequest);
//        String tokenNew = response.header(MConstants.KEY_USER_TOKEN);
//        if (!TextUtils.isEmpty(tokenNew)) {
//            JLog.d("获取到新的token:" + tokenOld);
//            XmlDB.getInstance(context).saveString(MConstants.KEY_USER_TOKEN, tokenNew);
//        }
//        return response;
        return chain.proceed(chain.request().newBuilder().build());

    }
}