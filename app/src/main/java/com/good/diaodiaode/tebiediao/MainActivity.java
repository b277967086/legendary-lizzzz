package com.good.diaodiaode.tebiediao;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.good.diaodiaode.tebiediao.db.DatabaseHelper;
import com.good.diaodiaode.tebiediao.db.User;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.j256.ormlite.dao.Dao;
import com.yanzhenjie.album.Album;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@ContentView(id = R.layout.activity_main)
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
    private DecelerateInterpolator accelerateDecelerateInterpolator = new DecelerateInterpolator();
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
    private Button uploadProgress;
    private LinkageWheelPickerDialog mLinkageWheelPickerDialog;
    private ArrayList<LinkageDataBean> datas;
    private Button kedaxunfei;
    private EditText mResultText;
    private Button ormlite;
    private Button permisstion;
    private Button singleinstance;
    private Button singleInstance;
    private Button video;

    IFuckInterface.Stub stub = new IFuckInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getInt() throws RemoteException {
            return 7782;
        }
    };
    private Button testmanifest;
    private Button testcontract;
    private Button maptojson;
    private ImageView iv;
    private Button startmain3;
    private Button startsms;
    private Button startpicpick;
    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 332;
    private int PERMISSION_WRITE_SDCARD  =484;

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mInstance.setTexts("认证成长值", "+1", "行为成长", "+10000", "业绩成长", "+200");
            mInstance.show();
            abortBroadcast();
        }
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d("TAG", "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(MainActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SDCardUtils.readSDCard();
//        SDCardUtils.readSystem(this);
//        SDCardUtils.readPathRoot();
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 444);
        ContentViewUtils.init(this);
//        setContentView(R.layout.activity_main);
//        NumberUtils.getBAt(4);
//        NumberUtils.quickSort(new int[]{8,4,9,7,6,5},0,5);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=58a2bcff");

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
        uploadProgress = (Button) findViewById(R.id.uploadProgress);
        kedaxunfei = (Button) findViewById(R.id.kedaxunfei);
        mResultText = (EditText) findViewById(R.id.mResultText);
        ormlite = (Button) findViewById(R.id.ormlite);
        permisstion = (Button) findViewById(R.id.permisstion);
        singleinstance = (Button) findViewById(R.id.singleinstance);
        singleInstance = (Button) findViewById(R.id.singleInstance);
        video = (Button) findViewById(R.id.video);
        testmanifest = (Button) findViewById(R.id.testmanifest);
        testcontract = (Button) findViewById(R.id.testcontract);
        maptojson = (Button) findViewById(R.id.testmap2json);
        iv = (ImageView) findViewById(R.id.iv_asdf);
        startmain3 = (Button) findViewById(R.id.startmain3);
        startsms = (Button) findViewById(R.id.startsms);
        startpicpick = (Button) findViewById(R.id.startpicpick);
//        addSpringView(rl);
        addSpringView(btShowToast);
        addSpringView(btshowclose);
        addSpringView(btThreadHelp);
        addSpringView(btnotification);
        addSpringView(sendBroadcast);

        mInstance = RotateCircleHelper.getInstance(getApplicationContext());

//        IntentFilter intentFilter = new IntentFilter("com.cn.liz");
//        intentFilter.setPriority(10);
//        registerReceiver(new MyBroadcastReceiver(), intentFilter);
//
//        IntentFilter intentFilter2 = new IntentFilter("com.cn.liz");
//        intentFilter.setPriority(10);
//        registerReceiver(new MyBroadcastReceiver(), intentFilter2);
//
//        IntentFilter intentFilter3 = new IntentFilter("com.cn.liz");
//        intentFilter.setPriority(10);
//        registerReceiver(new MyBroadcastReceiver(), intentFilter3);


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

        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e("rxjava", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("rxjava", "onError");
            }

            @Override
            public void onNext(String s) {
                Log.e("rxjava", s);
            }
        };

        final Observer<Student> observer2 = new Observer<Student>() {

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

        final Person per1 = new Person(123, "王二");
        Person per2 = new Person(234, "李三");
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
        for (int i = 0; i < 10; i++) {
            LinkageDataBean bean1 = new LinkageDataBean();
            bean1.setName("张" + i);
            ArrayList<LinkageDataBean> list2 = new ArrayList<>();
            bean1.setLinkageDataBeans(list2);
            for (int i2 = 0; i2 < 10; i2++) {
                LinkageDataBean bean2 = new LinkageDataBean();
                bean2.setName("李" + i + i2);
                ArrayList<LinkageDataBean> list3 = new ArrayList<>();
                bean2.setLinkageDataBeans(list3);
                for (int i3 = 0; i3 < 10; i3++) {
                    LinkageDataBean bean3 = new LinkageDataBean();
                    bean3.setName("赵" + i + i2 + i3);
                    list3.add(bean3);
                }
                list2.add(bean2);
            }
            datas.add(bean1);
        }


//        ArrayList<LinkageDataBean> list1 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            LinkageDataBean bean1 = new LinkageDataBean();
//            bean1.setName("张" + i);
//            list1.add(bean1);
//        }
//
//        ArrayList<LinkageDataBean> list2 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            LinkageDataBean bean1 = new LinkageDataBean();
//            bean1.setName("李" + i);
//            list2.add(bean1);
//        }
//
//        ArrayList<LinkageDataBean> list3 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            LinkageDataBean bean1 = new LinkageDataBean();
//            bean1.setName("王" + i);
//            list3.add(bean1);
//        }

        mLinkageWheelPickerDialog = new LinkageWheelPickerDialog.Builder()
//                .setCyclic(false)
//                .setCancelStringId("关闭")
//                .setTitleStringId("取消当前预约")
//                .setTitleIsShow(true)
//                .setSureStringId("确定")
//                .setThemeColor(getResources().getColor(R.color.colorPrimaryDark))
//                .setWheelItemTextNormalColor(getResources().getColor(R.color.colorAccent))
//                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimary))
//                .setWheelItemTextSelectorSize(14)
                .setCallBack(new OnSelectChangedListener() {
                    @Override
                    public void onSelectChanged(int... selectItems) {

                    }
                })
                .setCanLinkaged(true)
                .setLinkedData(datas)
//                .addNoLinkedData(list1)
//                .addNoLinkedData(list2)
//                .addNoLinkedData(list3)
                .setCurrentItems(3, 2)
                .build();

        linkage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinkageWheelPickerDialog.show(getSupportFragmentManager(), "mLinkageWheelPickerDialog");
            }
        });

        uploadProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UploadProgressBarDialog uploadProgressBarHelper = UploadProgressBarDialog.create(getApplicationContext());
                uploadProgressBarHelper.show();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        uploadProgressBarHelper.setUploadComplete();
                    }
                }, 5000L);
            }
        });


        //1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(getApplicationContext(), mInitListener);
        //2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");

        //听写监听器
        RecognizerListener mRecoListener = new RecognizerListener() {
            //听写结果回调接口(返回Json格式结果，用户可参见附录12.1)；
            //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
            // 关于解析Json的代码可参见MscDemo中JsonParser类；
            //isLast等于true时会话结束。
            public void onResult(RecognizerResult results, boolean isLast) {
                Log.d("Result:", results.getResultString());
            }

            //会话发生错误回调接口
            public void onError(SpeechError error) {
                error.getPlainDescription(true); //获取错误码描述}
                //开始录音
            }

            //音量值0~30
            @Override
            public void onVolumeChanged(int i, byte[] bytes) {

            }

            public void onBeginOfSpeech() {
            }

            //结束录音
            public void onEndOfSpeech() {
            }

            //扩展用接口
            public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            }
        };

        //1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        final RecognizerDialog iatDialog = new RecognizerDialog(this, mInitListener);
//2.设置听写参数，同上节
//3.设置回调接口
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult);
                if (b) {
                } else {

                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });

        //4.开始听写
        mIat.startListening(mRecoListener);

        kedaxunfei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3.开始听写
//                mIat.startListening(mRecoListener);
                iatDialog.show();
            }
        });

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        Dao<User, Integer> userDao;
        try {
            userDao = databaseHelper.getUserDao();
            userDao.createOrUpdate(new User("xiao1", "asdasdas"));
            userDao.createIfNotExists(new User("xiao2", "asdasdas"));
            userDao.createIfNotExists(new User("xiao3", "asdasdas"));
//            User user = userDao.queryForId(3);
            List<User> users = userDao.queryBuilder().where().eq("user_desc", "asdasdas").query();
            Log.v("ormlite", users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ormlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        permisstion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = getPackageManager().checkPermission(Manifest.permission.RECORD_AUDIO, getPackageName());
                if (flag == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 123);
                } else if (flag == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "直接开工", Toast.LENGTH_SHORT).show();
                }
            }
        });

        singleinstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int i = 0;
//                while (i < 10) {
//                    SingleUtils.getInstance();
//                    i++;
//                }
                SingleUtils.test();
            }
        });

        singleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecActivity.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        testmanifest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.good.diaodiaode.tebiediao.TestManifestActivity");
                startActivity(intent);
            }
        });

        testcontract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_PICK);
                i.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
        maptojson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map1 = new HashMap();
                map1.put("asdasd", "sdfsd电饭锅");
                map1.put("asdasdasd", "sdfasdsd电饭sdogjf锅");
                map1.put("asdaetposd", "sdfsd电sdfg饭锅");
                map1.put("assfdgdasd", "sdfsd电饭锅");
                map1.put("asdfghasd", "sdfsd电fghjf饭锅");


                JSONObject jsonObject = new JSONObject(map1);
                Log.e("textMap2Json", jsonObject.toString());


            }
        });

        startmain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });

        startsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SMSActivity.class));
            }
        });

        startpicpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    requestPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_WRITE_SDCARD);
                    return;
                }
                Album.startAlbum(MainActivity.this, ACTIVITY_REQUEST_SELECT_PHOTO
                        , 9
                        , ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)
                        , ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
            }
        });


        Log.d("test_isSameDay",""+ SystemClock.elapsedRealtime());
        Log.d("test_isSameDay",""+System.currentTimeMillis());
        Log.d("test_isSameDay",""+isSameDay(1554998399,System.currentTimeMillis()));
    }
    public static boolean checkPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }


    public static void requestPermission(Activity activity, String permission, int requestCode) {
        if (checkPermission(activity, permission)) {
            return;
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }
    public static boolean isSameDay(long startTime, long endTime) {
        if (String.valueOf(startTime).length() == 10) {
            startTime = startTime * 1000L;
        }
        if (String.valueOf(endTime).length() == 10) {
            endTime = endTime * 1000L;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(startTime);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(endTime);
        return startCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 123) {
            if (permissions[0].equals(Manifest.permission.RECORD_AUDIO)) {

                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.RECORD_AUDIO},123);
                    boolean isSecond = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[0]);
                    if (isSecond) {
                        ActivityCompat.requestPermissions(MainActivity.this, permissions, 321);
                    } else {
                        Toast.makeText(MainActivity.this, "没开通权限", Toast.LENGTH_SHORT).show();
                    }
                } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "开通权限,开工", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode == 444){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean ahendiao = SDCardUtils.saveFileToSDCardCustomDir(new byte[]{1, 2, 3}, "ahendiao", "lee123.txt");
                Toast.makeText(MainActivity.this,""+ahendiao , Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == PERMISSION_WRITE_SDCARD){
            Album.startAlbum(MainActivity.this, ACTIVITY_REQUEST_SELECT_PHOTO
                    , 9
                    , ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)
                    , ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private Student getStudent(int id) {
        return new Student(id, "撒冷");
    }

    class ThreadA implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("首先输出1-3");
                while (value <= 3) {
                    System.out.println(value++);
                }
                Condition456.signal();
            } finally {
                lock.unlock();
            }

            try {
                lock.lock();
                while (value <= 6) {
                    Condition789.await();
                }
                System.out.println("输出7-9");
                while (value <= 9) {
                    System.out.println(value++);
                }
                Condition101112.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        mResultText.setText(resultBuffer.toString());
        mResultText.setSelection(mResultText.length());
    }

    class ThreadB implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                while (value <= 3) {
                    Condition456.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            try {
                lock.lock();
                System.out.println("输出4-6");
                while (value <= 6) {
                    System.out.println(value++);
                }
                Condition789.signal();
            } finally {
                lock.unlock();
            }

            try {
                lock.lock();
                while (value <= 9) {
                    Condition101112.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            try {
                lock.lock();
                System.out.println("输出10-12");
                while (value <= 12) {
                    System.out.println(value++);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 123) {

        } else if (requestCode == 1) {
            String name = "";
            String phoneNumber = "";
            switch (resultCode) {
                case RESULT_OK:
                    switch (requestCode) {
                        case 1:
                            if (data == null) {
                                return;
                            }
                            Uri contactData = data.getData();
                            if (contactData == null) {
                                return;
                            }
                            Cursor cursor = managedQuery(contactData, null, null, null,
                                    null);
                            if (cursor.moveToFirst()) {
                                name = cursor
                                        .getString(cursor
                                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                String hasPhone = cursor
                                        .getString(cursor
                                                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                                String id = cursor.getString(cursor
                                        .getColumnIndex(ContactsContract.Contacts._ID));
                                if (hasPhone.equalsIgnoreCase("1")) {
                                    hasPhone = "true";
                                } else {
                                    hasPhone = "false";
                                }
                                if (Boolean.parseBoolean(hasPhone)) {
                                    Cursor phones = getContentResolver()
                                            .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                    null,
                                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                            + " = " + id, null, null);
                                    while (phones.moveToNext()) {
                                        phoneNumber = phones
                                                .getString(phones
                                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    }
                                    phones.close();
                                }

                                cursor.close();
                            }
                            Log.i("info", "联系人：" + name + "--"
                                    + (phoneNumber));

                            break;
                    }
                    break;
            }

        }else if (requestCode == ACTIVITY_REQUEST_SELECT_PHOTO) {
            if (resultCode == RESULT_OK) { // 判断是否成功。
                // 拿到用户选择的图片路径List：
                List<String> pathList = Album.parseResult(data);
                Log.e("onActivityResult",pathList.toString());
            } else if (resultCode == RESULT_CANCELED) { // 用户取消选择。
                // 根据需要提示用户取消了选择。
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
            Log.e("WTF_mappedValue", String.valueOf(mappedValue));
            View v = getSpringView(mCurrentTouchViewTag);
            if (v != null) {
                v.setScaleX(mappedValue);
                v.setScaleY(mappedValue);
            }
        }
    };

}
