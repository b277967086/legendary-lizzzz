package com.good.diaodiaode.tebiediao.hook;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;

import dalvik.system.DexClassLoader;

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

    //加载插件apk
    public static void init(Context context) {

        //DexClassLoader->PathClassLoader->BootClassLoader->findClass->pathList对象(DexPathList)->dexElements数组对象(Element数组)->遍历得element对象->findClass
        //所以只要合并dexElements到宿主的ClassLoader就行

        //宿主的ClassLoader
        ClassLoader pathClassLoader = context.getClassLoader();
        //新创建加载插件apk的DexClassLoader
        DexClassLoader dexClassLoader = new DexClassLoader("/sdcard/plugin-debug.apk", null, null, pathClassLoader);

        try {
            Class<?> baseDexClassLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = baseDexClassLoaderClass.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            /**
             * 获取宿主classloader的dexElements数组对象
             * */

            //1.获取宿主classloader的pathlist对象
            Object hostDexPathList = pathListField.get(pathClassLoader);

            //2.获取dexElements数组对象
            Class<?> dexPathListClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathListClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            Object[] hostDexElements = (Object[]) dexElementsField.get(hostDexPathList);

            //3.获取宿主classloader的pathlist对象
            Object pluginDexPathList = pathListField.get(dexClassLoader);
            //4.获取dexElements数组对象
            Object[] pluginDexElements = (Object[]) dexElementsField.get(pluginDexPathList);

            //5.合并两个数组hostDexElements,pluginDexElements
            Object[] newDexElements = new Object[hostDexElements.length + pluginDexElements.length];
            System.arraycopy(hostDexElements,0,newDexElements,0,hostDexElements.length);
            Log.e("xxxxx", "dexElementsField:" + dexElementsField.toString());
            System.arraycopy(pluginDexElements,0,newDexElements,hostDexElements.length,pluginDexElements.length);
            //6.将数组替换原来宿主classloader
            dexElementsField.set(hostDexPathList,newDexElements);

            Log.e("xxxxx", "dexElementsField:" + dexElementsField.toString());
            Log.e("xxxxx", "newDexElements:" + newDexElements.length);
        } catch (Exception e) {
            Log.e("xxxxx", "Exception:" + e.getMessage());
        }
    }

    //hook
    public static void hook(Context context) {

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
