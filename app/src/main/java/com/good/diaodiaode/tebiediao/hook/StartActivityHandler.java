package com.good.diaodiaode.tebiediao.hook;

import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create by leeZheng at 2019/3/8
 * hook住，并用动态代理替换activtyService
 */
public class StartActivityHandler implements InvocationHandler {
    private Object iActivityManagerObj;

    public StartActivityHandler(Object iActivityManagerObj) {
        this.iActivityManagerObj = iActivityManagerObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contains("startActivity")) {
            Log.e("StartActivityHandler", "hook");
            Log.e("StartActivityHandler", iActivityManagerObj.getClass().getCanonicalName());
        }
        return method.invoke(iActivityManagerObj, args);
    }
}
