package com.gx.favorlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.gx.favorlayout_favorlayout.FavorLayout;

import java.util.Random;

public class MainActivity extends Activity {
    private FavorLayout favorLayout;
    private int heartCount;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favorLayout = (FavorLayout) findViewById(R.id.favor);
        favorLayout.setDefaultPraiseCount(0);
        /**
         * 如果要使用自定义的图形的话调用FavorLayout的setViewType方法，例如下面用了一个自定义的图形
         *
         */
        favorLayout.setViewType(CustomImageView.class.getName());
        favorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    favorLayout.setFavor(0, true, "name" + random.nextInt(30));
                /**
                 * 另一种添加心形的方式，可以一次出现多个心形，最大并发数可以在xml里面设置app:favorIntercurrentHeartNum参数
                 * favorLayout.setFavor(heartCount, false, "name" + random.nextInt(30));
                 */
                heartCount += 100;
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favorLayout.removeAllHeartView();
    }
}
