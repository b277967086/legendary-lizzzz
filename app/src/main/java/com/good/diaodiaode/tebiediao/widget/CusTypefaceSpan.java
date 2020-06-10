//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.good.diaodiaode.tebiediao.widget;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class CusTypefaceSpan extends MetricAffectingSpan {
    private final Typeface typeface;

    public CusTypefaceSpan(Typeface typeface) {
        this.typeface = typeface;
    }

    public void updateDrawState(TextPaint drawState) {
        this.apply(drawState);
    }

    public void updateMeasureState(TextPaint paint) {
        this.apply(paint);
    }

    private void apply(Paint paint) {
        Typeface oldTypeface = paint.getTypeface();
        int oldStyle = oldTypeface != null ? oldTypeface.getStyle() : 0;
        int fakeStyle = oldStyle & ~this.typeface.getStyle();
        if ((fakeStyle & 1) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fakeStyle & 2) != 0) {
            paint.setTextSkewX(-0.25F);
        }

        paint.setTypeface(this.typeface);
    }
}
