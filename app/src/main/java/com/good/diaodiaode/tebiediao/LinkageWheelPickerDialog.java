package com.good.diaodiaode.tebiediao;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jzxiang on 16/4/19.
 */
public class LinkageWheelPickerDialog extends DialogFragment implements View.OnClickListener {
    PickerConfig mPickerConfig;
    private LinkageWheelManager mLinkageWheelManager;

    private static LinkageWheelPickerDialog newIntance(PickerConfig pickerConfig) {
        LinkageWheelPickerDialog timePickerDialog = new LinkageWheelPickerDialog();
        timePickerDialog.initialize(pickerConfig);
        return timePickerDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void onResume() {
        super.onResume();
        int height = getResources().getDimensionPixelSize(R.dimen.dp230);

        Window window = getDialog().getWindow();
//        //设置成全局的dialog
//        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
//        //去掉dialog区域外的灰色透明蒙版
//        window.clearFlags( WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!
        window.setGravity(Gravity.BOTTOM);
    }

    private void initialize(PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    View initView() {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
        LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_linkage_wheel_layout, null);
        TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(this);
        TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        sure.setOnClickListener(this);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        View toolbar = view.findViewById(R.id.toolbar);

        title.setText(mPickerConfig.mTitleString);
        cancel.setText(mPickerConfig.mCancelString);
        sure.setText(mPickerConfig.mSureString);
        toolbar.setBackgroundColor(mPickerConfig.mThemeColor);

        mLinkageWheelManager = new LinkageWheelManager(view, mPickerConfig);
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();
        } else if (i == R.id.tv_sure) {
            sureClicked();
        }
    }


    /*
    * @desc This method is called when onClick method is invoked by sure button. A Calendar instance is created and 
    *       initialized. 
    * @param none
    * @return none
    */
    void sureClicked() {
        int currentWheel1 = mLinkageWheelManager.getCurrentWheel1();
        int currentWheel2 = mLinkageWheelManager.getCurrentWheel2();
        int currentWheel3 = mLinkageWheelManager.getCurrentWheel3();
        if (mPickerConfig.mOnSelectChangedListener != null) {
            mPickerConfig.mOnSelectChangedListener.onSelectChanged(currentWheel1,currentWheel2,currentWheel3);
        }
        dismiss();
    }

    public static class Builder {
        PickerConfig mPickerConfig;

        public Builder() {
            mPickerConfig = new PickerConfig();
        }

        public Builder setType(Type type) {
            mPickerConfig.mType = type;
            return this;
        }

        public Builder setThemeColor(int color) {
            mPickerConfig.mThemeColor = color;
            return this;
        }

        public Builder setCancelStringId(String left) {
            mPickerConfig.mCancelString = left;
            return this;
        }

        public Builder setSureStringId(String right) {
            mPickerConfig.mSureString = right;
            return this;
        }

        public Builder setTitleStringId(String title) {
            mPickerConfig.mTitleString = title;
            return this;
        }

        public Builder setToolBarTextColor(int color) {
            mPickerConfig.mToolBarTVColor = color;
            return this;
        }

        public Builder setWheelItemTextNormalColor(int color) {
            mPickerConfig.mWheelTVNormalColor = color;
            return this;
        }

        public Builder setWheelItemTextSelectorColor(int color) {
            mPickerConfig.mWheelTVSelectorColor = color;
            return this;
        }

        public Builder setWheelItemTextSize(int size) {
            mPickerConfig.mWheelTVSize = size;
            return this;
        }
        public Builder setWheelItemTextSelectorSize(int size) {
            mPickerConfig.mWheelTVSelectorSize = size;
            return this;
        }

        public Builder setCyclic(boolean cyclic) {
            mPickerConfig.cyclic = cyclic;
            return this;
        }

        public Builder setTitleIsShow(boolean titleIsShow) {
            mPickerConfig.mTitleIsShow = titleIsShow;
            return this;
        }

        public Builder setCallBack(OnSelectChangedListener listener) {
            mPickerConfig.mOnSelectChangedListener = listener;
            return this;
        }

        public Builder setData(ArrayList<LinkageDataBean> data) {
            mPickerConfig.mLinkageDatas = data;
            return this;
        }

        public Builder setCurrentItems(int... selectItems) {
            mPickerConfig.mSelectItems = selectItems;
            return this;
        }

        public LinkageWheelPickerDialog build() {
            return newIntance(mPickerConfig);
        }

    }


}
