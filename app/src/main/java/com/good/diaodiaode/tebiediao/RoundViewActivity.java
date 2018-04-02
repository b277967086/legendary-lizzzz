package com.good.diaodiaode.tebiediao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by lizheng on 2018/2/9.
 */

public class RoundViewActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_view);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        Picasso.with(this.getApplicationContext()).load("http://pic1.ymatou.com/G02/M00/E0/64/CgvUBVqRLW6AUufPAAZRDe_Zq6I951_1_1_o.jpg").into(iv);
    }

}
