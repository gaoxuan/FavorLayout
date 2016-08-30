package com.gx.favorlayout_favorlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by gaoxuan on 2016/8/14.
 */
public class FavorLayout extends RelativeLayout {

    private static final int DEFAULT_RANGE = 30;
    private static final int DEFAULT_DURATION = 6000;
    private static final int DEFAULT_COUNT_INTERCURRENT = 10;
    private static final int DEFAULT_COUNT_MAX = 200;
    private static final int DEFAULT_NODE_COUNT = 4;
    private static final int DEFAULT_HEART_WIDTH = 40;
    private static final int DEFAULT_CONTENT_WIDTH = 70;
    private static final int DEFAULT_MARGIN_RIGHT = 10;
    private static final int DEFAULT_MARGIN_BOTTOM = 10;

    /**
     * 动画浮动模式
     */
    private static final int SPEED_MODE_LINEAR = 0;
    private static final int SPEED_MODE_ACCELERATE = 1;
    private static final int SPEED_MODE_DECELERATE = 2;
    private static final int SPEED_MODE_ACCELERATEDECELERATE = 3;

    /**
     * 维护一个最大数为DEFAULT_COUNT_MAX的心形池
     */
    private LinkedList<AnimImageView> heartViewList = new LinkedList<>();

    private int nHeartColor = Color.RED;
    private int mDuration;
    private int mSpeedMode;
    private int mIntercurrentHeartCount;
    private int mMaxHeartCount;
    private int mNodeCount;
    private int mHeartWidth;
    private int mMarginRight;
    private int mMarginBottom;
    private int mRange;
    private int currentHeartCount;
    private boolean mTip;

    private Interpolator interpolator;
    private PointF startPoint;
    private LayoutParams layoutParams;
    private Context mContext;
    private LinearLayout contentLL;
    private TextView numTV;
    private ImageView whiteIV;

    private String className; //动态指定动画的对象

    public FavorLayout(Context context) {
        this(context, null);
    }

    public FavorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FavorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttribute(attrs);
        initContentView();
        initHeartParameters();
    }

    private void initHeartParameters() {
        layoutParams = new LayoutParams(mHeartWidth, mHeartWidth);
        layoutParams.addRule(12);
        layoutParams.addRule(11);
    }

    private void initAttribute(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.FavorLayout);
        mDuration = a.getInt(R.styleable.FavorLayout_favorDuration, DEFAULT_DURATION);
        mIntercurrentHeartCount = a.getInt(R.styleable.FavorLayout_favorIntercurrentHeartNum, DEFAULT_COUNT_INTERCURRENT);
        mMaxHeartCount = a.getInt(R.styleable.FavorLayout_favorMaxHeartNum, DEFAULT_COUNT_MAX);
        mNodeCount = a.getInt(R.styleable.FavorLayout_favorNodeNum, DEFAULT_NODE_COUNT);
        mRange = (int) (a.getDimension(R.styleable.FavorLayout_favorRangeWidth, Util.getPxFromDp(DEFAULT_RANGE, mContext)) + 0.5);
        mHeartWidth = (int) (a.getDimension(R.styleable.FavorLayout_favorHeartWidth, Util.getPxFromDp(DEFAULT_HEART_WIDTH, mContext)) + 0.5);
        if (mHeartWidth < Util.getPxFromDp(DEFAULT_HEART_WIDTH, mContext)) { //如果小于这个值心形的构造会不完整
            mHeartWidth = Util.getPxFromDp(DEFAULT_HEART_WIDTH, mContext);
        }
        mMarginRight = (int) (a.getDimension(R.styleable.FavorLayout_favorMarginRight, Util.getPxFromDp(DEFAULT_MARGIN_RIGHT, mContext)) + 0.5);
        mMarginBottom = (int) (a.getDimension(R.styleable.FavorLayout_favorMarginBottom, Util.getPxFromDp(DEFAULT_MARGIN_BOTTOM, mContext)) + 0.5);
        mTip = a.getBoolean(R.styleable.FavorLayout_favorTip, true);
        mSpeedMode = a.getInt(R.styleable.FavorLayout_favorSpeedMode, 0);
        switch (mSpeedMode) {
            case SPEED_MODE_LINEAR:
                interpolator = new LinearInterpolator();
                break;
            case SPEED_MODE_ACCELERATE:
                interpolator = new AccelerateInterpolator();
                break;
            case SPEED_MODE_DECELERATE:
                interpolator = new DecelerateInterpolator();
                break;
            case SPEED_MODE_ACCELERATEDECELERATE:
                interpolator = new AccelerateDecelerateInterpolator();
        }
    }

    private void initContentView() {
        initLinearLayout();
        initImageView();
        initTextView();
        changeTipShow();
    }

    private void changeTipShow() {
        if (mTip) {
            whiteIV.setVisibility(VISIBLE);
            numTV.setVisibility(VISIBLE);
        } else {
            whiteIV.setVisibility(GONE);
            numTV.setVisibility(GONE);
        }
    }

    private void initImageView() {
        whiteIV = new ImageView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        whiteIV.setLayoutParams(layoutParams);
        whiteIV.setImageResource(R.drawable.start);
        contentLL.addView(whiteIV);
    }

    private void initTextView() {
        numTV = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        numTV.setLayoutParams(layoutParams);
        numTV.setTextColor(Color.WHITE);
        numTV.setTextSize(14);
        numTV.setText(String.valueOf(0));
        contentLL.addView(numTV);
    }

    private void initLinearLayout() {
        contentLL = new LinearLayout(mContext);
        LayoutParams layoutParams = new LayoutParams(Util.getPxFromDp(DEFAULT_CONTENT_WIDTH, mContext), ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_RIGHT);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(0, 0, mMarginRight, mMarginBottom);
        contentLL.setLayoutParams(layoutParams);
        contentLL.setOrientation(LinearLayout.VERTICAL);
        addView(contentLL);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (startPoint == null) {
            int x = getMeasuredWidth() - contentLL.getMeasuredWidth() + contentLL.getMeasuredWidth() / 2 - mHeartWidth / 2 - mMarginRight;
            int y = getMeasuredHeight() - contentLL.getMeasuredHeight() - mHeartWidth / 2 - mMarginBottom;
            startPoint = new PointF(x, y);
        }
    }

    /**
     * 有两种方式添加心形，设置一个总数，或者每次加一
     *
     * @param count
     * @param addOne   是不是每次加一
     * @param username
     */
    public void setFavor(int count, boolean addOne, String username) {
        if (null == numTV) return;
        if (username == null) {
            numTV.setText(Util.calcHeartValueInTV(count));
            currentHeartCount = count;
            return;
        }
        setFavorColor(username);

        int currentViewCount = mMaxHeartCount + 1 - getChildCount();
        if (addOne) {
            synchronized (FavorLayout.class) {
                ++currentHeartCount;
                numTV.setText(Util.calcHeartValueInTV(currentHeartCount));
                if (currentViewCount > 0)
                    addFavor();
            }
        } else {
            synchronized (FavorLayout.class) {
                int step = count - currentHeartCount;
                if (step > mIntercurrentHeartCount)
                    step = mIntercurrentHeartCount;
                else
                    step = currentViewCount;

                for (int i = 1; i <= step; ++i)
                    addFavor();
                currentHeartCount = count;
                numTV.setText(Util.calcHeartValueInTV(currentHeartCount));
            }
        }
    }

    public void setDefaultPraiseCount(int currentPraiseCount) {
        currentHeartCount = currentPraiseCount;
    }

    private void setFavorColor(String username) {
        nHeartColor = Util.BKDRHash(username);
    }

    public void setViewType(String className) {
        this.className = className;
    }

    public void removeAllHeartView() {
        if (heartViewList != null) {
            Iterator<AnimImageView> it = heartViewList.iterator();
            while (it.hasNext()) {
                removeView(it.next());
            }
        }
    }

    private void addFavor() {
        AnimImageView heartView;
        if (heartViewList.size() < mMaxHeartCount) {
            heartView = getHeartViewFormClassName(className);
            heartViewList.addFirst(heartView);
        } else {
            if (!heartViewList.getLast().isAnimEnded()) return;
            heartView = heartViewList.removeLast();
            heartViewList.addFirst(heartView);
        }
        heartView.setAnimEnded(false);
        heartView.setColor(nHeartColor);
        heartView.setLayoutParams(layoutParams);
        addView(heartView);
        Animator animator = getAnimator(heartView);
        animator.addListener(new AnimEndListener(heartView));
        animator.start();
    }

    private AnimImageView getHeartViewFormClassName(String className) {
        if (TextUtils.isEmpty(className))
            return new HeartView(mContext);
        else {
            try {
                Class c = Class.forName(className);
                Constructor constructor = c.getDeclaredConstructor(Context.class);
                return (AnimImageView) constructor.newInstance(mContext);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Animator getAnimator(AnimImageView heartView) {
        AnimatorSet set = getStartAnimator(heartView);
        ValueAnimator pathAnim = cratePathAnimation(heartView);
        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playTogether(set, pathAnim);
        finalSet.setInterpolator(interpolator);
        finalSet.setTarget(heartView);
        return finalSet;
    }

    private ValueAnimator cratePathAnimation(AnimImageView heartView) {
        Path path = Util.buildPath(startPoint, mNodeCount, (int) startPoint.y, mRange);
        ObjectAnimator animator = ObjectAnimator.ofFloat(heartView, "value", 0f, 1f);
        animator.addUpdateListener(new PathListener(heartView, path));
        animator.setTarget(heartView);
        animator.setDuration(mDuration);
        return animator;
    }

    private AnimatorSet getStartAnimator(AnimImageView heartView) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(heartView, View.SCALE_X, 0.1f, 1.4f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(heartView, View.SCALE_Y, 0.1f, 1.4f, 1f);
        AnimatorSet start = new AnimatorSet();
        start.setDuration(500);
        start.setInterpolator(new LinearInterpolator());
        start.playTogether(scaleX, scaleY);
        start.setTarget(heartView);
        return start;
    }

    private class AnimEndListener extends AnimatorListenerAdapter {
        private AnimImageView heartView;

        public AnimEndListener(AnimImageView heartView) {
            this.heartView = heartView;
        }

        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView(heartView);
            heartView.setAnimEnded(true);
        }
    }

    /**
     * 根据插值器产生的值改变View的坐标
     */
    class PathListener implements ValueAnimator.AnimatorUpdateListener {
        AnimImageView heartView;
        PathMeasure pathMeasure;

        public PathListener(AnimImageView heartView, Path path) {
            this.heartView = heartView;
            pathMeasure = new PathMeasure();
            pathMeasure.setPath(path, false);
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            float[] pos = new float[2];
            pathMeasure.getPosTan(startPoint.y * heartView.getValue(), pos, null);
            heartView.setX(pos[0]);
            heartView.setY(pos[1]);
            heartView.setAlpha(1.0f - heartView.getValue());
        }
    }
}
