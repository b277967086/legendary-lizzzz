package com.good.diaodiaode.tebiediao;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by jzxiang on 16/5/15.
 */
public class PickerConfig {

    public Type mType = DefaultConfig.TYPE;
    public int mThemeColor = DefaultConfig.COLOR;

    public String mCancelString = DefaultConfig.CANCEL;
    public String mSureString = DefaultConfig.SURE;
    public String mTitleString = DefaultConfig.TITLE;
    public int mToolBarTVColor = DefaultConfig.TOOLBAR_TV_COLOR;

    public int mWheelTVNormalColor = DefaultConfig.TV_NORMAL_COLOR;
    public int mWheelTVSelectorColor = DefaultConfig.TV_SELECTOR_COLOR;
    public int mWheelTVSize = DefaultConfig.TV_SIZE;
    public int mWheelTVSelectorSize = DefaultConfig.TV_SELECTED_SIZE;
    public boolean cyclic = DefaultConfig.CYCLIC;

    public String mYear = DefaultConfig.YEAR;
    public String mMonth = DefaultConfig.MONTH;
    public String mDay = DefaultConfig.DAY;
    public String mHour = DefaultConfig.HOUR;
    public String mMinute = DefaultConfig.MINUTE;
    public String mAmPm = DefaultConfig.AMPM;
    public long mEnableStartTime;
    public long mEnableEndTime;

    /**
     * LinkageWheelPickDialog使用
     * canLinkaged为true时，需要设置mLinkageDatas；
     * canLinkaged为false时，需要设置mNoLinkageDatas；
     */
    public boolean canLinkaged = false; //是否可以联动
    public ArrayList<LinkageDataBean> mLinkageDatas = new ArrayList<>();//联动的数据
    public LinkedList<ArrayList<LinkageDataBean>> mNoLinkageDatas = new LinkedList<>();//非联动的数据
    public int[] mSelectItems = new int[]{0,0,0};//当前的选中的item

    /**
     * The min timeMillseconds
     */
    public WheelCalendar mMinCalendar = new WheelCalendar(0);

    /**
     * The max timeMillseconds
     */
    public WheelCalendar mMaxCalendar = new WheelCalendar(0);

    /**
     * The default selector timeMillseconds
     */
    public WheelCalendar mCurrentCalendar = new WheelCalendar(System.currentTimeMillis());


    public OnSelectChangedListener mOnSelectChangedListener;

//    public OnDateSetListener mCallBack;

    public boolean mTitleIsShow = true;
}
