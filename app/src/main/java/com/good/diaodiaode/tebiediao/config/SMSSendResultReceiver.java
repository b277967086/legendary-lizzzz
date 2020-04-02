package com.good.diaodiaode.tebiediao.config;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SMSSendResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("ListView", "getResultCode=" + getResultCode());
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Log.e("ListView", "接收短信-->type=" + intent.getStringExtra("type"));
                Toast.makeText(context,"发送成功",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(context,"发送失败",Toast.LENGTH_LONG).show();
                break;
        }
    }
}