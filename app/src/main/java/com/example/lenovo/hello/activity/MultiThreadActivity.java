package com.example.lenovo.hello.activity;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.hello.R;

/**
 * Created by lenovo on 2017/11/16.
 */

public class MultiThreadActivity extends AppCompatActivity
{
    private final static String TAG = "MultiThreadActivity";
    private final static int TV_MULTI_THREAD = 1;
    private int n;
    private boolean stopFlag;
    private Button simpleThread, multiThread, endThread;
    private TextView tvSimpleThread,tvMultiThread;
    private Runnable runnable;
    private Handler handler ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multithread);
        init();
    }

    private void init()
    {
        n = 3000;
        handler = new MyHandle();
        simpleThread = (Button) findViewById(R.id.btn_simple_thread);
        multiThread = (Button) findViewById(R.id.btn_multi_thread);
        endThread = (Button) findViewById(R.id.btn_end_thread);
        tvSimpleThread = (TextView) findViewById(R.id.tv_simple_thread);
        tvMultiThread = (TextView) findViewById(R.id.tv_multi_thread);
        tvSimpleThread.setText("0");
        tvMultiThread.setText("0");

        stopFlag=true;
        simpleThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int t, sum = Integer.parseInt(tvMultiThread.getText().toString());
                for (int i = 0; i <= n; i++)
                {
                    t = (int) 1e5;
                    while (t-- != 0) ;
                    sum+=i;
                    tvSimpleThread.setText(""+sum);
                }
            }
        });

        multiThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (stopFlag)
                {
                    stopFlag=false;
                    runnable = new MyRunnable();
                    new Thread(runnable).start();
                }
            }
        });

        endThread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                stopFlag = true;
            }
        });
    }

    private class MyRunnable implements Runnable
    {

        @Override
        public void run()
        {
            int t, sum = Integer.parseInt(tvMultiThread.getText().toString());
            for (int i = 0; i <= n; i++)
            {
                if(stopFlag == true)
                {
                    return;
                }
                t = (int) 1e5;
                while (t-- != 0) ;
                sum+=i;
//                tvSimpleThread.setText(""+sum);   //子线程
                Message message = Message.obtain();
                message.what = TV_MULTI_THREAD;
                message.obj = ""+sum;
                handler.sendMessage(message);
            }
            stopFlag = true;
        }
    }

    private class MyHandle extends Handler
{
    @Override
    public void handleMessage(Message msg)
    {
        if(msg.what == TV_MULTI_THREAD)
        {
            tvMultiThread.setText(msg.obj.toString());
        }
    }
};
}
