/*
 * Copyright (C),2015-2015. 城家酒店管理有限公司
 * FileName: ReboundScrollView.java
 * Author:   jeremy
 * Date:     2015-09-07 17:17:06
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <jeremy>  <2015-09-07 17:17:06> <version>   <desc>
 */
package com.shaojun.networkdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    protected CompositeSubscription mCompositeSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 解除订阅
     */
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加订阅
     */
    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
}
