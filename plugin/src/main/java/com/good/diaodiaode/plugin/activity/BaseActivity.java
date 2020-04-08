package com.good.diaodiaode.plugin.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;

public class BaseActivity extends Activity {


    @Override
    public Resources getResources() {

        Resources resources1 = super.getResources();
        try {
            Class<?> assetManagerBuilderClass = Class.forName("android.content.res.AssetManager.Builder");
            Class<?> apkAssetsClass = Class.forName("android.content.res.ApkAssets");
            Object assetManagerBuilderObj = assetManagerBuilderClass.newInstance();
            Method addApkAssetsMethod = assetManagerBuilderClass.getDeclaredMethod("addApkAssets", apkAssetsClass);
            addApkAssetsMethod.setAccessible(true);
            Log.e("getResources", "getResources1");

            Method loadFromPathMethod = apkAssetsClass.getDeclaredMethod("loadFromPath", String.class);
            loadFromPathMethod.setAccessible(true);
            Object apkAssets = loadFromPathMethod.invoke(null, "/sdcard/plugin-debug.apk");
            Log.e("getResources", "getResources2");
            addApkAssetsMethod.invoke(assetManagerBuilderObj, apkAssets);
            Log.e("getResources", "getResources3");
            Method build = assetManagerBuilderClass.getDeclaredMethod("build");
            build.setAccessible(true);
            Log.e("getResources", "getResources4");
            //最终获取这个assetManager对象
            AssetManager assetManager = (AssetManager) build.invoke(assetManagerBuilderObj);
            Log.e("getResources", "getResources5");
            Resources resources = new Resources(assetManager, resources1.getDisplayMetrics(), resources1.getConfiguration());
            Log.e("getResources", "getResources6:" + resources.toString());
            return resources;
        } catch (Exception e) {
        }
        return resources1;
    }
}
