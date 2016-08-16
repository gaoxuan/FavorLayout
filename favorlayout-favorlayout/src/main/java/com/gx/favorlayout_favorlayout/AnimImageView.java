package com.gx.favorlayout_favorlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by gaoxuan on 2016/8/16.
 */
public abstract class AnimImageView extends ImageView {
    private float value;
    private boolean isAnimEnded;

    public AnimImageView(Context context) {
        super(context);
    }

    public AnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setAnimEnded(boolean isAnimEnded) {
        this.isAnimEnded = isAnimEnded;
    }

    public boolean isAnimEnded() {
        return isAnimEnded;
    }

    public abstract void setColor(int color);
}
