package com.good.diaodiaode.tebiediao.hook;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.system.Os;
import android.util.Log;

import com.good.diaodiaode.tebiediao.activity.PluginStubActivity;
import com.good.diaodiaode.tebiediao.utils.ReflectionHelper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        try {
            File file = new File("/sdcard/plugin-debug.apk");
            Log.e("xxxxxxxx_Absolute",file.getAbsolutePath());
            Log.e("xxxxxxxx_canon",file.getCanonicalPath());
            Log.e("xxxxxxxx_name",file.getName());
            Log.e("xxxxxxxx_path",file.getPath());
            //新创建加载插件apk的DexClassLoader
            DexClassLoader dexClassLoader = new DexClassLoader(file.getPath(), "/sdcard", null, pathClassLoader);
            Log.e("xxxxxxxx_init","0");
            Class<?> baseDexClassLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = baseDexClassLoaderClass.getDeclaredField("pathList");
            pathListField.setAccessible(true);
            Log.e("xxxxxxxx_init","1");
            /**
             * 获取宿主classloader的dexElements数组对象
             * */

            //1.获取宿主classloader的pathlist对象
            Object hostDexPathList = pathListField.get(pathClassLoader);
            Log.e("xxxxxxxx_init","2");
            //2.获取dexElements数组对象
            Class<?> dexPathListClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathListClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            Object[] hostDexElements = (Object[]) dexElementsField.get(hostDexPathList);
            Log.e("xxxxxxxx_init","3");
            //3.获取宿主classloader的pathlist对象
            Object pluginDexPathList = pathListField.get(dexClassLoader);
            Log.e("xxxxxxxx_init","4");
            //4.获取dexElements数组对象
            Object[] pluginDexElements = (Object[]) dexElementsField.get(pluginDexPathList);

            //5.合并两个数组hostDexElements,pluginDexElements
//           todo  这种写法不行，会在第6步报类转换异常
//            Object[] newDexElements = new Object[hostDexElements.length + pluginDexElements.length];
            Object[] newDexElements = (Object[]) Array.newInstance(hostDexElements.getClass().getComponentType(), hostDexElements.length + pluginDexElements.length);
            Log.e("xxxxxxxx_init","5");

            System.arraycopy(hostDexElements, 0, newDexElements, 0, hostDexElements.length);
            System.arraycopy(pluginDexElements, 0, newDexElements, hostDexElements.length, pluginDexElements.length);
            //6.将数组替换原来宿主classloader
            dexElementsField.set(hostDexPathList, newDexElements);
            Log.e("xxxxxxxx_init", "6:newDexElements:" + newDexElements.length);
        } catch (Exception e) {
            Log.e("xxxxx", "Exception:" + e.getMessage());
        }
    }

    //hook
    public static void hookStart(Context context) {

        //思路：把这个ActivityManager.getService()动态代理
        try {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                Class<?> mActivityManagerClass = Class.forName("android.app.ActivityTaskManager");

                Field iActivityManagerTaskSingleton = mActivityManagerClass.getDeclaredField("IActivityTaskManagerSingleton");

                iActivityManagerTaskSingleton.setAccessible(true);
                Object oActivityManagerSingleton = iActivityManagerTaskSingleton.get(null);

                Class<?> aClass = Class.forName("android.util.Singleton");

                Field mInstance = aClass.getDeclaredField("mInstance");
                mInstance.setAccessible(true);

                Object mIActivityManager = mInstance.get(oActivityManagerSingleton);

                Object proxyObj = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{Class.forName("android.app.IActivityTaskManager")}, new StartActivityHandler(context, mIActivityManager));

                //替换代理对象
                mInstance.set(oActivityManagerSingleton, proxyObj);

                Log.e("xxxxx", "success1");
            } else if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){


                Object oActivityManagerSingleton = ReflectionHelper.getField(Class.forName("android.app.ActivityManagerNative"), null, "gDefault");

                Class<?> aClass = Class.forName("android.util.Singleton");

                Field mInstance = aClass.getDeclaredField("mInstance");
                mInstance.setAccessible(true);

                Object mIActivityManager = mInstance.get(oActivityManagerSingleton);

                Object proxyObj = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new StartActivityHandler(context, mIActivityManager));

                //替换代理对象
                mInstance.set(oActivityManagerSingleton, proxyObj);
                Log.e("xxxxx", "success2");
            }else {
                Class<?> mActivityManagerClass = Class.forName("android.app.ActivityManager");

                Field iActivityManagerSingleton = mActivityManagerClass.getDeclaredField("IActivityManagerSingleton");

                iActivityManagerSingleton.setAccessible(true);
                Object oActivityManagerSingleton = iActivityManagerSingleton.get(null);

                Class<?> aClass = Class.forName("android.util.Singleton");

                Field mInstance = aClass.getDeclaredField("mInstance");
                mInstance.setAccessible(true);

                Object mIActivityManager = mInstance.get(oActivityManagerSingleton);

                Object proxyObj = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new StartActivityHandler(context, mIActivityManager));

                //替换代理对象
                mInstance.set(oActivityManagerSingleton, proxyObj);
                Log.e("xxxxx", "success2");
            }

        } catch (Exception e) {
            Log.e("xxxxx", "exception" + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void hookBack() {

        //hook住从ams进程通讯回来的路径，将intent替换回插件的activity
        try {
            //hook位置：替换掉ActivityThread中的handler的callback对象
            Class<?> clz = Class.forName("android.app.ActivityThread");
            Field acField = clz.getDeclaredField("sCurrentActivityThread");
            acField.setAccessible(true);
            Object acObj = acField.get(null);
            Log.e("xxxxx", "acObj:" + acObj.toString());
            Field mHField = clz.getDeclaredField("mH");
            mHField.setAccessible(true);
            Object mH = mHField.get(acObj);
            Log.e("xxxxx", "mH:" + mH.toString());
            Field mCallback = Handler.class.getDeclaredField("mCallback");
            mCallback.setAccessible(true);

            Handler.Callback callback = new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {

                    try {
                        //28以下是ActivityClientRecord
                        if (msg.what == 100) {
                            Intent proxyIntent = ReflectionHelper.getValue(msg.obj, "intent");
                            if (PluginStubActivity.class.getName().equals(proxyIntent.getComponent().getClassName()) && StartActivityHandler.orginIntent != null) {
                                ReflectionHelper.setValue(msg.obj,StartActivityHandler.orginIntent ,"intent");
                                StartActivityHandler.orginIntent = null;
                                Log.e("xxxxx_100", "LaunchActivity");
                            }
                            Log.e("xxxxx_100", "what");
                        } else if (msg.what == 159) {
                            //todo 兼容api28是ClientTransation
                            Log.e("xxxxx_159", "159");

                            Class<?> clz = Class.forName("android.app.servertransaction.ClientTransaction");
                            Field mActivityCallbacksField = clz.getDeclaredField("mActivityCallbacks");
                            mActivityCallbacksField.setAccessible(true);
                            List mActivityCallbacks = (List) mActivityCallbacksField.get(msg.obj);

                            Log.e("xxxxx_159", "mActivityCallbacks:" + mActivityCallbacks.toString());
                            for (Object o : mActivityCallbacks) {
                                if ("android.app.servertransaction.LaunchActivityItem".equals(o.getClass().getName())) {
                                    Log.e("xxxxx_159", "LaunchActivityItem1");
                                    Field mIntentField = o.getClass().getDeclaredField("mIntent");
                                    mIntentField.setAccessible(true);
                                    Intent proxyIntent = (Intent) mIntentField.get(o);
                                    if (PluginStubActivity.class.getName().equals(proxyIntent.getComponent().getClassName()) && StartActivityHandler.orginIntent != null) {
                                        mIntentField.set(o, StartActivityHandler.orginIntent);
                                        StartActivityHandler.orginIntent = null;
                                        Log.e("xxxxx_159", "LaunchActivityItem2");
                                    }
                                }
                            }

                        }
                    } catch (Exception e) {
                        Log.e("xxxxx", "exception2");
                        return false;
                    }
                    return false;
                }
            };

            //替换掉mH中的callback对象
            mCallback.set(mH, callback);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("xxxxx", "exception2");
        }
    }
}
