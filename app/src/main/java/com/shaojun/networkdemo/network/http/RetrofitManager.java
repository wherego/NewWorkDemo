package com.shaojun.networkdemo.network.http;

import android.content.Context;

import com.shaojun.networkdemo.MConstants;
import com.shaojun.networkdemo.network.http.converter.GsonConverterFactory;
import com.shaojun.networkdemo.network.service.APIService;
import com.shaojun.networkdemo.network.service.TestApiService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Sunflower on 2015/11/4.
 */
public class RetrofitManager {
    private Context mContext;
    private static String API_HOST = MConstants.BASE_URL;// 服务器地址
    private static APIService apiService;
    private static TestApiService testApiService;
    private static Retrofit retrofit;
    private static Retrofit testRetrofit;

    private static class RetrofitManagerHolder {
        static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private RetrofitManager() {

    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        initRetrofit();
    }

    public void initRetrofit() {
        if (mContext == null) {
//            JLog.e("mContext is null , you need call init(Context context)!");
            return;
        }
        HttpLoggerInterceptor loggingInterceptor = new HttpLoggerInterceptor(new HttpLoggerInterceptor.Logger() {
            @Override
            public void log(String message) {
//                JLog.d(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggerInterceptor.Level.BODY);

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(MConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(MConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new CjiaHeaderInterceptor())
                .addInterceptor(loggingInterceptor)
//                            .addNetworkInterceptor(new MyTokenInterceptor())
//                            .authenticator(new MyAuthenticator())
//                            .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static void initTestRetrofit() {
        HttpLoggerInterceptor loggingInterceptor = new HttpLoggerInterceptor(new HttpLoggerInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println(message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggerInterceptor.Level.BODY);

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(MConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(MConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new CjiaHeaderInterceptor())
                .build();

        testRetrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * 测试接口服务
     *
     * @return
     */
    public static TestApiService getTestApiService() {
        initTestRetrofit();
        if (testApiService == null) {
            testApiService = testRetrofit.create(TestApiService.class);
        }
        return testApiService;
    }

    /**
     * @return
     */
    public static APIService getApiService() {
        if (apiService == null) {
            apiService = retrofit.create(APIService.class);
        }
        return apiService;
    }

    /**
     * 当{@link APIService}中接口的注解为{@link retrofit2.http.Multipart}时，参数为{@link RequestBody}
     * 生成对应的RequestBody
     *
     * @param param
     * @return
     */
    protected RequestBody createRequestBody(int param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    protected RequestBody createRequestBody(long param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    protected RequestBody createRequestBody(String param) {
        return RequestBody.create(MediaType.parse("text/plain"), param);
    }

    protected RequestBody createRequestBody(File param) {
        return RequestBody.create(MediaType.parse("image/*"), param);
    }

//    /**
//     * 已二进制传递图片文件，对图片文件进行了压缩
//     *
//     * @param path 文件路径
//     * @return
//     */
//    protected RequestBody createPictureRequestBody(String path) {
//        Bitmap bitmap = ClippingPicture.decodeResizeBitmapSd(path, 400, 800);
//        return RequestBody.create(MediaType.parse("image/*"), ClippingPicture.bitmapToBytes(bitmap));
//    }


}
