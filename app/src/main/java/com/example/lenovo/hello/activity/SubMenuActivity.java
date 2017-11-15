package com.example.lenovo.hello.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.lenovo.hello.R;

/**
 * Created by lenovo on 2017/11/14.
 */

public class SubMenuActivity extends AppCompatActivity
{
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_submenu);
        //随机背景颜色
        //this.getWindow().getDecorView().setBackgroundColor(RandomColor.randomColorInt());
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_menu);
        TextView textView = (TextView) findViewById(R.id.tb_title);
        textView.setText("大标题");
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
