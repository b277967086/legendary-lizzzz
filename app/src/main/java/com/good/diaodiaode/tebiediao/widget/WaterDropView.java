package com.good.diaodiaode.tebiediao.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.good.diaodiaode.tebiediao.model.xPoints;
import com.good.diaodiaode.tebiediao.model.yPoints;

/**
 * Created by ex-lizheng102 on 2017-03-17.
 */

public class WaterDropView extends View {

    private DecelerateInterpolator accelerateDecelerateInterpolator = new DecelerateInterpolator();
    private xPoints xPoints1, xPoints2;
    private yPoints yPoints1, yPoints2;

    private float M = 0.551915024494f;
    private float mc;
    private Paint mPaint;
    private Path mPath1;
    private Path mPath2;
    private Path mPath3;
    private Path mPath4;
    private int mWid;
    private int mHei;




    public WaterDropView(Context context) {
        super(context);
        init();
    }

    public WaterDropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterDropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        xPoints1 = new xPoints();
        xPoints1.setXM(200, (int) (200 * mc));

        xPoints2 = new xPoints();
        xPoints2.setXM(-200, (int) (200 * mc));

        yPoints1 = new yPoints();
        yPoints1.setYM(200, (int) (200 * mc));

        yPoints2 = new yPoints();
        yPoints2.setYM(-200, (int) (200 * mc));

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4L);

        mPath1 = new Path();
        mPath2 = new Path();
        mPath3 = new Path();
        mPath4 = new Path();

        ValueAnimator mValueAnimator= ObjectAnimator.ofFloat(this,"mc",0,M);
        mValueAnimator.setInterpolator(accelerateDecelerateInterpolator);
        mValueAnimator.setDuration(500L);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.setRepeatCount(Integer.MAX_VALUE);
//        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mc = (float) animation.getAnimatedValue();
//                setMc(mc);
//                invalidate();
//            }
//        });
        mValueAnimator.start();
    }

    public void setMc(float mc) {
        xPoints1.setXM(200, (int) (200 * mc));
        xPoints2.setXM(-200, (int) (200 * mc));
        yPoints1.setYM(200, (int) (200 * mc));
        yPoints2.setYM(-200, (int) (200 * mc));
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWid= w/2;
        mHei=h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath1.reset();
        mPath2.reset();
        mPath3.reset();
        mPath4.reset();
        canvas.translate(mWid,mHei);
        mPath1.moveTo(yPoints1.getP1().x, yPoints1.getP1().y);
        mPath1.cubicTo(yPoints1.getP2().x, yPoints1.getP2().y, xPoints1.getP2().x, xPoints1.getP2().y, xPoints1.getP1().x, xPoints1.getP1().y);
        canvas.drawPath(mPath1, mPaint);

        mPath2.moveTo(yPoints1.getP1().x, yPoints1.getP1().y);
        mPath2.cubicTo(yPoints1.getP3().x, yPoints1.getP3().y, xPoints2.getP2().x, xPoints2.getP2().y, xPoints2.getP1().x, xPoints2.getP1().y);
        canvas.drawPath(mPath2, mPaint);

        mPath3.moveTo(yPoints2.getP1().x, yPoints2.getP1().y);
        mPath3.cubicTo(yPoints2.getP3().x, yPoints2.getP3().y, xPoints2.getP3().x, xPoints2.getP3().y, xPoints2.getP1().x, xPoints2.getP1().y);
        canvas.drawPath(mPath3, mPaint);

        mPath4.moveTo(yPoints2.getP1().x, yPoints2.getP1().y);
        mPath4.cubicTo(yPoints2.getP2().x, yPoints2.getP2().y, xPoints1.getP3().x, xPoints1.getP3().y, xPoints1.getP1().x, xPoints1.getP1().y);
        canvas.drawPath(mPath4, mPaint);

    }


}
