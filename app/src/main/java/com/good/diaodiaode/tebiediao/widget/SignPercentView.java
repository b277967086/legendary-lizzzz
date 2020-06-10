package com.good.diaodiaode.tebiediao.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.good.diaodiaode.tebiediao.R;
import com.good.diaodiaode.tebiediao.model.SignInProgressServerModel;
import com.good.diaodiaode.tebiediao.model.SignItemModel;
import com.good.diaodiaode.tebiediao.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 条形进度条（可设置 线性渐变-背景色-进度条颜色-进度条高度）
 */

public class SignPercentView extends ViewGroup {

    public static final float MAX = 100f;
    public static final int RADIUS = 15;     // 圆角矩形半径
    private Paint mPaint;
    private Paint mTextPaint;

    private int mWidth;
    private int mHeight;

    private int progressPercent;
    private int bgColor, progressColor;

    private List<SignItemModel> mFilterDatas = new ArrayList<>();

    private int mPathWidth = ScreenUtil.dip2px(getContext(), 8f);
    private float radius = mPathWidth / 2l;
    private int itemWidth;
    private int itemHeight = ScreenUtil.dip2px(getContext(), 82f);

    public SignPercentView(Context context) {
        super(context);
        init();
    }

    public SignPercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarPercentView);
//        bgColor = typedArray.getColor(R.styleable.BarPercentView_barBgColor, Color.parseColor("#FF9F25"));
//        progressColor = typedArray.getColor(R.styleable.BarPercentView_barProgressColor, Color.parseColor("#F1F0ED"));
//        radius = typedArray.getDimension(R.styleable.BarPercentView_barRadius, RADIUS);
//        typedArray.recycle();
        init();
    }

    public SignPercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);

        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(ScreenUtil.dip2px(getContext(), 20));
        mTextPaint.setColor(Color.parseColor("#80ffffff"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        itemWidth = (mWidth - mPathWidth) / 6;

        int width = MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.AT_MOST);
        int height = MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY);
        measureChildren(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (i < 6) {
                child.layout(itemWidth * i, 0, itemWidth * i + itemWidth, itemHeight);
            } else {
                int cel = (mWidth - mPathWidth) / (count - 6);
                int L = cel * (count - i - 1);
                child.layout(L, mHeight - itemHeight, L + cel, mHeight);
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        mPaint.setShader(null);

        //1、进度背景
        drawPorgressBackground(canvas, itemHeight * 52 / 82, itemHeight * 23 / 82);

        //2、进度
        drawProgress(canvas, itemHeight * 52 / 82, itemHeight * 23 / 82);

        //上层圆点/icon/文本
        drawItem(canvas);

        //item间的省略号
        drawDotText(canvas);

    }

    private void drawDotText(Canvas canvas) {
        int count = mFilterDatas.size();
        for (int i = 7; i < count; i++) {
            if (mFilterDatas.get(i).hasDot) {
                int cel = (mWidth - mPathWidth) / (count - 6);
                int Lpre = cel * (count - i);
                float textWidth = mTextPaint.measureText("······");
                canvas.drawText("······", Lpre - textWidth / 2, mHeight - ScreenUtil.dip2px(getContext(), 19), mTextPaint);
            }
        }
    }


    private void drawProgress(Canvas canvas, int topMargin, int bottomMargin) {
        mPaint.setColor(progressColor);
        if (progressPercent > 0) {
            int i = progressPercent % 7;
            //进度是7的倍数，进度拐弯
            if (i == 0 || progressPercent > 28) {

                Path progressPath = new Path();
                //上横线
                float[] radi1 = {radius, radius, 0f, 0f, 0f, 0f, radius, radius};
                progressPath.addRoundRect(new RectF(0, topMargin, mWidth - mPathWidth, topMargin + mPathWidth), radi1, Path.Direction.CW);
                //右竖线
                float[] radi2 = {0f, 0f, radius * 2, radius * 2, radius * 2, radius * 2, 0f, 0f};
                progressPath.addRoundRect(new RectF(mWidth - mPathWidth, topMargin, mWidth, mHeight - bottomMargin), radi2, Path.Direction.CW);
                //下横线
                float[] radi3 = {progressPercent == 30 ? radius : 0f, progressPercent == 30 ? radius : 0f, 0f, 0f, 0f, 0f, progressPercent == 30 ? radius : 0f, progressPercent == 30 ? radius : 0f};

                int count = getChildCount();
                int bottomItemWidth = (mWidth - mPathWidth) / (count - 6);
                int no7LeftPosition = bottomItemWidth * (count - 7 - i);

                progressPath.addRoundRect(new RectF(no7LeftPosition, mHeight - mPathWidth - bottomMargin, mWidth - mPathWidth, mHeight - bottomMargin), radi3, Path.Direction.CW);

                canvas.drawPath(progressPath, mPaint);

            } else {
                Path progressPath = new Path();
                float[] radi1 = {radius, radius, 0f, 0f, 0f, 0f, radius, radius};
                progressPath.addRoundRect(new RectF(0, topMargin, i * itemWidth, topMargin + mPathWidth), radi1, Path.Direction.CW);
                canvas.drawPath(progressPath, mPaint);
            }
        }
    }

    private void drawPorgressBackground(Canvas canvas, int topMargin, int bottomMargin) {
        mPaint.setColor(bgColor);

        Path backgroundPath = new Path();
        //上横线
        float[] radi1 = {radius, radius, 0f, 0f, 0f, 0f, radius, radius};
        backgroundPath.addRoundRect(new RectF(0, topMargin, mWidth - mPathWidth, topMargin + mPathWidth), radi1, Path.Direction.CW);

//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(mPathWidth);
//        backgroundPath.addArc(0f,0f,mHeight,mHeight,0,-90);

        //右竖线
        float[] radi2 = {0f, 0f, radius * 2, radius * 2, radius * 2, radius * 2, 0f, 0f};
        backgroundPath.addRoundRect(new RectF(mWidth - mPathWidth, topMargin, mWidth, mHeight - bottomMargin), radi2, Path.Direction.CW);

        //下横线
        float[] radi3 = {radius, radius, 0f, 0f, 0f, 0f, radius, radius};
        backgroundPath.addRoundRect(new RectF(0, mHeight - mPathWidth - bottomMargin, mWidth - mPathWidth, mHeight - bottomMargin), radi3, Path.Direction.CW);

        canvas.drawPath(backgroundPath, mPaint);
    }

    private void drawItem(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#ffffff"));
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            drawChild(canvas, getChildAt(i), 0);
        }
    }


    @Keep
    public void setData(SignInProgressServerModel.SignInBean signInBean) {

        if (signInBean == null || signInBean.getAmount() == null) {
            return;
        }

        SignInProgressServerModel.SignInBean.AmountBean amount = signInBean.getAmount();
        SignInProgressServerModel.SignInBean.ExtRewardBean extReward = signInBean.getExt_reward();
        if (extReward == null) {
            extReward = new SignInProgressServerModel.SignInBean.ExtRewardBean();
        }

        this.progressPercent = signInBean.getToday();

//        签到1-7天：前端展示第1/2/3/4/5/6/7/8/14/21/30天的金币数（11天）；
//        签到8-14天：前端展示第8/9/10/11/12/13/14/15/21/30天的金币数（10天）；
//        签到15-21天：前端展示第15/16/17/18/19/20/21//22/30天的金币数（9天）；
//        签到22-30天：前端展示第22/23/24/25/26/2728/29/30天的金币数（9天）；
        mFilterDatas.clear();
        try {
            if (progressPercent <= 7) {
                new ArrayList<>();
                mFilterDatas.add(new SignItemModel(1, amount.get_$1(), extReward.get_$1()));
                mFilterDatas.add(new SignItemModel(2, amount.get_$2(), extReward.get_$2()));
                mFilterDatas.add(new SignItemModel(3, amount.get_$3(), extReward.get_$3()));
                mFilterDatas.add(new SignItemModel(4, amount.get_$4(), extReward.get_$4()));
                mFilterDatas.add(new SignItemModel(5, amount.get_$5(), extReward.get_$5()));
                mFilterDatas.add(new SignItemModel(6, amount.get_$6(), extReward.get_$6()));
                mFilterDatas.add(new SignItemModel(7, amount.get_$7(), extReward.get_$7()));
                mFilterDatas.add(new SignItemModel(8, amount.get_$8(), extReward.get_$8()));
                mFilterDatas.add(new SignItemModel(14, amount.get_$14(), extReward.get_$14()).setHasDot(true));
                mFilterDatas.add(new SignItemModel(21, amount.get_$21(), extReward.get_$21()).setHasDot(true));
                mFilterDatas.add(new SignItemModel(30, amount.get_$30(), extReward.get_$30()).setHasDot(true));

            } else if (progressPercent <= 14) {
                mFilterDatas.add(new SignItemModel(8, amount.get_$8(), extReward.get_$8()));
                mFilterDatas.add(new SignItemModel(9, amount.get_$9(), extReward.get_$9()));
                mFilterDatas.add(new SignItemModel(10, amount.get_$10(), extReward.get_$10()));
                mFilterDatas.add(new SignItemModel(11, amount.get_$11(), extReward.get_$11()));
                mFilterDatas.add(new SignItemModel(12, amount.get_$12(), extReward.get_$12()));
                mFilterDatas.add(new SignItemModel(13, amount.get_$13(), extReward.get_$13()));
                mFilterDatas.add(new SignItemModel(14, amount.get_$14(), extReward.get_$14()));
                mFilterDatas.add(new SignItemModel(15, amount.get_$15(), extReward.get_$15()));
                mFilterDatas.add(new SignItemModel(21, amount.get_$21(), extReward.get_$21()).setHasDot(true));
                mFilterDatas.add(new SignItemModel(30, amount.get_$30(), extReward.get_$30()).setHasDot(true));

            } else if (progressPercent <= 21) {
                mFilterDatas.add(new SignItemModel(15, amount.get_$15(), extReward.get_$15()));
                mFilterDatas.add(new SignItemModel(16, amount.get_$16(), extReward.get_$16()));
                mFilterDatas.add(new SignItemModel(17, amount.get_$17(), extReward.get_$17()));
                mFilterDatas.add(new SignItemModel(18, amount.get_$18(), extReward.get_$18()));
                mFilterDatas.add(new SignItemModel(19, amount.get_$19(), extReward.get_$19()));
                mFilterDatas.add(new SignItemModel(20, amount.get_$20(), extReward.get_$20()));
                mFilterDatas.add(new SignItemModel(21, amount.get_$21(), extReward.get_$21()));
                mFilterDatas.add(new SignItemModel(22, amount.get_$22(), extReward.get_$22()));
                mFilterDatas.add(new SignItemModel(30, amount.get_$30(), extReward.get_$30()).setHasDot(true));

            } else {
                mFilterDatas.add(new SignItemModel(22, amount.get_$22(), extReward.get_$22()));
                mFilterDatas.add(new SignItemModel(23, amount.get_$23(), extReward.get_$23()));
                mFilterDatas.add(new SignItemModel(24, amount.get_$24(), extReward.get_$24()));
                mFilterDatas.add(new SignItemModel(25, amount.get_$25(), extReward.get_$25()));
                mFilterDatas.add(new SignItemModel(26, amount.get_$26(), extReward.get_$26()));
                mFilterDatas.add(new SignItemModel(27, amount.get_$27(), extReward.get_$27()));
                mFilterDatas.add(new SignItemModel(28, amount.get_$28(), extReward.get_$28()));
                mFilterDatas.add(new SignItemModel(29, amount.get_$29(), extReward.get_$29()));
                mFilterDatas.add(new SignItemModel(30, amount.get_$30(), extReward.get_$30()));
            }

            if (getChildCount() > 0) {
                removeAllViewsInLayout();
            }
            for (int index = 0; index < mFilterDatas.size(); index++) {
                SignItemModel model = mFilterDatas.get(index);
                SignItemView itemView = new SignItemView(getContext());
                itemView.setData(model.extReward, model.reward, model.day == progressPercent ? "今日已签" : String.format("%s天", model.day));
                itemView.setSelected(signInBean.getToday() >= model.day);
                addView(itemView);
            }
            invalidate();
        } catch (IndexOutOfBoundsException e) {
        }
    }

}

