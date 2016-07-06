package com.shaojun.networkdemo.network.http.subscriber;

//API异常
public class ApiException extends RuntimeException {

    public static final int UNKNOWN = 1000;
    public static final int PARSE_ERROR = 1001;
    public static final int TIME_OUT = 1002;

    private int code;
    private String errMessage;


    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.errMessage = throwable.getMessage();
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
