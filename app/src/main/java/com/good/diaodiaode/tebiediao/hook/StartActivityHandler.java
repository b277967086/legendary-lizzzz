package com.good.diaodiaode.tebiediao.hook;

import android.content.Context;
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
    private Context cotext;
    private Object mIActivityManager;
    public static Intent orginIntent;

    public StartActivityHandler(Context context, Object mIActivityManager) {
        this.mIActivityManager = mIActivityManager;
        this.cotext = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("startActivity") && args != null && args.length > 0) {
            for (int index = 0; index < args.length; index++) {
                if (args[index] instanceof Intent) {
                    orginIntent = (Intent) args[index];
                    Intent intent = new Intent();
                    intent.setClass(cotext, PluginStubActivity.class);
                    args[index] = intent;
                }
            }
        }
        return method.invoke(mIActivityManager, args);
    }
}
