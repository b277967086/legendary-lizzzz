package com.good.diaodiaode.tebiediao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.good.diaodiaode.tebiediao.R;

/**
 * Created by ZHUTIANJIAN133 on 2016-08-10.
 */
public class PADialog extends Dialog {
    private Context context;
    private TextView okBtn;
    private TextView cancelBtn;
    private TextView titleTv;
    private TextView contentTv;
    private static int mTheme = R.style.PADialog;

    public PADialog(Context context) {
        super(context, mTheme);
        this.context = context;
    }

    public PADialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.base_alertdialog);
        okBtn = (TextView) findViewById(R.id.positive_btn);
        cancelBtn = (TextView) findViewById(R.id.negative_btn);
        titleTv = (TextView) findViewById(R.id.dialog_title);
        contentTv = (TextView) findViewById(R.id.dialog_content);
    }

    public void setAllText(String title, String content, String okBtnText, String cancelBtnText) {
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        }
        if (!TextUtils.isEmpty(content)) {
            contentTv.setText(content);
        }
        if (!TextUtils.isEmpty(okBtnText)) {
            okBtn.setText(okBtnText);
        }
        if (!TextUtils.isEmpty(cancelBtnText)) {
            cancelBtn.setText(cancelBtnText);
        }
    }

    public void setClickListener(View.OnClickListener okClickListener, View.OnClickListener cancelClickListener) {
        okBtn.setOnClickListener(okClickListener);
        if (cancelClickListener != null) {
            cancelBtn.setVisibility(View.VISIBLE);
            cancelBtn.setOnClickListener(cancelClickListener);
        }
        else {
            cancelBtn.setVisibility(View.GONE);
        }
    }

    public TextView getOkBtn() {
        return okBtn;
    }
}
