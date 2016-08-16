package com.gx.favorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.gx.favorlayout_favorlayout.AnimImageView;

/**
 * Created by gaoxuan on 2016/8/16.
 */
public class CustomImageView extends AnimImageView {
    private Paint paint;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAlpha(255);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, paint);
    }

    @Override
    public void setColor(int color) {
        paint.setColor(color);
    }
}
