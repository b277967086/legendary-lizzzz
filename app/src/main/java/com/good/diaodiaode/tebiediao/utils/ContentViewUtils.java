package com.good.diaodiaode.tebiediao.utils;

import android.app.Activity;

import com.good.diaodiaode.tebiediao.annotation.ContentView;

import java.lang.reflect.Method;

/**
 * Created by lzzzz on 2017/6/27.
 */

public class ContentViewUtils {

    public static void init(Activity activity){
        Class<? extends Activity> aClass = activity.getClass();
        ContentView annotation = aClass.getAnnotation(ContentView.class);
        try {
            Method setContentView = aClass.getMethod("setContentView", int.class);
            setContentView.invoke(activity,annotation.id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
