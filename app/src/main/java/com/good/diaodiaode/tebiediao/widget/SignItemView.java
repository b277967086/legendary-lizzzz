package com.good.diaodiaode.tebiediao.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.good.diaodiaode.tebiediao.R;

public class SignItemView extends LinearLayout {

    private TextView tv_reward_top;
    private TextView tv_reward_bottom;
    private TextView tv_day;

    public SignItemView(Context context) {
        super(context);
        init();
    }

    public SignItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_sign_item, this);
        setGravity(Gravity.CENTER_HORIZONTAL);

        tv_reward_top = findViewById(R.id.tv_reward_top);
        tv_reward_bottom = findViewById(R.id.tv_reward_bottom);
        tv_day = findViewById(R.id.tv_day);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        tv_reward_top.setSelected(selected);
        tv_reward_bottom.setSelected(selected);
        if (selected) {
            tv_reward_top.setTextColor(Color.parseColor("#A3A3A3"));
            tv_reward_bottom.setTextColor(Color.parseColor("#A4A4A4"));
            tv_day.setTextColor(Color.parseColor("#A5A5A5"));
        } else {
            tv_reward_top.setTextColor(Color.parseColor("#FFFFFF"));
            tv_reward_bottom.setTextColor(Color.parseColor("#9F6124"));
            tv_day.setTextColor(Color.parseColor("#333333"));
        }
    }

    public void setData(int topCoin, int bottomCoin, String day) {
        if (topCoin > 0) {
            tv_reward_top.setText(String.format("+%d", topCoin));
        }

        if (bottomCoin > 0) {
            tv_reward_bottom.setText(String.valueOf(bottomCoin));
            tv_reward_bottom.setVisibility(VISIBLE);
        }else {
            tv_reward_bottom.setVisibility(INVISIBLE);
        }

        tv_day.setText(day);
    }
}
