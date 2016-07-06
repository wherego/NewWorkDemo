package com.shaojun.networkdemo.network.http.subscriber;

import android.text.TextUtils;

//服务返回异常
public class ResultException extends RuntimeException {

    public static final String DATA_IS_ERROR = "1123";
    private String errCode = "";
    private String errMessage = "";


    public ResultException(String errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
        this.errMessage = errMessage;
        if (TextUtils.isEmpty(errMessage)) {
            getExceptionMessage(errCode);
        }
    }

    public String getErrCode() {
        return errCode;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private String getExceptionMessage(String code) {
        if (code.equals(DATA_IS_ERROR)) {
            errMessage = "数据异常";
        } else {
            errMessage = "未定义的服务错误";
        }
        return errMessage;
    }
}