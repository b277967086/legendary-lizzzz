package com.good.diaodiaode.tebiediao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ex-lizheng102 on 2016-12-12.
 */

public class Student implements Parcelable {
    private int id;
    private String name;
    private String[] friendnames = new String[]{"是的","松岛","房东"};

    public Student(int i, String name){
        this.id = i;
        this.name = name;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    public String[] getFriendnames() {
        return friendnames;
    }

    public void setFriendnames(String[] friendnames) {
        this.friendnames = friendnames;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
