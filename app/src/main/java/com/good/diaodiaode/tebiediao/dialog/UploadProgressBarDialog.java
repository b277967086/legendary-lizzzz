package com.good.diaodiaode.tebiediao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.good.diaodiaode.tebiediao.R;
import com.good.diaodiaode.tebiediao.widget.UpLoadProgressView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ex-lizheng102 on 2016-09-01.
 */
public class UploadProgressBarDialog {

    private Dialog mDialog;
    private Context mContext;

    private UpLoadProgressView mUpLoadProgressView;
    private TextView textView;

    private UploadProgressBarDialog(Context ctx) {
        mContext = ctx;
        init(mContext);
    }

    public static UploadProgressBarDialog create(Context ctx) {
        return new UploadProgressBarDialog(ctx);
    }


    private void init(Context ctx) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.upload_progress_dialog_layout, null);
        textView = (TextView) view.findViewById(R.id.tv_content1);

        mDialog = new Dialog(ctx, R.style.PADialog);
        //设置成全局的dialog
        mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        //去掉dialog区域外的灰色透明蒙版
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(view);

        mUpLoadProgressView = (UpLoadProgressView) view.findViewById(R.id.rotateCirCleView);
        mUpLoadProgressView.setOnCompleteListener(new UpLoadProgressView.OnCompleteListener() {
            @Override
            public void onComplete() {
                if (mDialog != null && mDialog.isShowing()) {

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mDialog.dismiss();
                        }
                    }, 1000L);
                }
            }
        });
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
            mUpLoadProgressView.setOnDraw(true);
            mUpLoadProgressView.start();
//            new Thread(mUpLoadProgressView).start();
        }
    }

    //设置上传完成
    public void setUploadComplete() {
        mUpLoadProgressView.setUploadComplete(true);
    }

    //设置文字内容
    public void setTextContent(String content) {
        textView.setText(content);
    }
}
