package com.good.diaodiaode.tebiediao;

import android.app.Application;
import android.content.Context;

import com.good.diaodiaode.tebiediao.hook.HookUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by lizheng on 2018/2/9.
 */

public class MyApplacation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
//        WXSDKEngine.initialize(this,config);

        //插件相关
//        HookUtils.init(this);
//        HookUtils.hookStart(this);
//        HookUtils.hookBack();

        // 初始化
//        com.paincker.timetracer.tracer.MainThreadTracer tracer = com.paincker.timetracer.tracer.MainThreadTracer.INSTANCE;
//        tracer.setThreshold(1);
//        com.paincker.timetracer.tracer.TimeTracer.setTracer(tracer);
//        com.paincker.timetracer.tracer.TimeTracer.e("setTracer", "attachBaseContext");
    }
}
