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
    private static final int[] ARGB_COLOR = new int[]{0x65ff7de0, 0x65ff8206, 0x656927da,
            0x6500daef, 0x6500ef4d, 0x65fd003e, 0x65ff0000, 0x65b30208, 0x65be0f14,
            0x65f3080d, 0x65e00130, 0x65e10164, 0x65e10195, 0x65e001de, 0x65e101de,
            0x657501e0, 0x653b01e1, 0x651101e0, 0x650026e1, 0x650255e1, 0x650180e1,
            0x6501aae1, 0x6500dee1, 0x6500dab2, 0x6501e189, 0x6502e10c, 0x653be101,
            0x6585e102, 0x65b9e202, 0x65d9e100, 0x65e0c401, 0x65e16b01, 0x65e13b01,
            0x65e11b01, 0x65ae164f, 0x65c11456};

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
