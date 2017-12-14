package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.utils.RandomColor;

/**
 * PhoneActivity的子页面
 * Created by lenovo on 2017/11/1.
 */

public class SubActivity1 extends Activity
{
    private TextView tvUsername, tvPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_subpage1);
        //随机背景颜色
        this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        Bundle bundle = getIntent().getExtras();
        tvUsername = (TextView) findViewById(R.id.tv_phone_subpage1_username);
        tvPassword = (TextView) findViewById(R.id.tv_phone_subpage1_password);
        if (bundle == null)
        {
            tvUsername.setText("输入为空");
            tvPassword.setText("输入为空");
        } else
        {
            tvUsername.setText(bundle.get("username").toString());
            tvPassword.setText(bundle.get("password").toString());
        }
    }
}
