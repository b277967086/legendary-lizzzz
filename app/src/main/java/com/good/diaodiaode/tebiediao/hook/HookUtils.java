package com.good.diaodiaode.tebiediao.hook;

import android.app.ActivityManager;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * create by leeZheng at 2019/3/8
 * 反射+动态代理
 * 替换调到单例SingleTon对象下的IActivityManager对象
 */
public class HookUtils {

    public static void init(Context context) {


        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);


        try {
            Class activityManagerclass = Class.forName("android.app.ActivityManager");

            Field iActivityManagerSingleton = activityManagerclass.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingleton.setAccessible(true);
            Object SingletonObj = iActivityManagerSingleton.get(null);

            Class<?> singtonClass = Class.forName("android.util.Singleton");
            Field iActivityManagerF = singtonClass.getDeclaredField("mInstance");
            iActivityManagerF.setAccessible(true);
            Object iActivityManagerObj = iActivityManagerF.get(SingletonObj);

            Class<?> iActivityManagerIntercept = Class.forName("android.app.IActivityManager");
            StartActivityHandler activityHandler = new StartActivityHandler(iActivityManagerObj);
            Object iActivityManagerProxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{iActivityManagerIntercept}, activityHandler);
            iActivityManagerF.set(SingletonObj,iActivityManagerProxy);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
