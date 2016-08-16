package com.gx.favorlayout_favorlayout;

import android.content.Context;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gaoxuan on 2016/8/14.
 */
public class Util {
    private static Random random = new Random();
    private static final int[] ARGB_COLOR = new int[]{0xff7de0, 0xff8206, 0x6927da,
            0x00daef, 0x00ef4d, 0xfd003e, 0xff0000, 0xb30208, 0xbe0f14, 0xf3080d,
            0xe00130, 0xe10164, 0xe10195, 0xe001de, 0xe101de, 0x7501e0, 0x3b01e1,
            0x1101e0, 0x0026e1, 0x0255e1, 0x0180e1, 0x01aae1, 0x00dee1, 0x00dab2,
            0x01e189, 0x02e10c, 0x3be101, 0x85e102, 0xb9e202, 0xd9e100, 0xe0c401,
            0xe16b01, 0xe13b01, 0xe11b01, 0xae164f, 0xc11456};

    public static int getPxFromDp(int dpSize, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, context.getResources().getDisplayMetrics());
    }

    public static Path buildPath(PointF p, int nodeCount, int height, int range) {
        List<PointF> points = new ArrayList();
        for (int i = 0; i <= nodeCount; ++i) {
            if (i == 0) {
                points.add(p);
            } else {
                PointF temp = new PointF(0, 0);
                if (random.nextInt(100) % 2 == 0) {
                    temp.x = p.x + random.nextInt(range);
                } else {
                    temp.x = p.x - random.nextInt(range);
                }

                temp.y = height - height / nodeCount * i;
                points.add(temp);
            }
        }

        Path path = new Path();
        if (points.size() > 1) {
            for (int j = 0; j < points.size(); ++j) {
                PointF point = points.get(j);
                if (j == 0) {
                    path.moveTo(point.x, point.y);
                } else {
                    PointF prev = points.get(j - 1);
                    path.quadTo(prev.x, prev.y, point.x, point.y);
                }
            }
        }

        return path;
    }

    public static String calcHeartValueInTV(int size) {
        String result = "";
        if (size < 10000) {
            result = String.valueOf(size);
        } else {
            int value = size / 1000;
            int f = (size - value * 1000) / 100;
            if (f > 0)
                result = value + "." + f + "k";
            else
                result = value + "k";
        }

        return result;
    }

    public static int BKDRHash(String str) {
        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hash = 0;
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                hash = (hash * seed) + str.charAt(i);
            }
        }
        int value = (hash & 0x7FFFFFFF) % ARGB_COLOR.length;
        return ARGB_COLOR[value];
    }
}
