package com.good.diaodiaode.plugin.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;

public class BaseActivity extends Activity {

    Resources resources;

    protected void loadResources() {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, "/sdcard/plugin-debug.apk");
            Resources superRes = super.getResources();
            resources = new Resources(assetManager, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());
            Resources.Theme mTheme = resources.newTheme();
            mTheme.setTo(super.getTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public Resources getResources() {
//
//        if (resources == null) {
//            Resources resources1 = super.getResources();
//            try {
//                Class<?> assetManagerBuilderClass = Class.forName("android.content.res.AssetManager$Builder");
//                Object assetManagerBuilderObj = assetManagerBuilderClass.newInstance();
//                Log.e("getResources", "assetManagerBuilderObj:" + assetManagerBuilderObj.toString());
//
//                Class<?> apkAssetsClass = Class.forName("android.content.res.ApkAssets");
//                Method addApkAssetsMethod = assetManagerBuilderClass.getDeclaredMethod("addApkAssets", apkAssetsClass);
//                addApkAssetsMethod.setAccessible(true);
//                Log.e("getResources", "addApkAssetsMethod:" + addApkAssetsMethod.toString());
//
////                Method loadFromPathMethod = apkAssetsClass.getDeclaredMethod("loadFromPath", String.class);
////                loadFromPathMethod.setAccessible(true);
////                Object apkAssets = loadFromPathMethod.invoke(null, "/sdcard/plugin-debug.apk");
////                Log.e("getResources", "getResources2");
//
//
//                Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
//                Object sResourcesManager = ReflectionHelper.getStaticValue(resourcesManagerClass, "sResourcesManager");
//                Log.e("getResources", "sResourcesManager:" + sResourcesManager.toString());
//                Method loadApkAssets = ReflectionHelper.findMethod(resourcesManagerClass, "loadApkAssets", String.class, boolean.class, boolean.class);
//                Object apkAssetsPlugin = loadApkAssets.invoke(sResourcesManager, "/sdcard/plugin-debug.apk", false, false);
//                Object apkAssetsHost = loadApkAssets.invoke(sResourcesManager, getApplicationContext().getPackageResourcePath(), true, false);
////                Object apkAssets = ReflectionHelper.invoke(resourcesManagerClass, sResourcesManager, "loadApkAssets", "/sdcard/plugin-debug.apk", false, false);
//                addApkAssetsMethod.invoke(assetManagerBuilderObj, apkAssetsHost);
//                addApkAssetsMethod.invoke(assetManagerBuilderObj, apkAssetsPlugin);
//
//                Log.e("getResources", "addApkAssetsMethod:" + addApkAssetsMethod.toString());
//                Method build = assetManagerBuilderClass.getDeclaredMethod("build");
//                build.setAccessible(true);
//                Log.e("getResources", "getResources4");
//                //最终获取这个assetManager对象
//                AssetManager assetManager = (AssetManager) build.invoke(assetManagerBuilderObj);
//                Log.e("getResources", "assetManager:" + assetManager.toString());
//                resources = new Resources(assetManager, resources1.getDisplayMetrics(), resources1.getConfiguration());
//                Log.e("getResources", "resources:" + resources.toString());
//                return resources;
//            } catch (Exception e) {
//                Log.e("getResources", "Exception:" + e.getMessage());
//            }
////            return resources1;
//            loadResources();
//            if (resources == null) {
//                return super.getResources();
//            } else {
//                return resources;
//            }
//        } else {
//            return resources;
//        }
//    }
}
