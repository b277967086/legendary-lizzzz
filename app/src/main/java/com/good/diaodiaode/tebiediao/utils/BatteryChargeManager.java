package com.good.diaodiaode.tebiediao.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

public class BatteryChargeManager {

    private static final BatteryChargeManager ourInstance = new BatteryChargeManager();

    private Context mContext;
    private boolean swith;
    //    private JsonObject config;
    private BatteryReceiver mBatteryReceiver;
    private SchduleTimeTask schduleTimeTask;
    private volatile boolean isCharging;
    private volatile boolean isFull;
    private volatile long startTimes;
    private volatile long endTimes;
    private int full_rate = 2;
    private int rate = 1;

    public static BatteryChargeManager getInstance() {
        return ourInstance;
    }

    private BatteryChargeManager() {
    }

    public void setConfig(@NonNull Context context) {
        if (context != null) {
            mContext = context.getApplicationContext();
        }
        try {
            swith = true;
            if (swith) {
                register(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(Context context) {
        if (mBatteryReceiver == null) {
            mBatteryReceiver = new BatteryReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        if (mContext != null) {
            mContext.registerReceiver(mBatteryReceiver, filter);
        }
    }

    private void startTimeTick(Context context) {
        if (schduleTimeTask == null) {
            long delay = endTimes > startTimes ? 60 * 1000 - (endTimes - startTimes) : 0;
            startTimes = 0;
            endTimes = 0;
            schduleTimeTask = new SchduleTimeTask(60 * 1000, delay, new TimerTask() {
                @Override
                public void run() {
                    // TODO: 2020/5/25 请求接口
                    int integral;
                    //充电状态定时上报给后端
                    if (isCharging) {
                        if (isFull) {
                            integral = 60 / full_rate;
                        } else {
                            integral = 60 / rate;
                        }
//                        com.jifen.qukan.http.RequestParams params = com.jifen.qukan.http.RequestParams.Builder
//                                .get(Constants.URL_DISLIKE_SHIELD)
//                                .addParam("integral", integral)
//                                .build();
//                        HttpHelper.request(context, params);
                    }

                }
            });
            schduleTimeTask.start();
        }
    }

    public void unRegister() {
        if (mBatteryReceiver != null && mContext != null) {
            mContext.unregisterReceiver(mBatteryReceiver);
        }
        stopTimeTick();
    }

    private void stopTimeTick() {
        if (schduleTimeTask != null) {
            schduleTimeTask.stop();
            schduleTimeTask = null;
        }
    }

    private class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String acyion = intent.getAction();
                switch (acyion) {
                    case Intent.ACTION_POWER_CONNECTED://接通电源
                        isCharging = true;
                        startTimes = System.currentTimeMillis();
                        startTimeTick(context);
                        Toast.makeText(context, "开始充电", Toast.LENGTH_SHORT).show();
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED://拔出电源
                        isCharging = false;
                        endTimes = System.currentTimeMillis();
                        stopTimeTick();
                        Toast.makeText(context, "结束充电", Toast.LENGTH_SHORT).show();
                        break;

                    case Intent.ACTION_BATTERY_CHANGED://电量发生改变
                        isFull(intent);

                        break;
                }
            }
        }

        private void isFull(Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);  //当前电量
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1); //电量的刻度
            Log.e("zhang", "isFull-----" + "level:" + level + ";scale:" + scale);

            if (scale > 0 && level * 100 / scale < 100) {
                isFull = false;
            }

            int status = intent.getIntExtra(BatteryManager.EXTRA_SCALE, BatteryManager.BATTERY_STATUS_UNKNOWN);
            if (BatteryManager.BATTERY_STATUS_FULL == status) {
                isFull = true;
            }
        }
    }


    public static class SchduleTimeTask {
        private Timer timer;
        private TimerTask task;
        private long period;
        private long delay;

        public SchduleTimeTask(long period, long delay, TimerTask task) {
            this.task = task;
            this.period = period;
            this.delay = delay;
            if (timer == null) {
                timer = new Timer();
            }
        }

        public void start() {
            timer.schedule(task, delay, period);//每隔time时间段就执行一次
        }

        public void stop() {
            if (timer != null) {
                timer.cancel();
                if (task != null) {
                    task.cancel();  //将原任务从队列中移除
                }
            }
        }
    }

    public interface BatteryStateListener {
        public void onStateChanged();

        public void onStateLow();

        public void onStateOkay();

        public void onStatePowerConnected();

        public void onStatePowerDisconnected();
    }

}



