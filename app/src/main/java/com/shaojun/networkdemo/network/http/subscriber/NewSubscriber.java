package com.shaojun.networkdemo.network.http.subscriber;

import android.content.Context;
import android.widget.Toast;

import com.shaojun.networkdemo.widget.ProgressDialog;


/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己统一对请求数据进行处理
 * Created by liukun on 16/3/10.
 */
public class NewSubscriber<T> extends SubAPICallback<T> implements ProgressDialog.CancelProgressListener {
    private ProgressDialog progressDialog;
    private Context context;
    private boolean hasProgress = true;

    public NewSubscriber(Context context, boolean hasProgress) {
        this.context = context;
        this.hasProgress = hasProgress;
    }


    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelProgressListener(this);
        }
        if (hasProgress) {
            progressDialog.show();
        }
    }

    /**
     * 取消ProgressView
     */
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgress();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgress();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissProgress();
        super.onError(e);
    }

    @Override
    public void onNext(T t) {
    }

    //针对详细错误定义的实现方法
    @Override
    protected void onError(ApiException ex) {
        Toast.makeText(context, ex.getErrMessage(), Toast.LENGTH_SHORT).show();
    }

    //没权限处理401、403Http错误码，重新登录
    @Override
    protected void onPermissionError(ApiException ex) {
        Toast.makeText(context, ex.getErrMessage(), Toast.LENGTH_SHORT).show();

//        context.startActivity(new Intent(context, LoginActivity.class));

    }

    @Override
    protected void onResultError(ApiException ex) {
        Toast.makeText(context, ex.getErrMessage(), Toast.LENGTH_SHORT).show();

    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
//            JLog.i("解除订阅，请求取消!");
            this.unsubscribe();
        }
    }
}