package com.good.diaodiaode.tebiediao.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ex-lizheng102 on 2017-03-10.
 */

@DatabaseTable(tableName = "tb_user")
public class User {

    @DatabaseField(generatedId = true)
    private int user_id;
    @DatabaseField(columnName = "user_name")
    private String user_name;
    @DatabaseField(columnName = "user_desc")
    private String user_desc;

    public User() {
    }

    public User(String user_name, String user_desc) {
        this.user_name = user_name;
        this.user_desc = user_desc;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_desc() {
        return user_desc;
    }

    public void setUser_desc(String user_desc) {
        this.user_desc = user_desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_desc='" + user_desc + '\'' +
                '}';
    }
}
