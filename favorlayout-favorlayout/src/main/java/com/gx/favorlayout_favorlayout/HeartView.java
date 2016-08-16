package com.gx.favorlayout_favorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by gaoxuan on 2016/8/14.
 */
public class HeartView extends AnimImageView {
//    private float value;
    private Paint paint;
//    private boolean isAnimEnded;

    public HeartView(Context context) {
        super(context);
        paint = new Paint();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    public void setColor(int color) {
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(255);
        paint.setAntiAlias(true);
        //定义心形大小
        float scale = 2 * getResources().getDisplayMetrics().density / 3;
        path.moveTo(30 * scale, 18 * scale);
        path.cubicTo(45 * scale, -2 * scale, 74 * scale, 21 * scale, 30 * scale,
                50 * scale);
        path.moveTo(30 * scale, 18 * scale);
        path.cubicTo(15 * scale, -2 * scale, -14 * scale, 21 * scale, 30 * scale, 50 * scale);
        canvas.drawPath(path, paint);
        paint.setStrokeWidth(2);  //边框宽度
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
    }

//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        this.setMeasuredDimension(width, height);
//    }

//    public void setColor(int color) {
//        this.paint.setColor(color);
//    }

//    public void setValue(float value) {
//        this.value = value;
//    }
//
//    public float getValue() {
//        return value;
//    }
//
//    public void setAnimEnded(boolean isAnimEnded) {
//        this.isAnimEnded = isAnimEnded;
//    }
//
//    public boolean isAnimEnded() {
//        return isAnimEnded;
//    }
}
