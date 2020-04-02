package com.good.diaodiaode.tebiediao.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ex-lizheng102 on 2016-08-31.
 */
public class UpLoadProgressView extends View implements Runnable {

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mArc8447Paint;
    private Paint mPathPaint;
    private int mCirclePaintColor = 0xff64d2af;
    private int mArcPaintColor = 0xffffffff;
    private int mCircleRadius;
    private volatile float startAngle = 270;
    private volatile float swipeAngle = 60;
    private RectF ovalRect;
    private Path mPath;
    private PathMeasure tickPathMeasure;
    private float strokeWidth = 6;
    private float tickPrecent;
    private Path mSmallPath;
    private OnCompleteListener mOnCompleteListener;
    private ValueAnimator startAngleRotate;

    public UpLoadProgressView(Context context) {
        super(context);
        init();
    }

    public UpLoadProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UpLoadProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCirclePaintColor);

        createPaint();
        create8447Paint();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCircleRadius = w < h ? w / 2 : h / 2;
        //规划钩的路径

        mPath = new Path();
        mPath.moveTo(0.3f * mCircleRadius, mCircleRadius);
        mPath.lineTo(0.8f * mCircleRadius, 1.4f * mCircleRadius);
        mPath.lineTo(1.6f * mCircleRadius, 0.5f * mCircleRadius);
        tickPathMeasure = new PathMeasure(mPath, false);
        mSmallPath = new Path();
    }

    private RectF getOvalRect() {
        if (ovalRect == null) {
            ovalRect = new RectF(strokeWidth, strokeWidth, 2 * mCircleRadius - strokeWidth, 2 * mCircleRadius - strokeWidth);
        }
        return ovalRect;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景圆圈
//        canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius, mCirclePaint);
        //动态进度圆环
        canvas.drawArc(getOvalRect(), startAngle, swipeAngle, false, isUploadComplete ? mArc8447Paint : mArcPaint);

        //开始画勾
        if (isUploadComplete) {
            canvas.drawPath(mSmallPath, createPathPaint());
//            mSmallPath.reset();
        }
    }


    public void start() {
        startAngleRotate = ValueAnimator.ofFloat(startAngle, startAngle + 360);
        startAngleRotate.setDuration(500);
        startAngleRotate.setRepeatCount(Integer.MAX_VALUE);
        startAngleRotate.setRepeatMode(ValueAnimator.RESTART);
//        startAngleRotate.setInterpolator(new DecelerateInterpolator(2));
        startAngleRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        startAngleRotate.start();
    }

    @Override
    public void run() {
        //为再次启动还原初始位置
        startAngle = 270;
        swipeAngle = 0;

        while (isOnDraw) {
            long startTime = System.currentTimeMillis();
            startAngle += speed;


            if (isUploadComplete) {
                //圆弧角度改变
                startAngle = 420;
                swipeAngle += speed;
                if (swipeAngle >= 330) {
                    swipeAngle = 330;
                }

            } else {
                int index = (int) (startAngle / 360);
                if (index % 2 == 0) {
                    this.swipeAngle = this.startAngle % 720 / 2;
                } else {
                    this.swipeAngle = 360 - this.startAngle % 720 / 2;
                }
            }
//            if(swipeAngle>=320){
//                swipeAngle = 320;
//            }

            if (isUploadComplete) {

                if (tickPrecent < 1) {
                    tickPrecent += speed / 400L;
                } else {
                    tickPrecent = 1;
                }
                if (tickPathMeasure != null)
                    tickPathMeasure.getSegment(0, tickPrecent * tickPathMeasure.getLength(), mSmallPath, true);//该方法，可以获得整个路径的一部分
                if (mSmallPath != null)
                    mSmallPath.rLineTo(0, 0);//解决Android本身的一个bug

            }

            if (tickPrecent >= 1) {
                isOnDraw = false;
                if (mOnCompleteListener != null) {
                    mOnCompleteListener.onComplete();
                }
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

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.mOnCompleteListener = onCompleteListener;
    }

    public interface OnCompleteListener {
        void onComplete();
    }

    /**
     * 根据画笔的颜色，创建画笔
     *
     * @return
     */
    private Paint createPaint() {
        if (this.mArcPaint == null) {
            mArcPaint = new Paint();
            mArcPaint.setStrokeWidth(strokeWidth);
            mArcPaint.setStyle(Paint.Style.STROKE);
            mArcPaint.setAntiAlias(true);
            mArcPaint.setColor(mArcPaintColor);
        }
        return mArcPaint;
    }

    private Paint create8447Paint() {
        if (this.mArc8447Paint == null) {
            mArc8447Paint = new Paint();
            mArc8447Paint.setStrokeWidth(strokeWidth);
            mArc8447Paint.setStyle(Paint.Style.STROKE);
            mArc8447Paint.setAntiAlias(true);
            mArc8447Paint.setColor(mCirclePaintColor);
        }
        return mArc8447Paint;
    }

    private Paint createPathPaint() {
        if (this.mPathPaint == null) {
            mPathPaint = new Paint();
            mPathPaint.setStrokeWidth(8);
            mPathPaint.setStyle(Paint.Style.STROKE);
            mPathPaint.setAntiAlias(true);
            mPathPaint.setColor(mCirclePaintColor);
        }
        return mPathPaint;
    }

    private boolean isOnDraw = false;
    private boolean isUploadComplete = false;
    private float speed = 4;
    private static final int PEROID = 8;// 绘制周期,ms


    //设置进度条的运行一周的时间   单位ms
    public void setOneCircleTime(long time) {
        speed = 360 * PEROID / time;
    }

    public void setOnDraw(boolean isOnDraw) {

        //为再次启动还原初始位置
        startAngle = 270;
        swipeAngle = 60;
        this.isOnDraw = isOnDraw;
    }

    public void setUploadComplete(boolean uploadComplete) {
        isUploadComplete = uploadComplete;
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
