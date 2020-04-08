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

        try {
            Class<?> assetManagerBuilderClass = Class.forName("android.content.res.AssetManager.Builder");
            Class<?> apkAssetsClass = Class.forName("android.content.res.ApkAssets");
            Object assetManagerBuilderObj = assetManagerBuilderClass.newInstance();
            Method addApkAssetsMethod = assetManagerBuilderClass.getDeclaredMethod("addApkAssets",apkAssetsClass);
            addApkAssetsMethod.setAccessible(true);

            Method loadFromPathMethod = apkAssetsClass.getDeclaredMethod("loadFromPath", String.class);
            loadFromPathMethod.setAccessible(true);
            Object apkAssets = loadFromPathMethod.invoke(null, "/sdcard/plugin-debug.apk");

            addApkAssetsMethod.invoke(assetManagerBuilderObj,apkAssets);

            Method build = assetManagerBuilderClass.getDeclaredMethod("build");
            build.setAccessible(true);

            //最终获取这个assetManager对象
            AssetManager assetManager = (AssetManager) build.invoke(assetManagerBuilderObj);

            assetManager.close();

        } catch (Exception e) {
        }


        return super.getResources();
    }
}
