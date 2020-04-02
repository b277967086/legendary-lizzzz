package com.good.diaodiaode.tebiediao.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ex-lizheng102 on 2016-12-12.
 */

public class Person implements Parcelable {
    private int id;
    private String name;
    private String[] friendnames = new String[]{"阿杜","覆盖","付款"};

    public Person(int i,String name){
        this.id = i;
        this.name = name;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        friendnames = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeStringArray(friendnames);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
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

    public String[] getFriendnames() {
        return friendnames;
    }

    public void setFriendnames(String[] friendnames) {
        this.friendnames = friendnames;
    }
}
