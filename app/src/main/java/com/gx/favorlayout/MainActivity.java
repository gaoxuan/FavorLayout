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
        favorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    favorLayout.setFavor(0, true, "name" + random.nextInt(30));
                /**
                 * 另一种添加心形的方式
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
