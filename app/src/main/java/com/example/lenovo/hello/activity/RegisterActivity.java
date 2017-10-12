package com.example.lenovo.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lenovo.hello.R;

/**
 * Created by lenovo on 2017/9/21.
 */

public class RegisterActivity extends Activity
{
    //    TextView textView = (TextView)findViewById(R.id.tx_register_title);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
