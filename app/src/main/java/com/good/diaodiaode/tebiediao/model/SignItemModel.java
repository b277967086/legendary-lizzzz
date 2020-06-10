package com.good.diaodiaode.tebiediao.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SignItemModel implements Parcelable {
    public int day;
    public int reward;
    public int extReward;
    public boolean hasDot;

    public SignItemModel(int day, int reward, int extReward) {
        this.day = day;
        this.reward = reward;
        this.extReward = extReward;
    }

    protected SignItemModel(Parcel in) {
        day = in.readInt();
        reward = in.readInt();
        extReward = in.readInt();
    }

    public SignItemModel setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
        return this;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(day);
        dest.writeInt(reward);
        dest.writeInt(extReward);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SignItemModel> CREATOR = new Creator<SignItemModel>() {
        @Override
        public SignItemModel createFromParcel(Parcel in) {
            return new SignItemModel(in);
        }

        @Override
        public SignItemModel[] newArray(int size) {
            return new SignItemModel[size];
        }
    };
}
