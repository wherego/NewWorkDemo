package com.shaojun.networkdemo.network.http;


import com.shaojun.networkdemo.MConstants;

/**
 * Created by Sunflower on 2016/1/11.
 */
public class Response<T> {

    public String resultCode;
    public T result;//
    public String errorMsg;


    public T results;//妹纸图返回数据

    public boolean isSuccess() {
        if (resultCode != null) {
            return resultCode.equals(MConstants.SUCCESS_CODE);
        } else {
            return true;
        }
    }
}
