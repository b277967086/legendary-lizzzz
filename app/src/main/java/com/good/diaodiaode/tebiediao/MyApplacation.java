package com.good.diaodiaode.tebiediao;

import android.app.Application;

import com.good.diaodiaode.tebiediao.hook.HookUtils;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

/**
 * Created by lizheng on 2018/2/9.
 */

public class MyApplacation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);
        HookUtils.init(this);
    }
}
