package com.good.diaodiaode.plugin.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.jifen.qukan.plugin.R;

public class PluginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("xxxxxx", "onCreate");
        Resources resources = getResources();
        Log.e("xxxxxx", "resources:"+resources.toString());
        XmlResourceParser layout = resources.getLayout(R.layout.activity_plugin);
        Log.e("xxxxxx", layout.toString());
        setContentView( LayoutInflater.from(this).inflate(layout,null));
        Log.e("xxxxxx", "插件PluginActivity启动成功");
    }
}
