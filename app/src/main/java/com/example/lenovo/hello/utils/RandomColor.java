package com.example.lenovo.hello.utils;

import android.graphics.Color;
import android.util.Log;

import java.util.Random;

/**
 * Created by lenovo on 2017/10/31.
 */

public class RandomColor
{
    private RandomColor(){}

    public static int randomColorInt()
    {
        Random random = new Random(System.currentTimeMillis());
        String rgb = "#", tmp;
        for (int i = 0; i < 3; i++)
        {
            tmp = Integer.toHexString(random.nextInt(256)).toUpperCase();
            tmp = (tmp.length() == 1) ? '0' + tmp : tmp;
            rgb += tmp;
        }
        return Color.parseColor(rgb);
    }

    public  static int changeAlpha(int color,int alpha)
    {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return Color.argb(alpha,r,g,b);
    }
}
