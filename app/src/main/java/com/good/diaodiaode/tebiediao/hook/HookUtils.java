package com.good.diaodiaode.tebiediao.hook;

import android.app.ActivityManager;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * 反射+动态代理
 * 替换调到单例SingleTon对象下的IActivityManager对象
 */
public class HookUtils {

    public static void init(Context context) {

        //思路：把这个ActivityManager.getService()动态代理
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);


        try {
            Class<?> mIActivityManager = Class.forName("android.app.IActivityManager");
            Proxy.newProxyInstance(context.getClassLoader(), new Class[]{mIActivityManager}, new StartActivityHandler(activityManager));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
