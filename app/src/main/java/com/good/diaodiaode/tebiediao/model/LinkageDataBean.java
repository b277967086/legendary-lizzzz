package com.good.diaodiaode.tebiediao.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ex-lizheng102 on 2016-12-19.
 */

public class LinkageDataBean implements Parcelable {

    private String name;

    private ArrayList<LinkageDataBean> linkageDataBeans;

    public LinkageDataBean(){}

    protected LinkageDataBean(Parcel in) {
        name = in.readString();
        linkageDataBeans = in.readParcelable(LinkageDataBean.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(linkageDataBeans);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LinkageDataBean> CREATOR = new Creator<LinkageDataBean>() {
        @Override
        public LinkageDataBean createFromParcel(Parcel in) {
            return new LinkageDataBean(in);
        }

        @Override
        public LinkageDataBean[] newArray(int size) {
            return new LinkageDataBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LinkageDataBean> getLinkageDataBeans() {
        return linkageDataBeans;
    }

    public void setLinkageDataBeans(ArrayList<LinkageDataBean> linkageDataBeans) {
        this.linkageDataBeans = linkageDataBeans;
    }
}
