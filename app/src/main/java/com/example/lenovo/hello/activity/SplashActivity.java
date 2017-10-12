package com.example.lenovo.hello.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.lenovo.hello.R;
import com.example.lenovo.hello.database.DBManager;
import com.example.lenovo.hello.view.CircleProgressBar;

import org.litepal.tablemanager.Connector;

/**
 * Created by lenovo on 2017/9/28.
 */

public class SplashActivity extends Activity
{
    private static final String TAG = "SplashActivity";
    private CircleProgressBar circleProgressBar;
//    private CountDownTimer countDownTimer = new CountDownTimer(3000, 1000)
//    {
//        @Override
//        public void onTick(long l)
//        {
//
//        }
//
//        @Override
//        public void onFinish()
//        {
//            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();   //不能返回回来
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.cpb_timer);
        circleProgressBar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                circleProgressBar.cancelAnim(); //不然会调用end
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();   //不能返回回来
            }
        });
        circleProgressBar.setAnimatorListener(new MyAnimatorListener());
        circleProgressBar.startAnim();
        init();
//        countDownTimer.start();
//        Log.i(TAG, "1234");
    }

    @Override
    public void onBackPressed()
    {
        //do nothing
    }

    //整个app需要初始化的操作
    private void init()
    {
        Connector.getDatabase();
        new DBManager(this).copyDatabase();
    }

    private class MyAnimatorListener implements AnimatorListener
    {

        @Override
        public void onAnimationStart(Animator animation)
        {

        }

        @Override
        public void onAnimationEnd(Animator animation)  //计时结束
        {
            Log.i(TAG, "end");
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();   //不能返回回来
        }

        @Override
        public void onAnimationCancel(Animator animation)
        {

        }

        @Override
        public void onAnimationRepeat(Animator animation)
        {

        }
    }
}
