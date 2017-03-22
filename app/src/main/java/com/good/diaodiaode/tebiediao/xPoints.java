package com.good.diaodiaode.tebiediao;

import android.graphics.Point;

/**
 * Created by ex-lizheng102 on 2017-03-17.
 */

public class xPoints {

    private Point p1 = new Point();
    private Point p2 = new Point();
    private Point p3 = new Point();

    public void setXM(int x,int m){

        p1.set(x,0);
        p2.set(x,m);
        p3.set(x,-m);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

}
