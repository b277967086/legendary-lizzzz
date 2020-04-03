package com.good.diaodiaode.tebiediao.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.good.diaodiaode.tebiediao.R;

public class TestPluginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_plugin);
        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.jifen.qukan.plugin.activity", "com.jifen.qukan.plugin.activity.PluginActivity"));
                startActivity(intent);
            }
        });
    }
}
