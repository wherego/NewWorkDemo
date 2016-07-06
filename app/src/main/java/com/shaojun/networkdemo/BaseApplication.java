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

import android.app.Application;
import android.content.Context;

import com.shaojun.networkdemo.network.http.RetrofitManager;

import junit.runner.Version;


public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    // 首页面当前选中的tab
    private int currentIndex = 0;
    /**
     * global variable
     */
    private static Context mContext;
    public Version version;

    public static BaseApplication getInstance() {
        if (null == baseApplication) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        RetrofitManager.getInstance().init(mContext);
    }


}
