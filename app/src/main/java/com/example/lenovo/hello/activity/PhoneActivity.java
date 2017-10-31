package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.RandomColor;

/**
 * Created by lenovo on 2017/10/31.
 */

public class PhoneActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_phone);
        //随机背景颜色
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
    }
}
