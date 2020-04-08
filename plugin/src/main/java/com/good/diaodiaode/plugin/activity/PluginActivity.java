package com.good.diaodiaode.plugin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.jifen.qukan.plugin.R;

public class PluginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        Log.e("xxxxxx", "插件PluginActivity启动成功");
    }
}
