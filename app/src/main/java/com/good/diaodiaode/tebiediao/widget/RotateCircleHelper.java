package com.good.diaodiaode.tebiediao.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.good.diaodiaode.tebiediao.R;

import java.util.ArrayList;

/**
 * Created by ex-lizheng102 on 2016-09-01.
 */
public class RotateCircleHelper {

    private static RotateCircleHelper mRotateCircleHelper;

    //    private PopupWindow mPopupWindow;
    private Dialog mDialog;
    private Context mContext;

    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    private AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
    private DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();


    private RotateCirCleView mRotateCirCleView;
    private String[] mTexts;
    private ArrayList<TextView> textViewList;
    private ArrayList<AnimatorSet> mAnimatorSetList;
    private AnimatorSet mStartAnim;
    private ObjectAnimator mEndAnim;

    private RotateCircleHelper(Context ctx) {
        mContext = ctx;
        init(mContext);
    }

    /*public static RotateCircleHelper create(Context ctx) {
        if (mRotateCircleHelper == null) {
            synchronized (RotateCircleHelper.class) {
                if (mRotateCircleHelper == null) {
                    mRotateCircleHelper = new RotateCircleHelper(ctx);
                }
            }
        }
        return mRotateCircleHelper;
    }*/

    public static RotateCircleHelper getInstance(Context ctx) {
                if (mRotateCircleHelper == null) {
                    mRotateCircleHelper = new RotateCircleHelper(ctx);
                }
        return mRotateCircleHelper;
    }


    private ObjectAnimator createInAnimator(View view) {
        PropertyValuesHolder translationY1 = PropertyValuesHolder.ofFloat("translationY", 60f, -20f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, translationY1, alpha);
        //文字弹到最高时间
        animator.setDuration(160);
        return animator;
    }

    private ObjectAnimator createBackAnimator(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", -20f, 0f);
        //文字重最高回到中间的时间
        anim.setDuration(120);
        return anim;
    }

    private ObjectAnimator createOutAnimator(View view) {
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 0f, -60f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, translationY, alpha);
        //文字消失时间
        animator.setDuration(160);
        //文字在停留时间
        animator.setStartDelay(600);
        return animator;
    }

    //创建的开始动画
    private AnimatorSet createStartAnimator(View view) {

        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(view, "alpha", 0, 0.6f);
        ObjectAnimator animTranx = ObjectAnimator.ofFloat(view, "translationY", 0f, -50f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animAlpha, animTranx);

        set.setDuration(500);
        set.setInterpolator(decelerateInterpolator);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mAnimatorSetList != null && mAnimatorSetList.get(0) != null)
                    mAnimatorSetList.get(0).start();
            }
        });
        return set;
    }

    //创建的结束动画
    private ObjectAnimator createEndAnimator(View view) {
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(view, "alpha", 0.6f, 0f);
        animAlpha.setDuration(500);
        animAlpha.setInterpolator(accelerateInterpolator);
        animAlpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mDialog.dismiss();
            }
        });
        return animAlpha;
    }


    //创建文字的动画集
    private ArrayList<AnimatorSet> createAnimatorSet(String[] texts) {

        ArrayList<AnimatorSet> mAnimatorSetList = new ArrayList<>();
        if (texts != null) {
            for (int i = 0; i < texts.length; i++) {
                ObjectAnimator translationYIn = createInAnimator(textViewList.get(i % 2));
                ObjectAnimator translationYBack = createBackAnimator(textViewList.get(i % 2));
                ObjectAnimator translationYOut = createOutAnimator(textViewList.get(i % 2));
                AnimatorSet set = new AnimatorSet();
                set.play(translationYIn).before(translationYBack).before(translationYOut);
                mAnimatorSetList.add(set);

                addAnimatorInListener(translationYIn, i);
                addAnimatorBackListener(translationYBack, i);
                addAnimatorOutListener(translationYOut, i);
            }
        }
        return mAnimatorSetList;
    }

    private void addAnimatorInListener(ObjectAnimator translationYIn, final int index) {
        translationYIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                textViewList.get(index % 2).setText(mTexts[index]);
            }
        });
    }

    private void addAnimatorBackListener(ObjectAnimator translationYBack, final int index) {

        if (index % 2 == 0) { //第基数个动画级
            translationYBack.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //圆圈进度条
                    mRotateCirCleView.setOnDraw(true);
                    mRotateCirCleView.setOneCircleTime(400L);
                    new Thread(mRotateCirCleView).start();
                }
            });
        }
    }

    private void addAnimatorOutListener(ObjectAnimator translationYOut, final int index) {

        if (index + 1 < mTexts.length) { //不是最后一个动画

            translationYOut.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mAnimatorSetList.get(index + 1).start();
                }
            });
        } else { //最后一个动画
            translationYOut.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    mEndAnim.start();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mRotateCirCleView.setOnDraw(false);
                }
            });
        }
    }

    private void init(Context ctx) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rotate_circle_progress_layout, null);
        TextView textView1 = (TextView) view.findViewById(R.id.tv_content1);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content2);

        mDialog = new Dialog(ctx, R.style.PADialog);
        //设置成全局的dialog
        mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        //去掉dialog区域外的灰色透明蒙版
        mDialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(view);

        textViewList = new ArrayList<>();
        textViewList.add(textView1);
        textViewList.add(textView2);

        mRotateCirCleView = (RotateCirCleView) view.findViewById(R.id.rotateCirCleView);

        //整个圆圈的动画
        mStartAnim = createStartAnimator(view);
        mEndAnim = createEndAnimator(view);
    }


    public void setTexts(String... texts) {
        if (texts != null) {
            mTexts = texts;
            mAnimatorSetList = createAnimatorSet(mTexts);
        }
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
            mStartAnim.start();
        }
    }
}
