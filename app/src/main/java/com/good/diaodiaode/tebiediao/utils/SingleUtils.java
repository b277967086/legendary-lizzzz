package com.good.diaodiaode.tebiediao.utils;

import android.util.Log;

/**
 * 单例测试
 * Created by lizheng on 2018/4/13.
 */

public class SingleUtils {


    public SingleUtils() {
        Log.e("INSANTCE", "INSANTCE" + toString());
    }

    public static SingleUtils INSANTCE = new SingleUtils();

    public static SingleUtils getInstance() {
        return INSANTCE;
    }
    public static void test() {
    }

}
