package com.good.diaodiaode.tebiediao;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

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
