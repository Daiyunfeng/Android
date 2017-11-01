package com.example.lenovo.hello.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/10/31.
 */

public class MyToast
{
    private static Toast mToast;
    private MyToast(){}

    public static void showText(Context activity, String text)
    {
        if(mToast == null)
        {
            mToast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
        }
        else
        {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void showText(Context activity, String text,int length)
    {
        if(mToast == null)
        {
            mToast = Toast.makeText(activity, text, length);
        }
        else
        {
            mToast.setText(text);
            mToast.setDuration(length);
        }
        mToast.show();
    }
}
