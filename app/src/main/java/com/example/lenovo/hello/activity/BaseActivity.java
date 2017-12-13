package com.example.lenovo.hello.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.lenovo.hello.R;

/**
 * Created by lenovo on 2017/12/11.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multithread);
        setContentView(getLayoutID());
        init();
    }

    protected abstract int getLayoutID();

    protected abstract void init();
}
