package com.good.diaodiaode.tebiediao.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.good.diaodiaode.tebiediao.R;
import com.good.diaodiaode.tebiediao.model.SignInProgressServerModel;
import com.good.diaodiaode.tebiediao.model.SignItemModel;
import com.good.diaodiaode.tebiediao.utils.BatteryChargeManager;
import com.good.diaodiaode.tebiediao.widget.SignPercentView;

import java.util.ArrayList;
import java.util.Arrays;

public class SignViewActivity extends Activity {

    static SignInProgressServerModel.SignInBean signInBean;

    static {
        signInBean = new SignInProgressServerModel.SignInBean();
        signInBean.setToday(5);
        SignInProgressServerModel.SignInBean.AmountBean amountBean = new SignInProgressServerModel.SignInBean.AmountBean();
        amountBean.set_$1(198);
        amountBean.set_$2(198);
        amountBean.set_$3(198);
        amountBean.set_$4(198);
        amountBean.set_$5(198);
        amountBean.set_$6(198);
        amountBean.set_$7(1980);
        amountBean.set_$8(198);
        amountBean.set_$9(198);
        amountBean.set_$10(198);
        amountBean.set_$11(198);
        amountBean.set_$12(198);
        amountBean.set_$13(198);
        amountBean.set_$14(1980);
        amountBean.set_$15(198);
        amountBean.set_$16(198);
        amountBean.set_$17(198);
        amountBean.set_$18(198);
        amountBean.set_$19(198);
        amountBean.set_$20(198);
        amountBean.set_$21(1980);
        amountBean.set_$22(198);
        amountBean.set_$23(198);
        amountBean.set_$24(198);
        amountBean.set_$25(198);
        amountBean.set_$26(198);
        amountBean.set_$27(198);
        amountBean.set_$28(198);
        amountBean.set_$29(198);
        amountBean.set_$30(1980);
        signInBean.setAmount(amountBean);

        SignInProgressServerModel.SignInBean.ExtRewardBean amountBean1 = new SignInProgressServerModel.SignInBean.ExtRewardBean();
        amountBean1.set_$1(198);
        amountBean1.set_$2(1);
        amountBean1.set_$3(198);
        amountBean1.set_$4(198);
        amountBean1.set_$5(198);
        amountBean1.set_$6(198);
        amountBean1.set_$7(198);
        amountBean1.set_$8(198);
        amountBean1.set_$9(198);
        amountBean1.set_$10(198);
        amountBean1.set_$11(198);
        amountBean1.set_$12(198);
        amountBean1.set_$13(198);
        amountBean1.set_$14(1980);
        amountBean1.set_$15(198);
        amountBean1.set_$16(198);
        amountBean1.set_$17(198);
        amountBean1.set_$18(198);
        amountBean1.set_$19(198);
        amountBean1.set_$20(198);
        amountBean1.set_$21(1980);
        amountBean1.set_$22(198);
        amountBean1.set_$23(198);
        amountBean1.set_$24(198);
        amountBean1.set_$25(198);
        amountBean1.set_$26(198);
        amountBean1.set_$27(198);
        amountBean1.set_$28(198);
        amountBean1.set_$29(198);
        amountBean1.set_$30(1980);
        signInBean.setExt_reward(amountBean1);
    }

    public void clickbutton(View view) {

//        SignPercentDialog signPercentDialog = new SignPercentDialog(this);
//        signPercentDialog.setData(signInBean,198);
//        signPercentDialog.show();

        Snackbar.make(view,"撒打算打算打算",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        SignPercentView spv = findViewById(R.id.spv);


        spv.setData(signInBean);
//        BatteryChargeManager.getInstance().setConfig(this);
    }

    @Override
    protected void onDestroy() {
        BatteryChargeManager.getInstance().unRegister();
        super.onDestroy();
    }
}
