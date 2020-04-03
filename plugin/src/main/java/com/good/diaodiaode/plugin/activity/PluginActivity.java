package com.good.diaodiaode.plugin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class PluginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("xxxxxx", "插件PluginActivity启动成功");
    }
}
