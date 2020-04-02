package com.good.diaodiaode.tebiediao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ex-lizheng102 on 2016-08-31.
 */
public class RotateCirCleView extends View implements Runnable {

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private int mCirclePaintColor = 0xffff8447;
    private int mArcPaintColor = 0xffffffff;
    private int mCircleRadius;
    private volatile float startAngle = 270;
    private volatile float swipeAngle = 0;
    private RectF ovalRect;

    public RotateCirCleView(Context context) {
        super(context);
        init();
    }

    public RotateCirCleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateCirCleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCirclePaintColor);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCircleRadius = w < h ? w / 2 : h / 2;
    }

    private RectF getOvalRect() {
        if (ovalRect == null) {
            ovalRect = new RectF(16, 16, 2 * mCircleRadius - 16, 2 * mCircleRadius - 16);
        }
        return ovalRect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景圆圈
        canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius, mCirclePaint);
        //动态进度圆环
        canvas.drawArc(getOvalRect(), startAngle, swipeAngle, false, createPaint());
    }

    /**
     * 根据画笔的颜色，创建画笔
     *
     * @return
     */
    private Paint createPaint() {
        if (this.mArcPaint == null) {
            mArcPaint = new Paint();
            mArcPaint.setStrokeWidth(6);
            mArcPaint.setStyle(Paint.Style.STROKE);
            mArcPaint.setAntiAlias(true);
            mArcPaint.setColor(mArcPaintColor);
        }
        return mArcPaint;
    }

    private boolean isOnDraw = false;
    private boolean isRunning = false;
    private float speed = 2;
    private static final int PEROID = 8;// 绘制周期

    @Override
    public void run() {
        //为再次启动还原初始位置
        startAngle = 270;
        swipeAngle = 0;

        while (isOnDraw) {
            isRunning = true;
            long startTime = System.currentTimeMillis();
            startAngle = (startAngle + speed);
            swipeAngle = (swipeAngle + speed * 9 / 5);

            if (swipeAngle >= 360) {
                isOnDraw = false;
            }
            postInvalidate();

            long time = System.currentTimeMillis() - startTime;
            if (time < PEROID) {
                try {
                    Thread.sleep(PEROID - time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //设置进度条的运行一周的时间   单位ms
    public void setOneCircleTime(long time) {
        speed = 200 * PEROID / time;
    }

    public void setOnDraw(boolean isOnDraw) {

        //为再次启动还原初始位置
        startAngle = 270;
        swipeAngle = 0;
        this.isOnDraw = isOnDraw;
    }

    @Override
    protected void onDetachedFromWindow() {
        isOnDraw = false;

        //为再次启动还原初始位置
        startAngle = 270;
        swipeAngle = 0;
        super.onDetachedFromWindow();
    }

}
