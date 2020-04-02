package com.good.diaodiaode.tebiediao.hook;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.good.diaodiaode.tebiediao.activity.PluginStubActivity;

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
        if (method.getName().equals("startActivity")) {
            Log.e("xxxxxx", "hook_startActivity");
            for (int index = 0; index < args.length; index++) {
                if (args[index] instanceof Intent) {
                    Intent intent = new Intent();
                    intent.setClassName("com.good.diaodiaode.tebiediao.activity","PluginStubActivity");
                    args[index] = intent;
                }
            }
        }
        return method.invoke(iActivityManagerObj,args);
    }
}
