package com.good.diaodiaode.tebiediao;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SecActivity extends AppCompatActivity {

    private File mPicFile;
    private Button bt;
    private Button btShowToast;
    private Button btshowclose;
    private RelativeLayout rl;
    //    private Toast toast;
    private PopupWindow mPopupWindow;
    Field field = null;
    Object obj = null;
    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    private RotateCirCleView rcc;
    private RotateCircleHelper mInstance;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    private ObjectAnimator createInAnimator(View view) {
        PropertyValuesHolder translationY1 = PropertyValuesHolder.ofFloat("translationY", 60f, -20f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, translationY1, alpha);
        //文字弹到最高时间
        animator.setDuration(160);
        return animator;
    }

    private ObjectAnimator createBackAnimator(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", -20f, 0f);
        //文字重最高回到中间的时间
        anim.setDuration(120);
        return anim;
    }

    private ObjectAnimator createOutAnimator(View view) {
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 0f, -60f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, translationY, alpha);
        //文字消失时间
        animator.setDuration(160);
        //文字在停留时间
        animator.setStartDelay(600);
        return animator;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.good.diaodiaode.tebiediao/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.good.diaodiaode.tebiediao/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mInstance.setTexts("认证成长值", "+1", "行为成长", "+10000", "业绩成长", "+200");
            mInstance.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        bt = (Button) findViewById(R.id.bt_takepic);
        rl = (RelativeLayout) findViewById(R.id.rl);
        btShowToast = (Button) findViewById(R.id.bt_showtoast);
        btshowclose = (Button) findViewById(R.id.bt_showclose);

        mInstance = RotateCircleHelper.getInstance(this);
//        mInstance.setTexts("认证成长", "+1", "行为成长", "+1000","业绩成长","+200");

        registerReceiver(new MyBroadcastReceiver(), new IntentFilter("com.cn.liz"));

        btShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mInstance.show();
                Intent intent = new Intent("com.cn.liz");
                sendBroadcast(intent);
//                finish();
            }
        });

        btshowclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  先创建一个toast对象
                Toast toast = Toast.makeText(SecActivity.this, "永不消失的toast", Toast.LENGTH_SHORT);

                //  设置toast信息提示框显示的位置（在屏幕顶部水平居中显示）
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                try {
                    //  从toast对象中获得mtn变量
                    Field field = toast.getClass().getDeclaredField("mTN");
                    field.setAccessible(true);
                    Object obj = field.get(toast);
                    //  tn对象中获得了show方法
                    Method method = obj.getClass().getDeclaredMethod("show");
                    //  调用show方法来显示toast信息提示框
                    method.invoke(obj);
                } catch (Exception e) {
                    Log.e("eeeee", e.getMessage());
                }

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

//    private void showToast() {
//        try {
//            Method method = obj.getClass().getDeclaredMethod("show", null);
//            method.invoke(obj, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void closeToast() {
//        try {
//            Method method = obj.getClass().getDeclaredMethod("hide", null);
//            method.invoke(obj, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
        }
    }

    private String getSystemPhotoPath() {
        String pathSaveParent = null;
        try {
            pathSaveParent = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .getAbsolutePath();
        } catch (Exception e) {
            Toast.makeText(this, "当前系统相册路径不可用,添加失败", Toast.LENGTH_SHORT);
        }
        return pathSaveParent;
    }

}
