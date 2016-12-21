package com.good.diaodiaode.tebiediao;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private File mPicFile;
    private Button bt;
    private Button btShowToast;
    private Button btshowclose;
    private Button btThreadHelp;
    private Button btnotification;
    private RelativeLayout rl;
    //    private Toast toast;
    private PopupWindow mPopupWindow;
    private static final int NOTIFICATION_FLAG = 1;
    Field field = null;
    Object obj = null;
    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    private RotateCirCleView rcc;
    private RotateCircleHelper mInstance;

    private static int value = 1;
    private Lock lock = new ReentrantLock();
    private Condition Condition456 = lock.newCondition();
    private Condition Condition789 = lock.newCondition();
    private Condition Condition101112 = lock.newCondition();
    private NotificationManager manager;
    private Button sendBroadcast;
    private Button rxjava;
    private Button linkage;
    private LinkageWheelPickerDialog mLinkageWheelPickerDialog;
    private ArrayList<LinkageDataBean> datas;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

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


    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mInstance.setTexts("认证成长值", "+1", "行为成长", "+10000", "业绩成长", "+200");
            mInstance.show();
            abortBroadcast();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt_takepic);
//        addSpringView(bt);
        rl = (RelativeLayout) findViewById(R.id.rl);
        btShowToast = (Button) findViewById(R.id.bt_showtoast);
        btshowclose = (Button) findViewById(R.id.bt_showclose);
        btThreadHelp = (Button) findViewById(R.id.thread_help);
        btnotification = (Button) findViewById(R.id.notification);
        sendBroadcast = (Button) findViewById(R.id.sendbroadcast);
        rxjava = (Button) findViewById(R.id.bt_rxjava);
        linkage = (Button) findViewById(R.id.linkage);
        addSpringView(rl);
        addSpringView(btShowToast);
        addSpringView(btshowclose);
        addSpringView(btThreadHelp);
        addSpringView(btnotification);
        addSpringView(sendBroadcast);

        mInstance = RotateCircleHelper.getInstance(getApplicationContext());

        IntentFilter intentFilter = new IntentFilter("com.cn.liz");
        intentFilter.setPriority(10);
        registerReceiver(new MyBroadcastReceiver(), intentFilter);

        IntentFilter intentFilter2 = new IntentFilter("com.cn.liz");
        intentFilter.setPriority(10);
        registerReceiver(new MyBroadcastReceiver(), intentFilter2);

        IntentFilter intentFilter3 = new IntentFilter("com.cn.liz");
        intentFilter.setPriority(10);
        registerReceiver(new MyBroadcastReceiver(), intentFilter3);


        btShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.cn.liz");
                sendOrderedBroadcast(intent, null);
//                startActivity(new Intent(MainActivity.this, SecActivity.class));
            }
        });

        btshowclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  先创建一个toast对象
                Toast toast = Toast.makeText(MainActivity.this, "永不消失的toast", Toast.LENGTH_SHORT);

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


        btThreadHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Lock lock = new ReentrantLock();
                Condition Condition456 = lock.newCondition();
                Condition Condition789 = lock.newCondition();

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        lock.lock();
//                        while (value<=3){
//
//                        }
//
//                    }
//                }).start();

                Thread threadA = new Thread(new ThreadA());
                Thread threadB = new Thread(new ThreadB());
                threadA.start();
                threadB.start();


            }
        });

        btnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent pendingIntent3 = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), 0);
                // 通过Notification.Builder来创建通知，注意API Level
                // API16之后才支持
                Notification notify3 = new Notification.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.background_alertdialog)
                        .setTicker("TickerText:" + "您有新短消息，请注意查收！")
                        .setContentTitle("Notification Title")
                        .setContentText("This is the notification message")
                        .setContentIntent(pendingIntent3).setNumber(1).setAutoCancel(true).getNotification(); // 需要注意build()是在API
                // level16及之后增加的，API11可以使用getNotificatin()来替代
                manager.notify(NOTIFICATION_FLAG, notify3);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示
            }
        });

        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.baidu.android.pushservice.action.RECEIVE");
                sendBroadcast(intent);
            }
        });

        mScaleSpring = mSpringSystem.createSpring();
        mScaleSpring.addListener(mSpringListener);

        final Observer<String>  observer= new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e("rxjava","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("rxjava","onError");
            }

            @Override
            public void onNext(String s) {
                Log.e("rxjava",s);
            }
        };

        final Observer<Student> observer2 = new Observer<Student>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {

            }
        };

        final Person per1 = new Person(123,"王二");
        Person per2 = new Person(234,"李三");
        final Person[] persons = {per1, per2};

        rxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.from(persons).map(new Func1<Person, Student>() {
                    @Override
                    public Student call(Person person) {
                        return getStudent(person.getId());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student student) {
                        return Observable.from(student.getFriendnames());
                    }
                }).subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                }).observeOn(AndroidSchedulers.mainThread())

                        .subscribe(observer);

//                observable.subscribe(observer2);
            }
        });


        datas = new ArrayList<>();
        for (int i = 0;i<10;i++){
            LinkageDataBean bean1 = new LinkageDataBean();
            bean1.setName("张"+i);
            ArrayList<LinkageDataBean> list2 = new ArrayList<>();
            bean1.setLinkageDataBeans(list2);
            for (int i2 = 0;i2<10;i2++){
                LinkageDataBean bean2 = new LinkageDataBean();
                bean2.setName("李"+i+i2);
                ArrayList<LinkageDataBean> list3 = new ArrayList<>();
                bean2.setLinkageDataBeans(list3);
                for (int i3 = 0;i3<10;i3++){
                    LinkageDataBean bean3 = new LinkageDataBean();
                    bean3.setName("赵"+i+i2+i3);
                    list3.add(bean3);
                }
                list2.add(bean2);
            }
            datas.add(bean1);
        }



        mLinkageWheelPickerDialog = new LinkageWheelPickerDialog.Builder()
                .setCancelStringId("关闭")
                .setTitleStringId("取消当前预约")
                .setTitleIsShow(true)
                .setCyclic(false)
                .setSureStringId("确定")
                .setWheelItemTextNormalColor(getResources().getColor(R.color.colorAccent))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimary))
                .setWheelItemTextSelectorSize(14)
                .setCallBack(new OnSelectChangedListener() {
                    @Override
                    public void onSelectChanged(int... selectItems) {

                    }
                })
                .setData(datas)
                .setCurrentItems(3,7,9)
                .build();

        linkage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinkageWheelPickerDialog.show(getSupportFragmentManager(),"mLinkageWheelPickerDialog");
            }
        });
    }

    private Student getStudent(int id) {
        return new Student(id,"撒冷");
    }

    class ThreadA implements Runnable{
        @Override
        public void run()
        {
            try
            {
                lock.lock();
                System.out.println("首先输出1-3");
                while(value<=3)
                {
                    System.out.println(value++);
                }
                Condition456.signal();
            }
            finally
            {
                lock.unlock();
            }

            try
            {
                lock.lock();
                while(value<=6)
                {
                    Condition789.await();
                }
                System.out.println("输出7-9");
                while(value<=9)
                {
                    System.out.println(value++);
                }
                Condition101112.signal();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }

        }
    }

    class ThreadB implements Runnable{
        @Override
        public void run()
        {
            try
            {
                lock.lock();
                while(value<=3)
                {
                    Condition456.await();
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally{
                lock.unlock();
            }

            try{
                lock.lock();
                System.out.println("输出4-6");
                while(value<=6)
                {
                    System.out.println(value++);
                }
                Condition789.signal();
            }
            finally
            {
                lock.unlock();
            }

            try
            {
                lock.lock();
                while(value<=9)
                {
                    Condition101112.await();
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally{
                lock.unlock();
            }

            try{
                lock.lock();
                System.out.println("输出10-12");
                while(value<=12)
                {
                    System.out.println(value++);
                }
            }
            finally
            {
                lock.unlock();
            }
        }
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

    private String mCurrentTouchViewTag;
    private Spring mScaleSpring;
    private BaseSpringSystem mSpringSystem = SpringSystem.create();
    private Map<String, View> mSpringViewMap = new HashMap<String, View>();

    /**
     * 添加默认动画View,
     * listview中的view需要给view加上tag
     *
     * @param view
     */
    protected void addSpringView(View view) {
        view.setOnTouchListener(this);
        mSpringViewMap.put(getSpringViewTag(view), view);
    }

    private String getSpringViewTag(View view) {
        final int id = view.getId();
        String tag = String.valueOf(view.getTag());
        if (TextUtils.isEmpty(tag)) {
            tag = String.valueOf(id);
        } else {
            tag += id;
        }
        return tag;
    }

    private View getSpringView(String tag) {
        return mSpringViewMap.get(tag);
    }

    private void setSpringValue(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScaleSpring.setEndValue(2);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mScaleSpring.setEndValue(0);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setSpringValue(event.getAction());
        mCurrentTouchViewTag = getSpringViewTag(v);
        return false;
    }

    private SimpleSpringListener mSpringListener = new SimpleSpringListener() {
        @Override
        public void onSpringUpdate(Spring spring) {
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.8);
//            Log.e("spring.getCurrentValue()",spring.getCurrentValue());
            Log.e("WTF_mappedValue",String.valueOf(mappedValue));
            View v = getSpringView(mCurrentTouchViewTag);
            if (v != null) {
                v.setScaleX(mappedValue);
                v.setScaleY(mappedValue);
            }
        }
    };

}
