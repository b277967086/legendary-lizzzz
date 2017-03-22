package com.good.diaodiaode.tebiediao;

import android.graphics.Point;

/**
 * Created by ex-lizheng102 on 2017-03-17.
 */

public class yPoints {

    private Point p1 = new Point();
    private Point p2 = new Point();
    private Point p3 = new Point();

    public void setYM(int y,int m){
        p1.set(0,y);
        p2.set(m,y);
        p3.set(-m,y);
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }
}
