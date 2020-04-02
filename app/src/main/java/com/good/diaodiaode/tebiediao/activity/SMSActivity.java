package com.good.diaodiaode.tebiediao.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.good.diaodiaode.tebiediao.R;
import com.good.diaodiaode.tebiediao.config.SMSSendResultReceiver;

import java.util.ArrayList;

public class SMSActivity extends AppCompatActivity {

    private SMSSendResultReceiver mSMSReceiver = new SMSSendResultReceiver();
    private IntentFilter mSMSResultFilter = new IntentFilter();
    private final String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    private final String RECEIVE_SMS_ACTION = "RECEIVE_SMS_ACTION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        findViewById(R.id.sendsms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("13524627023", "泉州市先爱上的地位阿达是的啊实打实大师");
            }
        });

        findViewById(R.id.startsms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSTo("13524627023;12232212256", "泉州市先爱上的地位阿达是的啊实打实大师");
            }
        });

        mSMSResultFilter.addAction(SENT_SMS_ACTION);
//        mSMSResultFilter.addAction(RECEIVE_SMS_ACTION);
        registerReceiver(mSMSReceiver, mSMSResultFilter);
    }

    /**
     * 直接调用短信接口发短信
     *
     * @param phoneNumber
     * @param message
     */
    public void sendSMS(String phoneNumber, String message) {
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(message)) {
            return;
        }
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)) {
            divideAndSendSMS(phoneNumber, message);
            Log.e("ListView", "短信发送成功");
        } else {
            Log.e("ListView", "申请发送短信权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 10001);
        }
    }

    private void divideAndSendSMS(String phoneNumber, String message) {
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();

        if (message.length() <= 70) {
            smsManager.sendTextMessage(phoneNumber, null, message, createPendingIntent(), createReceivePendingIntent());
        } else {
            ArrayList<String> divideContents = smsManager.divideMessage(message);
            ArrayList<PendingIntent> sentIntents = new ArrayList<>();
            for (int i = 0; i < divideContents.size(); i++) {
                sentIntents.add(createPendingIntent());
            }
            smsManager.sendMultipartTextMessage(phoneNumber, null, divideContents, sentIntents, null);
        }
    }

    public void sendSMSTo(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

    private PendingIntent createPendingIntent() {
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        sentIntent.putExtra("type", "send");
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sentIntent, 0);
        return sentPI;
    }

    private PendingIntent createReceivePendingIntent() {
        Intent sentIntent = new Intent(RECEIVE_SMS_ACTION);
        sentIntent.putExtra("type", "receive");
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sentIntent, 0);
        return sentPI;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 10001 && grantResults != null && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                divideAndSendSMS("13524627023", "泉州市先爱上的地位阿达是的啊实打实大师");
                Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "权限申请失败", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
