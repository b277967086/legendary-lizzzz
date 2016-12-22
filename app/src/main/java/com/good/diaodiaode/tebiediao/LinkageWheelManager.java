package com.good.diaodiaode.tebiediao;

import android.content.Context;
import android.view.View;

import com.good.diaodiaode.tebiediao.wheel.OnWheelChangedListener;
import com.good.diaodiaode.tebiediao.wheel.WheelView;

import java.util.ArrayList;

/**
 * Created by jzxiang on 16/4/20.
 */
public class LinkageWheelManager {
    Context mContext;

    WheelView wheelView1, wheelView2, wheelView3;
    ArrayListWheelAdapter<LinkageDataBean> mWheel1Adapter, mWheel2Adapter, mWheel3Adapter;

    PickerConfig mPickerConfig;
    OnWheelChangedListener wheel1Listener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateWheel2();
            updateWheel3();
        }
    };
    OnWheelChangedListener wheel2Listener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateWheel3();
        }
    };

    public LinkageWheelManager(View view, PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;

        mContext = view.getContext();
        initialize(view);
    }

    public void initialize(View view) {
        initView(view);
        initWheel();
    }


    void initView(View view) {
        wheelView1 = (WheelView) view.findViewById(R.id.wheel_1);
        wheelView2 = (WheelView) view.findViewById(R.id.wheel_2);
        wheelView3 = (WheelView) view.findViewById(R.id.wheel_3);

        switch (mPickerConfig.mType) {
            case LINKAGE_SINGLE:
                Utils.hideViews(wheelView1, wheelView2);
                break;
            case LINKAGE_DOUBLE:
                Utils.hideViews(wheelView1);
                break;
            case LINKAGE_TREBLE:
                break;
            default:
                break;
        }
    }

    void initWheel() {

        //初始化wheelView1
        ArrayList<LinkageDataBean> wheel1Datas = null;
        try {
           wheel1Datas = mPickerConfig.canLinkaged ? mPickerConfig.mLinkageDatas : mPickerConfig.mNoLinkageDatas.get(0);
            mWheel1Adapter = new ArrayListWheelAdapter<>(mContext, wheel1Datas);
            mWheel1Adapter.setConfig(mPickerConfig);
            wheelView1.setViewAdapter(mWheel1Adapter);
            wheelView1.setCyclic(mPickerConfig.cyclic);
            wheelView1.setCurrentItem(mPickerConfig.mSelectItems[0]);
        } catch (Exception e) {
            Utils.hideViews(wheelView1, wheelView2, wheelView3);
        }

        //初始化wheelView2
        ArrayList<LinkageDataBean> wheel2Datas = null;
        try {
            wheel2Datas = mPickerConfig.canLinkaged ? mPickerConfig.mLinkageDatas.get(wheelView1.getCurrentItem()).getLinkageDataBeans() :
                    mPickerConfig.mNoLinkageDatas.get(1);
            mWheel2Adapter = new ArrayListWheelAdapter<>(mContext, wheel2Datas);
            mWheel2Adapter.setConfig(mPickerConfig);
            wheelView2.setViewAdapter(mWheel2Adapter);
            wheelView2.setCyclic(mPickerConfig.cyclic);
            wheelView2.setCurrentItem(mPickerConfig.mSelectItems[1]);
        } catch (Exception e) {
            Utils.hideViews(wheelView2, wheelView3);
        }

        //初始化wheelView3
        ArrayList<LinkageDataBean> wheel3Datas = null;
        try {
            wheel3Datas = mPickerConfig.canLinkaged ? mPickerConfig.mLinkageDatas.get(wheelView1.getCurrentItem()).getLinkageDataBeans().get(wheelView2.getCurrentItem()).getLinkageDataBeans() :
                    mPickerConfig.mNoLinkageDatas.get(2);
            mWheel3Adapter = new ArrayListWheelAdapter<>(mContext, wheel3Datas);
            mWheel3Adapter.setConfig(mPickerConfig);
            wheelView3.setViewAdapter(mWheel3Adapter);
            wheelView3.setCyclic(mPickerConfig.cyclic);
            wheelView3.setCurrentItem(mPickerConfig.mSelectItems[2]);
        } catch (Exception e) {
            Utils.hideViews(wheelView3);
        }finally {}
        if(mPickerConfig.canLinkaged){
            wheelView1.addChangingListener(wheel1Listener);
            wheelView2.addChangingListener(wheel2Listener);
        }
    }

    void updateWheel2() {
        LinkageDataBean wheelData = mPickerConfig.mLinkageDatas.get(getCurrentWheel1());
        if (mPickerConfig.mLinkageDatas == null || wheelData == null) {
            return;
        }
        refreshWheel(wheelView2, mWheel2Adapter, wheelData.getLinkageDataBeans());
    }

    void updateWheel3() {
        if (mPickerConfig.mLinkageDatas == null || mPickerConfig.mLinkageDatas.get(getCurrentWheel1()) == null ||
                mPickerConfig.mLinkageDatas.get(getCurrentWheel1()).getLinkageDataBeans().get(getCurrentWheel2()) == null) {
            return;
        }
        ArrayList<LinkageDataBean> wheelData = mPickerConfig.mLinkageDatas.get(getCurrentWheel1()).getLinkageDataBeans().get(getCurrentWheel2()).getLinkageDataBeans();
        refreshWheel(wheelView3, mWheel3Adapter, wheelData);
    }

    /**
     * 刷新WheelView 中的数据
     */
    private void refreshWheel(WheelView wheelView, ArrayListWheelAdapter<LinkageDataBean> mWheelAdapter, ArrayList<LinkageDataBean> wheelDatas) {
        if (wheelView.getVisibility() == View.GONE || wheelDatas == null)
            return;
        if (mWheelAdapter == null) {
            mWheelAdapter = new ArrayListWheelAdapter<>(mContext, wheelDatas);
            mWheelAdapter.setConfig(mPickerConfig);
            wheelView.setViewAdapter(mWheelAdapter);
            wheelView.setCyclic(mPickerConfig.cyclic);
        } else {
            mWheelAdapter.setItems(wheelDatas);
            mWheelAdapter.notifyDataChangedEvent();
        }
        wheelView.setCurrentItem(0);
    }

    public int getCurrentWheel1() {
        return wheelView1.getCurrentItem();
    }

    public int getCurrentWheel2() {
        return wheelView2.getCurrentItem();
    }

    public int getCurrentWheel3() {
        return wheelView3.getCurrentItem();
    }
}
