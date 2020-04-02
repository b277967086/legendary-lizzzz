package com.good.diaodiaode.tebiediao.service;

import android.app.IActivityController;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.good.diaodiaode.tebiediao.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class FloatWindowService extends Service {
    public FloatWindowService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setActivityController();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


//        try {
//            Class<?> clas = Class.forName("android.app.ActivityManager");
//            Method method = clas.getMethod("getService");
//
//            method.invoke(clas.get)
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ActivityManager.getService()
//        try {
//
//            mgr.setActivityController(new IActivityController.Stub());
//
//        } catch (Exception e) {
//
//        }


        final WindowManager.LayoutParams floatingBallLayoutParams = new WindowManager.LayoutParams();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {

            floatingBallLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {

            floatingBallLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            floatingBallLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;

        } else {

            floatingBallLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        final WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        final ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        floatingBallLayoutParams.height = 200;
        floatingBallLayoutParams.width = 200;
        floatingBallLayoutParams.x = 300;
        floatingBallLayoutParams.y = 300;
        floatingBallLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatWindowService.this, "被系统弹窗拦截了", Toast.LENGTH_SHORT).show();
            }
        });
        windowManager.addView(imageView, floatingBallLayoutParams);

        imageView.setOnTouchListener(new View.OnTouchListener() {

            boolean isMove = false;
            int mTouchStartX = 0;
            int mTouchStartY = 0;
            int mStartX = 0;
            int mStartY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();


                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        isMove = false;
                        mTouchStartX = (int) event.getRawX();
                        mTouchStartY = (int) event.getRawY();
                        mStartX = (int) event.getX();
                        mStartY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int mTouchCurrentX = (int) event.getRawX();
                        int mTouchCurrentY = (int) event.getRawY();
                        floatingBallLayoutParams.x += mTouchCurrentX - mTouchStartX;
                        floatingBallLayoutParams.y += mTouchCurrentY - mTouchStartY;
                        Log.e("LayoutParams", "mLayoutParams.x:" + floatingBallLayoutParams.x + ";mLayoutParams.y:" + floatingBallLayoutParams.y);
                        windowManager.updateViewLayout(imageView, floatingBallLayoutParams);

                        mTouchStartX = mTouchCurrentX;
                        mTouchStartY = mTouchCurrentY;
                        break;
                    case MotionEvent.ACTION_UP:
                        int mStopX = (int) event.getX();
                        int mStopY = (int) event.getY();
                        if (Math.abs(mStartX - mStopX) >= 1 || Math.abs(mStartY - mStopY) >= 1) {
                            isMove = true;
                        }
                        break;
                }
                //如果是移动事件不触发OnClick事件，防止移动的时候一放手形成点击事件
                return isMove;

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void setActivityController() {
        try {
            //1
            Class<?> aClass = Class.forName("android.app.ActivityManager");
            Method method = aClass.getMethod("getService");
            Object obj = method.invoke(null);


            //2同1
            Class activityManagerclass = Class.forName("android.app.ActivityManager");
            Field iActivityManagerSingleton = activityManagerclass.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingleton.setAccessible(true);
            Object SingletonObj = iActivityManagerSingleton.get(null);
            Class<?> singtonClass = Class.forName("android.util.Singleton");
            Field iActivityManagerF = singtonClass.getDeclaredField("mInstance");
            iActivityManagerF.setAccessible(true);
            Object iActivityManagerObj = iActivityManagerF.get(SingletonObj);

            Log.e("object_equal", String.valueOf(obj == iActivityManagerObj));

            Method[] methods = obj.getClass().getDeclaredMethods();

            Log.e("name", obj.getClass().getSimpleName());
            for (int i = 0; i < methods.length; i++) {
                Log.e("methods", methods[i].getName());
            }

            Method method2 = obj.getClass().getDeclaredMethod("setActivityController", ActivityController.class);
            method2.invoke(obj, new ActivityController());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ActivityController extends IActivityController.Stub {

        private final String TAG = ActivityController.class.getSimpleName();

        @Override
        public boolean activityResuming(String pkg) throws RemoteException {
            Log.e(TAG, "activityResuming -- " + pkg);
            return true;
        }

        @Override
        public boolean activityStarting(String pkg)
                throws RemoteException {
            Log.e(TAG, "activityStarting -- " + pkg + " intent=");
            return true;
        }

        @Override
        public boolean appCrashed(String processName, int pid, String shortMsg, String longMsg,
                                  long timeMillis, String stackTrace) throws RemoteException {
            Log.e(TAG, "appCrashed -- " + processName);
            return true;
        }

        @Override
        public int appEarlyNotResponding(String processName, int pid, String annotation)
                throws RemoteException {
            Log.e(TAG, "appEarlyNotResponding -- " + processName);
            return 0;
        }

        @Override
        public int appNotResponding(String processName, int pid, String processStats)
                throws RemoteException {
            Log.e(TAG, "processName -- " + processName);
            return 0;
        }

    }
}
