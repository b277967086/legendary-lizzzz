package com.good.diaodiaode.tebiediao.hook;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * 反射+动态代理
 * 替换调到单例SingleTon对象下的IActivityManager对象
 */
public class HookUtils {

    //不行？？？？？？
//    public static void init(Context context) {
//
//        //思路：把这个ActivityManager.getService()动态代理
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//
//        Log.e("xxxxxx", "hook init");
//        try {
//            Class<?> mIActivityManager = Class.forName("android.app.IActivityManager");
//            Object o = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{mIActivityManager}, new StartActivityHandler(activityManager));
//            Log.e("xxxxxx", "Proxy.newProxyInstance::"+o.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void init(Context context) {

        //思路：把这个ActivityManager.getService()动态代理
        try {
            Class<?> mActivityManagerClass = Class.forName("android.app.ActivityManager");

            Field iActivityManagerSingleton = mActivityManagerClass.getDeclaredField("IActivityManagerSingleton");

            iActivityManagerSingleton.setAccessible(true);
            Object oActivityManagerSingleton = iActivityManagerSingleton.get(null);

            Class<?> aClass = Class.forName("android.util.Singleton");

            Field mInstance = aClass.getDeclaredField("mInstance");
            mInstance.setAccessible(true);

            Object mIActivityManager = mInstance.get(oActivityManagerSingleton);

            Object proxyObj = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new StartActivityHandler(context,mIActivityManager));

            //替换代理对象
            mInstance.set(oActivityManagerSingleton,proxyObj);
        } catch (Exception e) {
            Log.e("xxxxx", e.getMessage());
            e.printStackTrace();
        }

    }
}
